package com.visnaa.gemstonepower.client.render;

import java.util.ArrayList;
import java.util.List;

public enum Tints
{
    EMPTY(0xFFFFFF),

    COAL(0x333333),

    IRON(0xFDFDFD),
    GOLD(0xFFCF4A),
    COPPER(0xF58862),
    ALUMINUM(0xB6D4E0),
    TIN(0xF0F0F0),
    BRONZE(0xFF8A30),
    SILVER(0xC1E8E6),
    ELECTRUM(0xFFD980),
    NICKEL(0xD5E39F),
    INVAR(0xD4D9C1),
    CONSTANTAN(0xFFA97A),
    PLATINUM(0xC4FEFF),
    STEEL(0xCFE1FF),
    LITHIUM(0xFFDBDB),
    MAGNESIUM(0xFFE6FA),
    URANIUM(0x76BF67),
    LEAD(0xB5B2D9),
    ZINC(0xFFEDD6),

    RUBY(0xFF4653),
    SAPPHIRE(0x2342C8),
    AQUAMARINE(0x3AF6FF),
    JADE(0x67DEA8),
    OPAL(0x141414),
    YELLOW_DIAMOND(0xE5DB6B),
    AMBER(0xEF9D26),
    TOPAZ(0x3EBAFF),
    BERYLLIUM(0x0EE8B8),
    BIXBIT(0xF80064),
    MALACHITE(0x00E886),
    ONYX(0x050505),
    PERIDOT(0x82F938),
    MOON_STONE(0xC1FDFF),
    SUN_STONE(0xFFE3A5),
    CITRINE(0xEFB84E),
    DOLOMITE(0xF6D0EC),
    TANZANITE(0x6E1FDC);

    private final int color;

    Tints(int colour)
    {
        this.color = colour;
    }

    public int getColor()
    {
        return color;
    }

    public static final List<Tintable> TINTED_ITEMS = new ArrayList<>();
    public static final List<Tintable> TINTED_BLOCKS = new ArrayList<>();
}
