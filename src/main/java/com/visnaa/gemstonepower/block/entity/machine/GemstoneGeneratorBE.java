package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.block.machine.GemstoneGeneratorBlock;
import com.visnaa.gemstonepower.capability.energy.EnergyStorage;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.data.recipe.GemstoneGeneratorRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.network.packet.EnergySyncS2C;
import com.visnaa.gemstonepower.network.packet.RecipeProgressSyncS2C;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;

public class GemstoneGeneratorBE extends MachineBE<GemstoneGeneratorRecipe>
{
    public GemstoneGeneratorBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.GEMSTONE_GENERATOR.get(), ModRecipes.GEMSTONE_GENERATOR_RECIPE, pos, state, 1, 0, ModMenus.GEMSTONE_GENERATOR.get());
    }

    @Override
    public void process(Level level, BlockPos pos, BlockState state)
    {
        boolean changed = false;

        if (!this.items.get(0).isEmpty())
        {
            RecipeHolder<GemstoneGeneratorRecipe> recipe;
            recipe = this.quickCheck.getRecipeFor(this, level).orElse(null);

            if (recipe != null)
            {
                this.processingProgress++;
                if (this.processingProgress == this.processingTotalTime)
                {
                    this.processingProgress = 0;
                    this.processingTotalTime = getTotalTime(level, this, recipe.value());
                    this.energyStorage.addEnergy(MachineUtil.getGeneration(state, recipe.value().getEnergy()));
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
            PacketDistributor.ALL.noArg().send(new RecipeProgressSyncS2C(getProcessingProgress(), getProcessingTotalTime(), pos));
        }
    }

    @Override
    public EnergyStorage createEnergyStorage()
    {
        return new EnergyStorage(MachineUtil.getCapacity(this.getBlockState(), ServerConfig.DEFAULT_GENERATOR_CAPACITY.get()), 0, Integer.MAX_VALUE) {
            @Override
            public void onEnergyChanged() {
                if (level != null && !level.isClientSide())
                    PacketDistributor.ALL.noArg().send(new EnergySyncS2C(getEnergy(), getCapacity(), getBlockPos()));
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
