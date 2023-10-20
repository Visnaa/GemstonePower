package com.visnaa.gemstonepower.client.screen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.widget.TextWidget;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientConfigScreen extends Screen
{
    private Minecraft minecraft;
    private Screen parent;

    private Button energyUnit;
    private Button doneButton;
    private EditBox unitBox;

    public ClientConfigScreen(Minecraft minecraft, Screen parent)
    {
        super(Component.translatable("menu." + GemstonePower.MOD_ID + ".config_screen"));
        this.minecraft = minecraft;
        this.parent = parent;
    }

    @Override
    protected void init()
    {
        doneButton = new Button.Builder(Component.translatable("menu." + GemstonePower.MOD_ID + ".config_screen.done"), button -> this.onClose())
                .pos((this.width - 200) / 2, this.height - 26)
                .size(200, 20).build();

        energyUnit = new Button.Builder(Component.translatable("menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit"), button -> {
            MachineUtil.EnergyUnits unit = MachineUtil.EnergyUnits.next(MachineUtil.EnergyUnits.byString(ClientConfig.ENERGY_UNIT.get()));
            if (unit != MachineUtil.EnergyUnits.CUSTOM)
            {
                energyUnit.setMessage(Component.translatable("menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit"));
                ClientConfig.ENERGY_UNIT.set(unit.getUnit());
                unitBox.setEditable(false);
                unitBox.setValue(unit.getUnit());
            }
            else
            {
                energyUnit.setMessage(Component.translatable("menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit"));
                unitBox.setEditable(true);
                unitBox.setValue(Component.translatable(unit.getKey()).getString());
            }
        }).pos(width / 2 - 155, 25).size(152, 18).build();

        unitBox = new EditBox(Minecraft.getInstance().font, width / 2 + 3, 26, 152, 16, Component.literal(ClientConfig.ENERGY_UNIT.get()));
        unitBox.setEditable(MachineUtil.EnergyUnits.byString(ClientConfig.ENERGY_UNIT.get()) == MachineUtil.EnergyUnits.CUSTOM);
        unitBox.setMaxLength(24);
        unitBox.setEditable(MachineUtil.EnergyUnits.byString(ClientConfig.ENERGY_UNIT.get()) == MachineUtil.EnergyUnits.CUSTOM);
        unitBox.setBordered(true);
        unitBox.setCanLoseFocus(true);
        unitBox.setValue(ClientConfig.ENERGY_UNIT.get());
        unitBox.setResponder((s) -> {
            ClientConfig.ENERGY_UNIT.set(s);
            ClientConfig.ENERGY_UNIT.save();
        });

        this.addRenderableWidget(this.energyUnit);
        this.addRenderableWidget(this.unitBox);
        this.addRenderableWidget(this.doneButton);
        this.addRenderableWidget(new TextWidget(9, this.width, 8, this.title));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose()
    {
        ClientConfig.CONFIG.save();
        minecraft.setScreen(parent);
    }
}
