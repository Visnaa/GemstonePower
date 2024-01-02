package com.visnaa.gemstonepower.integration.jei.category;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.machine.MachineScreen;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.data.recipe.MetalFormerRecipe;
import com.visnaa.gemstonepower.init.ModBlocks;
import com.visnaa.gemstonepower.integration.jei.GemstonePowerJEIPlugin;
import com.visnaa.gemstonepower.util.MachineUtil.MachineModes;
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

public class MetalFormerRecipeCategory implements IRecipeCategory<MetalFormerRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(GemstonePower.MOD_ID, "forming");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GemstonePower.MOD_ID, "textures/gui/metal_former_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic progress;
    private final IDrawableStatic energy;

    private final IDrawableStatic platingMode;
    private final IDrawableStatic rollingMode;
    private final IDrawableStatic extrudingMode;
    private final IDrawableStatic platingModeHover;
    private final IDrawableStatic rollingModeHover;
    private final IDrawableStatic extrudingModeHover;

    private final IDrawableAnimated progressAnimated;
    private final IDrawableAnimated energyAnimated;

    public MetalFormerRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 15, 37, 145, 18);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.METAL_FORMER.get()));
        this.progress = helper.createDrawable(TEXTURE, 176, 16, 27, 18);
        this.energy = helper.createDrawable(TEXTURE, 176, 0, 10, 16);

        this.platingMode = helper.createDrawable(TEXTURE, 176, 34, 20, 20);
        this.rollingMode = helper.createDrawable(TEXTURE, 196, 34, 20, 20);
        this.extrudingMode = helper.createDrawable(TEXTURE, 216, 34, 20, 20);
        this.platingModeHover = helper.createDrawable(TEXTURE, 176, 54, 20, 20);
        this.rollingModeHover = helper.createDrawable(TEXTURE, 196, 54, 20, 20);
        this.extrudingModeHover = helper.createDrawable(TEXTURE, 216, 54, 20, 20);

        this.progressAnimated = helper.createAnimatedDrawable(this.progress, 270, IDrawableAnimated.StartDirection.LEFT, false);
        this.energyAnimated = helper.createAnimatedDrawable(this.energy, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public RecipeType<MetalFormerRecipe> getRecipeType()
    {
        return GemstonePowerJEIPlugin.METAL_FORMER_CATEGORY;
    }

    @Override
    public Component getTitle()
    {
        return Component.literal("Metal Forming");
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
    public void setRecipe(IRecipeLayoutBuilder builder, MetalFormerRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 1).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 94, 1).addItemStack(new ItemStack(recipe.getResultItem(null).getItem(), recipe.getCount()));
    }

    @Override
    public void draw(MetalFormerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        this.progressAnimated.draw(graphics, 61, 0);
        this.energyAnimated.draw(graphics, 134, 1);
        if (MachineScreen.isMouseInArea(mouseX, mouseY, 61, 0, 27, 18))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fProcessing Time: §b" + recipe.getProcessingTime() + " t")), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);
        if (MachineScreen.isMouseInArea(mouseX, mouseY, 134, 1, 10, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fEnergy: §c" + recipe.getEnergyUsage() * recipe.getProcessingTime() + " " + ClientConfig.ENERGY_UNIT.get())), ItemStack.EMPTY.getTooltipImage(), (int) mouseX, (int) mouseY);

        if (MachineModes.getByName(recipe.getMachineMode()) == MachineModes.PRESSING)
            renderCorrectMode(platingMode, platingModeHover, graphics, (int) mouseX, (int) mouseY, recipe.getMachineMode());
        else if (MachineModes.getByName(recipe.getMachineMode()) == MachineModes.ROLLING)
            renderCorrectMode(rollingMode, rollingModeHover, graphics, (int) mouseX, (int) mouseY, recipe.getMachineMode());
        else if (MachineModes.getByName(recipe.getMachineMode()) == MachineModes.EXTRUDING)
            renderCorrectMode(extrudingMode, extrudingModeHover, graphics, (int) mouseX, (int) mouseY, recipe.getMachineMode());
    }

    private void renderCorrectMode(IDrawableStatic normal, IDrawableStatic hovered, GuiGraphics graphics, int mouseX, int mouseY, String mode)
    {
        if (MachineScreen.isMouseInArea(mouseX, mouseY, 0, -1, 20, 20))
        {
            hovered.draw(graphics, 0, -1);
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("§fMachine mode: §a" + mode.substring(0, 1).toUpperCase() + mode.substring(1))), ItemStack.EMPTY.getTooltipImage(), mouseX, mouseY);
        }
        else
            normal.draw(graphics, 0, -1);
    }
}
