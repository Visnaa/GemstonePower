package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.block.entity.FluidStorageBE;
import com.visnaa.gemstonepower.capability.fluid.MultiFluidTank;
import com.visnaa.gemstonepower.menu.machine.MachineMenu;
import com.visnaa.gemstonepower.network.packet.FluidSyncS2C;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;

public abstract class FluidMachineBE<T extends Recipe<Container>> extends MachineBE<T> implements FluidStorageBE
{
    protected final MultiFluidTank tanks;

    public FluidMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState state, HashMap<Fluid, Integer> tankMap)
    {
        this(type, null, pos, state, 0, 0, null, tankMap);
    }

    public FluidMachineBE(BlockEntityType<?> type, RecipeType<T> recipe, BlockPos pos, BlockState state, int inputSlotCount, int outputSlotCount, MenuType<? extends MachineMenu> menu, HashMap<Fluid, Integer> tankMap)
    {
        super(type, recipe, pos, state, inputSlotCount, outputSlotCount, menu);
        tanks = createTanks(tankMap);
    }

    @Override
    public void setChanged()
    {
        super.setChanged();
        tanks.setChanged(level, getBlockPos());
    }

    @Override
    public void setFluid(int tank, FluidStack stack)
    {
        getTank(tank).setFluid(stack);
    }

    @Override
    public void setCapacity(int tank, int capacity)
    {
        getTank(tank).setCapacity(capacity);
    }

    @Override
    public FluidTank getTank(int tank)
    {
        return tanks.getTank(tank);
    }

    public InteractionResult fillFromItem(Level level, Player player, InteractionHand hand)
    {
        return FluidStorageBE.fillFromItem(level, player, hand, tanks);
    }

    @Override
    public MultiFluidTank createTanks(HashMap<Fluid, Integer> fluidCapacityMap)
    {
        return new MultiFluidTank(fluidCapacityMap, (tank) -> {
            if (level != null && !level.isClientSide())
                PacketDistributor.ALL.noArg().send(new FluidSyncS2C(tanks.getFluidInTank(tank), getTank(tank).getCapacity(), tank, getBlockPos()));
        });
    }

    @Override
    public IFluidHandler getFluidHandler(Direction dir)
    {
        return tanks;
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tanks.writeToNbt(tag);
        setChanged();
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        tanks.readFromNbt(tag);
        setChanged();
    }
}
