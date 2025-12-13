package dev.greendev.neobots.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

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

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("BotName", getBotName());
        if (gameProfile != null) {
            tag.putUUID("ProfileUUID", gameProfile.getId());
            tag.putString("ProfileName", gameProfile.getName());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("BotName")) {
            setBotName(tag.getString("BotName"));
        }
        if (tag.contains("ProfileUUID")) {
            UUID uuid = tag.getUUID("ProfileUUID");
            String name = tag.getString("ProfileName");
            this.gameProfile = new GameProfile(uuid, name);
        }
    }

    @Override
    protected void registerGoals() {
        // No AI goals for now - bots are stationary
        // You can add goals here later for movement, following, etc.
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false; // Never despawn naturally
    }

    /**
     * Bots can spawn anywhere - used for spawn placement registration
     */
    public static boolean checkMobSpawnRules(
            EntityType<? extends BotEntity> entityType,
            net.minecraft.world.level.ServerLevelAccessor level,
            net.minecraft.world.entity.MobSpawnType spawnType,
            net.minecraft.core.BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return true; // Bots can always spawn when commanded
    }
}