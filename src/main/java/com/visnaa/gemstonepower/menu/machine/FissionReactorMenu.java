package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.block.entity.FissionReactorBE;
import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.menu.MenuData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class FissionReactorMenu extends MachineMenu
{
    public FissionReactorMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        super(ModMenus.FISSION_REACTOR.get(), ModRecipes.FISSION_REACTOR_RECIPE, new MenuData(id, inventory, new SimpleContainer(2), 2, MenuData.createSlots(
                        new Vector2i(53, 38),
                        new Vector2i(109, 38))),
                data == null ? null : data.readBlockPos());
    }

    public int getHeat()
    {
        if (blockEntity instanceof FissionReactorBE reactor)
            return reactor.getHeat();
        return 0;
    }

    public int getMaxHeat()
    {
        if (blockEntity instanceof FissionReactorBE reactor)
            return reactor.getMaxHeat();
        return 0;
    }

    public boolean isActivated()
    {
        if (blockEntity instanceof FissionReactorBE reactor)
            return reactor.isActivated();
        return false;
    }

    public int getHeatLevel()
    {
        float heat = getHeat();
        float maxHeat = getMaxHeat();

        int height = (int) (-16 * (heat / maxHeat) + 16);

        return maxHeat != 0 && heat != 0 && height <= 16 ? height : 16;
    }
}
