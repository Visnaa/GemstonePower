package com.visnaa.gemstonepower.integration.jei;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.machine.*;
import com.visnaa.gemstonepower.data.recipe.*;
import com.visnaa.gemstonepower.init.ModBlocks;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.integration.jei.category.*;
import com.visnaa.gemstonepower.menu.machine.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class GemstonePowerJEIPlugin implements IModPlugin
{
    public static RecipeType<CrystalGrowerRecipe> CRYSTAL_GROWER_CATEGORY = new RecipeType<>(CrystalGrowerRecipeCategory.UID, CrystalGrowerRecipe.class);
    public static RecipeType<CrystalChargerRecipe> CRYSTAL_CHARGER_CATEGORY = new RecipeType<>(CrystalChargerRecipeCategory.UID, CrystalChargerRecipe.class);

    public static RecipeType<MetalFormerRecipe> METAL_FORMER_CATEGORY = new RecipeType<>(MetalFormerRecipeCategory.UID, MetalFormerRecipe.class);
    public static RecipeType<PulverizerRecipe> PULVERIZER_CATEGORY = new RecipeType<>(PulverizerRecipeCategory.UID, PulverizerRecipe.class);
    public static RecipeType<AlloySmelterRecipe> ALLOY_SMELTER_CATEGORY = new RecipeType<>(AlloySmelterRecipeCategory.UID, AlloySmelterRecipe.class);
    public static RecipeType<ExtractorRecipe> EXTRACTOR_CATEGORY = new RecipeType<>(ExtractorRecipeCategory.UID, ExtractorRecipe.class);
    public static RecipeType<OreWasherRecipe> ORE_WASHER_CATEGORY = new RecipeType<>(OreWasherRecipeCategory.UID, OreWasherRecipe.class);
    public static RecipeType<SawmillRecipe> SAWMILL_CATEGORY = new RecipeType<>(SawmillRecipeCategory.UID, SawmillRecipe.class);
    public static RecipeType<PolarizerRecipe> POLARIZER_CATEGORY = new RecipeType<>(PolarizerRecipeCategory.UID, PolarizerRecipe.class);
    public static RecipeType<GemstoneManipulatorRecipe> GEMSTONE_MANIPULATOR_CATEGORY = new RecipeType<>(GemstoneManipulatorRecipeCategory.UID, GemstoneManipulatorRecipe.class);
    public static RecipeType<FissionReactorRecipe> FISSION_REACTOR_CATEGORY = new RecipeType<>(FissionReactorRecipeCategory.UID, FissionReactorRecipe.class);

    @Override
    public ResourceLocation getPluginUid()
    {
        return new ResourceLocation(GemstonePower.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        registration.addRecipeCategories(new CrystalGrowerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CrystalChargerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new MetalFormerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new PulverizerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new AlloySmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ExtractorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new OreWasherRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new SawmillRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new PolarizerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new GemstoneManipulatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new FissionReactorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager manager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<CrystalGrowerRecipe> crystalGrowerRecipes = manager.getAllRecipesFor(ModRecipes.CRYSTAL_GROWER_RECIPE);
        List<CrystalChargerRecipe> crystalChargerRecipes = manager.getAllRecipesFor(ModRecipes.CRYSTAL_CHARGER_RECIPE);
        List<MetalFormerRecipe> metalFormerRecipes = manager.getAllRecipesFor(ModRecipes.METAL_FORMER_RECIPE);
        List<PulverizerRecipe> pulverizerRecipes = manager.getAllRecipesFor(ModRecipes.PULVERIZER_RECIPE);
        List<AlloySmelterRecipe> alloySmelterRecipes = manager.getAllRecipesFor(ModRecipes.ALLOY_SMELTER_RECIPE);
        List<ExtractorRecipe> extractorRecipes = manager.getAllRecipesFor(ModRecipes.EXTRACTOR_RECIPE);
        List<OreWasherRecipe> oreWasherRecipes = manager.getAllRecipesFor(ModRecipes.ORE_WASHER_RECIPE);
        List<SawmillRecipe> sawmillRecipes = manager.getAllRecipesFor(ModRecipes.SAWMILL_RECIPE);
        List<PolarizerRecipe> polarizerRecipes = manager.getAllRecipesFor(ModRecipes.POLARIZER_RECIPE);
        List<GemstoneManipulatorRecipe> gemstoneManipulatorRecipes = manager.getAllRecipesFor(ModRecipes.GEMSTONE_MANIPULATOR_RECIPE);
        List<FissionReactorRecipe> fissionReactorRecipes = manager.getAllRecipesFor(ModRecipes.FISSION_REACTOR_RECIPE);

        registration.addRecipes(CRYSTAL_GROWER_CATEGORY, crystalGrowerRecipes);
        registration.addRecipes(CRYSTAL_CHARGER_CATEGORY, crystalChargerRecipes);
        registration.addRecipes(METAL_FORMER_CATEGORY, metalFormerRecipes);
        registration.addRecipes(PULVERIZER_CATEGORY, pulverizerRecipes);
        registration.addRecipes(ALLOY_SMELTER_CATEGORY, alloySmelterRecipes);
        registration.addRecipes(EXTRACTOR_CATEGORY, extractorRecipes);
        registration.addRecipes(ORE_WASHER_CATEGORY, oreWasherRecipes);
        registration.addRecipes(SAWMILL_CATEGORY, sawmillRecipes);
        registration.addRecipes(POLARIZER_CATEGORY, polarizerRecipes);
        registration.addRecipes(GEMSTONE_MANIPULATOR_CATEGORY, gemstoneManipulatorRecipes);
        registration.addRecipes(FISSION_REACTOR_CATEGORY, fissionReactorRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRYSTAL_GROWER.get()), CRYSTAL_GROWER_CATEGORY);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRYSTAL_CHARGER.get()), CRYSTAL_CHARGER_CATEGORY);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE.get()), RecipeTypes.SMELTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.METAL_FORMER.get()), METAL_FORMER_CATEGORY);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PULVERIZER.get()), PULVERIZER_CATEGORY);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALLOY_SMELTER.get()), ALLOY_SMELTER_CATEGORY);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRACTOR.get()), EXTRACTOR_CATEGORY);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ORE_WASHER.get()), ORE_WASHER_CATEGORY);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SAWMILL.get()), SAWMILL_CATEGORY);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.POLARIZER.get()), POLARIZER_CATEGORY);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.GEMSTONE_MANIPULATOR.get()), GEMSTONE_MANIPULATOR_CATEGORY);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FISSION_REACTOR.get()), FISSION_REACTOR_CATEGORY);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addRecipeClickArea(CrystalGrowerScreen.class, 74, 39, 30, 11, CRYSTAL_GROWER_CATEGORY);
        registration.addRecipeClickArea(CrystalChargerScreen.class, 81, 37, 15, 16, CRYSTAL_CHARGER_CATEGORY);

        registration.addRecipeClickArea(MetalFormerScreen.class, 76, 37, 26, 18, METAL_FORMER_CATEGORY);
        registration.addRecipeClickArea(PulverizerScreen.class, 80, 41, 18, 10, PULVERIZER_CATEGORY);
        registration.addRecipeClickArea(AlloySmelterScreen.class, 72, 34, 34, 24, ALLOY_SMELTER_CATEGORY);
        registration.addRecipeClickArea(ExtractorScreen.class, 80, 41, 18, 10, EXTRACTOR_CATEGORY);
        registration.addRecipeClickArea(OreWasherScreen.class, 75, 38, 19, 15, ORE_WASHER_CATEGORY);
        registration.addRecipeClickArea(SawmillScreen.class, 74, 39, 24, 13, SAWMILL_CATEGORY);
        registration.addRecipeClickArea(PolarizerScreen.class, 77, 39, 24, 12, POLARIZER_CATEGORY);
        registration.addRecipeClickArea(GemstoneManipulatorScreen.class, 76, 37, 24, 18, GEMSTONE_MANIPULATOR_CATEGORY);
        registration.addRecipeClickArea(FissionReactorScreen.class, 79, 36, 21, 20, FISSION_REACTOR_CATEGORY);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
    {
        registration.addRecipeTransferHandler(CrystalGrowerMenu.class, ModMenus.CRYSTAL_GROWER.get(), CRYSTAL_GROWER_CATEGORY, 0, 2, 2, 35);
        registration.addRecipeTransferHandler(CrystalChargerMenu.class, ModMenus.CRYSTAL_CHARGER.get(), CRYSTAL_CHARGER_CATEGORY, 0, 2, 2, 35);

        registration.addRecipeTransferHandler(MetalFormerMenu.class, ModMenus.METAL_FORMER.get(), METAL_FORMER_CATEGORY, 0, 2, 2, 35);
        registration.addRecipeTransferHandler(PulverizerMenu.class, ModMenus.PULVERIZER.get(), PULVERIZER_CATEGORY, 0, 1, 2, 35);
        registration.addRecipeTransferHandler(AlloySmelterMenu.class, ModMenus.ALLOY_SMELTER.get(), ALLOY_SMELTER_CATEGORY, 0, 3, 3, 36);
        registration.addRecipeTransferHandler(ExtractorMenu.class, ModMenus.EXTRACTOR.get(), EXTRACTOR_CATEGORY, 0, 1, 2, 35);
        registration.addRecipeTransferHandler(OreWasherMenu.class, ModMenus.ORE_WASHER.get(), ORE_WASHER_CATEGORY, 0, 1, 2, 35);
        registration.addRecipeTransferHandler(SawmillMenu.class, ModMenus.SAWMILL.get(), SAWMILL_CATEGORY, 0, 1, 2, 35);
        registration.addRecipeTransferHandler(PolarizerMenu.class, ModMenus.POLARIZER.get(), POLARIZER_CATEGORY, 0, 1, 2, 35);
        registration.addRecipeTransferHandler(GemstoneManipulatorMenu.class, ModMenus.GEMSTONE_MANIPULATOR.get(), GEMSTONE_MANIPULATOR_CATEGORY, 0, 3, 3, 36);
        registration.addRecipeTransferHandler(FissionReactorMenu.class, ModMenus.FISSION_REACTOR.get(), FISSION_REACTOR_CATEGORY, 0, 1, 2, 35);
    }
}
