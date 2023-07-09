package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.CrystalChargerRecipe;
import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.menu.machine.CrystalChargerMenu;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CrystalChargerBE extends MachineBE<CrystalChargerRecipe>
{
    public CrystalChargerBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CRYSTAL_CHARGER.get(), ModRecipes.CRYSTAL_CHARGER_RECIPE, pos, state, 1, 1);
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new CrystalChargerMenu(new MenuData(id, inv, this, 2, MenuData.createSlots(2)), this.getBlockPos());
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, MachineBE<?> machine)
    {
        if (level.isClientSide()) return;
        machine.process(level, pos, state);
    }
}
