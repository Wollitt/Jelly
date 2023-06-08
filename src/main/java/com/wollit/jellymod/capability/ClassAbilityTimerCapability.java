package com.wollit.jellymod.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class ClassAbilityTimerCapability {
    private int abilityTimer = 0;
    private final String ABILITY_TIMER_TAG = "class_ability_timer";

    public ClassAbilityTimerCapability() {}

    public int getAbilityTimer() {
        return abilityTimer;
    }

    public void setAbilityTimer(int abilityTimer) {
        this.abilityTimer = Math.max(abilityTimer, 0);
    }

    public void copyFrom(ClassAbilityTimerCapability timerCapability) {
        this.abilityTimer = timerCapability.getAbilityTimer();
    }

    public void saveNbtData(CompoundTag nbt) {
        nbt.putInt(ABILITY_TIMER_TAG, abilityTimer);
    }

    public void loadNbtData(CompoundTag nbt) {
        abilityTimer = nbt.getInt(ABILITY_TIMER_TAG);
    }
}
