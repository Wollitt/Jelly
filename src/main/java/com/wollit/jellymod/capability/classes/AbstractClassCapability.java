package com.wollit.jellymod.capability.classes;

import net.minecraft.nbt.CompoundTag;

public abstract class AbstractClassCapability {
    private String tag;

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public abstract  <T> void copyFrom(T object);

    public abstract void saveNbtData(CompoundTag nbt);

    public abstract void loadNbtData(CompoundTag nbt);
}
