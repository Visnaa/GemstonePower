package com.visnaa.gemstonepower.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.InventoryMenu;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class ConfigMachineBER implements BlockEntityRenderer<MachineBE<?>>
{
    private BlockEntityRendererProvider.Context context;

    public ConfigMachineBER(BlockEntityRendererProvider.Context context)
    {
        this.context = context;
    }

    @Override
    public void render(MachineBE<?> machine, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light, int overlay)
    {
        if (!(Minecraft.getInstance().getTextureManager().getTexture(InventoryMenu.BLOCK_ATLAS) instanceof TextureAtlas atlas))
            return;
        VertexConsumer consumer = buffer.getBuffer(Sheets.translucentCullBlockSheet());
        machine.getConfigs().keySet().forEach(dir -> renderFace(stack, consumer, dir, getTexture(machine.getConfigs().get(dir), atlas), LevelRenderer.getLightColor(machine.getLevel(), machine.getBlockState(), machine.getBlockPos().above())));
    }

    private TextureAtlasSprite getTexture(MachineUtil.MachineConfigs config, TextureAtlas atlas)
    {
        return switch (config)
        {
            case INPUT -> atlas.getSprite(GemstonePower.getId("block/config_input"));
            case OUTPUT -> atlas.getSprite(GemstonePower.getId("block/config_output"));
            case ALL -> atlas.getSprite(GemstonePower.getId("block/config_all"));
            default -> null;
        };
    }

    private void renderFace(PoseStack stack, VertexConsumer consumer, Direction direction, TextureAtlasSprite texture, int light)
    {
        if (texture == null)
            return;

        Matrix4f matrix = stack.last().pose();
        Matrix3f normal = stack.last().normal();
        switch (direction)
        {
            case NORTH -> {
                consumer.vertex(matrix, 1, 1, -0.0001f).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 0, -1).endVertex();
                consumer.vertex(matrix, 1, 0, -0.0001f).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 0, -1).endVertex();
                consumer.vertex(matrix, 0, 0, -0.0001f).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 0, -1).endVertex();
                consumer.vertex(matrix, 0, 1, -0.0001f).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 0, -1).endVertex();
            }
            case SOUTH -> {
                consumer.vertex(matrix, 0, 0, 1.0001f).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 0, 1).endVertex();
                consumer.vertex(matrix, 1, 0, 1.0001f).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 0, 1).endVertex();
                consumer.vertex(matrix, 1, 1, 1.0001f).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 0, 1).endVertex();
                consumer.vertex(matrix, 0, 1, 1.0001f).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 0, 1).endVertex();
            }
            case EAST -> {
                consumer.vertex(matrix, 1.0001f, 0, 0).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 1, 0, 0).endVertex();
                consumer.vertex(matrix, 1.0001f, 1, 0).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 1, 0, 0).endVertex();
                consumer.vertex(matrix, 1.0001f, 1, 1).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 1, 0, 0).endVertex();
                consumer.vertex(matrix, 1.0001f, 0, 1).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 1, 0, 0).endVertex();
            }
            case WEST -> {
                consumer.vertex(matrix, -0.0001f, 1, 1).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, -1, 0, 0).endVertex();
                consumer.vertex(matrix, -0.0001f, 1, 0).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, -1, 0, 0).endVertex();
                consumer.vertex(matrix, -0.0001f, 0, 0).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, -1, 0, 0).endVertex();
                consumer.vertex(matrix, -0.0001f, 0, 1).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, -1, 0, 0).endVertex();
            }
            case UP -> {
                consumer.vertex(matrix, 1, 1.0001f, 1).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 1, 0).endVertex();
                consumer.vertex(matrix, 1, 1.0001f, 0).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 1, 0).endVertex();
                consumer.vertex(matrix, 0, 1.0001f, 0).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 1, 0).endVertex();
                consumer.vertex(matrix, 0, 1.0001f, 1).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, 1, 0).endVertex();
            }
            case DOWN -> {
                consumer.vertex(matrix, 0, -0.0001f, 0).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, -1, 0).endVertex();
                consumer.vertex(matrix, 1, -0.0001f, 0).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, -1, 0).endVertex();
                consumer.vertex(matrix, 1, -0.0001f, 1).color(0xFFFFFFFF).uv(texture.getU1(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, -1, 0).endVertex();
                consumer.vertex(matrix, 0, -0.0001f, 1).color(0xFFFFFFFF).uv(texture.getU0(), texture.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normal, 0, -1, 0).endVertex();
            }
        }
    }
}
