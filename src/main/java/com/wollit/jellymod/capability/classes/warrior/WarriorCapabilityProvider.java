package com.wollit.jellymod.capability.classes.warrior;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import com.wollit.jellymod.capability.classes.AbstractClassCapabilityProvider;
import org.jetbrains.annotations.NotNull;

public class WarriorCapabilityProvider extends AbstractClassCapabilityProvider {

    @Override
    public @NotNull AbstractClassCapability createClass() {
        if (getPlayerClass() == null) {
            setPlayerClass(new WarriorClassCapability());
        }

        return this.getPlayerClass();
    }
}
