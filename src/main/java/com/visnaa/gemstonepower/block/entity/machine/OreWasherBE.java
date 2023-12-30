package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.OreWasherRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;

public class OreWasherBE extends FluidMachineBE<OreWasherRecipe>
{
    public OreWasherBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ORE_WASHER.get(), ModRecipes.ORE_WASHER_RECIPE, pos, state, 1, 4, ModMenus.ORE_WASHER.get(), MachineUtil.createFluidTank(Fluids.EMPTY, 10000));
    }

    @Override
    protected boolean canProcess(RegistryAccess access, @Nullable OreWasherRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (!items.get(0).isEmpty() && recipe != null && !getTank(0).isEmpty() && getTank(0).getFluid().getAmount() >= recipe.getFluid().getAmount())
        {
            NonNullList<ItemStack> results = recipe.getResultItems();
            for (int i = 0; i < results.size(); i++)
            {
                ItemStack result = results.get(i);
                if (result.isEmpty())
                    return false;
                else
                {
                    ItemStack output = items.get(i + 1);
                    if (!output.isEmpty())
                    {
                        if (!output.is(result.getItem()))
                            return false;
                        if (!(output.getCount() + result.getCount() <= size && output.getCount() + result.getCount() <= output.getMaxStackSize())) // Forge fix: make furnace respect stack sizes in furnace recipes
                            if (!(output.getCount() + result.getCount() <= result.getMaxStackSize()))
                                return false; // Forge fix: make furnace respect stack sizes in furnace recipes
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean process(RegistryAccess access, @Nullable OreWasherRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (recipe != null && this.canProcess(access, recipe, items, size))
        {
            if (tanks.getTank(0).isEmpty())
                return false;

            FluidStack fluid = tanks.getTank(0).drain(recipe.getFluid(), IFluidHandler.FluidAction.SIMULATE);
            if (fluid.getAmount() != recipe.getFluid().getAmount())
                return false;
            tanks.getTank(0).drain(recipe.getFluid(), IFluidHandler.FluidAction.EXECUTE);

            ItemStack input = items.get(0);
            NonNullList<ItemStack> results = recipe.getResultItems();

            for (int i = 0; i < results.size(); i++)
            {
                if (items.get(i + 1).isEmpty())
                {
                    items.set(i + 1, results.get(i).copy());
                } else if (items.get(i + 1).is(results.get(i).getItem()))
                {
                    items.get(i + 1).grow(results.get(i).getCount());
                }
            }
            input.shrink(1);
            return true;
        }
        return false;
    }
}
