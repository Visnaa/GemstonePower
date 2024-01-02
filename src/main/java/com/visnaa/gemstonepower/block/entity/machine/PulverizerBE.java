package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.PulverizerRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PulverizerBE extends MachineBE<PulverizerRecipe>
{
    public PulverizerBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.PULVERIZER.get(), ModRecipes.PULVERIZER_RECIPE, pos, state, 1, 1, ModMenus.PULVERIZER.get());
    }
}
