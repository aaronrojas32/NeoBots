package dev.greendev.neobots.bot;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages all bots in the server.
 * This class acts as the brain of the bot system, providing a single point
 * of control for creating, destroying, and accessing bots.
 */
public class BotManager {

    private final MinecraftServer server;
    private final Map<String, NeoBot> botsByName;

    /**
     * Constructs a new BotManager for the given server.
     *
     * @param server the Minecraft server instance
     */
    public BotManager(MinecraftServer server) {
        this.server = server;
        this.botsByName = new ConcurrentHashMap<>();
    }

    /**
     * Spawns a new bot at the position of the source player.
     *
     * @param source the player whose position will be used to spawn the bot
     * @param name   the name for the bot
     * @return the newly created and spawned NeoBot
     * @throws IllegalArgumentException if a bot with that name already exists
     * @throws IllegalStateException    if the source player's level is not a ServerLevel
     */
    public NeoBot spawnBot(ServerPlayer source, String name) {
        // Check if bot name already exists
        if (exists(name)) {
            throw new IllegalArgumentException("A bot with the name '" + name + "' already exists");
        }

        // Get the level where the bot will spawn
        ServerLevel level = (ServerLevel) source.level();

        // Create immutable bot data from source player's position
        BotData data = new BotData(
                UUID.randomUUID(),
                name,
                level.dimension().location().toString(),
                source.getX(),
                source.getY(),
                source.getZ(),
                source.getYRot(),
                source.getXRot()
        );

        // Create the bot instance
        NeoBot bot = new NeoBot(data, level);

        // Spawn the bot in the world
        bot.spawn();

        // Register the bot in the manager
        botsByName.put(name, bot);

        return bot;
    }

    /**
     * Despawns and removes a bot from the manager.
     *
     * @param name the name of the bot to despawn
     */
    public void despawnBot(String name) {
        NeoBot bot = botsByName.remove(name);
        if (bot != null) {
            bot.despawn();
        }
    }

    /**
     * Retrieves a bot by its name.
     *
     * @param name the name of the bot
     * @return the NeoBot instance, or null if not found
     */
    public NeoBot getBot(String name) {
        return botsByName.get(name);
    }

    /**
     * Returns all currently active bots.
     *
     * @return an immutable collection of all bots
     */
    public Collection<NeoBot> getAllBots() {
        return botsByName.values();
    }

    /**
     * Checks if a bot with the given name exists.
     *
     * @param name the name to check
     * @return true if a bot with that name exists, false otherwise
     */
    public boolean exists(String name) {
        return botsByName.containsKey(name);
    }

    /**
     * Despawns all bots and clears the manager.
     * This method ensures complete cleanup of all bot resources.
     * Useful for:
     * - Server shutdown
     * - World unload
     * - Mod reload
     * - Emergency cleanup
     */
    public void despawnAll() {
        // Create a copy to avoid ConcurrentModificationException
        for (String name : botsByName.keySet().toArray(new String[0])) {
            despawnBot(name);
        }
    }
}