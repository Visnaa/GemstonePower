package com.visnaa.gemstonepower.client.screen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.menu.machine.ElectricFurnaceMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class ElectricFurnaceScreen extends MachineScreen<ElectricFurnaceMenu>
{
    public ElectricFurnaceScreen(ElectricFurnaceMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/electric_furnace_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(77, 38),
                        new Vector2i(176, 16),
                        new Vector2i(23, 16))));
    }
}
