package dev.greendev.neobots.event;

import dev.greendev.neobots.command.BotCommand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * Handles mod event registration.
 * This class is responsible for registering commands and other mod features.
 */
@EventBusSubscriber(modid = "neobots")
public class ModEvents {

    /**
     * Registers all mod commands.
     * This method is called when the server is registering commands.
     *
     * @param event the command registration event
     */
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        BotCommand.register(event.getDispatcher());
    }
}