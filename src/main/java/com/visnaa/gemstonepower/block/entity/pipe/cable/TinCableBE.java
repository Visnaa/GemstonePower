package com.visnaa.gemstonepower.block.entity.pipe.cable;

import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TinCableBE extends CableBE
{
    public TinCableBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.TIN_CABLE.get(), pos, state, 1);
    }
}
