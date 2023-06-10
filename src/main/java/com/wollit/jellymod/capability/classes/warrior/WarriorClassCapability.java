package com.wollit.jellymod.capability.classes.warrior;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class WarriorClassCapability extends AbstractClassCapability {
    private Float melleDamageMultiplayer = 1.25F;
    private final String WARRIOR_DAMAGE_MULTIPLAYER_TAG = "warrior_damage_multiplayer";

    public WarriorClassCapability() {}

    public WarriorClassCapability(Float swordDamageMultiplayer) {
        this.melleDamageMultiplayer = swordDamageMultiplayer;
    }

    public String getMelleDamageTag() {
        return WARRIOR_DAMAGE_MULTIPLAYER_TAG;
    }

    public Float getMelleDmg() {
        return melleDamageMultiplayer;
    }

    @Override
    public <T> void useClassAbility(T object) {

    }

    @Override
    public <T> void copyFrom(T object) {
        this.melleDamageMultiplayer = (Float) object;
    }

    @Override
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(WARRIOR_DAMAGE_MULTIPLAYER_TAG, melleDamageMultiplayer);
    }

    @Override
    public void loadNbtData(CompoundTag nbt) {
        melleDamageMultiplayer = nbt.getFloat(WARRIOR_DAMAGE_MULTIPLAYER_TAG);
    }
}
