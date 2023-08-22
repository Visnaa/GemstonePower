package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.SolarPanelBE;
import com.visnaa.gemstonepower.block.entity.TankBE;
import com.visnaa.gemstonepower.block.entity.WaterMillBE;
import com.visnaa.gemstonepower.block.entity.WindTurbineBE;
import com.visnaa.gemstonepower.block.entity.machine.*;
import com.visnaa.gemstonepower.block.entity.pipe.cable.AluminumCableBE;
import com.visnaa.gemstonepower.block.entity.pipe.cable.CopperCableBE;
import com.visnaa.gemstonepower.block.entity.pipe.cable.ElectrumCableBE;
import com.visnaa.gemstonepower.block.entity.pipe.cable.TinCableBE;
import com.visnaa.gemstonepower.block.entity.pipe.fluid.CopperFluidPipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.fluid.GoldFluidPipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.fluid.InvarFluidPipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.fluid.SteelFluidPipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.item.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GemstonePower.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemstoneGeneratorBE>> GEMSTONE_GENERATOR = BLOCK_ENTITIES.register("gemstone_generator", () -> BlockEntityType.Builder.of(GemstoneGeneratorBE::new, ModBlocks.GEMSTONE_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<GemstoneCellBE>> GEMSTONE_CELL = BLOCK_ENTITIES.register("gemstone_cell", () -> BlockEntityType.Builder.of(GemstoneCellBE::new, ModBlocks.GEMSTONE_CELL.get()).build(null));

    public static final RegistryObject<BlockEntityType<CrystalGrowerBE>> CRYSTAL_GROWER = BLOCK_ENTITIES.register("crystal_grower", () -> BlockEntityType.Builder.of(CrystalGrowerBE::new, ModBlocks.CRYSTAL_GROWER.get()).build(null));
    public static final RegistryObject<BlockEntityType<CrystalChargerBE>> CRYSTAL_CHARGER = BLOCK_ENTITIES.register("crystal_charger", () -> BlockEntityType.Builder.of(CrystalChargerBE::new, ModBlocks.CRYSTAL_CHARGER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ElectricFurnaceBE>> ELECTRIC_FURNACE = BLOCK_ENTITIES.register("electric_furnace", () -> BlockEntityType.Builder.of(ElectricFurnaceBE::new, ModBlocks.ELECTRIC_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MetalFormerBE>> METAL_FORMER = BLOCK_ENTITIES.register("metal_former", () -> BlockEntityType.Builder.of(MetalFormerBE::new, ModBlocks.METAL_FORMER.get()).build(null));
    public static final RegistryObject<BlockEntityType<PulverizerBE>> PULVERIZER = BLOCK_ENTITIES.register("pulverizer", () -> BlockEntityType.Builder.of(PulverizerBE::new, ModBlocks.PULVERIZER.get()).build(null));
    public static final RegistryObject<BlockEntityType<AlloySmelterBE>> ALLOY_SMELTER = BLOCK_ENTITIES.register("alloy_smelter", () -> BlockEntityType.Builder.of(AlloySmelterBE::new, ModBlocks.ALLOY_SMELTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<ExtractorBE>> EXTRACTOR = BLOCK_ENTITIES.register("extractor", () -> BlockEntityType.Builder.of(ExtractorBE::new, ModBlocks.EXTRACTOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<OreWasherBE>> ORE_WASHER = BLOCK_ENTITIES.register("ore_washer", () -> BlockEntityType.Builder.of(OreWasherBE::new, ModBlocks.ORE_WASHER.get()).build(null));
    public static final RegistryObject<BlockEntityType<CobblestoneGeneratorBE>> COBBLESTONE_GENERATOR = BLOCK_ENTITIES.register("cobblestone_generator", () -> BlockEntityType.Builder.of(CobblestoneGeneratorBE::new, ModBlocks.COBBLESTONE_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<SawmillBE>> SAWMILL = BLOCK_ENTITIES.register("sawmill", () -> BlockEntityType.Builder.of(SawmillBE::new, ModBlocks.SAWMILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<PolarizerBE>> POLARIZER = BLOCK_ENTITIES.register("polarizer", () -> BlockEntityType.Builder.of(PolarizerBE::new, ModBlocks.POLARIZER.get()).build(null));

    public static final RegistryObject<BlockEntityType<SolarPanelBE>> SOLAR_PANEL = BLOCK_ENTITIES.register("solar_panel", () -> BlockEntityType.Builder.of(SolarPanelBE::new, ModBlocks.SOLAR_PANEL.get()).build(null));
    public static final RegistryObject<BlockEntityType<WaterMillBE>> WATER_MILL = BLOCK_ENTITIES.register("water_mill", () -> BlockEntityType.Builder.of(WaterMillBE::new, ModBlocks.WATER_MILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<WindTurbineBE>> WIND_TURBINE = BLOCK_ENTITIES.register("wind_turbine", () -> BlockEntityType.Builder.of(WindTurbineBE::new, ModBlocks.WIND_TURBINE.get()).build(null));

    public static final RegistryObject<BlockEntityType<TankBE>> TANK = BLOCK_ENTITIES.register("tank", () -> BlockEntityType.Builder.of(TankBE::new, ModBlocks.TANK.get()).build(null));

    public static final RegistryObject<BlockEntityType<CopperCableBE>> COPPER_CABLE = BLOCK_ENTITIES.register("copper_cable", () -> BlockEntityType.Builder.of(CopperCableBE::new, ModBlocks.COPPER_WIRE.get(), ModBlocks.COPPER_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<AluminumCableBE>> ALUMINUM_CABLE = BLOCK_ENTITIES.register("aluminum_cable", () -> BlockEntityType.Builder.of(AluminumCableBE::new, ModBlocks.ALUMINUM_WIRE.get(), ModBlocks.ALUMINUM_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<TinCableBE>> TIN_CABLE = BLOCK_ENTITIES.register("tin_cable", () -> BlockEntityType.Builder.of(TinCableBE::new, ModBlocks.TIN_WIRE.get(), ModBlocks.TIN_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<ElectrumCableBE>> ELECTRUM_CABLE = BLOCK_ENTITIES.register("electrum_cable", () -> BlockEntityType.Builder.of(ElectrumCableBE::new, ModBlocks.ELECTRUM_WIRE.get(), ModBlocks.ELECTRUM_CABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<IronItemPipeBE>> IRON_ITEM_PIPE = BLOCK_ENTITIES.register("iron_item_pipe", () -> BlockEntityType.Builder.of(IronItemPipeBE::new, ModBlocks.IRON_ITEM_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<GoldItemPipeBE>> GOLD_ITEM_PIPE = BLOCK_ENTITIES.register("gold_item_pipe", () -> BlockEntityType.Builder.of(GoldItemPipeBE::new, ModBlocks.GOLD_ITEM_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CopperItemPipeBE>> COPPER_ITEM_PIPE = BLOCK_ENTITIES.register("copper_item_pipe", () -> BlockEntityType.Builder.of(CopperItemPipeBE::new, ModBlocks.COPPER_ITEM_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<PlatinumItemPipeBE>> PLATINUM_ITEM_PIPE = BLOCK_ENTITIES.register("platinum_item_pipe", () -> BlockEntityType.Builder.of(PlatinumItemPipeBE::new, ModBlocks.PLATINUM_ITEM_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<LeadItemPipeBE>> LEAD_ITEM_PIPE = BLOCK_ENTITIES.register("lead_item_pipe", () -> BlockEntityType.Builder.of(LeadItemPipeBE::new, ModBlocks.LEAD_ITEM_PIPE.get()).build(null));

    public static final RegistryObject<BlockEntityType<GoldFluidPipeBE>> GOLD_FLUID_PIPE = BLOCK_ENTITIES.register("gold_fluid_pipe", () -> BlockEntityType.Builder.of(GoldFluidPipeBE::new, ModBlocks.GOLD_FLUID_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CopperFluidPipeBE>> COPPER_FLUID_PIPE = BLOCK_ENTITIES.register("copper_fluid_pipe", () -> BlockEntityType.Builder.of(CopperFluidPipeBE::new, ModBlocks.COPPER_FLUID_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<InvarFluidPipeBE>> INVAR_FLUID_PIPE = BLOCK_ENTITIES.register("invar_fluid_pipe", () -> BlockEntityType.Builder.of(InvarFluidPipeBE::new, ModBlocks.INVAR_FLUID_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SteelFluidPipeBE>> STEEL_FLUID_PIPE = BLOCK_ENTITIES.register("steel_fluid_pipe", () -> BlockEntityType.Builder.of(SteelFluidPipeBE::new, ModBlocks.STEEL_FLUID_PIPE.get()).build(null));
}
