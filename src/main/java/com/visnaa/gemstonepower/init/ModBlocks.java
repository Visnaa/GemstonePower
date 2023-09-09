package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.*;
import com.visnaa.gemstonepower.block.entity.ReactorFrameBE;
import com.visnaa.gemstonepower.block.grower.ResinOakGrower;
import com.visnaa.gemstonepower.block.machine.*;
import com.visnaa.gemstonepower.block.pipe.cable.AluminumCableBlock;
import com.visnaa.gemstonepower.block.pipe.cable.CopperCableBlock;
import com.visnaa.gemstonepower.block.pipe.cable.ElectrumCableBlock;
import com.visnaa.gemstonepower.block.pipe.cable.TinCableBlock;
import com.visnaa.gemstonepower.block.pipe.fluid.CopperFluidPipeBlock;
import com.visnaa.gemstonepower.block.pipe.fluid.GoldFluidPipeBlock;
import com.visnaa.gemstonepower.block.pipe.fluid.InvarFluidPipeBlock;
import com.visnaa.gemstonepower.block.pipe.fluid.SteelFluidPipeBlock;
import com.visnaa.gemstonepower.block.pipe.item.*;
import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GemstonePower.MOD_ID);

    public static final RegistryObject<Block> RESIN_OAK_SAPLING = BLOCKS.register("resin_oak_sapling", () -> new SaplingBlock(new ResinOakGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> RESIN_OAK_LOG = BLOCKS.register("resin_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> RESIN_OAK_LEAVES = BLOCKS.register("resin_oak_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> ALUMINUM_BLOCK = BLOCKS.register("aluminum_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.ALUMINUM));
    public static final RegistryObject<Block> ALUMINUM_ORE = BLOCKS.register("aluminum_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.ALUMINUM));
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = BLOCKS.register("deepslate_aluminum_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.ALUMINUM));

    public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("tin_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.TIN));
    public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.TIN));
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = BLOCKS.register("deepslate_tin_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.TIN));

    public static final RegistryObject<Block> BRONZE_BLOCK = BLOCKS.register("bronze_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.BRONZE));

    public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.SILVER));
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.SILVER));
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = BLOCKS.register("deepslate_silver_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.SILVER));

    public static final RegistryObject<Block> ELECTRUM_BLOCK = BLOCKS.register("electrum_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.ELECTRUM));

    public static final RegistryObject<Block> NICKEL_BLOCK = BLOCKS.register("nickel_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.NICKEL));
    public static final RegistryObject<Block> NICKEL_ORE = BLOCKS.register("nickel_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.NICKEL));
    public static final RegistryObject<Block> DEEPSLATE_NICKEL_ORE = BLOCKS.register("deepslate_nickel_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.NICKEL));

    public static final RegistryObject<Block> INVAR_BLOCK = BLOCKS.register("invar_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.INVAR));

    public static final RegistryObject<Block> CONSTANTAN_BLOCK = BLOCKS.register("constantan_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.CONSTANTAN));

    public static final RegistryObject<Block> PLATINUM_BLOCK = BLOCKS.register("platinum_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.PLATINUM));
    public static final RegistryObject<Block> PLATINUM_ORE = BLOCKS.register("platinum_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.PLATINUM));
    public static final RegistryObject<Block> DEEPSLATE_PLATINUM_ORE = BLOCKS.register("deepslate_platinum_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.PLATINUM));

    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.STEEL));

    public static final RegistryObject<Block> LITHIUM_BLOCK = BLOCKS.register("lithium_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.LITHIUM));
    public static final RegistryObject<Block> LITHIUM_ORE = BLOCKS.register("lithium_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.LITHIUM));
    public static final RegistryObject<Block> DEEPSLATE_LITHIUM_ORE = BLOCKS.register("deepslate_lithium_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.LITHIUM));

    public static final RegistryObject<Block> MAGNESIUM_BLOCK = BLOCKS.register("magnesium_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.MAGNESIUM));
    public static final RegistryObject<Block> MAGNESIUM_ORE = BLOCKS.register("magnesium_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.MAGNESIUM));
    public static final RegistryObject<Block> DEEPSLATE_MAGNESIUM_ORE = BLOCKS.register("deepslate_magnesium_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.MAGNESIUM));

    public static final RegistryObject<Block> URANIUM_BLOCK = BLOCKS.register("uranium_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.URANIUM));
    public static final RegistryObject<Block> URANIUM_ORE = BLOCKS.register("uranium_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.URANIUM));
    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = BLOCKS.register("deepslate_uranium_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.URANIUM));

    public static final RegistryObject<Block> LEAD_BLOCK = BLOCKS.register("lead_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.LEAD));
    public static final RegistryObject<Block> LEAD_ORE = BLOCKS.register("lead_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.LEAD));
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = BLOCKS.register("deepslate_lead_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.LEAD));

    public static final RegistryObject<Block> ZINC_BLOCK = BLOCKS.register("zinc_block", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), Tints.ZINC));
    public static final RegistryObject<Block> ZINC_ORE = BLOCKS.register("zinc_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), Tints.ZINC));
    public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = BLOCKS.register("deepslate_zinc_ore", () -> new TintedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(), Tints.ZINC));
    
    public static final RegistryObject<Block> GEMSTONE_GENERATOR = BLOCKS.register("gemstone_generator", () -> new GemstoneGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)));
    public static final RegistryObject<Block> GEMSTONE_CELL = BLOCKS.register("gemstone_cell", () -> new GemstoneCellBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRYSTAL_GROWER = BLOCKS.register("crystal_grower", () -> new CrystalGrowerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRYSTAL_CHARGER = BLOCKS.register("crystal_charger", () -> new CrystalChargerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ELECTRIC_FURNACE = BLOCKS.register("electric_furnace", () -> new ElectricFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 14 : 0)));
    public static final RegistryObject<Block> METAL_FORMER = BLOCKS.register("metal_former", () -> new MetalFormerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PULVERIZER = BLOCKS.register("pulverizer", () -> new PulverizerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ALLOY_SMELTER = BLOCKS.register("alloy_smelter", () -> new AlloySmelterBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> EXTRACTOR = BLOCKS.register("extractor", () -> new ExtractorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORE_WASHER = BLOCKS.register("ore_washer", () -> new OreWasherBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> COBBLESTONE_GENERATOR = BLOCKS.register("cobblestone_generator", () -> new CobblestoneGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SAWMILL = BLOCKS.register("sawmill", () -> new SawmillBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> POLARIZER = BLOCKS.register("polarizer", () -> new PolarizerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> FISSION_REACTOR = BLOCKS.register("fission_reactor", () -> new FissionReactorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> REACTOR_WALL = BLOCKS.register("reactor_wall", () -> new ReactorWallBlock(BlockBehaviour.Properties.of().strength(20.0F, 120.0F).mapColor(MapColor.TERRACOTTA_CYAN).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> REACTOR_FRAME = BLOCKS.register("reactor_frame", () -> new ReactorFrameBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.FUEL));
    public static final RegistryObject<Block> WATER_COOLING = BLOCKS.register("water_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.WATER));
    public static final RegistryObject<Block> SNOW_COOLING = BLOCKS.register("snow_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.SNOW));
    public static final RegistryObject<Block> ICE_COOLING = BLOCKS.register("ice_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.ICE));
    public static final RegistryObject<Block> PACKED_ICE_COOLING = BLOCKS.register("packed_ice_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.PACKED_ICE));
    public static final RegistryObject<Block> BLUE_ICE_COOLING = BLOCKS.register("blue_ice_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.BLUE_ICE));
    public static final RegistryObject<Block> PRISMARINE_COOLING = BLOCKS.register("prismarine_cooling", () -> new ReactorFrameBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops(), ReactorFrameBE.Type.PRISMARINE));

    public static final RegistryObject<Block> SOLAR_PANEL = BLOCKS.register("solar_panel", () -> new SolarPanelBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WATER_MILL = BLOCKS.register("water_mill", () -> new WaterMillBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WIND_TURBINE = BLOCKS.register("wind_turbine", () -> new WindTurbineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TANK = BLOCKS.register("tank", () -> new TankBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));

    public static final RegistryObject<Block> COPPER_WIRE = BLOCKS.register("copper_wire", () -> new CopperCableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).noOcclusion(), false));
    public static final RegistryObject<Block> COPPER_CABLE = BLOCKS.register("copper_cable", () -> new CopperCableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).noOcclusion(), true));
    public static final RegistryObject<Block> ALUMINUM_WIRE = BLOCKS.register("aluminum_wire", () -> new AluminumCableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).noOcclusion(), false));
    public static final RegistryObject<Block> ALUMINUM_CABLE = BLOCKS.register("aluminum_cable", () -> new AluminumCableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).noOcclusion(), true));
    public static final RegistryObject<Block> TIN_WIRE = BLOCKS.register("tin_wire", () -> new TinCableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).noOcclusion(), false));
    public static final RegistryObject<Block> TIN_CABLE = BLOCKS.register("tin_cable", () -> new TinCableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).noOcclusion(), true));
    public static final RegistryObject<Block> ELECTRUM_WIRE = BLOCKS.register("electrum_wire", () -> new ElectrumCableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).noOcclusion(), false));
    public static final RegistryObject<Block> ELECTRUM_CABLE = BLOCKS.register("electrum_cable", () -> new ElectrumCableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).noOcclusion(), true));

    public static final RegistryObject<Block> IRON_ITEM_PIPE = BLOCKS.register("iron_item_pipe", () -> new IronItemPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> GOLD_ITEM_PIPE = BLOCKS.register("gold_item_pipe", () -> new GoldItemPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> COPPER_ITEM_PIPE = BLOCKS.register("copper_item_pipe", () -> new CopperItemPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> PLATINUM_ITEM_PIPE = BLOCKS.register("platinum_item_pipe", () -> new PlatinumItemPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> LEAD_ITEM_PIPE = BLOCKS.register("lead_item_pipe", () -> new LeadItemPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> GOLD_FLUID_PIPE = BLOCKS.register("gold_fluid_pipe", () -> new GoldFluidPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> COPPER_FLUID_PIPE = BLOCKS.register("copper_fluid_pipe", () -> new CopperFluidPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> INVAR_FLUID_PIPE = BLOCKS.register("invar_fluid_pipe", () -> new InvarFluidPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> STEEL_FLUID_PIPE = BLOCKS.register("steel_fluid_pipe", () -> new SteelFluidPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> RUBY_CRYSTALS = BLOCKS.register("ruby_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.RUBY));
    public static final RegistryObject<Block> SAPPHIRE_CRYSTALS = BLOCKS.register("sapphire_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.SAPPHIRE));
    public static final RegistryObject<Block> AQUAMARINE_CRYSTALS = BLOCKS.register("aquamarine_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.AQUAMARINE));
    public static final RegistryObject<Block> JADE_CRYSTALS = BLOCKS.register("jade_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.JADE));
    public static final RegistryObject<Block> OPAL_CRYSTALS = BLOCKS.register("opal_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.OPAL));
    public static final RegistryObject<Block> YELLOW_DIAMOND_CRYSTALS = BLOCKS.register("yellow_diamond_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.YELLOW_DIAMOND));
    public static final RegistryObject<Block> AMBER_CRYSTALS = BLOCKS.register("amber_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.AMBER));
    public static final RegistryObject<Block> TOPAZ_CRYSTALS = BLOCKS.register("topaz_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.TOPAZ));
    public static final RegistryObject<Block> BERYLLIUM_CRYSTALS = BLOCKS.register("beryllium_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.BERYLLIUM));
    public static final RegistryObject<Block> BIXBIT_CRYSTALS = BLOCKS.register("bixbit_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.BIXBIT));
    public static final RegistryObject<Block> MALACHITE_CRYSTALS = BLOCKS.register("malachite_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.MALACHITE));
    public static final RegistryObject<Block> ONYX_CRYSTALS = BLOCKS.register("onyx_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.ONYX));
    public static final RegistryObject<Block> PERIDOT_CRYSTALS = BLOCKS.register("peridot_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.PERIDOT));
    public static final RegistryObject<Block> MOON_STONE_CRYSTALS = BLOCKS.register("moon_stone_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.MOON_STONE));
    public static final RegistryObject<Block> SUN_STONE_CRYSTALS = BLOCKS.register("sun_stone_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.SUN_STONE));
    public static final RegistryObject<Block> CITRINE_CRYSTALS = BLOCKS.register("citrine_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.CITRINE));
    public static final RegistryObject<Block> DOLOMITE_CRYSTALS = BLOCKS.register("dolomite_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.DOLOMITE));
    public static final RegistryObject<Block> TANZANITE_CRYSTALS = BLOCKS.register("tanzanite_crystals", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(5.0f).noOcclusion(), Tints.TANZANITE));
}
