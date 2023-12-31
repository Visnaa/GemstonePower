package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.capability.fluid.ItemFluidTank;
import com.visnaa.gemstonepower.init.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;
import java.util.List;

public class TankItem extends BlockItem
{
    public TankItem(Properties properties)
    {
        super(ModBlocks.TANK.get(), properties);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt)
    {
        ItemFluidTank handler = new ItemFluidTank(stack, 20 * FluidType.BUCKET_VOLUME);
        if (stack.hasTag() && stack.getTag().contains("FluidTank"))
            handler.readFromNBT(stack.getTag().getCompound("FluidTank"));
        return handler;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, list, flag);

        IFluidHandler handler = stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(ItemFluidTank.EMPTY);
        if (handler != ItemFluidTank.EMPTY)
        {
            String fluidName = "Empty";
            if (handler.getFluidInTank(0).getAmount() != 0)
                fluidName = handler.getFluidInTank(0).getFluid().getFluidType().getDescription().getString();
            list.add(Component.literal("Fluid: " + fluidName).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
            list.add(Component.literal("Amount: " + handler.getFluidInTank(0).getAmount() + " mB").withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack)
    {
        IFluidHandler handler = stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(ItemFluidTank.EMPTY);
        return !stack.isEmpty() && handler != ItemFluidTank.EMPTY && handler.getFluidInTank(0).getAmount() > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack)
    {
        IFluidHandler handler = stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(ItemFluidTank.EMPTY);
        if (!stack.isEmpty() && handler != ItemFluidTank.EMPTY)
            return Math.round(handler.getFluidInTank(0).getAmount() * 13.0F / handler.getTankCapacity(0));
        return 0;
    }

    @Override
    public int getBarColor(ItemStack stack)
    {
        IFluidHandler handler = stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(ItemFluidTank.EMPTY);
        if (!stack.isEmpty() && handler != ItemFluidTank.EMPTY)
            return Mth.hsvToRgb(Math.max(0.0F, (float) handler.getFluidInTank(0).getAmount() / handler.getTankCapacity(0)) / 3.0F, 1.0F, 1.0F);
        return 0x00FFFF;
    }
}
