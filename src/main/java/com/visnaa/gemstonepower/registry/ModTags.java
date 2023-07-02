package com.visnaa.gemstonepower.registry;

import com.visnaa.gemstonepower.GemstonePower;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;

public class ModTags
{
    public static final TagKey<Item> GEMSTONE = item("gemstone");
    public static final TagKey<Item> GEMS = item("gems");
    public static final TagKey<Item> CHARGED_GEMS = item("charged_gems");
    public static final TagKey<Item> GEM_SEEDS = item("gem_seeds");
    public static final TagKey<Item> CABLES = item("cables");

    public static final TagKey<Biome> RESIN_TREE_SPAWN = biome("resin_tree_spawn");

    private static TagKey<Item> item(String name)
    {
        return TagKey.create(Registries.ITEM, GemstonePower.getId(name));
    }

    private static TagKey<Biome> biome(String name)
    {
        return TagKey.create(Registries.BIOME, GemstonePower.getId(name));
    }
}
