package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.AlloySmelterRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class AlloySmelterBE extends MachineBE<AlloySmelterRecipe>
{
    public AlloySmelterBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ALLOY_SMELTER.get(), ModRecipes.ALLOY_SMELTER_RECIPE, pos, state, 2, 1, ModMenus.ALLOY_SMELTER.get());
    }

    @Override
    protected boolean canProcess(RegistryAccess access, @Nullable AlloySmelterRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (recipe != null && !items.get(0).isEmpty() && items.get(0).getCount() >= recipe.getAmount(items.get(0)) && !items.get(1).isEmpty() && items.get(1).getCount() >= recipe.getAmount(items.get(1)))
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
                else if (output.getCount() + result.getCount() <= size && output.getCount() + result.getCount() <= output.getMaxStackSize()) // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                else
                    return output.getCount() + result.getCount() <= result.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
            }
        }
        return false;
    }

    @Override
    protected boolean process(RegistryAccess access, @Nullable AlloySmelterRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (this.canProcess(access, recipe, items, size))
        {
            ItemStack input0 = items.get(0);
            ItemStack input1 = items.get(1);
            ItemStack result = recipe.assemble(this, access);
            ItemStack output = items.get(2);
            if (output.isEmpty())
                items.set(2, result.copy());
            else if (output.is(result.getItem()))
                output.grow(result.getCount());

            input0.shrink(recipe.getAmount(input0));
            input1.shrink(recipe.getAmount(input1));
            return true;
        }
        return false;
    }
}
