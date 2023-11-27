package com.visnaa.gemstonepower.client.screen.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.CrystalGrowerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class CrystalGrowerScreen extends MachineScreen<CrystalGrowerMenu>
{
    public CrystalGrowerScreen(CrystalGrowerMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/crystal_grower_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(74, 39),
                        new Vector2i(176, 16),
                        new Vector2i(31, 11))));
    }
}
