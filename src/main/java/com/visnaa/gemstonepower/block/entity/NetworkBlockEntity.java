package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.network.energy.EnergyNetwork;
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

public abstract class NetworkBlockEntity extends BlockEntity
{
    private final int TRANSFER_RATE;
    public EnergyNetwork network = new EnergyNetwork();

    public NetworkBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int transferRate)
    {
        super(type, pos, state);
        this.TRANSFER_RATE = transferRate;
    }

    protected void refreshNetwork(Level level, BlockPos pos, BlockState state)
    {
        if (this.network == null) this.network = new EnergyNetwork();
        this.network.refresh();
        if (this.network == null) this.network = new EnergyNetwork();
        this.network.registerToNetwork(this);
        this.setChanged();

        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && be instanceof NetworkBlockEntity && ((NetworkBlockEntity) be).network != null)
            {
                ((NetworkBlockEntity) be).network.merge(this.network);
                setChanged(level, pos, state);
                return;
            }
        }
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
                        handler.receiveEnergy((int) energy, false);
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
