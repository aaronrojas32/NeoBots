package dev.greendev.neobots;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

/**
 * Main mod entrypoint.
 */
@Mod(NeoBots.MODID)
public class NeoBots {

    public static final String MODID = "neobots";
    public static final Logger LOGGER = LogUtils.getLogger();

    public NeoBots(IEventBus modEventBus) {
        // Register common lifecycle events
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Common initialization (server + client)
    }
}
