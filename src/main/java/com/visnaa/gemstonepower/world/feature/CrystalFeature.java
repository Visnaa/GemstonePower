package com.visnaa.gemstonepower.world.feature;

import com.mojang.serialization.Codec;
import com.visnaa.gemstonepower.world.feature.configured.CrystalConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.List;

public class CrystalFeature extends Feature<CrystalConfiguration>
{
    public CrystalFeature(Codec<CrystalConfiguration> config) {
        super(config);
    }

    public boolean place(FeaturePlaceContext<CrystalConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        CrystalConfiguration config = context.config();
        if (!isAirOrWater(level.getBlockState(pos))) {
            return false;
        } else {
            List<Direction> list = config.getShuffledDirections(random);
            if (placeGrowthIfPossible(level, pos, level.getBlockState(pos), config, random, list)) {
                return true;
            } else {
                BlockPos.MutableBlockPos mutablePos = pos.mutable();

                for(Direction direction : list) {
                    mutablePos.set(pos);
                    List<Direction> list1 = config.getShuffledDirectionsExcept(random, direction.getOpposite());

                    for(int i = 0; i < config.searchRange; ++i) {
                        mutablePos.setWithOffset(pos, direction);
                        BlockState blockstate = level.getBlockState(mutablePos);
                        if (!isAirOrWater(blockstate) && !blockstate.is(config.placeBlock)) {
                            break;
                        }

                        if (placeGrowthIfPossible(level, mutablePos, blockstate, config, random, list1)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }
    }

    public static boolean placeGrowthIfPossible(WorldGenLevel level, BlockPos pos, BlockState state, CrystalConfiguration config, RandomSource random, List<Direction> directions) {
        BlockPos.MutableBlockPos mutablePos = pos.mutable();

        for(Direction direction : directions) {
            BlockState blockstate = level.getBlockState(mutablePos.setWithOffset(pos, direction));
            if (blockstate.is(config.canBePlacedOn)) {
                BlockState blockstate1 = config.placeBlock.getStateForPlacement(state, level, pos, direction);
                if (blockstate1 == null) {
                    return false;
                }

                level.setBlock(pos, blockstate1, 3);
                level.getChunk(pos).markPosForPostprocessing(pos);
                if (random.nextFloat() < config.chanceOfSpreading) {
                    config.placeBlock.getSpreader().spreadFromFaceTowardRandomDirection(blockstate1, level, pos, direction, random, true);
                }

                return true;
            }
        }

        return false;
    }

    private static boolean isAirOrWater(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER);
    }
}
