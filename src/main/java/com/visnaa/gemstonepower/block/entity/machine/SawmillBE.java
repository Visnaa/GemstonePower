package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.SawmillRecipe;
import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.menu.machine.SawmillMenu;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class SawmillBE extends MachineBE<SawmillRecipe>
{
    public SawmillBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.SAWMILL.get(), ModRecipes.SAWMILL_RECIPE, pos, state, 1, 1);
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new SawmillMenu(new MenuData(id, inv, this, 2, MenuData.createSlots(2)), this.getBlockPos());
    }
}
