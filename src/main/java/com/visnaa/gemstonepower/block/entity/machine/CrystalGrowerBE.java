package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.CrystalGrowerRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CrystalGrowerBE extends MachineBE<CrystalGrowerRecipe>
{
    public CrystalGrowerBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CRYSTAL_GROWER.get(), ModRecipes.CRYSTAL_GROWER_RECIPE, pos, state, 1, 1, ModMenus.CRYSTAL_GROWER.get());
    }
}
