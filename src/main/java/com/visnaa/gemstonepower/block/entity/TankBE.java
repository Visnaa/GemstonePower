package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.block.entity.machine.FluidMachineBE;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class TankBE extends FluidMachineBE<Recipe<Container>>
{
    private long lastTransfer;

    public TankBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.TANK.get(), pos, state, MachineUtil.createFluidTank(Fluids.EMPTY, 20000));
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        transferDown(level, pos, state);
    }

    public void transferDown(Level level, BlockPos pos, BlockState state)
    {
        if (level.isClientSide() || !state.is(state.getBlock()))
            return;

        if (lastTransfer >= level.getGameTime())
            return;
        lastTransfer = level.getGameTime();

        if (level.getBlockEntity(pos.below()) instanceof TankBE other)
        {
            if (level.getCapability(Capabilities.FluidHandler.BLOCK, other.getBlockPos(), other.getBlockState(), other, Direction.UP) == null || level.getCapability(Capabilities.FluidHandler.BLOCK, getBlockPos(), getBlockState(), this, Direction.DOWN) == null)
                return;

            if (tanks.getFluidInTank(0).isEmpty())
                return;

            if (other.getTank(0).getSpace() == 0 || tanks.getFluidInTank(0).getAmount() == 0)
                return;

            FluidUtil.tryFluidTransfer(other.getTank(0), getTank(0), 1, true);
        }

        if (level.getBlockEntity(pos.below()) != null && FluidUtil.getFluidHandler(level, pos.below(), Direction.UP).isPresent())
        {
            if (level.getCapability(Capabilities.FluidHandler.BLOCK, getBlockPos(), getBlockState(), this, Direction.DOWN) == null)
                return;

            if (tanks.getFluidInTank(0).isEmpty())
                return;

            IFluidHandler other = FluidUtil.getFluidHandler(level, pos.below(), Direction.UP).orElse(null);

            for (int tank = 0; tank < other.getTanks(); tank++)
            {
                int maxTransfer = Math.min(other.getTankCapacity(tank) - other.getFluidInTank(tank).getAmount(), tanks.getFluidInTank(0).getAmount());
                if (maxTransfer <= 0)
                    return;

                FluidUtil.tryFluidTransfer(other, getTank(0), maxTransfer, true);
            }
        }
    }
}
