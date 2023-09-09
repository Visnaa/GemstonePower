package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class ModTabs
{
    public static final Supplier<CreativeModeTab> MAIN_TAB = () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 4)
            .title(Component.translatable("itemGroup." + GemstonePower.MOD_ID + ".main_tab"))
            .icon(() -> new ItemStack(ModItems.GEMSTONE_GENERATOR.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((flags, output) ->{
                output.accept(ModItems.GEMSTONE_GENERATOR.get());
                output.accept(ModItems.GEMSTONE_CELL.get());

                output.accept(ModItems.CRYSTAL_GROWER.get());
                output.accept(ModItems.CRYSTAL_CHARGER.get());

                output.accept(ModItems.ELECTRIC_FURNACE.get());
                output.accept(ModItems.METAL_FORMER.get());
                output.accept(ModItems.PULVERIZER.get());
                output.accept(ModItems.ALLOY_SMELTER.get());
                output.accept(ModItems.EXTRACTOR.get());
                output.accept(ModItems.ORE_WASHER.get());
                output.accept(ModItems.COBBLESTONE_GENERATOR.get());
                output.accept(ModItems.SAWMILL.get());
                output.accept(ModItems.POLARIZER.get());

                output.accept(ModItems.SOLAR_PANEL.get());
                output.accept(ModItems.WATER_MILL.get());
                output.accept(ModItems.WIND_TURBINE.get());

                output.accept(ModItems.TANK.get());

                output.accept(ModItems.FISSION_REACTOR.get());
                output.accept(ModItems.REACTOR_WALL.get());
                output.accept(ModItems.REACTOR_FRAME.get());
                output.accept(ModItems.WATER_COOLING.get());
                output.accept(ModItems.SNOW_COOLING.get());
                output.accept(ModItems.ICE_COOLING.get());
                output.accept(ModItems.PACKED_ICE_COOLING.get());
                output.accept(ModItems.BLUE_ICE_COOLING.get());
                output.accept(ModItems.PRISMARINE_COOLING.get());

                output.accept(ModItems.INTERMEDIATE_UPGRADE.get());
                output.accept(ModItems.ADVANCED_UPGRADE.get());
                output.accept(ModItems.ULTRA_UPGRADE.get());
                output.accept(ModItems.EXTREME_UPGRADE.get());

                output.accept(ModItems.METAL_WRENCH.get());
                output.accept(ModItems.CRYSTAL_WRENCH.get());

                output.accept(ModItems.PLATE_PRESET.get());
                output.accept(ModItems.ROD_PRESET.get());
                output.accept(ModItems.WIRE_PRESET.get());
                output.accept(ModItems.RESIN.get());
                output.accept(ModItems.RUBBER.get());
                output.accept(ModItems.TREE_TAP.get());
                output.accept(ModItems.RESIN_OAK_SAPLING.get());
                output.accept(ModItems.RESIN_OAK_LOG.get());
                output.accept(ModItems.RESIN_OAK_LEAVES.get());

                output.accept(ModItems.COPPER_WIRE.get());
                output.accept(ModItems.COPPER_CABLE.get());
                output.accept(ModItems.ALUMINUM_WIRE.get());
                output.accept(ModItems.ALUMINUM_CABLE.get());
                output.accept(ModItems.TIN_WIRE.get());
                output.accept(ModItems.TIN_CABLE.get());
                output.accept(ModItems.ELECTRUM_WIRE.get());
                output.accept(ModItems.ELECTRUM_CABLE.get());

                output.accept(ModItems.IRON_ITEM_PIPE.get());
                output.accept(ModItems.GOLD_ITEM_PIPE.get());
                output.accept(ModItems.COPPER_ITEM_PIPE.get());
                output.accept(ModItems.PLATINUM_ITEM_PIPE.get());
                output.accept(ModItems.LEAD_ITEM_PIPE.get());

                output.accept(ModItems.GOLD_FLUID_PIPE.get());
                output.accept(ModItems.COPPER_FLUID_PIPE.get());
                output.accept(ModItems.INVAR_FLUID_PIPE.get());
                output.accept(ModItems.STEEL_FLUID_PIPE.get());
            }).build();

    public static final Supplier<CreativeModeTab> RESOURCES_TAB = () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + GemstonePower.MOD_ID + ".resources_tab"))
            .icon(() -> new ItemStack(ModItems.RUBY.get()))
            .withTabsBefore(new ResourceLocation(GemstonePower.MOD_ID, "main_tab"))
            .withTabsAfter(new ResourceLocation(GemstonePower.MOD_ID, "combat_tab"))
            .displayItems((flags, output) ->
            {
                output.accept(Items.COAL);
                output.accept(ModItems.COAL_DUST.get());

                output.accept(ModItems.LIGHT_GEMSTONE.get());
                output.accept(ModItems.DARK_GEMSTONE.get());

                output.accept(Items.IRON_INGOT);
                output.accept(Blocks.IRON_BLOCK);
                output.accept(Blocks.IRON_ORE);
                output.accept(Blocks.DEEPSLATE_IRON_ORE);
                output.accept(Items.RAW_IRON);
                output.accept(Items.IRON_NUGGET);
                output.accept(ModItems.IRON_DUST.get());
                output.accept(ModItems.IRON_TINY_PILE.get());
                output.accept(ModItems.IRON_ORE_DUST.get());
                output.accept(ModItems.IRON_PLATE.get());
                output.accept(ModItems.IRON_ROD.get());
                output.accept(ModItems.IRON_ROD_POLARIZED.get());
                output.accept(ModItems.IRON_GEAR.get());

                output.accept(Items.GOLD_INGOT);
                output.accept(Blocks.GOLD_BLOCK);
                output.accept(Blocks.GOLD_ORE);
                output.accept(Blocks.DEEPSLATE_GOLD_ORE);
                output.accept(Items.RAW_GOLD);
                output.accept(Items.GOLD_NUGGET);
                output.accept(ModItems.GOLD_DUST.get());
                output.accept(ModItems.GOLD_TINY_PILE.get());
                output.accept(ModItems.GOLD_ORE_DUST.get());
                output.accept(ModItems.GOLD_PLATE.get());
                output.accept(ModItems.GOLD_ROD.get());
                output.accept(ModItems.GOLD_GEAR.get());
                
                output.accept(Items.COPPER_INGOT);
                output.accept(Blocks.COPPER_BLOCK);
                output.accept(Blocks.COPPER_ORE);
                output.accept(Blocks.DEEPSLATE_COPPER_ORE);
                output.accept(Items.RAW_COPPER);
                output.accept(ModItems.COPPER_NUGGET.get());
                output.accept(ModItems.COPPER_DUST.get());
                output.accept(ModItems.COPPER_TINY_PILE.get());
                output.accept(ModItems.COPPER_ORE_DUST.get());
                output.accept(ModItems.COPPER_PLATE.get());
                output.accept(ModItems.COPPER_ROD.get());
                output.accept(ModItems.COPPER_GEAR.get());

                output.accept(ModItems.ALUMINUM_INGOT.get());
                output.accept(ModBlocks.ALUMINUM_BLOCK.get());
                output.accept(ModBlocks.ALUMINUM_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get());
                output.accept(ModItems.RAW_ALUMINUM.get());
                output.accept(ModItems.ALUMINUM_NUGGET.get());
                output.accept(ModItems.ALUMINUM_DUST.get());
                output.accept(ModItems.ALUMINUM_TINY_PILE.get());
                output.accept(ModItems.ALUMINUM_ORE_DUST.get());
                output.accept(ModItems.ALUMINUM_PLATE.get());
                output.accept(ModItems.ALUMINUM_ROD.get());
                output.accept(ModItems.ALUMINUM_GEAR.get());

                output.accept(ModItems.TIN_INGOT.get());
                output.accept(ModBlocks.TIN_BLOCK.get());
                output.accept(ModBlocks.TIN_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_TIN_ORE.get());
                output.accept(ModItems.RAW_TIN.get());
                output.accept(ModItems.TIN_NUGGET.get());
                output.accept(ModItems.TIN_DUST.get());
                output.accept(ModItems.TIN_TINY_PILE.get());
                output.accept(ModItems.TIN_ORE_DUST.get());
                output.accept(ModItems.TIN_PLATE.get());
                output.accept(ModItems.TIN_ROD.get());
                output.accept(ModItems.TIN_GEAR.get());

                output.accept(ModItems.BRONZE_INGOT.get());
                output.accept(ModBlocks.BRONZE_BLOCK.get());
                output.accept(ModItems.BRONZE_NUGGET.get());
                output.accept(ModItems.BRONZE_DUST.get());
                output.accept(ModItems.BRONZE_TINY_PILE.get());
                output.accept(ModItems.BRONZE_PLATE.get());
                output.accept(ModItems.BRONZE_ROD.get());
                output.accept(ModItems.BRONZE_GEAR.get());

                output.accept(ModItems.SILVER_INGOT.get());
                output.accept(ModBlocks.SILVER_BLOCK.get());
                output.accept(ModBlocks.SILVER_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_SILVER_ORE.get());
                output.accept(ModItems.RAW_SILVER.get());
                output.accept(ModItems.SILVER_NUGGET.get());
                output.accept(ModItems.SILVER_DUST.get());
                output.accept(ModItems.SILVER_TINY_PILE.get());
                output.accept(ModItems.SILVER_ORE_DUST.get());
                output.accept(ModItems.SILVER_PLATE.get());
                output.accept(ModItems.SILVER_ROD.get());
                output.accept(ModItems.SILVER_GEAR.get());

                output.accept(ModItems.ELECTRUM_INGOT.get());
                output.accept(ModBlocks.ELECTRUM_BLOCK.get());
                output.accept(ModItems.ELECTRUM_NUGGET.get());
                output.accept(ModItems.ELECTRUM_DUST.get());
                output.accept(ModItems.ELECTRUM_TINY_PILE.get());
                output.accept(ModItems.ELECTRUM_PLATE.get());
                output.accept(ModItems.ELECTRUM_ROD.get());
                output.accept(ModItems.ELECTRUM_GEAR.get());

                output.accept(ModItems.NICKEL_INGOT.get());
                output.accept(ModBlocks.NICKEL_BLOCK.get());
                output.accept(ModBlocks.NICKEL_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_NICKEL_ORE.get());
                output.accept(ModItems.RAW_NICKEL.get());
                output.accept(ModItems.NICKEL_NUGGET.get());
                output.accept(ModItems.NICKEL_DUST.get());
                output.accept(ModItems.NICKEL_TINY_PILE.get());
                output.accept(ModItems.NICKEL_ORE_DUST.get());
                output.accept(ModItems.NICKEL_PLATE.get());
                output.accept(ModItems.NICKEL_ROD.get());
                output.accept(ModItems.NICKEL_ROD_POLARIZED.get());
                output.accept(ModItems.NICKEL_GEAR.get());

                output.accept(ModItems.INVAR_INGOT.get());
                output.accept(ModBlocks.INVAR_BLOCK.get());
                output.accept(ModItems.INVAR_NUGGET.get());
                output.accept(ModItems.INVAR_DUST.get());
                output.accept(ModItems.INVAR_TINY_PILE.get());
                output.accept(ModItems.INVAR_PLATE.get());
                output.accept(ModItems.INVAR_ROD.get());
                output.accept(ModItems.INVAR_ROD_POLARIZED.get());
                output.accept(ModItems.INVAR_GEAR.get());

                output.accept(ModItems.CONSTANTAN_INGOT.get());
                output.accept(ModBlocks.CONSTANTAN_BLOCK.get());
                output.accept(ModItems.CONSTANTAN_NUGGET.get());
                output.accept(ModItems.CONSTANTAN_DUST.get());
                output.accept(ModItems.CONSTANTAN_TINY_PILE.get());
                output.accept(ModItems.CONSTANTAN_PLATE.get());
                output.accept(ModItems.CONSTANTAN_ROD.get());
                output.accept(ModItems.CONSTANTAN_ROD_POLARIZED.get());
                output.accept(ModItems.CONSTANTAN_GEAR.get());

                output.accept(ModItems.PLATINUM_INGOT.get());
                output.accept(ModBlocks.PLATINUM_BLOCK.get());
                output.accept(ModBlocks.PLATINUM_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_PLATINUM_ORE.get());
                output.accept(ModItems.RAW_PLATINUM.get());
                output.accept(ModItems.PLATINUM_NUGGET.get());
                output.accept(ModItems.PLATINUM_DUST.get());
                output.accept(ModItems.PLATINUM_TINY_PILE.get());
                output.accept(ModItems.PLATINUM_ORE_DUST.get());
                output.accept(ModItems.PLATINUM_PLATE.get());
                output.accept(ModItems.PLATINUM_ROD.get());
                output.accept(ModItems.PLATINUM_GEAR.get());

                output.accept(ModItems.STEEL_INGOT.get());
                output.accept(ModBlocks.STEEL_BLOCK.get());
                output.accept(ModItems.STEEL_NUGGET.get());
                output.accept(ModItems.STEEL_DUST.get());
                output.accept(ModItems.STEEL_TINY_PILE.get());
                output.accept(ModItems.STEEL_PLATE.get());
                output.accept(ModItems.STEEL_ROD.get());
                output.accept(ModItems.STEEL_ROD_POLARIZED.get());
                output.accept(ModItems.STEEL_GEAR.get());

                output.accept(ModItems.LITHIUM_INGOT.get());
                output.accept(ModBlocks.LITHIUM_BLOCK.get());
                output.accept(ModBlocks.LITHIUM_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_LITHIUM_ORE.get());
                output.accept(ModItems.RAW_LITHIUM.get());
                output.accept(ModItems.LITHIUM_NUGGET.get());
                output.accept(ModItems.LITHIUM_DUST.get());
                output.accept(ModItems.LITHIUM_TINY_PILE.get());
                output.accept(ModItems.LITHIUM_ORE_DUST.get());
                output.accept(ModItems.LITHIUM_PLATE.get());
                output.accept(ModItems.LITHIUM_ROD.get());
                output.accept(ModItems.LITHIUM_GEAR.get());

                output.accept(ModItems.MAGNESIUM_INGOT.get());
                output.accept(ModBlocks.MAGNESIUM_BLOCK.get());
                output.accept(ModBlocks.MAGNESIUM_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get());
                output.accept(ModItems.RAW_MAGNESIUM.get());
                output.accept(ModItems.MAGNESIUM_NUGGET.get());
                output.accept(ModItems.MAGNESIUM_DUST.get());
                output.accept(ModItems.MAGNESIUM_TINY_PILE.get());
                output.accept(ModItems.MAGNESIUM_ORE_DUST.get());
                output.accept(ModItems.MAGNESIUM_PLATE.get());
                output.accept(ModItems.MAGNESIUM_ROD.get());
                output.accept(ModItems.MAGNESIUM_GEAR.get());

                output.accept(ModItems.URANIUM_INGOT.get());
                output.accept(ModBlocks.URANIUM_BLOCK.get());
                output.accept(ModBlocks.URANIUM_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_URANIUM_ORE.get());
                output.accept(ModItems.RAW_URANIUM.get());
                output.accept(ModItems.URANIUM_NUGGET.get());
                output.accept(ModItems.URANIUM_DUST.get());
                output.accept(ModItems.URANIUM_TINY_PILE.get());
                output.accept(ModItems.URANIUM_ORE_DUST.get());
                output.accept(ModItems.URANIUM_PLATE.get());
                output.accept(ModItems.URANIUM_ROD.get());
                output.accept(ModItems.URANIUM_GEAR.get());

                output.accept(ModItems.LEAD_INGOT.get());
                output.accept(ModBlocks.LEAD_BLOCK.get());
                output.accept(ModBlocks.LEAD_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_LEAD_ORE.get());
                output.accept(ModItems.RAW_LEAD.get());
                output.accept(ModItems.LEAD_NUGGET.get());
                output.accept(ModItems.LEAD_DUST.get());
                output.accept(ModItems.LEAD_TINY_PILE.get());
                output.accept(ModItems.LEAD_ORE_DUST.get());
                output.accept(ModItems.LEAD_PLATE.get());
                output.accept(ModItems.LEAD_ROD.get());
                output.accept(ModItems.LEAD_GEAR.get());

                output.accept(ModItems.ZINC_INGOT.get());
                output.accept(ModBlocks.ZINC_BLOCK.get());
                output.accept(ModBlocks.ZINC_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_ZINC_ORE.get());
                output.accept(ModItems.RAW_ZINC.get());
                output.accept(ModItems.ZINC_NUGGET.get());
                output.accept(ModItems.ZINC_DUST.get());
                output.accept(ModItems.ZINC_TINY_PILE.get());
                output.accept(ModItems.ZINC_ORE_DUST.get());
                output.accept(ModItems.ZINC_PLATE.get());
                output.accept(ModItems.ZINC_ROD.get());
                output.accept(ModItems.ZINC_GEAR.get());

                output.accept(ModItems.RUBY.get());
                output.accept(ModItems.SAPPHIRE.get());
                output.accept(ModItems.AQUAMARINE.get());
                output.accept(ModItems.JADE.get());
                output.accept(ModItems.OPAL.get());
                output.accept(ModItems.YELLOW_DIAMOND.get());
                output.accept(ModItems.AMBER.get());
                output.accept(ModItems.TOPAZ.get());
                output.accept(ModItems.BERYLLIUM.get());
                output.accept(ModItems.BIXBIT.get());
                output.accept(ModItems.MALACHITE.get());
                output.accept(ModItems.ONYX.get());
                output.accept(ModItems.PERIDOT.get());
                output.accept(ModItems.MOON_STONE.get());
                output.accept(ModItems.SUN_STONE.get());
                output.accept(ModItems.CITRINE.get());
                output.accept(ModItems.DOLOMITE.get());
                output.accept(ModItems.TANZANITE.get());

                output.accept(ModItems.RUBY_SEED.get());
                output.accept(ModItems.SAPPHIRE_SEED.get());
                output.accept(ModItems.AQUAMARINE_SEED.get());
                output.accept(ModItems.JADE_SEED.get());
                output.accept(ModItems.OPAL_SEED.get());
                output.accept(ModItems.YELLOW_DIAMOND_SEED.get());
                output.accept(ModItems.AMBER_SEED.get());
                output.accept(ModItems.TOPAZ_SEED.get());
                output.accept(ModItems.BERYLLIUM_SEED.get());
                output.accept(ModItems.BIXBIT_SEED.get());
                output.accept(ModItems.MALACHITE_SEED.get());
                output.accept(ModItems.ONYX_SEED.get());
                output.accept(ModItems.PERIDOT_SEED.get());
                output.accept(ModItems.MOON_STONE_SEED.get());
                output.accept(ModItems.SUN_STONE_SEED.get());
                output.accept(ModItems.CITRINE_SEED.get());
                output.accept(ModItems.DOLOMITE_SEED.get());
                output.accept(ModItems.TANZANITE_SEED.get());

                output.accept(ModItems.RUBY_CHARGED.get());
                output.accept(ModItems.SAPPHIRE_CHARGED.get());
                output.accept(ModItems.AQUAMARINE_CHARGED.get());
                output.accept(ModItems.JADE_CHARGED.get());
                output.accept(ModItems.OPAL_CHARGED.get());
                output.accept(ModItems.YELLOW_DIAMOND_CHARGED.get());
                output.accept(ModItems.AMBER_CHARGED.get());
                output.accept(ModItems.TOPAZ_CHARGED.get());
                output.accept(ModItems.BERYLLIUM_CHARGED.get());
                output.accept(ModItems.BIXBIT_CHARGED.get());
                output.accept(ModItems.MALACHITE_CHARGED.get());
                output.accept(ModItems.ONYX_CHARGED.get());
                output.accept(ModItems.PERIDOT_CHARGED.get());
                output.accept(ModItems.MOON_STONE_CHARGED.get());
                output.accept(ModItems.SUN_STONE_CHARGED.get());
                output.accept(ModItems.CITRINE_CHARGED.get());
                output.accept(ModItems.DOLOMITE_CHARGED.get());
                output.accept(ModItems.TANZANITE_CHARGED.get());

                output.accept(ModBlocks.RUBY_CRYSTALS.get());
                output.accept(ModBlocks.SAPPHIRE_CRYSTALS.get());
                output.accept(ModBlocks.AQUAMARINE_CRYSTALS.get());
                output.accept(ModBlocks.JADE_CRYSTALS.get());
                output.accept(ModBlocks.OPAL_CRYSTALS.get());
                output.accept(ModBlocks.YELLOW_DIAMOND_CRYSTALS.get());
                output.accept(ModBlocks.AMBER_CRYSTALS.get());
                output.accept(ModBlocks.TOPAZ_CRYSTALS.get());
                output.accept(ModBlocks.BERYLLIUM_CRYSTALS.get());
                output.accept(ModBlocks.BIXBIT_CRYSTALS.get());
                output.accept(ModBlocks.MALACHITE_CRYSTALS.get());
                output.accept(ModBlocks.ONYX_CRYSTALS.get());
                output.accept(ModBlocks.PERIDOT_CRYSTALS.get());
                output.accept(ModBlocks.MOON_STONE_CRYSTALS.get());
                output.accept(ModBlocks.SUN_STONE_CRYSTALS.get());
                output.accept(ModBlocks.CITRINE_CRYSTALS.get());
                output.accept(ModBlocks.DOLOMITE_CRYSTALS.get());
                output.accept(ModBlocks.TANZANITE_CRYSTALS.get());
            }).build();

    public static final Supplier<CreativeModeTab> COMBAT_TAB = () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + GemstonePower.MOD_ID + ".combat_tab"))
            .icon(() -> new ItemStack(ModTools.GEMSTONE_SWORD.get()))
            .displayItems((flags, output) ->
            {
                output.accept(ModArmors.GEMSTONE_HELMET.get());
                output.accept(ModArmors.GEMSTONE_CHESTPLATE.get());
                output.accept(ModArmors.GEMSTONE_LEGGINGS.get());
                output.accept(ModArmors.GEMSTONE_BOOTS.get());
                output.accept(ModTools.GEMSTONE_SWORD.get());
                output.accept(ModTools.GEMSTONE_SHOVEL.get());
                output.accept(ModTools.GEMSTONE_PICKAXE.get());
                output.accept(ModTools.GEMSTONE_AXE.get());
                output.accept(ModTools.GEMSTONE_HOE.get());

                output.accept(ModArmors.COPPER_HELMET.get());
                output.accept(ModArmors.COPPER_CHESTPLATE.get());
                output.accept(ModArmors.COPPER_LEGGINGS.get());
                output.accept(ModArmors.COPPER_BOOTS.get());
                output.accept(ModTools.COPPER_SWORD.get());
                output.accept(ModTools.COPPER_SHOVEL.get());
                output.accept(ModTools.COPPER_PICKAXE.get());
                output.accept(ModTools.COPPER_AXE.get());
                output.accept(ModTools.COPPER_HOE.get());

                output.accept(ModArmors.ALUMINUM_HELMET.get());
                output.accept(ModArmors.ALUMINUM_CHESTPLATE.get());
                output.accept(ModArmors.ALUMINUM_LEGGINGS.get());
                output.accept(ModArmors.ALUMINUM_BOOTS.get());
                output.accept(ModTools.ALUMINUM_SWORD.get());
                output.accept(ModTools.ALUMINUM_SHOVEL.get());
                output.accept(ModTools.ALUMINUM_PICKAXE.get());
                output.accept(ModTools.ALUMINUM_AXE.get());
                output.accept(ModTools.ALUMINUM_HOE.get());

                output.accept(ModArmors.BRONZE_HELMET.get());
                output.accept(ModArmors.BRONZE_CHESTPLATE.get());
                output.accept(ModArmors.BRONZE_LEGGINGS.get());
                output.accept(ModArmors.BRONZE_BOOTS.get());
                output.accept(ModTools.BRONZE_SWORD.get());
                output.accept(ModTools.BRONZE_SHOVEL.get());
                output.accept(ModTools.BRONZE_PICKAXE.get());
                output.accept(ModTools.BRONZE_AXE.get());
                output.accept(ModTools.BRONZE_HOE.get());

                output.accept(ModArmors.SILVER_HELMET.get());
                output.accept(ModArmors.SILVER_CHESTPLATE.get());
                output.accept(ModArmors.SILVER_LEGGINGS.get());
                output.accept(ModArmors.SILVER_BOOTS.get());
                output.accept(ModTools.SILVER_SWORD.get());
                output.accept(ModTools.SILVER_SHOVEL.get());
                output.accept(ModTools.SILVER_PICKAXE.get());
                output.accept(ModTools.SILVER_AXE.get());
                output.accept(ModTools.SILVER_HOE.get());

                output.accept(ModArmors.INVAR_HELMET.get());
                output.accept(ModArmors.INVAR_CHESTPLATE.get());
                output.accept(ModArmors.INVAR_LEGGINGS.get());
                output.accept(ModArmors.INVAR_BOOTS.get());
                output.accept(ModTools.INVAR_SWORD.get());
                output.accept(ModTools.INVAR_SHOVEL.get());
                output.accept(ModTools.INVAR_PICKAXE.get());
                output.accept(ModTools.INVAR_AXE.get());
                output.accept(ModTools.INVAR_HOE.get());

                output.accept(ModArmors.STEEL_HELMET.get());
                output.accept(ModArmors.STEEL_CHESTPLATE.get());
                output.accept(ModArmors.STEEL_LEGGINGS.get());
                output.accept(ModArmors.STEEL_BOOTS.get());
                output.accept(ModTools.STEEL_SWORD.get());
                output.accept(ModTools.STEEL_SHOVEL.get());
                output.accept(ModTools.STEEL_PICKAXE.get());
                output.accept(ModTools.STEEL_AXE.get());
                output.accept(ModTools.STEEL_HOE.get());

                output.accept(ModItems.RUBY_ARROW.get());
                output.accept(ModItems.SAPPHIRE_ARROW.get());
                output.accept(ModItems.AQUAMARINE_ARROW.get());
                output.accept(ModItems.JADE_ARROW.get());
                output.accept(ModItems.OPAL_ARROW.get());
                output.accept(ModItems.YELLOW_DIAMOND_ARROW.get());
                output.accept(ModItems.AMBER_ARROW.get());
                output.accept(ModItems.TOPAZ_ARROW.get());
                output.accept(ModItems.BERYLLIUM_ARROW.get());
                output.accept(ModItems.BIXBIT_ARROW.get());
                output.accept(ModItems.MALACHITE_ARROW.get());
                output.accept(ModItems.ONYX_ARROW.get());
                output.accept(ModItems.PERIDOT_ARROW.get());
                output.accept(ModItems.MOON_STONE_ARROW.get());
                output.accept(ModItems.SUN_STONE_ARROW.get());
                output.accept(ModItems.CITRINE_ARROW.get());
                output.accept(ModItems.DOLOMITE_ARROW.get());
                output.accept(ModItems.TANZANITE_ARROW.get());

                output.accept(ModItems.CHARGED_RUBY_ARROW.get());
                output.accept(ModItems.CHARGED_SAPPHIRE_ARROW.get());
                output.accept(ModItems.CHARGED_AQUAMARINE_ARROW.get());
                output.accept(ModItems.CHARGED_JADE_ARROW.get());
                output.accept(ModItems.CHARGED_OPAL_ARROW.get());
                output.accept(ModItems.CHARGED_YELLOW_DIAMOND_ARROW.get());
                output.accept(ModItems.CHARGED_AMBER_ARROW.get());
                output.accept(ModItems.CHARGED_TOPAZ_ARROW.get());
                output.accept(ModItems.CHARGED_BERYLLIUM_ARROW.get());
                output.accept(ModItems.CHARGED_BIXBIT_ARROW.get());
                output.accept(ModItems.CHARGED_MALACHITE_ARROW.get());
                output.accept(ModItems.CHARGED_ONYX_ARROW.get());
                output.accept(ModItems.CHARGED_PERIDOT_ARROW.get());
                output.accept(ModItems.CHARGED_MOON_STONE_ARROW.get());
                output.accept(ModItems.CHARGED_SUN_STONE_ARROW.get());
                output.accept(ModItems.CHARGED_CITRINE_ARROW.get());
                output.accept(ModItems.CHARGED_DOLOMITE_ARROW.get());
                output.accept(ModItems.CHARGED_TANZANITE_ARROW.get());
            }).build();
}
