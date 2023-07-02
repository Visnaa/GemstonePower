package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.CrystalBlock;
import com.visnaa.gemstonepower.world.feature.configured.CrystalConfiguration;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public final class ModConfiguredFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> RESIN_TREE = register("resin_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINUM = register("aluminum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINUM_EXTRA = register("aluminum_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN = register("tin");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_EXTRA = register("tin_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER = register("silver");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_EXTRA = register("silver_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NICKEL = register("nickel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NICKEL_EXTRA = register("nickel_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PLATINUM = register("platinum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LITHIUM = register("lithium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGNESIUM = register("magnesium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> URANIUM = register("uranium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> URANIUM_EXTRA = register("uranium_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD = register("lead");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_EXTRA = register("lead_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZINC = register("zinc");
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBY = register("ruby");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAPPHIRE = register("sapphire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AQUAMARINE = register("aquamarine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JADE = register("jade");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OPAL = register("opal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_DIAMOND = register("yellow_diamond");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AMBER = register("amber");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TOPAZ = register("topaz");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BERYLLIUM = register("beryllium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIXBIT = register("bixbit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MALACHITE = register("malachite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ONYX = register("onyx");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PERIDOT = register("peridot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOON_STONE = register("moon_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SUN_STONE = register("sun_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CITRINE = register("citrine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DOLOMITE = register("dolomite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TANZANITE = register("tanzanite");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        register(context, RESIN_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.RESIN_OAK_LOG.get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(ModBlocks.RESIN_OAK_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
        
        register(context, ALUMINUM, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.ALUMINUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_ALUMINUM_ORE.get().defaultBlockState())), 4));
        register(context, ALUMINUM_EXTRA, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.ALUMINUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_ALUMINUM_ORE.get().defaultBlockState())), 9));
        register(context, TIN, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.TIN_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())), 4));
        register(context, TIN_EXTRA, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.TIN_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())), 9));
        register(context, SILVER, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.SILVER_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())), 4));
        register(context, SILVER_EXTRA, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.SILVER_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())), 9));
        register(context, NICKEL, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.NICKEL_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_NICKEL_ORE.get().defaultBlockState())), 4));
        register(context, NICKEL_EXTRA, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.NICKEL_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_NICKEL_ORE.get().defaultBlockState())), 9));
        register(context, PLATINUM, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.PLATINUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_PLATINUM_ORE.get().defaultBlockState())), 2));
        register(context, LITHIUM, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.LITHIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_LITHIUM_ORE.get().defaultBlockState())), 4));
        register(context, MAGNESIUM, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.MAGNESIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get().defaultBlockState())), 4));
        register(context, URANIUM, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.URANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_URANIUM_ORE.get().defaultBlockState())), 1));
        register(context, URANIUM_EXTRA, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.URANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_URANIUM_ORE.get().defaultBlockState())), 3));
        register(context, LEAD, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.LEAD_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())), 4));
        register(context, LEAD_EXTRA, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.LEAD_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())), 9));
        register(context, ZINC, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                        ModBlocks.ZINC_ORE.get().defaultBlockState()),
                OreConfiguration.target(
                        new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        ModBlocks.DEEPSLATE_ZINC_ORE.get().defaultBlockState())), 6));

        HolderSet<Block> blocks = HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.GRANITE, Blocks.DRIPSTONE_BLOCK, Blocks.CALCITE, Blocks.TUFF, Blocks.DEEPSLATE);

        register(context, RUBY, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.RUBY_CRYSTALS.get(), 
                10, true, true, true, 0.9F, blocks));
        register(context, SAPPHIRE, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.SAPPHIRE_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, AQUAMARINE, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.AQUAMARINE_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, JADE, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.JADE_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, OPAL, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.OPAL_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, YELLOW_DIAMOND, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.YELLOW_DIAMOND_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, AMBER, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.AMBER_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, TOPAZ, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.TOPAZ_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, BERYLLIUM, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.BERYLLIUM_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, BIXBIT, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.BIXBIT_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, MALACHITE, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.MALACHITE_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, ONYX, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.ONYX_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, PERIDOT, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.PERIDOT_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, MOON_STONE, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.MOON_STONE_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, SUN_STONE, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.SUN_STONE_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, CITRINE, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.CITRINE_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, DOLOMITE, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.DOLOMITE_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
        register(context, TANZANITE, ModFeatures.CRYSTALS.get(), new CrystalConfiguration((CrystalBlock) ModBlocks.TANZANITE_CRYSTALS.get(),
                10, true, true, true, 0.9F, blocks));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config)
    {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> register(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, GemstonePower.getId(name));
    }
}
