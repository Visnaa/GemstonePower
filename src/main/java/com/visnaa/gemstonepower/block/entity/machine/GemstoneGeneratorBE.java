package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.block.machine.GemstoneGeneratorBlock;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.data.recipe.GemstoneGeneratorRecipe;
import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.menu.machine.GemstoneGeneratorMenu;
import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.EnergySyncS2C;
import com.visnaa.gemstonepower.network.packet.RecipeProgressSyncS2C;
import com.visnaa.gemstonepower.pipe.energy.ForgeEnergyStorage;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModRecipes;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GemstoneGeneratorBE extends MachineBE<GemstoneGeneratorRecipe>
{
    private int energyPerTick;

    public GemstoneGeneratorBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.GEMSTONE_GENERATOR.get(), ModRecipes.GEMSTONE_GENERATOR_RECIPE, pos, state, 1, 0);
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new GemstoneGeneratorMenu(new MenuData(id, inv, this, 1, MenuData.createSlots(1)), this.getBlockPos());
    }

    @Override
    public void process(Level level, BlockPos pos, BlockState state)
    {
        boolean changed = false;

        if (!this.items.get(0).isEmpty())
        {
            GemstoneGeneratorRecipe recipe;
            recipe = this.quickCheck.getRecipeFor(this, level).orElse(null);

            if (recipe != null)
            {
                this.processingProgress++;
                if (this.processingProgress == this.processingTotalTime)
                {
                    this.processingProgress = 0;
                    this.processingTotalTime = getTotalTime(level, this, recipe);
                    this.energyStorage.addEnergy(MachineUtil.getGeneration(state, recipe.getEnergy()));
                    this.items.get(0).shrink(1);
                }
                changed = true;
            } else
            {
                this.processingProgress = 0;
            }
        } else if (this.processingProgress > 0)
        {
            this.processingProgress = 0;
        }

        if (state.getValue(GemstoneGeneratorBlock.POWERED) != processingProgress > 0)
        {
            changed = true;
            state = state.setValue(GemstoneGeneratorBlock.POWERED, processingProgress > 0);
            level.setBlock(pos, state, 3);
        }

        if (changed)
        {
            setChanged(level, pos, state);
            ModPackets.sendToClient(new RecipeProgressSyncS2C(getProcessingProgress(), getProcessingTotalTime(), pos));
        }
    }

    @Override
    protected ForgeEnergyStorage createEnergyStorage()
    {
        return new ForgeEnergyStorage(MachineUtil.getCapacity(this.getBlockState(), ServerConfig.DEFAULT_GENERATOR_CAPACITY.get()), 0, Integer.MAX_VALUE) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                if (!level.isClientSide())
                    ModPackets.sendToClient(new EnergySyncS2C(getEnergy(), getCapacity(), getBlockPos()));
            }

            @Override
            public boolean canReceive()
            {
                return false;
            }

            @Override
            public boolean canExtract()
            {
                return true;
            }
        };
    }
}
