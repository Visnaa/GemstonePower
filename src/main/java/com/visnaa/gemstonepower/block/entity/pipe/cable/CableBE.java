package com.visnaa.gemstonepower.block.entity.pipe.cable;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.capability.PipeNetwork;
import com.visnaa.gemstonepower.capability.energy.EnergyNetwork;
import com.visnaa.gemstonepower.capability.energy.EnergyStorage;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.common.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public abstract class CableBE extends PipeBE
{
    public EnergyNetwork network = new EnergyNetwork();
    private final int transfer;

    public CableBE(BlockEntityType<?> type, BlockPos pos, BlockState state, int transfer)
    {
        super(type, pos, state);
        this.transfer = (int) (ServerConfig.ENERGY_CABLE_TRANSFER_MUL.get() * transfer);
    }

    @Override
    public int getTransfer()
    {
        return transfer;
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        super.tick(level, pos, state);
        checkExplode(level, pos, state);
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
            else if (be != null && (getClass().isAssignableFrom(be.getClass()) || be.getCapability(Capabilities.ENERGY, direction).isPresent()) && state.getValue(PipeBlock.CONNECTIONS.get(direction)).equals("false"))
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
            this.network = new EnergyNetwork();
        this.network.refresh();
        if (this.network == null)
            this.network = new EnergyNetwork();
        this.network.registerToNetwork(this);
        this.setChanged();

        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction));
            if (be instanceof CableBE cable && getClass().isAssignableFrom(cable.getClass()) && cable.network != null && canMerge(level, pos, direction))
                cable.network.merge(this.network);
        }
        setChanged(level, pos, state);
    }

    @Override
    protected void registerNeighbours(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction));
            if (be != null && be.getCapability(Capabilities.ENERGY, direction).isPresent())
            {
                IEnergyStorage handler = be.getCapability(Capabilities.ENERGY, direction).orElse(EnergyStorage.EMPTY);
                if (handler == EnergyStorage.EMPTY)
                    continue;

                if (handler.canExtract() && handler.canReceive() && state.getValue(PipeBlock.CONNECTIONS.get(direction)).equals("true"))
                    network.registerBattery(be, direction);
                else if (handler.canExtract() && state.getValue(PipeBlock.CONNECTIONS.get(direction)).equals("true"))
                    network.registerInput(be, direction);
                else if (handler.canReceive() && state.getValue(PipeBlock.CONNECTIONS.get(direction)).equals("true"))
                    network.registerOutput(be, direction);
            }
        }
    }

    protected void distribute(Level level, BlockPos pos, BlockState state)
    {
        network.distribute(level, state, pos, getTransfer());
    }

    protected void checkExplode(Level level, BlockPos pos, BlockState state)
    {
        if (state.hasProperty(BooleanProperty.create("isolated")) && !state.getValue(BooleanProperty.create("isolated")))
            for (Direction direction : Direction.values())
                if (level.getBlockState(pos.relative(direction)).is(Blocks.WATER))
                    this.network.explode();
    }

    @Override
    public PipeNetwork<? extends PipeBE> getNetwork()
    {
        return network;
    }
}
