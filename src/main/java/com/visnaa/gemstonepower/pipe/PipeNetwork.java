package com.visnaa.gemstonepower.pipe;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface PipeNetwork<T extends PipeBE>
{
    void registerToNetwork(T pipe);

    void distribute(Level level, BlockState state, BlockPos pos, int transfer);

    void destroy(PipeBE caller);
}
