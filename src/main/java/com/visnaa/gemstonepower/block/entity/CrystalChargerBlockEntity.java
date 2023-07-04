package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.menu.CrystalChargerMenu;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.data.recipe.CrystalChargerRecipe;
import com.visnaa.gemstonepower.data.recipe.CrystalGrowerRecipe;
import com.visnaa.gemstonepower.network.energy.ForgeEnergyStorage;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModRecipes;
import com.visnaa.gemstonepower.util.EnergyUtilities;
import com.visnaa.gemstonepower.util.Tier;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

public class CrystalChargerBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible
{
    private static final int[] SLOTS_FOR_INPUT = new int[]{0};
    protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    int chargingProgress;
    int chargingTotalTime;
    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int id)
        {
            return switch (id)
                    {
                        case 0 -> CrystalChargerBlockEntity.this.chargingProgress;
                        case 1 -> CrystalChargerBlockEntity.this.chargingTotalTime;
                        case 2 -> CrystalChargerBlockEntity.this.energyStorage.getEnergyStored();
                        case 3 -> CrystalChargerBlockEntity.this.energyStorage.getMaxEnergyStored();
                        default -> 0;
                    };
        }

        public void set(int id, int val)
        {
            switch (id)
            {
                case 0 -> CrystalChargerBlockEntity.this.chargingProgress = val;
                case 1 -> CrystalChargerBlockEntity.this.chargingTotalTime = val;
            }

        }

        public int getCount() {
            return 4;
        }
    };
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<Container, ? extends CrystalChargerRecipe> quickCheck;

    public CrystalChargerBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CRYSTAL_CHARGER.get(), pos, state);
        this.quickCheck = RecipeManager.createCheck((RecipeType) ModRecipes.CRYSTAL_CHARGER_RECIPE);
    }

    protected Component getDefaultName()
    {
        String name = Component.translatable("menu." + GemstonePower.MOD_ID + ".crystal_charger").getString();
        String tier = Component.translatable("menu." + GemstonePower.MOD_ID + ".tier." + this.getBlockState().getValue(Tier.TIER).getSerializedName()).getString();
        return Component.literal(name + " (" + tier + ")");
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new CrystalChargerMenu(id, inv, this, this.dataAccess);
    }

    public void load(CompoundTag tag)
    {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.chargingProgress = tag.getInt("ChargingProgress");
        this.chargingTotalTime = tag.getInt("ChargingTotalTime");
        this.energyStorage.deserializeNBT(tag.get("Energy"));
        CompoundTag compoundtag = tag.getCompound("RecipesUsed");

        for(String s : compoundtag.getAllKeys())
        {
            this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }
    }

    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.putInt("ChargingProgress", this.chargingProgress);
        tag.putInt("ChargingTotalTime", this.chargingTotalTime);
        tag.put("Energy", energyStorage.serializeNBT());
        ContainerHelper.saveAllItems(tag, this.items);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((id, amount) -> compoundtag.putInt(id.toString(), amount));
        tag.put("RecipesUsed", compoundtag);
    }
    
    public static void serverTick(Level level, BlockPos pos, BlockState state, CrystalChargerBlockEntity blockEntity)
    {
        blockEntity.process(level, pos, state);
    }

    public void process(Level level, BlockPos pos, BlockState state)
    {
        boolean changed = false;

        boolean isFuelPresent = !this.items.get(0).isEmpty();
        if (isFuelPresent)
        {
            CrystalChargerRecipe recipe;
            recipe = this.quickCheck.getRecipeFor(this, level).orElse(null);

            int i = getMaxStackSize();

            if (this.canProcess(level.registryAccess(), recipe, this.items, i))
            {
                if (this.energyStorage.getEnergyStored() < getEnergyUsage(level, this)) return;
                this.chargingProgress++;
                this.energyStorage.consumeEnergy(getEnergyUsage(level, this));
                if (this.chargingProgress == this.chargingTotalTime)
                {
                    this.chargingProgress = 0;
                    this.chargingTotalTime = getTotalTime(level, this);
                    if (this.process(level.registryAccess(), recipe, this.items, i))
                    {
                        this.setRecipeUsed(recipe);
                    }

                    changed = true;
                }
            } else
            {
                this.chargingProgress = 0;
            }
        } else if (this.chargingProgress > 0)
        {
            this.chargingProgress = Mth.clamp(this.chargingProgress - 2, 0, this.chargingTotalTime);
        }

        if (changed)
        {
            setChanged(level, pos, state);
        }
    }

    private boolean canProcess(RegistryAccess access, @Nullable CrystalChargerRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (!items.get(0).isEmpty() && recipe != null)
        {
            ItemStack result = recipe.assemble(this, access);
            if (result.isEmpty())
                return false;
            else
            {
                ItemStack output = items.get(1);
                if (output.isEmpty())
                    return true;
                else if (!output.is(result.getItem()))
                    return false;
                else if (output.getCount() + result.getCount() <= size && output.getCount() + result.getCount() <= output.getMaxStackSize()) // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                else
                    return output.getCount() + result.getCount() <= result.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
            }
        }
        return false;
    }

    private boolean process(RegistryAccess access, @Nullable CrystalChargerRecipe recipe, NonNullList<ItemStack> items, int size)
    {
        if (recipe != null && this.canProcess(access, recipe, items, size))
        {
            ItemStack input = items.get(0);
            ItemStack result = recipe.assemble(this, access);
            ItemStack output = items.get(1);
            if (output.isEmpty())
                items.set(1, result.copy());
            else if (output.is(result.getItem()))
                output.grow(result.getCount());

            input.shrink(1);
            return true;
        }
        return false;
    }

    private static int getTotalTime(Level level, CrystalChargerBlockEntity blockEntity)
    {
        return EnergyUtilities.getTotalTime(blockEntity.getBlockState(), blockEntity.quickCheck.getRecipeFor(blockEntity, level).map(CrystalChargerRecipe::getProcessingTime).orElse(ServerConfig.DEFAULT_MACHINE_TIME.get()));
    }

    private static int getEnergyUsage(Level level, CrystalChargerBlockEntity blockEntity)
    {
        return EnergyUtilities.getUsage(blockEntity.getBlockState(), blockEntity.quickCheck.getRecipeFor(blockEntity, level).map(CrystalChargerRecipe::getEnergyUsage).orElse(ServerConfig.DEFAULT_MACHINE_USAGE.get()));
    }

    public int[] getSlotsForFace(Direction dir)
    {
        return SLOTS_FOR_INPUT;
    }

    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, @Nullable Direction dir)
    {
        return this.canPlaceItem(0, itemStack);
    }

    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction dir)
    {
        return dir == Direction.DOWN;
    }

    public int getContainerSize()
    {
        return this.items.size();
    }

    public boolean isEmpty()
    {
        for(ItemStack itemstack : this.items)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    public ItemStack getItem(int id)
    {
        return this.items.get(id);
    }

    public ItemStack removeItem(int id, int value)
    {
        return ContainerHelper.removeItem(this.items, id, value);
    }

    public ItemStack removeItemNoUpdate(int value)
    {
        return ContainerHelper.takeItem(this.items, value);
    }

    public void setItem(int id, ItemStack itemStack)
    {
        ItemStack itemstack = this.items.get(id);
        boolean isStackValid = !itemStack.isEmpty() && itemStack.is(itemstack.getItem()) && ItemStack.isSameItemSameTags(itemStack, itemstack);
        this.items.set(id, itemStack);
        if (itemStack.getCount() > this.getMaxStackSize())
        {
            itemStack.setCount(this.getMaxStackSize());
        }

        if (!isStackValid)
        {
            this.chargingTotalTime = getTotalTime(this.level, this);
            this.chargingProgress = 0;
            this.setChanged();
        }
    }

    public boolean stillValid(Player player)
    {
        if (this.level.getBlockEntity(this.worldPosition) != this)
        {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    public boolean canPlaceItem(int id, ItemStack itemStack)
    {
        if (id == 1)
            return false;
        return id == 0;
    }

    public void clearContent()
    {
        this.items.clear();
    }

    public void setRecipeUsed(@Nullable Recipe<?> recipe)
    {
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }

    @Nullable
    public CrystalGrowerRecipe getRecipeUsed()
    {
        return null;
    }

    public void awardUsedRecipes(Player player) {}

    public void fillStackedContents(StackedContents contents)
    {
        for(ItemStack itemStack : this.items)
        {
            contents.accountStack(itemStack);
        }
    }

    LazyOptional<? extends IItemHandler>[] itemHandlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    final ForgeEnergyStorage energyStorage = createEnergyStorage();
    LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing)
    {
        if (!this.remove)
        {
            if (capability == ForgeCapabilities.ITEM_HANDLER && facing != null)
            {
                if (facing == Direction.UP)
                    return itemHandlers[0].cast();
                else if (facing == Direction.DOWN)
                    return itemHandlers[1].cast();
                else
                    return itemHandlers[2].cast();
            } else if (capability == ForgeCapabilities.ENERGY)
            {
                return energy.cast();
            }
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        for (int i = 0; i < itemHandlers.length; i++)
            itemHandlers[i].invalidate();
        energy.invalidate();
    }

    @Override
    public void reviveCaps()
    {
        super.reviveCaps();
        this.itemHandlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
        this.energy = LazyOptional.of(() -> energyStorage);
    }

    private ForgeEnergyStorage createEnergyStorage()
    {
        return new ForgeEnergyStorage(EnergyUtilities.getCapacity(this.getBlockState(), ServerConfig.DEFAULT_MACHINE_CAPACITY.get()), Integer.MAX_VALUE, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }

            @Override
            public boolean canReceive()
            {
                return true;
            }

            @Override
            public boolean canExtract()
            {
                return false;
            }
        };
    }
}
