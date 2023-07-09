package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.menu.machine.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModContainers
{
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, GemstonePower.MOD_ID);

    public static final RegistryObject<MenuType<GemstoneGeneratorMenu>> GEMSTONE_GENERATOR = CONTAINERS.register("gemstone_generator", () -> IForgeMenuType.create(GemstoneGeneratorMenu::new));
    public static final RegistryObject<MenuType<GemstoneCellMenu>> GEMSTONE_CELL = CONTAINERS.register("gemstone_cell", () -> IForgeMenuType.create(GemstoneCellMenu::new));
    public static final RegistryObject<MenuType<CrystalGrowerMenu>> CRYSTAL_GROWER = CONTAINERS.register("crystal_grower", () -> IForgeMenuType.create(CrystalGrowerMenu::new));
    public static final RegistryObject<MenuType<CrystalChargerMenu>> CRYSTAL_CHARGER = CONTAINERS.register("crystal_charger", () -> IForgeMenuType.create(CrystalChargerMenu::new));
    public static final RegistryObject<MenuType<ElectricFurnaceMenu>> ELECTRIC_FURNACE = CONTAINERS.register("electric_furnace", () -> IForgeMenuType.create(ElectricFurnaceMenu::new));
    public static final RegistryObject<MenuType<MetalFormerMenu>> METAL_FORMER = CONTAINERS.register("metal_former", () -> IForgeMenuType.create(MetalFormerMenu::new));
    public static final RegistryObject<MenuType<PulverizerMenu>> PULVERIZER = CONTAINERS.register("pulverizer", () -> IForgeMenuType.create(PulverizerMenu::new));
    public static final RegistryObject<MenuType<AlloySmelterMenu>> ALLOY_SMELTER = CONTAINERS.register("alloy_smelter", () -> IForgeMenuType.create(AlloySmelterMenu::new));
    public static final RegistryObject<MenuType<ExtractorMenu>> EXTRACTOR = CONTAINERS.register("extractor", () -> IForgeMenuType.create(ExtractorMenu::new));
    public static final RegistryObject<MenuType<OreWasherMenu>> ORE_WASHER = CONTAINERS.register("ore_washer", () -> IForgeMenuType.create(OreWasherMenu::new));
    public static final RegistryObject<MenuType<SawmillMenu>> SAWMILL = CONTAINERS.register("sawmill", () -> IForgeMenuType.create(SawmillMenu::new));
    public static final RegistryObject<MenuType<PolarizerMenu>> POLARIZER = CONTAINERS.register("polarizer", () -> IForgeMenuType.create(PolarizerMenu::new));
}
