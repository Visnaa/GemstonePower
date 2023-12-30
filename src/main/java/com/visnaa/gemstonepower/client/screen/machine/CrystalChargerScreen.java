package com.visnaa.gemstonepower.client.screen.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.CrystalChargerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class CrystalChargerScreen extends MachineScreen<CrystalChargerMenu>
{
    public CrystalChargerScreen(CrystalChargerMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/crystal_charger_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(81, 37),
                        new Vector2i(179, 16),
                        new Vector2i(15, 16))));
    }
}
