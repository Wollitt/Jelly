package com.wollit.jellymod.entity.gorilla;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wollit.jellymod.JellyMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GorillaRenderer extends GeoEntityRenderer<GorillaEntity> {

    public GorillaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GorillaModel());
    }

    @Override
    public ResourceLocation getTextureLocation(GorillaEntity animatable) {
        return new ResourceLocation(JellyMod.MOD_ID, "textures/entity/gorilla.png");
    }

    @Override
    public void render(GorillaEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
//        poseStack.scale(1.5F, 1.5F, 1.5F);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
