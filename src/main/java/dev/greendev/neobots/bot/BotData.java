package dev.greendev.neobots.bot;

import java.util.UUID;

public class BotData {

    private final UUID id;
    private final String name;
    private final String dimension;
    private final double x, y ,z;
    private final float yaw, pitch;

    public BotData(UUID id, String name, String dimension, double x, double y, double z, float yaw, float pitch){
        this.id = id;
        this.name   = name;
        this.dimension = dimension;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDimension(){
        return dimension;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
