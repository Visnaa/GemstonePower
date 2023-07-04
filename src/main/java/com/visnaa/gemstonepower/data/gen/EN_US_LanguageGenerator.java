package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.registry.*;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EN_US_LanguageGenerator extends LanguageProvider
{
    public EN_US_LanguageGenerator(PackOutput output)
    {
        super(output, GemstonePower.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
    // TABS
        this.add("itemGroup.gemstonepower.main_tab", "Gemstone Power");
        this.add("itemGroup.gemstonepower.resources_tab", "Gemstone Power (Resources)");
        this.add("itemGroup.gemstonepower.combat_tab", "Gemstone Power (Combat)");
        
    // GEMSTONE
        this.add(ModItems.LIGHT_GEMSTONE.get(), "Light Gemstone");
        this.add(ModItems.DARK_GEMSTONE.get(), "Dark Gemstone");

        this.add(ModArmors.GEMSTONE_HELMET.get(), "Gemstone Helmet");
        this.add(ModArmors.GEMSTONE_CHESTPLATE.get(), "Gemstone Chestplate");
        this.add(ModArmors.GEMSTONE_LEGGINGS.get(), "Gemstone Leggings");
        this.add(ModArmors.GEMSTONE_BOOTS.get(), "Gemstone Boots");

        this.add(ModTools.GEMSTONE_SWORD.get(), "Gemstone Sword");
        this.add(ModTools.GEMSTONE_SHOVEL.get(), "Gemstone Shovel");
        this.add(ModTools.GEMSTONE_PICKAXE.get(), "Gemstone Pickaxe");
        this.add(ModTools.GEMSTONE_AXE.get(), "Gemstone Axe");
        this.add(ModTools.GEMSTONE_HOE.get(), "Gemstone Hoe");

    // AZURITE
        this.add(ModItems.AZURITE_CRYSTAL.get(), "Azurite Crystal");
        this.add(ModItems.AZURITE_CRYSTAL_CHARGED.get(), "Azurite Crystal (Charged)");
        this.add(ModItems.AZURITE_CRYSTAL_SEED.get(), "Azurite Crystal Seed");

    // CUPRITE
        this.add(ModItems.CUPRITE_CRYSTAL.get(), "Cuprite Crystal");
        this.add(ModItems.CUPRITE_CRYSTAL_CHARGED.get(), "Cuprite Crystal (Charged)");
        this.add(ModItems.CUPRITE_CRYSTAL_SEED.get(), "Cuprite Crystal Seed");

    // IRON
        this.add(ModItems.IRON_DUST.get(), "Iron Dust");
        this.add(ModItems.IRON_TINY_PILE.get(), "Tiny Pile Of Iron Dust");
        this.add(ModItems.IRON_ORE_DUST.get(), "Iron Ore Dust");
        this.add(ModItems.IRON_PLATE.get(), "Iron Plate");
        this.add(ModItems.IRON_ROD.get(), "Iron Rod");
        this.add(ModItems.IRON_ROD_POLARIZED.get(), "Iron Rod (Polarized)");
        this.add(ModItems.IRON_GEAR.get(), "Iron Gear");

    // GOLD
        this.add(ModItems.GOLD_DUST.get(), "Gold Dust");
        this.add(ModItems.GOLD_TINY_PILE.get(), "Tiny Pile Of Gold Dust");
        this.add(ModItems.GOLD_ORE_DUST.get(), "Gold Ore Dust");
        this.add(ModItems.GOLD_PLATE.get(), "Gold Plate");
        this.add(ModItems.GOLD_ROD.get(), "Gold Rod");
        this.add(ModItems.GOLD_GEAR.get(), "Gold Gear");

    // COPPER
        this.add(ModItems.COPPER_NUGGET.get(), "Copper Nugget");
        this.add(ModItems.COPPER_DUST.get(), "Copper Dust");
        this.add(ModItems.COPPER_TINY_PILE.get(), "Tiny Pile Of Copper Dust");
        this.add(ModItems.COPPER_ORE_DUST.get(), "Copper Ore Dust");
        this.add(ModItems.COPPER_PLATE.get(), "Copper Plate");
        this.add(ModItems.COPPER_ROD.get(), "Copper Rod");
        this.add(ModItems.COPPER_GEAR.get(), "Copper Gear");

        this.add(ModArmors.COPPER_HELMET.get(), "Copper Helmet");
        this.add(ModArmors.COPPER_CHESTPLATE.get(), "Copper Chestplate");
        this.add(ModArmors.COPPER_LEGGINGS.get(), "Copper Leggings");
        this.add(ModArmors.COPPER_BOOTS.get(), "Copper Boots");

        this.add(ModTools.COPPER_SWORD.get(), "Copper Sword");
        this.add(ModTools.COPPER_SHOVEL.get(), "Copper Shovel");
        this.add(ModTools.COPPER_PICKAXE.get(), "Copper Pickaxe");
        this.add(ModTools.COPPER_AXE.get(), "Copper Axe");
        this.add(ModTools.COPPER_HOE.get(), "Copper Hoe");

    // ALUMINIUM
        this.add(ModItems.ALUMINUM_INGOT.get(), "Aluminum Ingot");
        this.add(ModBlocks.ALUMINUM_BLOCK.get(), "Aluminum Block");
        this.add(ModBlocks.ALUMINUM_ORE.get(), "Aluminum Ore");
        this.add(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(), "Deepslate Aluminum Ore");
        this.add(ModItems.RAW_ALUMINUM.get(), "Raw Aluminum");
        this.add(ModItems.ALUMINUM_NUGGET.get(), "Aluminum Nugget");
        this.add(ModItems.ALUMINUM_DUST.get(), "Aluminum Dust");
        this.add(ModItems.ALUMINUM_TINY_PILE.get(), "Tiny Pile Of Aluminum Dust");
        this.add(ModItems.ALUMINUM_ORE_DUST.get(), "Aluminum Ore Dust");
        this.add(ModItems.ALUMINUM_PLATE.get(), "Aluminum Plate");
        this.add(ModItems.ALUMINUM_ROD.get(), "Aluminum Rod");
        this.add(ModItems.ALUMINUM_GEAR.get(), "Aluminum Gear");

        this.add(ModArmors.ALUMINUM_HELMET.get(), "Aluminum Helmet");
        this.add(ModArmors.ALUMINUM_CHESTPLATE.get(), "Aluminum Chestplate");
        this.add(ModArmors.ALUMINUM_LEGGINGS.get(), "Aluminum Leggings");
        this.add(ModArmors.ALUMINUM_BOOTS.get(), "Aluminum Boots");

        this.add(ModTools.ALUMINUM_SWORD.get(), "Aluminum Sword");
        this.add(ModTools.ALUMINUM_SHOVEL.get(), "Aluminum Shovel");
        this.add(ModTools.ALUMINUM_PICKAXE.get(), "Aluminum Pickaxe");
        this.add(ModTools.ALUMINUM_AXE.get(), "Aluminum Axe");
        this.add(ModTools.ALUMINUM_HOE.get(), "Aluminum Hoe");

    // TIN
        this.add(ModItems.TIN_INGOT.get(), "Tin Ingot");
        this.add(ModBlocks.TIN_BLOCK.get(), "Tin Block");
        this.add(ModBlocks.TIN_ORE.get(), "Tin Ore");
        this.add(ModBlocks.DEEPSLATE_TIN_ORE.get(), "Deepslate Tin Ore");
        this.add(ModItems.RAW_TIN.get(), "Raw Tin");
        this.add(ModItems.TIN_NUGGET.get(), "Tin Nugget");
        this.add(ModItems.TIN_DUST.get(), "Tin Dust");
        this.add(ModItems.TIN_TINY_PILE.get(), "Tiny Pile Of Tin Dust");
        this.add(ModItems.TIN_ORE_DUST.get(), "Tin Ore Dust");
        this.add(ModItems.TIN_PLATE.get(), "Tin Plate");
        this.add(ModItems.TIN_ROD.get(), "Tin Rod");
        this.add(ModItems.TIN_GEAR.get(), "Tin Gear");

    // BRONZE
        this.add(ModItems.BRONZE_INGOT.get(), "Bronze Ingot");
        this.add(ModBlocks.BRONZE_BLOCK.get(), "Bronze Block");
        this.add(ModItems.BRONZE_NUGGET.get(), "Bronze Nugget");
        this.add(ModItems.BRONZE_DUST.get(), "Bronze Dust");
        this.add(ModItems.BRONZE_TINY_PILE.get(), "Tiny Pile Of Bronze Dust");
        this.add(ModItems.BRONZE_PLATE.get(), "Bronze Plate");
        this.add(ModItems.BRONZE_ROD.get(), "Bronze Rod");
        this.add(ModItems.BRONZE_GEAR.get(), "Bronze Gear");

        this.add(ModArmors.BRONZE_HELMET.get(), "Bronze Helmet");
        this.add(ModArmors.BRONZE_CHESTPLATE.get(), "Bronze Chestplate");
        this.add(ModArmors.BRONZE_LEGGINGS.get(), "Bronze Leggings");
        this.add(ModArmors.BRONZE_BOOTS.get(), "Bronze Boots");

        this.add(ModTools.BRONZE_SWORD.get(), "Bronze Sword");
        this.add(ModTools.BRONZE_SHOVEL.get(), "Bronze Shovel");
        this.add(ModTools.BRONZE_PICKAXE.get(), "Bronze Pickaxe");
        this.add(ModTools.BRONZE_AXE.get(), "Bronze Axe");
        this.add(ModTools.BRONZE_HOE.get(), "Bronze Hoe");

    // SILVER
        this.add(ModItems.SILVER_INGOT.get(), "Silver Ingot");
        this.add(ModBlocks.SILVER_BLOCK.get(), "Silver Block");
        this.add(ModBlocks.SILVER_ORE.get(), "Silver Ore");
        this.add(ModBlocks.DEEPSLATE_SILVER_ORE.get(), "Deepslate Silver Ore");
        this.add(ModItems.RAW_SILVER.get(), "Raw Silver");
        this.add(ModItems.SILVER_NUGGET.get(), "Silver Nugget");
        this.add(ModItems.SILVER_DUST.get(), "Silver Dust");
        this.add(ModItems.SILVER_TINY_PILE.get(), "Tiny Pile Of Silver Dust");
        this.add(ModItems.SILVER_ORE_DUST.get(), "Silver Ore Dust");
        this.add(ModItems.SILVER_PLATE.get(), "Silver Plate");
        this.add(ModItems.SILVER_ROD.get(), "Silver Rod");
        this.add(ModItems.SILVER_GEAR.get(), "Silver Gear");

        this.add(ModArmors.SILVER_HELMET.get(), "Silver Helmet");
        this.add(ModArmors.SILVER_CHESTPLATE.get(), "Silver Chestplate");
        this.add(ModArmors.SILVER_LEGGINGS.get(), "Silver Leggings");
        this.add(ModArmors.SILVER_BOOTS.get(), "Silver Boots");

        this.add(ModTools.SILVER_SWORD.get(), "Silver Sword");
        this.add(ModTools.SILVER_SHOVEL.get(), "Silver Shovel");
        this.add(ModTools.SILVER_PICKAXE.get(), "Silver Pickaxe");
        this.add(ModTools.SILVER_AXE.get(), "Silver Axe");
        this.add(ModTools.SILVER_HOE.get(), "Silver Hoe");

    // ELECTRUM
        this.add(ModItems.ELECTRUM_INGOT.get(), "Electrum Ingot");
        this.add(ModBlocks.ELECTRUM_BLOCK.get(), "Electrum Block");
        this.add(ModItems.ELECTRUM_NUGGET.get(), "Electrum Nugget");
        this.add(ModItems.ELECTRUM_DUST.get(), "Electrum Dust");
        this.add(ModItems.ELECTRUM_TINY_PILE.get(), "Tiny Pile Of Electrum Dust");
        this.add(ModItems.ELECTRUM_PLATE.get(), "Electrum Plate");
        this.add(ModItems.ELECTRUM_ROD.get(), "Electrum Rod");
        this.add(ModItems.ELECTRUM_GEAR.get(), "Electrum Gear");

    // NICKEL
        this.add(ModItems.NICKEL_INGOT.get(), "Nickel Ingot");
        this.add(ModBlocks.NICKEL_BLOCK.get(), "Nickel Block");
        this.add(ModBlocks.NICKEL_ORE.get(), "Nickel Ore");
        this.add(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), "Deepslate Nickel Ore");
        this.add(ModItems.RAW_NICKEL.get(), "Raw Nickel");
        this.add(ModItems.NICKEL_NUGGET.get(), "Nickel Nugget");
        this.add(ModItems.NICKEL_DUST.get(), "Nickel Dust");
        this.add(ModItems.NICKEL_TINY_PILE.get(), "Tiny Pile Of Nickel Dust");
        this.add(ModItems.NICKEL_ORE_DUST.get(), "Nickel Ore Dust");
        this.add(ModItems.NICKEL_PLATE.get(), "Nickel Plate");
        this.add(ModItems.NICKEL_ROD.get(), "Nickel Rod");
        this.add(ModItems.NICKEL_ROD_POLARIZED.get(), "Nickel Rod (Polarized)");
        this.add(ModItems.NICKEL_GEAR.get(), "Nickel Gear");

    // INVAR
        this.add(ModItems.INVAR_INGOT.get(), "Invar Ingot");
        this.add(ModBlocks.INVAR_BLOCK.get(), "Invar Block");
        this.add(ModItems.INVAR_NUGGET.get(), "Invar Nugget");
        this.add(ModItems.INVAR_DUST.get(), "Invar Dust");
        this.add(ModItems.INVAR_TINY_PILE.get(), "Tiny Pile Of Invar Dust");
        this.add(ModItems.INVAR_PLATE.get(), "Invar Plate");
        this.add(ModItems.INVAR_ROD.get(), "Invar Rod");
        this.add(ModItems.INVAR_ROD_POLARIZED.get(), "Invar Rod (Polarized)");
        this.add(ModItems.INVAR_GEAR.get(), "Invar Gear");

        this.add(ModArmors.INVAR_HELMET.get(), "Invar Helmet");
        this.add(ModArmors.INVAR_CHESTPLATE.get(), "Invar Chestplate");
        this.add(ModArmors.INVAR_LEGGINGS.get(), "Invar Leggings");
        this.add(ModArmors.INVAR_BOOTS.get(), "Invar Boots");

        this.add(ModTools.INVAR_SWORD.get(), "Invar Sword");
        this.add(ModTools.INVAR_SHOVEL.get(), "Invar Shovel");
        this.add(ModTools.INVAR_PICKAXE.get(), "Invar Pickaxe");
        this.add(ModTools.INVAR_AXE.get(), "Invar Axe");
        this.add(ModTools.INVAR_HOE.get(), "Invar Hoe");

    // CONSTANTAN
        this.add(ModItems.CONSTANTAN_INGOT.get(), "Constantan Ingot");
        this.add(ModBlocks.CONSTANTAN_BLOCK.get(), "Constantan Block");
        this.add(ModItems.CONSTANTAN_NUGGET.get(), "Constantan Nugget");
        this.add(ModItems.CONSTANTAN_DUST.get(), "Constantan Dust");
        this.add(ModItems.CONSTANTAN_TINY_PILE.get(), "Tiny Pile Of Constantan Dust");
        this.add(ModItems.CONSTANTAN_PLATE.get(), "Constantan Plate");
        this.add(ModItems.CONSTANTAN_ROD.get(), "Constantan Rod");
        this.add(ModItems.CONSTANTAN_ROD_POLARIZED.get(), "Constantan Rod (Polarized)");
        this.add(ModItems.CONSTANTAN_GEAR.get(), "Constantan Gear");

    // PLATINUM
        this.add(ModItems.PLATINUM_INGOT.get(), "Platinum Ingot");
        this.add(ModBlocks.PLATINUM_BLOCK.get(), "Platinum Block");
        this.add(ModBlocks.PLATINUM_ORE.get(), "Platinum Ore");
        this.add(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), "Deepslate Platinum Ore");
        this.add(ModItems.RAW_PLATINUM.get(), "Raw Platinum");
        this.add(ModItems.PLATINUM_NUGGET.get(), "Platinum Nugget");
        this.add(ModItems.PLATINUM_DUST.get(), "Platinum Dust");
        this.add(ModItems.PLATINUM_TINY_PILE.get(), "Tiny Pile Of Platinum Dust");
        this.add(ModItems.PLATINUM_ORE_DUST.get(), "Platinum Ore Dust");
        this.add(ModItems.PLATINUM_PLATE.get(), "Platinum Plate");
        this.add(ModItems.PLATINUM_ROD.get(), "Platinum Rod");
        this.add(ModItems.PLATINUM_GEAR.get(), "Platinum Gear");

    // STEEL
        this.add(ModItems.STEEL_INGOT.get(), "Steel Ingot");
        this.add(ModBlocks.STEEL_BLOCK.get(), "Steel Block");
        this.add(ModItems.STEEL_NUGGET.get(), "Steel Nugget");
        this.add(ModItems.STEEL_DUST.get(), "Steel Dust");
        this.add(ModItems.STEEL_TINY_PILE.get(), "Tiny Pile Of Steel Dust");
        this.add(ModItems.STEEL_PLATE.get(), "Steel Plate");
        this.add(ModItems.STEEL_ROD.get(), "Steel Rod");
        this.add(ModItems.STEEL_ROD_POLARIZED.get(), "Steel Rod (Polarized)");
        this.add(ModItems.STEEL_GEAR.get(), "Steel Gear");

        this.add(ModArmors.STEEL_HELMET.get(), "Steel Helmet");
        this.add(ModArmors.STEEL_CHESTPLATE.get(), "Steel Chestplate");
        this.add(ModArmors.STEEL_LEGGINGS.get(), "Steel Leggings");
        this.add(ModArmors.STEEL_BOOTS.get(), "Steel Boots");

        this.add(ModTools.STEEL_SWORD.get(), "Steel Sword");
        this.add(ModTools.STEEL_SHOVEL.get(), "Steel Shovel");
        this.add(ModTools.STEEL_PICKAXE.get(), "Steel Pickaxe");
        this.add(ModTools.STEEL_AXE.get(), "Steel Axe");
        this.add(ModTools.STEEL_HOE.get(), "Steel Hoe");

    // LITHIUM
        this.add(ModItems.LITHIUM_INGOT.get(), "Lithium Ingot");
        this.add(ModBlocks.LITHIUM_BLOCK.get(), "Lithium Block");
        this.add(ModBlocks.LITHIUM_ORE.get(), "Lithium Ore");
        this.add(ModBlocks.DEEPSLATE_LITHIUM_ORE.get(), "Deepslate Lithium Ore");
        this.add(ModItems.RAW_LITHIUM.get(), "Raw Lithium");
        this.add(ModItems.LITHIUM_NUGGET.get(), "Lithium Nugget");
        this.add(ModItems.LITHIUM_DUST.get(), "Lithium Dust");
        this.add(ModItems.LITHIUM_TINY_PILE.get(), "Tiny Pile Of Lithium Dust");
        this.add(ModItems.LITHIUM_ORE_DUST.get(), "Lithium Ore Dust");
        this.add(ModItems.LITHIUM_PLATE.get(), "Lithium Plate");
        this.add(ModItems.LITHIUM_ROD.get(), "Lithium Rod");
        this.add(ModItems.LITHIUM_GEAR.get(), "Lithium Gear");

    // MAGNESIUM
        this.add(ModItems.MAGNESIUM_INGOT.get(), "Magnesium Ingot");
        this.add(ModBlocks.MAGNESIUM_BLOCK.get(), "Magnesium Block");
        this.add(ModBlocks.MAGNESIUM_ORE.get(), "Magnesium Ore");
        this.add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get(), "Deepslate Magnesium Ore");
        this.add(ModItems.RAW_MAGNESIUM.get(), "Raw Magnesium");
        this.add(ModItems.MAGNESIUM_NUGGET.get(), "Magnesium Nugget");
        this.add(ModItems.MAGNESIUM_DUST.get(), "Magnesium Dust");
        this.add(ModItems.MAGNESIUM_TINY_PILE.get(), "Tiny Pile Of Magnesium Dust");
        this.add(ModItems.MAGNESIUM_ORE_DUST.get(), "Magnesium Ore Dust");
        this.add(ModItems.MAGNESIUM_PLATE.get(), "Magnesium Plate");
        this.add(ModItems.MAGNESIUM_ROD.get(), "Magnesium Rod");
        this.add(ModItems.MAGNESIUM_GEAR.get(), "Magnesium Gear");

    // URANIUM
        this.add(ModItems.URANIUM_INGOT.get(), "Uranium Ingot");
        this.add(ModBlocks.URANIUM_BLOCK.get(), "Uranium Block");
        this.add(ModBlocks.URANIUM_ORE.get(), "Uranium Ore");
        this.add(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), "Deepslate Uranium Ore");
        this.add(ModItems.RAW_URANIUM.get(), "Raw Uranium");
        this.add(ModItems.URANIUM_NUGGET.get(), "Uranium Nugget");
        this.add(ModItems.URANIUM_DUST.get(), "Uranium Dust");
        this.add(ModItems.URANIUM_TINY_PILE.get(), "Tiny Pile Of Uranium Dust");
        this.add(ModItems.URANIUM_ORE_DUST.get(), "Uranium Ore Dust");
        this.add(ModItems.URANIUM_PLATE.get(), "Uranium Plate");
        this.add(ModItems.URANIUM_ROD.get(), "Uranium Rod");
        this.add(ModItems.URANIUM_GEAR.get(), "Uranium Gear");

    // LEAD
        this.add(ModItems.LEAD_INGOT.get(), "Lead Ingot");
        this.add(ModBlocks.LEAD_BLOCK.get(), "Lead Block");
        this.add(ModBlocks.LEAD_ORE.get(), "Lead Ore");
        this.add(ModBlocks.DEEPSLATE_LEAD_ORE.get(), "Deepslate Lead Ore");
        this.add(ModItems.RAW_LEAD.get(), "Raw Lead");
        this.add(ModItems.LEAD_NUGGET.get(), "Lead Nugget");
        this.add(ModItems.LEAD_DUST.get(), "Lead Dust");
        this.add(ModItems.LEAD_TINY_PILE.get(), "Tiny Pile Of Lead Dust");
        this.add(ModItems.LEAD_ORE_DUST.get(), "Lead Ore Dust");
        this.add(ModItems.LEAD_PLATE.get(), "Lead Plate");
        this.add(ModItems.LEAD_ROD.get(), "Lead Rod");
        this.add(ModItems.LEAD_GEAR.get(), "Lead Gear");

    // ZINC
        this.add(ModItems.ZINC_INGOT.get(), "Zinc Ingot");
        this.add(ModBlocks.ZINC_BLOCK.get(), "Zinc Block");
        this.add(ModBlocks.ZINC_ORE.get(), "Zinc Ore");
        this.add(ModBlocks.DEEPSLATE_ZINC_ORE.get(), "Deepslate Zinc Ore");
        this.add(ModItems.RAW_ZINC.get(), "Raw Zinc");
        this.add(ModItems.ZINC_NUGGET.get(), "Zinc Nugget");
        this.add(ModItems.ZINC_DUST.get(), "Zinc Dust");
        this.add(ModItems.ZINC_TINY_PILE.get(), "Tiny Pile Of Zinc Dust");
        this.add(ModItems.ZINC_ORE_DUST.get(), "Zinc Ore Dust");
        this.add(ModItems.ZINC_PLATE.get(), "Zinc Plate");
        this.add(ModItems.ZINC_ROD.get(), "Zinc Rod");
        this.add(ModItems.ZINC_GEAR.get(), "Zinc Gear");

    // CRYSTALS
        this.add(ModItems.RUBY.get(), "Ruby");
        this.add(ModItems.SAPPHIRE.get(), "Sapphire");
        this.add(ModItems.AQUAMARINE.get(), "Aquamarine");
        this.add(ModItems.JADE.get(), "Jade");
        this.add(ModItems.OPAL.get(), "Opal");
        this.add(ModItems.YELLOW_DIAMOND.get(), "Yellow Diamond");
        this.add(ModItems.AMBER.get(), "Amber");
        this.add(ModItems.TOPAZ.get(), "Topaz");
        this.add(ModItems.BERYLLIUM.get(), "Beryllium");
        this.add(ModItems.BIXBIT.get(), "Bixbit");
        this.add(ModItems.MALACHITE.get(), "Malachite");
        this.add(ModItems.ONYX.get(), "Onyx");
        this.add(ModItems.PERIDOT.get(), "Peridot");
        this.add(ModItems.MOON_STONE.get(), "Moon Stone");
        this.add(ModItems.SUN_STONE.get(), "Sun Stone");
        this.add(ModItems.CITRINE.get(), "Citrine");
        this.add(ModItems.DOLOMITE.get(), "Dolomite");
        this.add(ModItems.TANZANITE.get(), "Tanzanite");

        this.add(ModItems.RUBY_SEED.get(), "Ruby Seed");
        this.add(ModItems.SAPPHIRE_SEED.get(), "Sapphire Seed");
        this.add(ModItems.AQUAMARINE_SEED.get(), "Aquamarine Seed");
        this.add(ModItems.JADE_SEED.get(), "Jade Seed");
        this.add(ModItems.OPAL_SEED.get(), "Opal Seed");
        this.add(ModItems.YELLOW_DIAMOND_SEED.get(), "Yellow Diamond Seed");
        this.add(ModItems.AMBER_SEED.get(), "Amber Seed");
        this.add(ModItems.TOPAZ_SEED.get(), "Topaz Seed");
        this.add(ModItems.BERYLLIUM_SEED.get(), "Beryllium Seed");
        this.add(ModItems.BIXBIT_SEED.get(), "Bixbit Seed");
        this.add(ModItems.MALACHITE_SEED.get(), "Malachite Seed");
        this.add(ModItems.ONYX_SEED.get(), "Onyx Seed");
        this.add(ModItems.PERIDOT_SEED.get(), "Peridot Seed");
        this.add(ModItems.MOON_STONE_SEED.get(), "Moon Stone Seed");
        this.add(ModItems.SUN_STONE_SEED.get(), "Sun Stone Seed");
        this.add(ModItems.CITRINE_SEED.get(), "Citrine Seed");
        this.add(ModItems.DOLOMITE_SEED.get(), "Dolomite Seed");
        this.add(ModItems.TANZANITE_SEED.get(), "Tanzanite Seed");

        this.add(ModItems.RUBY_CHARGED.get(), "Ruby (Charged)");
        this.add(ModItems.SAPPHIRE_CHARGED.get(), "Sapphire (Charged)");
        this.add(ModItems.AQUAMARINE_CHARGED.get(), "Aquamarine (Charged)");
        this.add(ModItems.JADE_CHARGED.get(), "Jade (Charged)");
        this.add(ModItems.OPAL_CHARGED.get(), "Opal (Charged)");
        this.add(ModItems.YELLOW_DIAMOND_CHARGED.get(), "Yellow Diamond (Charged)");
        this.add(ModItems.AMBER_CHARGED.get(), "Amber (Charged)");
        this.add(ModItems.TOPAZ_CHARGED.get(), "Topaz (Charged)");
        this.add(ModItems.BERYLLIUM_CHARGED.get(), "Beryllium (Charged)");
        this.add(ModItems.BIXBIT_CHARGED.get(), "Bixbit (Charged)");
        this.add(ModItems.MALACHITE_CHARGED.get(), "Malachite (Charged)");
        this.add(ModItems.ONYX_CHARGED.get(), "Onyx (Charged)");
        this.add(ModItems.PERIDOT_CHARGED.get(), "Peridot (Charged)");
        this.add(ModItems.MOON_STONE_CHARGED.get(), "Moon Stone (Charged)");
        this.add(ModItems.SUN_STONE_CHARGED.get(), "Sun Stone (Charged)");
        this.add(ModItems.CITRINE_CHARGED.get(), "Citrine (Charged)");
        this.add(ModItems.DOLOMITE_CHARGED.get(), "Dolomite (Charged)");
        this.add(ModItems.TANZANITE_CHARGED.get(), "Tanzanite (Charged)");

        this.add(ModBlocks.RUBY_CRYSTALS.get(), "Ruby Crystals");
        this.add(ModBlocks.SAPPHIRE_CRYSTALS.get(), "Sapphire Crystals");
        this.add(ModBlocks.AQUAMARINE_CRYSTALS.get(), "Aquamarine Crystals");
        this.add(ModBlocks.JADE_CRYSTALS.get(), "Jade Crystals");
        this.add(ModBlocks.OPAL_CRYSTALS.get(), "Opal Crystals");
        this.add(ModBlocks.YELLOW_DIAMOND_CRYSTALS.get(), "Yellow Diamond Crystals");
        this.add(ModBlocks.AMBER_CRYSTALS.get(), "Amber Crystals");
        this.add(ModBlocks.TOPAZ_CRYSTALS.get(), "Topaz Crystals");
        this.add(ModBlocks.BERYLLIUM_CRYSTALS.get(), "Beryllium Crystals");
        this.add(ModBlocks.BIXBIT_CRYSTALS.get(), "Bixbit Crystals");
        this.add(ModBlocks.MALACHITE_CRYSTALS.get(), "Malachite Crystals");
        this.add(ModBlocks.ONYX_CRYSTALS.get(), "Onyx Crystals");
        this.add(ModBlocks.PERIDOT_CRYSTALS.get(), "Peridot Crystals");
        this.add(ModBlocks.MOON_STONE_CRYSTALS.get(), "Moon Stone Crystals");
        this.add(ModBlocks.SUN_STONE_CRYSTALS.get(), "Sun Stone Crystals");
        this.add(ModBlocks.CITRINE_CRYSTALS.get(), "Citrine Crystals");
        this.add(ModBlocks.DOLOMITE_CRYSTALS.get(), "Dolomite Crystals");
        this.add(ModBlocks.TANZANITE_CRYSTALS.get(), "Tanzanite Crystals");

    // MACHINES
        this.add(ModBlocks.GEMSTONE_GENERATOR.get(), "Gemstone Generator");
        this.add(ModBlocks.GEMSTONE_CELL.get(), "Gemstone Cell");
        this.add(ModBlocks.CRYSTAL_GROWER.get(), "Crystal Grower");
        this.add(ModBlocks.CRYSTAL_CHARGER.get(), "Crystal Charger");

        this.add(ModBlocks.ELECTRIC_FURNACE.get(), "Electric Furnace");
        this.add(ModBlocks.METAL_FORMER.get(), "Metal Former");
        this.add(ModBlocks.PULVERIZER.get(), "Pulverizer");
        this.add(ModBlocks.ALLOY_SMELTER.get(), "Alloy Smelter");
        this.add(ModBlocks.EXTRACTOR.get(), "Extractor");
        this.add(ModBlocks.ORE_WASHER.get(), "Ore Washer");
        this.add(ModBlocks.COBBLESTONE_GENERATOR.get(), "Cobblestone Generator");
        this.add(ModBlocks.SAWMILL.get(), "Sawmill");
        this.add(ModBlocks.POLARIZER.get(), "Polarizer");

        this.add(ModBlocks.SOLAR_PANEL.get(), "Solar Panel");
        this.add(ModBlocks.WATER_MILL.get(), "Water Mill");
        this.add(ModBlocks.WIND_TURBINE.get(), "Wind Turbine");

    // MENUS
        this.add("menu." + GemstonePower.MOD_ID + ".gemstone_generator", "Gemstone Generator");
        this.add("menu." + GemstonePower.MOD_ID + ".gemstone_cell", "Gemstone Cell");
        this.add("menu." + GemstonePower.MOD_ID + ".crystal_grower", "Crystal Grower");
        this.add("menu." + GemstonePower.MOD_ID + ".crystal_charger", "Crystal Charger");
        this.add("menu." + GemstonePower.MOD_ID + ".electric_furnace", "Electric Furnace");
        this.add("menu." + GemstonePower.MOD_ID + ".metal_former", "Metal Former");
        this.add("menu." + GemstonePower.MOD_ID + ".pulverizer", "Pulverizer");
        this.add("menu." + GemstonePower.MOD_ID + ".alloy_smelter", "Alloy Smelter");
        this.add("menu." + GemstonePower.MOD_ID + ".extractor", "Extractor");
        this.add("menu." + GemstonePower.MOD_ID + ".ore_washer", "Ore Washer");
        this.add("menu." + GemstonePower.MOD_ID + ".sawmill", "Sawmill");
        this.add("menu." + GemstonePower.MOD_ID + ".polarizer", "Polarizer");

        this.add("menu." + GemstonePower.MOD_ID + ".energy_shift_tip", "§8Press §bSHIFT §8to get more details");

        this.add("menu." + GemstonePower.MOD_ID + ".tier.standard", "Standard");
        this.add("menu." + GemstonePower.MOD_ID + ".tier.intermediate", "Intermediate");
        this.add("menu." + GemstonePower.MOD_ID + ".tier.advanced", "Advanced");
        this.add("menu." + GemstonePower.MOD_ID + ".tier.ultra", "Ultra");
        this.add("menu." + GemstonePower.MOD_ID + ".tier.extreme", "Extreme");

        this.add("menu." + GemstonePower.MOD_ID + ".config_screen", "Gemstone Power client config");
        this.add("menu." + GemstonePower.MOD_ID + ".config_screen.done", "Done");
        this.add("menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit", "Displayed energy unit");
        this.add("menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit.ge", "GE (Gemstone Energy)");
        this.add("menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit.fe", "FE (Forge Energy)");
        this.add("menu." + GemstonePower.MOD_ID + ".config_screen.energy_unit.rf", "RF (Redstone Flux)");

    // CABLES
        this.add(ModBlocks.COPPER_WIRE.get(), "Copper Wire");
        this.add(ModBlocks.COPPER_CABLE.get(), "Copper Cable");
        this.add(ModBlocks.ALUMINUM_WIRE.get(), "Aluminum Wire");
        this.add(ModBlocks.ALUMINUM_CABLE.get(), "Aluminum Cable");
        this.add(ModBlocks.TIN_WIRE.get(), "Tin Wire");
        this.add(ModBlocks.TIN_CABLE.get(), "Tin Cable");
        this.add(ModBlocks.ELECTRUM_WIRE.get(), "Electrum Wire");
        this.add(ModBlocks.ELECTRUM_CABLE.get(), "Electrum Cable");

    // MICS
        this.add(ModItems.INTERMEDIATE_UPGRADE.get(), "Intermediate Upgrade");
        this.add(ModItems.ADVANCED_UPGRADE.get(), "Advanced Upgrade");
        this.add(ModItems.ULTRA_UPGRADE.get(), "Ultra Upgrade");
        this.add(ModItems.EXTREME_UPGRADE.get(), "Extreme Upgrade");

        this.add(ModItems.PLATE_PRESET.get(), "Plate Preset");
        this.add(ModItems.ROD_PRESET.get(), "Rod Preset");
        this.add(ModItems.WIRE_PRESET.get(), "Wire Preset");
        this.add(ModItems.RESIN.get(), "Resin");
        this.add(ModItems.RUBBER.get(), "Rubber");
        this.add(ModItems.TREE_TAP.get(), "Tree Tap");
        this.add(ModItems.RESIN_OAK_SAPLING.get(), "Resin Oak Sapling");
        this.add(ModItems.RESIN_OAK_LOG.get(), "Resin Oak Log");
        this.add(ModItems.RESIN_OAK_LEAVES.get(), "Resin Oak Leaves");
        this.add(ModItems.COAL_DUST.get(), "Coal Dust");

    // DAMAGE TYPE
        this.add("death.attack.gemstonepower:electrocuted", "%s was electrocuted");
        this.add("death.attack.gemstonepower:radiation", "%s irradiated too much");

    // TRIM MATERIAL
        this.add("trim_material.gemstonepower.ruby", "Ruby Material");
        this.add("trim_material.gemstonepower.sapphire", "Sapphire Material");
        this.add("trim_material.gemstonepower.aquamarine", "Aquamarine Material");
        this.add("trim_material.gemstonepower.jade", "Jade Material");
        this.add("trim_material.gemstonepower.opal", "Opal Material");
        this.add("trim_material.gemstonepower.yellow_diamond", "Yellow Diamond Material");
        this.add("trim_material.gemstonepower.amber", "Amber Material");
        this.add("trim_material.gemstonepower.topaz", "Topaz Material");
        this.add("trim_material.gemstonepower.beryllium", "Beryllium Material");
        this.add("trim_material.gemstonepower.bixbit", "Bixbit Material");
        this.add("trim_material.gemstonepower.malachite", "Malachite Material");
        this.add("trim_material.gemstonepower.onyx", "Onyx Material");
        this.add("trim_material.gemstonepower.peridot", "Peridot Material");
        this.add("trim_material.gemstonepower.moon_stone", "Moon Stone Material");
        this.add("trim_material.gemstonepower.sun_stone", "Sun Stone Material");
        this.add("trim_material.gemstonepower.citrine", "Citrine Material");
        this.add("trim_material.gemstonepower.dolomite", "Dolomite Material");
        this.add("trim_material.gemstonepower.tanzanite", "Tanzanite Material");

        this.add("trim_material.gemstonepower.aluminum", "Aluminum Material");
        this.add("trim_material.gemstonepower.tin", "Tin Material");
        this.add("trim_material.gemstonepower.bronze", "Bronze Material");
        this.add("trim_material.gemstonepower.silver", "Silver Material");
        this.add("trim_material.gemstonepower.electrum", "Electrum Material");
        this.add("trim_material.gemstonepower.nickel", "Nickel Material");
        this.add("trim_material.gemstonepower.invar", "Invar Material");
        this.add("trim_material.gemstonepower.constantan", "Constantan Material");
        this.add("trim_material.gemstonepower.platinum", "Platinum Material");
        this.add("trim_material.gemstonepower.steel", "Steel Material");
        this.add("trim_material.gemstonepower.lithium", "Lithium Material");
        this.add("trim_material.gemstonepower.magnesium", "Magnesium Material");
        this.add("trim_material.gemstonepower.uranium", "Uranium Material");
        this.add("trim_material.gemstonepower.lead", "Lead Material");
        this.add("trim_material.gemstonepower.zinc", "Zinc Material");

    // ARROWS
        this.add(ModItems.RUBY_ARROW.get(), "Ruby Arrow");
        this.add(ModItems.SAPPHIRE_ARROW.get(), "Sapphire Arrow");
        this.add(ModItems.AQUAMARINE_ARROW.get(), "Aquamarine Arrow");
        this.add(ModItems.JADE_ARROW.get(), "Jade Arrow");
        this.add(ModItems.OPAL_ARROW.get(), "Opal Arrow");
        this.add(ModItems.YELLOW_DIAMOND_ARROW.get(), "Yellow Diamond Arrow");
        this.add(ModItems.AMBER_ARROW.get(), "Amber Arrow");
        this.add(ModItems.TOPAZ_ARROW.get(), "Topaz Arrow");
        this.add(ModItems.BERYLLIUM_ARROW.get(), "Beryllium Arrow");
        this.add(ModItems.BIXBIT_ARROW.get(), "Bixbit Arrow");
        this.add(ModItems.MALACHITE_ARROW.get(), "Malachite Arrow");
        this.add(ModItems.ONYX_ARROW.get(), "Onyx Arrow");
        this.add(ModItems.PERIDOT_ARROW.get(), "Peridot Arrow");
        this.add(ModItems.MOON_STONE_ARROW.get(), "Moon Stone Arrow");
        this.add(ModItems.SUN_STONE_ARROW.get(), "Sun Stone Arrow");
        this.add(ModItems.CITRINE_ARROW.get(), "Citrine Arrow");
        this.add(ModItems.DOLOMITE_ARROW.get(), "Dolomite Arrow");
        this.add(ModItems.TANZANITE_ARROW.get(), "Tanzanite Arrow");

        this.add(ModItems.CHARGED_RUBY_ARROW.get(), "Charged Ruby Arrow");
        this.add(ModItems.CHARGED_SAPPHIRE_ARROW.get(), "Charged Sapphire Arrow");
        this.add(ModItems.CHARGED_AQUAMARINE_ARROW.get(), "Charged Aquamarine Arrow");
        this.add(ModItems.CHARGED_JADE_ARROW.get(), "Charged Jade Arrow");
        this.add(ModItems.CHARGED_OPAL_ARROW.get(), "Charged Opal Arrow");
        this.add(ModItems.CHARGED_YELLOW_DIAMOND_ARROW.get(), "Charged Yellow Diamond Arrow");
        this.add(ModItems.CHARGED_AMBER_ARROW.get(), "Charged Amber Arrow");
        this.add(ModItems.CHARGED_TOPAZ_ARROW.get(), "Charged Topaz Arrow");
        this.add(ModItems.CHARGED_BERYLLIUM_ARROW.get(), "Charged Beryllium Arrow");
        this.add(ModItems.CHARGED_BIXBIT_ARROW.get(), "Charged Bixbit Arrow");
        this.add(ModItems.CHARGED_MALACHITE_ARROW.get(), "Charged Malachite Arrow");
        this.add(ModItems.CHARGED_ONYX_ARROW.get(), "Charged Onyx Arrow");
        this.add(ModItems.CHARGED_PERIDOT_ARROW.get(), "Charged Peridot Arrow");
        this.add(ModItems.CHARGED_MOON_STONE_ARROW.get(), "Charged Moon Stone Arrow");
        this.add(ModItems.CHARGED_SUN_STONE_ARROW.get(), "Charged Sun Stone Arrow");
        this.add(ModItems.CHARGED_CITRINE_ARROW.get(), "Charged Citrine Arrow");
        this.add(ModItems.CHARGED_DOLOMITE_ARROW.get(), "Charged Dolomite Arrow");
        this.add(ModItems.CHARGED_TANZANITE_ARROW.get(), "Charged Tanzanite Arrow");

    // ENTITIES
        this.add(ModEntities.RUBY_ARROW.get(), "Ruby Arrow");
        this.add(ModEntities.SAPPHIRE_ARROW.get(), "Sapphire Arrow");
        this.add(ModEntities.AQUAMARINE_ARROW.get(), "Aquamarine Arrow");
        this.add(ModEntities.JADE_ARROW.get(), "Jade Arrow");
        this.add(ModEntities.OPAL_ARROW.get(), "Opal Arrow");
        this.add(ModEntities.YELLOW_DIAMOND_ARROW.get(), "Yellow Diamond Arrow");
        this.add(ModEntities.AMBER_ARROW.get(), "Amber Arrow");
        this.add(ModEntities.TOPAZ_ARROW.get(), "Topaz Arrow");
        this.add(ModEntities.BERYLLIUM_ARROW.get(), "Beryllium Arrow");
        this.add(ModEntities.BIXBIT_ARROW.get(), "Bixbit Arrow");
        this.add(ModEntities.MALACHITE_ARROW.get(), "Malachite Arrow");
        this.add(ModEntities.ONYX_ARROW.get(), "Onyx Arrow");
        this.add(ModEntities.PERIDOT_ARROW.get(), "Peridot Arrow");
        this.add(ModEntities.MOON_STONE_ARROW.get(), "Moon Stone Arrow");
        this.add(ModEntities.SUN_STONE_ARROW.get(), "Sun Stone Arrow");
        this.add(ModEntities.CITRINE_ARROW.get(), "Citrine Arrow");
        this.add(ModEntities.DOLOMITE_ARROW.get(), "Dolomite Arrow");
        this.add(ModEntities.TANZANITE_ARROW.get(), "Tanzanite Arrow");

    // ADVANCEMENTS
        this.add("advancement." + GemstonePower.MOD_ID + ".root.title", "The seed of beginning");
        this.add("advancement." + GemstonePower.MOD_ID + ".root.description", "Obtain seed of any gem");
        this.add("advancement." + GemstonePower.MOD_ID + ".gemstone_generator.title", "Coal energy");
        this.add("advancement." + GemstonePower.MOD_ID + ".gemstone_generator.description", "Craft a Gemstone Generator");
        this.add("advancement." + GemstonePower.MOD_ID + ".gemstone_cell.title", "Shocking storage");
        this.add("advancement." + GemstonePower.MOD_ID + ".gemstone_cell.description", "Craft a Gemstone Cell");
        this.add("advancement." + GemstonePower.MOD_ID + ".alloy_smelter.title", "1 + 1 = alloy?");
        this.add("advancement." + GemstonePower.MOD_ID + ".alloy_smelter.description", "Craft an Alloy Smelter");
        this.add("advancement." + GemstonePower.MOD_ID + ".cobblestone_generator.title", "Skyblock vibes");
        this.add("advancement." + GemstonePower.MOD_ID + ".cobblestone_generator.description", "Craft a Cobblestone Generator");
        this.add("advancement." + GemstonePower.MOD_ID + ".crystal_charger.title", "Does it charge phones?");
        this.add("advancement." + GemstonePower.MOD_ID + ".crystal_charger.description", "Craft a Crystal Charger");
        this.add("advancement." + GemstonePower.MOD_ID + ".crystal_grower.title", "It grows!");
        this.add("advancement." + GemstonePower.MOD_ID + ".crystal_grower.description", "Craft a Crystal Grower");
        this.add("advancement." + GemstonePower.MOD_ID + ".electric_furnace.title", "Alternatives");
        this.add("advancement." + GemstonePower.MOD_ID + ".electric_furnace.description", "Craft a Electric Furnace");
        this.add("advancement." + GemstonePower.MOD_ID + ".extractor.title", "You know suqma?");
        this.add("advancement." + GemstonePower.MOD_ID + ".extractor.description", "Craft an Extractor");
        this.add("advancement." + GemstonePower.MOD_ID + ".metal_former.title", "Desired shape");
        this.add("advancement." + GemstonePower.MOD_ID + ".metal_former.description", "Craft a Metal Former");
        this.add("advancement." + GemstonePower.MOD_ID + ".ore_washer.title", "Time for bath!");
        this.add("advancement." + GemstonePower.MOD_ID + ".ore_washer.description", "Craft an Ore Washer");
        this.add("advancement." + GemstonePower.MOD_ID + ".polarizer.title", "Magnetic");
        this.add("advancement." + GemstonePower.MOD_ID + ".polarizer.description", "Craft a Polarizer");
        this.add("advancement." + GemstonePower.MOD_ID + ".pulverizer.title", "The finest (dust) of them all");
        this.add("advancement." + GemstonePower.MOD_ID + ".pulverizer.description", "Craft a Pulverizer");
        this.add("advancement." + GemstonePower.MOD_ID + ".sawmill.title", "I'm not a lumberjack!");
        this.add("advancement." + GemstonePower.MOD_ID + ".sawmill.description", "Craft a Sawmill");
        this.add("advancement." + GemstonePower.MOD_ID + ".solar_panel.title", "I have a bright idea");
        this.add("advancement." + GemstonePower.MOD_ID + ".solar_panel.description", "Craft a Solar Panel");
        this.add("advancement." + GemstonePower.MOD_ID + ".water_mill.title", "Splashy splash");
        this.add("advancement." + GemstonePower.MOD_ID + ".water_mill.description", "Craft a Water Mill");
        this.add("advancement." + GemstonePower.MOD_ID + ".wind_turbine.title", "Energy from thin air");
        this.add("advancement." + GemstonePower.MOD_ID + ".wind_turbine.description", "Craft a Wind Turbine");
        this.add("advancement." + GemstonePower.MOD_ID + ".intermediate_upgrade.title", "Go faster!");
        this.add("advancement." + GemstonePower.MOD_ID + ".intermediate_upgrade.description", "Craft an Intermediate Upgrade");
        this.add("advancement." + GemstonePower.MOD_ID + ".advanced_upgrade.title", "Advanced processing");
        this.add("advancement." + GemstonePower.MOD_ID + ".advanced_upgrade.description", "Craft an Advanced Upgrade");
        this.add("advancement." + GemstonePower.MOD_ID + ".ultra_upgrade.title", "Getting there");
        this.add("advancement." + GemstonePower.MOD_ID + ".ultra_upgrade.description", "Craft an Ultra Upgrade");
        this.add("advancement." + GemstonePower.MOD_ID + ".extreme_upgrade.title", "Extreme potential");
        this.add("advancement." + GemstonePower.MOD_ID + ".extreme_upgrade.description", "Craft an Extreme Upgrade");
        this.add("advancement." + GemstonePower.MOD_ID + ".gem.title", "Time for a big guy");
        this.add("advancement." + GemstonePower.MOD_ID + ".gem.description", "Grow a gem in Crystal Grower" );
        this.add("advancement." + GemstonePower.MOD_ID + ".charged_gem.title", "Gemstone Power!");
        this.add("advancement." + GemstonePower.MOD_ID + ".charged_gem.description", "Charge a gem in Crystal Charger" );
        this.add("advancement." + GemstonePower.MOD_ID + ".resin.title", "Blood of trees");
        this.add("advancement." + GemstonePower.MOD_ID + ".resin.description", "Obtain resin using tree tap");
        this.add("advancement." + GemstonePower.MOD_ID + ".rubber.title", "Correct my mistakes");
        this.add("advancement." + GemstonePower.MOD_ID + ".rubber.description", "Craft some rubber");
        this.add("advancement." + GemstonePower.MOD_ID + ".cable.title", "Is Covid 19 back?");
        this.add("advancement." + GemstonePower.MOD_ID + ".cable.description", "Isolate a wire using rubber");
    }
}
