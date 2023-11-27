package com.visnaa.gemstonepower.block.entity.pipe.fluid;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.capability.fluid.FluidPipeNetwork;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.capabilities.Capabilities;

public abstract class FluidPipeBE extends PipeBE
{
    public FluidPipeNetwork network = new FluidPipeNetwork();
    private final int transfer;

    public FluidPipeBE(BlockEntityType<?> type, BlockPos pos, BlockState state, int transfer)
    {
        super(type, pos, state);
        this.transfer = (int) (ServerConfig.FLUID_PIPE_TRANSFER_MUL.get() * transfer);
    }

    @Override
    public int getTransfer()
    {
        return transfer;
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        updateConnections(level, pos, state);
        refreshNetwork(level, pos, state);
        registerNeighbours(level, pos, state);
        distribute(level, pos, state);
    }

    @Override
    protected void updateConnections(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            if (!(state.getBlock() instanceof PipeBlock))
                continue;

            BlockEntity be = level.getBlockEntity(pos.relative(direction));
            if (prohibitedConnections.get(direction))
                continue;

            if (be == null && !state.getValue(PipeBlock.CONNECTIONS.get(direction)).equals("false"))
            {
                level.setBlockAndUpdate(pos, state = level.getBlockState(pos).setValue(PipeBlock.CONNECTIONS.get(direction), "false"));
                setChanged(level, pos, state);
            }
            else if (be != null && (getClass().isAssignableFrom(be.getClass()) || be.getCapability(Capabilities.FLUID_HANDLER, direction).isPresent()) && state.getValue(PipeBlock.CONNECTIONS.get(direction)).equals("false"))
            {
                level.setBlockAndUpdate(pos, state = level.getBlockState(pos).setValue(PipeBlock.CONNECTIONS.get(direction), "true"));
                setChanged(level, pos, state);
            }
        }
    }

    @Override
    protected void refreshNetwork(Level level, BlockPos pos, BlockState state)
    {
        if (this.network == null)
            this.network = new FluidPipeNetwork();
        this.network.refresh();
        if (this.network == null)
            this.network = new FluidPipeNetwork();
        this.network.registerToNetwork(this);
        this.setChanged();

        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction));
            if (be instanceof FluidPipeBE pipe && getClass().isAssignableFrom(pipe.getClass()) && pipe.network != null && canMerge(level, pos, direction))
                pipe.network.merge(this.network);
        }
        setChanged(level, pos, state);
    }

    @Override
    protected void registerNeighbours(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(getBlockPos().relative(direction));
            if (be != null && be.getCapability(Capabilities.FLUID_HANDLER, direction).isPresent())
            {
                if (state.getValue(PipeBlock.CONNECTIONS.get(direction)).equals("extracts"))
                    network.registerInput(be, direction);
                else if (state.getValue(PipeBlock.CONNECTIONS.get(direction)).equals("true"))
                    network.registerOutput(be, direction);
            }
        }
    }

    @Override
    protected void distribute(Level level, BlockPos pos, BlockState state)
    {
        network.distribute(level, state, pos, getTransfer());
    }

    @Override
    public FluidPipeNetwork getNetwork()
    {
        return network;
    }
}
