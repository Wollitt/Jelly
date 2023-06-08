package com.wollit.jellymod.capability.classes.warrior.paladin;

import com.wollit.jellymod.capability.classes.AbstractClassCapability;
import com.wollit.jellymod.capability.classes.warrior.WarriorCapabilityProvider;
import com.wollit.jellymod.capability.classes.warrior.WarriorClassCapability;
import org.jetbrains.annotations.NotNull;

public class PaladinCapabilityProvider extends WarriorCapabilityProvider {

    @Override
    public @NotNull AbstractClassCapability createClass() {
        if (getPlayerClass() == null) {
            setPlayerClass(new PaladinClassCapability());
        }

        return this.getPlayerClass();
    }
}
