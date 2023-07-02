package com.visnaa.gemstonepower.config;

import com.mojang.serialization.Codec;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.util.EnergyUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ConfigScreen extends Screen
{
    private OptionsList optionsList;
    private OptionInstance<EnergyUtilities.EnergyUnits> energyUnit;
    private Button doneButton;

    public ConfigScreen()
    {
        super(Component.translatable("menu." + GemstonePower.MOD_ID + ".config_screen"));
    }

    @Override
    protected void init()
    {
        this.optionsList = new OptionsList(this.minecraft, this.width, this.height, 24, this.height - 32, 25);
        this.doneButton = new Button.Builder(Component.translatable("menu." + GemstonePower.MOD_ID + ".config_screen.done"), button -> this.onClose())
                .pos((this.width - 200) / 2, this.height - 26)
                .size(200, 20).build();
        this.energyUnit = new OptionInstance<>("menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit",
                OptionInstance.noTooltip(), OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(List.of(EnergyUtilities.EnergyUnits.values()),
                Codec.INT.xmap(EnergyUtilities.EnergyUnits::byId, EnergyUtilities.EnergyUnits::getId)), ClientConfig.ENERGY_UNIT.get(),
                (value) -> {
                    ClientConfig.ENERGY_UNIT.set(value);
                    ClientConfig.ENERGY_UNIT.save();
                });
        this.optionsList.addBig(this.energyUnit);
        this.addRenderableWidget(this.optionsList);
        this.addRenderableWidget(this.doneButton);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(graphics);
        graphics.drawCenteredString(Minecraft.getInstance().font, this.title.getString(), this.width / 2, 8, 0xFFFFFF);
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose()
    {
        ClientConfig.CONFIG.save();
        super.onClose();
    }
}
