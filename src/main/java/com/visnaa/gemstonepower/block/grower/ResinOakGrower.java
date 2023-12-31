package com.visnaa.gemstonepower.block.grower;

import com.visnaa.gemstonepower.init.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class ResinOakGrower extends AbstractTreeGrower
{
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource source, boolean hasFlowers)
    {
        return ModConfiguredFeatures.RESIN_TREE;
    }
}
