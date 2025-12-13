package dev.greendev.neobots;

import com.mojang.logging.LogUtils;
import dev.greendev.neobots.entity.BotEntity;
import dev.greendev.neobots.entity.ModEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import org.slf4j.Logger;

/**
 * Main mod entrypoint.
 */
@Mod(NeoBots.MODID)
public class NeoBots {

    public static final String MODID = "neobots";
    public static final Logger LOGGER = LogUtils.getLogger();

    public NeoBots(IEventBus modEventBus) {
        // Register entities
        ModEntity.register(modEventBus);

        // Register lifecycle events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::entityAttributes);
        modEventBus.addListener(this::spawnPlacements);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Common initialization (server + client)
        LOGGER.info("NeoBots common setup complete");
    }

    private void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntity.BOT.get(), BotEntity.createAttributes().build());
    }

    private void spawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                ModEntity.BOT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BotEntity::checkMobSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.AND
        );
    }
}