package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public final class ModPlacedFeatures
{
    public static final ResourceKey<PlacedFeature> RESIN_TREE = register("resin_tree");

    public static final ResourceKey<PlacedFeature> ALUMINUM = register("aluminum");
    public static final ResourceKey<PlacedFeature> ALUMINUM_EXTRA = register("aluminum_extra");
    public static final ResourceKey<PlacedFeature> TIN = register("tin");
    public static final ResourceKey<PlacedFeature> TIN_EXTRA = register("tin_extra");
    public static final ResourceKey<PlacedFeature> SILVER = register("silver");
    public static final ResourceKey<PlacedFeature> SILVER_EXTRA = register("silver_extra");
    public static final ResourceKey<PlacedFeature> NICKEL = register("nickel");
    public static final ResourceKey<PlacedFeature> NICKEL_EXTRA = register("nickel_extra");
    public static final ResourceKey<PlacedFeature> PLATINUM = register("platinum");
    public static final ResourceKey<PlacedFeature> LITHIUM = register("lithium");
    public static final ResourceKey<PlacedFeature> MAGNESIUM = register("magnesium");
    public static final ResourceKey<PlacedFeature> URANIUM = register("uranium");
    public static final ResourceKey<PlacedFeature> URANIUM_EXTRA = register("uranium_extra");
    public static final ResourceKey<PlacedFeature> LEAD = register("lead");
    public static final ResourceKey<PlacedFeature> LEAD_EXTRA = register("lead_extra");
    public static final ResourceKey<PlacedFeature> ZINC = register("zinc");

    public static final ResourceKey<PlacedFeature> RUBY = register("ruby");
    public static final ResourceKey<PlacedFeature> SAPPHIRE = register("sapphire");
    public static final ResourceKey<PlacedFeature> AQUAMARINE = register("aquamarine");
    public static final ResourceKey<PlacedFeature> JADE = register("jade");
    public static final ResourceKey<PlacedFeature> OPAL = register("opal");
    public static final ResourceKey<PlacedFeature> YELLOW_DIAMOND = register("yellow_diamond");
    public static final ResourceKey<PlacedFeature> AMBER = register("amber");
    public static final ResourceKey<PlacedFeature> TOPAZ = register("topaz");
    public static final ResourceKey<PlacedFeature> BERYLLIUM = register("beryllium");
    public static final ResourceKey<PlacedFeature> BIXBIT = register("bixbit");
    public static final ResourceKey<PlacedFeature> MALACHITE = register("malachite");
    public static final ResourceKey<PlacedFeature> ONYX = register("onyx");
    public static final ResourceKey<PlacedFeature> PERIDOT = register("peridot");
    public static final ResourceKey<PlacedFeature> MOON_STONE = register("moon_stone");
    public static final ResourceKey<PlacedFeature> SUN_STONE = register("sun_stone");
    public static final ResourceKey<PlacedFeature> CITRINE = register("citrine");
    public static final ResourceKey<PlacedFeature> DOLOMITE = register("dolomite");
    public static final ResourceKey<PlacedFeature> TANZANITE = register("tanzanite");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, RESIN_TREE, configuredFeatures.getOrThrow(ModConfiguredFeatures.RESIN_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.02F, 1), ModBlocks.RESIN_OAK_SAPLING.get()));
        
        register(context, ALUMINUM, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALUMINUM),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-16),
                        VerticalAnchor.absolute(64)),
                BiomeFilter.biome());
        register(context, ALUMINUM_EXTRA, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALUMINUM),
                CountPlacement.of(6),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-32),
                        VerticalAnchor.absolute(64)),
                BiomeFilter.biome());
        register(context, TIN, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-20),
                        VerticalAnchor.absolute(48)),
                BiomeFilter.biome());
        register(context, TIN_EXTRA, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN),
                CountPlacement.of(6),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-32),
                        VerticalAnchor.absolute(100)),
                BiomeFilter.biome());
        register(context, SILVER, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(20)),
                BiomeFilter.biome());
        register(context, SILVER_EXTRA, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER),
                CountPlacement.of(6),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(40)),
                BiomeFilter.biome());
        register(context, NICKEL, configuredFeatures.getOrThrow(ModConfiguredFeatures.NICKEL),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-52),
                        VerticalAnchor.absolute(30)),
                BiomeFilter.biome());
        register(context, NICKEL_EXTRA, configuredFeatures.getOrThrow(ModConfiguredFeatures.NICKEL),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(10)),
                BiomeFilter.biome());
        register(context, PLATINUM, configuredFeatures.getOrThrow(ModConfiguredFeatures.PLATINUM),
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(16)),
                BiomeFilter.biome());
        register(context, LITHIUM, configuredFeatures.getOrThrow(ModConfiguredFeatures.LITHIUM),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-24),
                        VerticalAnchor.absolute(30)),
                BiomeFilter.biome());
        register(context, MAGNESIUM, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAGNESIUM),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-24),
                        VerticalAnchor.absolute(30)),
                BiomeFilter.biome());
        register(context, URANIUM, configuredFeatures.getOrThrow(ModConfiguredFeatures.URANIUM),
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(-20)),
                BiomeFilter.biome());
        register(context, URANIUM_EXTRA, configuredFeatures.getOrThrow(ModConfiguredFeatures.URANIUM),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(10)),
                BiomeFilter.biome());
        register(context, LEAD, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAD),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(20)),
                BiomeFilter.biome());
        register(context, LEAD_EXTRA, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAD),
                CountPlacement.of(6),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(40)),
                BiomeFilter.biome());
        register(context, ZINC, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZINC),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(20)),
                BiomeFilter.biome());

        List<PlacementModifier> crystalModifiers = List.of(CountPlacement.of(8), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, InSquarePlacement.spread(), SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13), BiomeFilter.biome());
        
        register(context, RUBY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RUBY), crystalModifiers);
        register(context, SAPPHIRE, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAPPHIRE), crystalModifiers);
        register(context, AQUAMARINE, configuredFeatures.getOrThrow(ModConfiguredFeatures.AQUAMARINE), crystalModifiers);
        register(context, JADE, configuredFeatures.getOrThrow(ModConfiguredFeatures.JADE), crystalModifiers);
        register(context, OPAL, configuredFeatures.getOrThrow(ModConfiguredFeatures.OPAL), crystalModifiers);
        register(context, YELLOW_DIAMOND, configuredFeatures.getOrThrow(ModConfiguredFeatures.YELLOW_DIAMOND), crystalModifiers);
        register(context, AMBER, configuredFeatures.getOrThrow(ModConfiguredFeatures.AMBER), crystalModifiers);
        register(context, TOPAZ, configuredFeatures.getOrThrow(ModConfiguredFeatures.TOPAZ), crystalModifiers);
        register(context, BERYLLIUM, configuredFeatures.getOrThrow(ModConfiguredFeatures.BERYLLIUM), crystalModifiers);
        register(context, BIXBIT, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIXBIT), crystalModifiers);
        register(context, MALACHITE, configuredFeatures.getOrThrow(ModConfiguredFeatures.MALACHITE), crystalModifiers);
        register(context, ONYX, configuredFeatures.getOrThrow(ModConfiguredFeatures.ONYX), crystalModifiers);
        register(context, PERIDOT, configuredFeatures.getOrThrow(ModConfiguredFeatures.PERIDOT), crystalModifiers);
        register(context, MOON_STONE, configuredFeatures.getOrThrow(ModConfiguredFeatures.MOON_STONE), crystalModifiers);
        register(context, SUN_STONE, configuredFeatures.getOrThrow(ModConfiguredFeatures.SUN_STONE), crystalModifiers);
        register(context, CITRINE, configuredFeatures.getOrThrow(ModConfiguredFeatures.CITRINE), crystalModifiers);
        register(context, DOLOMITE, configuredFeatures.getOrThrow(ModConfiguredFeatures.DOLOMITE), crystalModifiers);
        register(context, TANZANITE, configuredFeatures.getOrThrow(ModConfiguredFeatures.TANZANITE), crystalModifiers);
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
