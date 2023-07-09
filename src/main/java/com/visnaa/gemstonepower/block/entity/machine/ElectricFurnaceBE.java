package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.block.machine.ElectricFurnaceBlock;
import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.menu.machine.ElectricFurnaceMenu;
import com.visnaa.gemstonepower.networking.ModPackets;
import com.visnaa.gemstonepower.networking.packet.RecipeProgressSyncS2C;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ElectricFurnaceBE extends MachineBE<SmeltingRecipe>
{
    public ElectricFurnaceBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ELECTRIC_FURNACE.get(), RecipeType.SMELTING, pos, state, 1, 1);
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new ElectricFurnaceMenu(new MenuData(id, inv, this, 2, MenuData.createSlots(2)), this.getBlockPos());
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, MachineBE<?> machine)
    {
        if (level.isClientSide()) return;
        machine.process(level, pos, state);
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
