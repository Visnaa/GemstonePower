package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.OreWasherRecipe;
import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.menu.machine.OreWasherMenu;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class OreWasherBE extends MachineBE<OreWasherRecipe>
{
    public OreWasherBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ORE_WASHER.get(), ModRecipes.ORE_WASHER_RECIPE, pos, state, 1, 4);
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new OreWasherMenu(new MenuData(id, inv, this, 5, MenuData.createSlots(5)), this.getBlockPos());
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, MachineBE<?> machine)
    {
        if (level.isClientSide()) return;
        machine.process(level, pos, state);
    }

    @Override
    protected boolean canProcess(RegistryAccess access, @Nullable OreWasherRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (!items.get(0).isEmpty() && recipe != null)
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
