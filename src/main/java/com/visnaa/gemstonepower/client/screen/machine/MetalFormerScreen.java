package com.visnaa.gemstonepower.client.screen.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.ScreenData;
import com.visnaa.gemstonepower.menu.machine.MetalFormerMenu;
import com.visnaa.gemstonepower.network.packet.MachineModeSyncC2S;
import com.visnaa.gemstonepower.util.MachineUtil.MachineModes;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Vector2i;

@OnlyIn(Dist.CLIENT)
public class MetalFormerScreen extends MachineScreen<MetalFormerMenu>
{
    protected static final WidgetSprites PRESSING_SPRITES = new WidgetSprites(GemstonePower.getId("metal_former/pressing"), GemstonePower.getId("metal_former/pressing_highlighted"));
    protected static final WidgetSprites ROLLING_SPRITES = new WidgetSprites(GemstonePower.getId("metal_former/rolling"), GemstonePower.getId("metal_former/rolling_highlighted"));
    protected static final WidgetSprites EXTRUDING_SPRITES = new WidgetSprites(GemstonePower.getId("metal_former/extruding"), GemstonePower.getId("metal_former/extruding_highlighted"));
    private ImageButton pressingButton, rollingButton, extrudingButton;

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
        pressingButton = new ImageButton(leftPos + 14, topPos + 36, 20, 20, PRESSING_SPRITES, button -> {
            menu.setMachineMode(hasShiftDown() ? MachineModes.EXTRUDING.getMode() : MachineModes.ROLLING.getMode());
            PacketDistributor.SERVER.noArg().send(new MachineModeSyncC2S(hasShiftDown() ? MachineModes.EXTRUDING.getMode() : MachineModes.ROLLING.getMode(), menu.getBlockPos()));
            pressingButton.visible = false;
            rollingButton.visible = !hasShiftDown();
            extrudingButton.visible = hasShiftDown();
        });

        rollingButton = new ImageButton(leftPos + 14, topPos + 36, 20, 20, ROLLING_SPRITES, button -> {
            menu.setMachineMode(hasShiftDown() ? MachineModes.PRESSING.getMode() : MachineModes.EXTRUDING.getMode());
            PacketDistributor.SERVER.noArg().send(new MachineModeSyncC2S(hasShiftDown() ? MachineModes.PRESSING.getMode() : MachineModes.EXTRUDING.getMode(), menu.getBlockPos()));
            rollingButton.visible = false;
            extrudingButton.visible = !hasShiftDown();
            pressingButton.visible = hasShiftDown();
        });

        extrudingButton = new ImageButton(leftPos + 14, topPos + 36, 20, 20, EXTRUDING_SPRITES, button -> {
            menu.setMachineMode(hasShiftDown() ? MachineModes.ROLLING.getMode() : MachineModes.PRESSING.getMode());
            PacketDistributor.SERVER.noArg().send(new MachineModeSyncC2S(hasShiftDown() ? MachineModes.ROLLING.getMode() : MachineModes.PRESSING.getMode(), menu.getBlockPos()));
            extrudingButton.visible = false;
            pressingButton.visible = !hasShiftDown();
            rollingButton.visible = hasShiftDown();
        });

        pressingButton.visible = menu.getMachineMode() == 0;
        rollingButton.visible = menu.getMachineMode() == 1;
        extrudingButton.visible = menu.getMachineMode() == 2;

        addRenderableWidget(pressingButton);
        addRenderableWidget(rollingButton);
        addRenderableWidget(extrudingButton);
    }
}
