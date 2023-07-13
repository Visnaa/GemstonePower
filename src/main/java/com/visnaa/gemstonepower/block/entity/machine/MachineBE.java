package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.EnergyStorageBE;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.data.recipe.EnergyRecipe;
import com.visnaa.gemstonepower.networking.ModPackets;
import com.visnaa.gemstonepower.networking.packet.RecipeProgressSyncS2C;
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
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

public abstract class MachineBE<T extends Recipe<Container>> extends EnergyStorageBE implements WorldlyContainer, RecipeHolder, StackedContentsCompatible
{
    protected LazyOptional<? extends IItemHandler>[] itemHandlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    protected NonNullList<ItemStack> items;
    protected final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    protected final RecipeManager.CachedCheck<Container, T> quickCheck;

    protected final int[] inputSlots;
    protected final int[] outputSlots;

    protected int processingProgress;
    protected int processingTotalTime;

    public MachineBE(BlockEntityType<?> type, RecipeType<T> recipe, BlockPos pos, BlockState state, int inputSlotCount, int outputSlotCount)
    {
        super(type, pos, state);
        this.items = NonNullList.withSize(inputSlotCount + outputSlotCount, ItemStack.EMPTY);
        this.quickCheck = RecipeManager.createCheck(recipe);
        this.inputSlots = IntStream.range(0, inputSlotCount).toArray();
        this.outputSlots = IntStream.range(inputSlotCount, inputSlotCount + outputSlotCount).toArray();
    }

    @Override
    protected Component getDefaultName()
    {
        String name = Component.translatable("menu." + GemstonePower.MOD_ID + "." + ForgeRegistries.BLOCKS.getKey(getBlockState().getBlock()).getPath()).getString();
        String tier = Component.translatable("menu." + GemstonePower.MOD_ID + ".tier." + this.getBlockState().getValue(Tier.TIER).getSerializedName()).getString();
        return Component.literal(name + " (" + tier + ")");
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);

        this.processingProgress = tag.getInt("ProcessingProgress");
        this.processingTotalTime = tag.getInt("ProcessingTotalTime");

        CompoundTag compoundtag = tag.getCompound("RecipesUsed");
        for(String s : compoundtag.getAllKeys())
        {
            this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);

        tag.putInt("ProcessingProgress", this.processingProgress);
        tag.putInt("ProcessingTotalTime", this.processingTotalTime);

        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((id, amount) -> compoundtag.putInt(id.toString(), amount));
        tag.put("RecipesUsed", compoundtag);
    }

    public void setProcessingProgress(int processingProgress)
    {
        this.processingProgress = processingProgress;
    }

    public void setProcessingTotalTime(int processingTotalTime)
    {
        this.processingTotalTime = processingTotalTime;
    }

    public int getProcessingProgress()
    {
        return this.processingProgress;
    }

    public int getProcessingTotalTime()
    {
        return this.processingTotalTime;
    }

    public void process(Level level, BlockPos pos, BlockState state)
    {
        boolean changed = false;
        int size = getMaxStackSize();
        T recipe = this.quickCheck.getRecipeFor(this, level).orElse(null);

        if (this.energyStorage.getEnergyStored() < getEnergyUsage(level, this, recipe)) return;

        if (this.canProcess(level.registryAccess(), recipe, this.items, size))
        {
            this.processingProgress++;
            this.energyStorage.consumeEnergy(getEnergyUsage(level, this, recipe));
            if (this.processingProgress == this.processingTotalTime)
            {
                this.processingProgress = 0;
                this.processingTotalTime = getTotalTime(level, this, recipe);
                if (this.process(level.registryAccess(), recipe, this.items, size))
                {
                    this.setRecipeUsed(recipe);
                }
            }
            changed = true;
        }

        if (changed)
        {
            setChanged(level, pos, state);
            ModPackets.sendToClient(new RecipeProgressSyncS2C(getProcessingProgress(), getProcessingTotalTime(), pos));
        }
    }

    protected boolean canProcess(RegistryAccess access, @Nullable T recipe, NonNullList<ItemStack> items, int size)
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

    protected boolean process(RegistryAccess access, @Nullable T recipe, NonNullList<ItemStack> items, int size)
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

    protected static <R extends Recipe<Container>> int getTotalTime(Level level, MachineBE<R> blockEntity, R recipe)
    {
        if (!(recipe instanceof EnergyRecipe energyRecipe)) return EnergyUtilities.getUsage(blockEntity.getBlockState(), ServerConfig.DEFAULT_MACHINE_TIME.get());
        return EnergyUtilities.getTotalTime(blockEntity.getBlockState(), blockEntity.quickCheck.getRecipeFor(blockEntity, level).map((r) -> energyRecipe.getProcessingTime()).orElse(ServerConfig.DEFAULT_MACHINE_TIME.get()));
    }

    protected static <R extends Recipe<Container>> int getEnergyUsage(Level level, MachineBE<R> blockEntity, R recipe)
    {
        if (!(recipe instanceof EnergyRecipe energyRecipe)) return EnergyUtilities.getUsage(blockEntity.getBlockState(), ServerConfig.DEFAULT_MACHINE_USAGE.get());
        return EnergyUtilities.getUsage(blockEntity.getBlockState(), blockEntity.quickCheck.getRecipeFor(blockEntity, level).map((r) -> energyRecipe.getEnergyUsage()).orElse(ServerConfig.DEFAULT_MACHINE_USAGE.get()));
    }

    @Override
    public int getContainerSize()
    {
        return this.items.size();
    }

    @Override
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

    @Override
    public ItemStack getItem(int id)
    {
        return this.items.get(id);
    }

    @Override
    public ItemStack removeItem(int id, int value)
    {
        return ContainerHelper.removeItem(this.items, id, value);
    }

    @Override
    public ItemStack removeItemNoUpdate(int value)
    {
        return ContainerHelper.takeItem(this.items, value);
    }

    @Override
    public void setItem(int id, ItemStack itemStack)
    {
        ItemStack itemstack = this.items.get(id);
        boolean isStackValid = !itemStack.isEmpty() && itemStack.is(itemstack.getItem()) && ItemStack.isSameItemSameTags(itemStack, itemstack);
        this.items.set(id, itemStack);
        if (itemStack.getCount() > this.getMaxStackSize())
        {
            itemStack.setCount(this.getMaxStackSize());
        }

        if (!isStackValid && !List.of(outputSlots).contains(id))
        {
            this.processingTotalTime = getTotalTime(this.level, this, this.quickCheck.getRecipeFor(this, this.level).orElse(null));
            this.processingProgress = 0;
            this.setChanged();
            ModPackets.sendToClient(new RecipeProgressSyncS2C(getProcessingProgress(), getProcessingTotalTime(), getBlockPos()));
        }
    }

    @Override
    public void clearContent()
    {
        this.items.clear();
    }

    @Override
    public int[] getSlotsForFace(Direction dir)
    {
        return outputSlots;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, @Nullable Direction dir)
    {
        return List.of(inputSlots).contains(slot) && this.canPlaceItem(slot, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction dir)
    {
        return List.of(outputSlots).contains(slot) && dir == Direction.DOWN;
    }

    @Override
    public boolean stillValid(Player player)
    {
        if (this.level.getBlockEntity(this.worldPosition) != this)
        {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public boolean canPlaceItem(int id, ItemStack itemStack)
    {
        return List.of(inputSlots).contains(id);
    }

    @Override
    public void setRecipeUsed(@Nullable Recipe<?> recipe)
    {
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed()
    {
        return null;
    }

    @Override
    public void fillStackedContents(StackedContents contents)
    {
        for(ItemStack itemStack : this.items)
        {
            contents.accountStack(itemStack);
        }
    }

    @Override
    public <I> LazyOptional<I> getCapability(Capability<I> capability, @Nullable Direction facing)
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
            }
        }
        return super.getCapability(capability, facing);
    }
}