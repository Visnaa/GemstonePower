package com.visnaa.gemstonepower.client.render;

public interface Tintable
{
    default int getColor()
    {
        return Tints.NONE.getColor();
    }
}
