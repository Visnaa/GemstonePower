package com.visnaa.gemstonepower.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    public static final ForgeConfigSpec.ConfigValue<String> ENERGY_UNIT;
    public static final ForgeConfigSpec.ConfigValue<String> HEAT_UNIT;
    public static final ForgeConfigSpec.ConfigValue<String> TIME_UNIT;

    static
    {
        BUILDER.push("Gemstone Power client configurations");

        ENERGY_UNIT = BUILDER.comment("Displayed energy unit")
                .define("energy_unit", "GE");
        HEAT_UNIT = BUILDER.comment("Displayed heat unit")
                .define("heat_unit", "HU");
        TIME_UNIT = BUILDER.comment("Displayed time unit")
                .define("time_unit", "t");

        BUILDER.pop();
        CONFIG = BUILDER.build();
    }
}
