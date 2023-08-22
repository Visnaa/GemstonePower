package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.init.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TankItem extends BlockItem
{
    public TankItem(Properties properties)
    {
        super(ModBlocks.TANK.get(), properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, list, flag);
        if (stack.hasTag())
        {
            String fluidName = "Empty";
            if (stack.getTag().getInt("Amount") != 0)
                fluidName = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(stack.getTag().getString("FluidName"))).getFluidType().getDescription().getString();
            list.add(Component.literal("Fluid: " + fluidName).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
            list.add(Component.literal("Amount: " + stack.getTag().getInt("Amount") + " mB").withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack)
    {
        return !stack.isEmpty() && stack.hasTag() && stack.getTag().getInt("Amount") > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack)
    {
        if (!stack.isEmpty() && stack.hasTag())
            return Math.round(stack.getTag().getInt("Amount") * 13.0F / 20000);
        return 0;
    }

    @Override
    public int getBarColor(ItemStack stack)
    {
        if (!stack.isEmpty() && stack.hasTag())
            return Mth.hsvToRgb(Math.max(0.0F, (float) stack.getTag().getInt("Amount") / 20000) / 3.0F, 1.0F, 1.0F);
        return 0x00FFFF;
    }
}
