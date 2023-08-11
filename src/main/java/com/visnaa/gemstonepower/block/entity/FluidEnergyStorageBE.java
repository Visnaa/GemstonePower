package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.FluidSyncS2C;
import com.visnaa.gemstonepower.pipe.fluid.MultiFluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class FluidEnergyStorageBE extends BlockEntity implements TickingBlockEntity
{
    protected final MultiFluidTank tanks;

    public FluidEnergyStorageBE(BlockEntityType<? extends FluidEnergyStorageBE> type, BlockPos pos, BlockState state, HashMap<Fluid, Integer> tanks)
    {
        super(type, pos, state);
        this.tanks = new MultiFluidTank(tanks, this::fluidChanged);
    }

    @Override
    public void setChanged()
    {
        super.setChanged();
        tanks.setChanged(level, getBlockPos());
    }

    private void fluidChanged(int tank)
    {
        setChanged();
        if (level != null && !level.isClientSide())
            ModPackets.sendToClient(new FluidSyncS2C(tanks.getFluidInTank(tank), tanks.getTankCapacity(tank), tank, getBlockPos()));
    }

    public void setFluid(int tank, FluidStack stack)
    {
        getTank(tank).setFluid(stack);
    }

    public void setCapacity(int tank, int capacity)
    {
        getTank(tank).setCapacity(capacity);
    }

    public FluidTank getTank(int tank)
    {
        return tanks.getTank(tank);
    }

    public InteractionResult fillFromItem(Level level, Player player, InteractionHand hand)
    {
        ItemStack item = player.getItemInHand(hand);
        if (item.isEmpty())
            return InteractionResult.PASS;

        for (int tank = 0; tank < tanks.getTanks(); tank++)
        {
            if (FluidUtil.getFluidHandler(item).isPresent() && tanks.getTank(tank) != null)
            {
                boolean isItemEmpty = FluidUtil.getFluidContained(item).orElse(FluidStack.EMPTY).isEmpty();
                IFluidHandlerItem itemHandler = FluidUtil.getFluidHandler(item).orElse(null);
                IFluidHandler blockHandler = tanks.getTank(tank);
                int maxTransfer = Math.min(itemHandler.getTankCapacity(0), blockHandler.getTankCapacity(tank));
                if (maxTransfer <= 0)
                    continue;

                if (isItemEmpty)
                {
                    FluidStack fluid = FluidUtil.tryFluidTransfer(itemHandler, blockHandler, maxTransfer, true);
                    if (fluid.isEmpty())
                        continue;

                    if (level.isClientSide())
                    {
                        player.playSound(SoundEvents.BUCKET_FILL);
                        return InteractionResult.SUCCESS;
                    } else
                    {
                        item.shrink(1);
                        if (item.getCount() <= 0)
                            player.setItemInHand(hand, itemHandler.getContainer());
                        else if (!player.addItem(itemHandler.getContainer()))
                            player.drop(itemHandler.getContainer(), true);
                        return InteractionResult.CONSUME;
                    }

                } else
                {
                    FluidStack fluid = FluidUtil.tryFluidTransfer(blockHandler, itemHandler, maxTransfer, true);
                    if (fluid.isEmpty())
                        continue;

                    if (level.isClientSide())
                    {
                        player.playSound(SoundEvents.BUCKET_EMPTY);
                        return InteractionResult.SUCCESS;
                    } else
                    {
                        item.shrink(1);
                        if (item.getCount() <= 0)
                            player.setItemInHand(hand, itemHandler.getContainer());
                        else if (!player.addItem(itemHandler.getContainer()))
                            player.drop(itemHandler.getContainer(), true);
                        return InteractionResult.CONSUME;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    @NotNull
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction)
    {
        if (capability == ForgeCapabilities.FLUID_HANDLER)
            return LazyOptional.of(() -> tanks).cast();
        return LazyOptional.empty();
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        tanks.readFromNbt(tag);
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tanks.writeToNbt(tag);
        setChanged();
    }
}
