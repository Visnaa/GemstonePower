package com.visnaa.gemstonepower.capability.fluid;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

public class ItemFluidTank implements IFluidHandlerItem
{
    private ItemStack container;
    private int capacity;
    private FluidTank fluidTank;

    public static final ItemFluidTank EMPTY = new ItemFluidTank(ItemStack.EMPTY, 0);

    public ItemFluidTank(ItemStack container, int capacity)
    {
        this.container = container;
        this.capacity = capacity;
        this.fluidTank = new FluidTank(capacity);
    }

    @Override
    public @NotNull ItemStack getContainer()
    {
        return container;
    }

    @Override
    public int getTanks()
    {
        return 1;
    }

    @Override
    public @NotNull FluidStack getFluidInTank(int tank)
    {
        return fluidTank.getFluidInTank(0);
    }

    @Override
    public int getTankCapacity(int tank)
    {
        return capacity;
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack)
    {
        return true;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action)
    {
        return fluidTank.fill(resource, action);
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, FluidAction action)
    {
        return fluidTank.drain(resource, action);
    }

    @Override
    public @NotNull FluidStack drain(int maxDrain, FluidAction action)
    {
        return fluidTank.drain(maxDrain, action);
    }

    public void readFromNBT(CompoundTag nbt)
    {
        fluidTank.readFromNBT(nbt);
    }

    public CompoundTag writeToNBT(CompoundTag nbt) {

        return fluidTank.writeToNBT(nbt);
    }
}
