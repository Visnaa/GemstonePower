package com.visnaa.gemstonepower.integration.jei.category;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.MachineScreen;
import com.visnaa.gemstonepower.config.ClientConfig;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.List;

public class OreWasherRecipeCategory implements IRecipeCategory<OreWasherRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(GemstonePower.MOD_ID, "ore_washing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GemstonePower.MOD_ID, "textures/gui/ore_washer_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic fluidTank;
    private final IDrawableStatic batterySlot;
    private final IDrawableStatic progress;
    private final IDrawableStatic energy;

    private final IDrawableAnimated progressAnimated;
    private final IDrawableAnimated energyAnimated;

    public OreWasherRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 34, 25, 126, 46);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ORE_WASHER.get()));
        this.fluidTank = helper.createDrawable(TEXTURE, 14, 19, 18, 48);
        this.batterySlot = helper.createDrawable(TEXTURE, 149, 37, 12, 18);
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
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 13).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 66, 3).addItemStack(!recipe.getResultItems().isEmpty() ? new ItemStack(recipe.getResultItems().get(0).getItem(), recipe.getCounts()[0]) : ItemStack.EMPTY);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 85, 3).addItemStack(recipe.getResultItems().size() > 1 ? new ItemStack(recipe.getResultItems().get(1).getItem(), recipe.getCounts()[1]) : ItemStack.EMPTY);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 66, 22).addItemStack(recipe.getResultItems().size() > 2 ? new ItemStack(recipe.getResultItems().get(2).getItem(), recipe.getCounts()[2]) : ItemStack.EMPTY);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 85, 22).addItemStack(recipe.getResultItems().size() > 3 ? new ItemStack(recipe.getResultItems().get(3).getItem(), recipe.getCounts()[3]) : ItemStack.EMPTY);
    }

    @Override
    public void draw(OreWasherRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        this.progressAnimated.draw(graphics, 41, 13);

        this.energyAnimated.draw(graphics, 115, 13);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 115, 13, 10, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fEnergy: §c" + recipe.getEnergyUsage() * recipe.getProcessingTime() + " " + ClientConfig.ENERGY_UNIT.get())), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);

        this.fluidTank.draw(graphics, -1, -1);
        FluidTank tank = new FluidTank(recipe.getFluid().getAmount());
        tank.setFluid(recipe.getFluid());
        MachineScreen.renderFluid(graphics, 0, 0, 46, tank);
        if (MachineScreen.isMouseInArea((int) mouseX, (int) mouseY, 0, 0, 16, 46))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(
                    Component.literal("§fFluid: §6" + recipe.getFluid().getDisplayName().getString()),
                    Component.literal("§fAmount: §b" + recipe.getFluid().getAmount())), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
    }
}
