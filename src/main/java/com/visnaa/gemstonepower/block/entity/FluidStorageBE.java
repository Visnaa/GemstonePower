package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.capability.fluid.ItemFluidTank;
import com.visnaa.gemstonepower.capability.fluid.MultiFluidTank;
import com.visnaa.gemstonepower.item.PortableTank;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;

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
                        player.playSound(SoundEvents.BUCKET_FILL);
                    else if (item.getItem() instanceof PortableTank)
                        handlePortableTank(item, itemHandler);
                    return InteractionResult.sidedSuccess(level.isClientSide());

                } else
                {
                    FluidStack fluid = FluidUtil.tryFluidTransfer(blockHandler, itemHandler, maxTransfer, true);
                    if (fluid.isEmpty())
                        continue;

                    if (level.isClientSide())
                        player.playSound(SoundEvents.BUCKET_EMPTY);
                    else if (item.getItem() instanceof PortableTank)
                        handlePortableTank(item, itemHandler);
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            }
        }
        return InteractionResult.PASS;
    }

    private static void handlePortableTank(ItemStack stack, @Nullable IFluidHandlerItem itemHandler)
    {
        if (stack.getItem() instanceof PortableTank)
        {
            if (itemHandler instanceof ItemFluidTank itemFluidTank && itemHandler != ItemFluidTank.EMPTY)
            {
                stack.getOrCreateTag().put("FluidTank", itemFluidTank.writeToNBT(new CompoundTag()));
                if (itemHandler.getFluidInTank(0).getFluid().isSame(Fluids.LAVA))
                    stack.getOrCreateTag().putInt("FluidColor", 0xFF711C);
                else if (!itemHandler.getFluidInTank(0).isEmpty())
                    stack.getOrCreateTag().putInt("FluidColor", IClientFluidTypeExtensions.of(itemHandler.getFluidInTank(0).getFluid()).getTintColor());
                else stack.getOrCreateTag().putInt("FluidColor", 0xFFFFFF);
            }
        }
    }
}
