package net.frozencheesecat.testmod.entity.client;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.entity.custom.TigerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class TigerModel extends GeoModel<TigerEntity> {
    @Override
    public ResourceLocation getModelResource(TigerEntity animatable) {
        return new ResourceLocation(TestMod.MODID, "geo/tiger.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TigerEntity animatable) {
        return new ResourceLocation(TestMod.MODID, "textures/entity/tiger.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TigerEntity animatable) {
        return new ResourceLocation(TestMod.MODID, "animations/tiger.animation.json");
    }

    @Override
    public void setCustomAnimations(TigerEntity animatable, long instanceId, AnimationState<TigerEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}