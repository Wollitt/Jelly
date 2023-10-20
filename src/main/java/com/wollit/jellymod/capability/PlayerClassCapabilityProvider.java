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

public class PlayerClassCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerClassCapability> CLASS = CapabilityManager.get(new CapabilityToken<PlayerClassCapability>() {});

    private PlayerClassCapability playerClass = null;
    private final LazyOptional<PlayerClassCapability> optional = LazyOptional.of(this::createClass);

    private PlayerClassCapability createClass() {
        if (this.playerClass == null) {
            this.playerClass = new PlayerClassCapability();
        }

        return this.playerClass;
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
