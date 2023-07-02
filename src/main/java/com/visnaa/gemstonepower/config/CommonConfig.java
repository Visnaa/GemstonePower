package com.visnaa.gemstonepower.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    public static final ForgeConfigSpec.IntValue ENERGY_TRANSFER_RATE;
    public static final ForgeConfigSpec.IntValue DEFAULT_MACHINE_CAPACITY;
    public static final ForgeConfigSpec.IntValue DEFAULT_GENERATOR_CAPACITY;

    static
    {
        BUILDER.push("Gemstone Power configurations");

        ENERGY_TRANSFER_RATE = BUILDER.comment(" Rate at which blocks will transfer energy")
                .defineInRange("energy_transfer_rate", 1, 0, Integer.MAX_VALUE);
        DEFAULT_MACHINE_CAPACITY = BUILDER.comment(" Machines energy storage capacity")
                .defineInRange("default_machine_capacity", 50000, 1, Integer.MAX_VALUE);
        DEFAULT_GENERATOR_CAPACITY = BUILDER.comment(" Generators energy storage capacity")
                .defineInRange("machine_capacity", 10000, 1, Integer.MAX_VALUE);

        BUILDER.pop();
        CONFIG = BUILDER.build();
    }
}
