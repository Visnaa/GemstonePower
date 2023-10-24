package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.data.recipe.MetalFormerRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class MetalFormerBE extends MachineBE<MetalFormerRecipe>
{
    public MetalFormerBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.METAL_FORMER.get(), ModRecipes.METAL_FORMER_RECIPE, pos, state, 1, 1, ModMenus.METAL_FORMER.get());
    }

    @Override
    protected boolean canProcess(RegistryAccess access, @Nullable MetalFormerRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        return super.canProcess(access, recipe, items, size) && MachineUtil.MachineModes.isValid(mode, recipe.getMachineMode());
    }
}
