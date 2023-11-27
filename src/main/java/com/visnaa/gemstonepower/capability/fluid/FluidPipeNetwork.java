package com.visnaa.gemstonepower.capability.fluid;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.fluid.FluidPipeBE;
import com.visnaa.gemstonepower.capability.PipeNetwork;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FluidPipeNetwork implements PipeNetwork<FluidPipeBE>
{
    private Set<FluidPipeBE> pipes;
    public HashMap<BlockEntity, Direction> inputs;
    public HashMap<BlockEntity, Direction> outputs;
    private long lastDistribution;

    public FluidPipeNetwork()
    {
        this.pipes = new HashSet<>();
        this.inputs = new HashMap<>();
        this.outputs = new HashMap<>();
    }

    @Override
    public void registerToNetwork(FluidPipeBE pipe)
    {
        if (!this.pipes.contains(pipe))
            this.pipes.add(pipe);
    }

    public void registerInput(BlockEntity input, Direction direction)
    {
        if (input != null)
        {
            inputs.put(input, direction);
            outputs.remove(input);
        }
    }

    public void registerOutput(BlockEntity output, Direction direction)
    {
        if (output != null)
        {
            outputs.put(output, direction);
            inputs.remove(output);
        }
    }

    public void refresh()
    {
        for (BlockEntity be : new HashSet<>(inputs.keySet()))
            if (be.isRemoved() || !be.getCapability(Capabilities.FLUID_HANDLER, inputs.get(be)).isPresent())
                inputs.remove(be);

        for (BlockEntity be : new HashSet<>(outputs.keySet()))
            if (be.isRemoved() || !be.getCapability(Capabilities.FLUID_HANDLER, outputs.get(be)).isPresent())
                outputs.remove(be);

        this.pipes.removeIf(pipe -> {
            if (pipe.isRemoved())
            {
                if (pipe.network != null)
                    pipe.network.destroy(pipe);
                return true;
            }
            return false;
        });
    }

    public void merge(FluidPipeNetwork newHost)
    {
        if (newHost == this) return;

        for (FluidPipeBE pipe : this.pipes)
        {
            if (!newHost.pipes.contains(pipe))
            {
                newHost.registerToNetwork(pipe);
                pipe.network = newHost;
            }
        }

        for (BlockEntity input : this.inputs.keySet())
            if (!newHost.inputs.containsKey(input))
                newHost.registerInput(input, this.inputs.get(input));

        for (BlockEntity output : this.outputs.keySet())
            if (!newHost.outputs.containsKey(output))
                newHost.registerInput(output, this.outputs.get(output));
    }

    @Override
    public void distribute(Level level, BlockState state, BlockPos pos, int transfer)
    {
        if (level.isClientSide() || level.getGameTime() <= lastDistribution)
            return;
        lastDistribution = level.getGameTime() + ServerConfig.FLUID_PIPE_FREQUENCY.get() - 1;

        for (int i = 0; i < transfer; i++)
        {
            for (BlockEntity input : inputs.keySet())
            {
                boolean success = false;
                for (BlockEntity output : outputs.keySet())
                {
                    if (input.getLevel() == null || output.getLevel() == null || input.getLevel().isClientSide() || input.getLevel().isClientSide())
                        return;

                    IFluidHandler iHandler = FluidUtil.getFluidHandler(input.getLevel(), input.getBlockPos(), inputs.get(input)).orElse(new FluidTank(0));
                    IFluidHandler oHandler = FluidUtil.getFluidHandler(output.getLevel(), output.getBlockPos(), outputs.get(output)).orElse(new FluidTank(0));

                    FluidStack stack = FluidUtil.tryFluidTransfer(oHandler, iHandler, 1, true);
                    if (stack.isEmpty())
                        continue;
                    success = true;
                    break;

                }
                if (success)
                    break;
            }
        }
    }

    @Override
    public void destroy(PipeBE caller)
    {
        if (caller.getLevel() != null && !caller.getLevel().isClientSide())
            for (FluidPipeBE pipe : this.pipes)
                if (pipe != null)
                    pipe.network = new FluidPipeNetwork();
    }
}
