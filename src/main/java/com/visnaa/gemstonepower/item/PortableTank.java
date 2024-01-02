package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.capability.fluid.ItemFluidTank;
import com.visnaa.gemstonepower.init.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class PortableTank extends Item
{
    public PortableTank(Properties properties)
    {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        saveData(stack);
        if (!stack.is(ModItems.PORTABLE_TANK.get()))
            return InteractionResultHolder.pass(stack);

        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
        if (hitResult.getType() == HitResult.Type.MISS)
            return InteractionResultHolder.pass(stack);

        BlockPos pos = hitResult.getBlockPos().relative(hitResult.getDirection());
        if (level.getBlockState(hitResult.getBlockPos()).getBlock() instanceof SimpleWaterloggedBlock)
            pos = hitResult.getBlockPos();
        BlockState state = level.getBlockState(pos);

        IFluidHandler handler = stack.getCapability(Capabilities.FluidHandler.ITEM);
        if (handler != null && handler != ItemFluidTank.EMPTY)
        {
            if (level.mayInteract(player, pos) && player.mayUseItemAt(pos, hitResult.getDirection(), stack))
            {
                if ((((state.getBlock() instanceof SimpleWaterloggedBlock && handler.getFluidInTank(0).getFluid().isSame(Fluids.WATER)) || state.getFluidState().isEmpty()) || (state.getFluidState().is(handler.getFluidInTank(0).getFluid()) && player.isShiftKeyDown())) && handler.getFluidInTank(0).getAmount() >= FluidType.BUCKET_VOLUME)
                {
                    if (handler.drain(FluidType.BUCKET_VOLUME, IFluidHandler.FluidAction.SIMULATE).getAmount() != FluidType.BUCKET_VOLUME)
                        return InteractionResultHolder.pass(stack);
                    Fluid fluid = handler.getFluidInTank(0).getFluid();

                    if (state.getBlock() instanceof SimpleWaterloggedBlock waterloggedBlock)
                    {
                        if (!waterloggedBlock.canPlaceLiquid(player, level, pos, state, fluid))
                            return InteractionResultHolder.pass(stack);
                        waterloggedBlock.placeLiquid(level, pos, state, fluid.defaultFluidState());
                    }
                    else
                        level.setBlockAndUpdate(pos, fluid.defaultFluidState().createLegacyBlock());

                    handler.drain(FluidType.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
                    level.playSound(player, pos, Optional.ofNullable(fluid.getFluidType().getSound(SoundActions.BUCKET_EMPTY)).orElse(SoundEvents.BUCKET_EMPTY), SoundSource.BLOCKS);
                    saveData(stack);
                    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
                }
                else if (state.getFluidState().isSource() && (handler.getFluidInTank(0).isEmpty() || state.getFluidState().is(handler.getFluidInTank(0).getFluid())) && handler.getFluidInTank(0).getAmount() <= handler.getTankCapacity(0) - FluidType.BUCKET_VOLUME)
                {
                    Fluid fluid = state.getFluidState().getType();
                    if (handler.fill(new FluidStack(fluid, FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.SIMULATE) != FluidType.BUCKET_VOLUME)
                        return InteractionResultHolder.pass(stack);

                    if (state.getBlock() instanceof SimpleWaterloggedBlock waterloggedBlock)
                    {
                        if (waterloggedBlock.pickupBlock(player, level, pos, state).isEmpty())
                            return InteractionResultHolder.pass(stack);
                    }
                    else
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);

                    handler.fill(new FluidStack(fluid, FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE);
                    level.playSound(player, pos, Optional.ofNullable(fluid.getFluidType().getSound(SoundActions.BUCKET_FILL)).orElse(SoundEvents.BUCKET_FILL), SoundSource.BLOCKS);
                    level.gameEvent(player, GameEvent.FLUID_PLACE, pos);
                    saveData(stack);
                    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
                }
            }
        }
        return InteractionResultHolder.pass(stack);
    }

    public static IFluidHandlerItem getFluidHandler(ItemStack stack, Void context)
    {
        ItemFluidTank handler = new ItemFluidTank(stack, 5 * FluidType.BUCKET_VOLUME);
        if (stack.hasTag() && stack.getTag().contains("FluidTank"))
            handler.readFromNBT(stack.getTag().getCompound("FluidTank"));
        return handler;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, list, flag);

        IFluidHandler handler = stack.getCapability(Capabilities.FluidHandler.ITEM);
        if (handler != null && handler != ItemFluidTank.EMPTY)
        {
            String fluidName = "Empty";
            if (handler.getFluidInTank(0).getAmount() != 0)
                fluidName = handler.getFluidInTank(0).getFluid().getFluidType().getDescription().getString();
            list.add(Component.literal("Fluid: " + fluidName).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
            list.add(Component.literal("Amount: " + handler.getFluidInTank(0).getAmount() + " mB / " + handler.getTankCapacity(0) + " mB").withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
        }
    }

    private void saveData(ItemStack stack)
    {
        IFluidHandler handler = stack.getCapability(Capabilities.FluidHandler.ITEM);
        if (handler instanceof ItemFluidTank itemFluidTank && handler != ItemFluidTank.EMPTY)
        {
            stack.getOrCreateTag().put("FluidTank", itemFluidTank.writeToNBT(new CompoundTag()));
            if (handler.getFluidInTank(0).getFluid().isSame(Fluids.LAVA))
                stack.getOrCreateTag().putInt("FluidColor", 0xFF711C);
            else if (!handler.getFluidInTank(0).isEmpty())
                stack.getOrCreateTag().putInt("FluidColor", IClientFluidTypeExtensions.of(handler.getFluidInTank(0).getFluid()).getTintColor());
            else stack.getOrCreateTag().putInt("FluidColor", 0xFFFFFF);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack)
    {
        IFluidHandler handler = stack.getCapability(Capabilities.FluidHandler.ITEM);
        return !stack.isEmpty() && handler != null && handler != ItemFluidTank.EMPTY && handler.getFluidInTank(0).getAmount() > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack)
    {
        IFluidHandler handler = stack.getCapability(Capabilities.FluidHandler.ITEM);
        if (!stack.isEmpty() && handler != null && handler != ItemFluidTank.EMPTY)
            return Math.round(handler.getFluidInTank(0).getAmount() * 13.0F / handler.getTankCapacity(0));
        return 0;
    }

    @Override
    public int getBarColor(ItemStack stack)
    {
        IFluidHandler handler = stack.getCapability(Capabilities.FluidHandler.ITEM);
        if (!stack.isEmpty() && handler != null && handler != ItemFluidTank.EMPTY)
            return Mth.hsvToRgb(Math.max(0.0F, (float) handler.getFluidInTank(0).getAmount() / handler.getTankCapacity(0)) / 3.0F, 1.0F, 1.0F);
        return 0x00FFFF;
    }
}
