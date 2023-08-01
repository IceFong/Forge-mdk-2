package net.frozencheesecat.testmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.entity.custom.TigerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TigerRenderer extends GeoEntityRenderer<TigerEntity> {
    public TigerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TigerModel(new ResourceLocation(TestMod.MODID, "tiger")));
    }

    @Override
    public ResourceLocation getTextureLocation(TigerEntity animatable) {
        return new ResourceLocation(TestMod.MODID, "textures/entity/tiger.png");
    }

    @Override
    public void render(TigerEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}