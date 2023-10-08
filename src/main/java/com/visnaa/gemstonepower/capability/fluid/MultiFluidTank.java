package com.visnaa.gemstonepower.capability.fluid;

import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.FluidSyncS2C;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class MultiFluidTank implements IFluidHandler
{
    private List<FluidTank> tanks;

    public MultiFluidTank(HashMap<Fluid, Integer> fluidCapacityMap, Consumer<Integer> contentsChanged)
    {
        List<FluidTank> list = new ArrayList<>();
        for (Fluid fluid : fluidCapacityMap.keySet())
        {
            if (fluid.isSame(Fluids.EMPTY))
                list.add(new FluidTank(Math.max(fluidCapacityMap.get(fluid), 0)) {
                    @Override
                    protected void onContentsChanged()
                    {
                        super.onContentsChanged();
                        contentsChanged.accept(MultiFluidTank.this.tanks.indexOf(this));
                    }

                    @Override
                    public void setFluid(FluidStack stack)
                    {
                        super.setFluid(stack);
                        onContentsChanged();
                    }
                });
            else
                list.add(new FluidTank(Math.max(fluidCapacityMap.get(fluid), 0), f -> f.getFluid().isSame(fluid)) {
                    @Override
                    public void onContentsChanged()
                    {
                        super.onContentsChanged();
                        contentsChanged.accept(MultiFluidTank.this.tanks.indexOf(this));
                    }

                    @Override
                    public void setFluid(FluidStack stack)
                    {
                        super.setFluid(stack);
                        onContentsChanged();
                    }
                });
        }
        this.tanks = list.stream().toList();
    }

    public void setChanged(Level level, BlockPos pos)
    {
        if (level != null && !level.isClientSide())
            tanks.forEach(tank -> ModPackets.sendToClient(new FluidSyncS2C(tank.getFluid(), tank.getCapacity(), tanks.indexOf(tank), pos)));
    }

    public void writeToNbt(CompoundTag nbt)
    {
        CompoundTag tag = new CompoundTag();
        tanks.forEach(tank -> tank.writeToNBT(tag));
        nbt.put("MultiFluidTank", tag);
    }

    public void readFromNbt(CompoundTag nbt)
    {
        CompoundTag tag = nbt.getCompound("MultiFluidTank");
        tanks.forEach(tank -> tank.readFromNBT(tag));
    }

    public FluidTank getTank(int tank)
    {
        return tanks.get(tank);
    }

    @Override
    public int getTanks()
    {
        return tanks.size();
    }

    @NotNull
    @Override
    public FluidStack getFluidInTank(int tank)
    {
        return tanks.get(tank).getFluid();
    }

    @Override
    public int getTankCapacity(int tank)
    {
        return tanks.get(tank).getCapacity();
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack)
    {
        return tanks.get(tank).isFluidValid(stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action)
    {
        for (FluidTank tank : tanks)
        {
            int amount = tank.fill(resource, action);
            if (amount > 0)
                return amount;
        }
        return 0;
    }

    @NotNull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action)
    {
        for (FluidTank tank : tanks)
        {
            FluidStack fluid = tank.drain(resource, action);
            if (!fluid.isEmpty())
                return fluid;
        }
        return FluidStack.EMPTY;
    }

    @NotNull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action)
    {
        for (FluidTank tank : tanks)
        {
            FluidStack fluid = tank.drain(maxDrain, action);
            if (!fluid.isEmpty())
                return fluid;
        }
        return FluidStack.EMPTY;
    }
}
