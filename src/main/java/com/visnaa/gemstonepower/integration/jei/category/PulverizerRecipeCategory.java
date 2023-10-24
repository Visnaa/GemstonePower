package com.visnaa.gemstonepower.integration.jei.category;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.machine.MachineScreen;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.data.recipe.PulverizerRecipe;
import com.visnaa.gemstonepower.init.ModBlocks;
import com.visnaa.gemstonepower.integration.jei.GemstonePowerJEIPlugin;
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

import java.util.List;

public class PulverizerRecipeCategory implements IRecipeCategory<PulverizerRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(GemstonePower.MOD_ID, "pulverizing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GemstonePower.MOD_ID, "textures/gui/pulverizer_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic progress;
    private final IDrawableStatic energy;

    private final IDrawableAnimated progressAnimated;
    private final IDrawableAnimated energyAnimated;

    public PulverizerRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 52, 37, 108, 18);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.PULVERIZER.get()));
        this.progress = helper.createDrawable(TEXTURE, 176, 16, 18, 10);
        this.energy = helper.createDrawable(TEXTURE, 176, 0, 10, 16);

        this.progressAnimated = helper.createAnimatedDrawable(this.progress, 180, IDrawableAnimated.StartDirection.LEFT, false);
        this.energyAnimated = helper.createAnimatedDrawable(this.energy, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public RecipeType<PulverizerRecipe> getRecipeType()
    {
        return GemstonePowerJEIPlugin.PULVERIZER_CATEGORY;
    }

    @Override
    public Component getTitle()
    {
        return Component.literal("Pulverizing");
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
    public void setRecipe(IRecipeLayoutBuilder builder, PulverizerRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 1).addItemStack(new ItemStack(recipe.getResultItem(null).getItem(), recipe.getCount()));
    }

    @Override
    public void draw(PulverizerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        this.progressAnimated.draw(graphics, 28, 4);
        this.energyAnimated.draw(graphics, 97, 1);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 28, 4, 18, 10))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fProcessing time: §b" + recipe.getProcessingTime() + " t")), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 97, 1, 10, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fEnergy: §c" + recipe.getEnergyUsage() * recipe.getProcessingTime() + " " + ClientConfig.ENERGY_UNIT.get())), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
    }
}
