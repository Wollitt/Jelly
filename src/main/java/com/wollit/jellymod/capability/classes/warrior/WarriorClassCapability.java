package com.wollit.jellymod.capability.classes.warrior;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class WarriorClassCapability extends AbstractClassCapability {
    private Float swordDamageMultiplayer = 1.25F;
    private final String tag = "sword_damage_multiplayer";

    public Float getSwordDmg() {
        return swordDamageMultiplayer;
    }

    @Override
    public <T> void copyFrom(T object) {
        this.swordDamageMultiplayer = (Float) object;
    }

    @Override
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(tag, swordDamageMultiplayer);
    }

    @Override
    public void loadNbtData(CompoundTag nbt) {
        swordDamageMultiplayer = nbt.getFloat(tag);
    }
}
