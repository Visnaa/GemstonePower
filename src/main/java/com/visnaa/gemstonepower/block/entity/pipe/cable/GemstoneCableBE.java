package com.visnaa.gemstonepower.block.entity.pipe.cable;

import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GemstoneCableBE extends CableBE
{
    public GemstoneCableBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.GEMSTONE_CABLE.get(), pos, state, 16384);
    }
}
