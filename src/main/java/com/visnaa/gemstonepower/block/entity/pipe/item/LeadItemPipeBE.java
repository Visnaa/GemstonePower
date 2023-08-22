package com.visnaa.gemstonepower.block.entity.pipe.item;

import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class LeadItemPipeBE extends ItemPipeBE
{
    public LeadItemPipeBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.LEAD_ITEM_PIPE.get(), pos, state, 1);
    }
}