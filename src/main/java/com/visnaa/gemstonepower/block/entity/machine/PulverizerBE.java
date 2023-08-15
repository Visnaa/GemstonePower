package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.PulverizerRecipe;
import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.menu.machine.PulverizerMenu;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class PulverizerBE extends MachineBE<PulverizerRecipe>
{
    public PulverizerBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.PULVERIZER.get(), ModRecipes.PULVERIZER_RECIPE, pos, state, 1, 1);
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new PulverizerMenu(new MenuData(id, inv, this, 2, MenuData.createSlots(2)), this.getBlockPos());
    }
}
