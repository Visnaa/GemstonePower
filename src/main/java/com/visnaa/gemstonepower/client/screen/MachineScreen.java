package com.visnaa.gemstonepower.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.visnaa.gemstonepower.menu.machine.MachineMenu;
import com.visnaa.gemstonepower.util.EnergyUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.item.ItemStack;

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

        if (mouseX >= this.leftPos + 148 && mouseX <= this.leftPos + 159 && mouseY >= this.topPos + 38 && mouseY <= this.topPos + 54)
        {
            graphics.renderTooltip(Minecraft.getInstance().font, EnergyUtilities.getDefaultTooltips(menu.getEnergy(), menu.getCapacity()), ItemStack.EMPTY.getTooltipImage(), mouseX, mouseY);
        }
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
}
