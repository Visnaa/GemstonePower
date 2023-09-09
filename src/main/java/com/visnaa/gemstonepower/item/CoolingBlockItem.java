package com.visnaa.gemstonepower.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CoolingBlockItem extends BlockItem
{
    private int cooling;

    public CoolingBlockItem(Block block, Properties properties, int cooling)
    {
        super(block, properties);
        this.cooling = cooling;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.literal("§7Cooling: §b" + cooling + " HU/t §7per side"));
    }
}
