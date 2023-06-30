package com.visnaa.gemstonepower.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimMaterial;

public final class ModTrims
{
    public static final ResourceKey<TrimMaterial> ALUMINUM = register("aluminium");
    public static final ResourceKey<TrimMaterial> TIN = register("tin");
    public static final ResourceKey<TrimMaterial> BRONZE = register("bronze");
    public static final ResourceKey<TrimMaterial> SILVER = register("silver");
    public static final ResourceKey<TrimMaterial> ELECTRUM = register("electrum");
    public static final ResourceKey<TrimMaterial> NICKEL = register("nickel");
    public static final ResourceKey<TrimMaterial> INVAR = register("invar");
    public static final ResourceKey<TrimMaterial> CONSTANTAN = register("constantan");
    public static final ResourceKey<TrimMaterial> STEEL = register("steel");
    public static final ResourceKey<TrimMaterial> LITHIUM = register("lithium");
    public static final ResourceKey<TrimMaterial> MAGNESIUM = register("magnesium");
    public static final ResourceKey<TrimMaterial> URANIUM = register("uranium");
    public static final ResourceKey<TrimMaterial> LEAD = register("lead");
    public static final ResourceKey<TrimMaterial> ZINC = register("zinc");

    public static final ResourceKey<TrimMaterial> RUBY = register("ruby");
    public static final ResourceKey<TrimMaterial> SAPPHIRE = register("sapphire");
    public static final ResourceKey<TrimMaterial> AQUAMARINE = register("aquamarine");
    public static final ResourceKey<TrimMaterial> JADE = register("jade");
    public static final ResourceKey<TrimMaterial> OPAL = register("opal");
    public static final ResourceKey<TrimMaterial> YELLOW_DIAMOND = register("yellow_diamond");
    public static final ResourceKey<TrimMaterial> AMBER = register("amber");
    public static final ResourceKey<TrimMaterial> TOPAZ = register("topaz");
    public static final ResourceKey<TrimMaterial> BERYLLIUM = register("beryllium");
    public static final ResourceKey<TrimMaterial> BIXBIT = register("bixbit");
    public static final ResourceKey<TrimMaterial> MALACHITE = register("malachite");
    public static final ResourceKey<TrimMaterial> ONYX = register("onyx");
    public static final ResourceKey<TrimMaterial> PERIDOT = register("peridot");
    public static final ResourceKey<TrimMaterial> MOON_STONE = register("moon_stone");
    public static final ResourceKey<TrimMaterial> SUN_STONE = register("sun_stone");
    public static final ResourceKey<TrimMaterial> CITRINE = register("citrine");
    public static final ResourceKey<TrimMaterial> DOLOMITE = register("dolomite");
    public static final ResourceKey<TrimMaterial> TANZANITE = register("tanzanite");

    private static ResourceKey<TrimMaterial> register(String name)
    {
        return ResourceKey.create(Registries.TRIM_MATERIAL, new ResourceLocation(name));
    }
}
