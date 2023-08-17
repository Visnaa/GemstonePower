package com.visnaa.gemstonepower.pipe.fluid;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.fluid.FluidPipeBE;
import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.pipe.PipeNetwork;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.*;

public class FluidPipeNetwork implements PipeNetwork
{
    private List<FluidPipeBE> pipes;
    public Set<BlockEntity> inputs;
    public Set<BlockEntity> outputs;
    private long lastDistribution;

    public FluidPipeNetwork()
    {
        this.pipes = new ArrayList<>();
        this.inputs = new HashSet<>();
        this.outputs = new HashSet<>();
    }

    public void registerToNetwork(FluidPipeBE pipe)
    {
        if (!this.pipes.contains(pipe))
            this.pipes.add(pipe);
    }

    public void registerInput(BlockEntity input)
    {
        if (input != null) this.inputs.add(input);
    }

    public void registerOutput(BlockEntity output)
    {
        if (output != null) this.outputs.add(output);
    }

    public void refresh()
    {
        this.inputs.removeIf(be -> be == null ||
                !be.getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent());

        this.outputs.removeIf(be -> be == null ||
                !be.getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent());

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

        for (BlockEntity input : this.inputs)
            if (!newHost.inputs.contains(input))
                newHost.registerInput(input);
        for (BlockEntity output : this.outputs)
            if (!newHost.outputs.contains(output))
                newHost.registerOutput(output);
    }

    public void distribute(Level level, BlockState state, int transfer)
    {
        if (level.isClientSide() || !state.getValue(PipeBlock.EXTRACTS))
            return;

        if (level.getGameTime() <= lastDistribution)
            return;
        lastDistribution = level.getGameTime() + ServerConfig.FLUID_PIPE_FREQUENCY.get() - 1;

        for (int i = 0; i < transfer; i++)
        {
            for (BlockEntity input : inputs)
            {
                boolean success = false;
                for (BlockEntity output : outputs)
                {
                    if (input.getLevel() == null || output.getLevel() == null || input.getLevel().isClientSide() || input.getLevel().isClientSide())
                        return;

                    IFluidHandler iHandler = FluidUtil.getFluidHandler(input.getLevel(), input.getBlockPos(), null).orElse(new FluidTank(0));
                    IFluidHandler oHandler = FluidUtil.getFluidHandler(output.getLevel(), output.getBlockPos(), null).orElse(new FluidTank(0));

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

    public void destroy(PipeBE caller)
    {
        if (!Objects.requireNonNull(caller.getLevel()).isClientSide())
        {
            for (FluidPipeBE pipe : this.pipes)
                if (pipe != null)
                    pipe.network = null;
        }
    }
}
