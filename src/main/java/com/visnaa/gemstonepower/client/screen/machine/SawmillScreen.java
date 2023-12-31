package com.visnaa.gemstonepower.client.screen.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.SawmillMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class SawmillScreen extends MachineScreen<SawmillMenu>
{
    public SawmillScreen(SawmillMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/sawmill_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(75, 39),
                        new Vector2i(176, 16),
                        new Vector2i(27, 13))));
    }
}
