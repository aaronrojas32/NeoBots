package dev.greendev.neobots;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/**
 * Client-only initialization.
 * This class is never loaded on a dedicated server.
 */
@Mod(value = NeoBots.MODID, dist = Dist.CLIENT)
public class NeoBotsClient {

    public NeoBotsClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod
        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                ConfigurationScreen::new
        );
    }
}