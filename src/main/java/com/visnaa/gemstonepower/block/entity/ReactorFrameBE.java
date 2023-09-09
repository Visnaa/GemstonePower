package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.block.ReactorFrameBlock;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class ReactorFrameBE extends BlockEntity
{
    private Type type;
    private FissionReactorBE reactor;

    public ReactorFrameBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.REACTOR_FRAME.get(), pos, state);
        this.type = Type.FUEL;
    }

    public ReactorFrameBE(BlockPos pos, BlockState state, Type type)
    {
        this(pos, state);
        this.type = type;
    }

    public boolean isValid(Level level)
    {
        if (level == null || level.isClientSide())
            return false;

        for (Direction direction : Direction.values())
        {
            BlockState state = level.getBlockState(getBlockPos().relative(direction));
            if (!state.is(ModBlocks.FISSION_REACTOR.get()) && !state.is(ModBlocks.REACTOR_WALL.get()) && !(state.getBlock() instanceof ReactorFrameBlock))
                return false;
        }
        return true;
    }

    public Set<ReactorFrameBE> registerFrame(Set<ReactorFrameBE> frames, FissionReactorBE reactor, Level level)
    {
        if (level == null || level.isClientSide())
            return frames;

        this.reactor = reactor;
        frames.add(this);
        for (Direction direction : Direction.values())
        {
            BlockEntity b = level.getBlockEntity(getBlockPos().relative(direction));
            if (level.getBlockEntity(getBlockPos().relative(direction)) instanceof ReactorFrameBE frame && !frames.contains(frame))
                frame.registerFrame(frames, reactor, level);
            else if (level.getBlockEntity(getBlockPos().relative(direction)) instanceof ReactorWallBE wall)
                wall.setReactor(reactor);
        }
        return frames;
    }

    public void broken(Level level)
    {
        if (reactor != null)
            reactor.broken(level);
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.putString("Type", type == null ? Type.FUEL.getName() : type.getName());
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        type = Type.getByName(tag.getString("Type"));
    }

    public Type getFrameType()
    {
        return type == null ? Type.FUEL : type;
    }

    public enum Type
    {
        FUEL("fuel", 0),
        WATER("water", 10),
        SNOW("snow", 15),
        ICE("ice", 20),
        PACKED_ICE("packed_ice", 25),
        BLUE_ICE("blue_ice", 40),
        PRISMARINE("prismarine", 60);

        private String name;
        private int cooling;

        Type(String name, int cooling)
        {
            this.name = name;
            this.cooling = cooling;
        }

        public String getName()
        {
            return name;
        }

        public int getCooling()
        {
            return cooling;
        }

        public static Type getByName(String name)
        {
            for (Type type : values())
                if (type.getName().equals(name))
                    return type;
            return WATER;
        }
    }
}
