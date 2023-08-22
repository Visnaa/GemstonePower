package com.visnaa.gemstonepower.block.entity.pipe;

import com.visnaa.gemstonepower.block.entity.TickingBlockEntity;
import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.pipe.PipeNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public abstract class PipeBE extends BlockEntity implements TickingBlockEntity
{
    protected final HashMap<Direction, Boolean> prohibitedConnections;

    public PipeBE(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        prohibitedConnections = new HashMap<>();
        for (Direction direction : Direction.values())
            prohibitedConnections.put(direction, false);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        updateProhibitedConnections(level, pos, state);

        updateConnections(level, pos, state);
        refreshNetwork(level, pos, state);
        registerNeighbours(level, pos, state);
        distribute(level, pos, state);
    }

    public abstract int getTransfer();

    public abstract PipeNetwork<? extends PipeBE> getNetwork();

    protected abstract void updateConnections(Level level, BlockPos pos, BlockState state);
    protected abstract void refreshNetwork(Level level, BlockPos pos, BlockState state);
    protected abstract void registerNeighbours(Level level, BlockPos pos, BlockState state);
    protected abstract void distribute(Level level, BlockPos pos, BlockState state);

    public void playerChangedConnection(Level level, BlockPos pos, Direction direction, boolean isFalse)
    {
        if (level == null || level.isClientSide())
            return;

        BlockEntity be = level.getBlockEntity(pos.relative(direction));
        if (be != null && isFalse)
            prohibitedConnections.put(direction, true);
        else
        {
            if (be instanceof PipeBE pipe)
                pipe.prohibitedConnections.put(direction.getOpposite(), false);
            prohibitedConnections.put(direction, false);
        }
        if (getNetwork() != null)
            getNetwork().destroy(this);
    }

    private void updateProhibitedConnections(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            if (!prohibitedConnections.get(direction))
                continue;

            BlockEntity be = level.getBlockEntity(pos.relative(direction));
            if (be == null)
            {
                prohibitedConnections.put(direction, false);
                continue;
            }
            BlockState otherState = level.getBlockState(pos.relative(direction));
            if (be instanceof PipeBE pipe && otherState.getBlock() instanceof PipeBlock && !otherState.getValue(PipeBlock.CONNECTIONS.get(direction.getOpposite())).equals("false"))
            {
                state = state.setValue(PipeBlock.CONNECTIONS.get(direction), "false");
                otherState = otherState.setValue(PipeBlock.CONNECTIONS.get(direction.getOpposite()), "false");
                pipe.prohibitedConnections.put(direction.getOpposite(), true);

                if (pipe.getNetwork() != null)
                    pipe.getNetwork().destroy(pipe);
                if (getNetwork() != null && !state.getValue(PipeBlock.CONNECTIONS.get(direction)).equals("false"))
                    getNetwork().destroy(this);

                level.setBlockAndUpdate(pos, state);
                level.setBlockAndUpdate(pos.relative(direction), otherState);
            }
        }
    }

    protected boolean canMerge(Level level, BlockPos pos, Direction direction)
    {
        BlockEntity be = level.getBlockEntity(pos.relative(direction));
        if (!(be instanceof PipeBE pipe))
            return false;

        if (prohibitedConnections.get(direction))
            pipe.prohibitedConnections.put(direction.getOpposite(), true);
        else if (pipe.prohibitedConnections.get(direction.getOpposite()))
            prohibitedConnections.put(direction, true);
        else
            return true;
        return false;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        super.saveAdditional(nbt);
        CompoundTag tag = new CompoundTag();
        for (Direction direction : Direction.values())
            tag.putBoolean(direction.getName(), prohibitedConnections.get(direction));
        nbt.put("ProhibitedConnections", tag);
    }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);
        CompoundTag tag = nbt.getCompound("ProhibitedConnections");
        for (Direction direction : Direction.values())
            prohibitedConnections.put(direction, tag.getBoolean(direction.getName()));
    }
}
