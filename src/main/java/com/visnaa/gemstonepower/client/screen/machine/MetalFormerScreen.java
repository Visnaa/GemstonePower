package com.visnaa.gemstonepower.client.screen.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.MetalFormerMenu;
import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.MachineModeSyncC2S;
import com.visnaa.gemstonepower.util.MachineUtil.MachineModes;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class MetalFormerScreen extends MachineScreen<MetalFormerMenu>
{
    private ImageButton platingButton, rollingButton, extrudingButton;

    public MetalFormerScreen(MetalFormerMenu menu, Inventory inventory, Component name)
    {
        super(menu, new ScreenData(inventory, name, GemstonePower.getId("textures/gui/metal_former_gui.png"),
                new ScreenData.ProgressBarData(
                        new Vector2i(76, 37),
                        new Vector2i(176, 16),
                        new Vector2i(27, 18))));
    }

    @Override
    public void init()
    {
        super.init();
        platingButton = new ImageButton(leftPos + 14, topPos + 36, 20, 20, 176, 34, 20, data.texture(), button -> {
            menu.setMachineMode(hasShiftDown() ? MachineModes.EXTRUDING.getMode() : MachineModes.ROLLING.getMode());
            ModPackets.sendToServer(new MachineModeSyncC2S(hasShiftDown() ? MachineModes.EXTRUDING.getMode() : MachineModes.ROLLING.getMode(), menu.getBlockPos()));
            platingButton.visible = false;
            rollingButton.visible = !hasShiftDown();
            extrudingButton.visible = hasShiftDown();
        });

        rollingButton = new ImageButton(leftPos + 14, topPos + 36, 20, 20, 196, 34, 20, data.texture(), button -> {
            menu.setMachineMode(hasShiftDown() ? MachineModes.PRESSING.getMode() : MachineModes.EXTRUDING.getMode());
            ModPackets.sendToServer(new MachineModeSyncC2S(hasShiftDown() ? MachineModes.PRESSING.getMode() : MachineModes.EXTRUDING.getMode(), menu.getBlockPos()));
            rollingButton.visible = false;
            extrudingButton.visible = !hasShiftDown();
            platingButton.visible = hasShiftDown();
        });

        extrudingButton = new ImageButton(leftPos + 14, topPos + 36, 20, 20, 216, 34, 20, data.texture(), button -> {
            menu.setMachineMode(hasShiftDown() ? MachineModes.ROLLING.getMode() : MachineModes.PRESSING.getMode());
            ModPackets.sendToServer(new MachineModeSyncC2S(hasShiftDown() ? MachineModes.ROLLING.getMode() : MachineModes.PRESSING.getMode(), menu.getBlockPos()));
            extrudingButton.visible = false;
            platingButton.visible = !hasShiftDown();
            rollingButton.visible = hasShiftDown();
        });

        platingButton.visible = menu.getMachineMode() == 0;
        rollingButton.visible = menu.getMachineMode() == 1;
        extrudingButton.visible = menu.getMachineMode() == 2;

        addRenderableWidget(platingButton);
        addRenderableWidget(rollingButton);
        addRenderableWidget(extrudingButton);
    }
}
