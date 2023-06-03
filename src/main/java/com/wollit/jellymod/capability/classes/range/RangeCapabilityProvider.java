package com.wollit.jellymod.capability.classes.range;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import com.wollit.jellymod.capability.classes.AbstractClassCapabilityProvider;
import org.jetbrains.annotations.NotNull;

public class RangeCapabilityProvider extends AbstractClassCapabilityProvider {

    @Override
    public @NotNull AbstractClassCapability createClass() {
        if (getPlayerClass() == null) {
            setPlayerClass(new RangeClassCapability());
        }

        return this.getPlayerClass();
    }
}
