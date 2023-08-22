package com.visnaa.gemstonepower.world.feature.configured;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.visnaa.gemstonepower.block.CrystalBlock;
import com.visnaa.gemstonepower.init.ModBlocks;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public class CrystalConfiguration implements FeatureConfiguration
{
    public static final Codec<CrystalConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").flatXmap(CrystalConfiguration::apply, DataResult::success).orElse((CrystalBlock) ModBlocks.RUBY_CRYSTALS.get()).forGetter((getter) -> {
            return getter.placeBlock;
        }), Codec.intRange(1, 64).fieldOf("search_range").orElse(10).forGetter((getter) -> {
            return getter.searchRange;
        }), Codec.BOOL.fieldOf("can_place_on_floor").orElse(false).forGetter((getter) -> {
            return getter.canPlaceOnFloor;
        }), Codec.BOOL.fieldOf("can_place_on_ceiling").orElse(false).forGetter((getter) -> {
            return getter.canPlaceOnCeiling;
        }), Codec.BOOL.fieldOf("can_place_on_wall").orElse(false).forGetter((getter) -> {
            return getter.canPlaceOnWall;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("chance_of_spreading").orElse(0.5F).forGetter((getter) -> {
            return getter.chanceOfSpreading;
        }), RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_be_placed_on").forGetter((getter) -> {
            return getter.canBePlacedOn;
        })).apply(instance, CrystalConfiguration::new);
    });
    public final CrystalBlock placeBlock;
    public final int searchRange;
    public final boolean canPlaceOnFloor;
    public final boolean canPlaceOnCeiling;
    public final boolean canPlaceOnWall;
    public final float chanceOfSpreading;
    public final HolderSet<Block> canBePlacedOn;
    private final ObjectArrayList<Direction> validDirections;

    private static DataResult<CrystalBlock> apply(Block block) {
        DataResult dataresult;
        if (block instanceof CrystalBlock crystalBlock) {
            dataresult = DataResult.success(crystalBlock);
        } else {
            dataresult = DataResult.error(() -> "Growth block should be a crystal block");
        }

        return dataresult;
    }

    public CrystalConfiguration(CrystalBlock block, int searchRange, boolean canPlaceOnFloor, boolean canPlaceOnCeiling, boolean canPlaceOnWall, float chanceOfSpreading, HolderSet<Block> canBePlacedOn) {
        this.placeBlock = block;
        this.searchRange = searchRange;
        this.canPlaceOnFloor = canPlaceOnFloor;
        this.canPlaceOnCeiling = canPlaceOnCeiling;
        this.canPlaceOnWall = canPlaceOnWall;
        this.chanceOfSpreading = chanceOfSpreading;
        this.canBePlacedOn = canBePlacedOn;
        this.validDirections = new ObjectArrayList<>(6);
        if (canPlaceOnCeiling) {
            this.validDirections.add(Direction.UP);
        }

        if (canPlaceOnFloor) {
            this.validDirections.add(Direction.DOWN);
        }

        if (canPlaceOnWall) {
            Direction.Plane.HORIZONTAL.forEach(this.validDirections::add);
        }

    }

    public List<Direction> getShuffledDirectionsExcept(RandomSource random, Direction direction) {
        return Util.toShuffledList(this.validDirections.stream().filter((dir) -> {
            return dir != direction;
        }), random);
    }

    public List<Direction> getShuffledDirections(RandomSource random) {
        return Util.shuffledCopy(this.validDirections, random);
    }
}
