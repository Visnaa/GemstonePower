package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.MetalFormerRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.util.MachinePresets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class MetalFormerBE extends MachineBE<MetalFormerRecipe>
{
    public MetalFormerBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.METAL_FORMER.get(), ModRecipes.METAL_FORMER_RECIPE, pos, state, 2, 1, ModMenus.METAL_FORMER.get());
    }

    @Override
    protected boolean canProcess(RegistryAccess access, @Nullable MetalFormerRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (!items.get(0).isEmpty() && recipe != null && MachinePresets.compare(items.get(1), recipe.getPreset()))
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
    protected boolean process(RegistryAccess access, @Nullable MetalFormerRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (recipe != null && this.canProcess(access, recipe, items, size))
        {
            ItemStack input = items.get(0);
            ItemStack result = recipe.assemble(this, access);
            ItemStack output = items.get(2);
            if (output.isEmpty())
                items.set(2, result.copy());
            else if (output.is(result.getItem()))
                output.grow(result.getCount());

            input.shrink(1);
            return true;
        }
        return false;
    }
}
