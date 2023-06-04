package com.wollit.jellymod.capability.classes.range.sniper;

import com.wollit.jellymod.capability.classes.range.ArcherClassCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class SniperClassCapability extends ArcherClassCapability {
    private Float movementSpeedMultiplayer = 1.5F;
    private final String tag = "sniper_movement_speed_multiplayer";

    public SniperClassCapability() {
        super(1.4F, "sniper_damage_multiplayer");
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
        nbt.putFloat(getTag(), getRangeDmg());
        nbt.putFloat(tag, movementSpeedMultiplayer);
    }

    @Override
    public void loadNbtData(CompoundTag nbt) {
        setRangeDmg(nbt.getFloat(getTag()));
        setMovementSpeedMultiplayer(nbt.getFloat(tag));
    }
}
