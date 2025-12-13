package dev.greendev.neobots.bot;

import com.mojang.authlib.GameProfile;
import dev.greendev.neobots.entity.BotEntity;
import dev.greendev.neobots.entity.ModEntity;
import net.minecraft.server.level.ServerLevel;

/**
 * Represents a server-side bot backed by a BotEntity.
 * This class is the only one allowed to interact directly with BotEntity.
 */
public class NeoBot {

    private final BotData data;
    private final ServerLevel level;
    private BotEntity botEntity;

    public NeoBot(BotData data, ServerLevel level) {
        this.data = data;
        this.level = level;
        this.botEntity = null;
    }

    /**
     * Spawns the bot in the world using its stored data.
     * Creates a visible BotEntity that players can see.
     *
     * @throws IllegalStateException if the bot is already spawned
     */
    public void spawn() {
        if (isSpawned()) {
            throw new IllegalStateException("Bot is already spawned");
        }

        // Create the bot entity
        botEntity = new BotEntity(ModEntity.BOT.get(), level);

        // Set position and rotation
        botEntity.setPos(data.getX(), data.getY(), data.getZ());
        botEntity.setYRot(data.getYaw());
        botEntity.setXRot(data.getPitch());

        // Set the bot's name and profile
        botEntity.setBotName(data.getName());
        botEntity.setGameProfile(new GameProfile(data.getId(), data.getName()));

        // Add to world - this makes it visible to all players
        level.addFreshEntity(botEntity);
    }

    /**
     * Removes the bot from the world.
     */
    public void despawn() {
        if (!isSpawned()) {
            return;
        }

        botEntity.remove(net.minecraft.world.entity.Entity.RemovalReason.DISCARDED);
        botEntity = null;
    }

    /**
     * @return true if the bot is currently spawned
     */
    public boolean isSpawned() {
        return botEntity != null && botEntity.isAlive();
    }

    /**
     * @return the underlying BotEntity
     * @throws IllegalStateException if the bot is not spawned
     */
    public BotEntity getEntity() {
        if (!isSpawned()) {
            throw new IllegalStateException("Bot is not spawned");
        }
        return botEntity;
    }

    /**
     * @return immutable bot data
     */
    public BotData getData() {
        return data;
    }

    /**
     * Updates the bot's position in real-time
     */
    public void teleport(double x, double y, double z) {
        if (isSpawned()) {
            botEntity.teleportTo(x, y, z);
        }
    }

    /**
     * Updates the bot's rotation
     */
    public void setRotation(float yaw, float pitch) {
        if (isSpawned()) {
            botEntity.setYRot(yaw);
            botEntity.setXRot(pitch);
        }
    }
}