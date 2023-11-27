package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.ReactorFrameBE;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.item.*;
import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, GemstonePower.MOD_ID);

    public static final DeferredHolder<Item, Item> AZURITE_CRYSTAL = ITEMS.register("azurite_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> AZURITE_CRYSTAL_CHARGED = ITEMS.register("azurite_crystal_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> AZURITE_CRYSTAL_SEED = ITEMS.register("azurite_crystal_seed", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> CUPRITE_CRYSTAL = ITEMS.register("cuprite_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CUPRITE_CRYSTAL_CHARGED = ITEMS.register("cuprite_crystal_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> CUPRITE_CRYSTAL_SEED = ITEMS.register("cuprite_crystal_seed", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> TREE_TAP = ITEMS.register("tree_tap", () -> new TreeTapItem(new Item.Properties().durability(64)));
    public static final DeferredHolder<Item, Item> RESIN_OAK_SAPLING = ITEMS.register("resin_oak_sapling", () -> new BlockItem(ModBlocks.RESIN_OAK_SAPLING.get(), new Item.Properties()));
    public static final DeferredHolder<Item, Item> RESIN_OAK_LOG = ITEMS.register("resin_oak_log", () -> new BlockItem(ModBlocks.RESIN_OAK_LOG.get(), new Item.Properties()));
    public static final DeferredHolder<Item, Item> RESIN_OAK_LEAVES = ITEMS.register("resin_oak_leaves", () -> new BlockItem(ModBlocks.RESIN_OAK_LEAVES.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> COAL_DUST = ITEMS.register("coal_dust", () -> new TintedItem(new Item.Properties(), Tints.COAL));

    public static final DeferredHolder<Item, Item> IRON_DUST = ITEMS.register("iron_dust", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final DeferredHolder<Item, Item> IRON_TINY_PILE = ITEMS.register("iron_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final DeferredHolder<Item, Item> IRON_ORE_DUST = ITEMS.register("iron_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final DeferredHolder<Item, Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final DeferredHolder<Item, Item> IRON_ROD = ITEMS.register("iron_rod", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final DeferredHolder<Item, Item> IRON_ROD_POLARIZED = ITEMS.register("iron_rod_polarized", () -> new ChargedCrystalItem(new Item.Properties(), Tints.IRON));
    public static final DeferredHolder<Item, Item> IRON_GEAR = ITEMS.register("iron_gear", () -> new TintedItem(new Item.Properties(), Tints.IRON));

    public static final DeferredHolder<Item, Item> GOLD_DUST = ITEMS.register("gold_dust", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final DeferredHolder<Item, Item> GOLD_TINY_PILE = ITEMS.register("gold_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final DeferredHolder<Item, Item> GOLD_ORE_DUST = ITEMS.register("gold_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final DeferredHolder<Item, Item> GOLD_PLATE = ITEMS.register("gold_plate", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final DeferredHolder<Item, Item> GOLD_ROD = ITEMS.register("gold_rod", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final DeferredHolder<Item, Item> GOLD_GEAR = ITEMS.register("gold_gear", () -> new TintedItem(new Item.Properties(), Tints.GOLD));

    public static final DeferredHolder<Item, Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> COPPER_DUST = ITEMS.register("copper_dust", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> COPPER_TINY_PILE = ITEMS.register("copper_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> COPPER_ORE_DUST = ITEMS.register("copper_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> COPPER_PLATE = ITEMS.register("copper_plate", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> COPPER_ROD = ITEMS.register("copper_rod", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> COPPER_GEAR = ITEMS.register("copper_gear", () -> new TintedItem(new Item.Properties(), Tints.COPPER));

    public static final DeferredHolder<Item, Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_BLOCK = ITEMS.register("aluminum_block", () -> new TintedBlockItem(ModBlocks.ALUMINUM_BLOCK.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_ORE = ITEMS.register("aluminum_ore", () -> new TintedBlockItem(ModBlocks.ALUMINUM_ORE.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> DEEPSLATE_ALUMINUM_ORE = ITEMS.register("deepslate_aluminum_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> RAW_ALUMINUM = ITEMS.register("raw_aluminum", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_DUST = ITEMS.register("aluminum_dust", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_TINY_PILE = ITEMS.register("aluminum_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_ORE_DUST = ITEMS.register("aluminum_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_PLATE = ITEMS.register("aluminum_plate", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_ROD = ITEMS.register("aluminum_rod", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_GEAR = ITEMS.register("aluminum_gear", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));

    public static final DeferredHolder<Item, Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_BLOCK = ITEMS.register("tin_block", () -> new TintedBlockItem(ModBlocks.TIN_BLOCK.get(), new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_ORE = ITEMS.register("tin_ore", () -> new TintedBlockItem(ModBlocks.TIN_ORE.get(), new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> DEEPSLATE_TIN_ORE = ITEMS.register("deepslate_tin_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_TIN_ORE.get(), new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> RAW_TIN = ITEMS.register("raw_tin", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_DUST = ITEMS.register("tin_dust", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_TINY_PILE = ITEMS.register("tin_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_ORE_DUST = ITEMS.register("tin_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_PLATE = ITEMS.register("tin_plate", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_ROD = ITEMS.register("tin_rod", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_GEAR = ITEMS.register("tin_gear", () -> new TintedItem(new Item.Properties(), Tints.TIN));

    public static final DeferredHolder<Item, Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final DeferredHolder<Item, Item> BRONZE_BLOCK = ITEMS.register("bronze_block", () -> new TintedBlockItem(ModBlocks.BRONZE_BLOCK.get(), new Item.Properties(), Tints.BRONZE));
    public static final DeferredHolder<Item, Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final DeferredHolder<Item, Item> BRONZE_DUST = ITEMS.register("bronze_dust", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final DeferredHolder<Item, Item> BRONZE_TINY_PILE = ITEMS.register("bronze_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final DeferredHolder<Item, Item> BRONZE_PLATE = ITEMS.register("bronze_plate", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final DeferredHolder<Item, Item> BRONZE_ROD = ITEMS.register("bronze_rod", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final DeferredHolder<Item, Item> BRONZE_GEAR = ITEMS.register("bronze_gear", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));

    public static final DeferredHolder<Item, Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> SILVER_BLOCK = ITEMS.register("silver_block", () -> new TintedBlockItem(ModBlocks.SILVER_BLOCK.get(), new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> SILVER_ORE = ITEMS.register("silver_ore", () -> new TintedBlockItem(ModBlocks.SILVER_ORE.get(), new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> DEEPSLATE_SILVER_ORE = ITEMS.register("deepslate_silver_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_SILVER_ORE.get(), new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> SILVER_DUST = ITEMS.register("silver_dust", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> SILVER_TINY_PILE = ITEMS.register("silver_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> SILVER_ORE_DUST = ITEMS.register("silver_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> SILVER_PLATE = ITEMS.register("silver_plate", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> SILVER_ROD = ITEMS.register("silver_rod", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final DeferredHolder<Item, Item> SILVER_GEAR = ITEMS.register("silver_gear", () -> new TintedItem(new Item.Properties(), Tints.SILVER));

    public static final DeferredHolder<Item, Item> ELECTRUM_INGOT = ITEMS.register("electrum_ingot", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final DeferredHolder<Item, Item> ELECTRUM_BLOCK = ITEMS.register("electrum_block", () -> new TintedBlockItem(ModBlocks.ELECTRUM_BLOCK.get(), new Item.Properties(), Tints.ELECTRUM));
    public static final DeferredHolder<Item, Item> ELECTRUM_NUGGET = ITEMS.register("electrum_nugget", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final DeferredHolder<Item, Item> ELECTRUM_DUST = ITEMS.register("electrum_dust", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final DeferredHolder<Item, Item> ELECTRUM_TINY_PILE = ITEMS.register("electrum_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final DeferredHolder<Item, Item> ELECTRUM_PLATE = ITEMS.register("electrum_plate", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final DeferredHolder<Item, Item> ELECTRUM_ROD = ITEMS.register("electrum_rod", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final DeferredHolder<Item, Item> ELECTRUM_GEAR = ITEMS.register("electrum_gear", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));

    public static final DeferredHolder<Item, Item> NICKEL_INGOT = ITEMS.register("nickel_ingot", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_BLOCK = ITEMS.register("nickel_block", () -> new TintedBlockItem(ModBlocks.NICKEL_BLOCK.get(), new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_ORE = ITEMS.register("nickel_ore", () -> new TintedBlockItem(ModBlocks.NICKEL_ORE.get(), new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> DEEPSLATE_NICKEL_ORE = ITEMS.register("deepslate_nickel_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> RAW_NICKEL = ITEMS.register("raw_nickel", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_NUGGET = ITEMS.register("nickel_nugget", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_DUST = ITEMS.register("nickel_dust", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_TINY_PILE = ITEMS.register("nickel_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_ORE_DUST = ITEMS.register("nickel_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_PLATE = ITEMS.register("nickel_plate", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_ROD = ITEMS.register("nickel_rod", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_ROD_POLARIZED = ITEMS.register("nickel_rod_polarized", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NICKEL));
    public static final DeferredHolder<Item, Item> NICKEL_GEAR = ITEMS.register("nickel_gear", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));

    public static final DeferredHolder<Item, Item> INVAR_INGOT = ITEMS.register("invar_ingot", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final DeferredHolder<Item, Item> INVAR_BLOCK = ITEMS.register("invar_block", () -> new TintedBlockItem(ModBlocks.INVAR_BLOCK.get(), new Item.Properties(), Tints.INVAR));
    public static final DeferredHolder<Item, Item> INVAR_NUGGET = ITEMS.register("invar_nugget", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final DeferredHolder<Item, Item> INVAR_DUST = ITEMS.register("invar_dust", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final DeferredHolder<Item, Item> INVAR_TINY_PILE = ITEMS.register("invar_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final DeferredHolder<Item, Item> INVAR_PLATE = ITEMS.register("invar_plate", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final DeferredHolder<Item, Item> INVAR_ROD = ITEMS.register("invar_rod", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final DeferredHolder<Item, Item> INVAR_ROD_POLARIZED = ITEMS.register("invar_rod_polarized", () -> new ChargedCrystalItem(new Item.Properties(), Tints.INVAR));
    public static final DeferredHolder<Item, Item> INVAR_GEAR = ITEMS.register("invar_gear", () -> new TintedItem(new Item.Properties(), Tints.INVAR));

    public static final DeferredHolder<Item, Item> CONSTANTAN_INGOT = ITEMS.register("constantan_ingot", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final DeferredHolder<Item, Item> CONSTANTAN_BLOCK = ITEMS.register("constantan_block", () -> new TintedBlockItem(ModBlocks.CONSTANTAN_BLOCK.get(), new Item.Properties(), Tints.CONSTANTAN));
    public static final DeferredHolder<Item, Item> CONSTANTAN_NUGGET = ITEMS.register("constantan_nugget", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final DeferredHolder<Item, Item> CONSTANTAN_DUST = ITEMS.register("constantan_dust", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final DeferredHolder<Item, Item> CONSTANTAN_TINY_PILE = ITEMS.register("constantan_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final DeferredHolder<Item, Item> CONSTANTAN_PLATE = ITEMS.register("constantan_plate", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final DeferredHolder<Item, Item> CONSTANTAN_ROD = ITEMS.register("constantan_rod", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final DeferredHolder<Item, Item> CONSTANTAN_ROD_POLARIZED = ITEMS.register("constantan_rod_polarized", () -> new ChargedCrystalItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final DeferredHolder<Item, Item> CONSTANTAN_GEAR = ITEMS.register("constantan_gear", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));

    public static final DeferredHolder<Item, Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> PLATINUM_BLOCK = ITEMS.register("platinum_block", () -> new TintedBlockItem(ModBlocks.PLATINUM_BLOCK.get(), new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> PLATINUM_ORE = ITEMS.register("platinum_ore", () -> new TintedBlockItem(ModBlocks.PLATINUM_ORE.get(), new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> DEEPSLATE_PLATINUM_ORE = ITEMS.register("deepslate_platinum_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> RAW_PLATINUM = ITEMS.register("raw_platinum", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> PLATINUM_DUST = ITEMS.register("platinum_dust", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> PLATINUM_TINY_PILE = ITEMS.register("platinum_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> PLATINUM_ORE_DUST = ITEMS.register("platinum_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> PLATINUM_PLATE = ITEMS.register("platinum_plate", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> PLATINUM_ROD = ITEMS.register("platinum_rod", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> PLATINUM_GEAR = ITEMS.register("platinum_gear", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));

    public static final DeferredHolder<Item, Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final DeferredHolder<Item, Item> STEEL_BLOCK = ITEMS.register("steel_block", () -> new TintedBlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties(), Tints.STEEL));
    public static final DeferredHolder<Item, Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final DeferredHolder<Item, Item> STEEL_DUST = ITEMS.register("steel_dust", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final DeferredHolder<Item, Item> STEEL_TINY_PILE = ITEMS.register("steel_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final DeferredHolder<Item, Item> STEEL_PLATE = ITEMS.register("steel_plate", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final DeferredHolder<Item, Item> STEEL_ROD = ITEMS.register("steel_rod", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final DeferredHolder<Item, Item> STEEL_ROD_POLARIZED = ITEMS.register("steel_rod_polarized", () -> new ChargedCrystalItem(new Item.Properties(), Tints.STEEL));
    public static final DeferredHolder<Item, Item> STEEL_GEAR = ITEMS.register("steel_gear", () -> new TintedItem(new Item.Properties(), Tints.STEEL));

    public static final DeferredHolder<Item, Item> LITHIUM_INGOT = ITEMS.register("lithium_ingot", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> LITHIUM_BLOCK = ITEMS.register("lithium_block", () -> new TintedBlockItem(ModBlocks.LITHIUM_BLOCK.get(), new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> LITHIUM_ORE = ITEMS.register("lithium_ore", () -> new TintedBlockItem(ModBlocks.LITHIUM_ORE.get(), new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> DEEPSLATE_LITHIUM_ORE = ITEMS.register("deepslate_lithium_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_LITHIUM_ORE.get(), new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> RAW_LITHIUM = ITEMS.register("raw_lithium", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> LITHIUM_NUGGET = ITEMS.register("lithium_nugget", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> LITHIUM_DUST = ITEMS.register("lithium_dust", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> LITHIUM_TINY_PILE = ITEMS.register("lithium_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> LITHIUM_ORE_DUST = ITEMS.register("lithium_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> LITHIUM_PLATE = ITEMS.register("lithium_plate", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> LITHIUM_ROD = ITEMS.register("lithium_rod", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final DeferredHolder<Item, Item> LITHIUM_GEAR = ITEMS.register("lithium_gear", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));

    public static final DeferredHolder<Item, Item> MAGNESIUM_INGOT = ITEMS.register("magnesium_ingot", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> MAGNESIUM_BLOCK = ITEMS.register("magnesium_block", () -> new TintedBlockItem(ModBlocks.MAGNESIUM_BLOCK.get(), new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> MAGNESIUM_ORE = ITEMS.register("magnesium_ore", () -> new TintedBlockItem(ModBlocks.MAGNESIUM_ORE.get(), new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> DEEPSLATE_MAGNESIUM_ORE = ITEMS.register("deepslate_magnesium_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get(), new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> RAW_MAGNESIUM = ITEMS.register("raw_magnesium", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> MAGNESIUM_NUGGET = ITEMS.register("magnesium_nugget", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> MAGNESIUM_DUST = ITEMS.register("magnesium_dust", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> MAGNESIUM_TINY_PILE = ITEMS.register("magnesium_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> MAGNESIUM_ORE_DUST = ITEMS.register("magnesium_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> MAGNESIUM_PLATE = ITEMS.register("magnesium_plate", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> MAGNESIUM_ROD = ITEMS.register("magnesium_rod", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final DeferredHolder<Item, Item> MAGNESIUM_GEAR = ITEMS.register("magnesium_gear", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));

    public static final DeferredHolder<Item, Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> URANIUM_BLOCK = ITEMS.register("uranium_block", () -> new RadioactiveBlockItem(ModBlocks.URANIUM_BLOCK.get(), new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> URANIUM_ORE = ITEMS.register("uranium_ore", () -> new RadioactiveBlockItem(ModBlocks.URANIUM_ORE.get(), new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> DEEPSLATE_URANIUM_ORE = ITEMS.register("deepslate_uranium_ore", () -> new RadioactiveBlockItem(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> RAW_URANIUM = ITEMS.register("raw_uranium", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> URANIUM_NUGGET = ITEMS.register("uranium_nugget", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> URANIUM_DUST = ITEMS.register("uranium_dust", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> URANIUM_TINY_PILE = ITEMS.register("uranium_tiny_pile", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> URANIUM_ORE_DUST = ITEMS.register("uranium_ore_dust", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> URANIUM_PLATE = ITEMS.register("uranium_plate", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> URANIUM_ROD = ITEMS.register("uranium_rod", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final DeferredHolder<Item, Item> URANIUM_GEAR = ITEMS.register("uranium_gear", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));

    public static final DeferredHolder<Item, Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> LEAD_BLOCK = ITEMS.register("lead_block", () -> new TintedBlockItem(ModBlocks.LEAD_BLOCK.get(), new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> LEAD_ORE = ITEMS.register("lead_ore", () -> new TintedBlockItem(ModBlocks.LEAD_ORE.get(), new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> DEEPSLATE_LEAD_ORE = ITEMS.register("deepslate_lead_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_LEAD_ORE.get(), new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> RAW_LEAD = ITEMS.register("raw_lead", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> LEAD_NUGGET = ITEMS.register("lead_nugget", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> LEAD_DUST = ITEMS.register("lead_dust", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> LEAD_TINY_PILE = ITEMS.register("lead_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> LEAD_ORE_DUST = ITEMS.register("lead_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> LEAD_PLATE = ITEMS.register("lead_plate", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> LEAD_ROD = ITEMS.register("lead_rod", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final DeferredHolder<Item, Item> LEAD_GEAR = ITEMS.register("lead_gear", () -> new TintedItem(new Item.Properties(), Tints.LEAD));

    public static final DeferredHolder<Item, Item> ZINC_INGOT = ITEMS.register("zinc_ingot", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> ZINC_BLOCK = ITEMS.register("zinc_block", () -> new TintedBlockItem(ModBlocks.ZINC_BLOCK.get(), new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> ZINC_ORE = ITEMS.register("zinc_ore", () -> new TintedBlockItem(ModBlocks.ZINC_ORE.get(), new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> DEEPSLATE_ZINC_ORE = ITEMS.register("deepslate_zinc_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_ZINC_ORE.get(), new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> RAW_ZINC = ITEMS.register("raw_zinc", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> ZINC_NUGGET = ITEMS.register("zinc_nugget", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> ZINC_DUST = ITEMS.register("zinc_dust", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> ZINC_TINY_PILE = ITEMS.register("zinc_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> ZINC_ORE_DUST = ITEMS.register("zinc_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> ZINC_PLATE = ITEMS.register("zinc_plate", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> ZINC_ROD = ITEMS.register("zinc_rod", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final DeferredHolder<Item, Item> ZINC_GEAR = ITEMS.register("zinc_gear", () -> new TintedItem(new Item.Properties(), Tints.ZINC));

    public static final DeferredHolder<Item, Item> GEMSTONE_GENERATOR = ITEMS.register("gemstone_generator", () -> new BlockItem(ModBlocks.GEMSTONE_GENERATOR.get(), new Item.Properties()));
    public static final DeferredHolder<Item, Item> GEMSTONE_CELL = ITEMS.register("gemstone_cell", () -> new BlockItem(ModBlocks.GEMSTONE_CELL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> CRYSTAL_GROWER = ITEMS.register("crystal_grower", () -> new TintedBlockItem(ModBlocks.CRYSTAL_GROWER.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> CRYSTAL_CHARGER = ITEMS.register("crystal_charger", () -> new TintedBlockItem(ModBlocks.CRYSTAL_CHARGER.get(), new Item.Properties(), Tints.STANDARD));

    public static final DeferredHolder<Item, Item> ELECTRIC_FURNACE = ITEMS.register("electric_furnace", () -> new TintedBlockItem(ModBlocks.ELECTRIC_FURNACE.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> METAL_FORMER = ITEMS.register("metal_former", () -> new TintedBlockItem(ModBlocks.METAL_FORMER.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> PULVERIZER = ITEMS.register("pulverizer", () -> new TintedBlockItem(ModBlocks.PULVERIZER.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> ALLOY_SMELTER = ITEMS.register("alloy_smelter", () -> new TintedBlockItem(ModBlocks.ALLOY_SMELTER.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> EXTRACTOR = ITEMS.register("extractor", () -> new TintedBlockItem(ModBlocks.EXTRACTOR.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> ORE_WASHER = ITEMS.register("ore_washer", () -> new TintedBlockItem(ModBlocks.ORE_WASHER.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> COBBLESTONE_GENERATOR = ITEMS.register("cobblestone_generator", () -> new TintedBlockItem(ModBlocks.COBBLESTONE_GENERATOR.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> SAWMILL = ITEMS.register("sawmill", () -> new TintedBlockItem(ModBlocks.SAWMILL.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> POLARIZER = ITEMS.register("polarizer", () -> new TintedBlockItem(ModBlocks.POLARIZER.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> GEMSTONE_MANIPULATOR = ITEMS.register("gemstone_manipulator", () -> new TintedBlockItem(ModBlocks.GEMSTONE_MANIPULATOR.get(), new Item.Properties(), Tints.STANDARD));

    public static final DeferredHolder<Item, Item> SOLAR_PANEL = ITEMS.register("solar_panel", () -> new TintedBlockItem(ModBlocks.SOLAR_PANEL.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> WATER_MILL = ITEMS.register("water_mill", () -> new TintedBlockItem(ModBlocks.WATER_MILL.get(), new Item.Properties(), Tints.STANDARD));
    public static final DeferredHolder<Item, Item> WIND_TURBINE = ITEMS.register("wind_turbine", () -> new TintedBlockItem(ModBlocks.WIND_TURBINE.get(), new Item.Properties(), Tints.STANDARD));

    public static final DeferredHolder<Item, Item> TANK = ITEMS.register("tank", () -> new TankItem(new Item.Properties()));
    public static final DeferredHolder<Item, Item> PORTABLE_TANK = ITEMS.register("portable_tank", () -> new PortableTank(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FISSION_REACTOR = ITEMS.register("fission_reactor", () -> new BlockItem(ModBlocks.FISSION_REACTOR.get(), new Item.Properties()));
    public static final DeferredHolder<Item, Item> REACTOR_WALL = ITEMS.register("reactor_wall", () -> new BlockItem(ModBlocks.REACTOR_WALL.get(), new Item.Properties()));
    public static final DeferredHolder<Item, Item> REACTOR_FRAME = ITEMS.register("reactor_frame", () -> new BlockItem(ModBlocks.REACTOR_FRAME.get(), new Item.Properties()));
    public static final DeferredHolder<Item, Item> WATER_COOLING = ITEMS.register("water_cooling", () -> new CoolingBlockItem(ModBlocks.WATER_COOLING.get(), new Item.Properties(), ReactorFrameBE.Type.WATER.getCooling()));
    public static final DeferredHolder<Item, Item> SNOW_COOLING = ITEMS.register("snow_cooling", () -> new CoolingBlockItem(ModBlocks.SNOW_COOLING.get(), new Item.Properties(), ReactorFrameBE.Type.SNOW.getCooling()));
    public static final DeferredHolder<Item, Item> ICE_COOLING = ITEMS.register("ice_cooling", () -> new CoolingBlockItem(ModBlocks.ICE_COOLING.get(), new Item.Properties(), ReactorFrameBE.Type.ICE.getCooling()));
    public static final DeferredHolder<Item, Item> PACKED_ICE_COOLING = ITEMS.register("packed_ice_cooling", () -> new CoolingBlockItem(ModBlocks.PACKED_ICE_COOLING.get(), new Item.Properties(), ReactorFrameBE.Type.PACKED_ICE.getCooling()));
    public static final DeferredHolder<Item, Item> BLUE_ICE_COOLING = ITEMS.register("blue_ice_cooling", () -> new CoolingBlockItem(ModBlocks.BLUE_ICE_COOLING.get(), new Item.Properties(), ReactorFrameBE.Type.BLUE_ICE.getCooling()));
    public static final DeferredHolder<Item, Item> PRISMARINE_COOLING = ITEMS.register("prismarine_cooling", () -> new CoolingBlockItem(ModBlocks.PRISMARINE_COOLING.get(), new Item.Properties(), ReactorFrameBE.Type.PRISMARINE.getCooling()));

    public static final DeferredHolder<Item, Item> METAL_WRENCH = ITEMS.register("metal_wrench", () -> new WrenchItem(new Item.Properties(), 128));
    public static final DeferredHolder<Item, Item> CRYSTAL_WRENCH = ITEMS.register("crystal_wrench", () -> new WrenchItem(new Item.Properties(), 1024));

    public static final DeferredHolder<Item, Item> RESIN = ITEMS.register("resin", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> RUBBER = ITEMS.register("rubber", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> INTERMEDIATE_UPGRADE = ITEMS.register("intermediate_upgrade", () -> new UpgradeItem(new Item.Properties(), Tier.INTERMEDIATE, Tints.INTERMEDIATE));
    public static final DeferredHolder<Item, Item> ADVANCED_UPGRADE = ITEMS.register("advanced_upgrade", () -> new UpgradeItem(new Item.Properties(), Tier.ADVANCED, Tints.ADVANCED));
    public static final DeferredHolder<Item, Item> ULTRA_UPGRADE = ITEMS.register("ultra_upgrade", () -> new UpgradeItem(new Item.Properties(), Tier.ULTRA, Tints.ULTRA));
    public static final DeferredHolder<Item, Item> EXTREME_UPGRADE = ITEMS.register("extreme_upgrade", () -> new UpgradeItem(new Item.Properties(), Tier.EXTREME, Tints.EXTREME));

    public static final DeferredHolder<Item, Item> COPPER_WIRE = ITEMS.register("copper_wire", () -> new TintedBlockItem(ModBlocks.COPPER_WIRE.get(), new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> COPPER_CABLE = ITEMS.register("copper_cable", () -> new TintedBlockItem(ModBlocks.COPPER_CABLE.get(), new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> ALUMINUM_WIRE = ITEMS.register("aluminum_wire", () -> new TintedBlockItem(ModBlocks.ALUMINUM_WIRE.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> ALUMINUM_CABLE = ITEMS.register("aluminum_cable", () -> new TintedBlockItem(ModBlocks.ALUMINUM_CABLE.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final DeferredHolder<Item, Item> TIN_WIRE = ITEMS.register("tin_wire", () -> new TintedBlockItem(ModBlocks.TIN_WIRE.get(), new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> TIN_CABLE = ITEMS.register("tin_cable", () -> new TintedBlockItem(ModBlocks.TIN_CABLE.get(), new Item.Properties(), Tints.TIN));
    public static final DeferredHolder<Item, Item> ELECTRUM_WIRE = ITEMS.register("electrum_wire", () -> new TintedBlockItem(ModBlocks.ELECTRUM_WIRE.get(), new Item.Properties(), Tints.ELECTRUM));
    public static final DeferredHolder<Item, Item> ELECTRUM_CABLE = ITEMS.register("electrum_cable", () -> new TintedBlockItem(ModBlocks.ELECTRUM_CABLE.get(), new Item.Properties(), Tints.ELECTRUM));
    public static final DeferredHolder<Item, Item> GEMSTONE_CABLE = ITEMS.register("gemstone_cable", () -> new BlockItem(ModBlocks.GEMSTONE_CABLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> IRON_ITEM_PIPE = ITEMS.register("iron_item_pipe", () -> new TintedBlockItem(ModBlocks.IRON_ITEM_PIPE.get(), new Item.Properties(), Tints.IRON));
    public static final DeferredHolder<Item, Item> GOLD_ITEM_PIPE = ITEMS.register("gold_item_pipe", () -> new TintedBlockItem(ModBlocks.GOLD_ITEM_PIPE.get(), new Item.Properties(), Tints.GOLD));
    public static final DeferredHolder<Item, Item> COPPER_ITEM_PIPE = ITEMS.register("copper_item_pipe", () -> new TintedBlockItem(ModBlocks.COPPER_ITEM_PIPE.get(), new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> PLATINUM_ITEM_PIPE = ITEMS.register("platinum_item_pipe", () -> new TintedBlockItem(ModBlocks.PLATINUM_ITEM_PIPE.get(), new Item.Properties(), Tints.PLATINUM));
    public static final DeferredHolder<Item, Item> LEAD_ITEM_PIPE = ITEMS.register("lead_item_pipe", () -> new TintedBlockItem(ModBlocks.LEAD_ITEM_PIPE.get(), new Item.Properties(), Tints.LEAD));

    public static final DeferredHolder<Item, Item> GOLD_FLUID_PIPE = ITEMS.register("gold_fluid_pipe", () -> new TintedBlockItem(ModBlocks.GOLD_FLUID_PIPE.get(), new Item.Properties(), Tints.GOLD));
    public static final DeferredHolder<Item, Item> COPPER_FLUID_PIPE = ITEMS.register("copper_fluid_pipe", () -> new TintedBlockItem(ModBlocks.COPPER_FLUID_PIPE.get(), new Item.Properties(), Tints.COPPER));
    public static final DeferredHolder<Item, Item> INVAR_FLUID_PIPE = ITEMS.register("invar_fluid_pipe", () -> new TintedBlockItem(ModBlocks.INVAR_FLUID_PIPE.get(), new Item.Properties(), Tints.INVAR));
    public static final DeferredHolder<Item, Item> STEEL_FLUID_PIPE = ITEMS.register("steel_fluid_pipe", () -> new TintedBlockItem(ModBlocks.STEEL_FLUID_PIPE.get(), new Item.Properties(), Tints.STEEL));

    public static final DeferredHolder<Item, Item> LIGHT_GEMSTONE = ITEMS.register("light_gemstone", () -> new CrystalItem(new Item.Properties(), MobEffects.HEALTH_BOOST));
    public static final DeferredHolder<Item, Item> DARK_GEMSTONE = ITEMS.register("dark_gemstone", () -> new CrystalItem(new Item.Properties(), MobEffects.SATURATION));
    public static final DeferredHolder<Item, Item> FORTUNE_CRYSTAL = ITEMS.register("fortune_crystal", () -> new FortuneCrystal(new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, Item> STONE_OF_KNOWLEDGE = ITEMS.register("stone_of_knowledge", () -> new AwakeningStoneItem(new Item.Properties(), true));
    public static final DeferredHolder<Item, Item> STONE_OF_OBLIVION = ITEMS.register("stone_of_oblivion", () -> new AwakeningStoneItem(new Item.Properties(), false));

    public static final DeferredHolder<Item, Item> RUBY = ITEMS.register("ruby", () -> new CrystalItem(new Item.Properties(), MobEffects.REGENERATION));
    public static final DeferredHolder<Item, Item> SAPPHIRE = ITEMS.register("sapphire", () -> new CrystalItem(new Item.Properties(), MobEffects.DOLPHINS_GRACE));
    public static final DeferredHolder<Item, Item> AQUAMARINE = ITEMS.register("aquamarine", () -> new CrystalItem(new Item.Properties(), MobEffects.WATER_BREATHING));
    public static final DeferredHolder<Item, Item> JADE = ITEMS.register("jade", () -> new CrystalItem(new Item.Properties(), MobEffects.MOVEMENT_SPEED));
    public static final DeferredHolder<Item, Item> OPAL = ITEMS.register("opal", () -> new CrystalItem(new Item.Properties(), MobEffects.MOVEMENT_SLOWDOWN));
    public static final DeferredHolder<Item, Item> YELLOW_DIAMOND = ITEMS.register("yellow_diamond", () -> new CrystalItem(new Item.Properties(), MobEffects.DIG_SPEED));
    public static final DeferredHolder<Item, Item> AMBER = ITEMS.register("amber", () -> new CrystalItem(new Item.Properties(), MobEffects.FIRE_RESISTANCE));
    public static final DeferredHolder<Item, Item> TOPAZ = ITEMS.register("topaz", () -> new CrystalItem(new Item.Properties(), MobEffects.INVISIBILITY));
    public static final DeferredHolder<Item, Item> BERYLLIUM = ITEMS.register("beryllium", () -> new CrystalItem(new Item.Properties(), MobEffects.GLOWING));
    public static final DeferredHolder<Item, Item> BIXBIT = ITEMS.register("bixbit", () -> new CrystalItem(new Item.Properties(), MobEffects.JUMP));
    public static final DeferredHolder<Item, Item> MALACHITE = ITEMS.register("malachite", () -> new CrystalItem(new Item.Properties(), MobEffects.LUCK));
    public static final DeferredHolder<Item, Item> ONYX = ITEMS.register("onyx", () -> new CrystalItem(new Item.Properties(), MobEffects.NIGHT_VISION));
    public static final DeferredHolder<Item, Item> PERIDOT = ITEMS.register("peridot", () -> new CrystalItem(new Item.Properties(), MobEffects.WEAKNESS));
    public static final DeferredHolder<Item, Item> MOON_STONE = ITEMS.register("moon_stone", () -> new CrystalItem(new Item.Properties(), MobEffects.UNLUCK));
    public static final DeferredHolder<Item, Item> SUN_STONE = ITEMS.register("sun_stone", () -> new CrystalItem(new Item.Properties(), MobEffects.HUNGER));
    public static final DeferredHolder<Item, Item> CITRINE = ITEMS.register("citrine", () -> new CrystalItem(new Item.Properties(), MobEffects.POISON));
    public static final DeferredHolder<Item, Item> DOLOMITE = ITEMS.register("dolomite", () -> new CrystalItem(new Item.Properties(), MobEffects.BLINDNESS));
    public static final DeferredHolder<Item, Item> TANZANITE = ITEMS.register("tanzanite", () -> new CrystalItem(new Item.Properties(), MobEffects.DAMAGE_BOOST));

    public static final DeferredHolder<Item, Item> RUBY_SEED = ITEMS.register("ruby_seed", () -> new CrystalItem(new Item.Properties(), Tints.RUBY));
    public static final DeferredHolder<Item, Item> SAPPHIRE_SEED = ITEMS.register("sapphire_seed", () -> new CrystalItem(new Item.Properties(), Tints.SAPPHIRE));
    public static final DeferredHolder<Item, Item> AQUAMARINE_SEED = ITEMS.register("aquamarine_seed", () -> new CrystalItem(new Item.Properties(), Tints.AQUAMARINE));
    public static final DeferredHolder<Item, Item> JADE_SEED = ITEMS.register("jade_seed", () -> new CrystalItem(new Item.Properties(), Tints.JADE));
    public static final DeferredHolder<Item, Item> OPAL_SEED = ITEMS.register("opal_seed", () -> new CrystalItem(new Item.Properties(), Tints.OPAL));
    public static final DeferredHolder<Item, Item> YELLOW_DIAMOND_SEED = ITEMS.register("yellow_diamond_seed", () -> new CrystalItem(new Item.Properties(), Tints.YELLOW_DIAMOND));
    public static final DeferredHolder<Item, Item> AMBER_SEED = ITEMS.register("amber_seed", () -> new CrystalItem(new Item.Properties(), Tints.AMBER));
    public static final DeferredHolder<Item, Item> TOPAZ_SEED = ITEMS.register("topaz_seed", () -> new CrystalItem(new Item.Properties(), Tints.TOPAZ));
    public static final DeferredHolder<Item, Item> BERYLLIUM_SEED = ITEMS.register("beryllium_seed", () -> new CrystalItem(new Item.Properties(), Tints.BERYLLIUM));
    public static final DeferredHolder<Item, Item> BIXBIT_SEED = ITEMS.register("bixbit_seed", () -> new CrystalItem(new Item.Properties(), Tints.BIXBIT));
    public static final DeferredHolder<Item, Item> MALACHITE_SEED = ITEMS.register("malachite_seed", () -> new CrystalItem(new Item.Properties(), Tints.MALACHITE));
    public static final DeferredHolder<Item, Item> ONYX_SEED = ITEMS.register("onyx_seed", () -> new CrystalItem(new Item.Properties(), Tints.ONYX));
    public static final DeferredHolder<Item, Item> PERIDOT_SEED = ITEMS.register("peridot_seed", () -> new CrystalItem(new Item.Properties(), Tints.PERIDOT));
    public static final DeferredHolder<Item, Item> MOON_STONE_SEED = ITEMS.register("moon_stone_seed", () -> new CrystalItem(new Item.Properties(), Tints.MOON_STONE));
    public static final DeferredHolder<Item, Item> SUN_STONE_SEED = ITEMS.register("sun_stone_seed", () -> new CrystalItem(new Item.Properties(), Tints.SUN_STONE));
    public static final DeferredHolder<Item, Item> CITRINE_SEED = ITEMS.register("citrine_seed", () -> new CrystalItem(new Item.Properties(), Tints.CITRINE));
    public static final DeferredHolder<Item, Item> DOLOMITE_SEED = ITEMS.register("dolomite_seed", () -> new CrystalItem(new Item.Properties(), Tints.DOLOMITE));
    public static final DeferredHolder<Item, Item> TANZANITE_SEED = ITEMS.register("tanzanite_seed", () -> new CrystalItem(new Item.Properties(), Tints.TANZANITE));

    public static final DeferredHolder<Item, Item> RUBY_CHARGED = ITEMS.register("ruby_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> SAPPHIRE_CHARGED = ITEMS.register("sapphire_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> AQUAMARINE_CHARGED = ITEMS.register("aquamarine_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> JADE_CHARGED = ITEMS.register("jade_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> OPAL_CHARGED = ITEMS.register("opal_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> YELLOW_DIAMOND_CHARGED = ITEMS.register("yellow_diamond_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> AMBER_CHARGED = ITEMS.register("amber_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> TOPAZ_CHARGED = ITEMS.register("topaz_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> BERYLLIUM_CHARGED = ITEMS.register("beryllium_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> BIXBIT_CHARGED = ITEMS.register("bixbit_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> MALACHITE_CHARGED = ITEMS.register("malachite_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> ONYX_CHARGED = ITEMS.register("onyx_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> PERIDOT_CHARGED = ITEMS.register("peridot_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> MOON_STONE_CHARGED = ITEMS.register("moon_stone_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> SUN_STONE_CHARGED = ITEMS.register("sun_stone_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> CITRINE_CHARGED = ITEMS.register("citrine_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> DOLOMITE_CHARGED = ITEMS.register("dolomite_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));
    public static final DeferredHolder<Item, Item> TANZANITE_CHARGED = ITEMS.register("tanzanite_charged", () -> new ChargedCrystalItem(new Item.Properties(), Tints.NONE));

    public static final DeferredHolder<Item, Item> RUBY_CRYSTALS = ITEMS.register("ruby_crystals", () -> new TintedBlockItem(ModBlocks.RUBY_CRYSTALS.get(), new Item.Properties(), Tints.RUBY));
    public static final DeferredHolder<Item, Item> SAPPHIRE_CRYSTALS = ITEMS.register("sapphire_crystals", () -> new TintedBlockItem(ModBlocks.SAPPHIRE_CRYSTALS.get(), new Item.Properties(), Tints.SAPPHIRE));
    public static final DeferredHolder<Item, Item> AQUAMARINE_CRYSTALS = ITEMS.register("aquamarine_crystals", () -> new TintedBlockItem(ModBlocks.AQUAMARINE_CRYSTALS.get(), new Item.Properties(), Tints.AQUAMARINE));
    public static final DeferredHolder<Item, Item> JADE_CRYSTALS = ITEMS.register("jade_crystals", () -> new TintedBlockItem(ModBlocks.JADE_CRYSTALS.get(), new Item.Properties(), Tints.JADE));
    public static final DeferredHolder<Item, Item> OPAL_CRYSTALS = ITEMS.register("opal_crystals", () -> new TintedBlockItem(ModBlocks.OPAL_CRYSTALS.get(), new Item.Properties(), Tints.OPAL));
    public static final DeferredHolder<Item, Item> YELLOW_DIAMOND_CRYSTALS = ITEMS.register("yellow_diamond_crystals", () -> new TintedBlockItem(ModBlocks.YELLOW_DIAMOND_CRYSTALS.get(), new Item.Properties(), Tints.YELLOW_DIAMOND));
    public static final DeferredHolder<Item, Item> AMBER_CRYSTALS = ITEMS.register("amber_crystals", () -> new TintedBlockItem(ModBlocks.AMBER_CRYSTALS.get(), new Item.Properties(), Tints.AMBER));
    public static final DeferredHolder<Item, Item> TOPAZ_CRYSTALS = ITEMS.register("topaz_crystals", () -> new TintedBlockItem(ModBlocks.TOPAZ_CRYSTALS.get(), new Item.Properties(), Tints.TOPAZ));
    public static final DeferredHolder<Item, Item> BERYLLIUM_CRYSTALS = ITEMS.register("beryllium_crystals", () -> new TintedBlockItem(ModBlocks.BERYLLIUM_CRYSTALS.get(), new Item.Properties(), Tints.BERYLLIUM));
    public static final DeferredHolder<Item, Item> BIXBIT_CRYSTALS = ITEMS.register("bixbit_crystals", () -> new TintedBlockItem(ModBlocks.BIXBIT_CRYSTALS.get(), new Item.Properties(), Tints.BIXBIT));
    public static final DeferredHolder<Item, Item> MALACHITE_CRYSTALS = ITEMS.register("malachite_crystals", () -> new TintedBlockItem(ModBlocks.MALACHITE_CRYSTALS.get(), new Item.Properties(), Tints.MALACHITE));
    public static final DeferredHolder<Item, Item> ONYX_CRYSTALS = ITEMS.register("onyx_crystals", () -> new TintedBlockItem(ModBlocks.ONYX_CRYSTALS.get(), new Item.Properties(), Tints.ONYX));
    public static final DeferredHolder<Item, Item> PERIDOT_CRYSTALS = ITEMS.register("peridot_crystals", () -> new TintedBlockItem(ModBlocks.PERIDOT_CRYSTALS.get(), new Item.Properties(), Tints.PERIDOT));
    public static final DeferredHolder<Item, Item> MOON_STONE_CRYSTALS = ITEMS.register("moon_stone_crystals", () -> new TintedBlockItem(ModBlocks.MOON_STONE_CRYSTALS.get(), new Item.Properties(), Tints.MOON_STONE));
    public static final DeferredHolder<Item, Item> SUN_STONE_CRYSTALS = ITEMS.register("sun_stone_crystals", () -> new TintedBlockItem(ModBlocks.SUN_STONE_CRYSTALS.get(), new Item.Properties(), Tints.SUN_STONE));
    public static final DeferredHolder<Item, Item> CITRINE_CRYSTALS = ITEMS.register("citrine_crystals", () -> new TintedBlockItem(ModBlocks.CITRINE_CRYSTALS.get(), new Item.Properties(), Tints.CITRINE));
    public static final DeferredHolder<Item, Item> DOLOMITE_CRYSTALS = ITEMS.register("dolomite_crystals", () -> new TintedBlockItem(ModBlocks.DOLOMITE_CRYSTALS.get(), new Item.Properties(), Tints.DOLOMITE));
    public static final DeferredHolder<Item, Item> TANZANITE_CRYSTALS = ITEMS.register("tanzanite_crystals", () -> new TintedBlockItem(ModBlocks.TANZANITE_CRYSTALS.get(), new Item.Properties(), Tints.TANZANITE));

    public static final DeferredHolder<Item, CrystalArrowItem> RUBY_ARROW = ITEMS.register("ruby_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.RUBY, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> SAPPHIRE_ARROW = ITEMS.register("sapphire_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.SAPPHIRE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> AQUAMARINE_ARROW = ITEMS.register("aquamarine_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.AQUAMARINE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> JADE_ARROW = ITEMS.register("jade_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.JADE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> OPAL_ARROW = ITEMS.register("opal_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.OPAL, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> YELLOW_DIAMOND_ARROW = ITEMS.register("yellow_diamond_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.YELLOW_DIAMOND, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> AMBER_ARROW = ITEMS.register("amber_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.AMBER, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> TOPAZ_ARROW = ITEMS.register("topaz_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.TOPAZ, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> BERYLLIUM_ARROW = ITEMS.register("beryllium_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.BERYLLIUM, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> BIXBIT_ARROW = ITEMS.register("bixbit_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.BIXBIT, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> MALACHITE_ARROW = ITEMS.register("malachite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.MALACHITE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> ONYX_ARROW = ITEMS.register("onyx_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.ONYX, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> PERIDOT_ARROW = ITEMS.register("peridot_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.PERIDOT, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> MOON_STONE_ARROW = ITEMS.register("moon_stone_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.MOON_STONE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> SUN_STONE_ARROW = ITEMS.register("sun_stone_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.SUN_STONE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> CITRINE_ARROW = ITEMS.register("citrine_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.CITRINE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> DOLOMITE_ARROW = ITEMS.register("dolomite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.DOLOMITE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final DeferredHolder<Item, CrystalArrowItem> TANZANITE_ARROW = ITEMS.register("tanzanite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.TANZANITE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));

    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_RUBY_ARROW = ITEMS.register("charged_ruby_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.RUBY, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_SAPPHIRE_ARROW = ITEMS.register("charged_sapphire_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.SAPPHIRE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_AQUAMARINE_ARROW = ITEMS.register("charged_aquamarine_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.AQUAMARINE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_JADE_ARROW = ITEMS.register("charged_jade_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.JADE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_OPAL_ARROW = ITEMS.register("charged_opal_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.OPAL, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_YELLOW_DIAMOND_ARROW = ITEMS.register("charged_yellow_diamond_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.YELLOW_DIAMOND, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_AMBER_ARROW = ITEMS.register("charged_amber_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.AMBER, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_TOPAZ_ARROW = ITEMS.register("charged_topaz_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.TOPAZ, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_BERYLLIUM_ARROW = ITEMS.register("charged_beryllium_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.BERYLLIUM, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_BIXBIT_ARROW = ITEMS.register("charged_bixbit_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.BIXBIT, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_MALACHITE_ARROW = ITEMS.register("charged_malachite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.MALACHITE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_ONYX_ARROW = ITEMS.register("charged_onyx_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.ONYX, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_PERIDOT_ARROW = ITEMS.register("charged_peridot_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.PERIDOT, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_MOON_STONE_ARROW = ITEMS.register("charged_moon_stone_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.MOON_STONE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_SUN_STONE_ARROW = ITEMS.register("charged_sun_stone_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.SUN_STONE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_CITRINE_ARROW = ITEMS.register("charged_citrine_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.CITRINE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_DOLOMITE_ARROW = ITEMS.register("charged_dolomite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.DOLOMITE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final DeferredHolder<Item, CrystalArrowItem> CHARGED_TANZANITE_ARROW = ITEMS.register("charged_tanzanite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.TANZANITE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
}
