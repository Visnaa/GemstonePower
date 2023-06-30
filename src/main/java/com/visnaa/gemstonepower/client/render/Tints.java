package com.visnaa.gemstonepower.client.render;

import java.util.ArrayList;
import java.util.List;

public enum Tints
{
    EMPTY(0xFFFFFF),

    COAL(0x333333),

    IRON(0xC9C9C9),
    GOLD(0xFFD663),
    COPPER(0xB4684D),
    ALUMINUM(0xB6D4E0),
    TIN(0xF0F0F0),
    BRONZE(0xD17C15),
    SILVER(0xC1E8E6),
    ELECTRUM(0xEDB42D),
    NICKEL(0xD5E39F),
    INVAR(0xD4D9C1),
    CONSTANTAN(0xFF955C),
    PLATINUM(0x73FDFF),
    STEEL(0x95A6C2),
    LITHIUM(0xFCE3EB),
    MAGNESIUM(0xFFCCF4),
    URANIUM(0x47DE65),
    LEAD(0xB4ADFF),
    ZINC(0xFFE0B8),

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
