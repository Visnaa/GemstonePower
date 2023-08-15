package com.visnaa.gemstonepower.block.entity.pipe.cable;

import com.visnaa.gemstonepower.block.entity.TickingBlockEntity;
import com.visnaa.gemstonepower.block.pipe.cable.AluminumCableBlock;
import com.visnaa.gemstonepower.pipe.energy.EnergyNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class CableBE extends BlockEntity implements TickingBlockEntity
{
    private final int TRANSFER_RATE;
    public EnergyNetwork network = new EnergyNetwork();

    public CableBE(BlockEntityType<?> type, BlockPos pos, BlockState state, int transferRate)
    {
        super(type, pos, state);
        this.TRANSFER_RATE = transferRate;
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        refreshInputs(level, pos, state);
        distributeEnergy(level, pos, state);
        checkExplode(level, pos, state);
    }

    protected <T extends CableBE> void updateConnections(Level level, BlockPos pos, BlockState state, Class<T> type)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && (type.isAssignableFrom(be.getClass()) || be.getCapability(ForgeCapabilities.ENERGY, direction).isPresent()))
            {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(AluminumCableBlock.CONNECTIONS.get(direction.getOpposite()), true));
                setChanged(level, pos, state);
            } else {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(AluminumCableBlock.CONNECTIONS.get(direction.getOpposite()), false));
                setChanged(level, pos, state);
            }
        }
    }

    protected <T extends CableBE> void refreshNetwork(Level level, BlockPos pos, BlockState state, Class<T> type)
    {
        if (this.network == null) this.network = new EnergyNetwork();
        this.network.refresh();
        if (this.network == null) this.network = new EnergyNetwork();
        this.network.registerToNetwork(this);
        this.setChanged();

        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && type.isAssignableFrom(be.getClass()) && ((CableBE) be).network != null)
                ((CableBE) be).network.merge(this.network);
        }
        setChanged(level, pos, state);
    }

    protected void refreshInputs(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && be.getCapability(ForgeCapabilities.ENERGY, direction).isPresent())
            {
                if (be.getCapability(ForgeCapabilities.ENERGY, direction).map(IEnergyStorage::canExtract).orElse(false) &&
                    be.getCapability(ForgeCapabilities.ENERGY, direction).map(IEnergyStorage::canReceive).orElse(false))
                {
                    network.registerBattery(be);
                }
                else if (be.getCapability(ForgeCapabilities.ENERGY, direction).map(IEnergyStorage::canExtract).orElse(false))
                {
                    network.registerInput(be);
                }
            }
        }
    }

    protected void distributeEnergy(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction));
            if (be != null && be.getCapability(ForgeCapabilities.ENERGY, direction).isPresent() &&
                    be.getCapability(ForgeCapabilities.ENERGY, direction).map(IEnergyStorage::canReceive).orElse(false) && this.network.getEnergy() > 0)
            {
                be.getCapability(ForgeCapabilities.ENERGY, direction).map(handler -> {
                    long energy = Math.min(this.TRANSFER_RATE, this.network.getEnergy());
                    int received = handler.receiveEnergy((int) energy, true);
                    if (this.network.withdrawFromNetwork(received))
                    {
                        handler.receiveEnergy((int) energy, false);
                    }
                    return true;
                });
                be.setChanged();
            }
        }
    }

    protected void checkExplode(Level level, BlockPos pos, BlockState state)
    {
        if (state.hasProperty(BooleanProperty.create("isolated")) && !state.getValue(BooleanProperty.create("isolated")))
        {
            for (Direction direction : Direction.values())
            {
                if (level.getBlockState(pos.relative(direction)).is(Blocks.WATER))
                {
                    this.network.explode();
                }
            }
        }
    }
}
