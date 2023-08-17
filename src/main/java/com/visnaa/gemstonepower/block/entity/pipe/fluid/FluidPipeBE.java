package com.visnaa.gemstonepower.block.entity.pipe.fluid;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.block.pipe.item.IronItemPipeBlock;
import com.visnaa.gemstonepower.block.pipe.item.ItemPipeBlock;
import com.visnaa.gemstonepower.pipe.fluid.FluidPipeNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public abstract class FluidPipeBE extends PipeBE
{
    public FluidPipeNetwork network = new FluidPipeNetwork();

    public FluidPipeBE(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    public abstract int getTransfer();

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        updateConnections(level, pos, state);
        refreshNetwork(level, pos, state);
        refreshOutputs(level, pos, state);
        distributeItems(level, pos, state);
    }

    protected void updateConnections(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && (getClass().isAssignableFrom(be.getClass()) || be.getCapability(ForgeCapabilities.FLUID_HANDLER, direction).isPresent()))
            {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(IronItemPipeBlock.CONNECTIONS.get(direction.getOpposite()), true));
                setChanged(level, pos, state);
            } else {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(IronItemPipeBlock.CONNECTIONS.get(direction.getOpposite()), false));
                setChanged(level, pos, state);
            }
        }
    }

    protected void refreshNetwork(Level level, BlockPos pos, BlockState state)
    {
        if (this.network == null) this.network = new FluidPipeNetwork();
        this.network.refresh();
        if (this.network == null) this.network = new FluidPipeNetwork();
        this.network.registerToNetwork(this);
        this.setChanged();

        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && getClass().isAssignableFrom(be.getClass()) && ((FluidPipeBE) be).network != null)
                ((FluidPipeBE) be).network.merge(this.network);
        }
        setChanged(level, pos, state);
    }

    protected void refreshOutputs(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(getBlockPos().relative(direction.getOpposite()));
            if (be != null && be.getCapability(ForgeCapabilities.FLUID_HANDLER, direction).isPresent())
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
        if (state.getValue(PipeBlock.EXTRACTS))
            network.distribute(level, state, getTransfer());
    }

    @Override
    public FluidPipeNetwork getNetwork()
    {
        return network;
    }
}
