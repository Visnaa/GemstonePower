package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.world.biome.CustomBiome;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class ModBiomes
{
    public static final List<CustomBiome> OVERWORLD_BIOMES = new ArrayList<>();
    public static final List<CustomBiome> NETHER_BIOMES = new ArrayList<>();

    public static final CustomBiome RESIN_PLAINS = overworld("resin_plains", new CustomBiome.Builder(Blocks.GRASS_BLOCK, Blocks.STONE, Blocks.GRAVEL)
            .temperature(-0.5F, 0.5F)
            .humidity(-0.1F, 0.3F)
            .continentalness(-0.1F, 0.5F)
            .erosion(0.4F, 0.6F)
            .depth(0, 0)
            .weirdness(-1F, 1F)
            .offset(0));

    private static CustomBiome overworld(String name, CustomBiome.Builder builder)
    {
        CustomBiome biome = builder.build(getKey(name));
        OVERWORLD_BIOMES.add(biome);
        return biome;
    }

    private static CustomBiome nether(String name, CustomBiome.Builder builder)
    {
        CustomBiome biome = builder.build(getKey(name));
        NETHER_BIOMES.add(biome);
        return biome;
    }

    private static ResourceKey<Biome> getKey(String name)
    {
        return ResourceKey.create(Registries.BIOME, GemstonePower.getId(name));
    }
}
