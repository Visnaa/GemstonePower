package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes
{
    public static final ResourceKey<DamageType> ELECTROCUTED = damageType("electrocuted");
    public static final ResourceKey<DamageType> RADIATION = damageType("radiation");

    private static ResourceKey<DamageType> damageType(String name)
    {
        return ResourceKey.create(Registries.DAMAGE_TYPE, GemstonePower.getId(name));
    }
}
