package com.wollit.jellymod.capability.classes.archer.sniper;

import com.wollit.jellymod.capability.classes.archer.ArcherClassCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class SniperClassCapability extends ArcherClassCapability {
    private Float movementSpeedMultiplayer = 1.5F;
    private final String SNIPER_MOVEMENT_SPEED_TAG = "sniper_movement_speed_multiplayer";

    public SniperClassCapability() {
        super(2.0F);
    }

    public Float getMovementSpeedMultiplayer() {
        return movementSpeedMultiplayer;
    }

    public void setMovementSpeedMultiplayer(Float movementSpeedMultiplayer) {
        this.movementSpeedMultiplayer = movementSpeedMultiplayer;
    }

    @Override
    public <T> void copyFrom(T object) {
        SniperClassCapability playerClass = (SniperClassCapability) object;
        this.setRangeDmg(playerClass.getRangeDmg());
        this.setMovementSpeedMultiplayer(playerClass.getMovementSpeedMultiplayer());
    }

    @Override
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(getRangeDamageTag(), getRangeDmg());
        nbt.putFloat(SNIPER_MOVEMENT_SPEED_TAG, movementSpeedMultiplayer);
    }

    @Override
    public void loadNbtData(CompoundTag nbt) {
        setRangeDmg(nbt.getFloat(getRangeDamageTag()));
        setMovementSpeedMultiplayer(nbt.getFloat(SNIPER_MOVEMENT_SPEED_TAG));
    }
}
