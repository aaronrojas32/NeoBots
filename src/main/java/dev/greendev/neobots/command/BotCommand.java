package dev.greendev.neobots.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.greendev.neobots.bot.BotManager;
import dev.greendev.neobots.bot.NeoBot;
import dev.greendev.neobots.event.ServerLifecycleEvents;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * Registers and handles the /bot command and its subcommands.
 * This class translates user input into calls to BotManager.
 * It does NOT contain bot logic or create FakePlayers directly.
 * Works for both dedicated servers and single-player worlds.
 */
public class BotCommand {

    /**
     * Registers the /bot command with all its subcommands.
     * In single-player, the player automatically has operator permissions.
     * In multiplayer, requires operator level 4.
     *
     * @param dispatcher the command dispatcher
     */
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("bot")
                        .requires(source -> {
                            // In single-player, the player is always an operator
                            // In multiplayer, requires level 4
                            return source.hasPermission(2); // Level 2 allows usage in single-player and for ops
                        })
                        .then(Commands.literal("spawn")
                                .then(Commands.argument("name", StringArgumentType.word())
                                        .executes(BotCommand::spawnBot)
                                )
                        )
                        .then(Commands.literal("despawn")
                                .then(Commands.argument("name", StringArgumentType.word())
                                        .executes(BotCommand::despawnBot)
                                )
                        )
                        .then(Commands.literal("list")
                                .executes(BotCommand::listBots)
                        )
        );
    }

    /**
     * Handles the /bot spawn <name> command.
     * Spawns a bot at the player's position.
     *
     * @param context the command context
     * @return 1 if successful, 0 otherwise
     * @throws CommandSyntaxException if the command source is not a player
     */
    private static int spawnBot(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getPlayerOrException();
        String name = StringArgumentType.getString(context, "name");

        BotManager botManager = ServerLifecycleEvents.getBotManager();
        if (botManager == null) {
            source.sendFailure(Component.literal("BotManager is not initialized"));
            return 0;
        }

        try {
            NeoBot bot = botManager.spawnBot(player, name);
            source.sendSuccess(
                    () -> Component.literal("Bot '" + name + "' spawned successfully"),
                    true
            );
            return 1;
        } catch (IllegalArgumentException e) {
            source.sendFailure(Component.literal("Error: " + e.getMessage()));
            return 0;
        }
    }

    /**
     * Handles the /bot despawn <name> command.
     * Removes a bot from the world.
     *
     * @param context the command context
     * @return 1 if successful, 0 otherwise
     */
    private static int despawnBot(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        String name = StringArgumentType.getString(context, "name");

        BotManager botManager = ServerLifecycleEvents.getBotManager();
        if (botManager == null) {
            source.sendFailure(Component.literal("BotManager is not initialized"));
            return 0;
        }

        if (!botManager.exists(name)) {
            source.sendFailure(Component.literal("Bot '" + name + "' does not exist"));
            return 0;
        }

        botManager.despawnBot(name);
        source.sendSuccess(
                () -> Component.literal("Bot '" + name + "' despawned successfully"),
                true
        );
        return 1;
    }

    /**
     * Handles the /bot list command.
     * Lists all currently active bots.
     *
     * @param context the command context
     * @return 1 if successful
     */
    private static int listBots(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        BotManager botManager = ServerLifecycleEvents.getBotManager();
        if (botManager == null) {
            source.sendFailure(Component.literal("BotManager is not initialized"));
            return 0;
        }

        var bots = botManager.getAllBots();

        if (bots.isEmpty()) {
            source.sendSuccess(
                    () -> Component.literal("No bots are currently active"),
                    false
            );
            return 1;
        }

        source.sendSuccess(
                () -> Component.literal("Active bots (" + bots.size() + "):"),
                false
        );

        for (NeoBot bot : bots) {
            String botInfo = String.format("  - %s (UUID: %s)",
                    bot.getData().name(),
                    bot.getData().id().toString()
            );
            source.sendSuccess(
                    () -> Component.literal(botInfo),
                    false
            );
        }

        return 1;
    }
}