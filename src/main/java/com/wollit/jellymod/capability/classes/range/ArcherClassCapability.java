package com.wollit.jellymod.capability.classes.range;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class ArcherClassCapability extends AbstractClassCapability {
    private Float rangeDamageMultiplayer = 1.25F;

    public ArcherClassCapability() {
        this.setTag("range_damage_multiplayer");
    }

    public ArcherClassCapability(Float rangeDamageMultiplayer, String tag) {
        this.rangeDamageMultiplayer = rangeDamageMultiplayer;
        this.setTag(tag);
    }

    public Float getRangeDmg() {
        return rangeDamageMultiplayer;
    }

    public void setRangeDmg(Float rangeDamageMultiplayer) {
        this.rangeDamageMultiplayer = rangeDamageMultiplayer;
    }

    @Override
    public <T> void copyFrom(T object) {
        ArcherClassCapability playerClass = (ArcherClassCapability) object;
        this.setRangeDmg(playerClass.getRangeDmg());
    }

    @Override
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(getTag(), rangeDamageMultiplayer);
    }

    @Override
    public void loadNbtData(CompoundTag nbt) {
        rangeDamageMultiplayer = nbt.getFloat(getTag());
    }
}
