package com.visnaa.gemstonepower.block.entity.cable;

import com.visnaa.gemstonepower.block.cable.ElectrumCableBlock;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.network.energy.EnergyNetwork;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class ElectrumCableBE extends CableBE
{
    public ElectrumCableBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ELECTRUM_CABLE.get(), pos, state, ServerConfig.ENERGY_TRANSFER_RATE.get() * 4);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, ElectrumCableBE blockEntity)
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
            if (be != null && ((!(be instanceof CableBE) || be instanceof ElectrumCableBE) || be.getCapability(ForgeCapabilities.ENERGY, direction).isPresent()))
            {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(ElectrumCableBlock.CONNECTIONS.get(direction.getOpposite()), true));
                setChanged(level, pos, state);
            } else {
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(ElectrumCableBlock.CONNECTIONS.get(direction.getOpposite()), false));
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
            if (be != null && be instanceof ElectrumCableBE cable && cable.network != null)
            {
                cable.network.merge(this.network);
                setChanged(level, pos, state);
                return;
            }
        }
    }
}