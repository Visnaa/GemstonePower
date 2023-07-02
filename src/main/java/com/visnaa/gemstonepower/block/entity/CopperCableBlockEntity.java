package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.block.CopperCableBlock;
import com.visnaa.gemstonepower.config.CommonConfig;
import com.visnaa.gemstonepower.network.energy.EnergyNetwork;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class CopperCableBlockEntity extends NetworkBlockEntity
{
    public CopperCableBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.COPPER_CABLE.get(), pos, state, CommonConfig.ENERGY_TRANSFER_RATE.get());
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, CopperCableBlockEntity blockEntity)
    {
        blockEntity.updateConnections(level, pos, state);
        blockEntity.refreshNetwork(level, pos, state);
        blockEntity.refreshInputs(level, pos, state);
        blockEntity.distributeEnergy(level, pos, state);
        blockEntity.checkExplode(level, pos, state);
    }

    protected void updateConnections(Level level, BlockPos pos, BlockState state)
    {
        for (Direction direction : Direction.values())
        {
            BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
            if (be != null && ((!(be instanceof NetworkBlockEntity) || be instanceof CopperCableBlockEntity) || be.getCapability(ForgeCapabilities.ENERGY, direction).isPresent()))
            {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(CopperCableBlock.CONNECTIONS.get(direction.getOpposite()), true));
                setChanged(level, pos, state);
            } else {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(CopperCableBlock.CONNECTIONS.get(direction.getOpposite()), false));
                setChanged(level, pos, state);
            }
        }
    }

    @Override
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
            if (be != null && be instanceof CopperCableBlockEntity cable && cable.network != null)
            {
                cable.network.merge(this.network);
                setChanged(level, pos, state);
                return;
            }
        }
    }
}
