package com.visnaa.gemstonepower.event.mod;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.gen.*;
import com.visnaa.gemstonepower.data.recipe.*;
import com.visnaa.gemstonepower.networking.ModPackets;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = GemstonePower.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents
{
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event)
    {
        ModPackets.register();
    }

    @SubscribeEvent
    public static void registerRecipeTypes(RegisterEvent event)
    {
        event.register(ForgeRegistries.Keys.RECIPE_TYPES, helper -> {
            helper.register(GemstoneGeneratorRecipe.TYPE_ID, ModRecipes.GEMSTONE_GENERATOR_RECIPE);
            helper.register(CrystalGrowerRecipe.TYPE_ID, ModRecipes.CRYSTAL_GROWER_RECIPE);
            helper.register(CrystalChargerRecipe.TYPE_ID, ModRecipes.CRYSTAL_CHARGER_RECIPE);
            helper.register(MetalFormerRecipe.TYPE_ID, ModRecipes.METAL_FORMER_RECIPE);
            helper.register(PulverizerRecipe.TYPE_ID, ModRecipes.PULVERIZER_RECIPE);
            helper.register(AlloySmelterRecipe.TYPE_ID, ModRecipes.ALLOY_SMELTER_RECIPE);
            helper.register(ExtractorRecipe.TYPE_ID, ModRecipes.EXTRACTOR_RECIPE);
            helper.register(OreWasherRecipe.TYPE_ID, ModRecipes.ORE_WASHER_RECIPE);
            helper.register(SawmillRecipe.TYPE_ID, ModRecipes.SAWMILL_RECIPE);
            helper.register(PolarizerRecipe.TYPE_ID, ModRecipes.POLARIZER_RECIPE);
        });
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> completablefuture = CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor());

        // Client
        generator.addProvider(true, new ItemModelGenerator(generator.getPackOutput(), existingFileHelper));
        generator.addProvider(true, new BlockModelGenerator(generator.getPackOutput(), existingFileHelper));
        generator.addProvider(true, new EN_US_LanguageGenerator(generator.getPackOutput()));

        // Server
        generator.addProvider(true, new RecipeGenerator(generator.getPackOutput()));
        generator.addProvider(true, new LootTableProvider(generator.getPackOutput(), Set.of(), List.of(new LootTableProvider.SubProviderEntry(BlockLootGenerator::new, LootContextParamSets.BLOCK))));
        generator.addProvider(true, new ItemTagGenerator(generator.getPackOutput(), Registries.ITEM, completablefuture, existingFileHelper));
        generator.addProvider(true, new BlockTagGenerator(generator.getPackOutput(), Registries.BLOCK, completablefuture, existingFileHelper));
        generator.addProvider(true, new DamageTypeTagGenerator(generator.getPackOutput(), completablefuture, existingFileHelper));
        generator.addProvider(true, new EntityTagGenerator(generator.getPackOutput(), completablefuture, existingFileHelper));
        generator.addProvider(true, new BiomeTagGenerator(generator.getPackOutput(), completablefuture, existingFileHelper));
        generator.addProvider(true, new TrimMaterialGenerator(generator.getPackOutput()));
        generator.addProvider(true, new WorldGenGenerator(generator.getPackOutput(), completablefuture));
        generator.addProvider(true, AdvancementGenerator.create(generator.getPackOutput(), completablefuture));
    }
}
