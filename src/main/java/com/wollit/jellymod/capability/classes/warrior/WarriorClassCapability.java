package com.wollit.jellymod.capability.classes.warrior;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class WarriorClassCapability extends AbstractClassCapability {
    private Float meleeDamageMultiplayer = 1.1F;
    private final String WARRIOR_DAMAGE_MULTIPLAYER_TAG = "warrior_damage_multiplayer";

    public WarriorClassCapability() {
        this.name = "Warrior";
    }

    public WarriorClassCapability(Float swordDamageMultiplayer, String name) {
        this.meleeDamageMultiplayer = swordDamageMultiplayer;
        this.name = name;
    }

    public Float getMelleDmg() {
        return meleeDamageMultiplayer;
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
        this.meleeDamageMultiplayer = (Float) object;
    }

    @Override
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(WARRIOR_DAMAGE_MULTIPLAYER_TAG, meleeDamageMultiplayer);
    }

    @Override
    public void loadNbtData(CompoundTag nbt) {
        meleeDamageMultiplayer = nbt.getFloat(WARRIOR_DAMAGE_MULTIPLAYER_TAG);
    }
}
