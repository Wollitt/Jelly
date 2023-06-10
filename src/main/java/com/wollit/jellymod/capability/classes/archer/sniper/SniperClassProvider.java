package com.wollit.jellymod.capability.classes.archer.sniper;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import com.wollit.jellymod.capability.classes.archer.ArcherCapabilityProvider;
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
