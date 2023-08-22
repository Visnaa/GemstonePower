package com.visnaa.gemstonepower.client.screen;

import com.mojang.serialization.Codec;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.widget.TextWidget;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ClientConfigScreen extends Screen
{
    private Minecraft minecraft;
    private Screen parent;

    private OptionsList optionsList;
    private OptionInstance<MachineUtil.EnergyUnits> energyUnit;
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
        this.optionsList = new OptionsList(this.minecraft, this.width, this.height, 24, this.height - 24, 25);
        this.doneButton = new Button.Builder(Component.translatable("menu." + GemstonePower.MOD_ID + ".config_screen.done"), button -> this.onClose())
                .pos((this.width - 200) / 2, this.height - 26)
                .size(200, 20).build();

        unitBox = new EditBox(Minecraft.getInstance().font, width / 2 - 155, 50, 310, 16, Component.literal(ClientConfig.ENERGY_UNIT.get()));
        unitBox.setEditable(MachineUtil.EnergyUnits.byString(ClientConfig.ENERGY_UNIT.get()) == MachineUtil.EnergyUnits.CUSTOM);
        unitBox.setMaxLength(256);
        unitBox.setBordered(true);
        unitBox.setCanLoseFocus(true);
        unitBox.setValue(ClientConfig.ENERGY_UNIT.get());
        unitBox.setResponder((s) -> {
            ClientConfig.ENERGY_UNIT.set(s);
            ClientConfig.ENERGY_UNIT.save();
        });

        this.energyUnit = new OptionInstance<>("menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit",
                OptionInstance.noTooltip(), OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(List.of(MachineUtil.EnergyUnits.values()),
                Codec.INT.xmap(MachineUtil.EnergyUnits::byId, MachineUtil.EnergyUnits::getId)), MachineUtil.EnergyUnits.byString(ClientConfig.ENERGY_UNIT.get()), (value) -> {
                    if (value == MachineUtil.EnergyUnits.CUSTOM)
                    {
                        unitBox.setEditable(true);
                    }
                    else
                    {
                        ClientConfig.ENERGY_UNIT.set(value.getUnit());
                        ClientConfig.ENERGY_UNIT.save();
                        unitBox.setValue(ClientConfig.ENERGY_UNIT.get());
                        unitBox.setEditable(false);
                    }
                });

        this.optionsList.addBig(this.energyUnit);
        this.addRenderableWidget(this.optionsList);
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
        super.onClose();
    }
}
