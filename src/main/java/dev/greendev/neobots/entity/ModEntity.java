package dev.greendev.neobots.entity;

import dev.greendev.neobots.NeoBots;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registers all custom entities for the mod.
 */
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, NeoBots.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<BotEntity>> BOT =
            ENTITIES.register("bot", () -> EntityType.Builder.of(BotEntity::new, MobCategory.MISC)
                    .sized(0.6F, 1.8F) // Player size
                    .clientTrackingRange(10)
                    .updateInterval(3)
                    .build("bot"));

    public static void register(IEventBus modEventBus) {
        ENTITIES.register(modEventBus);
    }
}