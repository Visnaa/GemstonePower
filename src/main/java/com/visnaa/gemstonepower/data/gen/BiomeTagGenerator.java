package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
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
    }
}
