package com.wollit.jellymod.capability.classes;

import net.minecraft.nbt.CompoundTag;

public abstract class AbstractClassCapability {

    public abstract  <T> void copyFrom(T object);

    public abstract void saveNbtData(CompoundTag nbt);

    public abstract void loadNbtData(CompoundTag nbt);
}
