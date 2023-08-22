package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.world.feature.CrystalFeature;
import com.visnaa.gemstonepower.world.feature.configured.CrystalConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, GemstonePower.MOD_ID);

    public static final RegistryObject<CrystalFeature> CRYSTALS = FEATURES.register("crystals", () -> new CrystalFeature(CrystalConfiguration.CODEC));
}
