package com.visnaa.gemstonepower.client.screen.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.AlloySmelterMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class AlloySmelterScreen extends MachineScreen<AlloySmelterMenu>
{
    public AlloySmelterScreen(AlloySmelterMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/alloy_smelter_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(72, 34),
                        new Vector2i(176, 16),
                        new Vector2i(31, 24))));
    }
}
