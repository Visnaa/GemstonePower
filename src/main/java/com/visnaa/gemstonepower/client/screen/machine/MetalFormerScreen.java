package com.visnaa.gemstonepower.client.screen.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.MetalFormerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class MetalFormerScreen extends MachineScreen<MetalFormerMenu>
{
    public MetalFormerScreen(MetalFormerMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/metal_former_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(76, 37),
                        new Vector2i(176, 16),
                        new Vector2i(27, 18))));
    }
}
