package com.visnaa.gemstonepower.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public interface TickingBlockEntity
{
    static void serverTick(Level level, BlockPos pos, BlockState state, TickingBlockEntity blockEntity)
    {
        blockEntity.tick(level, pos, state);
    }

    public void tick(Level level, BlockPos pos, BlockState state);
}
