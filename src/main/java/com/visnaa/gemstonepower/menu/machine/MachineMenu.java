package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.FissionReactorBE;
import com.visnaa.gemstonepower.block.entity.machine.FluidMachineBE;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.menu.MenuData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class MachineMenu extends AbstractContainerMenu
{
    private final MenuData data;
    protected final Level level;
    protected final BlockEntity blockEntity;
    private final RecipeType<? extends Recipe<Container>> recipeType;

    public MachineMenu(MenuType<?> type, RecipeType<? extends Recipe<Container>> recipe, MenuData data, BlockPos pos)
    {
        super(type, data.windowId());
        this.data = data;
        this.level = data.inventory().player.level();
        this.blockEntity = pos == null ? null : this.level.getBlockEntity(pos);
        this.recipeType = recipe;

        checkContainerSize(data.container(), data.containerSize());
        createSlots();
    }

    private void createSlots()
    {
        // Machine slots
        for (int id = 0; id < data.slots().size(); id++)
        {
            this.addSlot(new Slot(data.container(), id, data.slots().get(id).x(), data.slots().get(id).y()));
        }

        // Player's inventory slots
        for(int x = 0; x < 3; ++x)
        {
            for(int y = 0; y < 9; ++y)
            {
                this.addSlot(new Slot(data.inventory(), y + x * 9 + 9, 8 + y * 18, 84 + x * 18));
            }
        }

        // Hotbar slots
        for(int i = 0; i < 9; ++i)
        {
            this.addSlot(new Slot(data.inventory(), i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player)
    {
        return this.data.container().stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot)
    {
        final int vanillaSlotCount = 36;
        final int blockEntitySlotCount = this.data.slots().size();

        Slot sourceSlot = this.slots.get(slot);
        if (sourceSlot == null || !sourceSlot.hasItem())
            return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyStack = sourceStack.copy();

        if (slot < blockEntitySlotCount)
        {
            if (!moveItemStackTo(sourceStack, blockEntitySlotCount, blockEntitySlotCount + vanillaSlotCount, false))
            {
                return ItemStack.EMPTY;
            }
        }
        else if (slot < blockEntitySlotCount + vanillaSlotCount)
        {
            if (!moveItemStackTo(sourceStack, 0, blockEntitySlotCount, false))
            {
                return ItemStack.EMPTY;
            }
        }
        else
        {
            GemstonePower.LOGGER.error("Invalid slot index " + slot);
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

    protected boolean canProcess(ItemStack itemStack)
    {
        Container container = new SimpleContainer(data.containerSize());
        container.setItem(0, itemStack);
        return this.level.getRecipeManager().getRecipeFor(this.recipeType, container, this.level).isPresent();
    }

    public int getProcessingProcess(int progressBarWidth)
    {
        int progress = 0;
        int total = 0;
        if (blockEntity instanceof MachineBE<?> machine)
        {
            progress = machine.getProcessingProgress();
            total = machine.getProcessingTotalTime();
        }
        return total != 0 && progress != 0 ? progress * (progressBarWidth + 1) / total : 0;
    }

    public int getEnergyLevel()
    {
        float energy = getEnergy();
        float capacity = getCapacity();

        int batteryHeight = (int) (-16 * (energy / capacity) + 16);

        return capacity != 0 && energy != 0 && batteryHeight <= 16 ? batteryHeight : 16;
    }

    public int getEnergy()
    {
        if (blockEntity instanceof MachineBE<?> machine)
            return machine.getEnergy();
        return 0;
    }

    public int getCapacity()
    {
        if (blockEntity instanceof MachineBE<?> machine)
            return machine.getCapacity();
        return 0;
    }

    public BlockPos getBlockPos()
    {
        if (blockEntity instanceof MachineBE<?> machine)
            return machine.getBlockPos();
        return null;
    }

    public int getMachineMode()
    {
        if (blockEntity instanceof MachineBE<?> machine)
            return machine.getMode();
        return 0;
    }

    public void setMachineMode(int mode)
    {
        if (blockEntity instanceof MachineBE<?> machine)
            machine.setMode(mode);
    }

    public FluidTank getFluidTank(int tank)
    {
        if (blockEntity instanceof FluidMachineBE<?> machine)
            return machine.getTank(tank);
        return new FluidTank(0);
    }
}
