package com.visnaa.gemstonepower.block.entity.machine;

import com.google.common.collect.Maps;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.EnergyStorageBE;
import com.visnaa.gemstonepower.block.entity.TickingBlockEntity;
import com.visnaa.gemstonepower.block.machine.MachineBlock;
import com.visnaa.gemstonepower.capability.energy.EnergyStorage;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.data.recipe.EnergyRecipe;
import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.menu.machine.MachineMenu;
import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.EnergySyncS2C;
import com.visnaa.gemstonepower.network.packet.MachineConfigSyncS2C;
import com.visnaa.gemstonepower.network.packet.RecipeProgressSyncS2C;
import com.visnaa.gemstonepower.util.MachineUtil;
import com.visnaa.gemstonepower.util.Tier;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.capabilities.Capabilities;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.util.LazyOptional;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.wrapper.EmptyHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.IntStream;

public class MachineBE<T extends Recipe<Container>> extends BaseContainerBlockEntity implements EnergyStorageBE, TickingBlockEntity, WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible
{
    protected HashMap<Direction, LazyOptional<? extends IItemHandler>> itemHandlers = Maps.newHashMap(Maps.asMap(Set.of(Direction.values()), dir -> LazyOptional.of(() -> new SidedInvWrapper(this, dir))));
    protected NonNullList<ItemStack> items;
    protected final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    protected final RecipeManager.CachedCheck<Container, T> quickCheck;
    protected final RecipeType<T> recipe;
    protected final HashMap<Direction, MachineUtil.MachineConfigs> configs = Maps.newHashMap(Maps.asMap(Set.of(Direction.values()), dir -> MachineUtil.MachineConfigs.NONE));

    protected final int[] inputSlots;
    protected final int[] outputSlots;

    protected int processingProgress;
    protected int processingTotalTime;
    protected int mode;

    protected final EnergyStorage energyStorage = createEnergyStorage();
    private LazyOptional<IEnergyStorage> lazyEnergy = LazyOptional.of(() -> energyStorage);

    private MenuType<? extends MachineMenu> menu;

    public MachineBE(BlockEntityType<?> type, RecipeType<T> recipe, BlockPos pos, BlockState state, int inputSlotCount, int outputSlotCount, MenuType<? extends MachineMenu> menu)
    {
        super(type, pos, state);
        this.items = NonNullList.withSize(inputSlotCount + outputSlotCount, ItemStack.EMPTY);
        this.quickCheck = RecipeManager.createCheck(recipe);
        this.recipe = recipe;
        this.inputSlots = IntStream.range(0, inputSlotCount).toArray();
        this.outputSlots = IntStream.range(inputSlotCount, inputSlotCount + outputSlotCount).toArray();
        this.menu = menu;
    }

    @Override
    protected Component getDefaultName()
    {
        String name = Component.translatable("menu." + GemstonePower.MOD_ID + "." + BuiltInRegistries.BLOCK.getKey(getBlockState().getBlock()).getPath()).getString();
        String tier = getBlockState().hasProperty(Tier.TIER) ? "(" + Component.translatable("menu." + GemstonePower.MOD_ID + ".tier." + this.getBlockState().getValue(Tier.TIER).getSerializedName()).getString() + ")" : "";
        return Component.literal(name + " " + tier);
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        if (menu != null)
            return new MachineMenu(menu, recipe, new MenuData(id, inv, this, inputSlots.length + outputSlots.length, MenuData.createSlots(inputSlots.length + outputSlots.length)), getBlockPos());
        return null;
    }

    @Override
    public void setChanged()
    {
        super.setChanged();
        energyStorage.onEnergyChanged();
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);

        this.processingProgress = tag.getInt("ProcessingProgress");
        this.processingTotalTime = tag.getInt("ProcessingTotalTime");
        this.mode = tag.getInt("Mode");
        if (tag.contains("Energy"))
            energyStorage.deserializeNBT(tag.get("Energy"));

        CompoundTag recipeTag = tag.getCompound("RecipesUsed");
        recipeTag.getAllKeys().forEach(key -> recipesUsed.put(new ResourceLocation(key), recipeTag.getInt(key)));

        if (tag.contains("Configs"))
        {
            CompoundTag configTag = tag.getCompound("Configs");
            setConfig(MachineUtil.MachineConfigs.getByName(configTag.getString(getBlockState().getValue(MachineBlock.FACING).getClockWise().getName())),
                    MachineUtil.MachineConfigs.getByName(configTag.getString(getBlockState().getValue(MachineBlock.FACING).getCounterClockWise().getName())),
                    MachineUtil.MachineConfigs.getByName(configTag.getString(getBlockState().getValue(MachineBlock.FACING).getOpposite().getName())),
                    MachineUtil.MachineConfigs.getByName(configTag.getString("up")),
                    MachineUtil.MachineConfigs.getByName(configTag.getString("down")));
        }
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);

        tag.putInt("ProcessingProgress", this.processingProgress);
        tag.putInt("ProcessingTotalTime", this.processingTotalTime);
        tag.putInt("Mode", this.mode);
        tag.put("Energy", energyStorage.serializeNBT());

        CompoundTag recipesTag = new CompoundTag();
        this.recipesUsed.forEach((id, amount) -> recipesTag.putInt(id.toString(), amount));
        tag.put("RecipesUsed", recipesTag);

        if (getBlockState().hasProperty(MachineBlock.FACING))
        {
            CompoundTag configTag = new CompoundTag();
            this.configs.forEach((dir, config) -> configTag.putString(dir.toString(), config.getSerializedName()));
            tag.put("Configs", configTag);
            sendConfigToClients(configs.get(getBlockState().getValue(MachineBlock.FACING).getClockWise()), configs.get(getBlockState().getValue(MachineBlock.FACING).getCounterClockWise()), configs.get(getBlockState().getValue(MachineBlock.FACING).getOpposite()), configs.get(Direction.UP), configs.get(Direction.DOWN));
        }
        setChanged();
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

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        process(level, pos, state);
        sendItems(level, pos, state);
    }

    public void process(Level level, BlockPos pos, BlockState state)
    {
        boolean changed = false;
        int size = getMaxStackSize();
        RecipeHolder<T> recipeHolder = this.quickCheck.getRecipeFor(this, level).orElse(null);
        if (recipeHolder == null)
            return;
        T recipe = recipeHolder.value();

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
                    this.setRecipeUsed(recipeHolder);
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

    public void sendItems(Level level, BlockPos pos, BlockState state)
    {
        for (Direction dir : Direction.values())
        {
            if (!configs.get(dir).canOutput() || level.getBlockEntity(pos.relative(dir)) == null || !level.getBlockEntity(pos.relative(dir)).getCapability(Capabilities.ITEM_HANDLER, dir.getOpposite()).isPresent() || !hasItems())
                continue;

            List<ItemStack> outputItems = new ArrayList<>();
            for (int slot : outputSlots)
            {
                if (items.get(slot).isEmpty())
                    continue;
                outputItems.add(items.get(slot));
            }
            if (outputItems.isEmpty())
                return;

            for (ItemStack stack : outputItems)
            {
                if (stack.isEmpty())
                    continue;

                ItemStack transfer = stack.copyWithCount(1);
                ItemStack remainder = ItemHandlerHelper.insertItemStacked(level.getBlockEntity(pos.relative(dir)).getCapability(Capabilities.ITEM_HANDLER, dir.getOpposite()).orElse(EmptyHandler.INSTANCE), transfer, false);
                if (remainder.isEmpty())
                    stack.shrink(1);
            }
        }
    }

    private boolean hasItems()
    {
        for (int slot : outputSlots)
            if (!items.get(slot).isEmpty())
                return true;
        return false;
    }

    protected static <R extends Recipe<Container>> int getTotalTime(Level level, MachineBE<R> blockEntity, R recipe)
    {
        if (!(recipe instanceof EnergyRecipe energyRecipe))
            return MachineUtil.getTotalTime(blockEntity.getBlockState(), ServerConfig.DEFAULT_MACHINE_TIME.get());
        return MachineUtil.getTotalTime(blockEntity.getBlockState(), blockEntity.quickCheck.getRecipeFor(blockEntity, level).map((r) -> energyRecipe.getProcessingTime()).orElse(ServerConfig.DEFAULT_MACHINE_TIME.get()));
    }

    protected static <R extends Recipe<Container>> int getEnergyUsage(Level level, MachineBE<R> blockEntity, R recipe)
    {
        if (!(recipe instanceof EnergyRecipe energyRecipe))
            return MachineUtil.getUsage(blockEntity.getBlockState(), ServerConfig.DEFAULT_MACHINE_USAGE.get());
        return MachineUtil.getUsage(blockEntity.getBlockState(), blockEntity.quickCheck.getRecipeFor(blockEntity, level).map((r) -> energyRecipe.getEnergyUsage()).orElse(ServerConfig.DEFAULT_MACHINE_USAGE.get()));
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
            RecipeHolder<T> holder = this.quickCheck.getRecipeFor(this, this.level).orElse(null);
            this.processingTotalTime = holder == null ? Integer.MAX_VALUE : getTotalTime(this.level, this, holder.value());
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
        if (configs.get(dir).canInteract())
        {
            if (configs.get(dir).canInput() && configs.get(dir).canOutput())
                return IntStream.concat(Arrays.stream(inputSlots), Arrays.stream(outputSlots)).toArray();
            else if (configs.get(dir).canInput())
                return inputSlots;
            else if (configs.get(dir).canOutput())
                return outputSlots;
        }
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int id, ItemStack itemStack, @Nullable Direction dir)
    {
        if (!configs.get(dir).canInput())
            return false;
        for (int slot : inputSlots)
            if (slot == id)
                return true;
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int id, ItemStack itemStack, Direction dir)
    {
        if (!configs.get(dir).canOutput())
            return false;
        if (configs.get(dir).canInput())
        {
            for (int slot : outputSlots)
                if (slot - inputSlots[inputSlots.length - 1] == id)
                    return true;
        }
        else
        {
            for (int slot : IntStream.concat(Arrays.stream(inputSlots), Arrays.stream(outputSlots)).toArray())
                if (slot == id)
                    return true;
        }
        return false;
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
        for (int slot : inputSlots)
            if (slot == id)
                return true;
        return false;
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> holder)
    {
        if (holder != null) {
            ResourceLocation resourcelocation = holder.id();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }

    @Nullable
    @Override
    public RecipeHolder<?> getRecipeUsed()
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
    public <I> @NotNull LazyOptional<I> getCapability(@NotNull Capability<I> capability, @Nullable Direction direction)
    {
        if (!this.remove)
        {
            if (capability == Capabilities.ITEM_HANDLER && direction != null)
            {
                LazyOptional<? extends IItemHandler> handler = itemHandlers.get(direction);
                return handler.isPresent() ? handler.cast() : super.getCapability(capability, direction);
            }
            else if (capability == Capabilities.ENERGY)
                return lazyEnergy.cast();
        }
        return super.getCapability(capability, direction);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        lazyEnergy.invalidate();
    }

    @Override
    public void reviveCaps()
    {
        super.reviveCaps();
        lazyEnergy = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public void setEnergy(int energy)
    {
        energyStorage.setEnergy(energy);
    }

    @Override
    public void setCapacity(int capacity)
    {
        energyStorage.setCapacity(capacity);
    }

    @Override
    public int getEnergy()
    {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getCapacity()
    {
        return energyStorage.getMaxEnergyStored();
    }

    public int getMode()
    {
        return mode;
    }

    public void setMode(int mode)
    {
        this.mode = mode;
        if (level != null && !level.isClientSide())
        {
            processingProgress = 0;
            ModPackets.sendToClient(new RecipeProgressSyncS2C(getProcessingProgress(), getProcessingTotalTime(), getBlockPos()));
        }
    }

    public Tier getTier()
    {
        if (level == null || !getBlockState().hasProperty(Tier.TIER))
            return Tier.NONE;
        return getBlockState().getValue(Tier.TIER);
    }

    public void setConfig(MachineUtil.MachineConfigs left, MachineUtil.MachineConfigs right, MachineUtil.MachineConfigs back, MachineUtil.MachineConfigs up, MachineUtil.MachineConfigs down)
    {
        configs.put(getBlockState().getValue(MachineBlock.FACING), MachineUtil.MachineConfigs.NONE);
        configs.put(getBlockState().getValue(MachineBlock.FACING).getClockWise(), left);
        configs.put(getBlockState().getValue(MachineBlock.FACING).getCounterClockWise(), right);
        configs.put(getBlockState().getValue(MachineBlock.FACING).getOpposite(), back);
        configs.put(Direction.UP, up);
        configs.put(Direction.DOWN, down);
        setChanged();
    }

    public void sendConfigToClients(MachineUtil.MachineConfigs left, MachineUtil.MachineConfigs right, MachineUtil.MachineConfigs back, MachineUtil.MachineConfigs up, MachineUtil.MachineConfigs down)
    {
        if (level == null || level.isClientSide())
            return;
        setConfig(left, right, back, up, down);
        ModPackets.sendToClient(new MachineConfigSyncS2C(left, right, back, up, down, getBlockPos()));
    }

    public HashMap<Direction, MachineUtil.MachineConfigs> getConfigs()
    {
        return configs;
    }

    @Override
    public EnergyStorage createEnergyStorage()
    {
        return new EnergyStorage(ServerConfig.DEFAULT_MACHINE_CAPACITY.get(), ServerConfig.ENERGY_TRANSFER_RATE.get(), ServerConfig.ENERGY_TRANSFER_RATE.get())
        {
            @Override
            public void onEnergyChanged()
            {
                if (level != null && !level.isClientSide())
                    ModPackets.sendToClient(new EnergySyncS2C(energy, capacity, getBlockPos()));
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
