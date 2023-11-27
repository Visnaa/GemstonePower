package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModBiomes;
import com.visnaa.gemstonepower.init.ModTags;
import com.visnaa.gemstonepower.world.biome.CustomBiome;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BiomeTagGenerator extends BiomeTagsProvider
{
    public BiomeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, completableFuture, GemstonePower.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        this.tag(ModTags.RESIN_TREE_SPAWN)
                .addTag(BiomeTags.IS_FOREST)
                .addTag(Tags.Biomes.IS_PLAINS)
                .addTag(Tags.Biomes.IS_SWAMP);

        for (CustomBiome biome : ModBiomes.OVERWORLD_BIOMES)
            this.tag(BiomeTags.IS_OVERWORLD).add(biome.key());

        this.tag(Tags.Biomes.IS_PLAINS)
                .add(ModBiomes.RESIN_PLAINS.key());
        this.tag(Tags.Biomes.IS_DRY_OVERWORLD)
                .add(ModBiomes.RESIN_PLAINS.key());

        this.tag(BiomeTags.HAS_BURIED_TREASURE)
                .add(ModBiomes.RESIN_PLAINS.key());
        this.tag(BiomeTags.HAS_MINESHAFT)
                .add(ModBiomes.RESIN_PLAINS.key());
        this.tag(BiomeTags.HAS_OCEAN_RUIN_COLD)
                .add(ModBiomes.RESIN_PLAINS.key());
        this.tag(BiomeTags.HAS_PILLAGER_OUTPOST)
                .add(ModBiomes.RESIN_PLAINS.key());
        this.tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD)
                .add(ModBiomes.RESIN_PLAINS.key());
        this.tag(BiomeTags.HAS_SHIPWRECK_BEACHED)
                .add(ModBiomes.RESIN_PLAINS.key());
        this.tag(BiomeTags.HAS_STRONGHOLD)
                .add(ModBiomes.RESIN_PLAINS.key());
        this.tag(BiomeTags.HAS_VILLAGE_PLAINS)
                .add(ModBiomes.RESIN_PLAINS.key());
    }
}
