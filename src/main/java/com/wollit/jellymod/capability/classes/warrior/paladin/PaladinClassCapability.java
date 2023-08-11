package com.wollit.jellymod.capability.classes.warrior.paladin;

import com.wollit.jellymod.capability.classes.warrior.WarriorClassCapability;
import com.wollit.jellymod.network.ModNetwork;
import com.wollit.jellymod.network.PacketUsePaladinClassAbilityC2S;

public class PaladinClassCapability extends WarriorClassCapability {
    public PaladinClassCapability() {
        super(2.0F, "Paladin");
    }

    @Override
    public <T> void useClassAbility(T object) {
        ModNetwork.sendToServer(new PacketUsePaladinClassAbilityC2S());
    }
}
