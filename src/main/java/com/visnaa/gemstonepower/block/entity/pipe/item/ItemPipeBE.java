package com.visnaa.gemstonepower.block.entity.pipe.item;

import com.visnaa.gemstonepower.block.pipe.item.IronItemPipeBlock;
import com.visnaa.gemstonepower.block.pipe.item.ItemPipeBlock;
import com.visnaa.gemstonepower.pipe.item.ItemPipeNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemPipeBE extends BlockEntity
{
    public ItemPipeNetwork network = new ItemPipeNetwork();

    public ItemPipeBE(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    public abstract int getTransfer();

    public List<ItemStack> getItems()
    {
        if (level == null || level.isClientSide())
            return new ArrayList<>();

        List<ItemStack> items = new ArrayList<>();
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(getBlockPos().relative(direction.getOpposite()));
            if (be != null && be.getCapability(ForgeCapabilities.ITEM_HANDLER, direction).isPresent())
            {
                be.getCapability(ForgeCapabilities.ITEM_HANDLER, direction).map(handler -> {
                    for (int slot = 0; slot < handler.getSlots(); slot++)
                    {
                        ItemStack stack = handler.extractItem(slot, handler.getStackInSlot(slot).getCount(), false);
                        items.add(stack);
                    }
                    return true;
                });
            }
        }
        return items.stream().toList();
    }

    protected <T extends ItemPipeBE> void updateConnections(Level level, BlockPos pos, BlockState state, Class<T> type)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && (type.isAssignableFrom(be.getClass()) || be.getCapability(ForgeCapabilities.ITEM_HANDLER, direction).isPresent()))
            {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(IronItemPipeBlock.CONNECTIONS.get(direction.getOpposite()), true));
                setChanged(level, pos, state);
            } else {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(IronItemPipeBlock.CONNECTIONS.get(direction.getOpposite()), false));
                setChanged(level, pos, state);
            }
        }
    }

    protected <T extends ItemPipeBE> void refreshNetwork(Level level, BlockPos pos, BlockState state, Class<T> type)
    {
        if (this.network == null) this.network = new ItemPipeNetwork();
        this.network.refresh();
        if (this.network == null) this.network = new ItemPipeNetwork();
        this.network.registerToNetwork(this);
        this.setChanged();

        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && type.isAssignableFrom(be.getClass()) && ((ItemPipeBE) be).network != null)
            {
                ((ItemPipeBE) be).network.merge(this.network);
                setChanged(level, pos, state);
                return;
            }
        }
    }

    protected void refreshOutputs(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(getBlockPos().relative(direction.getOpposite()));
            if (be != null && be.getCapability(ForgeCapabilities.ITEM_HANDLER, direction).isPresent())
            {
                if (state.getValue(ItemPipeBlock.EXTRACTS))
                    network.registerInput(be);
                else
                    network.registerOutput(be);
            }
        }
    }

    protected void distributeItems(Level level, BlockPos pos, BlockState state)
    {
        if (state.getValue(ItemPipeBlock.EXTRACTS))
            network.distribute(level, state, getTransfer());
    }
}
