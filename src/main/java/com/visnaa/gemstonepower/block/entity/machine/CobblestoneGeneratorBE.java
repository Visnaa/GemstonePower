package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class CobblestoneGeneratorBE extends MachineBE<Recipe<Container>>
{
    private int cooldown = 0;

    public CobblestoneGeneratorBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.COBBLESTONE_GENERATOR.get(), null, pos, state, 1, 1);
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return null;
    }

    @Override
    public void process(Level level, BlockPos pos, BlockState state)
    {
        if (energyStorage.getEnergyStored() > MachineUtil.getUsage(state, ServerConfig.DEFAULT_MACHINE_USAGE.get()) && cooldown <= 0)
        {
            BlockEntity be = level.getBlockEntity(pos.below());
            if (be != null && be.getCapability(ForgeCapabilities.ITEM_HANDLER).isPresent())
            {
                be.getCapability(ForgeCapabilities.ITEM_HANDLER).map(handler -> {
                    for (int i = 0; i < handler.getSlots(); i++)
                    {
                        if ((handler.getStackInSlot(i).is(Items.COBBLESTONE) || handler.getStackInSlot(i).isEmpty()) && handler.getStackInSlot(i).getCount() <= handler.getStackInSlot(i).getMaxStackSize() - 1)
                        {
                            ItemStack stack = handler.insertItem(i, new ItemStack(Blocks.COBBLESTONE), false);
                            if (stack.isEmpty())
                            {
                                energyStorage.consumeEnergy(MachineUtil.getUsage(state, ServerConfig.DEFAULT_MACHINE_USAGE.get()));
                                cooldown = ServerConfig.DEFAULT_MACHINE_TIME.get();
                            }
                            return true;
                        }
                    }
                    return false;
                });
            }
        }
        cooldown--;
    }
}
