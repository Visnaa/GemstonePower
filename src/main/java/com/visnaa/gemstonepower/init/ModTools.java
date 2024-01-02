package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.item.tool.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTools
{
    public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(BuiltInRegistries.ITEM, GemstonePower.MOD_ID);

    public static final DeferredHolder<Item, Item> GEMSTONE_SWORD = TOOLS.register("gemstone_sword", () -> new SwordItem(ToolMaterial.GEMSTONE, 3, -2.4F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> GEMSTONE_SHOVEL = TOOLS.register("gemstone_shovel", () -> new ShovelItem(ToolMaterial.GEMSTONE, 1.5F, -3.0F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> GEMSTONE_PICKAXE = TOOLS.register("gemstone_pickaxe", () -> new PickaxeItem(ToolMaterial.GEMSTONE, 1, -2.8F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> GEMSTONE_AXE = TOOLS.register("gemstone_axe", () -> new AxeItem(ToolMaterial.GEMSTONE, 6.0F, -3.1F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> GEMSTONE_HOE = TOOLS.register("gemstone_hoe", () -> new HoeItem(ToolMaterial.GEMSTONE, -4, 0.0F, new Item.Properties()));

    public static final DeferredHolder<Item, Item> COPPER_SWORD = TOOLS.register("copper_sword", () -> new TintedSwordItem(ToolMaterial.COPPER, 3, -2.4F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> COPPER_SHOVEL = TOOLS.register("copper_shovel", () -> new TintedShovelItem(ToolMaterial.COPPER, 1.5F, -3F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> COPPER_PICKAXE = TOOLS.register("copper_pickaxe", () -> new TintedPickaxeItem(ToolMaterial.COPPER, 1, -2.8F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> COPPER_AXE = TOOLS.register("copper_axe", () -> new TintedAxeItem(ToolMaterial.COPPER, 6, -3.1F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> COPPER_HOE = TOOLS.register("copper_hoe", () -> new TintedHoeItem(ToolMaterial.COPPER, -2, -1.0F, new Item.Properties()));

    public static final DeferredHolder<Item, Item> ALUMINUM_SWORD = TOOLS.register("aluminum_sword", () -> new TintedSwordItem(ToolMaterial.ALUMINUM, 3, -2.4F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINUM_SHOVEL = TOOLS.register("aluminum_shovel", () -> new TintedShovelItem(ToolMaterial.ALUMINUM, 1.5F, -3F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINUM_PICKAXE = TOOLS.register("aluminum_pickaxe", () -> new TintedPickaxeItem(ToolMaterial.ALUMINUM, 1, -2.8F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINUM_AXE = TOOLS.register("aluminum_axe", () -> new TintedAxeItem(ToolMaterial.ALUMINUM, 6, -3.1F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ALUMINUM_HOE = TOOLS.register("aluminum_hoe", () -> new TintedHoeItem(ToolMaterial.ALUMINUM, -2, -1.0F, new Item.Properties()));

    public static final DeferredHolder<Item, Item> BRONZE_SWORD = TOOLS.register("bronze_sword", () -> new TintedSwordItem(ToolMaterial.BRONZE, 3, -2.4F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> BRONZE_SHOVEL = TOOLS.register("bronze_shovel", () -> new TintedShovelItem(ToolMaterial.BRONZE, 1.5F, -3F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> BRONZE_PICKAXE = TOOLS.register("bronze_pickaxe", () -> new TintedPickaxeItem(ToolMaterial.BRONZE, 1, -2.8F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> BRONZE_AXE = TOOLS.register("bronze_axe", () -> new TintedAxeItem(ToolMaterial.BRONZE, 6, -3.1F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> BRONZE_HOE = TOOLS.register("bronze_hoe", () -> new TintedHoeItem(ToolMaterial.BRONZE, -2, -1.0F, new Item.Properties()));

    public static final DeferredHolder<Item, Item> SILVER_SWORD = TOOLS.register("silver_sword", () -> new TintedSwordItem(ToolMaterial.SILVER, 3, -2.4F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> SILVER_SHOVEL = TOOLS.register("silver_shovel", () -> new TintedShovelItem(ToolMaterial.SILVER, 1.5F, -3F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> SILVER_PICKAXE = TOOLS.register("silver_pickaxe", () -> new TintedPickaxeItem(ToolMaterial.SILVER, 1, -2.8F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> SILVER_AXE = TOOLS.register("silver_axe", () -> new TintedAxeItem(ToolMaterial.SILVER, 6, -3.1F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> SILVER_HOE = TOOLS.register("silver_hoe", () -> new TintedHoeItem(ToolMaterial.SILVER, -2, -1.0F, new Item.Properties()));

    public static final DeferredHolder<Item, Item> INVAR_SWORD = TOOLS.register("invar_sword", () -> new TintedSwordItem(ToolMaterial.INVAR, 3, -2.4F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> INVAR_SHOVEL = TOOLS.register("invar_shovel", () -> new TintedShovelItem(ToolMaterial.INVAR, 1.5F, -3F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> INVAR_PICKAXE = TOOLS.register("invar_pickaxe", () -> new TintedPickaxeItem(ToolMaterial.INVAR, 1, -2.8F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> INVAR_AXE = TOOLS.register("invar_axe", () -> new TintedAxeItem(ToolMaterial.INVAR, 6, -3.1F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> INVAR_HOE = TOOLS.register("invar_hoe", () -> new TintedHoeItem(ToolMaterial.INVAR, -2, -1.0F, new Item.Properties()));

    public static final DeferredHolder<Item, Item> STEEL_SWORD = TOOLS.register("steel_sword", () -> new TintedSwordItem(ToolMaterial.STEEL, 3, -2.4F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> STEEL_SHOVEL = TOOLS.register("steel_shovel", () -> new TintedShovelItem(ToolMaterial.STEEL, 1.5F, -3F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> STEEL_PICKAXE = TOOLS.register("steel_pickaxe", () -> new TintedPickaxeItem(ToolMaterial.STEEL, 1, -2.8F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> STEEL_AXE = TOOLS.register("steel_axe", () -> new TintedAxeItem(ToolMaterial.STEEL, 6, -3.1F, new Item.Properties()));
    public static final DeferredHolder<Item, Item> STEEL_HOE = TOOLS.register("steel_hoe", () -> new TintedHoeItem(ToolMaterial.STEEL, -2, -1.0F, new Item.Properties()));
}
