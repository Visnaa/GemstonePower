package com.visnaa.gemstonepower.integration.jei.category;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.machine.MachineScreen;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.data.recipe.FissionReactorRecipe;
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

public class FissionReactorRecipeCategory implements IRecipeCategory<FissionReactorRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(GemstonePower.MOD_ID, "nuclear_fission");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GemstonePower.MOD_ID, "textures/gui/fission_reactor_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic progress;
    private final IDrawableStatic energy;
    private final IDrawableStatic heat;

    private final IDrawableAnimated progressAnimated;
    private final IDrawableAnimated energyAnimated;
    private final IDrawableAnimated heatAnimated;

    public FissionReactorRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 17, 33, 150, 26);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FISSION_REACTOR.get()));
        this.progress = helper.createDrawable(TEXTURE, 176, 16, 21, 20);
        this.energy = helper.createDrawable(TEXTURE, 176, 0, 10, 16);
        this.heat = helper.createDrawable(TEXTURE, 176, 36, 3, 16);

        this.progressAnimated = helper.createAnimatedDrawable(this.progress, 200, IDrawableAnimated.StartDirection.LEFT, false);
        this.energyAnimated = helper.createAnimatedDrawable(this.energy, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
        this.heatAnimated = helper.createAnimatedDrawable(this.heat, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public RecipeType<FissionReactorRecipe> getRecipeType()
    {
        return GemstonePowerJEIPlugin.FISSION_REACTOR_CATEGORY;
    }

    @Override
    public Component getTitle()
    {
        return Component.literal("Nuclear Fission");
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
    public void setRecipe(IRecipeLayoutBuilder builder, FissionReactorRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 36, 5).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 92, 5).addItemStack(new ItemStack(recipe.getResultItem(null).getItem(), 1));
    }

    @Override
    public void draw(FissionReactorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        this.progressAnimated.draw(graphics, 62, 3);
        this.energyAnimated.draw(graphics, 132, 5);
        this.heatAnimated.draw(graphics, 4, 5);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 62, 3, 21, 20))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fProcessing Time: §b" + recipe.getProcessingTime() + " t")), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 132, 5, 10, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fEnergy: §c" + recipe.getEnergyGeneration() + " " + ClientConfig.ENERGY_UNIT.get() + "/t")), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 4, 5, 3, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fHeat: §e" + recipe.getHeatGeneration() + " " + "HU/t")), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
    }
}
