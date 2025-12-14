package dev.greendev.neobots.bot;

import java.util.UUID;

/**
 * Represents the immutable data of a bot in the system.
 * This class holds identification, spatial, and dimensional information
 * for a bot entity.
 */
public record BotData(UUID id, String name, String dimension, double x, double y, double z, float yaw, float pitch) {

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
    public BotData {
    }

    /**
     * Returns the unique identifier of the bot.
     *
     * @return the bot's UUID
     */
    @Override
    public UUID id() {
        return id;
    }

    /**
     * Returns the name of the bot.
     *
     * @return the bot's name
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Returns the dimension where the bot is located.
     *
     * @return the dimension name
     */
    @Override
    public String dimension() {
        return dimension;
    }

    /**
     * Returns the x-coordinate of the bot's position.
     *
     * @return the x-coordinate
     */
    @Override
    public double x() {
        return x;
    }

    /**
     * Returns the y-coordinate of the bot's position.
     *
     * @return the y-coordinate
     */
    @Override
    public double y() {
        return y;
    }

    /**
     * Returns the z-coordinate of the bot's position.
     *
     * @return the z-coordinate
     */
    @Override
    public double z() {
        return z;
    }

    /**
     * Returns the horizontal rotation (yaw) of the bot.
     *
     * @return the yaw in degrees
     */
    @Override
    public float yaw() {
        return yaw;
    }

    /**
     * Returns the vertical rotation (pitch) of the bot.
     *
     * @return the pitch in degrees
     */
    @Override
    public float pitch() {
        return pitch;
    }
}