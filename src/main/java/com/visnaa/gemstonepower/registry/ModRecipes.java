package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.recipe.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GemstonePower.MOD_ID);

    public static final RegistryObject<GemstoneGeneratorRecipe.Serializer> GEMSTONE_GENERATOR_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("gemstone_generator", GemstoneGeneratorRecipe.Serializer::new);
    public static final RegistryObject<CrystalGrowerRecipe.Serializer> CRYSTAL_GROWER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("crystal_grower", CrystalGrowerRecipe.Serializer::new);
    public static final RegistryObject<CrystalChargerRecipe.Serializer> CRYSTAL_CHARGER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("crystal_charger", CrystalChargerRecipe.Serializer::new);
    public static final RegistryObject<MetalFormerRecipe.Serializer> METAL_FORMER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("metal_former", MetalFormerRecipe.Serializer::new);
    public static final RegistryObject<PulverizerRecipe.Serializer> PULVERIZER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("pulverizer", PulverizerRecipe.Serializer::new);
    public static final RegistryObject<AlloySmelterRecipe.Serializer> ALLOY_SMELTER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("alloy_smelter", AlloySmelterRecipe.Serializer::new);
    public static final RegistryObject<ExtractorRecipe.Serializer> EXTRACTOR_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("extractor", ExtractorRecipe.Serializer::new);
    public static final RegistryObject<OreWasherRecipe.Serializer> ORE_WASHER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("ore_washer", OreWasherRecipe.Serializer::new);
    public static final RegistryObject<SawmillRecipe.Serializer> SAWMILL_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("sawmill", SawmillRecipe.Serializer::new);
    public static final RegistryObject<PolarizerRecipe.Serializer> POLARIZER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("polarizer", PolarizerRecipe.Serializer::new);

    public static RecipeType<GemstoneGeneratorRecipe> GEMSTONE_GENERATOR_RECIPE = new GemstoneGeneratorRecipe.Type();
    public static RecipeType<CrystalGrowerRecipe> CRYSTAL_GROWER_RECIPE = new CrystalGrowerRecipe.Type();
    public static RecipeType<CrystalChargerRecipe> CRYSTAL_CHARGER_RECIPE = new CrystalChargerRecipe.Type();
    public static RecipeType<MetalFormerRecipe> METAL_FORMER_RECIPE = new MetalFormerRecipe.Type();
    public static RecipeType<PulverizerRecipe> PULVERIZER_RECIPE = new PulverizerRecipe.Type();
    public static RecipeType<AlloySmelterRecipe> ALLOY_SMELTER_RECIPE = new AlloySmelterRecipe.Type();
    public static RecipeType<ExtractorRecipe> EXTRACTOR_RECIPE = new ExtractorRecipe.Type();
    public static RecipeType<OreWasherRecipe> ORE_WASHER_RECIPE = new OreWasherRecipe.Type();
    public static RecipeType<SawmillRecipe> SAWMILL_RECIPE = new SawmillRecipe.Type();
    public static RecipeType<PolarizerRecipe> POLARIZER_RECIPE = new PolarizerRecipe.Type();
}
