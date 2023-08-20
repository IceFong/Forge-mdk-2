package net.frozencheesecat.testmod.blockentity.renderer;

import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.PoseStack;

import net.frozencheesecat.testmod.blockentity.firstBlock.FirstBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class FirstBlockEntityRenderer implements BlockEntityRenderer<FirstBlockEntity> {

    public FirstBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(FirstBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource,
            int combinedLight, int combinedOverlay) {
        
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack itemStack = blockEntity.getRendererStack();
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.65f, 0.5f);
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.mulPose(new Quaternionf(new AxisAngle4f(90, 0, 0, 0)));

        switch (pBlockEntity.getBlockState().getValue(FirstBlockEntity.)) {
            case NORTH -> pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(0));
            case EAST -> pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(90));
            case SOUTH -> pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
            case WEST -> pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(270));
        }

    }
    


}
