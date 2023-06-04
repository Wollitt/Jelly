package com.wollit.jellymod.capability.classes.range.sniper;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import com.wollit.jellymod.capability.classes.range.ArcherCapabilityProvider;
import org.jetbrains.annotations.NotNull;

public class SniperClassProvider extends ArcherCapabilityProvider {

    @Override
    public @NotNull AbstractClassCapability createClass() {
        if (getPlayerClass() == null) {
            setPlayerClass(new SniperClassCapability());
        }

        return this.getPlayerClass();
    }
}
