package com.visnaa.gemstonepower.event.mod;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.client.render.block.ConfigMachineBER;
import com.visnaa.gemstonepower.client.render.block.TankBER;
import com.visnaa.gemstonepower.client.render.entity.CrystalArrowRenderer;
import com.visnaa.gemstonepower.client.screen.ClientConfigScreen;
import com.visnaa.gemstonepower.client.screen.machine.*;
import com.visnaa.gemstonepower.init.*;
import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = GemstonePower.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents
{
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(ClientConfigScreen::new));

        event.enqueueWork(() -> {
            MenuScreens.register(ModMenus.GEMSTONE_GENERATOR.get(), GemstoneGeneratorScreen::new);
            MenuScreens.register(ModMenus.GEMSTONE_CELL.get(), GemstoneCellScreen::new);
            MenuScreens.register(ModMenus.CRYSTAL_GROWER.get(), CrystalGrowerScreen::new);
            MenuScreens.register(ModMenus.CRYSTAL_CHARGER.get(), CrystalChargerScreen::new);
            MenuScreens.register(ModMenus.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
            MenuScreens.register(ModMenus.METAL_FORMER.get(), MetalFormerScreen::new);
            MenuScreens.register(ModMenus.PULVERIZER.get(), PulverizerScreen::new);
            MenuScreens.register(ModMenus.ALLOY_SMELTER.get(), AlloySmelterScreen::new);
            MenuScreens.register(ModMenus.EXTRACTOR.get(), ExtractorScreen::new);
            MenuScreens.register(ModMenus.ORE_WASHER.get(), OreWasherScreen::new);
            MenuScreens.register(ModMenus.SAWMILL.get(), SawmillScreen::new);
            MenuScreens.register(ModMenus.POLARIZER.get(), PolarizerScreen::new);
            MenuScreens.register(ModMenus.GEMSTONE_MANIPULATOR.get(), GemstoneManipulatorScreen::new);
            MenuScreens.register(ModMenus.FISSION_REACTOR.get(), FissionReactorScreen::new);
        });
    }

    @SubscribeEvent
    public static void registerTabs(RegisterEvent event)
    {
        event.register(Registries.CREATIVE_MODE_TAB, new ResourceLocation(GemstonePower.MOD_ID, "main_tab"), ModTabs.MAIN_TAB);
        event.register(Registries.CREATIVE_MODE_TAB, new ResourceLocation(GemstonePower.MOD_ID, "resources_tab"), ModTabs.RESOURCES_TAB);
        event.register(Registries.CREATIVE_MODE_TAB, new ResourceLocation(GemstonePower.MOD_ID, "combat_tab"), ModTabs.COMBAT_TAB);
    }

    @SubscribeEvent
    public static void registerColouredItems(RegisterColorHandlersEvent.Item event)
    {
        Tints.TINTED_ITEMS.forEach(item -> event.register((stack, layer) -> layer == 0 ? item.getColor() : 0xFFFFFF, (ItemLike) item));
        event.register((stack, layer) -> event.getBlockColors().getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, layer), ModBlocks.RESIN_OAK_LEAVES.get());
        event.register((stack, layer) -> event.getBlockColors().getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, layer), ModBlocks.WATER_COOLING.get());
        event.register((stack, layer) -> layer != 0 && stack.hasTag() && stack.getTag().contains("FluidColor") ? stack.getTag().getInt("FluidColor") : 0xFFFFFF, ModItems.PORTABLE_TANK.get());
    }

    @SubscribeEvent
    public static void registerColouredBlocks(RegisterColorHandlersEvent.Block event)
    {
        Tints.TINTED_BLOCKS.forEach(block -> event.register((state, getter, pos, layer) -> block.getColor(), (Block) block));
        event.register((state, getter, pos, layer) -> getter != null && pos != null ? BiomeColors.getAverageFoliageColor(getter, pos) : FoliageColor.getDefaultColor(), ModBlocks.RESIN_OAK_LEAVES.get());
        event.register((state, getter, pos, layer) -> getter != null && pos != null ? BiomeColors.getAverageWaterColor(getter, pos) : 0x3F76E4, ModBlocks.WATER_COOLING.get());

        Tier.BLOCKS.forEach(block -> event.register((state, getter, pos, layer) ->
        {
            if (layer != 0)
            {
                return Tints.NONE.getColor();
            }
            return Tier.getTierTint(block.getTier(state)).getColor();
        }, (Block) block));
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(ModBlockEntities.TANK.get(), TankBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GEMSTONE_MANIPULATOR.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GEMSTONE_GENERATOR.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GEMSTONE_CELL.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CRYSTAL_GROWER.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CRYSTAL_CHARGER.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ELECTRIC_FURNACE.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.METAL_FORMER.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PULVERIZER.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALLOY_SMELTER.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.EXTRACTOR.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ORE_WASHER.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.SAWMILL.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.POLARIZER.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GEMSTONE_MANIPULATOR.get(), ConfigMachineBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.FISSION_REACTOR.get(), ConfigMachineBER::new);

        event.registerEntityRenderer(ModEntities.RUBY_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.SAPPHIRE_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.AQUAMARINE_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.JADE_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.OPAL_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.YELLOW_DIAMOND_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.AMBER_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.TOPAZ_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.BERYLLIUM_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.BIXBIT_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.MALACHITE_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.ONYX_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.PERIDOT_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.MOON_STONE_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.SUN_STONE_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.CITRINE_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.DOLOMITE_ARROW.get(), CrystalArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.TANZANITE_ARROW.get(), CrystalArrowRenderer::new);
    }
}
