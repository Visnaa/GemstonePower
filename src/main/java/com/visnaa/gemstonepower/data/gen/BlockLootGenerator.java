package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.registry.ModBlocks;
import com.visnaa.gemstonepower.registry.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;

public class BlockLootGenerator extends BlockLootSubProvider
{
    public BlockLootGenerator()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        this.add(ModBlocks.RUBY_CRYSTALS.get(), block -> createOreDrop(ModBlocks.RUBY_CRYSTALS.get(), ModItems.RUBY_SEED.get()));
        this.add(ModBlocks.SAPPHIRE_CRYSTALS.get(), block -> createOreDrop(ModBlocks.SAPPHIRE_CRYSTALS.get(), ModItems.SAPPHIRE_SEED.get()));
        this.add(ModBlocks.AQUAMARINE_CRYSTALS.get(), block -> createOreDrop(ModBlocks.AQUAMARINE_CRYSTALS.get(), ModItems.AQUAMARINE_SEED.get()));
        this.add(ModBlocks.JADE_CRYSTALS.get(), block -> createOreDrop(ModBlocks.JADE_CRYSTALS.get(), ModItems.JADE_SEED.get()));
        this.add(ModBlocks.OPAL_CRYSTALS.get(), block -> createOreDrop(ModBlocks.OPAL_CRYSTALS.get(), ModItems.OPAL_SEED.get()));
        this.add(ModBlocks.YELLOW_DIAMOND_CRYSTALS.get(), block -> createOreDrop(ModBlocks.YELLOW_DIAMOND_CRYSTALS.get(), ModItems.YELLOW_DIAMOND_SEED.get()));
        this.add(ModBlocks.AMBER_CRYSTALS.get(), block -> createOreDrop(ModBlocks.AMBER_CRYSTALS.get(), ModItems.AMBER_SEED.get()));
        this.add(ModBlocks.TOPAZ_CRYSTALS.get(), block -> createOreDrop(ModBlocks.TOPAZ_CRYSTALS.get(), ModItems.TOPAZ_SEED.get()));
        this.add(ModBlocks.BERYLLIUM_CRYSTALS.get(), block -> createOreDrop(ModBlocks.BERYLLIUM_CRYSTALS.get(), ModItems.BERYLLIUM_SEED.get()));
        this.add(ModBlocks.BIXBIT_CRYSTALS.get(), block -> createOreDrop(ModBlocks.BIXBIT_CRYSTALS.get(), ModItems.BIXBIT_SEED.get()));
        this.add(ModBlocks.MALACHITE_CRYSTALS.get(), block -> createOreDrop(ModBlocks.MALACHITE_CRYSTALS.get(), ModItems.MALACHITE_SEED.get()));
        this.add(ModBlocks.ONYX_CRYSTALS.get(), block -> createOreDrop(ModBlocks.ONYX_CRYSTALS.get(), ModItems.ONYX_SEED.get()));
        this.add(ModBlocks.PERIDOT_CRYSTALS.get(), block -> createOreDrop(ModBlocks.PERIDOT_CRYSTALS.get(), ModItems.PERIDOT_SEED.get()));
        this.add(ModBlocks.MOON_STONE_CRYSTALS.get(), block -> createOreDrop(ModBlocks.MOON_STONE_CRYSTALS.get(), ModItems.MOON_STONE_SEED.get()));
        this.add(ModBlocks.SUN_STONE_CRYSTALS.get(), block -> createOreDrop(ModBlocks.SUN_STONE_CRYSTALS.get(), ModItems.SUN_STONE_SEED.get()));
        this.add(ModBlocks.CITRINE_CRYSTALS.get(), block -> createOreDrop(ModBlocks.CITRINE_CRYSTALS.get(), ModItems.CITRINE_SEED.get()));
        this.add(ModBlocks.DOLOMITE_CRYSTALS.get(), block -> createOreDrop(ModBlocks.DOLOMITE_CRYSTALS.get(), ModItems.DOLOMITE_SEED.get()));
        this.add(ModBlocks.TANZANITE_CRYSTALS.get(), block -> createOreDrop(ModBlocks.TANZANITE_CRYSTALS.get(), ModItems.TANZANITE_SEED.get()));

        this.dropSelf(ModBlocks.ALUMINUM_BLOCK.get());
        this.add(ModBlocks.ALUMINUM_ORE.get(), block -> createOreDrop(ModBlocks.ALUMINUM_ORE.get(), ModItems.RAW_ALUMINUM.get()));
        this.add(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(), ModItems.RAW_ALUMINUM.get()));

        this.dropSelf(ModBlocks.TIN_BLOCK.get());
        this.add(ModBlocks.TIN_ORE.get(), block -> createOreDrop(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get()));
        this.add(ModBlocks.DEEPSLATE_TIN_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_TIN_ORE.get(), ModItems.RAW_TIN.get()));

        this.dropSelf(ModBlocks.BRONZE_BLOCK.get());

        this.dropSelf(ModBlocks.SILVER_BLOCK.get());
        this.add(ModBlocks.SILVER_ORE.get(), block -> createOreDrop(ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get()));
        this.add(ModBlocks.DEEPSLATE_SILVER_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_SILVER_ORE.get(), ModItems.RAW_SILVER.get()));

        this.dropSelf(ModBlocks.ELECTRUM_BLOCK.get());

        this.dropSelf(ModBlocks.NICKEL_BLOCK.get());
        this.add(ModBlocks.NICKEL_ORE.get(), block -> createOreDrop(ModBlocks.NICKEL_ORE.get(), ModItems.RAW_NICKEL.get()));
        this.add(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), ModItems.RAW_NICKEL.get()));
        
        this.dropSelf(ModBlocks.INVAR_BLOCK.get());

        this.dropSelf(ModBlocks.CONSTANTAN_BLOCK.get());

        this.dropSelf(ModBlocks.PLATINUM_BLOCK.get());
        this.add(ModBlocks.PLATINUM_ORE.get(), block -> createOreDrop(ModBlocks.PLATINUM_ORE.get(), ModItems.RAW_PLATINUM.get()));
        this.add(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), ModItems.RAW_PLATINUM.get()));

        this.dropSelf(ModBlocks.STEEL_BLOCK.get());

        this.dropSelf(ModBlocks.LITHIUM_BLOCK.get());
        this.add(ModBlocks.LITHIUM_ORE.get(), block -> createOreDrop(ModBlocks.LITHIUM_ORE.get(), ModItems.RAW_LITHIUM.get()));
        this.add(ModBlocks.DEEPSLATE_LITHIUM_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_LITHIUM_ORE.get(), ModItems.RAW_LITHIUM.get()));

        this.dropSelf(ModBlocks.MAGNESIUM_BLOCK.get());
        this.add(ModBlocks.MAGNESIUM_ORE.get(), block -> createOreDrop(ModBlocks.MAGNESIUM_ORE.get(), ModItems.RAW_MAGNESIUM.get()));
        this.add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get(), ModItems.RAW_MAGNESIUM.get()));

        this.dropSelf(ModBlocks.URANIUM_BLOCK.get());
        this.add(ModBlocks.URANIUM_ORE.get(), block -> createOreDrop(ModBlocks.URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()));

        this.dropSelf(ModBlocks.LEAD_BLOCK.get());
        this.add(ModBlocks.LEAD_ORE.get(), block -> createOreDrop(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get()));
        this.add(ModBlocks.DEEPSLATE_LEAD_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), ModItems.RAW_LEAD.get()));

        this.dropSelf(ModBlocks.ZINC_BLOCK.get());
        this.add(ModBlocks.ZINC_ORE.get(), block -> createOreDrop(ModBlocks.ZINC_ORE.get(), ModItems.RAW_ZINC.get()));
        this.add(ModBlocks.DEEPSLATE_ZINC_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_ZINC_ORE.get(), ModItems.RAW_ZINC.get()));

        this.dropSelf(ModBlocks.RESIN_OAK_SAPLING.get());
        this.dropSelf(ModBlocks.RESIN_OAK_LOG.get());
        this.add(ModBlocks.RESIN_OAK_LEAVES.get(), state -> createOakLeavesDrops(state, ModBlocks.RESIN_OAK_SAPLING.get(), VanillaBlockLoot.NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.COPPER_WIRE.get());
        this.dropSelf(ModBlocks.COPPER_CABLE.get());
        this.dropSelf(ModBlocks.ALUMINUM_WIRE.get());
        this.dropSelf(ModBlocks.ALUMINUM_CABLE.get());
        this.dropSelf(ModBlocks.TIN_WIRE.get());
        this.dropSelf(ModBlocks.TIN_CABLE.get());
        this.dropSelf(ModBlocks.ELECTRUM_WIRE.get());
        this.dropSelf(ModBlocks.ELECTRUM_CABLE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        List<Block> tiered = List.of(ModBlocks.GEMSTONE_GENERATOR.get(), ModBlocks.GEMSTONE_CELL.get(), ModBlocks.CRYSTAL_GROWER.get(), ModBlocks.CRYSTAL_CHARGER.get(),
                ModBlocks.ELECTRIC_FURNACE.get(), ModBlocks.METAL_FORMER.get(), ModBlocks.PULVERIZER.get(), ModBlocks.ALLOY_SMELTER.get(), ModBlocks.EXTRACTOR.get(),
                ModBlocks.ORE_WASHER.get(), ModBlocks.COBBLESTONE_GENERATOR.get(), ModBlocks.SAWMILL.get(), ModBlocks.POLARIZER.get(), ModBlocks.SOLAR_PANEL.get(),
                ModBlocks.WATER_MILL.get(), ModBlocks.WIND_TURBINE.get(), ModBlocks.ITEM_PIPE.get());
        return ModBlocks.BLOCKS.getEntries().stream().filter(block -> !tiered.contains(block.get())).map(RegistryObject::get)::iterator;
    }
}
