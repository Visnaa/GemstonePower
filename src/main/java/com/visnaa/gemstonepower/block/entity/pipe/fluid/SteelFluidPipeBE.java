package com.visnaa.gemstonepower.block.entity.pipe.fluid;

import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SteelFluidPipeBE extends FluidPipeBE
{
    public SteelFluidPipeBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.STEEL_FLUID_PIPE.get(), pos, state);
    }

    @Override
    public int getTransfer()
    {
        return 1;
    }
}
