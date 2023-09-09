package com.visnaa.gemstonepower.client.screen.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.FissionReactorMenu;
import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.FissionReactorActivationC2S;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class FissionReactorScreen extends MachineScreen<FissionReactorMenu>
{
    private Button activateButton;

    public FissionReactorScreen(FissionReactorMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/fission_reactor_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(79, 36),
                        new Vector2i(176, 16),
                        new Vector2i(21, 20))));
    }

    @Override
    public void init()
    {
        super.init();
        this.activateButton = new Button.Builder(Component.literal(!menu.isActivated() ? "Activate" : "Deactivate"), button -> this.handleActivation())
                .pos((this.width - 200) / 2 + 50, this.topPos - 32)
                .size(100, 20).build();
        this.addRenderableWidget(activateButton);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        super.render(graphics, mouseX, mouseY, partialTicks);

        if (isMouseInArea(mouseX, mouseY, leftPos + 21, topPos + 38, 3, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, MachineUtil.getHeatScaled(menu.getHeat(), menu.getMaxHeat()), ItemStack.EMPTY.getTooltipImage(), mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY)
    {
        super.renderBg(graphics, partialTicks, mouseX, mouseY);

        int heat = Math.max(this.menu.getHeatLevel(), 0);
        graphics.blit(this.data.texture(), leftPos + 21, topPos + 38 + heat, 176, 36 + heat, 3, 16 - heat);
    }

    private void handleActivation()
    {
        boolean activated = menu.isActivated();
        if (menu.getBlockPos() != null)
        {
            ModPackets.sendToServer(new FissionReactorActivationC2S(!activated, menu.getBlockPos()));
            activateButton.setMessage(Component.literal(activated ? "Activate" : "Deactivate"));
        }
    }

    private ChatFormatting getColor()
    {
        int heat = Math.max(this.menu.getHeatLevel(), 0);
        if (heat < 2)
            return ChatFormatting.DARK_RED;
        else if (heat < 4)
            return ChatFormatting.RED;
        else if (heat < 7)
            return ChatFormatting.GOLD;
        else if (heat < 10)
            return ChatFormatting.YELLOW;
        else if (heat < 13)
            return ChatFormatting.GREEN;
        return ChatFormatting.AQUA;
    }
}
