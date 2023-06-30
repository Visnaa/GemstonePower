package com.visnaa.gemstonepower.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.menu.OreWasherMenu;
import com.visnaa.gemstonepower.util.EnergyUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OreWasherScreen extends AbstractContainerScreen<OreWasherMenu>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(GemstonePower.MOD_ID ,"textures/gui/ore_washer_gui.png");

    public OreWasherScreen(OreWasherMenu menu, Inventory inventory, Component component)
    {
        super(menu, inventory, component);
    }

    public void init()
    {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    public void containerTick()
    {
        super.containerTick();
    }

    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);

        if (mouseX >= this.leftPos + 148 && mouseX <= this.leftPos + 159 && mouseY >= this.topPos + 38 && mouseY <= this.topPos + 54)
        {
            graphics.renderTooltip(Minecraft.getInstance().font, EnergyUtilities.getDefaultTooltips(menu.getData(2), menu.getData(3)), ItemStack.EMPTY.getTooltipImage(), mouseX, mouseY);
        }
    }

    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = this.leftPos;
        int j = this.topPos;
        graphics.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);

        int progress = this.menu.getWashingProgress();
        graphics.blit(TEXTURE, i + 75, j + 38, 176, 16, progress, 15);

        int energy = this.menu.getEnergy();
        graphics.blit(TEXTURE, i + 149, j + 38 + energy, 176, energy, 10, 16 - energy);
    }
}
