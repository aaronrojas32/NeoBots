package dev.greendev.neobots.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.greendev.neobots.entity.BotEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;

/**
 * Renders the bot entity using the player model.
 */
public class BotRenderer extends MobRenderer<BotEntity, PlayerModel<BotEntity>> {

    public BotRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(BotEntity entity) {
        // Use default Steve skin
        // You can customize this to use custom skins based on the bot's profile
        return DefaultPlayerSkin.getDefaultTexture();
    }

    @Override
    protected void scale(BotEntity entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F); // Player scale
    }
}