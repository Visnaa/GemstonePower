package com.visnaa.gemstonepower.event.mod;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.client.render.entity.CrystalArrowRenderer;
import com.visnaa.gemstonepower.client.screen.*;
import com.visnaa.gemstonepower.registry.*;
import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = GemstonePower.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents
{
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            MenuScreens.register(ModContainers.GEMSTONE_GENERATOR.get(), GemstoneGeneratorScreen::new);
            MenuScreens.register(ModContainers.GEMSTONE_CELL.get(), GemstoneCellScreen::new);
            MenuScreens.register(ModContainers.CRYSTAL_GROWER.get(), CrystalGrowerScreen::new);
            MenuScreens.register(ModContainers.CRYSTAL_CHARGER.get(), CrystalChargerScreen::new);
            MenuScreens.register(ModContainers.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
            MenuScreens.register(ModContainers.METAL_FORMER.get(), MetalFormerScreen::new);
            MenuScreens.register(ModContainers.PULVERIZER.get(), PulverizerScreen::new);
            MenuScreens.register(ModContainers.ALLOY_SMELTER.get(), AlloySmelterScreen::new);
            MenuScreens.register(ModContainers.EXTRACTOR.get(), ExtractorScreen::new);
            MenuScreens.register(ModContainers.ORE_WASHER.get(), OreWasherScreen::new);
            MenuScreens.register(ModContainers.SAWMILL.get(), SawmillScreen::new);
            MenuScreens.register(ModContainers.POLARIZER.get(), PolarizerScreen::new);
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
        event.register((stack, layer) -> event.getBlockColors().getColor(((BlockItem)stack.getItem()).getBlock().defaultBlockState(), null, null, layer), ModBlocks.RESIN_OAK_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerColouredBlocks(RegisterColorHandlersEvent.Block event)
    {
        Tints.TINTED_BLOCKS.forEach(block -> event.register((state, getter, pos, layer) -> block.getColor(), (Block) block));
        event.register((state, getter, pos, layer) -> getter != null && pos != null ? BiomeColors.getAverageFoliageColor(getter, pos) : FoliageColor.getDefaultColor(), ModBlocks.RESIN_OAK_LEAVES.get());

        Tier.BLOCKS.forEach(block -> event.register((state, getter, pos, layer) ->
        {
            if (layer != 0)
            {
                return Tints.EMPTY.getColor();
            }
            return Tier.getTierTint(block.getTier(state)).getColor();
        }, (Block) block));
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
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
