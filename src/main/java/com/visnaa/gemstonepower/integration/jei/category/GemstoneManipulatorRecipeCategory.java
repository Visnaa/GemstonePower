package com.visnaa.gemstonepower.integration.jei.category;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.machine.MachineScreen;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.data.recipe.GemstoneManipulatorRecipe;
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

public class GemstoneManipulatorRecipeCategory implements IRecipeCategory<GemstoneManipulatorRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(GemstonePower.MOD_ID, "gemstone_manipulation");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GemstonePower.MOD_ID, "textures/gui/gemstone_manipulator_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic progress;
    private final IDrawableStatic energy;

    private final IDrawableAnimated progressAnimated;
    private final IDrawableAnimated energyAnimated;

    public GemstoneManipulatorRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 27, 37, 133, 18);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GEMSTONE_MANIPULATOR.get()));
        this.progress = helper.createDrawable(TEXTURE, 176, 16, 20, 14);
        this.energy = helper.createDrawable(TEXTURE, 176, 0, 10, 16);

        this.progressAnimated = helper.createAnimatedDrawable(this.progress, 200, IDrawableAnimated.StartDirection.LEFT, false);
        this.energyAnimated = helper.createAnimatedDrawable(this.energy, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public RecipeType<GemstoneManipulatorRecipe> getRecipeType()
    {
        return GemstonePowerJEIPlugin.GEMSTONE_MANIPULATOR_CATEGORY;
    }

    @Override
    public Component getTitle()
    {
        return Component.literal("Gemstone Manipulation");
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
    public void setRecipe(IRecipeLayoutBuilder builder, GemstoneManipulatorRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 1).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 82, 1).addItemStack(new ItemStack(recipe.getResultItem(null).getItem(), recipe.getCount()));
    }

    @Override
    public void draw(GemstoneManipulatorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        this.progressAnimated.draw(graphics, 51, 2);
        this.energyAnimated.draw(graphics, 122, 1);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 51, 2, 20, 14))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fProcessing time: §b" + recipe.getProcessingTime() + " t")), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 122, 1, 10, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fEnergy: §c" + recipe.getEnergyUsage() * recipe.getProcessingTime() + " " + ClientConfig.ENERGY_UNIT.get())), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
    }
}
