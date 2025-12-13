package dev.greendev.neobots.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.greendev.neobots.entity.BotEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;

public class BotRender extends MobRenderer<BotEntity, PlayerRenderState, PlayerModel> {

    public BotRender(EntityRendererProvider.Context context) {
        // En 1.21.8, PlayerModel NO tiene parámetros de tipo
        super(context, new PlayerModel(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
    }

    @Override
    public PlayerRenderState createRenderState() {
        return new PlayerRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(PlayerRenderState state) {
        // PlayerRenderState tiene un campo 'skin' de tipo PlayerSkin
        // Necesitamos extraer el ResourceLocation de PlayerSkin
        PlayerSkin skin = state.skin;
        if (skin != null) {
            return skin.texture();
        }
        // Fallback a skin por defecto (Steve)
        return DefaultPlayerSkin.getDefaultTexture();
    }
}