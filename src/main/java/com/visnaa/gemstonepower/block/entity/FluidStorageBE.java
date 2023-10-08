package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.capability.fluid.MultiFluidTank;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.HashMap;

public interface FluidStorageBE
{
    void setFluid(int tank, FluidStack stack);

    void setCapacity(int tank, int capacity);

    FluidTank getTank(int tank);

    MultiFluidTank createTanks(HashMap<Fluid, Integer> fluidCapacityMap);

    static InteractionResult fillFromItem(Level level, Player player, InteractionHand hand, MultiFluidTank tanks)
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
}
