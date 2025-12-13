package dev.greendev.neobots.event;

import dev.greendev.neobots.bot.BotManager;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

/**
 * Manages the server lifecycle and exposes the MinecraftServer instance.
 * This class handles initialization and cleanup of the bot system.
 * Works for both dedicated servers and single-player worlds.
 */
@EventBusSubscriber(modid = "neobots")
public class ServerLifecycleEvents {

    private static MinecraftServer server;
    private static BotManager botManager;

    /**
     * Called when the server is starting (dedicated server or integrated server).
     * Initializes the server reference and creates the BotManager instance.
     *
     * @param event the server starting event
     */
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        server = event.getServer();
        botManager = new BotManager(server);
    }

    /**
     * Called when the server is stopping (dedicated server or integrated server).
     * Despawns all bots and cleans up references.
     *
     * @param event the server stopping event
     */
    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        if (botManager != null) {
            botManager.despawnAll();
            botManager = null;
        }
        server = null;
    }

    /**
     * Returns the current MinecraftServer instance.
     * This works for both dedicated servers and single-player integrated servers.
     *
     * @return the server instance, or null if not started
     */
    public static MinecraftServer getServer() {
        return server;
    }

    /**
     * Returns the BotManager instance.
     *
     * @return the bot manager, or null if not initialized
     */
    public static BotManager getBotManager() {
        return botManager;
    }
}