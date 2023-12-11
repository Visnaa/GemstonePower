package com.visnaa.gemstonepower.client.screen.widget;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.machine.MachineScreen;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class MachineConfigButton extends ImageButton
{
    private MachineUtil.MachineConfigs config;
    private OnPress onPress;

    public MachineConfigButton(int posX, int posY, int width, int height, MachineUtil.MachineConfigs defaultValue, OnPress onPress)
    {
        super(posX, posY, width, height, null, MachineConfigButton::cycle);
        this.config = defaultValue;
        this.onPress = onPress;
    }

    private static void cycle(Button button)
    {
        if (!(button instanceof MachineConfigButton configButton))
            return;

        configButton.config = switch (configButton.config)
        {
            case NONE -> MachineUtil.MachineConfigs.INPUT;
            case INPUT -> MachineUtil.MachineConfigs.OUTPUT;
            case OUTPUT -> MachineUtil.MachineConfigs.ALL;
            case ALL -> MachineUtil.MachineConfigs.NONE;
        };
        configButton.onPress.onPress(configButton);
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        if (!(Minecraft.getInstance().getTextureManager().getTexture(InventoryMenu.BLOCK_ATLAS) instanceof TextureAtlas))
            return;
        if (config != MachineUtil.MachineConfigs.NONE)
            graphics.blit(getX(), getY(), 0, 16, 16, getSprite());

        String color = config == MachineUtil.MachineConfigs.INPUT ? "b" : config == MachineUtil.MachineConfigs.OUTPUT ? "6" : config == MachineUtil.MachineConfigs.ALL ? "a" : "c";
        if (MachineScreen.isMouseInArea(mouseX, mouseY, getX(), getY(), 16, 16))
            graphics.renderTooltip(Minecraft.getInstance().font, List.of(Component.literal("Configuration: §" + color + config.getSerializedName().substring(0, 1).toUpperCase() + config.getSerializedName().substring(1))), ItemStack.EMPTY.getTooltipImage(), mouseX, mouseY);
    }

    private TextureAtlasSprite getSprite()
    {
        TextureAtlas atlas = (TextureAtlas) Minecraft.getInstance().getTextureManager().getTexture(InventoryMenu.BLOCK_ATLAS);
        return switch (config)
        {
            case INPUT -> atlas.getSprite(GemstonePower.getId("block/config_input"));
            case OUTPUT -> atlas.getSprite(GemstonePower.getId("block/config_output"));
            default -> atlas.getSprite(GemstonePower.getId("block/config_all"));
        };
    }

    public MachineUtil.MachineConfigs getConfig()
    {
        return config;
    }
}
