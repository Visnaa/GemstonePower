package com.visnaa.gemstonepower.pipe.item;

import com.visnaa.gemstonepower.block.entity.pipe.item.BaseItemPipeBE;
import com.visnaa.gemstonepower.block.pipe.item.BaseItemPipeBlock;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ItemPipeNetwork
{
    private List<BaseItemPipeBE> pipes;
    public List<BlockEntity> inputs;
    public List<BlockEntity> outputs;
    private long lastDistribution;

    public ItemPipeNetwork()
    {
        this.pipes = new ArrayList<>();
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public void registerToNetwork(BaseItemPipeBE cable)
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

        for (BaseItemPipeBE pipe : this.pipes)
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
        if (level.isClientSide() || !state.getValue(BaseItemPipeBlock.EXTRACTS))
            return;

        if (level.getGameTime() <= lastDistribution)
            return;
        lastDistribution = level.getGameTime();

        HashMap<BlockEntity, HashMap<ItemStack, Integer>> itemsMap = new HashMap<>();
        for (BlockEntity input : inputs)
        {
            HashMap<ItemStack, Integer> items = input.getCapability(ForgeCapabilities.ITEM_HANDLER).map(handler -> {
                HashMap<ItemStack, Integer> itemList = new HashMap<>();
                for (int slot = 0; slot < handler.getSlots(); slot++)
                {
                    ItemStack item = handler.extractItem(slot, handler.getStackInSlot(slot).getCount(), false);
                    if (!item.isEmpty())
                        itemList.put(item, slot);
                }
                return itemList;
            }).orElse(new HashMap<>());

            if (!items.isEmpty())
                itemsMap.put(input, items);
        }

        if (itemsMap.isEmpty())
            return;

        int transferredAmount = transfer;
        for (BlockEntity be : itemsMap.keySet())
        {
            int amount = itemsMap.get(be).size();
            boolean lessThanAmount = amount - transferredAmount > 0;

            for (int i = 0; i < transferredAmount || i < amount; i++)
            {
                for (BlockEntity output : outputs)
                {
                    boolean changed = output.getCapability(ForgeCapabilities.ITEM_HANDLER).map(handler -> {
                        boolean flag = false;
                        for (ItemStack item : itemsMap.get(be).keySet())
                        {
                            for (int slot = 0; slot < handler.getSlots(); slot++)
                            {
                                ItemStack left = handler.insertItem(slot, item, false);
                                if (!left.isEmpty())
                                {
                                    int slotId = itemsMap.get(be).get(item);
                                    itemsMap.get(be).remove(item);
                                    itemsMap.get(be).put(left, slotId);
                                }
                                else
                                {
                                    itemsMap.get(be).remove(item);
                                    itemsMap.get(be).put(ItemStack.EMPTY, slot);
                                    flag = true;
                                    break;
                                }
                            }

                            if (!itemsMap.get(be).containsKey(item))
                                break;
                        }
                        return flag;
                    }).orElse(false);

                    if (changed)
                    {
                        HashMap<ItemStack, Integer> map = itemsMap.get(be);
                        for (ItemStack stack : map.keySet())
                            if (stack.isEmpty())
                                map.remove(stack);

                        itemsMap.replace(be, map);
                        break;
                    }
                }
            }

            if (!lessThanAmount)
                break;
            else
                transferredAmount -= amount;
        }

        List<BlockEntity> remove = new ArrayList<>();
        for (BlockEntity be : itemsMap.keySet())
        {
            if (itemsMap.get(be).isEmpty())
                remove.add(be);
        }
        remove.forEach(itemsMap::remove);

        if (!itemsMap.isEmpty())
        {
            for (BlockEntity be : itemsMap.keySet())
            {
                be.getCapability(ForgeCapabilities.ITEM_HANDLER).map(handler -> {
                    List<ItemStack> toBeRemoved = new ArrayList<>();
                    for (ItemStack stack : itemsMap.get(be).keySet())
                    {
                        if (handler.getStackInSlot(itemsMap.get(be).get(stack)).isEmpty())
                        {
                            handler.insertItem(itemsMap.get(be).get(stack), stack, false);
                            toBeRemoved.add(stack);
                            break;
                        }
                    }
                    HashMap<ItemStack, Integer> map = itemsMap.get(be);
                    for (ItemStack stack : map.keySet())
                    {
                        if (toBeRemoved.contains(stack))
                            map.remove(stack);
                    }
                    itemsMap.replace(be, map);

                    if (!itemsMap.get(be).isEmpty())
                    {
                        NonNullList<ItemStack> stacks = NonNullList.createWithCapacity(itemsMap.get(be).size());
                        stacks.addAll(itemsMap.get(be).keySet());
                        Containers.dropContents(level, be.getBlockPos().above(), stacks);
                    }
                    return true;
                });
            }
        }
    }

    public void destroy(BaseItemPipeBE caller)
    {
        if (!Objects.requireNonNull(caller.getLevel()).isClientSide())
        {
            for (BaseItemPipeBE pipe : this.pipes)
                if (pipe != null)
                    pipe.network = null;
        }
    }
}
