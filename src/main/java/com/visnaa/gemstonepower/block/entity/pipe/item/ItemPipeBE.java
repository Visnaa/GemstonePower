package com.visnaa.gemstonepower.block.entity.pipe.item;

import com.visnaa.gemstonepower.block.pipe.item.ItemPipeBlock;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class ItemPipeBE extends BaseItemPipeBE
{
    public ItemPipeBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ITEM_PIPE.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, ItemPipeBE blockEntity)
    {
        blockEntity.updateConnections(level, pos, state);
        blockEntity.refreshNetwork(level, pos, state, ItemPipeBE.class);
        blockEntity.refreshOutputs(level, pos, state);
        blockEntity.distributeItems(level, pos, state);
    }

    protected void updateConnections(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && ((!(be instanceof BaseItemPipeBE) || be instanceof ItemPipeBE) || be.getCapability(ForgeCapabilities.ITEM_HANDLER, direction).isPresent()))
            {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(ItemPipeBlock.CONNECTIONS.get(direction.getOpposite()), true));
                setChanged(level, pos, state);
            } else {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(ItemPipeBlock.CONNECTIONS.get(direction.getOpposite()), false));
                setChanged(level, pos, state);
            }
        }
    }

    @Override
    public int getTransfer()
    {
        return 1;
    }
}