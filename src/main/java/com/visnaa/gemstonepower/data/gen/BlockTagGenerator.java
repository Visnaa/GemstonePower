package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.visnaa.gemstonepower.data.tag.CommonTags.Blocks.*;
import static net.minecraft.tags.BlockTags.*;
import static net.minecraftforge.common.Tags.Blocks.*;

public class BlockTagGenerator extends TagsProvider<Block>
{
    public BlockTagGenerator(PackOutput output, ResourceKey<? extends Registry<Block>> registry, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, registry, completableFuture, GemstonePower.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RUBY_CRYSTALS.getKey())
                .add(ModBlocks.SAPPHIRE_CRYSTALS.getKey())
                .add(ModBlocks.AQUAMARINE_CRYSTALS.getKey())
                .add(ModBlocks.JADE_CRYSTALS.getKey())
                .add(ModBlocks.OPAL_CRYSTALS.getKey())
                .add(ModBlocks.YELLOW_DIAMOND_CRYSTALS.getKey())
                .add(ModBlocks.AMBER_CRYSTALS.getKey())
                .add(ModBlocks.TOPAZ_CRYSTALS.getKey())
                .add(ModBlocks.BERYLLIUM_CRYSTALS.getKey())
                .add(ModBlocks.BIXBIT_CRYSTALS.getKey())
                .add(ModBlocks.MALACHITE_CRYSTALS.getKey())
                .add(ModBlocks.ONYX_CRYSTALS.getKey())
                .add(ModBlocks.PERIDOT_CRYSTALS.getKey())
                .add(ModBlocks.SUN_STONE_CRYSTALS.getKey())
                .add(ModBlocks.MOON_STONE_CRYSTALS.getKey())
                .add(ModBlocks.CITRINE_CRYSTALS.getKey())
                .add(ModBlocks.DOLOMITE_CRYSTALS.getKey())
                .add(ModBlocks.TANZANITE_CRYSTALS.getKey())

                .add(ModBlocks.ALUMINUM_BLOCK.getKey())
                .add(ModBlocks.ALUMINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ALUMINUM_ORE.getKey())
                .add(ModBlocks.TIN_BLOCK.getKey())
                .add(ModBlocks.TIN_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.getKey())
                .add(ModBlocks.BRONZE_BLOCK.getKey())
                .add(ModBlocks.SILVER_BLOCK.getKey())
                .add(ModBlocks.SILVER_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.getKey())
                .add(ModBlocks.ELECTRUM_BLOCK.getKey())
                .add(ModBlocks.NICKEL_BLOCK.getKey())
                .add(ModBlocks.NICKEL_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.getKey())
                .add(ModBlocks.INVAR_BLOCK.getKey())
                .add(ModBlocks.CONSTANTAN_BLOCK.getKey())
                .add(ModBlocks.PLATINUM_BLOCK.getKey())
                .add(ModBlocks.PLATINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_PLATINUM_ORE.getKey())
                .add(ModBlocks.STEEL_BLOCK.getKey())
                .add(ModBlocks.LITHIUM_BLOCK.getKey())
                .add(ModBlocks.LITHIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LITHIUM_ORE.getKey())
                .add(ModBlocks.MAGNESIUM_BLOCK.getKey())
                .add(ModBlocks.MAGNESIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.getKey())
                .add(ModBlocks.URANIUM_BLOCK.getKey())
                .add(ModBlocks.URANIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.getKey())
                .add(ModBlocks.LEAD_BLOCK.getKey())
                .add(ModBlocks.LEAD_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.getKey())
                .add(ModBlocks.ZINC_BLOCK.getKey())
                .add(ModBlocks.ZINC_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.getKey())

                .add(ModBlocks.GEMSTONE_GENERATOR.getKey())
                .add(ModBlocks.GEMSTONE_CELL.getKey())
                .add(ModBlocks.CRYSTAL_GROWER.getKey())
                .add(ModBlocks.CRYSTAL_CHARGER.getKey())

                .add(ModBlocks.ELECTRIC_FURNACE.getKey())
                .add(ModBlocks.METAL_FORMER.getKey())
                .add(ModBlocks.PULVERIZER.getKey())
                .add(ModBlocks.ALLOY_SMELTER.getKey())
                .add(ModBlocks.EXTRACTOR.getKey())
                .add(ModBlocks.ORE_WASHER.getKey())
                .add(ModBlocks.COBBLESTONE_GENERATOR.getKey())
                .add(ModBlocks.SAWMILL.getKey())
                .add(ModBlocks.POLARIZER.getKey())
                .add(ModBlocks.GEMSTONE_MANIPULATOR.getKey())

                .add(ModBlocks.SOLAR_PANEL.getKey())
                .add(ModBlocks.WATER_MILL.getKey())
                .add(ModBlocks.WIND_TURBINE.getKey())

                .add(ModBlocks.FISSION_REACTOR.getKey())
                .add(ModBlocks.REACTOR_FRAME.getKey())
                .add(ModBlocks.REACTOR_WALL.getKey());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RUBY_CRYSTALS.getKey())
                .add(ModBlocks.SAPPHIRE_CRYSTALS.getKey())
                .add(ModBlocks.AQUAMARINE_CRYSTALS.getKey())
                .add(ModBlocks.JADE_CRYSTALS.getKey())
                .add(ModBlocks.OPAL_CRYSTALS.getKey())
                .add(ModBlocks.YELLOW_DIAMOND_CRYSTALS.getKey())
                .add(ModBlocks.AMBER_CRYSTALS.getKey())
                .add(ModBlocks.TOPAZ_CRYSTALS.getKey())
                .add(ModBlocks.BERYLLIUM_CRYSTALS.getKey())
                .add(ModBlocks.BIXBIT_CRYSTALS.getKey())
                .add(ModBlocks.MALACHITE_CRYSTALS.getKey())
                .add(ModBlocks.ONYX_CRYSTALS.getKey())
                .add(ModBlocks.PERIDOT_CRYSTALS.getKey())
                .add(ModBlocks.SUN_STONE_CRYSTALS.getKey())
                .add(ModBlocks.MOON_STONE_CRYSTALS.getKey())
                .add(ModBlocks.CITRINE_CRYSTALS.getKey())
                .add(ModBlocks.DOLOMITE_CRYSTALS.getKey())
                .add(ModBlocks.TANZANITE_CRYSTALS.getKey())

                .add(ModBlocks.GEMSTONE_GENERATOR.getKey())
                .add(ModBlocks.GEMSTONE_CELL.getKey())
                .add(ModBlocks.CRYSTAL_GROWER.getKey())
                .add(ModBlocks.CRYSTAL_CHARGER.getKey())

                .add(ModBlocks.ALUMINUM_BLOCK.getKey())
                .add(ModBlocks.TIN_BLOCK.getKey())
                .add(ModBlocks.BRONZE_BLOCK.getKey())
                .add(ModBlocks.SILVER_BLOCK.getKey())
                .add(ModBlocks.ELECTRUM_BLOCK.getKey())
                .add(ModBlocks.NICKEL_BLOCK.getKey())
                .add(ModBlocks.INVAR_BLOCK.getKey())
                .add(ModBlocks.CONSTANTAN_BLOCK.getKey())
                .add(ModBlocks.STEEL_BLOCK.getKey())
                .add(ModBlocks.LITHIUM_BLOCK.getKey())
                .add(ModBlocks.MAGNESIUM_BLOCK.getKey())
                .add(ModBlocks.URANIUM_BLOCK.getKey())
                .add(ModBlocks.LEAD_BLOCK.getKey())
                .add(ModBlocks.ZINC_BLOCK.getKey())

                .add(ModBlocks.ELECTRIC_FURNACE.getKey())
                .add(ModBlocks.METAL_FORMER.getKey())
                .add(ModBlocks.PULVERIZER.getKey())
                .add(ModBlocks.ALLOY_SMELTER.getKey())
                .add(ModBlocks.EXTRACTOR.getKey())
                .add(ModBlocks.ORE_WASHER.getKey())
                .add(ModBlocks.COBBLESTONE_GENERATOR.getKey())
                .add(ModBlocks.SAWMILL.getKey())
                .add(ModBlocks.POLARIZER.getKey())
                .add(ModBlocks.GEMSTONE_MANIPULATOR.getKey())

                .add(ModBlocks.SOLAR_PANEL.getKey())
                .add(ModBlocks.WATER_MILL.getKey())
                .add(ModBlocks.WIND_TURBINE.getKey())

                .add(ModBlocks.FISSION_REACTOR.getKey())
                .add(ModBlocks.REACTOR_FRAME.getKey())
                .add(ModBlocks.REACTOR_WALL.getKey());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.ALUMINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ALUMINUM_ORE.getKey())
                .add(ModBlocks.TIN_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.getKey())
                .add(ModBlocks.SILVER_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.getKey())
                .add(ModBlocks.NICKEL_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.getKey())
                .add(ModBlocks.PLATINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_PLATINUM_ORE.getKey())
                .add(ModBlocks.LITHIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LITHIUM_ORE.getKey())
                .add(ModBlocks.MAGNESIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.getKey())
                .add(ModBlocks.URANIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.getKey())
                .add(ModBlocks.LEAD_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.getKey())
                .add(ModBlocks.ZINC_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.getKey());

        this.tag(ORES)
                .add(ModBlocks.ALUMINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ALUMINUM_ORE.getKey())
                .add(ModBlocks.TIN_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.getKey())
                .add(ModBlocks.SILVER_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.getKey())
                .add(ModBlocks.NICKEL_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.getKey())
                .add(ModBlocks.PLATINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_PLATINUM_ORE.getKey())
                .add(ModBlocks.LITHIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LITHIUM_ORE.getKey())
                .add(ModBlocks.MAGNESIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.getKey())
                .add(ModBlocks.URANIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.getKey())
                .add(ModBlocks.LEAD_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.getKey())
                .add(ModBlocks.ZINC_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.getKey());
        this.tag(ORES_IN_GROUND_STONE)
                .add(ModBlocks.ALUMINUM_ORE.getKey())
                .add(ModBlocks.TIN_ORE.getKey())
                .add(ModBlocks.SILVER_ORE.getKey())
                .add(ModBlocks.NICKEL_ORE.getKey())
                .add(ModBlocks.PLATINUM_ORE.getKey())
                .add(ModBlocks.LITHIUM_ORE.getKey())
                .add(ModBlocks.MAGNESIUM_ORE.getKey())
                .add(ModBlocks.URANIUM_ORE.getKey())
                .add(ModBlocks.LEAD_ORE.getKey())
                .add(ModBlocks.ZINC_ORE.getKey());
        this.tag(ORES_IN_GROUND_DEEPSLATE)
                .add(ModBlocks.DEEPSLATE_ALUMINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_PLATINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LITHIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.getKey());
        this.tag(ORES_ALUMINUM)
                .add(ModBlocks.ALUMINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ALUMINUM_ORE.getKey());
        this.tag(ORES_TIN)
                .add(ModBlocks.TIN_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.getKey());
        this.tag(ORES_SILVER)
                .add(ModBlocks.SILVER_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.getKey());
        this.tag(ORES_NICKEL)
                .add(ModBlocks.NICKEL_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.getKey());
        this.tag(ORES_PLATINUM)
                .add(ModBlocks.PLATINUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_PLATINUM_ORE.getKey());
        this.tag(ORES_LITHIUM)
                .add(ModBlocks.LITHIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LITHIUM_ORE.getKey());
        this.tag(ORES_MAGNESIUM)
                .add(ModBlocks.MAGNESIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.getKey());
        this.tag(ORES_URANIUM)
                .add(ModBlocks.URANIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.getKey());
        this.tag(ORES_LEAD)
                .add(ModBlocks.LEAD_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.getKey());
        this.tag(ORES_ZINC)
                .add(ModBlocks.ZINC_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.getKey());

        this.tag(STORAGE_BLOCKS)
                .add(ModBlocks.ALUMINUM_BLOCK.getKey())
                .add(ModBlocks.TIN_BLOCK.getKey())
                .add(ModBlocks.BRONZE_BLOCK.getKey())
                .add(ModBlocks.SILVER_BLOCK.getKey())
                .add(ModBlocks.ELECTRUM_BLOCK.getKey())
                .add(ModBlocks.NICKEL_BLOCK.getKey())
                .add(ModBlocks.INVAR_BLOCK.getKey())
                .add(ModBlocks.CONSTANTAN_BLOCK.getKey())
                .add(ModBlocks.PLATINUM_BLOCK.getKey())
                .add(ModBlocks.STEEL_BLOCK.getKey())
                .add(ModBlocks.LITHIUM_BLOCK.getKey())
                .add(ModBlocks.MAGNESIUM_BLOCK.getKey())
                .add(ModBlocks.URANIUM_BLOCK.getKey())
                .add(ModBlocks.LEAD_BLOCK.getKey())
                .add(ModBlocks.ZINC_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_ALUMINUM)
                .add(ModBlocks.ALUMINUM_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_TIN)
                .add(ModBlocks.TIN_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_SILVER)
                .add(ModBlocks.SILVER_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_BRONZE)
                .add(ModBlocks.BRONZE_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_ELECTRUM)
                .add(ModBlocks.ELECTRUM_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_NICKEL)
                .add(ModBlocks.NICKEL_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_INVAR)
                .add(ModBlocks.INVAR_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_CONSTANTAN)
                .add(ModBlocks.CONSTANTAN_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_PLATINUM)
                .add(ModBlocks.PLATINUM_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_STEEL)
                .add(ModBlocks.STEEL_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_LITHIUM)
                .add(ModBlocks.LITHIUM_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_MAGNESIUM)
                .add(ModBlocks.MAGNESIUM_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_URANIUM)
                .add(ModBlocks.URANIUM_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_LEAD)
                .add(ModBlocks.LEAD_BLOCK.getKey());
        this.tag(STORAGE_BLOCKS_ZINC)
                .add(ModBlocks.ZINC_BLOCK.getKey());

        this.tag(WIRES)
                .addTag(WIRES_COPPER)
                .addTag(WIRES_ALUMINUM)
                .addTag(WIRES_TIN)
                .addTag(WIRES_ELECTRUM);
        this.tag(WIRES_COPPER)
                .add(ModBlocks.COPPER_WIRE.getKey())
                .add(ModBlocks.COPPER_CABLE.getKey());
        this.tag(WIRES_ALUMINUM)
                .add(ModBlocks.ALUMINUM_WIRE.getKey())
                .add(ModBlocks.ALUMINUM_CABLE.getKey());
        this.tag(WIRES_TIN)
                .add(ModBlocks.TIN_WIRE.getKey())
                .add(ModBlocks.TIN_CABLE.getKey());
        this.tag(WIRES_ELECTRUM)
                .add(ModBlocks.ELECTRUM_WIRE.getKey())
                .add(ModBlocks.ELECTRUM_CABLE.getKey());

        this.tag(MINEABLE_WITH_AXE)
                .add(ModBlocks.RESIN_OAK_LOG.getKey());

        this.tag(SAPLINGS)
                .add(ModBlocks.RESIN_OAK_SAPLING.getKey());
        this.tag(LOGS)
                .add(ModBlocks.RESIN_OAK_LOG.getKey());
        this.tag(LOGS_THAT_BURN)
                .add(ModBlocks.RESIN_OAK_LOG.getKey());

        this.tag(LEAVES)
                .add(ModBlocks.RESIN_OAK_LEAVES.getKey());
        this.tag(MINEABLE_WITH_HOE)
                .add(ModBlocks.RESIN_OAK_LEAVES.getKey());
    }
}
