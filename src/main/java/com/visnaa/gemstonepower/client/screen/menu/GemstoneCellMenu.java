package com.visnaa.gemstonepower.client.screen.menu;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.registry.ModContainers;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GemstoneCellMenu extends AbstractContainerMenu
{
    private final Container container;
    private final ContainerData data;
    protected final Level level;

    public GemstoneCellMenu(int id, Inventory inventory)
    {
        this(ModContainers.GEMSTONE_CELL.get(), id, inventory, new SimpleContainer(0), new SimpleContainerData(2));
    }

    public GemstoneCellMenu(int id, Inventory inventory, Container container, ContainerData data)
    {
        this(ModContainers.GEMSTONE_CELL.get(), id, inventory, container, data);
    }

    protected GemstoneCellMenu(MenuType<?> menu, int id, Inventory inventory, Container container, ContainerData data)
    {
        super(menu, id);
        checkContainerSize(container, 0);
        checkContainerDataCount(data, 2);
        this.container = container;
        this.data = data;
        this.level = inventory.player.level();

        for(int x = 0; x < 3; ++x)
        {
            for(int y = 0; y < 9; ++y)
            {
                this.addSlot(new Slot(inventory, y + x * 9 + 9, 8 + y * 18, 84 + x * 18));
            }
        }

        for(int i = 0; i < 9; ++i)
        {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }

        this.addDataSlots(data);
    }

    public boolean stillValid(Player player)
    {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        final int vanillaSlotCount = 36;
        final int blockEntitySlotCount = 0;

        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyStack = sourceStack.copy();

        if (index < blockEntitySlotCount)
        {
            if (!moveItemStackTo(sourceStack, blockEntitySlotCount, blockEntitySlotCount + vanillaSlotCount, false))
            {
                return ItemStack.EMPTY;
            }
        }
        else if (index < blockEntitySlotCount + vanillaSlotCount)
        {
            if (!moveItemStackTo(sourceStack, 0, blockEntitySlotCount, false))
            {
                return ItemStack.EMPTY;
            }
        }
        else
        {
            GemstonePower.LOGGER.error("Invalid slot index " + index);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0)
        {
            sourceSlot.set(ItemStack.EMPTY);
        }
        else
        {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(player, sourceStack);
        return copyStack;
    }

    public int getData(int data)
    {
        return this.data.get(data);
    }

    public int getEnergy()
    {
        float i = this.data.get(0);
        float j = this.data.get(1);

        int val = (int) (-16 * (i / j) + 16);

        return j != 0 && i != 0 && val <= 16 ? val : 16;
    }
}
