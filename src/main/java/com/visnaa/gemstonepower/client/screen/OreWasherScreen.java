package com.visnaa.gemstonepower.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.menu.machine.OreWasherMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.KeybindContents;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.joml.Vector2i;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class OreWasherScreen extends MachineScreen<OreWasherMenu>
{
    public OreWasherScreen(OreWasherMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/ore_washer_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(75, 38),
                        new Vector2i(176, 16),
                        new Vector2i(19, 15))));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderFluid(graphics, mouseX, mouseY);
    }
}
