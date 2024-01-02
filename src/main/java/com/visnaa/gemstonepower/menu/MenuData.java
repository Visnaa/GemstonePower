package com.visnaa.gemstonepower.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

import java.util.HashMap;

public record MenuData(int windowId, Inventory inventory, Container container, int containerSize, HashMap<Integer, Vector2i> slots)
{
    public static HashMap<Integer, Vector2i> createSlots(Vector2i... slots)
    {
        HashMap<Integer, Vector2i> map = new HashMap<>();
        for (int id = 0; id < slots.length; id++)
        {
            map.put(id, slots[id]);
        }
        return map;
    }

    public static HashMap<Integer, Vector2i> createSlots(int slots)
    {
        HashMap<Integer, Vector2i> map = new HashMap<>();
        for (int id = 0; id < slots; id++)
        {
            map.put(id, new Vector2i());
        }
        return map;
    }
}
