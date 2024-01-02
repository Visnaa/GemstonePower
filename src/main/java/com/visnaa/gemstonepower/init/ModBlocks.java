package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.*;
import com.visnaa.gemstonepower.block.entity.ReactorFrameBE;
import com.visnaa.gemstonepower.block.machine.*;
import com.visnaa.gemstonepower.block.pipe.cable.*;
import com.visnaa.gemstonepower.block.pipe.fluid.CopperFluidPipeBlock;
import com.visnaa.gemstonepower.block.pipe.fluid.GoldFluidPipeBlock;
import com.visnaa.gemstonepower.block.pipe.fluid.InvarFluidPipeBlock;
import com.visnaa.gemstonepower.block.pipe.fluid.SteelFluidPipeBlock;
import com.visnaa.gemstonepower.block.pipe.item.*;
import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;

public final class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, GemstonePower.MOD_ID);

    public static final DeferredHolder<Block, Block> RESIN_OAK_SAPLING = BLOCKS.register("resin_oak_sapling", () -> new SaplingBlock(new TreeGrower("resin_oak", 0.8F,Optional.empty(), Optional.empty(), Optional.of(ModConfiguredFeatures.RESIN_TREE), Optional.empty(), Optional.empty(), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> RESIN_OAK_LOG = BLOCKS.register("resin_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredHolder<Block, Block> RESIN_OAK_LEAVES = BLOCKS.register("resin_oak_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredHolder<Block, Block> ALUMINUM_BLOCK = BLOCKS.register("aluminum_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.ALUMINUM));
    public static final DeferredHolder<Block, Block> ALUMINUM_ORE = BLOCKS.register("aluminum_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.ALUMINUM));
    public static final DeferredHolder<Block, Block> DEEPSLATE_ALUMINUM_ORE = BLOCKS.register("deepslate_aluminum_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.ALUMINUM));

    public static final DeferredHolder<Block, Block> TIN_BLOCK = BLOCKS.register("tin_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.TIN));
    public static final DeferredHolder<Block, Block> TIN_ORE = BLOCKS.register("tin_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.TIN));
    public static final DeferredHolder<Block, Block> DEEPSLATE_TIN_ORE = BLOCKS.register("deepslate_tin_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.TIN));

    public static final DeferredHolder<Block, Block> BRONZE_BLOCK = BLOCKS.register("bronze_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.BRONZE));

    public static final DeferredHolder<Block, Block> SILVER_BLOCK = BLOCKS.register("silver_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.SILVER));
    public static final DeferredHolder<Block, Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.SILVER));
    public static final DeferredHolder<Block, Block> DEEPSLATE_SILVER_ORE = BLOCKS.register("deepslate_silver_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.SILVER));

    public static final DeferredHolder<Block, Block> ELECTRUM_BLOCK = BLOCKS.register("electrum_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.ELECTRUM));

    public static final DeferredHolder<Block, Block> NICKEL_BLOCK = BLOCKS.register("nickel_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.NICKEL));
    public static final DeferredHolder<Block, Block> NICKEL_ORE = BLOCKS.register("nickel_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.NICKEL));
    public static final DeferredHolder<Block, Block> DEEPSLATE_NICKEL_ORE = BLOCKS.register("deepslate_nickel_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.NICKEL));

    public static final DeferredHolder<Block, Block> INVAR_BLOCK = BLOCKS.register("invar_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.INVAR));

    public static final DeferredHolder<Block, Block> CONSTANTAN_BLOCK = BLOCKS.register("constantan_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.CONSTANTAN));

    public static final DeferredHolder<Block, Block> PLATINUM_BLOCK = BLOCKS.register("platinum_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.PLATINUM));
    public static final DeferredHolder<Block, Block> PLATINUM_ORE = BLOCKS.register("platinum_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.PLATINUM));
    public static final DeferredHolder<Block, Block> DEEPSLATE_PLATINUM_ORE = BLOCKS.register("deepslate_platinum_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.PLATINUM));

    public static final DeferredHolder<Block, Block> STEEL_BLOCK = BLOCKS.register("steel_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.STEEL));

    public static final DeferredHolder<Block, Block> LITHIUM_BLOCK = BLOCKS.register("lithium_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.LITHIUM));
    public static final DeferredHolder<Block, Block> LITHIUM_ORE = BLOCKS.register("lithium_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.LITHIUM));
    public static final DeferredHolder<Block, Block> DEEPSLATE_LITHIUM_ORE = BLOCKS.register("deepslate_lithium_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.LITHIUM));

    public static final DeferredHolder<Block, Block> MAGNESIUM_BLOCK = BLOCKS.register("magnesium_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.MAGNESIUM));
    public static final DeferredHolder<Block, Block> MAGNESIUM_ORE = BLOCKS.register("magnesium_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.MAGNESIUM));
    public static final DeferredHolder<Block, Block> DEEPSLATE_MAGNESIUM_ORE = BLOCKS.register("deepslate_magnesium_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.MAGNESIUM));

    public static final DeferredHolder<Block, Block> URANIUM_BLOCK = BLOCKS.register("uranium_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.URANIUM));
    public static final DeferredHolder<Block, Block> URANIUM_ORE = BLOCKS.register("uranium_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.URANIUM));
    public static final DeferredHolder<Block, Block> DEEPSLATE_URANIUM_ORE = BLOCKS.register("deepslate_uranium_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.URANIUM));

    public static final DeferredHolder<Block, Block> LEAD_BLOCK = BLOCKS.register("lead_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.LEAD));
    public static final DeferredHolder<Block, Block> LEAD_ORE = BLOCKS.register("lead_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.LEAD));
    public static final DeferredHolder<Block, Block> DEEPSLATE_LEAD_ORE = BLOCKS.register("deepslate_lead_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.LEAD));

    public static final DeferredHolder<Block, Block> ZINC_BLOCK = BLOCKS.register("zinc_block", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.ZINC));
    public static final DeferredHolder<Block, Block> ZINC_ORE = BLOCKS.register("zinc_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.ZINC));
    public static final DeferredHolder<Block, Block> DEEPSLATE_ZINC_ORE = BLOCKS.register("deepslate_zinc_ore", () -> new TintedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.ZINC));

    public static final DeferredHolder<Block, Block> GEMSTONE_GENERATOR = BLOCKS.register("gemstone_generator", () -> new GemstoneGeneratorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)));
    public static final DeferredHolder<Block, Block> GEMSTONE_CELL = BLOCKS.register("gemstone_cell", () -> new GemstoneCellBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> CRYSTAL_GROWER = BLOCKS.register("crystal_grower", () -> new CrystalGrowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> CRYSTAL_CHARGER = BLOCKS.register("crystal_charger", () -> new CrystalChargerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> ELECTRIC_FURNACE = BLOCKS.register("electric_furnace", () -> new ElectricFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 14 : 0)));
    public static final DeferredHolder<Block, Block> METAL_FORMER = BLOCKS.register("metal_former", () -> new MetalFormerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> PULVERIZER = BLOCKS.register("pulverizer", () -> new PulverizerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> ALLOY_SMELTER = BLOCKS.register("alloy_smelter", () -> new AlloySmelterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> EXTRACTOR = BLOCKS.register("extractor", () -> new ExtractorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> ORE_WASHER = BLOCKS.register("ore_washer", () -> new OreWasherBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> COBBLESTONE_GENERATOR = BLOCKS.register("cobblestone_generator", () -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> SAWMILL = BLOCKS.register("sawmill", () -> new SawmillBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> POLARIZER = BLOCKS.register("polarizer", () -> new PolarizerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> GEMSTONE_MANIPULATOR = BLOCKS.register("gemstone_manipulator", () -> new GemstoneManipulatorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> FISSION_REACTOR = BLOCKS.register("fission_reactor", () -> new FissionReactorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> REACTOR_WALL = BLOCKS.register("reactor_wall", () -> new ReactorWallBlock(BlockBehaviour.Properties.of().strength(20.0F, 120.0F).mapColor(MapColor.TERRACOTTA_CYAN).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> REACTOR_FRAME = BLOCKS.register("reactor_frame", () -> new ReactorFrameBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.FUEL));
    public static final DeferredHolder<Block, Block> WATER_COOLING = BLOCKS.register("water_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.WATER));
    public static final DeferredHolder<Block, Block> SNOW_COOLING = BLOCKS.register("snow_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.SNOW));
    public static final DeferredHolder<Block, Block> ICE_COOLING = BLOCKS.register("ice_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.ICE));
    public static final DeferredHolder<Block, Block> PACKED_ICE_COOLING = BLOCKS.register("packed_ice_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.PACKED_ICE));
    public static final DeferredHolder<Block, Block> BLUE_ICE_COOLING = BLOCKS.register("blue_ice_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.BLUE_ICE));
    public static final DeferredHolder<Block, Block> PRISMARINE_COOLING = BLOCKS.register("prismarine_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.PRISMARINE));

    public static final DeferredHolder<Block, Block> SOLAR_PANEL = BLOCKS.register("solar_panel", () -> new SolarPanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> WATER_MILL = BLOCKS.register("water_mill", () -> new WaterMillBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> WIND_TURBINE = BLOCKS.register("wind_turbine", () -> new WindTurbineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> TANK = BLOCKS.register("tank", () -> new TankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));

    public static final DeferredHolder<Block, Block> COPPER_WIRE = BLOCKS.register("copper_wire", () -> new CopperCableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion(), false));
    public static final DeferredHolder<Block, Block> COPPER_CABLE = BLOCKS.register("copper_cable", () -> new CopperCableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion(), true));
    public static final DeferredHolder<Block, Block> ALUMINUM_WIRE = BLOCKS.register("aluminum_wire", () -> new AluminumCableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion(), false));
    public static final DeferredHolder<Block, Block> ALUMINUM_CABLE = BLOCKS.register("aluminum_cable", () -> new AluminumCableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion(), true));
    public static final DeferredHolder<Block, Block> TIN_WIRE = BLOCKS.register("tin_wire", () -> new TinCableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion(), false));
    public static final DeferredHolder<Block, Block> TIN_CABLE = BLOCKS.register("tin_cable", () -> new TinCableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion(), true));
    public static final DeferredHolder<Block, Block> ELECTRUM_WIRE = BLOCKS.register("electrum_wire", () -> new ElectrumCableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion(), false));
    public static final DeferredHolder<Block, Block> ELECTRUM_CABLE = BLOCKS.register("electrum_cable", () -> new ElectrumCableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion(), true));
    public static final DeferredHolder<Block, Block> GEMSTONE_CABLE = BLOCKS.register("gemstone_cable", () -> new GemstoneCableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion(), true));

    public static final DeferredHolder<Block, Block> IRON_ITEM_PIPE = BLOCKS.register("iron_item_pipe", () -> new IronItemPipeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredHolder<Block, Block> GOLD_ITEM_PIPE = BLOCKS.register("gold_item_pipe", () -> new GoldItemPipeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredHolder<Block, Block> COPPER_ITEM_PIPE = BLOCKS.register("copper_item_pipe", () -> new CopperItemPipeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredHolder<Block, Block> PLATINUM_ITEM_PIPE = BLOCKS.register("platinum_item_pipe", () -> new PlatinumItemPipeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredHolder<Block, Block> LEAD_ITEM_PIPE = BLOCKS.register("lead_item_pipe", () -> new LeadItemPipeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final DeferredHolder<Block, Block> GOLD_FLUID_PIPE = BLOCKS.register("gold_fluid_pipe", () -> new GoldFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredHolder<Block, Block> COPPER_FLUID_PIPE = BLOCKS.register("copper_fluid_pipe", () -> new CopperFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredHolder<Block, Block> INVAR_FLUID_PIPE = BLOCKS.register("invar_fluid_pipe", () -> new InvarFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredHolder<Block, Block> STEEL_FLUID_PIPE = BLOCKS.register("steel_fluid_pipe", () -> new SteelFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final DeferredHolder<Block, Block> RUBY_CRYSTALS = BLOCKS.register("ruby_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.RUBY));
    public static final DeferredHolder<Block, Block> SAPPHIRE_CRYSTALS = BLOCKS.register("sapphire_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.SAPPHIRE));
    public static final DeferredHolder<Block, Block> AQUAMARINE_CRYSTALS = BLOCKS.register("aquamarine_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.AQUAMARINE));
    public static final DeferredHolder<Block, Block> JADE_CRYSTALS = BLOCKS.register("jade_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.JADE));
    public static final DeferredHolder<Block, Block> OPAL_CRYSTALS = BLOCKS.register("opal_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.OPAL));
    public static final DeferredHolder<Block, Block> YELLOW_DIAMOND_CRYSTALS = BLOCKS.register("yellow_diamond_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.YELLOW_DIAMOND));
    public static final DeferredHolder<Block, Block> AMBER_CRYSTALS = BLOCKS.register("amber_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.AMBER));
    public static final DeferredHolder<Block, Block> TOPAZ_CRYSTALS = BLOCKS.register("topaz_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.TOPAZ));
    public static final DeferredHolder<Block, Block> BERYLLIUM_CRYSTALS = BLOCKS.register("beryllium_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.BERYLLIUM));
    public static final DeferredHolder<Block, Block> BIXBIT_CRYSTALS = BLOCKS.register("bixbit_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.BIXBIT));
    public static final DeferredHolder<Block, Block> MALACHITE_CRYSTALS = BLOCKS.register("malachite_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.MALACHITE));
    public static final DeferredHolder<Block, Block> ONYX_CRYSTALS = BLOCKS.register("onyx_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.ONYX));
    public static final DeferredHolder<Block, Block> PERIDOT_CRYSTALS = BLOCKS.register("peridot_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.PERIDOT));
    public static final DeferredHolder<Block, Block> MOON_STONE_CRYSTALS = BLOCKS.register("moon_stone_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.MOON_STONE));
    public static final DeferredHolder<Block, Block> SUN_STONE_CRYSTALS = BLOCKS.register("sun_stone_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.SUN_STONE));
    public static final DeferredHolder<Block, Block> CITRINE_CRYSTALS = BLOCKS.register("citrine_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.CITRINE));
    public static final DeferredHolder<Block, Block> DOLOMITE_CRYSTALS = BLOCKS.register("dolomite_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.DOLOMITE));
    public static final DeferredHolder<Block, Block> TANZANITE_CRYSTALS = BLOCKS.register("tanzanite_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.TANZANITE));
}
