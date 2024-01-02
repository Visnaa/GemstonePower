package com.visnaa.gemstonepower.client.screen.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ClientConfigScreen;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.client.screen.widget.MachineConfigButton;
import com.visnaa.gemstonepower.menu.machine.MachineMenu;
import com.visnaa.gemstonepower.network.packet.MachineConfigSyncC2S;
import com.visnaa.gemstonepower.util.MachineUtil;
import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class MachineScreen<T extends MachineMenu> extends AbstractContainerScreen<T>
{
    protected static WidgetSprites CLIENT_CONFIG_SPRITES = new WidgetSprites(GemstonePower.getId("client_config_button/default"), GemstonePower.getId("client_config_button/default_highlighted"));
    protected static ResourceLocation CONFIG_TAB = GemstonePower.getId("config_tab");
    protected final ScreenData data;
    private String sideTexture = "machine";
    private Button clientConfigButton;
    private MachineConfigButton machineConfigLeft;
    private MachineConfigButton machineConfigRight;
    private MachineConfigButton machineConfigBack;
    private MachineConfigButton machineConfigUp;
    private MachineConfigButton machineConfigDown;

    public MachineScreen(T menu, ScreenData data)
    {
        super(menu, data.inventory(), data.name());
        this.data = data;
    }

    @Override
    public void init()
    {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        clientConfigButton = new ImageButton(this.leftPos + 147, this.topPos + 58, 14, 14, getClientConfigSprites(), button -> Minecraft.getInstance().setScreen(new ClientConfigScreen(Minecraft.getInstance(), this)));
        addRenderableWidget(clientConfigButton);

        machineConfigLeft = new MachineConfigButton(leftPos + imageWidth + 4, topPos + 20, 16, 16, menu.getConfig("left"), this::sendMachineConfig);
        machineConfigRight = new MachineConfigButton(leftPos + imageWidth + 36, topPos + 20, 16, 16, menu.getConfig("right"), this::sendMachineConfig);
        machineConfigBack = new MachineConfigButton(leftPos + imageWidth + 36, topPos + 4, 16, 16, menu.getConfig("back"), this::sendMachineConfig);
        machineConfigUp = new MachineConfigButton(leftPos + imageWidth + 20, topPos + 4, 16, 16, menu.getConfig("up"), this::sendMachineConfig);
        machineConfigDown = new MachineConfigButton(leftPos + imageWidth + 20, topPos + 36, 16, 16, menu.getConfig("down"), this::sendMachineConfig);
        addRenderableWidget(machineConfigLeft);
        addRenderableWidget(machineConfigRight);
        addRenderableWidget(machineConfigBack);
        addRenderableWidget(machineConfigUp);
        addRenderableWidget(machineConfigDown);
    }

    protected WidgetSprites getClientConfigSprites()
    {
        return CLIENT_CONFIG_SPRITES;
    }

    @Override
    public void containerTick()
    {
        super.containerTick();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(graphics, mouseX, mouseY, partialTicks);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);

        if (isMouseInArea(mouseX, mouseY, this.leftPos + 149, this.topPos + 38, 11, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, MachineUtil.getEnergyScaled(menu.getEnergy(), menu.getCapacity()), ItemStack.EMPTY.getTooltipImage(), mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.setShaderTexture(0, this.data.texture());
        int x = this.leftPos;
        int y = this.topPos;
        graphics.blit(this.data.texture(), x, y, 0, 0, this.imageWidth, this.imageHeight);

        graphics.blitSprite(CONFIG_TAB, leftPos + imageWidth, topPos, 57, 56);
        renderMachine(graphics, sideTexture);

        // Progress Bar
        graphics.blit(this.data.texture(),
                x + this.data.progressBar().pos().x(), y + this.data.progressBar().pos().y(),
                this.data.progressBar().overlayPos().x(), this.data.progressBar().overlayPos().y(),
                this.menu.getProcessingProcess(this.data.progressBar().dimensions().x()), this.data.progressBar().dimensions().y());

        // Battery
        int energy = this.menu.getEnergyLevel();
        graphics.blit(this.data.texture(), x + 149, y + 38 + energy, 176, energy, 10, 16 - energy);
    }

    private void renderMachine(GuiGraphics graphics, String sideTexture)
    {
        if (!(Minecraft.getInstance().getTextureManager().getTexture(InventoryMenu.BLOCK_ATLAS) instanceof TextureAtlas atlas) || menu.getOwner() == null)
            return;

        TextureAtlasSprite front = atlas.getSprite(GemstonePower.getId("block/" + BuiltInRegistries.BLOCK.getKey(menu.getOwner()).getPath()));
        TextureAtlasSprite side = atlas.getSprite(GemstonePower.getId("block/" + sideTexture));
        int overlayColor = 0xCC000000 + Tier.getTierTint(menu.getTier()).getColor();
        renderMachineFace(graphics, side, imageWidth + 20, 20, 0, overlayColor);
        renderMachineFace(graphics, side, imageWidth + 4, 20, 0, overlayColor);
        renderMachineFace(graphics, side, imageWidth + 20, 4, 0, overlayColor);
        renderMachineFace(graphics, side, imageWidth + 20, 36, 0, overlayColor);
        renderMachineFace(graphics, side, imageWidth + 36, 4, 0, overlayColor);
        renderMachineFace(graphics, side, imageWidth + 36, 20, 0, overlayColor);
        renderMachineFace(graphics, front, imageWidth + 20, 20, -1, 0);
    }

    private void renderMachineFace(GuiGraphics graphics, TextureAtlasSprite sprite, int xOffset, int yOffset, int z, int overlayColor)
    {
        graphics.blit(leftPos + xOffset, topPos + yOffset, 0, 16, 16, sprite);
        Matrix4f pose = graphics.pose().last().pose();
        Matrix3f normal = graphics.pose().last().normal();
        VertexConsumer textureConsumer = graphics.bufferSource().getBuffer(Sheets.translucentCullBlockSheet());
        VertexConsumer overlayConsumer = graphics.bufferSource().getBuffer(RenderType.debugQuads());
        textureConsumer.vertex(pose, leftPos + xOffset, topPos + yOffset, z).color(0xFFFFFFFF).uv(sprite.getU0(), sprite.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0xFFFFFF).normal(normal, 0, 0, 1).endVertex();
        textureConsumer.vertex(pose, leftPos + xOffset + 16, topPos + yOffset, z).color(0xFFFFFFFF).uv(sprite.getU1(), sprite.getV0()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0xFFFFFF).normal(normal, 0, 0, 1).endVertex();
        textureConsumer.vertex(pose, leftPos + xOffset + 16, topPos + yOffset + 16, z).color(0xFFFFFFFF).uv(sprite.getU1(), sprite.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0xFFFFFF).normal(normal, 0, 0, 1).endVertex();
        textureConsumer.vertex(pose, leftPos + xOffset, topPos + yOffset + 16, z).color(0xFFFFFFFF).uv(sprite.getU0(), sprite.getV1()).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0xFFFFFF).normal(normal, 0, 0, 1).endVertex();

        if (overlayColor >= 0xCCFFFFFF)
            return;
        overlayConsumer.vertex(pose, leftPos + xOffset, topPos + yOffset, z).color(overlayColor).endVertex();
        overlayConsumer.vertex(pose, leftPos + xOffset + 16, topPos + yOffset, z).color(overlayColor).endVertex();
        overlayConsumer.vertex(pose, leftPos + xOffset + 16, topPos + yOffset + 16, z).color(overlayColor).endVertex();
        overlayConsumer.vertex(pose, leftPos + xOffset, topPos + yOffset + 16, z).color(overlayColor).endVertex();
    }

    protected void renderFluid(GuiGraphics graphics, int mouseX, int mouseY)
    {
        renderFluid(graphics, this.leftPos + 15, this.topPos + 20, 46, menu.getFluidTank(0));
        renderFluidTooltip(graphics, mouseX, mouseY);
    }

    protected void renderFluidTooltip(GuiGraphics graphics, int mouseX, int mouseY)
    {
        if (isMouseInArea(mouseX, mouseY, this.leftPos + 15, this.topPos + 20, 16, 46))
        {
            FluidStack fluid = menu.getFluidTank(0).getFluid();
            Component fluidName = Component.literal("§fFluid: §6" + fluid.getDisplayName().getString());
            Component fluidAmount = Component.literal("§fAmount: §b" + fluid.getAmount() + " §fmB");
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(fluidName, fluidAmount), ItemStack.EMPTY.getTooltipImage(), mouseX, mouseY);
        }
    }

    public static void renderFluid(GuiGraphics graphics, int x, int y, int height, FluidTank tank)
    {
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        if (!tank.isEmpty())
        {
            FluidStack fluidStack = tank.getFluid();
            IClientFluidTypeExtensions fluid = IClientFluidTypeExtensions.of(fluidStack.getFluid());
            ResourceLocation texture = fluid.getStillTexture(fluidStack);
            if (texture != null)
            {
                if (Minecraft.getInstance().getTextureManager().getTexture(InventoryMenu.BLOCK_ATLAS) instanceof TextureAtlas atlas)
                {
                    TextureAtlasSprite sprite = atlas.getSprite(texture);

                    int color = fluid.getTintColor();
                    RenderSystem.setShaderColor(
                            (color >> 16 & 255) / 255.0F,
                            (color >> 8 & 255) / 255.0F,
                            (color & 255) / 255.0F,
                            (color >>> 24) / 255.0F);
                    RenderSystem.enableBlend();

                    int stored = tank.getFluidAmount();
                    float capacity = tank.getCapacity();
                    float filledVolume = stored / capacity;
                    int rendererHeight = (int) (filledVolume * height);


                    int atlasWidth = (int) (sprite.contents().width() / (sprite.getU1() - sprite.getU0()));
                    int atlasHeight = (int) (sprite.contents().height() / (sprite.getV1() - sprite.getV0()));

                    graphics.pose().pushPose();
                    graphics.pose().translate(0, height - 16, 0);
                    for (int i = 0; i < Math.ceil(rendererHeight / 16f); i++)
                    {
                        int drawingHeight = Math.min(16, rendererHeight - 16 * i);
                        int notDrawingHeight = 16 - drawingHeight;
                        graphics.blit(InventoryMenu.BLOCK_ATLAS, x, y + notDrawingHeight, 0,
                                sprite.getU0() * atlasWidth, sprite.getV0() * atlasHeight + notDrawingHeight,
                                16, drawingHeight, atlasWidth, atlasHeight);
                        graphics.pose().translate(0, -16, 0);
                    }
                    RenderSystem.setShaderColor(1, 1, 1, 1);
                    graphics.pose().popPose();
                }
            }
        }
        RenderSystem.disableDepthTest();
    }

    public void setSideTexture(String sideTexture)
    {
        this.sideTexture = sideTexture;
    }

    public static boolean isMouseInArea(double mouseX, double mouseY, int x, int y, int width, int height)
    {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private void sendMachineConfig(Button button)
    {
        PacketDistributor.SERVER.noArg().send(new MachineConfigSyncC2S(machineConfigLeft.getConfig(), machineConfigRight.getConfig(), machineConfigBack.getConfig(), machineConfigUp.getConfig(), machineConfigDown.getConfig(), menu.getBlockPos()));
    }
}
