package com.visnaa.gemstonepower.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    public static final ForgeConfigSpec.ConfigValue<String> ENERGY_UNIT;

    static
    {
        BUILDER.push("Gemstone Power client configurations");

        ENERGY_UNIT = BUILDER.comment("Displayed energy unit")
                .define("energy_unit", "GE");

        BUILDER.pop();
        CONFIG = BUILDER.build();
    }
}
