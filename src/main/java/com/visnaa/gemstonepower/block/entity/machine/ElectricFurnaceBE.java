package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.block.machine.ElectricFurnaceBlock;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.RecipeProgressSyncS2C;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ElectricFurnaceBE extends MachineBE<SmeltingRecipe>
{
    public ElectricFurnaceBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ELECTRIC_FURNACE.get(), RecipeType.SMELTING, pos, state, 1, 1, ModMenus.ELECTRIC_FURNACE.get());
    }

    @Override
    public void process(Level level, BlockPos pos, BlockState state)
    {
        super.process(level, pos, state);
        if (state.getValue(ElectricFurnaceBlock.LIT) != this.processingProgress > 0)
        {
            state = state.setValue(ElectricFurnaceBlock.LIT, this.processingProgress > 0);
            level.setBlock(pos, state, 3);
            setChanged(level, pos, state);
            ModPackets.sendToClient(new RecipeProgressSyncS2C(getProcessingProgress(), getProcessingTotalTime(), pos));
        }
    }
}
