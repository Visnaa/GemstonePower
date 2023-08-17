package com.visnaa.gemstonepower.block.entity.pipe;

import com.visnaa.gemstonepower.block.entity.TickingBlockEntity;
import com.visnaa.gemstonepower.pipe.PipeNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class PipeBE extends BlockEntity implements TickingBlockEntity
{
    public PipeBE(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    public abstract PipeNetwork getNetwork();
}
