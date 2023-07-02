package com.visnaa.gemstonepower.config;

import com.visnaa.gemstonepower.util.EnergyUtilities;
import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    public static final ForgeConfigSpec.EnumValue<EnergyUtilities.EnergyUnits> ENERGY_UNIT;

    static
    {
        BUILDER.push("Gemstone Power client configurations");

        ENERGY_UNIT = BUILDER.comment("Displayed energy unit")
                .defineEnum("energy_unit", EnergyUtilities.EnergyUnits.GE, EnergyUtilities.EnergyUnits.FE, EnergyUtilities.EnergyUnits.RF, EnergyUtilities.EnergyUnits.GE);

        BUILDER.pop();
        CONFIG = BUILDER.build();
    }
}
