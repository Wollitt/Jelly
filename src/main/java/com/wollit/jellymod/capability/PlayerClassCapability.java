package com.wollit.jellymod.capability;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@Getter
@Setter
@AutoRegisterCapability
public class PlayerClassCapability {

    public String className = "";
    public Float damageMultiplayer = 1.1F;

    public void setClassName(String className) {
        this.className = className;
    }

    public void copyFrom(PlayerClassCapability source) {
        this.className = source.className;
    }

    public void saveNbtData(CompoundTag nbt) {
        nbt.putString("CLASS_NAME", className);
    }

    public void loadNbtData(CompoundTag nbt) {
        this.className = nbt.getString("CLASS_NAME");
    }
}
