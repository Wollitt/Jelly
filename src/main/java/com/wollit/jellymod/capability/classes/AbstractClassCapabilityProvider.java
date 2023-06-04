package com.wollit.jellymod.capability.classes;

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

public abstract class AbstractClassCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<AbstractClassCapability> CLASS = CapabilityManager.get(new CapabilityToken<AbstractClassCapability>() {});

    private AbstractClassCapability playerClass = null;
    private final LazyOptional<AbstractClassCapability> optional = LazyOptional.of(this::createClass);

    public abstract @NotNull AbstractClassCapability createClass();

    protected AbstractClassCapability getPlayerClass() {
        return playerClass;
    }

    protected void setPlayerClass(AbstractClassCapability playerClass) {
        this.playerClass = playerClass;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CLASS) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createClass().saveNbtData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createClass().loadNbtData(nbt);
    }
}
