package com.visnaa.gemstonepower.data.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.machine.MetalFormerBE;
import com.visnaa.gemstonepower.init.ModBlocks;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MetalFormerRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "metal_former");
    private final Ingredient input;
    private final ItemStack output;
    private final String mode;
    private final int count;
    private final int processingTime;
    private final int energyUsage;

    public MetalFormerRecipe(Ingredient input, ItemStack output, String mode, int count, int processingTime, int energyUsage)
    {
        this.input = input;
        this.output = output;
        this.mode = mode;
        this.count = count;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        if (level.isClientSide) return false;
        if (input.test(container.getItem(0)) && container instanceof MetalFormerBE metalFormer && MachineUtil.MachineModes.isValid(metalFormer.getMode(), mode))
            return true;
        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(1, input);
        return ingredients;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess access)
    {
        return new ItemStack(output.getItem(), getCount());
    }

    @Override
    public ItemStack getResultItem(@Nullable RegistryAccess access)
    {
        return output.copy();
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.METAL_FORMER_RECIPE;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }

    @Override
    public boolean isIncomplete()
    {
        return true;
    }

    public int getCount()
    {
        return count;
    }

    public String getMachineMode()
    {
        return mode;
    }

    public int getProcessingTime()
    {
        return processingTime;
    }

    public int getEnergyUsage()
    {
        return energyUsage;
    }

    public ItemStack getIcon()
    {
        return new ItemStack(ModBlocks.METAL_FORMER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.METAL_FORMER_RECIPE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<MetalFormerRecipe>
    {
        @Override
        public String toString()
        {
            return MetalFormerRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer implements RecipeSerializer<MetalFormerRecipe>
    {
        private final Codec<MetalFormerRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(recipe -> recipe.getIngredients().get(0)),
                BuiltInRegistries.ITEM.byNameCodec().xmap(ItemStack::new, ItemStack::getItem).fieldOf("output").forGetter(recipe -> recipe.getResultItem(null)),
                Codec.STRING.fieldOf("mode").forGetter(MetalFormerRecipe::getMachineMode),
                Codec.INT.fieldOf("count").forGetter(MetalFormerRecipe::getCount),
                Codec.INT.fieldOf("processingTime").forGetter(MetalFormerRecipe::getProcessingTime),
                Codec.INT.fieldOf("energyUsage").forGetter(MetalFormerRecipe::getEnergyUsage)
        ).apply(builder, MetalFormerRecipe::new));

        @Override
        public Codec<MetalFormerRecipe> codec()
        {
            return CODEC;
        }

        @Nullable
        @Override
        public MetalFormerRecipe fromNetwork(FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            String mode = buffer.readUtf();
            int count = buffer.readInt();
            int processingTime = buffer.readInt();
            int energyUsage = buffer.readInt();
            return new MetalFormerRecipe(inputs.get(0), output, mode, count, processingTime, energyUsage);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MetalFormerRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient seed : recipe.getIngredients())
            {
                seed.toNetwork(buffer);
            }
            buffer.writeItem(recipe.getResultItem(null));
            buffer.writeUtf(recipe.getMachineMode());
            buffer.writeInt(recipe.getCount());
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergyUsage());
        }
    }
}
