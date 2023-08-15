package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.registry.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TankItem extends BlockItem
{
    public TankItem(Properties p_40566_)
    {
        super(ModBlocks.TANK.get(), p_40566_.defaultDurability(20000));
    }

    @Override
    public void setDamage(ItemStack stack, int damage)
    {
        super.setDamage(stack, damage);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, list, flag);
        String fluidName = "Empty";
        if (stack.getTag().getInt("Amount") != 0)
            fluidName = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(stack.getTag().getString("FluidName"))).getFluidType().getDescription().getString();
        list.add(Component.literal("Fluid: " + fluidName).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
        list.add(Component.literal("Amount: " + stack.getTag().getInt("Amount") + " mB").withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
    }
}
