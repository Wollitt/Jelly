package com.wollit.jellymod.capability.classes.range;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import com.wollit.jellymod.capability.classes.AbstractClassCapabilityProvider;
import org.jetbrains.annotations.NotNull;

public class ArcherCapabilityProvider extends AbstractClassCapabilityProvider {

    @Override
    public @NotNull AbstractClassCapability createClass() {
        if (getPlayerClass() == null) {
            setPlayerClass(new ArcherClassCapability());
        }

        return this.getPlayerClass();
    }
}
