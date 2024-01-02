package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.GemstoneManipulatorRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class GemstoneManipulatorBE extends MachineBE<GemstoneManipulatorRecipe>
{
    public GemstoneManipulatorBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.GEMSTONE_MANIPULATOR.get(), ModRecipes.GEMSTONE_MANIPULATOR_RECIPE, pos, state, 2, 1, ModMenus.GEMSTONE_MANIPULATOR.get());
    }

    protected boolean canProcess(RegistryAccess access, @Nullable GemstoneManipulatorRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (!items.get(0).isEmpty() && !items.get(1).isEmpty() && recipe != null)
        {
            ItemStack result = recipe.assemble(this, access);
            if (result.isEmpty())
                return false;
            else
            {
                ItemStack output = items.get(2);
                if (output.isEmpty())
                    return true;
                else if (!output.is(result.getItem()))
                    return false;
                else if (output.getCount() + result.getCount() <= size && output.getCount() + result.getCount() <= output.getMaxStackSize())
                    return true;
                else
                    return output.getCount() + result.getCount() <= result.getMaxStackSize();
            }
        }
        return false;
    }

    protected boolean process(RegistryAccess access, @Nullable GemstoneManipulatorRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (recipe != null && this.canProcess(access, recipe, items, size))
        {
            ItemStack input1 = items.get(0);
            ItemStack input2 = items.get(1);
            ItemStack result = recipe.assemble(this, access);
            ItemStack output = items.get(2);
            if (output.isEmpty())
                items.set(2, result.copy());
            else if (output.is(result.getItem()))
                output.grow(result.getCount());

            input1.shrink(1);
            input2.shrink(1);
            return true;
        }
        return false;
    }
}
