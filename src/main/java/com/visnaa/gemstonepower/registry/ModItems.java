package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.item.*;
import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GemstonePower.MOD_ID);

    public static final RegistryObject<Item> AZURITE_CRYSTAL = ITEMS.register("azurite_crystal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AZURITE_CRYSTAL_CHARGED = ITEMS.register("azurite_crystal_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> AZURITE_CRYSTAL_SEED = ITEMS.register("azurite_crystal_seed", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUPRITE_CRYSTAL = ITEMS.register("cuprite_crystal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CUPRITE_CRYSTAL_CHARGED = ITEMS.register("cuprite_crystal_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> CUPRITE_CRYSTAL_SEED = ITEMS.register("cuprite_crystal_seed", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TREE_TAP = ITEMS.register("tree_tap", () -> new TreeTapItem(new Item.Properties().durability(64)));
    public static final RegistryObject<Item> RESIN_OAK_SAPLING = ITEMS.register("resin_oak_sapling", () -> new BlockItem(ModBlocks.RESIN_OAK_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> RESIN_OAK_LOG = ITEMS.register("resin_oak_log", () -> new BlockItem(ModBlocks.RESIN_OAK_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> RESIN_OAK_LEAVES = ITEMS.register("resin_oak_leaves", () -> new BlockItem(ModBlocks.RESIN_OAK_LEAVES.get(), new Item.Properties()));

    public static final RegistryObject<Item> COAL_DUST = ITEMS.register("coal_dust", () -> new TintedItem(new Item.Properties(), Tints.COAL));

    public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final RegistryObject<Item> IRON_TINY_PILE = ITEMS.register("iron_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final RegistryObject<Item> IRON_ORE_DUST = ITEMS.register("iron_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod", () -> new TintedItem(new Item.Properties(), Tints.IRON));
    public static final RegistryObject<Item> IRON_ROD_POLARIZED = ITEMS.register("iron_rod_polarized", () -> new FoilItem(new Item.Properties(), Tints.IRON));
    public static final RegistryObject<Item> IRON_GEAR = ITEMS.register("iron_gear", () -> new TintedItem(new Item.Properties(), Tints.IRON));

    public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final RegistryObject<Item> GOLD_TINY_PILE = ITEMS.register("gold_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final RegistryObject<Item> GOLD_ORE_DUST = ITEMS.register("gold_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final RegistryObject<Item> GOLD_ROD = ITEMS.register("gold_rod", () -> new TintedItem(new Item.Properties(), Tints.GOLD));
    public static final RegistryObject<Item> GOLD_GEAR = ITEMS.register("gold_gear", () -> new TintedItem(new Item.Properties(), Tints.GOLD));

    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final RegistryObject<Item> COPPER_DUST = ITEMS.register("copper_dust", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final RegistryObject<Item> COPPER_TINY_PILE = ITEMS.register("copper_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final RegistryObject<Item> COPPER_ORE_DUST = ITEMS.register("copper_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final RegistryObject<Item> COPPER_ROD = ITEMS.register("copper_rod", () -> new TintedItem(new Item.Properties(), Tints.COPPER));
    public static final RegistryObject<Item> COPPER_GEAR = ITEMS.register("copper_gear", () -> new TintedItem(new Item.Properties(), Tints.COPPER));

    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_BLOCK = ITEMS.register("aluminum_block", () -> new TintedBlockItem(ModBlocks.ALUMINUM_BLOCK.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_ORE = ITEMS.register("aluminum_ore", () -> new TintedBlockItem(ModBlocks.ALUMINUM_ORE.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> DEEPSLATE_ALUMINUM_ORE = ITEMS.register("deepslate_aluminum_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> RAW_ALUMINUM = ITEMS.register("raw_aluminum", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_DUST = ITEMS.register("aluminum_dust", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_TINY_PILE = ITEMS.register("aluminum_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_ORE_DUST = ITEMS.register("aluminum_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_PLATE = ITEMS.register("aluminum_plate", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_ROD = ITEMS.register("aluminum_rod", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_GEAR = ITEMS.register("aluminum_gear", () -> new TintedItem(new Item.Properties(), Tints.ALUMINUM));

    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_BLOCK = ITEMS.register("tin_block", () -> new TintedBlockItem(ModBlocks.TIN_BLOCK.get(), new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_ORE = ITEMS.register("tin_ore", () -> new TintedBlockItem(ModBlocks.TIN_ORE.get(), new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> DEEPSLATE_TIN_ORE = ITEMS.register("deepslate_tin_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_TIN_ORE.get(), new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_TINY_PILE = ITEMS.register("tin_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_ORE_DUST = ITEMS.register("tin_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_PLATE = ITEMS.register("tin_plate", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_ROD = ITEMS.register("tin_rod", () -> new TintedItem(new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_GEAR = ITEMS.register("tin_gear", () -> new TintedItem(new Item.Properties(), Tints.TIN));

    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final RegistryObject<Item> BRONZE_BLOCK = ITEMS.register("bronze_block", () -> new TintedBlockItem(ModBlocks.BRONZE_BLOCK.get(), new Item.Properties(), Tints.BRONZE));
    public static final RegistryObject<Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final RegistryObject<Item> BRONZE_DUST = ITEMS.register("bronze_dust", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final RegistryObject<Item> BRONZE_TINY_PILE = ITEMS.register("bronze_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final RegistryObject<Item> BRONZE_PLATE = ITEMS.register("bronze_plate", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final RegistryObject<Item> BRONZE_ROD = ITEMS.register("bronze_rod", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));
    public static final RegistryObject<Item> BRONZE_GEAR = ITEMS.register("bronze_gear", () -> new TintedItem(new Item.Properties(), Tints.BRONZE));

    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> SILVER_BLOCK = ITEMS.register("silver_block", () -> new TintedBlockItem(ModBlocks.SILVER_BLOCK.get(), new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> SILVER_ORE = ITEMS.register("silver_ore", () -> new TintedBlockItem(ModBlocks.SILVER_ORE.get(), new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> DEEPSLATE_SILVER_ORE = ITEMS.register("deepslate_silver_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_SILVER_ORE.get(), new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> SILVER_DUST = ITEMS.register("silver_dust", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> SILVER_TINY_PILE = ITEMS.register("silver_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> SILVER_ORE_DUST = ITEMS.register("silver_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> SILVER_PLATE = ITEMS.register("silver_plate", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> SILVER_ROD = ITEMS.register("silver_rod", () -> new TintedItem(new Item.Properties(), Tints.SILVER));
    public static final RegistryObject<Item> SILVER_GEAR = ITEMS.register("silver_gear", () -> new TintedItem(new Item.Properties(), Tints.SILVER));

    public static final RegistryObject<Item> ELECTRUM_INGOT = ITEMS.register("electrum_ingot", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final RegistryObject<Item> ELECTRUM_BLOCK = ITEMS.register("electrum_block", () -> new TintedBlockItem(ModBlocks.ELECTRUM_BLOCK.get(), new Item.Properties(), Tints.ELECTRUM));
    public static final RegistryObject<Item> ELECTRUM_NUGGET = ITEMS.register("electrum_nugget", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final RegistryObject<Item> ELECTRUM_DUST = ITEMS.register("electrum_dust", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final RegistryObject<Item> ELECTRUM_TINY_PILE = ITEMS.register("electrum_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final RegistryObject<Item> ELECTRUM_PLATE = ITEMS.register("electrum_plate", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final RegistryObject<Item> ELECTRUM_ROD = ITEMS.register("electrum_rod", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));
    public static final RegistryObject<Item> ELECTRUM_GEAR = ITEMS.register("electrum_gear", () -> new TintedItem(new Item.Properties(), Tints.ELECTRUM));

    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_BLOCK = ITEMS.register("nickel_block", () -> new TintedBlockItem(ModBlocks.NICKEL_BLOCK.get(), new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_ORE = ITEMS.register("nickel_ore", () -> new TintedBlockItem(ModBlocks.NICKEL_ORE.get(), new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> DEEPSLATE_NICKEL_ORE = ITEMS.register("deepslate_nickel_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> RAW_NICKEL = ITEMS.register("raw_nickel", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_NUGGET = ITEMS.register("nickel_nugget", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_DUST = ITEMS.register("nickel_dust", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_TINY_PILE = ITEMS.register("nickel_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_ORE_DUST = ITEMS.register("nickel_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_PLATE = ITEMS.register("nickel_plate", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_ROD = ITEMS.register("nickel_rod", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_ROD_POLARIZED = ITEMS.register("nickel_rod_polarized", () -> new FoilItem(new Item.Properties(), Tints.NICKEL));
    public static final RegistryObject<Item> NICKEL_GEAR = ITEMS.register("nickel_gear", () -> new TintedItem(new Item.Properties(), Tints.NICKEL));

    public static final RegistryObject<Item> INVAR_INGOT = ITEMS.register("invar_ingot", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final RegistryObject<Item> INVAR_BLOCK = ITEMS.register("invar_block", () -> new TintedBlockItem(ModBlocks.INVAR_BLOCK.get(), new Item.Properties(), Tints.INVAR));
    public static final RegistryObject<Item> INVAR_NUGGET = ITEMS.register("invar_nugget", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final RegistryObject<Item> INVAR_DUST = ITEMS.register("invar_dust", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final RegistryObject<Item> INVAR_TINY_PILE = ITEMS.register("invar_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final RegistryObject<Item> INVAR_PLATE = ITEMS.register("invar_plate", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final RegistryObject<Item> INVAR_ROD = ITEMS.register("invar_rod", () -> new TintedItem(new Item.Properties(), Tints.INVAR));
    public static final RegistryObject<Item> INVAR_ROD_POLARIZED = ITEMS.register("invar_rod_polarized", () -> new FoilItem(new Item.Properties(), Tints.INVAR));
    public static final RegistryObject<Item> INVAR_GEAR = ITEMS.register("invar_gear", () -> new TintedItem(new Item.Properties(), Tints.INVAR));

    public static final RegistryObject<Item> CONSTANTAN_INGOT = ITEMS.register("constantan_ingot", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final RegistryObject<Item> CONSTANTAN_BLOCK = ITEMS.register("constantan_block", () -> new TintedBlockItem(ModBlocks.CONSTANTAN_BLOCK.get(), new Item.Properties(), Tints.CONSTANTAN));
    public static final RegistryObject<Item> CONSTANTAN_NUGGET = ITEMS.register("constantan_nugget", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final RegistryObject<Item> CONSTANTAN_DUST = ITEMS.register("constantan_dust", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final RegistryObject<Item> CONSTANTAN_TINY_PILE = ITEMS.register("constantan_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final RegistryObject<Item> CONSTANTAN_PLATE = ITEMS.register("constantan_plate", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final RegistryObject<Item> CONSTANTAN_ROD = ITEMS.register("constantan_rod", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final RegistryObject<Item> CONSTANTAN_ROD_POLARIZED = ITEMS.register("constantan_rod_polarized", () -> new FoilItem(new Item.Properties(), Tints.CONSTANTAN));
    public static final RegistryObject<Item> CONSTANTAN_GEAR = ITEMS.register("constantan_gear", () -> new TintedItem(new Item.Properties(), Tints.CONSTANTAN));

    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> PLATINUM_BLOCK = ITEMS.register("platinum_block", () -> new TintedBlockItem(ModBlocks.PLATINUM_BLOCK.get(), new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> PLATINUM_ORE = ITEMS.register("platinum_ore", () -> new TintedBlockItem(ModBlocks.PLATINUM_ORE.get(), new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> DEEPSLATE_PLATINUM_ORE = ITEMS.register("deepslate_platinum_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> RAW_PLATINUM = ITEMS.register("raw_platinum", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> PLATINUM_DUST = ITEMS.register("platinum_dust", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> PLATINUM_TINY_PILE = ITEMS.register("platinum_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> PLATINUM_ORE_DUST = ITEMS.register("platinum_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> PLATINUM_PLATE = ITEMS.register("platinum_plate", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> PLATINUM_ROD = ITEMS.register("platinum_rod", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));
    public static final RegistryObject<Item> PLATINUM_GEAR = ITEMS.register("platinum_gear", () -> new TintedItem(new Item.Properties(), Tints.PLATINUM));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final RegistryObject<Item> STEEL_BLOCK = ITEMS.register("steel_block", () -> new TintedBlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties(), Tints.STEEL));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final RegistryObject<Item> STEEL_DUST = ITEMS.register("steel_dust", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final RegistryObject<Item> STEEL_TINY_PILE = ITEMS.register("steel_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final RegistryObject<Item> STEEL_ROD = ITEMS.register("steel_rod", () -> new TintedItem(new Item.Properties(), Tints.STEEL));
    public static final RegistryObject<Item> STEEL_ROD_POLARIZED = ITEMS.register("steel_rod_polarized", () -> new FoilItem(new Item.Properties(), Tints.STEEL));
    public static final RegistryObject<Item> STEEL_GEAR = ITEMS.register("steel_gear", () -> new TintedItem(new Item.Properties(), Tints.STEEL));

    public static final RegistryObject<Item> LITHIUM_INGOT = ITEMS.register("lithium_ingot", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> LITHIUM_BLOCK = ITEMS.register("lithium_block", () -> new TintedBlockItem(ModBlocks.LITHIUM_BLOCK.get(), new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> LITHIUM_ORE = ITEMS.register("lithium_ore", () -> new TintedBlockItem(ModBlocks.LITHIUM_ORE.get(), new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> DEEPSLATE_LITHIUM_ORE = ITEMS.register("deepslate_lithium_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_LITHIUM_ORE.get(), new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> RAW_LITHIUM = ITEMS.register("raw_lithium", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> LITHIUM_NUGGET = ITEMS.register("lithium_nugget", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> LITHIUM_DUST = ITEMS.register("lithium_dust", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> LITHIUM_TINY_PILE = ITEMS.register("lithium_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> LITHIUM_ORE_DUST = ITEMS.register("lithium_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> LITHIUM_PLATE = ITEMS.register("lithium_plate", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> LITHIUM_ROD = ITEMS.register("lithium_rod", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));
    public static final RegistryObject<Item> LITHIUM_GEAR = ITEMS.register("lithium_gear", () -> new TintedItem(new Item.Properties(), Tints.LITHIUM));

    public static final RegistryObject<Item> MAGNESIUM_INGOT = ITEMS.register("magnesium_ingot", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> MAGNESIUM_BLOCK = ITEMS.register("magnesium_block", () -> new TintedBlockItem(ModBlocks.MAGNESIUM_BLOCK.get(), new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> MAGNESIUM_ORE = ITEMS.register("magnesium_ore", () -> new TintedBlockItem(ModBlocks.MAGNESIUM_ORE.get(), new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> DEEPSLATE_MAGNESIUM_ORE = ITEMS.register("deepslate_magnesium_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get(), new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> RAW_MAGNESIUM = ITEMS.register("raw_magnesium", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> MAGNESIUM_NUGGET = ITEMS.register("magnesium_nugget", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> MAGNESIUM_DUST = ITEMS.register("magnesium_dust", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> MAGNESIUM_TINY_PILE = ITEMS.register("magnesium_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> MAGNESIUM_ORE_DUST = ITEMS.register("magnesium_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> MAGNESIUM_PLATE = ITEMS.register("magnesium_plate", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> MAGNESIUM_ROD = ITEMS.register("magnesium_rod", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));
    public static final RegistryObject<Item> MAGNESIUM_GEAR = ITEMS.register("magnesium_gear", () -> new TintedItem(new Item.Properties(), Tints.MAGNESIUM));

    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> URANIUM_BLOCK = ITEMS.register("uranium_block", () -> new RadioactiveBlockItem(ModBlocks.URANIUM_BLOCK.get(), new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> URANIUM_ORE = ITEMS.register("uranium_ore", () -> new RadioactiveBlockItem(ModBlocks.URANIUM_ORE.get(), new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> DEEPSLATE_URANIUM_ORE = ITEMS.register("deepslate_uranium_ore", () -> new RadioactiveBlockItem(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> URANIUM_NUGGET = ITEMS.register("uranium_nugget", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> URANIUM_DUST = ITEMS.register("uranium_dust", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> URANIUM_TINY_PILE = ITEMS.register("uranium_tiny_pile", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> URANIUM_ORE_DUST = ITEMS.register("uranium_ore_dust", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> URANIUM_PLATE = ITEMS.register("uranium_plate", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> URANIUM_ROD = ITEMS.register("uranium_rod", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));
    public static final RegistryObject<Item> URANIUM_GEAR = ITEMS.register("uranium_gear", () -> new RadioactiveItem(new Item.Properties(), Tints.URANIUM));

    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> LEAD_BLOCK = ITEMS.register("lead_block", () -> new TintedBlockItem(ModBlocks.LEAD_BLOCK.get(), new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> LEAD_ORE = ITEMS.register("lead_ore", () -> new TintedBlockItem(ModBlocks.LEAD_ORE.get(), new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> DEEPSLATE_LEAD_ORE = ITEMS.register("deepslate_lead_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_LEAD_ORE.get(), new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register("lead_nugget", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> LEAD_DUST = ITEMS.register("lead_dust", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> LEAD_TINY_PILE = ITEMS.register("lead_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> LEAD_ORE_DUST = ITEMS.register("lead_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> LEAD_PLATE = ITEMS.register("lead_plate", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> LEAD_ROD = ITEMS.register("lead_rod", () -> new TintedItem(new Item.Properties(), Tints.LEAD));
    public static final RegistryObject<Item> LEAD_GEAR = ITEMS.register("lead_gear", () -> new TintedItem(new Item.Properties(), Tints.LEAD));

    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> ZINC_BLOCK = ITEMS.register("zinc_block", () -> new TintedBlockItem(ModBlocks.ZINC_BLOCK.get(), new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> ZINC_ORE = ITEMS.register("zinc_ore", () -> new TintedBlockItem(ModBlocks.ZINC_ORE.get(), new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> DEEPSLATE_ZINC_ORE = ITEMS.register("deepslate_zinc_ore", () -> new TintedBlockItem(ModBlocks.DEEPSLATE_ZINC_ORE.get(), new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> ZINC_DUST = ITEMS.register("zinc_dust", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> ZINC_TINY_PILE = ITEMS.register("zinc_tiny_pile", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> ZINC_ORE_DUST = ITEMS.register("zinc_ore_dust", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> ZINC_PLATE = ITEMS.register("zinc_plate", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> ZINC_ROD = ITEMS.register("zinc_rod", () -> new TintedItem(new Item.Properties(), Tints.ZINC));
    public static final RegistryObject<Item> ZINC_GEAR = ITEMS.register("zinc_gear", () -> new TintedItem(new Item.Properties(), Tints.ZINC));

    public static final RegistryObject<Item> GEMSTONE_GENERATOR = ITEMS.register("gemstone_generator", () -> new BlockItem(ModBlocks.GEMSTONE_GENERATOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> GEMSTONE_CELL = ITEMS.register("gemstone_cell", () -> new BlockItem(ModBlocks.GEMSTONE_CELL.get(), new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_GROWER = ITEMS.register("crystal_grower", () -> new TintedBlockItem(ModBlocks.CRYSTAL_GROWER.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> CRYSTAL_CHARGER = ITEMS.register("crystal_charger", () -> new TintedBlockItem(ModBlocks.CRYSTAL_CHARGER.get(), new Item.Properties(), Tints.STANDARD));

    public static final RegistryObject<Item> ELECTRIC_FURNACE = ITEMS.register("electric_furnace", () -> new TintedBlockItem(ModBlocks.ELECTRIC_FURNACE.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> METAL_FORMER = ITEMS.register("metal_former", () -> new TintedBlockItem(ModBlocks.METAL_FORMER.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> PULVERIZER = ITEMS.register("pulverizer", () -> new TintedBlockItem(ModBlocks.PULVERIZER.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> ALLOY_SMELTER = ITEMS.register("alloy_smelter", () -> new TintedBlockItem(ModBlocks.ALLOY_SMELTER.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> EXTRACTOR = ITEMS.register("extractor", () -> new TintedBlockItem(ModBlocks.EXTRACTOR.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> ORE_WASHER = ITEMS.register("ore_washer", () -> new TintedBlockItem(ModBlocks.ORE_WASHER.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> COBBLESTONE_GENERATOR = ITEMS.register("cobblestone_generator", () -> new TintedBlockItem(ModBlocks.COBBLESTONE_GENERATOR.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> SAWMILL = ITEMS.register("sawmill", () -> new TintedBlockItem(ModBlocks.SAWMILL.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> POLARIZER = ITEMS.register("polarizer", () -> new TintedBlockItem(ModBlocks.POLARIZER.get(), new Item.Properties(), Tints.STANDARD));

    public static final RegistryObject<Item> SOLAR_PANEL = ITEMS.register("solar_panel", () -> new TintedBlockItem(ModBlocks.SOLAR_PANEL.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> WATER_MILL = ITEMS.register("water_mill", () -> new TintedBlockItem(ModBlocks.WATER_MILL.get(), new Item.Properties(), Tints.STANDARD));
    public static final RegistryObject<Item> WIND_TURBINE = ITEMS.register("wind_turbine", () -> new TintedBlockItem(ModBlocks.WIND_TURBINE.get(), new Item.Properties(), Tints.STANDARD));

    public static final RegistryObject<Item> PLATE_PRESET = ITEMS.register("plate_preset", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROD_PRESET = ITEMS.register("rod_preset", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WIRE_PRESET = ITEMS.register("wire_preset", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RESIN = ITEMS.register("resin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBBER = ITEMS.register("rubber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INTERMEDIATE_UPGRADE = ITEMS.register("intermediate_upgrade", () -> new UpgradeItem(new Item.Properties(), Tier.INTERMEDIATE, Tints.INTERMEDIATE));
    public static final RegistryObject<Item> ADVANCED_UPGRADE = ITEMS.register("advanced_upgrade", () -> new UpgradeItem(new Item.Properties(), Tier.ADVANCED, Tints.ADVANCED));
    public static final RegistryObject<Item> ULTRA_UPGRADE = ITEMS.register("ultra_upgrade", () -> new UpgradeItem(new Item.Properties(), Tier.ULTRA, Tints.ULTRA));
    public static final RegistryObject<Item> EXTREME_UPGRADE = ITEMS.register("extreme_upgrade", () -> new UpgradeItem(new Item.Properties(), Tier.EXTREME, Tints.EXTREME));

    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire", () -> new TintedBlockItem(ModBlocks.COPPER_WIRE.get(), new Item.Properties(), Tints.COPPER));
    public static final RegistryObject<Item> COPPER_CABLE = ITEMS.register("copper_cable", () -> new TintedBlockItem(ModBlocks.COPPER_CABLE.get(), new Item.Properties(), Tints.COPPER));
    public static final RegistryObject<Item> ALUMINUM_WIRE = ITEMS.register("aluminum_wire", () -> new TintedBlockItem(ModBlocks.ALUMINUM_WIRE.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> ALUMINUM_CABLE = ITEMS.register("aluminum_cable", () -> new TintedBlockItem(ModBlocks.ALUMINUM_CABLE.get(), new Item.Properties(), Tints.ALUMINUM));
    public static final RegistryObject<Item> TIN_WIRE = ITEMS.register("tin_wire", () -> new TintedBlockItem(ModBlocks.TIN_WIRE.get(), new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> TIN_CABLE = ITEMS.register("tin_cable", () -> new TintedBlockItem(ModBlocks.TIN_CABLE.get(), new Item.Properties(), Tints.TIN));
    public static final RegistryObject<Item> ELECTRUM_WIRE = ITEMS.register("electrum_wire", () -> new TintedBlockItem(ModBlocks.ELECTRUM_WIRE.get(), new Item.Properties(), Tints.ELECTRUM));
    public static final RegistryObject<Item> ELECTRUM_CABLE = ITEMS.register("electrum_cable", () -> new TintedBlockItem(ModBlocks.ELECTRUM_CABLE.get(), new Item.Properties(), Tints.ELECTRUM));

    public static final RegistryObject<Item> LIGHT_GEMSTONE = ITEMS.register("light_gemstone", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DARK_GEMSTONE = ITEMS.register("dark_gemstone", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JADE = ITEMS.register("jade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OPAL = ITEMS.register("opal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_DIAMOND = ITEMS.register("yellow_diamond", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TOPAZ = ITEMS.register("topaz", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BERYLLIUM = ITEMS.register("beryllium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BIXBIT = ITEMS.register("bixbit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MALACHITE = ITEMS.register("malachite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ONYX = ITEMS.register("onyx", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PERIDOT = ITEMS.register("peridot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOON_STONE = ITEMS.register("moon_stone", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUN_STONE = ITEMS.register("sun_stone", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DOLOMITE = ITEMS.register("dolomite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TANZANITE = ITEMS.register("tanzanite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUBY_SEED = ITEMS.register("ruby_seed", () -> new TintedItem(new Item.Properties(), Tints.RUBY));
    public static final RegistryObject<Item> SAPPHIRE_SEED = ITEMS.register("sapphire_seed", () -> new TintedItem(new Item.Properties(), Tints.SAPPHIRE));
    public static final RegistryObject<Item> AQUAMARINE_SEED = ITEMS.register("aquamarine_seed", () -> new TintedItem(new Item.Properties(), Tints.AQUAMARINE));
    public static final RegistryObject<Item> JADE_SEED = ITEMS.register("jade_seed", () -> new TintedItem(new Item.Properties(), Tints.JADE));
    public static final RegistryObject<Item> OPAL_SEED = ITEMS.register("opal_seed", () -> new TintedItem(new Item.Properties(), Tints.OPAL));
    public static final RegistryObject<Item> YELLOW_DIAMOND_SEED = ITEMS.register("yellow_diamond_seed", () -> new TintedItem(new Item.Properties(), Tints.YELLOW_DIAMOND));
    public static final RegistryObject<Item> AMBER_SEED = ITEMS.register("amber_seed", () -> new TintedItem(new Item.Properties(), Tints.AMBER));
    public static final RegistryObject<Item> TOPAZ_SEED = ITEMS.register("topaz_seed", () -> new TintedItem(new Item.Properties(), Tints.TOPAZ));
    public static final RegistryObject<Item> BERYLLIUM_SEED = ITEMS.register("beryllium_seed", () -> new TintedItem(new Item.Properties(), Tints.BERYLLIUM));
    public static final RegistryObject<Item> BIXBIT_SEED = ITEMS.register("bixbit_seed", () -> new TintedItem(new Item.Properties(), Tints.BIXBIT));
    public static final RegistryObject<Item> MALACHITE_SEED = ITEMS.register("malachite_seed", () -> new TintedItem(new Item.Properties(), Tints.MALACHITE));
    public static final RegistryObject<Item> ONYX_SEED = ITEMS.register("onyx_seed", () -> new TintedItem(new Item.Properties(), Tints.ONYX));
    public static final RegistryObject<Item> PERIDOT_SEED = ITEMS.register("peridot_seed", () -> new TintedItem(new Item.Properties(), Tints.PERIDOT));
    public static final RegistryObject<Item> MOON_STONE_SEED = ITEMS.register("moon_stone_seed", () -> new TintedItem(new Item.Properties(), Tints.MOON_STONE));
    public static final RegistryObject<Item> SUN_STONE_SEED = ITEMS.register("sun_stone_seed", () -> new TintedItem(new Item.Properties(), Tints.SUN_STONE));
    public static final RegistryObject<Item> CITRINE_SEED = ITEMS.register("citrine_seed", () -> new TintedItem(new Item.Properties(), Tints.CITRINE));
    public static final RegistryObject<Item> DOLOMITE_SEED = ITEMS.register("dolomite_seed", () -> new TintedItem(new Item.Properties(), Tints.DOLOMITE));
    public static final RegistryObject<Item> TANZANITE_SEED = ITEMS.register("tanzanite_seed", () -> new TintedItem(new Item.Properties(), Tints.TANZANITE));

    public static final RegistryObject<Item> RUBY_CHARGED = ITEMS.register("ruby_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> SAPPHIRE_CHARGED = ITEMS.register("sapphire_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> AQUAMARINE_CHARGED = ITEMS.register("aquamarine_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> JADE_CHARGED = ITEMS.register("jade_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> OPAL_CHARGED = ITEMS.register("opal_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> YELLOW_DIAMOND_CHARGED = ITEMS.register("yellow_diamond_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> AMBER_CHARGED = ITEMS.register("amber_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> TOPAZ_CHARGED = ITEMS.register("topaz_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> BERYLLIUM_CHARGED = ITEMS.register("beryllium_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> BIXBIT_CHARGED = ITEMS.register("bixbit_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> MALACHITE_CHARGED = ITEMS.register("malachite_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> ONYX_CHARGED = ITEMS.register("onyx_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> PERIDOT_CHARGED = ITEMS.register("peridot_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> MOON_STONE_CHARGED = ITEMS.register("moon_stone_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> SUN_STONE_CHARGED = ITEMS.register("sun_stone_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> CITRINE_CHARGED = ITEMS.register("citrine_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> DOLOMITE_CHARGED = ITEMS.register("dolomite_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));
    public static final RegistryObject<Item> TANZANITE_CHARGED = ITEMS.register("tanzanite_charged", () -> new FoilItem(new Item.Properties(), Tints.EMPTY));

    public static final RegistryObject<Item> RUBY_CRYSTALS = ITEMS.register("ruby_crystals", () -> new TintedBlockItem(ModBlocks.RUBY_CRYSTALS.get(), new Item.Properties(), Tints.RUBY));
    public static final RegistryObject<Item> SAPPHIRE_CRYSTALS = ITEMS.register("sapphire_crystals", () -> new TintedBlockItem(ModBlocks.SAPPHIRE_CRYSTALS.get(), new Item.Properties(), Tints.SAPPHIRE));
    public static final RegistryObject<Item> AQUAMARINE_CRYSTALS = ITEMS.register("aquamarine_crystals", () -> new TintedBlockItem(ModBlocks.AQUAMARINE_CRYSTALS.get(), new Item.Properties(), Tints.AQUAMARINE));
    public static final RegistryObject<Item> JADE_CRYSTALS = ITEMS.register("jade_crystals", () -> new TintedBlockItem(ModBlocks.JADE_CRYSTALS.get(), new Item.Properties(), Tints.JADE));
    public static final RegistryObject<Item> OPAL_CRYSTALS = ITEMS.register("opal_crystals", () -> new TintedBlockItem(ModBlocks.OPAL_CRYSTALS.get(), new Item.Properties(), Tints.OPAL));
    public static final RegistryObject<Item> YELLOW_DIAMOND_CRYSTALS = ITEMS.register("yellow_diamond_crystals", () -> new TintedBlockItem(ModBlocks.YELLOW_DIAMOND_CRYSTALS.get(), new Item.Properties(), Tints.YELLOW_DIAMOND));
    public static final RegistryObject<Item> AMBER_CRYSTALS = ITEMS.register("amber_crystals", () -> new TintedBlockItem(ModBlocks.AMBER_CRYSTALS.get(), new Item.Properties(), Tints.AMBER));
    public static final RegistryObject<Item> TOPAZ_CRYSTALS = ITEMS.register("topaz_crystals", () -> new TintedBlockItem(ModBlocks.TOPAZ_CRYSTALS.get(), new Item.Properties(), Tints.TOPAZ));
    public static final RegistryObject<Item> BERYLLIUM_CRYSTALS = ITEMS.register("beryllium_crystals", () -> new TintedBlockItem(ModBlocks.BERYLLIUM_CRYSTALS.get(), new Item.Properties(), Tints.BERYLLIUM));
    public static final RegistryObject<Item> BIXBIT_CRYSTALS = ITEMS.register("bixbit_crystals", () -> new TintedBlockItem(ModBlocks.BIXBIT_CRYSTALS.get(), new Item.Properties(), Tints.BIXBIT));
    public static final RegistryObject<Item> MALACHITE_CRYSTALS = ITEMS.register("malachite_crystals", () -> new TintedBlockItem(ModBlocks.MALACHITE_CRYSTALS.get(), new Item.Properties(), Tints.MALACHITE));
    public static final RegistryObject<Item> ONYX_CRYSTALS = ITEMS.register("onyx_crystals", () -> new TintedBlockItem(ModBlocks.ONYX_CRYSTALS.get(), new Item.Properties(), Tints.ONYX));
    public static final RegistryObject<Item> PERIDOT_CRYSTALS = ITEMS.register("peridot_crystals", () -> new TintedBlockItem(ModBlocks.PERIDOT_CRYSTALS.get(), new Item.Properties(), Tints.PERIDOT));
    public static final RegistryObject<Item> MOON_STONE_CRYSTALS = ITEMS.register("moon_stone_crystals", () -> new TintedBlockItem(ModBlocks.MOON_STONE_CRYSTALS.get(), new Item.Properties(), Tints.MOON_STONE));
    public static final RegistryObject<Item> SUN_STONE_CRYSTALS = ITEMS.register("sun_stone_crystals", () -> new TintedBlockItem(ModBlocks.SUN_STONE_CRYSTALS.get(), new Item.Properties(), Tints.SUN_STONE));
    public static final RegistryObject<Item> CITRINE_CRYSTALS = ITEMS.register("citrine_crystals", () -> new TintedBlockItem(ModBlocks.CITRINE_CRYSTALS.get(), new Item.Properties(), Tints.CITRINE));
    public static final RegistryObject<Item> DOLOMITE_CRYSTALS = ITEMS.register("dolomite_crystals", () -> new TintedBlockItem(ModBlocks.DOLOMITE_CRYSTALS.get(), new Item.Properties(), Tints.DOLOMITE));
    public static final RegistryObject<Item> TANZANITE_CRYSTALS = ITEMS.register("tanzanite_crystals", () -> new TintedBlockItem(ModBlocks.TANZANITE_CRYSTALS.get(), new Item.Properties(), Tints.TANZANITE));

    public static final RegistryObject<CrystalArrowItem> RUBY_ARROW = ITEMS.register("ruby_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.RUBY, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> SAPPHIRE_ARROW = ITEMS.register("sapphire_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.SAPPHIRE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> AQUAMARINE_ARROW = ITEMS.register("aquamarine_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.AQUAMARINE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> JADE_ARROW = ITEMS.register("jade_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.JADE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> OPAL_ARROW = ITEMS.register("opal_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.OPAL, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> YELLOW_DIAMOND_ARROW = ITEMS.register("yellow_diamond_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.YELLOW_DIAMOND, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> AMBER_ARROW = ITEMS.register("amber_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.AMBER, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> TOPAZ_ARROW = ITEMS.register("topaz_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.TOPAZ, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> BERYLLIUM_ARROW = ITEMS.register("beryllium_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.BERYLLIUM, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> BIXBIT_ARROW = ITEMS.register("bixbit_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.BIXBIT, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> MALACHITE_ARROW = ITEMS.register("malachite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.MALACHITE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> ONYX_ARROW = ITEMS.register("onyx_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.ONYX, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> PERIDOT_ARROW = ITEMS.register("peridot_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.PERIDOT, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> MOON_STONE_ARROW = ITEMS.register("moon_stone_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.MOON_STONE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> SUN_STONE_ARROW = ITEMS.register("sun_stone_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.SUN_STONE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> CITRINE_ARROW = ITEMS.register("citrine_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.CITRINE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> DOLOMITE_ARROW = ITEMS.register("dolomite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.DOLOMITE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));
    public static final RegistryObject<CrystalArrowItem> TANZANITE_ARROW = ITEMS.register("tanzanite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.TANZANITE, hit -> hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)), false));

    public static final RegistryObject<CrystalArrowItem> CHARGED_RUBY_ARROW = ITEMS.register("charged_ruby_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.RUBY, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_SAPPHIRE_ARROW = ITEMS.register("charged_sapphire_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.SAPPHIRE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_AQUAMARINE_ARROW = ITEMS.register("charged_aquamarine_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.AQUAMARINE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_JADE_ARROW = ITEMS.register("charged_jade_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.JADE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_OPAL_ARROW = ITEMS.register("charged_opal_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.OPAL, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_YELLOW_DIAMOND_ARROW = ITEMS.register("charged_yellow_diamond_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.YELLOW_DIAMOND, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_AMBER_ARROW = ITEMS.register("charged_amber_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.AMBER, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_TOPAZ_ARROW = ITEMS.register("charged_topaz_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.TOPAZ, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_BERYLLIUM_ARROW = ITEMS.register("charged_beryllium_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.BERYLLIUM, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_BIXBIT_ARROW = ITEMS.register("charged_bixbit_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.BIXBIT, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_MALACHITE_ARROW = ITEMS.register("charged_malachite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.MALACHITE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_ONYX_ARROW = ITEMS.register("charged_onyx_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.ONYX, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_PERIDOT_ARROW = ITEMS.register("charged_peridot_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.PERIDOT, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_MOON_STONE_ARROW = ITEMS.register("charged_moon_stone_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.MOON_STONE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_SUN_STONE_ARROW = ITEMS.register("charged_sun_stone_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.SUN_STONE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_CITRINE_ARROW = ITEMS.register("charged_citrine_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.CITRINE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_DOLOMITE_ARROW = ITEMS.register("charged_dolomite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.DOLOMITE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
    public static final RegistryObject<CrystalArrowItem> CHARGED_TANZANITE_ARROW = ITEMS.register("charged_tanzanite_arrow", () -> new CrystalArrowItem(new Item.Properties(), Tints.TANZANITE, hit -> { hit.getEntity().addDeltaMovement(new Vec3(0, 1F, 0)); hit.getEntity().hurt(hit.getEntity().level().damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7F); }, true));
}
