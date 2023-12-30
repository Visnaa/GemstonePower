package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.init.ModConfiguredFeatures;
import com.visnaa.gemstonepower.init.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.RegistryPatchGenerator;

import java.util.concurrent.CompletableFuture;

public class GemstonePowerRegistryGenerator
{
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);

    public static CompletableFuture<RegistrySetBuilder.PatchedRegistries> createLookup(CompletableFuture<HolderLookup.Provider> provider)
    {
        return RegistryPatchGenerator.createLookup(provider, BUILDER);
    }
}
