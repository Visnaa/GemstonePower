package com.visnaa.gemstonepower.block.entity.pipe.item;

import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class IronItemPipeBE extends ItemPipeBE
{
    public IronItemPipeBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.IRON_ITEM_PIPE.get(), pos, state);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        updateConnections(level, pos, state, IronItemPipeBE.class);
        refreshNetwork(level, pos, state, IronItemPipeBE.class);
        super.tick(level, pos, state);
    }

    @Override
    public int getTransfer()
    {
        return 1;
    }
}