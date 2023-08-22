package com.visnaa.gemstonepower.block.entity.pipe.item;

import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GoldItemPipeBE extends ItemPipeBE
{
    public GoldItemPipeBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.GOLD_ITEM_PIPE.get(), pos, state, 1);
    }
}