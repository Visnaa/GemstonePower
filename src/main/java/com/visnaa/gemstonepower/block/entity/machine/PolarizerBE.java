package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.PolarizerRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PolarizerBE extends MachineBE<PolarizerRecipe>
{
    public PolarizerBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.POLARIZER.get(), ModRecipes.POLARIZER_RECIPE, pos, state, 1, 1, ModMenus.POLARIZER.get());
    }
}
