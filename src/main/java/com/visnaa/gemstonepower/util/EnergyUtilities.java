package com.visnaa.gemstonepower.util;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.client.PrioritizeChunkUpdates;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.OptionEnum;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

public final class EnergyUtilities
{
    public static String getEnergyScaled(int energy)
    {
        final String unit = ClientConfig.ENERGY_UNIT.get().getUnit();
        int wholeDigits;
        int fractionDigits;
        float scaledEnergy;
        if (energy > 1000000)
        {
            scaledEnergy = energy / 1000000f;
            wholeDigits = (int) scaledEnergy;
            fractionDigits = (int) (energy % 1000000) / 100;
            if (fractionDigits == 0) return wholeDigits + " M" + unit;
            return wholeDigits + "." + fractionDigits + " M" + unit;
        }
        else if (energy > 1000)
        {
            scaledEnergy = energy / 1000f;
            wholeDigits = (int) scaledEnergy;
            fractionDigits = (int) (energy % 1000) / 100;
            if (fractionDigits == 0) return wholeDigits + " k" + unit;
            return wholeDigits + "." + fractionDigits + " k" + unit;
        }
        return energy + " " + unit;
    }

    public static List<Component> getDefaultTooltips(int energy, int maxEnergy)
    {
        final String unit = ClientConfig.ENERGY_UNIT.get().getUnit();
        List<Component> components = new ArrayList<>();
        Component energyText = Component.literal("Energy:");
        Component energyAmount = Component.literal(energy + " " + unit + " / " + maxEnergy + " " + unit);
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

    public static int getCapacity(BlockState state, int baseCapacity)
    {
        return (int) Math.round(Math.ceil(2 * baseCapacity * getMultiplier(state.getValue(Tier.TIER))));
    }

    public static int getUsage(BlockState state, int baseUsage)
    {
        return (int) Math.round(Math.ceil(baseUsage * getMultiplier(state.getValue(Tier.TIER))));
    }

    public static int getGeneration(BlockState state, int baseGeneration)
    {
        return (int) Math.round(Math.ceil(1.5 * baseGeneration * getMultiplier(state.getValue(Tier.TIER))));
    }

    public static int getTotalTime(BlockState state, int baseTotalTime)
    {
        return (int) Math.round(Math.ceil(baseTotalTime * (1 / getMultiplier(state.getValue(Tier.TIER)))));
    }

    private static double getMultiplier(Tier tier)
    {
        switch (tier)
        {
            case STANDARD: return ServerConfig.STANDARD_TIER_MULTIPLIER.get();
            case INTERMEDIATE: return ServerConfig.INTERMEDIATE_TIER_MULTIPLIER.get();
            case ADVANCED: return ServerConfig.ADVANCED_TIER_MULTIPLIER.get();
            case ULTRA: return ServerConfig.ULTRA_TIER_MULTIPLIER.get();
            case EXTREME: return ServerConfig.EXTREME_TIER_MULTIPLIER.get();
        }
        return 1;
    }

    public enum EnergyUnits implements OptionEnum
    {
        GE(0, "GE"),
        FE(1, "FE"),
        RF(2, "RF");

        private static final IntFunction<EnergyUnits> BY_ID = ByIdMap.continuous(EnergyUnits::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        private final int id;
        private final String unit;

        EnergyUnits(int id, String unit)
        {
            this.id = id;
            this.unit = unit;
        }

        public int getId() {
            return this.id;
        }

        @Override
        public String getKey()
        {
            return "menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit." + unit.toLowerCase();
        }

        public String getUnit()
        {
            return unit;
        }

        public static EnergyUnits byId(int id)
        {
            return BY_ID.apply(id);
        }
    }
}
