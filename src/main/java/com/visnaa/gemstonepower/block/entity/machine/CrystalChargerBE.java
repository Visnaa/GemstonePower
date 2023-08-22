package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.CrystalChargerRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CrystalChargerBE extends MachineBE<CrystalChargerRecipe>
{
    public CrystalChargerBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CRYSTAL_CHARGER.get(), ModRecipes.CRYSTAL_CHARGER_RECIPE, pos, state, 1, 1, ModMenus.CRYSTAL_CHARGER.get());
    }
}
