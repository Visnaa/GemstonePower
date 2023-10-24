package com.visnaa.gemstonepower.integration.jei.category;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.machine.MachineScreen;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.data.recipe.AlloySmelterRecipe;
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

public class AlloySmelterRecipeCategory implements IRecipeCategory<AlloySmelterRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(GemstonePower.MOD_ID, "alloying");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GemstonePower.MOD_ID, "textures/gui/alloy_smelter_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic progress;
    private final IDrawableStatic energy;

    private final IDrawableAnimated progressAnimated;
    private final IDrawableAnimated energyAnimated;

    public AlloySmelterRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 52, 27, 108, 39);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ALLOY_SMELTER.get()));
        this.progress = helper.createDrawable(TEXTURE, 176, 16, 35, 24);
        this.energy = helper.createDrawable(TEXTURE, 176, 0, 10, 16);

        this.progressAnimated = helper.createAnimatedDrawable(this.progress, 350, IDrawableAnimated.StartDirection.LEFT, false);
        this.energyAnimated = helper.createAnimatedDrawable(this.energy, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public RecipeType<AlloySmelterRecipe> getRecipeType()
    {
        return GemstonePowerJEIPlugin.ALLOY_SMELTER_CATEGORY;
    }

    @Override
    public Component getTitle()
    {
        return Component.literal("Alloying");
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
    public void setRecipe(IRecipeLayoutBuilder builder, AlloySmelterRecipe recipe, IFocusGroup focuses)
    {
        ItemStack input1 = new ItemStack(recipe.getIngredients().get(0).getItems()[0].getItem());
        ItemStack input2 = new ItemStack(recipe.getIngredients().get(1).getItems()[0].getItem());
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addItemStack(new ItemStack(input1.getItem(), recipe.getAmount(input1)));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 22).addItemStack(new ItemStack(input2.getItem(), recipe.getAmount(input2)));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 11).addItemStack(new ItemStack(recipe.getResultItem(null).getItem(), recipe.getCount()));
    }

    @Override
    public void draw(AlloySmelterRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        this.progressAnimated.draw(graphics, 20, 7);
        this.energyAnimated.draw(graphics, 97, 11);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 20, 7, 35, 24))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fProcessing time: §b" + recipe.getProcessingTime() + " t")), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 97, 11, 10, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fEnergy: §c" + recipe.getEnergyUsage() * recipe.getProcessingTime() + " " + ClientConfig.ENERGY_UNIT.get())), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
    }
}
