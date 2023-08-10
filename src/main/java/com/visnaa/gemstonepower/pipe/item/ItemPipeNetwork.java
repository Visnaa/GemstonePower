package com.visnaa.gemstonepower.pipe.item;

import com.visnaa.gemstonepower.block.entity.pipe.item.IronItemPipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.item.ItemPipeBE;
import com.visnaa.gemstonepower.block.pipe.item.ItemPipeBlock;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemPipeNetwork
{
    private List<ItemPipeBE> pipes;
    public List<BlockEntity> inputs;
    public List<BlockEntity> outputs;
    private long lastDistribution;

    public ItemPipeNetwork()
    {
        this.pipes = new ArrayList<>();
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public void registerToNetwork(ItemPipeBE cable)
    {
        if (!this.pipes.contains(cable))
            this.pipes.add(cable);
    }

    public void registerInput(BlockEntity input)
    {
        if (input != null && !this.inputs.contains(input)) this.inputs.add(input);
    }

    public void registerOutput(BlockEntity battery)
    {
        if (battery != null && !this.outputs.contains(battery)) this.outputs.add(battery);
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
                                                        if (level.getBlockEntity(input.getBlockPos().relative(dir)) instanceof IronItemPipeBE)
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

    public void destroy(ItemPipeBE caller)
    {
        if (!Objects.requireNonNull(caller.getLevel()).isClientSide())
        {
            for (ItemPipeBE pipe : this.pipes)
                if (pipe != null)
                    pipe.network = null;
        }
    }
}
