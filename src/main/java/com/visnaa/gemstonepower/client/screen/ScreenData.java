package com.visnaa.gemstonepower.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public record ScreenData(Inventory inventory, Component name, ResourceLocation texture, ProgressBarData progressBar)
{
    protected record ProgressBarData(Vector2i pos, Vector2i overlayPos, Vector2i dimensions) {}
}
