package com.visnaa.gemstonepower.client.screen.menu;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.recipe.CrystalChargerRecipe;
import com.visnaa.gemstonepower.registry.ModContainers;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class CrystalChargerMenu extends AbstractContainerMenu
{
    private final Container container;
    private final ContainerData data;
    protected final Level level;
    private final RecipeType<? extends CrystalChargerRecipe> recipeType;

    public CrystalChargerMenu(int id, Inventory inventory)
    {
        this(ModContainers.CRYSTAL_CHARGER.get(), ModRecipes.CRYSTAL_CHARGER_RECIPE, id, inventory, new SimpleContainer(2), new SimpleContainerData(4));
    }

    public CrystalChargerMenu(int id, Inventory inventory, Container container, ContainerData data)
    {
        this(ModContainers.CRYSTAL_CHARGER.get(), ModRecipes.CRYSTAL_CHARGER_RECIPE, id, inventory, container, data);
    }

    protected CrystalChargerMenu(MenuType<?> menu, RecipeType<? extends CrystalChargerRecipe> recipe, int id, Inventory inventory, Container container, ContainerData data)
    {
        super(menu, id);
        this.recipeType = recipe;
        checkContainerSize(container, 2);
        checkContainerDataCount(data, 4);
        this.container = container;
        this.data = data;
        this.level = inventory.player.level();
        this.addSlot(new Slot(container, 0, 53, 38));
        this.addSlot(new Slot(container, 1, 109, 38));

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
        final int blockEntitySlotCount = 2;

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

    protected boolean canProcess(ItemStack itemStack)
    {
        return this.level.getRecipeManager().getRecipeFor((RecipeType<CrystalChargerRecipe>) this.recipeType, new SimpleContainer(itemStack), this.level).isPresent();
    }

    public int getChargingProcess()
    {
        int i = this.data.get(0);
        int j = this.data.get(1);
        return j != 0 && i != 0 ? i * 16 / j : 0;
    }

    public int getEnergy()
    {
        float i = this.data.get(2);
        float j = this.data.get(3);

        int val = (int) (-16 * (i / j) + 16);

        return j != 0 && i != 0 && val <= 16 ? val : 16;
    }

    public int getData(int data)
    {
        return this.data.get(data);
    }
}
