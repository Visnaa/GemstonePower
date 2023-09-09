package com.visnaa.gemstonepower.block;

import com.visnaa.gemstonepower.block.entity.ReactorWallBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ReactorWallBlock extends BaseEntityBlock
{
    public ReactorWallBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new ReactorWallBE(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean flag)
    {
        if (level.getBlockEntity(pos) instanceof ReactorWallBE wall)
            wall.broken(level);
        super.onRemove(state, level, pos, state1, flag);
    }
}
