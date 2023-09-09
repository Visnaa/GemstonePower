package com.visnaa.gemstonepower.client.screen.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.MachineMenu;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.List;

public abstract class MachineScreen<T extends MachineMenu> extends AbstractContainerScreen<T>
{
    protected final ScreenData data;

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
    }

    @Override
    public void containerTick()
    {
        super.containerTick();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);

        if (isMouseInArea(mouseX, mouseY, this.leftPos + 148, this.topPos + 38, 11, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, MachineUtil.getEnergyScaled(menu.getEnergy(), menu.getCapacity()), ItemStack.EMPTY.getTooltipImage(), mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.setShaderTexture(0, this.data.texture());
        int x = this.leftPos;
        int y = this.topPos;
        graphics.blit(this.data.texture(), x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Progress Bar
        graphics.blit(this.data.texture(),
                x + this.data.progressBar().pos().x(), y + this.data.progressBar().pos().y(),
                this.data.progressBar().overlayPos().x(), this.data.progressBar().overlayPos().y(),
                this.menu.getProcessingProcess(this.data.progressBar().dimensions().x()), this.data.progressBar().dimensions().y());

        // Battery
        int energy = this.menu.getEnergyLevel();
        graphics.blit(this.data.texture(), x + 149, y + 38 + energy, 176, energy, 10, 16 - energy);
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

    public static boolean isMouseInArea(int mouseX, int mouseY, int x, int y, int width, int height)
    {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
