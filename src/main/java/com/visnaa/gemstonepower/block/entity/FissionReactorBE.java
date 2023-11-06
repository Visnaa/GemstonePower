package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.block.FissionReactorBlock;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.capability.energy.EnergyStorage;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.data.recipe.FissionReactorRecipe;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.EnergySyncS2C;
import com.visnaa.gemstonepower.network.packet.FissionReactorSyncS2C;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;

public class FissionReactorBE extends MachineBE<FissionReactorRecipe>
{
    private Set<ReactorFrameBE> frames;
    private boolean activated;
    private boolean isWorking;
    private int energyGeneration;
    private int heatGeneration;
    private int heat;
    private int maxHeat;

    public FissionReactorBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.FISSION_REACTOR.get(), ModRecipes.FISSION_REACTOR_RECIPE, pos, state, 1, 1, ModMenus.FISSION_REACTOR.get());
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        if (level == null || level.isClientSide())
            return;

        boolean changed = false;
        frames = checkIsValid(level, pos, state);
        if (!frames.isEmpty() && activated)
        {
            int fuelFramesCount = 0;
            int cooling = 0;

            for (ReactorFrameBE frame : frames)
            {
                if (frame.getFrameType() != ReactorFrameBE.Type.FUEL)
                    continue;

                fuelFramesCount++;
                for (Direction direction : Direction.values())
                    if (level.getBlockEntity(frame.getBlockPos().relative(direction)) instanceof ReactorFrameBE cooler && cooler.getFrameType() != ReactorFrameBE.Type.FUEL)
                        cooling += cooler.getFrameType().getCooling();
            }

            int heatChange = -cooling;

            if (fuelFramesCount > 0)
            {
                RecipeHolder<FissionReactorRecipe> holder = quickCheck.getRecipeFor(this, level).orElse(null);
                if (holder != null)
                {
                    FissionReactorRecipe recipe = holder.value();
                    energyGeneration = recipe.getEnergyGeneration() * fuelFramesCount;
                    heatGeneration = recipe.getHeatGeneration() * fuelFramesCount;
                    heatChange += heatGeneration;

                    processingTotalTime = recipe.getProcessingTime();

                    if (canProcess(level.registryAccess(), recipe, items, getMaxStackSize()))
                    {
                        if (processingTotalTime > 0)
                        {
                            if (processingProgress >= processingTotalTime)
                            {
                                process(level.registryAccess(), recipe, items, getMaxStackSize());
                                energyStorage.addEnergy(energyGeneration * recipe.getProcessingTime());
                                processingProgress = 0;
                                isWorking = false;
                                energyGeneration = 0;
                                heatGeneration = 0;
                            } else
                            {
                                processingProgress++;
                                isWorking = true;
                            }
                        }
                    }
                }
            }
            heat += Math.max(heatChange, 0);
            maxHeat = (int) (Math.pow(frames.size(), (double) 2 / 3) * 2000);

            if (heat > maxHeat)
                explode(level);

            changed = true;
        }
        else if (!activated)
        {
            isWorking = false;
            processingProgress = 0;
            energyGeneration = 0;
            heatGeneration = 0;
            changed = true;
        }

        if (heat > 0)
        {
            heat -= Math.min(5, heat);
            changed = true;
        }

        if (changed)
        {
            setChanged(level, pos, state);
            ModPackets.sendToClient(new FissionReactorSyncS2C(activated, getProcessingProgress(), getProcessingTotalTime(), energyGeneration, heatGeneration, heat, maxHeat, pos));
        }
    }

    private Set<ReactorFrameBE> checkIsValid(Level level, BlockPos pos, BlockState state)
    {
        if (level == null || level.isClientSide() || !state.hasProperty(FissionReactorBlock.FACING))
            return new HashSet<>();

        Set<ReactorFrameBE> frames = new HashSet<>();
        BlockPos behind = pos.relative(state.getValue(FissionReactorBlock.FACING).getOpposite());
        if (level.getBlockEntity(behind) instanceof ReactorFrameBE frame)
        {
            for (ReactorFrameBE reactorFrame : frame.registerFrame(new HashSet<>(), this, level))
                if (!reactorFrame.isValid(level))
                    return new HashSet<>();
                else
                    frames.add(reactorFrame);
        }
        else
            return new HashSet<>();
        return frames;
    }

    public void broken(Level level)
    {
        if ((!frames.equals(checkIsValid(level, getBlockPos(), level.getBlockState(getBlockPos()))) || isWorking) && (heatGeneration > 0 || heat > 0))
            explode(level);
    }

    private void explode(Level level)
    {
        level.explode(null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 300.0F, Level.ExplosionInteraction.BLOCK);
    }

    public void setFromServerPacket(boolean activated, int energyGeneration, int heatGeneration, int heat, int maxHeat)
    {
        this.activated = activated;
        this.energyGeneration = energyGeneration;
        this.heatGeneration = heatGeneration;
        this.heat = heat;
        this.maxHeat = maxHeat;
    }

    public void setFromClientPacket(boolean activated)
    {
        this.activated = activated;
        setChanged();
        ModPackets.sendToClient(new FissionReactorSyncS2C(activated, getProcessingProgress(), getProcessingTotalTime(), energyGeneration, heatGeneration, heat, maxHeat, getBlockPos()));
    }

    public int getHeat()
    {
        return heat;
    }

    public int getMaxHeat()
    {
        return maxHeat;
    }

    public boolean isActivated()
    {
        return activated;
    }

    @Override
    public EnergyStorage createEnergyStorage()
    {
        return new EnergyStorage(ServerConfig.DEFAULT_GENERATOR_CAPACITY.get() * 10000, 0, Integer.MAX_VALUE)
        {
            @Override
            public void onEnergyChanged()
            {
                if (level != null && !level.isClientSide())
                    ModPackets.sendToClient(new EnergySyncS2C(energy, capacity, getBlockPos()));
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

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.putBoolean("Activated", activated);
        tag.putBoolean("IsWorking", isWorking);
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        activated = tag.getBoolean("Activated");
        isWorking = tag.getBoolean("IsWorking");
    }
}
