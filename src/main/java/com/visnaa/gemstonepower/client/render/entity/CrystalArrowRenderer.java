package com.visnaa.gemstonepower.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.visnaa.gemstonepower.entity.projectile.CrystalArrow;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class CrystalArrowRenderer extends EntityRenderer<CrystalArrow>
{
    public CrystalArrowRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(CrystalArrow arrow)
    {
        return new ResourceLocation("textures/entity/projectiles/arrow.png");
    }

    public void render(CrystalArrow arrow, float f11, float partialTick, PoseStack stack, MultiBufferSource buffer, int light) {
        stack.pushPose();
        stack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, arrow.yRotO, arrow.getYRot()) - 90.0F));
        stack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, arrow.xRotO, arrow.getXRot())));
        int i = 0;
        float f = 0.0F;
        float f1 = 0.5F;
        float f2 = 0.0F;
        float f3 = 0.15625F;
        float f4 = 0.0F;
        float f5 = 0.15625F;
        float f6 = 0.15625F;
        float f7 = 0.3125F;
        float f8 = 0.05625F;
        float f9 = (float)arrow.shakeTime - partialTick;
        if (f9 > 0.0F) {
            float f10 = -Mth.sin(f9 * 3.0F) * f9;
            stack.mulPose(Axis.ZP.rotationDegrees(f10));
        }

        stack.mulPose(Axis.XP.rotationDegrees(45.0F));
        stack.scale(0.05625F, 0.05625F, 0.05625F);
        stack.translate(-4.0F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(arrow)));
        PoseStack.Pose posestack$pose = stack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, -2, -2, 255, 255, 255, 0.0F, 0.15625F, -1, 0, 0, light);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, -2, 2, 255, 255, 255, 0.15625F, 0.15625F, -1, 0, 0, light);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, 2, 2, 255, 255, 255, 0.15625F, 0.3125F, -1, 0, 0, light);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, 2, -2, 255, 255, 255, 0.0F, 0.3125F, -1, 0, 0, light);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, 2, -2, 255, 255, 255, 0.0F, 0.15625F, 1, 0, 0, light);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, 2, 2, 255, 255, 255, 0.15625F, 0.15625F, 1, 0, 0, light);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, -2, 2, 255, 255, 255, 0.15625F, 0.3125F, 1, 0, 0, light);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, -2, -2, 255, 255, 255, 0.0F, 0.3125F, 1, 0, 0, light);

        for(int j = 0; j < 4; ++j) {
            stack.mulPose(Axis.XP.rotationDegrees(90.0F));
            this.vertex(matrix4f, matrix3f, vertexconsumer, -8, -2, 0, 255, 255, 255, 0.0F, 0.0F, 0, 1, 0, light);
            this.vertex(matrix4f, matrix3f, vertexconsumer, 8, -2, 0, 255, 255, 255, 0.5F, 0.0F, 0, 1, 0, light);
            this.vertex(matrix4f, matrix3f, vertexconsumer, 8, 2, 0, 255, 255, 255, 0.5F, 0.15625F, 0, 1, 0, light);
            this.vertex(matrix4f, matrix3f, vertexconsumer, -8, 2, 0, 255, 255, 255, 0.0F, 0.15625F, 0, 1, 0, light);
        }

        stack.popPose();
        super.render(arrow, f11, partialTick, stack, buffer, light);
    }

    public void vertex(Matrix4f vertex, Matrix3f normal, VertexConsumer consumer, int x, int y, int z, int r, int g, int b, float u, float v, int normalX, int normalZ, int normalY, int uv2)
    {
        consumer.vertex(vertex, (float)x, (float)y, (float)z).color(r, g, b, 255).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(uv2).normal(normal, (float)normalX, (float)normalY, (float)normalZ).endVertex();
    }
}
