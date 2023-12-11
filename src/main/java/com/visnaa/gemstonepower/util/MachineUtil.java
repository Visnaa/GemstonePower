package com.visnaa.gemstonepower.util;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
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
    public static String getScaled(int value, final String unit)
    {
        int wholeDigits;
        int fractionDigits;
        float scaledEnergy;
        if (value > 1000000)
        {
            scaledEnergy = value / 1000000f;
            wholeDigits = (int) scaledEnergy;
            fractionDigits = (int) (value % 1000000) / 100;
            if (fractionDigits == 0)
                return wholeDigits + " M" + unit;
            return wholeDigits + "." + fractionDigits + " M" + unit;
        }
        else if (value > 1000)
        {
            scaledEnergy = value / 1000f;
            wholeDigits = (int) scaledEnergy;
            fractionDigits = (int) (value % 1000) / 100;
            if (fractionDigits == 0)
                return wholeDigits + " k" + unit;
            return wholeDigits + "." + fractionDigits + " k" + unit;
        }
        return value + " " + unit;
    }

    public static List<Component> getEnergyScaled(int energy, int maxEnergy)
    {
        final String unit = ClientConfig.ENERGY_UNIT.get();
        List<Component> components = new ArrayList<>();
        Component energyText = Component.literal("Energy:");
        Component energyAmount = Component.literal(energy + " " + unit + " / " + maxEnergy + " " + unit).withStyle(ChatFormatting.RED);
        Component energyScaled = Component.literal(getScaled(energy, ClientConfig.ENERGY_UNIT.get()) + " / " + MachineUtil.getScaled(maxEnergy, ClientConfig.ENERGY_UNIT.get())).withStyle(ChatFormatting.RED);
        Component shift = Component.translatable("menu." + GemstonePower.MOD_ID + ".show_details");
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

    public static List<Component> getHeatScaled(int heat, int maxHeat)
    {
        List<Component> components = new ArrayList<>();
        Component heatText = Component.literal("Heat:");
        Component heatAmount = Component.literal(heat + " " + "HU" + " / " + maxHeat + " " + "HU").withStyle(ChatFormatting.YELLOW);
        Component heatScaled = Component.literal(getScaled(heat, "HU") + " / " + MachineUtil.getScaled(maxHeat, "HU")).withStyle(ChatFormatting.YELLOW);
        Component shift = Component.translatable("menu." + GemstonePower.MOD_ID + ".show_details");
        if (Screen.hasShiftDown())
        {
            components.add(heatText);
            components.add(heatAmount);
        } else {
            components.add(heatText);
            components.add(heatScaled);
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
        {
            int i = (int) Math.round(Math.ceil(baseTotalTime / getMultiplier(state.getValue(Tier.TIER))));
            return i;
        }
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

    public enum EnergyUnits
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

        public int getId() {
            return this.id;
        }

        @NotNull
        public String getKey()
        {
            return "menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit." + unit.toLowerCase();
        }

        public String getUnit()
        {
            return unit;
        }

        public static EnergyUnits next(EnergyUnits unit)
        {
            return BY_ID.apply(unit.getId() + 1);
        }

        public static EnergyUnits byString(String text)
        {
            for (EnergyUnits unit : EnergyUnits.values())
                if (Objects.equals(unit.getUnit(), text))
                    return unit;
            return EnergyUnits.CUSTOM;
        }
    }

    public enum MachineModes
    {
        PRESSING(0, "pressing"),
        ROLLING(1, "rolling"),
        EXTRUDING(2, "extruding");

        private int mode;
        private String name;

        MachineModes(int mode, String name)
        {
            this.mode = mode;
            this.name = name;
        }

        public static MachineModes getByName(String name)
        {
            for (MachineModes mode : MachineModes.values())
                if (name.equals(mode.name))
                    return mode;
            return PRESSING;
        }

        public static boolean isValid(int mode, String modeStr)
        {
            for (MachineModes machineMode : MachineModes.values())
                if (mode == machineMode.mode && modeStr.equals(machineMode.name))
                    return true;
            return false;
        }

        public int getMode()
        {
            return mode;
        }

        public String getName()
        {
            return name;
        }
    }

    public enum MachineConfigs implements StringRepresentable
    {
        NONE("none"),
        INPUT("input"),
        OUTPUT("output"),
        ALL("all");

        private String name;

        MachineConfigs(String name)
        {
            this.name = name;
        }

        @Override
        public String getSerializedName()
        {
            return name;
        }

        public boolean canInput()
        {
            return this == INPUT || this == ALL;
        }

        public boolean canOutput()
        {
            return this == OUTPUT || this == ALL;
        }

        public boolean canInteract()
        {
            return this != NONE;
        }

        public static MachineConfigs getByName(String name)
        {
            for (MachineConfigs config : MachineConfigs.values())
                if (name.equals(config.name))
                    return config;
            return NONE;
        }
    }
}
