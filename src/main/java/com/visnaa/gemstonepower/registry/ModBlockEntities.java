package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GemstonePower.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemstoneGeneratorBlockEntity>> GEMSTONE_GENERATOR = BLOCK_ENTITIES.register("gemstone_generator", () -> BlockEntityType.Builder.of(GemstoneGeneratorBlockEntity::new, ModBlocks.GEMSTONE_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<GemstoneCellBlockEntity>> GEMSTONE_CELL = BLOCK_ENTITIES.register("gemstone_cell", () -> BlockEntityType.Builder.of(GemstoneCellBlockEntity::new, ModBlocks.GEMSTONE_CELL.get()).build(null));

    public static final RegistryObject<BlockEntityType<CrystalGrowerBlockEntity>> CRYSTAL_GROWER = BLOCK_ENTITIES.register("crystal_grower", () -> BlockEntityType.Builder.of(CrystalGrowerBlockEntity::new, ModBlocks.CRYSTAL_GROWER.get()).build(null));
    public static final RegistryObject<BlockEntityType<CrystalChargerBlockEntity>> CRYSTAL_CHARGER = BLOCK_ENTITIES.register("crystal_charger", () -> BlockEntityType.Builder.of(CrystalChargerBlockEntity::new, ModBlocks.CRYSTAL_CHARGER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ElectricFurnaceBlockEntity>> ELECTRIC_FURNACE = BLOCK_ENTITIES.register("electric_furnace", () -> BlockEntityType.Builder.of(ElectricFurnaceBlockEntity::new, ModBlocks.ELECTRIC_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MetalFormerBlockEntity>> METAL_FORMER = BLOCK_ENTITIES.register("metal_former", () -> BlockEntityType.Builder.of(MetalFormerBlockEntity::new, ModBlocks.METAL_FORMER.get()).build(null));
    public static final RegistryObject<BlockEntityType<PulverizerBlockEntity>> PULVERIZER = BLOCK_ENTITIES.register("pulverizer", () -> BlockEntityType.Builder.of(PulverizerBlockEntity::new, ModBlocks.PULVERIZER.get()).build(null));
    public static final RegistryObject<BlockEntityType<AlloySmelterBlockEntity>> ALLOY_SMELTER = BLOCK_ENTITIES.register("alloy_smelter", () -> BlockEntityType.Builder.of(AlloySmelterBlockEntity::new, ModBlocks.ALLOY_SMELTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<ExtractorBlockEntity>> EXTRACTOR = BLOCK_ENTITIES.register("extractor", () -> BlockEntityType.Builder.of(ExtractorBlockEntity::new, ModBlocks.EXTRACTOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<OreWasherBlockEntity>> ORE_WASHER = BLOCK_ENTITIES.register("ore_washer", () -> BlockEntityType.Builder.of(OreWasherBlockEntity::new, ModBlocks.ORE_WASHER.get()).build(null));
    public static final RegistryObject<BlockEntityType<CobblestoneGeneratorBlockEntity>> COBBLESTONE_GENERATOR = BLOCK_ENTITIES.register("cobblestone_generator", () -> BlockEntityType.Builder.of(CobblestoneGeneratorBlockEntity::new, ModBlocks.COBBLESTONE_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<SawmillBlockEntity>> SAWMILL = BLOCK_ENTITIES.register("sawmill", () -> BlockEntityType.Builder.of(SawmillBlockEntity::new, ModBlocks.SAWMILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<PolarizerBlockEntity>> POLARIZER = BLOCK_ENTITIES.register("polarizer", () -> BlockEntityType.Builder.of(PolarizerBlockEntity::new, ModBlocks.POLARIZER.get()).build(null));

    public static final RegistryObject<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL = BLOCK_ENTITIES.register("solar_panel", () -> BlockEntityType.Builder.of(SolarPanelBlockEntity::new, ModBlocks.SOLAR_PANEL.get()).build(null));
    public static final RegistryObject<BlockEntityType<WaterMillBlockEntity>> WATER_MILL = BLOCK_ENTITIES.register("water_mill", () -> BlockEntityType.Builder.of(WaterMillBlockEntity::new, ModBlocks.WATER_MILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<WindTurbineBlockEntity>> WIND_TURBINE = BLOCK_ENTITIES.register("wind_turbine", () -> BlockEntityType.Builder.of(WindTurbineBlockEntity::new, ModBlocks.WIND_TURBINE.get()).build(null));

    public static final RegistryObject<BlockEntityType<CopperCableBlockEntity>> COPPER_CABLE = BLOCK_ENTITIES.register("copper_cable", () -> BlockEntityType.Builder.of(CopperCableBlockEntity::new, ModBlocks.COPPER_WIRE.get(), ModBlocks.COPPER_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<AluminumCableBlockEntity>> ALUMINUM_CABLE = BLOCK_ENTITIES.register("aluminum_cable", () -> BlockEntityType.Builder.of(AluminumCableBlockEntity::new, ModBlocks.ALUMINUM_WIRE.get(), ModBlocks.ALUMINUM_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<TinCableBlockEntity>> TIN_CABLE = BLOCK_ENTITIES.register("tin_cable", () -> BlockEntityType.Builder.of(TinCableBlockEntity::new, ModBlocks.TIN_WIRE.get(), ModBlocks.TIN_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<ElectrumCableBlockEntity>> ELECTRUM_CABLE = BLOCK_ENTITIES.register("electrum_cable", () -> BlockEntityType.Builder.of(ElectrumCableBlockEntity::new, ModBlocks.ELECTRUM_WIRE.get(), ModBlocks.ELECTRUM_CABLE.get()).build(null));
}
