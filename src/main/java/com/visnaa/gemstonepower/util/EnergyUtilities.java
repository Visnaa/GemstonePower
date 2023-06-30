package com.visnaa.gemstonepower.util;

import com.visnaa.gemstonepower.GemstonePower;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public final class EnergyUtilities
{
    public static final int UTR = 40; // "UTR" stands for universal transfer rate. At this rate energy will be transferred between blocks per tick.
    public static final int BV = 160;

    public static String getEnergyScaled(int energy)
    {
        int wholeDigits;
        int fractionDigits;
        float scaledEnergy;
        if (energy > 1000000)
        {
            scaledEnergy = energy / 1000000f;
            wholeDigits = (int) scaledEnergy;
            fractionDigits = (int) (energy % 1000000) / 100;
            if (fractionDigits == 0) return wholeDigits + " MFE";
            return wholeDigits + "." + fractionDigits + " MFE";
        }
        else if (energy > 1000)
        {
            scaledEnergy = energy / 1000f;
            wholeDigits = (int) scaledEnergy;
            fractionDigits = (int) (energy % 1000) / 100;
            if (fractionDigits == 0) return wholeDigits + " kFE";
            return wholeDigits + "." + fractionDigits + " kFE";
        }
        return energy + " FE";
    }

    public static List<Component> getDefaultTooltips(int energy, int maxEnergy)
    {
        List<Component> components = new ArrayList<>();
        Component energyText = Component.literal("Energy:");
        Component energyAmount = Component.literal(energy + " FE / " + maxEnergy + " FE");
        Component energyScaled = Component.literal(EnergyUtilities.getEnergyScaled(energy) + " / " + EnergyUtilities.getEnergyScaled(maxEnergy));
        Component shift = Component.translatable("menu." + GemstonePower.MOD_ID + ".energy_shift_tip");
        if (Screen.hasShiftDown())
        {
            components.add(energyText);
            components.add(energyAmount);
        } else {
            components.add(energyText);
            components.add(energyScaled);
            components.add(shift);
        }
        return components;
    }
}
