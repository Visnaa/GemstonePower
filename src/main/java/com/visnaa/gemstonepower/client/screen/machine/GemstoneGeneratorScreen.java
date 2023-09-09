package com.visnaa.gemstonepower.client.screen.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.GemstoneGeneratorMenu;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class GemstoneGeneratorScreen extends MachineScreen<GemstoneGeneratorMenu>
{
    public GemstoneGeneratorScreen(GemstoneGeneratorMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/gemstone_generator_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(77, 38),
                        new Vector2i(176, 16),
                        new Vector2i(23, 16))));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);

        if (mouseX >= this.leftPos + 110 && mouseX <= this.leftPos + 120 && mouseY >= this.topPos + 38 && mouseY <= this.topPos + 54)
        {
            graphics.renderTooltip(Minecraft.getInstance().font, MachineUtil.getEnergyScaled(menu.getEnergy(), menu.getCapacity()), ItemStack.EMPTY.getTooltipImage(), mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.setShaderTexture(0, this.data.texture());
        int x = this.leftPos;
        int y = this.topPos;
        graphics.blit(this.data.texture(), x, y, 0, 0, this.imageWidth, this.imageHeight);

        graphics.blit(this.data.texture(),
                x + this.data.progressBar().pos().x(), y + this.data.progressBar().pos().y(),
                this.data.progressBar().overlayPos().x(), this.data.progressBar().overlayPos().y(),
                this.menu.getProcessingProcess(this.data.progressBar().dimensions().x()), this.data.progressBar().dimensions().y());

        int energy = this.menu.getEnergyLevel();
        graphics.blit(this.data.texture(), x + 110, y + 38 + energy, 176, energy, 10, 16 - energy);
    }
}
