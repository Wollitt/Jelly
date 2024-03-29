package com.wollit.jellymod.capability.classes.archer;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class ArcherClassCapability extends AbstractClassCapability {
    private Float rangeDamageMultiplayer = 1.1F;
    private final String ARCHER_DAMAGE_MULTIPLAYER_TAG = "archer_damage_multiplayer";

    public ArcherClassCapability() {
        this.name = "Archer";
    }

    public Float getRangeDmg() {
        return rangeDamageMultiplayer;
    }

    public void setRangeDmg(Float rangeDamageMultiplayer) {
        this.rangeDamageMultiplayer = rangeDamageMultiplayer;
    }

    @Override
    public String getClassName() {
        return name;
    }

    @Override
    public <T> void useClassAbility(T object) {

    }

    @Override
    public <T> void copyFrom(T object) {
        ArcherClassCapability playerClass = (ArcherClassCapability) object;
        this.setRangeDmg(playerClass.getRangeDmg());
    }

    @Override
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(ARCHER_DAMAGE_MULTIPLAYER_TAG, rangeDamageMultiplayer);
    }

    @Override
    public void loadNbtData(CompoundTag nbt) {
        rangeDamageMultiplayer = nbt.getFloat(ARCHER_DAMAGE_MULTIPLAYER_TAG);
    }
}
