package com.visnaa.gemstonepower.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    public static final ForgeConfigSpec.IntValue ENERGY_TRANSFER_RATE;
    public static final ForgeConfigSpec.IntValue DEFAULT_MACHINE_USAGE;
    public static final ForgeConfigSpec.IntValue DEFAULT_MACHINE_TIME;
    public static final ForgeConfigSpec.IntValue DEFAULT_MACHINE_CAPACITY;
    public static final ForgeConfigSpec.IntValue DEFAULT_GENERATOR_CAPACITY;
    public static final ForgeConfigSpec.IntValue IDLE_GENERATOR_GENERATION;

    public static final ForgeConfigSpec.DoubleValue STANDARD_TIER_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue INTERMEDIATE_TIER_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue ADVANCED_TIER_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue ULTRA_TIER_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue EXTREME_TIER_MULTIPLIER;

    static
    {
        BUILDER.push("Gemstone Power configurations");

        BUILDER.comment("#".repeat(29));
        BUILDER.comment(" Machine settings" + " ".repeat(11) + "#");
        BUILDER.comment("#".repeat(29));

        ENERGY_TRANSFER_RATE = BUILDER.comment("Rate at which blocks will transfer energy")
                .defineInRange("energy_transfer_rate", 40, 0, Integer.MAX_VALUE);
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

        BUILDER.pop();
        CONFIG = BUILDER.build();
    }
}
