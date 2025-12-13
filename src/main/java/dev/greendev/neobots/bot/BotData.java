package dev.greendev.neobots.bot;

import java.util.UUID;

/**
 * Represents the immutable data of a bot in the system.
 * This class holds identification, spatial, and dimensional information
 * for a bot entity.
 */
public class BotData {

    private final UUID id;
    private final String name;
    private final String dimension;
    private final double x, y, z;
    private final float yaw, pitch;

    /**
     * Constructs a new BotData instance with the specified parameters.
     *
     * @param id        the unique identifier for the bot
     * @param name      the name of the bot
     * @param dimension the dimension/world where the bot is located
     * @param x         the x-coordinate of the bot's position
     * @param y         the y-coordinate of the bot's position
     * @param z         the z-coordinate of the bot's position
     * @param yaw       the horizontal rotation (yaw) of the bot in degrees
     * @param pitch     the vertical rotation (pitch) of the bot in degrees
     */
    public BotData(UUID id, String name, String dimension, double x, double y, double z, float yaw, float pitch) {
        this.id = id;
        this.name = name;
        this.dimension = dimension;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Returns the unique identifier of the bot.
     *
     * @return the bot's UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the name of the bot.
     *
     * @return the bot's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the dimension where the bot is located.
     *
     * @return the dimension name
     */
    public String getDimension() {
        return dimension;
    }

    /**
     * Returns the x-coordinate of the bot's position.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the bot's position.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the z-coordinate of the bot's position.
     *
     * @return the z-coordinate
     */
    public double getZ() {
        return z;
    }

    /**
     * Returns the horizontal rotation (yaw) of the bot.
     *
     * @return the yaw in degrees
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * Returns the vertical rotation (pitch) of the bot.
     *
     * @return the pitch in degrees
     */
    public float getPitch() {
        return pitch;
    }
}