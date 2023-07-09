package com.visnaa.gemstonepower.util;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class StringProperty extends Property<String>
{
    private final ImmutableSet<String> values;

    protected StringProperty(String name, String... values)
    {
        super(name, String.class);
        Set<String> set = Sets.newHashSet();
        Collections.addAll(set, values);
        this.values = ImmutableSet.copyOf(set);
    }

    public Collection<String> getPossibleValues() {
        return this.values;
    }

    public static StringProperty create(String name, String... values) {
        return new StringProperty(name, values);
    }

    public Optional<String> getValue(String name)
    {
        return Optional.of(name);
    }

    public String getName(String name) {
        return name;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof StringProperty property && super.equals(object)) {
            return this.values.equals(property.values);
        } else {
            return false;
        }
    }
}
