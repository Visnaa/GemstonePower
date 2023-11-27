package com.visnaa.gemstonepower.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig
{
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec CONFIG;

    public static final ModConfigSpec.ConfigValue<String> ENERGY_UNIT;
    public static final ModConfigSpec.ConfigValue<String> HEAT_UNIT;
    public static final ModConfigSpec.ConfigValue<String> TIME_UNIT;

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
