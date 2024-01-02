package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.world.feature.CrystalFeature;
import com.visnaa.gemstonepower.world.feature.configured.CrystalConfiguration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, GemstonePower.MOD_ID);

    public static final Supplier<CrystalFeature> CRYSTALS = FEATURES.register("crystals", () -> new CrystalFeature(CrystalConfiguration.CODEC));
}
