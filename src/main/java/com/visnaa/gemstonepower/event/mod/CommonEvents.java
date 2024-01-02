package com.visnaa.gemstonepower.event.mod;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.EnergyStorageBE;
import com.visnaa.gemstonepower.block.entity.FluidStorageBE;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.data.gen.*;
import com.visnaa.gemstonepower.data.recipe.*;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModItems;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.item.PortableTank;
import com.visnaa.gemstonepower.item.TankItem;
import com.visnaa.gemstonepower.network.packet.*;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = GemstonePower.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents
{
    @SubscribeEvent
    public static void registerRecipeTypes(RegisterEvent event)
    {
        event.register(BuiltInRegistries.RECIPE_TYPE.key(), helper -> {
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
        generator.addProvider(true, new RecipeGenerator(generator.getPackOutput(), completablefuture));
        generator.addProvider(true, new LootTableProvider(generator.getPackOutput(), Set.of(), List.of(new LootTableProvider.SubProviderEntry(BlockLootGenerator::new, LootContextParamSets.BLOCK))));
        generator.addProvider(true, new ItemTagGenerator(generator.getPackOutput(), Registries.ITEM, completablefuture, existingFileHelper));
        generator.addProvider(true, new BlockTagGenerator(generator.getPackOutput(), Registries.BLOCK, completablefuture, existingFileHelper));
        generator.addProvider(true, new DamageTypeTagGenerator(generator.getPackOutput(), completablefuture, existingFileHelper));
        generator.addProvider(true, new EntityTagGenerator(generator.getPackOutput(), completablefuture, existingFileHelper));
        generator.addProvider(true, new BiomeTagGenerator(generator.getPackOutput(), completablefuture, existingFileHelper));
        generator.addProvider(true, new TrimMaterialGenerator(generator.getPackOutput()));
        generator.addProvider(true, AdvancementGenerator.create(generator.getPackOutput(), completablefuture, existingFileHelper));


        CompletableFuture<RegistrySetBuilder.PatchedRegistries> gemstonePowerCompletableFuture = GemstonePowerRegistryGenerator.createLookup(completablefuture);
        generator.addProvider(true, (DataProvider.Factory<DatapackBuiltinEntriesProvider>) (output) -> new DatapackBuiltinEntriesProvider(output, gemstonePowerCompletableFuture, Set.of(GemstonePower.MOD_ID)));
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event)
    {
        event.registerItem(Capabilities.FluidHandler.ITEM, TankItem::getFluidHandler, ModItems.TANK.get());
        event.registerItem(Capabilities.FluidHandler.ITEM, PortableTank::getFluidHandler, ModItems.PORTABLE_TANK.get());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.GEMSTONE_GENERATOR.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.GEMSTONE_GENERATOR.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.GEMSTONE_CELL.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.GEMSTONE_CELL.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.CRYSTAL_GROWER.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.CRYSTAL_GROWER.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.CRYSTAL_CHARGER.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.CRYSTAL_CHARGER.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.ELECTRIC_FURNACE.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.ELECTRIC_FURNACE.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.METAL_FORMER.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.METAL_FORMER.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.PULVERIZER.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.PULVERIZER.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.ALLOY_SMELTER.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.ALLOY_SMELTER.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.EXTRACTOR.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.EXTRACTOR.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.ORE_WASHER.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.ORE_WASHER.get(), EnergyStorageBE::getEnergyStorage);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.ORE_WASHER.get(), FluidStorageBE::getFluidHandler);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.COBBLESTONE_GENERATOR.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.SAWMILL.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.SAWMILL.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.POLARIZER.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.POLARIZER.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.GEMSTONE_MANIPULATOR.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.GEMSTONE_MANIPULATOR.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.FISSION_REACTOR.get(), MachineBE::getItemHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.FISSION_REACTOR.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.SOLAR_PANEL.get(), EnergyStorageBE::getEnergyStorage);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.WATER_MILL.get(), EnergyStorageBE::getEnergyStorage);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.WIND_TURBINE.get(), EnergyStorageBE::getEnergyStorage);

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.TANK.get(), FluidStorageBE::getFluidHandler);
    }

    @SubscribeEvent
    public static void registerPackets(RegisterPayloadHandlerEvent event)
    {
        IPayloadRegistrar registrar = event.registrar(GemstonePower.MOD_ID);

        registrar.play(EnergySyncS2C.ID, EnergySyncS2C::new, handler -> handler.client(EnergySyncS2C.Handler.getInstance()::handle));
        registrar.play(FissionReactorSyncS2C.ID, FissionReactorSyncS2C::new, handler -> handler.client(FissionReactorSyncS2C.Handler.getInstance()::handle));
        registrar.play(FluidSyncS2C.ID, FluidSyncS2C::new, handler -> handler.client(FluidSyncS2C.Handler.getInstance()::handle));
        registrar.play(MachineConfigSyncS2C.ID, MachineConfigSyncS2C::new, handler -> handler.client(MachineConfigSyncS2C.Handler.getInstance()::handle));
        registrar.play(RecipeProgressSyncS2C.ID, RecipeProgressSyncS2C::new, handler -> handler.client(RecipeProgressSyncS2C.Handler.getInstance()::handle));

        registrar.play(FissionReactorActivationC2S.ID, FissionReactorActivationC2S::new, handler -> handler.server(FissionReactorActivationC2S.Handler.getInstance()::handle));
        registrar.play(MachineConfigSyncC2S.ID, MachineConfigSyncC2S::new, handler -> handler.server(MachineConfigSyncC2S.Handler.getInstance()::handle));
        registrar.play(MachineModeSyncC2S.ID, MachineModeSyncC2S::new, handler -> handler.server(MachineModeSyncC2S.Handler.getInstance()::handle));
    }
}
