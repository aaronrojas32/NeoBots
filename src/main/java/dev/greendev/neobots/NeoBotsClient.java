package dev.greendev.neobots;

import dev.greendev.neobots.client.BotRender;
import dev.greendev.neobots.entity.ModEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/**
 * Client-only initialization.
 * This class is never loaded on a dedicated server.
 */
@Mod(value = NeoBots.MODID, dist = Dist.CLIENT)
public class NeoBotsClient {

    public NeoBotsClient(ModContainer container, IEventBus modEventBus) {
        // Allows NeoForge to create a config screen for this mod
        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                ConfigurationScreen::new
        );

        // Register entity renderers
        modEventBus.addListener(this::registerRenderers);
    }

    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.BOT.get(), BotRender::new);
    }
}