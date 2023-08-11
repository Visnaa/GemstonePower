package com.visnaa.gemstonepower.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sun.jna.platform.win32.COM.IConnectionPoint;
import com.visnaa.gemstonepower.block.entity.TankBE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.client.model.EmptyModel;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import org.apache.http.impl.client.RedirectLocations;
import org.joml.*;

public class TankBER implements BlockEntityRenderer<TankBE>
{
    private BlockEntityRendererProvider.Context context;

    public TankBER(BlockEntityRendererProvider.Context context)
    {
        this.context = context;
    }

    @Override
    public void render(TankBE blockEntity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light, int overlay)
    {
        if (blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent() && blockEntity.getLevel() != null)
        {
            Fluid fluid = blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).map(h -> h.getFluidInTank(0).getFluid()).orElse(Fluids.EMPTY);
            if (!fluid.isSame(Fluids.EMPTY))
            {
                VertexConsumer vertexConsumer = buffer.getBuffer(Sheets.translucentCullBlockSheet());
                TextureAtlasSprite texture = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(IClientFluidTypeExtensions.of(fluid).getStillTexture());
                float height = 15 * blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).map(h -> (float) h.getFluidInTank(0).getAmount() / h.getTankCapacity(0)).orElse(0F) / 16F;
                int color = IClientFluidTypeExtensions.of(fluid).getTintColor(fluid.defaultFluidState(), blockEntity.getLevel(), blockEntity.getBlockPos());

                renderFace(Direction.UP, stack.last().pose(), stack.last().normal(), vertexConsumer, texture,
                        0.03125F, 0.03125F, 0.03125F + height, 0.9375F, 0.9375F, color, light);

                renderFace(Direction.SOUTH, stack.last().pose(), stack.last().normal(), vertexConsumer, texture,
                        0.03125F, 0.03125F, 0.03125F, 0.9375F, height, color, light);
                renderFace(Direction.NORTH, stack.last().pose(), stack.last().normal(), vertexConsumer, texture,
                        0.03125F, 0.03125F, 0.03125F, 0.9375F, height, color, light);
                renderFace(Direction.EAST, stack.last().pose(), stack.last().normal(), vertexConsumer, texture,
                        0.03125F, 0.03125F, 0.03125F, 0.9375F, height, color, light);
                renderFace(Direction.WEST, stack.last().pose(), stack.last().normal(), vertexConsumer, texture,
                        0.03125F, 0.03125F, 0.03125F, 0.9375F, height, color, light);
            }
        }
    }

    public static void renderFace(Direction dir, Matrix4f pose, Matrix3f normal, VertexConsumer consumer, TextureAtlasSprite texture, float x, float y, float z, float w, float h, int color, int light)
    {
        switch (dir)
        {
            case DOWN -> renderFace(pose, normal, consumer, texture, color, light, x, x + w, 1.0f - z, 1.0f - z, y, y, y + h, y + h, x, x + w, y, y + h, 0, -1, 0);
            case UP -> renderFace(pose, normal, consumer, texture, color, light, x, x + w, z, z, y + h, y + h, y, y, x, x + w, y, y + h, 0, 1, 0);
            case NORTH -> renderFace(pose, normal, consumer, texture, color, light, x, x + w, y + h, y, z, z, z, z, x, x + w, y, y + h, 0, 0, -1);
            case SOUTH -> renderFace(pose, normal, consumer, texture, color, light, x, x + w, y, y + h, 1.0f - z, 1.0f - z, 1.0f - z, 1.0f - z, x + w, x, y + h, y, 0, 0, 1);
            case EAST -> renderFace(pose, normal, consumer, texture, color, light, 1.0f - z, 1.0f - z, y + h, y, x, x + w, x + w, x, x, x + w, y, y + h, 1, 0, 0);
            case WEST -> renderFace(pose, normal, consumer, texture, color, light, z, z, y, y + h, x, x + w, x + w, x, x + w, x, y + h, y, -1, 0, 0);
        }
    }

    private static void renderFace(Matrix4f pose, Matrix3f normal, VertexConsumer consumer, TextureAtlasSprite texture, int color, int light, float x0, float x1, float y0, float y1, float z0, float z1, float z2, float z3, float u0, float u1, float v0, float v1, float normalX, float normalY, float normalZ)
    {
        float minU = u0 * texture.contents().width();
        float maxU = u1 * texture.contents().width();
        float minV = v0 * texture.contents().height();
        float maxV = v1 * texture.contents().height();

        consumer.vertex(pose, x0, y0, z0).color(color).uv(texture.getU(minU), texture.getV(minV)).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, normalX, normalY, normalZ).endVertex();
        consumer.vertex(pose, x1, y0, z1).color(color).uv(texture.getU(maxU), texture.getV(minV)).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, normalX, normalY, normalZ).endVertex();
        consumer.vertex(pose, x1, y1, z2).color(color).uv(texture.getU(maxU), texture.getV(maxV)).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, normalX, normalY, normalZ).endVertex();
        consumer.vertex(pose, x0, y1, z3).color(color).uv(texture.getU(minU), texture.getV(maxV)).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, normalX, normalY, normalZ).endVertex();
    }
}
