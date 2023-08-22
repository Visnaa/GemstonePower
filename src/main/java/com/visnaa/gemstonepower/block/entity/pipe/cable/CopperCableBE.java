package com.visnaa.gemstonepower.block.entity.pipe.cable;

import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CopperCableBE extends CableBE
{
    public CopperCableBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.COPPER_CABLE.get(), pos, state, 1);
    }
}
