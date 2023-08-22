package com.visnaa.gemstonepower.util;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.OptionEnum;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;

public final class MachineUtil
{
    public static String getEnergyScaled(int energy)
    {
        final String unit = ClientConfig.ENERGY_UNIT.get();
        int wholeDigits;
        int fractionDigits;
        float scaledEnergy;
        if (energy > 1000000)
        {
            scaledEnergy = energy / 1000000f;
            wholeDigits = (int) scaledEnergy;
            fractionDigits = (int) (energy % 1000000) / 100;
            if (fractionDigits == 0)
                return wholeDigits + " M" + unit;
            return wholeDigits + "." + fractionDigits + " M" + unit;
        }
        else if (energy > 1000)
        {
            scaledEnergy = energy / 1000f;
            wholeDigits = (int) scaledEnergy;
            fractionDigits = (int) (energy % 1000) / 100;
            if (fractionDigits == 0)
                return wholeDigits + " k" + unit;
            return wholeDigits + "." + fractionDigits + " k" + unit;
        }
        return energy + " §c" + unit;
    }

    public static List<Component> getDefaultTooltips(int energy, int maxEnergy)
    {
        final String unit = ClientConfig.ENERGY_UNIT.get();
        List<Component> components = new ArrayList<>();
        Component energyText = Component.literal("Energy:");
        Component energyAmount = Component.literal("§c" + energy + " " + unit + "§f / §c" + maxEnergy + " " + unit);
        Component energyScaled = Component.literal("§c" + MachineUtil.getEnergyScaled(energy) + "§f / §c" + MachineUtil.getEnergyScaled(maxEnergy));
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
        if (state.hasProperty(Tier.TIER))
            return (int) Math.round(Math.ceil(2 * baseCapacity * getMultiplier(state.getValue(Tier.TIER))));
        return 0;
    }

    public static int getUsage(BlockState state, int baseUsage)
    {
        if (state.hasProperty(Tier.TIER))
            return (int) Math.round(Math.ceil(baseUsage * getMultiplier(state.getValue(Tier.TIER))));
        return 0;
    }

    public static int getGeneration(BlockState state, int baseGeneration)
    {
        if (state.hasProperty(Tier.TIER))
            return (int) Math.round(Math.ceil(baseGeneration * getMultiplier(state.getValue(Tier.TIER))));
        return 0;
    }

    public static int getTotalTime(BlockState state, int baseTotalTime)
    {
        if (state.hasProperty(Tier.TIER))
            return (int) Math.round(Math.ceil(baseTotalTime * (1 / getMultiplier(state.getValue(Tier.TIER)))));
        return 0;
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

    public static HashMap<Fluid, Integer> createFluidTank(Fluid fluid, int capacity)
    {
        return createFluidTank(List.of(fluid), List.of(capacity));
    }

    public static HashMap<Fluid, Integer> createFluidTank(List<Fluid> fluids, List<Integer> capacities)
    {
        HashMap<Fluid, Integer> tanks = new HashMap<>();
        for (int i = 0; i < Math.max(fluids.size(), capacities.size()); i++)
            tanks.put(fluids.get(i) == null ? Fluids.EMPTY : fluids.get(0), capacities.get(i) == null ? 0 : capacities.get(i));
        return tanks;
    }

    public enum EnergyUnits implements OptionEnum
    {
        CUSTOM(0, "custom"),
        GE(1, "GE"),
        FE(2, "FE"),
        RF(3, "RF");

        private static final IntFunction<EnergyUnits> BY_ID = ByIdMap.continuous(EnergyUnits::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        private final int id;
        private final String unit;

        EnergyUnits(int id, String unit)
        {
            this.id = id;
            this.unit = unit;
        }

        @Override
        public int getId() {
            return this.id;
        }

        @Override
        @NotNull
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

        public static EnergyUnits byString(String text)
        {
            for (EnergyUnits unit : EnergyUnits.values())
                if (Objects.equals(unit.getUnit(), text))
                    return unit;
            return EnergyUnits.GE;
        }
    }
}
