package com.visnaa.gemstonepower.client.screen.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.GemstoneManipulatorMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class GemstoneManipulatorScreen extends MachineScreen<GemstoneManipulatorMenu>
{
    public GemstoneManipulatorScreen(GemstoneManipulatorMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/gemstone_manipulator_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(78, 39),
                        new Vector2i(176, 16),
                        new Vector2i(20, 14))));
    }
}
