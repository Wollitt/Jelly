package com.wollit.jellymod.capability.classes.range;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class RangeClassCapability extends AbstractClassCapability {
    private Float rangeDamageMultiplayer = 1.25F;
    private final String tag = "range_damage_multiplayer";

    public Float getRangeDmg() {
        return rangeDamageMultiplayer;
    }

    @Override
    public <T> void copyFrom(T object) {
        this.rangeDamageMultiplayer = (Float) object;
    }

    @Override
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(tag, rangeDamageMultiplayer);
    }

    @Override
    public void loadNbtData(CompoundTag nbt) {
        rangeDamageMultiplayer = nbt.getFloat(tag);
    }
}
