package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.SawmillRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SawmillBE extends MachineBE<SawmillRecipe>
{
    public SawmillBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.SAWMILL.get(), ModRecipes.SAWMILL_RECIPE, pos, state, 1, 1, ModMenus.SAWMILL.get());
    }
}
