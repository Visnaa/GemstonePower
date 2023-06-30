package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public final class ModPlacedFeatures
{
    public static final ResourceKey<PlacedFeature> RESIN_TREE = register("resin_tree");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, RESIN_TREE, configuredFeatures.getOrThrow(ModConfiguredFeatures.RESIN_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.02F, 1), ModBlocks.RESIN_OAK_SAPLING.get()));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, PlacementModifier... modifiers)
    {
        register(context, key, config, List.of(modifiers));
    }

    private static ResourceKey<PlacedFeature> register(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, GemstonePower.getId(name));
    }
}
