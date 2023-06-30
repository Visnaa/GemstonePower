package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.GemstoneGeneratorBlock;
import com.visnaa.gemstonepower.client.screen.menu.GemstoneGeneratorMenu;
import com.visnaa.gemstonepower.data.recipe.GemstoneGeneratorRecipe;
import com.visnaa.gemstonepower.network.energy.ForgeEnergyStorage;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModRecipes;
import com.visnaa.gemstonepower.util.EnergyUtilities;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GemstoneGeneratorBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible
{
    private static final int[] SLOTS_FOR_INPUT = new int[]{0};
    protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    int chargingProgress;
    int chargingTotalTime;
    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int id)
        {
            return switch (id)
                    {
                        case 0 -> GemstoneGeneratorBlockEntity.this.chargingProgress;
                        case 1 -> GemstoneGeneratorBlockEntity.this.chargingTotalTime;
                        case 2 -> GemstoneGeneratorBlockEntity.this.energyStorage.getEnergyStored();
                        case 3 -> GemstoneGeneratorBlockEntity.this.energyStorage.getMaxEnergyStored();
                        default -> 0;
                    };
        }

        public void set(int id, int val)
        {
            switch (id)
            {
                case 0 -> GemstoneGeneratorBlockEntity.this.chargingProgress = val;
                case 1 -> GemstoneGeneratorBlockEntity.this.chargingTotalTime = val;
            }

        }

        public int getCount() {
            return 4;
        }
    };
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<Container, ? extends GemstoneGeneratorRecipe> quickCheck;

    public GemstoneGeneratorBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.GEMSTONE_GENERATOR.get(), pos, state);
        this.quickCheck = RecipeManager.createCheck((RecipeType) ModRecipes.GEMSTONE_GENERATOR_RECIPE);
    }

    protected Component getDefaultName() {
        return Component.translatable("menu." + GemstonePower.MOD_ID + ".gemstone_generator");
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new GemstoneGeneratorMenu(id, inv, this, this.dataAccess);
    }

    public void load(CompoundTag tag)
    {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.chargingProgress = tag.getInt("ChargingTime");
        this.chargingTotalTime = tag.getInt("ChargingTimeTotal");
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
        tag.putInt("ChargingTime", this.chargingProgress);
        tag.putInt("ChargingTimeTotal", this.chargingTotalTime);
        tag.put("Energy", energyStorage.serializeNBT());
        ContainerHelper.saveAllItems(tag, this.items);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((p_187449_, p_187450_) -> compoundtag.putInt(p_187449_.toString(), p_187450_));
        tag.put("RecipesUsed", compoundtag);
    }
    
    public static void serverTick(Level level, BlockPos pos, BlockState state, GemstoneGeneratorBlockEntity blockEntity)
    {
        blockEntity.process(level, pos, state);
        blockEntity.sendPower(level, pos, state);
    }

    public void process(Level level, BlockPos pos, BlockState state)
    {
        boolean changed = false;

        ItemStack fuel = this.items.get(0);
        boolean isFuelPresent = !fuel.isEmpty();
        if (isFuelPresent)
        {
            GemstoneGeneratorRecipe recipe;
            recipe = this.quickCheck.getRecipeFor(this, level).orElse(null);

            if (this.canBurn(recipe, this.items))
            {
                ++this.chargingProgress;
                if (this.chargingProgress == this.chargingTotalTime)
                {
                    this.chargingProgress = 0;
                    this.chargingTotalTime = getTotalTime(level, this);
                    if (this.burn(recipe, this.items))
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

        if (state.getValue(GemstoneGeneratorBlock.POWERED) != chargingProgress > 0)
        {
            changed = true;
            state = state.setValue(GemstoneGeneratorBlock.POWERED, chargingProgress > 0);
            level.setBlock(pos, state, 3);
        }

        if (changed)
        {
            setChanged(level, pos, state);
        }
    }

    protected void sendPower(Level level, BlockPos pos, BlockState state)
    {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            List<BlockEntity> outputs = new ArrayList<>();
            for (Direction direction : Direction.values())
            {
                BlockEntity be = level.getBlockEntity(pos.relative(direction));
                if (be != null && be.getCapability(ForgeCapabilities.ENERGY, direction).isPresent())
                {
                    outputs.add(be);
                }
            }

            if (outputs.size() > 0)
            {
                int energy = Math.min(capacity.get() / outputs.size(), EnergyUtilities.UTR);
                for (BlockEntity be : outputs)
                {
                    be.getCapability(ForgeCapabilities.ENERGY).map(handler ->
                    {
                        int received = handler.receiveEnergy(energy, false);
                        capacity.addAndGet(-received);
                        energyStorage.consumeEnergy(received);
                        setChanged();
                        return true;
                    });
                }
                setChanged(level, pos, state);
            }
        }
    }

    private boolean canBurn(@Nullable GemstoneGeneratorRecipe recipe, NonNullList<ItemStack> items)
    {
        if (!items.get(0).isEmpty() && recipe != null)
        {
            return recipe.getIngredients().get(0).test(items.get(0));
        }
        return false;
    }

    private boolean burn(@Nullable GemstoneGeneratorRecipe recipe, NonNullList<ItemStack> items)
    {
        if (recipe != null && this.canBurn(recipe, items))
        {
            energyStorage.addEnergy(recipe.getEnergy());
            items.get(0).shrink(1);
            return true;
        }
        return false;
    }

    private static int getTotalTime(Level level, GemstoneGeneratorBlockEntity blockEntity)
    {
        return blockEntity.quickCheck.getRecipeFor(blockEntity, level).map(GemstoneGeneratorRecipe::getBurnTime).orElse(200);
    }

    public int[] getSlotsForFace(Direction dir)
    {
        return SLOTS_FOR_INPUT;
    }

    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, @Nullable Direction dir)
    {
        return this.canPlaceItem(slot, itemStack);
    }

    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction dir)
    {
        return false;
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

        if (id == 0 && !isStackValid) {
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

    public boolean canPlaceItem(int p_58389_, ItemStack p_58390_)
    {
        return true;
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
    public GemstoneGeneratorRecipe getRecipeUsed()
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
        for (int x = 0; x < itemHandlers.length; x++)
            itemHandlers[x].invalidate();
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
        return new ForgeEnergyStorage(100000000, 0, 10000) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }

            @Override
            public boolean canReceive()
            {
                return false;
            }

            @Override
            public boolean canExtract()
            {
                return true;
            }
        };
    }
}
