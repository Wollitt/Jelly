package com.wollit.jellymod.entity.gorilla;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.entity.gorilla.GorillaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GorillaModel extends GeoModel<GorillaEntity> {

    @Override
    public ResourceLocation getModelResource(GorillaEntity animatable) {
        return new ResourceLocation(JellyMod.MOD_ID, "geo/gorilla.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GorillaEntity animatable) {
        return new ResourceLocation(JellyMod.MOD_ID, "textures/entity/gorilla.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GorillaEntity animatable) {
        return new ResourceLocation(JellyMod.MOD_ID, "animations/gorilla.animation.json");
    }

    @Override
    public void setCustomAnimations(GorillaEntity animatable, long instanceId, AnimationState<GorillaEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

            if (head != null) {
                EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

                float additionalX = 10;

                head.setRotX(entityModelData.headPitch() * ((float) Math.PI / 160));
                head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);
            }
    }
}
