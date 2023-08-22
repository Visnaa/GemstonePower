package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.ExtractorRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ExtractorBE extends MachineBE<ExtractorRecipe>
{
    public ExtractorBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.EXTRACTOR.get(), ModRecipes.EXTRACTOR_RECIPE, pos, state, 1, 1, ModMenus.EXTRACTOR.get());
    }
}
