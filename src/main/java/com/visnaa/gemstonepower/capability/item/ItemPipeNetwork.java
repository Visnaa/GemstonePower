package com.visnaa.gemstonepower.capability.item;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.item.ItemPipeBE;
import com.visnaa.gemstonepower.capability.PipeNetwork;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.EmptyHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ItemPipeNetwork implements PipeNetwork<ItemPipeBE>
{
    private Set<ItemPipeBE> pipes;
    public HashMap<BlockEntity, Direction> inputs;
    public HashMap<BlockEntity, Direction> outputs;
    private long lastDistribution;

    public ItemPipeNetwork()
    {
        this.pipes = new HashSet<>();
        this.inputs = new HashMap<>();
        this.outputs = new HashMap<>();
    }

    @Override
    public void registerToNetwork(ItemPipeBE pipe)
    {
        this.pipes.add(pipe);
    }

    public void registerInput(BlockEntity input, Direction dir)
    {
        if (input != null)
        {
            inputs.put(input, dir);
            outputs.remove(input);
        }
    }

    public void registerOutput(BlockEntity output, Direction dir)
    {
        if (output != null)
        {
            outputs.put(output, dir);
            inputs.remove(output);
        }
    }

    public void refresh()
    {
        for (BlockEntity be : new HashSet<>(inputs.keySet()))
            if (be.isRemoved() || !be.getCapability(Capabilities.ITEM_HANDLER, inputs.get(be)).isPresent())
                inputs.remove(be);

        for (BlockEntity be : new HashSet<>(outputs.keySet()))
            if (be.isRemoved() || !be.getCapability(Capabilities.ITEM_HANDLER, outputs.get(be)).isPresent())
                outputs.remove(be);

        this.pipes.removeIf(pipe -> {
            if (pipe == null || pipe.isRemoved())
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
        if (level == null || level.isClientSide() || lastDistribution >= level.getGameTime())
            return;
        lastDistribution = level.getGameTime() + ServerConfig.ITEM_PIPE_FREQUENCY.get() - 1;

        for (int i = 0; i < transfer; i++)
        {
            for (BlockEntity input : inputs.keySet())
            {
                boolean success = false;

                for (BlockEntity output : outputs.keySet())
                {
                    IItemHandler iHandler = input.getCapability(Capabilities.ITEM_HANDLER, inputs.get(input).getOpposite()).orElse(EmptyHandler.INSTANCE);
                    if (iHandler == EmptyHandler.INSTANCE || iHandler.getSlots() <= 0)
                        break;

                    IItemHandler oHandler = output.getCapability(Capabilities.ITEM_HANDLER, outputs.get(output).getOpposite()).orElse(EmptyHandler.INSTANCE);
                    if (oHandler == EmptyHandler.INSTANCE || oHandler.getSlots() <= 0)
                        continue;

                    for (int iSlot = iHandler.getSlots() - 1; iSlot >= 0; iSlot--)
                    {
                        ItemStack stack = iHandler.extractItem(iSlot, 1, true);
                        if (stack.isEmpty())
                            continue;

                        for (int oSlot = 0; oSlot < oHandler.getSlots(); oSlot++)
                        {
                            ItemStack left = oHandler.insertItem(oSlot, stack, true);
                            if (!left.isEmpty())
                                continue;

                            if (input instanceof Container iContainer && output instanceof Container oContainer)
                            {
                                if (input instanceof WorldlyContainer be)
                                {
                                    if (be.canTakeItemThroughFace(iSlot, stack, inputs.get(input).getOpposite()))
                                    {
                                        iHandler.extractItem(iSlot, 1, false);
                                        oHandler.insertItem(oSlot, stack, false);
                                        success = true;
                                    }
                                }
                                else if (iContainer.canTakeItem(oContainer, iSlot, stack))
                                {
                                    iHandler.extractItem(iSlot, 1, false);
                                    oHandler.insertItem(oSlot, stack, false);
                                    success = true;
                                }
                            }
                            if (success)
                                break;
                        }
                        if (success)
                            break;
                    }
                    if (success)
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
            for (ItemPipeBE pipe : this.pipes)
                if (pipe != null)
                    pipe.network = new ItemPipeNetwork();
    }
}
