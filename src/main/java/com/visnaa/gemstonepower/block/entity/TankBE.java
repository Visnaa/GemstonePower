package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class TankBE extends FluidStorageBE
{
    private static final HashMap<Fluid, Integer> TANK;

    static
    {
        TANK = new HashMap<>();
        TANK.put(Fluids.EMPTY, 20000);
    }

    public TankBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.TANK.get(), pos, state, TANK);
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

        if (level.getBlockEntity(pos.below()) instanceof TankBE other)
        {
            if (!other.getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent() || !getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent())
                return;

            if (tanks.getFluidInTank(0).isEmpty())
                return;

            int maxTransfer = Math.min(other.getTank(0).getSpace(), tanks.getFluidInTank(0).getAmount());
            if (maxTransfer <= 0)
                return;

            FluidUtil.tryFluidTransfer(other.getTank(0), getTank(0), maxTransfer, true);
        }

        if (level.getBlockEntity(pos.below()) != null && FluidUtil.getFluidHandler(level, pos.below(), Direction.UP).isPresent())
        {
            if (!getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent())
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

    @NotNull
    @Override
    public<T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction)
    {
        if (capability == ForgeCapabilities.FLUID_HANDLER)
            return LazyOptional.of(() -> tanks).cast();
        return LazyOptional.empty();
    }
}
