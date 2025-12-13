package dev.greendev.neobots.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.UUID;

/**
 * A visible bot entity that looks like a player.
 * This entity can be seen by all players and appears in the world.
 */
public class BotEntity extends PathfinderMob {

    private static final EntityDataAccessor<String> DATA_NAME =
            SynchedEntityData.defineId(BotEntity.class, EntityDataSerializers.STRING);

    private GameProfile gameProfile;

    public BotEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.setPersistenceRequired();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_NAME, "Bot");
    }

    public void setBotName(String name) {
        this.entityData.set(DATA_NAME, name);
        this.setCustomName(net.minecraft.network.chat.Component.literal(name));
        this.setCustomNameVisible(true);
    }

    public String getBotName() {
        return this.entityData.get(DATA_NAME);
    }

    public void setGameProfile(GameProfile profile) {
        this.gameProfile = profile;
    }

    public GameProfile getGameProfile() {
        return this.gameProfile;
    }

    /**
     * New (1.21+) save: use ValueOutput instead of CompoundTag.
     * We store strings for profile UUID/name to avoid differing helper methods.
     */
    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);

        String name = getBotName();
        if (name != null && !name.isEmpty()) {
            output.putString("BotName", name);
        }

        if (gameProfile != null) {
            if (gameProfile.getId() != null) {
                output.putString("ProfileUUID", gameProfile.getId().toString());
            }
            if (gameProfile.getName() != null) {
                output.putString("ProfileName", gameProfile.getName());
            }
        }
    }

    /**
     * New (1.21+) load: use ValueInput instead of CompoundTag.
     * Use getStringOr to provide safe defaults.
     */
    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);

        String botName = input.getStringOr("BotName", "");
        if (!botName.isEmpty()) {
            setBotName(botName);
        }

        String uuidStr = input.getStringOr("ProfileUUID", "");
        if (uuidStr != null && !uuidStr.isEmpty()) {
            try {
                UUID uuid = UUID.fromString(uuidStr);
                String profileName = input.getStringOr("ProfileName", "");
                this.gameProfile = new GameProfile(uuid, profileName);
            } catch (IllegalArgumentException ignored) {
                // malformed UUID in saved data — ignore
            }
        }
    }

    @Override
    protected void registerGoals() {
        // No AI goals for now - bots are stationary
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false; // Never despawn naturally
    }

    /**
     * Bots can spawn anywhere - used for spawn placement registration.
     * Signature matches 1.21: uses ServerLevelAccessor, MobCategory, BlockPos, RandomSource.
     */
    public static boolean checkMobSpawnRules(
            EntityType<? extends BotEntity> entityType,
            ServerLevelAccessor level,
            MobCategory spawnType,
            BlockPos pos,
            RandomSource random) {
        return true; // Bots can always spawn when commanded
    }
}
