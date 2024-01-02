package com.visnaa.gemstonepower.block.entity.pipe.cable;

import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ElectrumCableBE extends CableBE
{
    public ElectrumCableBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ELECTRUM_CABLE.get(), pos, state, 1024);
    }
}
