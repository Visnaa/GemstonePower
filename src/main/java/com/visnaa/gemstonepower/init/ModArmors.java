package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.item.armour.ArmorMaterial;
import com.visnaa.gemstonepower.item.armour.TintedArmourItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModArmors
{
    public static final DeferredRegister<Item> ARMOUR_PIECES = DeferredRegister.create(BuiltInRegistries.ITEM, GemstonePower.MOD_ID);

    public static final DeferredHolder<Item, Item> GEMSTONE_HELMET = register("gemstone_helmet",ArmorItem.Type.HELMET, ArmorMaterial.GEMSTONE);
    public static final DeferredHolder<Item, Item> GEMSTONE_CHESTPLATE = register("gemstone_chestplate", ArmorItem.Type.CHESTPLATE, ArmorMaterial.GEMSTONE);
    public static final DeferredHolder<Item, Item> GEMSTONE_LEGGINGS = register("gemstone_leggings", ArmorItem.Type.LEGGINGS, ArmorMaterial.GEMSTONE);
    public static final DeferredHolder<Item, Item> GEMSTONE_BOOTS = register("gemstone_boots", ArmorItem.Type.BOOTS, ArmorMaterial.GEMSTONE);

    public static final DeferredHolder<Item, Item> COPPER_HELMET = register("copper_helmet", ArmorItem.Type.HELMET, ArmorMaterial.COPPER);
    public static final DeferredHolder<Item, Item> COPPER_CHESTPLATE = register("copper_chestplate", ArmorItem.Type.CHESTPLATE, ArmorMaterial.COPPER);
    public static final DeferredHolder<Item, Item> COPPER_LEGGINGS = register("copper_leggings", ArmorItem.Type.LEGGINGS, ArmorMaterial.COPPER);
    public static final DeferredHolder<Item, Item> COPPER_BOOTS = register("copper_boots", ArmorItem.Type.BOOTS, ArmorMaterial.COPPER);

    public static final DeferredHolder<Item, Item> ALUMINUM_HELMET = register("aluminum_helmet", ArmorItem.Type.HELMET, ArmorMaterial.ALUMINUM);
    public static final DeferredHolder<Item, Item> ALUMINUM_CHESTPLATE = register("aluminum_chestplate", ArmorItem.Type.CHESTPLATE, ArmorMaterial.ALUMINUM);
    public static final DeferredHolder<Item, Item> ALUMINUM_LEGGINGS = register("aluminum_leggings", ArmorItem.Type.LEGGINGS, ArmorMaterial.ALUMINUM);
    public static final DeferredHolder<Item, Item> ALUMINUM_BOOTS = register("aluminum_boots", ArmorItem.Type.BOOTS, ArmorMaterial.ALUMINUM);

    public static final DeferredHolder<Item, Item> BRONZE_HELMET = register("bronze_helmet", ArmorItem.Type.HELMET, ArmorMaterial.BRONZE);
    public static final DeferredHolder<Item, Item> BRONZE_CHESTPLATE = register("bronze_chestplate", ArmorItem.Type.CHESTPLATE, ArmorMaterial.BRONZE);
    public static final DeferredHolder<Item, Item> BRONZE_LEGGINGS = register("bronze_leggings", ArmorItem.Type.LEGGINGS, ArmorMaterial.BRONZE);
    public static final DeferredHolder<Item, Item> BRONZE_BOOTS = register("bronze_boots", ArmorItem.Type.BOOTS, ArmorMaterial.BRONZE);

    public static final DeferredHolder<Item, Item> SILVER_HELMET = register("silver_helmet", ArmorItem.Type.HELMET, ArmorMaterial.SILVER);
    public static final DeferredHolder<Item, Item> SILVER_CHESTPLATE = register("silver_chestplate", ArmorItem.Type.CHESTPLATE, ArmorMaterial.SILVER);
    public static final DeferredHolder<Item, Item> SILVER_LEGGINGS = register("silver_leggings", ArmorItem.Type.LEGGINGS, ArmorMaterial.SILVER);
    public static final DeferredHolder<Item, Item> SILVER_BOOTS = register("silver_boots", ArmorItem.Type.BOOTS, ArmorMaterial.SILVER);

    public static final DeferredHolder<Item, Item> INVAR_HELMET = register("invar_helmet", ArmorItem.Type.HELMET, ArmorMaterial.INVAR);
    public static final DeferredHolder<Item, Item> INVAR_CHESTPLATE = register("invar_chestplate", ArmorItem.Type.CHESTPLATE, ArmorMaterial.INVAR);
    public static final DeferredHolder<Item, Item> INVAR_LEGGINGS = register("invar_leggings", ArmorItem.Type.LEGGINGS, ArmorMaterial.INVAR);
    public static final DeferredHolder<Item, Item> INVAR_BOOTS = register("invar_boots", ArmorItem.Type.BOOTS, ArmorMaterial.INVAR);

    public static final DeferredHolder<Item, Item> STEEL_HELMET = register("steel_helmet", ArmorItem.Type.HELMET, ArmorMaterial.STEEL);
    public static final DeferredHolder<Item, Item> STEEL_CHESTPLATE = register("steel_chestplate", ArmorItem.Type.CHESTPLATE, ArmorMaterial.STEEL);
    public static final DeferredHolder<Item, Item> STEEL_LEGGINGS = register("steel_leggings", ArmorItem.Type.LEGGINGS, ArmorMaterial.STEEL);
    public static final DeferredHolder<Item, Item> STEEL_BOOTS = register("steel_boots", ArmorItem.Type.BOOTS, ArmorMaterial.STEEL);

    public static DeferredHolder<Item, Item> register(String name, ArmorItem.Type type, ArmorMaterial material)
    {
        return ARMOUR_PIECES.register(name, () -> new TintedArmourItem(material, type, new Item.Properties()));
    }
}
