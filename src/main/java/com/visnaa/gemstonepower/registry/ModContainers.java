package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.menu.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModContainers
{
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, GemstonePower.MOD_ID);

    public static final RegistryObject<MenuType<GemstoneGeneratorMenu>> GEMSTONE_GENERATOR = CONTAINERS.register("gemstone_generator", () -> IForgeMenuType.create((windowId, inv, data) -> new GemstoneGeneratorMenu(windowId, inv)));
    public static final RegistryObject<MenuType<GemstoneCellMenu>> GEMSTONE_CELL = CONTAINERS.register("gemstone_cell", () -> IForgeMenuType.create((windowId, inv, data) -> new GemstoneCellMenu(windowId, inv)));
    public static final RegistryObject<MenuType<CrystalGrowerMenu>> CRYSTAL_GROWER = CONTAINERS.register("crystal_grower", () -> IForgeMenuType.create((windowId, inv, data) -> new CrystalGrowerMenu(windowId, inv)));
    public static final RegistryObject<MenuType<CrystalChargerMenu>> CRYSTAL_CHARGER = CONTAINERS.register("crystal_charger", () -> IForgeMenuType.create((windowId, inv, data) -> new CrystalChargerMenu(windowId, inv)));
    public static final RegistryObject<MenuType<ElectricFurnaceMenu>> ELECTRIC_FURNACE = CONTAINERS.register("electric_furnace", () -> IForgeMenuType.create((id, inv, data) -> new ElectricFurnaceMenu(id, inv)));
    public static final RegistryObject<MenuType<MetalFormerMenu>> METAL_FORMER = CONTAINERS.register("metal_former", () -> IForgeMenuType.create((id, inv, data) -> new MetalFormerMenu(id, inv)));
    public static final RegistryObject<MenuType<PulverizerMenu>> PULVERIZER = CONTAINERS.register("pulverizer", () -> IForgeMenuType.create((id, inv, data) -> new PulverizerMenu(id, inv)));
    public static final RegistryObject<MenuType<AlloySmelterMenu>> ALLOY_SMELTER = CONTAINERS.register("alloy_smelter", () -> IForgeMenuType.create((id, inv, data) -> new AlloySmelterMenu(id, inv)));
    public static final RegistryObject<MenuType<ExtractorMenu>> EXTRACTOR = CONTAINERS.register("extractor", () -> IForgeMenuType.create((id, inv, data) -> new ExtractorMenu(id, inv)));
    public static final RegistryObject<MenuType<OreWasherMenu>> ORE_WASHER = CONTAINERS.register("ore_washer", () -> IForgeMenuType.create((id, inv, data) -> new OreWasherMenu(id, inv)));
    public static final RegistryObject<MenuType<SawmillMenu>> SAWMILL = CONTAINERS.register("sawmill", () -> IForgeMenuType.create((id, inv, data) -> new SawmillMenu(id, inv)));
    public static final RegistryObject<MenuType<PolarizerMenu>> POLARIZER = CONTAINERS.register("polarizer", () -> IForgeMenuType.create((id, inv, data) -> new PolarizerMenu(id, inv)));
}
