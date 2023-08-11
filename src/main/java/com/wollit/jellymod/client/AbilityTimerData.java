package com.wollit.jellymod.client;

public class AbilityTimerData {
    private static int abilityTimer;

    public static void setAbilityTimer(int abilityTimer) {
        AbilityTimerData.abilityTimer = abilityTimer;
    }

    public static int getAbilityTimer() {
        return abilityTimer;
    }
}