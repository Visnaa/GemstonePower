package com.visnaa.gemstonepower.client.screen.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TextWidget extends AbstractWidget
{
    private Component text;

    public TextWidget(int y, int width, int height, Component text)
    {
        super(width / 2, y, width, height, text);
        this.text = text;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        graphics.drawCenteredString(Minecraft.getInstance().font, text, width / 2, getY(), 0xFFFFFF);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output)
    {
        output.add(NarratedElementType.TITLE, text);
    }
}
