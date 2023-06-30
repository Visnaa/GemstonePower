package com.visnaa.gemstonepower.integration.jei.category;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.recipe.OreWasherRecipe;
import com.visnaa.gemstonepower.integration.jei.GemstonePowerJEIPlugin;
import com.visnaa.gemstonepower.registry.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class OreWasherRecipeCategory implements IRecipeCategory<OreWasherRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(GemstonePower.MOD_ID, "ore_washing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GemstonePower.MOD_ID, "textures/gui/ore_washer_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic progress;
    private final IDrawableStatic energy;

    private final IDrawableAnimated progressAnimated;
    private final IDrawableAnimated energyAnimated;

    public OreWasherRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 52, 17, 108, 47);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ORE_WASHER.get()));
        this.progress = helper.createDrawable(TEXTURE, 176, 16, 19, 15);
        this.energy = helper.createDrawable(TEXTURE, 176, 0, 10, 16);

        this.progressAnimated = helper.createAnimatedDrawable(this.progress, 190, IDrawableAnimated.StartDirection.LEFT, false);
        this.energyAnimated = helper.createAnimatedDrawable(this.energy, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public RecipeType<OreWasherRecipe> getRecipeType()
    {
        return GemstonePowerJEIPlugin.ORE_WASHER_CATEGORY;
    }

    @Override
    public Component getTitle()
    {
        return Component.literal("Ore Washing");
    }

    @Override
    public IDrawable getBackground()
    {
        return this.background;
    }

    @Override
    public IDrawable getIcon()
    {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, OreWasherRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 21).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 48, 11).addItemStack(recipe.getResultItems().size() > 0 ? recipe.getResultItems().get(0) : ItemStack.EMPTY);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 67, 11).addItemStack(recipe.getResultItems().size() > 1 ? recipe.getResultItems().get(1) : ItemStack.EMPTY);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 48, 30).addItemStack(recipe.getResultItems().size() > 2 ? recipe.getResultItems().get(2) : ItemStack.EMPTY);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 67, 30).addItemStack(recipe.getResultItems().size() > 3 ? recipe.getResultItems().get(3) : ItemStack.EMPTY);
    }

    @Override
    public void draw(OreWasherRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        this.progressAnimated.draw(graphics, 23, 21);
        this.energyAnimated.draw(graphics, 97, 21);
        //Minecraft.getInstance().font.draw(stack, "Energy: " + recipe.getEnergyUsage() * recipe.getProcessingTime() + " FE", 0, 0, 0x888888);
    }
}
