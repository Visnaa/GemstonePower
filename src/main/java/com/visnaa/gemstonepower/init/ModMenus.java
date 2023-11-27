package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.menu.machine.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModMenus
{
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(BuiltInRegistries.MENU, GemstonePower.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<GemstoneGeneratorMenu>> GEMSTONE_GENERATOR = CONTAINERS.register("gemstone_generator", () -> IMenuTypeExtension.create(GemstoneGeneratorMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<GemstoneCellMenu>> GEMSTONE_CELL = CONTAINERS.register("gemstone_cell", () -> IMenuTypeExtension.create(GemstoneCellMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<CrystalGrowerMenu>> CRYSTAL_GROWER = CONTAINERS.register("crystal_grower", () -> IMenuTypeExtension.create(CrystalGrowerMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<CrystalChargerMenu>> CRYSTAL_CHARGER = CONTAINERS.register("crystal_charger", () -> IMenuTypeExtension.create(CrystalChargerMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<ElectricFurnaceMenu>> ELECTRIC_FURNACE = CONTAINERS.register("electric_furnace", () -> IMenuTypeExtension.create(ElectricFurnaceMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<MetalFormerMenu>> METAL_FORMER = CONTAINERS.register("metal_former", () -> IMenuTypeExtension.create(MetalFormerMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<PulverizerMenu>> PULVERIZER = CONTAINERS.register("pulverizer", () -> IMenuTypeExtension.create(PulverizerMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<AlloySmelterMenu>> ALLOY_SMELTER = CONTAINERS.register("alloy_smelter", () -> IMenuTypeExtension.create(AlloySmelterMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<ExtractorMenu>> EXTRACTOR = CONTAINERS.register("extractor", () -> IMenuTypeExtension.create(ExtractorMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<OreWasherMenu>> ORE_WASHER = CONTAINERS.register("ore_washer", () -> IMenuTypeExtension.create(OreWasherMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<SawmillMenu>> SAWMILL = CONTAINERS.register("sawmill", () -> IMenuTypeExtension.create(SawmillMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<PolarizerMenu>> POLARIZER = CONTAINERS.register("polarizer", () -> IMenuTypeExtension.create(PolarizerMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<GemstoneManipulatorMenu>> GEMSTONE_MANIPULATOR = CONTAINERS.register("gemstone_manipulator", () -> IMenuTypeExtension.create(GemstoneManipulatorMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<FissionReactorMenu>> FISSION_REACTOR = CONTAINERS.register("fission_reactor", () -> IMenuTypeExtension.create(FissionReactorMenu::new));
}
