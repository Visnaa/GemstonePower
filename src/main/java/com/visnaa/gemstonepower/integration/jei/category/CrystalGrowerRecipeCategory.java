package com.visnaa.gemstonepower.integration.jei.category;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.data.recipe.CrystalGrowerRecipe;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CrystalGrowerRecipeCategory implements IRecipeCategory<CrystalGrowerRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(GemstonePower.MOD_ID, "crystal_growing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GemstonePower.MOD_ID, "textures/gui/crystal_grower_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic progress;
    private final IDrawableStatic energy;

    private final IDrawableAnimated progressAnimated;
    private final IDrawableAnimated energyAnimated;

    public CrystalGrowerRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 52, 27, 108, 28);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRYSTAL_GROWER.get()));
        this.progress = helper.createDrawable(TEXTURE, 176, 16, 31, 11);
        this.energy = helper.createDrawable(TEXTURE, 176, 0, 10, 16);

        this.progressAnimated = helper.createAnimatedDrawable(this.progress, 310, IDrawableAnimated.StartDirection.LEFT, false);
        this.energyAnimated = helper.createAnimatedDrawable(this.energy, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public RecipeType<CrystalGrowerRecipe> getRecipeType()
    {
        return GemstonePowerJEIPlugin.CRYSTAL_GROWER_CATEGORY;
    }

    @Override
    public Component getTitle()
    {
        return Component.literal("Crystal Growing");
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
    public void setRecipe(IRecipeLayoutBuilder builder, CrystalGrowerRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 11).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 11).addItemStack(new ItemStack(recipe.getResultItem(null).getItem(), recipe.getCount()));
    }

    @Override
    public void draw(CrystalGrowerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        this.progressAnimated.draw(graphics, 22, 12);
        this.energyAnimated.draw(graphics, 97, 11);
        graphics.drawString(Minecraft.getInstance().font, "Energy: " + recipe.getEnergyUsage() * recipe.getProcessingTime() + " " + ClientConfig.ENERGY_UNIT.get(), 0, 0, 0x888888, false);
    }
}
