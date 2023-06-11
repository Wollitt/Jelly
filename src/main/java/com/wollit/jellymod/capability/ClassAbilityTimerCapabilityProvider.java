package com.wollit.jellymod.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClassAbilityTimerCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ClassAbilityTimerCapability> ABILITY_TIMER = CapabilityManager.get(new CapabilityToken<ClassAbilityTimerCapability>() {});


    private ClassAbilityTimerCapability abilityTimer = null;
    private final LazyOptional<ClassAbilityTimerCapability> optional = LazyOptional.of(this::createAbilityTimer);

    public ClassAbilityTimerCapability createAbilityTimer() {
        if (getAbilityTimer() == null) {
            setAbilityTimer(new ClassAbilityTimerCapability());
        }

        return this.getAbilityTimer();
    }

    private ClassAbilityTimerCapability getAbilityTimer() {
        return abilityTimer;
    }

    private void setAbilityTimer(ClassAbilityTimerCapability abilityTimer) {
        this.abilityTimer = abilityTimer;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ABILITY_TIMER) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createAbilityTimer().saveNbtData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createAbilityTimer().loadNbtData(nbt);
    }
}
