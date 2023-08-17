package com.visnaa.gemstonepower.pipe.item;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.item.ItemPipeBE;
import com.visnaa.gemstonepower.block.pipe.item.ItemPipeBlock;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.pipe.PipeNetwork;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import java.util.*;

public class ItemPipeNetwork implements PipeNetwork
{
    private List<ItemPipeBE> pipes;
    public Set<BlockEntity> inputs;
    public Set<BlockEntity> outputs;
    private long lastDistribution;

    public ItemPipeNetwork()
    {
        this.pipes = new ArrayList<>();
        this.inputs = new HashSet<>();
        this.outputs = new HashSet<>();
    }

    public void registerToNetwork(ItemPipeBE pipe)
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
                !be.getCapability(ForgeCapabilities.ITEM_HANDLER).isPresent());

        this.outputs.removeIf(be -> be == null ||
                !be.getCapability(ForgeCapabilities.ITEM_HANDLER).isPresent());

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

    public void merge(ItemPipeNetwork newHost)
    {
        if (newHost == this) return;

        for (ItemPipeBE pipe : this.pipes)
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
        if (level.isClientSide() || !state.getValue(ItemPipeBlock.EXTRACTS))
            return;

        if (level.getGameTime() <= lastDistribution)
            return;
        lastDistribution = level.getGameTime() + ServerConfig.ITEM_PIPE_FREQUENCY.get() - 1;

        for (int i = 0; i < transfer; i++)
        {
            for (BlockEntity output : outputs)
            {
                for (BlockEntity input : inputs)
                {
                    input.getCapability(ForgeCapabilities.ITEM_HANDLER).map(iHandler ->
                    {
                        for (int iSlot = iHandler.getSlots() - 1; iSlot >= 0; iSlot--)
                        {
                            ItemStack stack = iHandler.extractItem(iSlot, 1, true);
                            if (!stack.isEmpty())
                            {
                                int finalISlot = iSlot;
                                boolean transferred = output.getCapability(ForgeCapabilities.ITEM_HANDLER).map(oHandler -> {
                                    for (int oSlot = 0; oSlot < oHandler.getSlots(); oSlot++)
                                    {
                                        ItemStack left = oHandler.insertItem(oSlot, stack, true);
                                        if (left.isEmpty())
                                        {
                                            if (input instanceof Container container)
                                            {
                                                if (input instanceof WorldlyContainer be)
                                                {
                                                    Direction direction = Direction.NORTH;
                                                    for (Direction dir : Direction.values())
                                                        if (level.getBlockEntity(input.getBlockPos().relative(dir)) instanceof ItemPipeBE)
                                                            direction = dir;

                                                    if (be.canTakeItemThroughFace(finalISlot, stack, direction))
                                                    {
                                                        iHandler.extractItem(finalISlot, 1, false);
                                                        oHandler.insertItem(oSlot, stack, false);
                                                        return true;
                                                    }
                                                }
                                                else
                                                    if (container.canTakeItem(null, finalISlot, stack))
                                                    {
                                                        iHandler.extractItem(finalISlot, 1, false);
                                                        oHandler.insertItem(oSlot, stack, false);
                                                        return true;
                                                    }
                                            }
                                        }
                                    }
                                    return false;
                                }).orElse(false);

                                if (transferred)
                                    break;
                            }
                        }
                        return true;
                    });
                }
            }
        }
    }

    public void destroy(PipeBE caller)
    {
        if (!Objects.requireNonNull(caller.getLevel()).isClientSide())
        {
            for (ItemPipeBE pipe : this.pipes)
                if (pipe != null)
                    pipe.network = null;
        }
    }
}
