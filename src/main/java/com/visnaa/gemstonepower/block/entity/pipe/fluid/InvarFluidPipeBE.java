package com.visnaa.gemstonepower.block.entity.pipe.fluid;

import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class InvarFluidPipeBE extends FluidPipeBE
{
    public InvarFluidPipeBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.INVAR_FLUID_PIPE.get(), pos, state, 1);
    }
}
