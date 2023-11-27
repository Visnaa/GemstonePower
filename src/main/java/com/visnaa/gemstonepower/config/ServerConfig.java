package com.visnaa.gemstonepower.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class ServerConfig
{
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec CONFIG;

    public static final ModConfigSpec.IntValue ENERGY_TRANSFER_RATE;
    public static final ModConfigSpec.IntValue DEFAULT_MACHINE_USAGE;
    public static final ModConfigSpec.IntValue DEFAULT_MACHINE_TIME;
    public static final ModConfigSpec.IntValue DEFAULT_MACHINE_CAPACITY;
    public static final ModConfigSpec.IntValue DEFAULT_GENERATOR_CAPACITY;
    public static final ModConfigSpec.IntValue IDLE_GENERATOR_GENERATION;

    public static final ModConfigSpec.DoubleValue STANDARD_TIER_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue INTERMEDIATE_TIER_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue ADVANCED_TIER_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue ULTRA_TIER_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue EXTREME_TIER_MULTIPLIER;

    public static final ModConfigSpec.LongValue ENERGY_CABLE_FREQUENCY;
    public static final ModConfigSpec.DoubleValue ENERGY_CABLE_TRANSFER_MUL;
    public static final ModConfigSpec.LongValue ITEM_PIPE_FREQUENCY;
    public static final ModConfigSpec.DoubleValue ITEM_PIPE_TRANSFER_MUL;
    public static final ModConfigSpec.LongValue FLUID_PIPE_FREQUENCY;
    public static final ModConfigSpec.DoubleValue FLUID_PIPE_TRANSFER_MUL;

    public static final ModConfigSpec.IntValue MAX_FISSION_REACTOR_SIZE;

    public static final ModConfigSpec.IntValue FORTUNE_CRYSTAL_COOLDOWN;
    public static final ModConfigSpec.ConfigValue<List<?>> AWAKENED_PLAYERS;

    static
    {
        BUILDER.push("Gemstone Power configurations");

        BUILDER.comment("#".repeat(29));
        BUILDER.comment(" Machine settings" + " ".repeat(11) + "#");
        BUILDER.comment("#".repeat(29));

        ENERGY_TRANSFER_RATE = BUILDER.comment("Rate at which blocks will transfer energy")
                .defineInRange("energy_transfer_rate", 65536, 0, Integer.MAX_VALUE);
        DEFAULT_MACHINE_USAGE = BUILDER.comment("Machines default energy usage per tick")
                .defineInRange("default_machine_usage", 40, 1, Integer.MAX_VALUE);
        DEFAULT_MACHINE_TIME = BUILDER.comment("Machines default processing duration")
                .defineInRange("default_machine_time", 200, 1, Integer.MAX_VALUE);
        DEFAULT_MACHINE_CAPACITY = BUILDER.comment("Machines energy storage capacity")
                .defineInRange("default_machine_capacity", 50000, 1, Integer.MAX_VALUE);
        DEFAULT_GENERATOR_CAPACITY = BUILDER.comment("Generators energy storage capacity")
                .defineInRange("default_generator_capacity", 10000, 1, Integer.MAX_VALUE);
        IDLE_GENERATOR_GENERATION = BUILDER.comment("Idle generators (e.g. Solar Panel) generated energy per tick")
                .defineInRange("idle_generator_generation", 5, 1, Integer.MAX_VALUE);

        BUILDER.comment("#".repeat(29));
        BUILDER.comment(" Tiers multipliers" + " ".repeat(10) + "#");
        BUILDER.comment("#".repeat(29));

        STANDARD_TIER_MULTIPLIER = BUILDER.comment("Standard tier multiplier")
                .defineInRange("standard_multiplier", 1.0, 0.1, Integer.MAX_VALUE);
        INTERMEDIATE_TIER_MULTIPLIER = BUILDER.comment("Intermediate tier multiplier")
                .defineInRange("intermediate_multiplier", 1.5, 0.1, Integer.MAX_VALUE);
        ADVANCED_TIER_MULTIPLIER = BUILDER.comment("Advanced tier multiplier")
                .defineInRange("advanced_multiplier", 2.25, 0.1, Integer.MAX_VALUE);
        ULTRA_TIER_MULTIPLIER = BUILDER.comment("Ultra tier multiplier")
                .defineInRange("ultra_multiplier", 3.25, 0.1, Integer.MAX_VALUE);
        EXTREME_TIER_MULTIPLIER = BUILDER.comment("Extreme tier multiplier")
                .defineInRange("extreme_multiplier", 5.0, 0.1, Integer.MAX_VALUE);

        BUILDER.comment("#".repeat(29));
        BUILDER.comment(" Fluid & Item pipe settings" + " ".repeat(1) + "#");
        BUILDER.comment("#".repeat(29));

        ENERGY_CABLE_FREQUENCY = BUILDER.comment("Sets in what time intervals cables and wires distribute energy (in ticks)")
                .defineInRange("item_pipe_frequency", 1L, 1L, Long.MAX_VALUE);
        ENERGY_CABLE_TRANSFER_MUL = BUILDER.comment("Multiplies base cable transfer by specified amount")
                .defineInRange("energy_cable_transfer_mul", 1, 0, Double.MAX_VALUE);
        ITEM_PIPE_FREQUENCY = BUILDER.comment("Sets in what time intervals item pipes distribute items (in ticks)")
                .defineInRange("item_pipe_frequency", 1L, 1L, Long.MAX_VALUE);
        ITEM_PIPE_TRANSFER_MUL = BUILDER.comment("Multiplies base item pipe transfer by specified amount")
                .defineInRange("item_pipe_transfer_mul", 1, 0, Double.MAX_VALUE);
        FLUID_PIPE_FREQUENCY = BUILDER.comment("Sets in what time intervals fluid pipes distribute fluids (in ticks)")
                .defineInRange("fluid_pipe_frequency", 1L, 1L, Long.MAX_VALUE);
        FLUID_PIPE_TRANSFER_MUL = BUILDER.comment("Multiplies base fluid pipe transfer by specified amount")
                .defineInRange("fluid_pipe_transfer_mul", 1, 0, Double.MAX_VALUE);

        MAX_FISSION_REACTOR_SIZE = BUILDER.comment("Max edge length of fission reactor")
                .defineInRange("max_fission_reactor_size", 10, 0, Integer.MAX_VALUE);

        BUILDER.comment("#".repeat(29));
        BUILDER.comment(" Crystal settings" + " ".repeat(11) + "#");
        BUILDER.comment("#".repeat(29));

        FORTUNE_CRYSTAL_COOLDOWN = BUILDER.comment("Cooldown of Fortune Crystal in ticks")
                .defineInRange("fortune_crystal_cooldown", 12000, 0, Integer.MAX_VALUE);
        AWAKENED_PLAYERS = BUILDER.comment("UUIDs of players that can use all of features that gems provide")
                .defineListAllowEmpty("awakened_players", new ArrayList<>(), s -> true);

        BUILDER.pop();
        CONFIG = BUILDER.build();
    }
}
