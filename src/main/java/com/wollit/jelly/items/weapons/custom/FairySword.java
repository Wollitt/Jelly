package com.wollit.jelly.items.weapons.custom;

import com.wollit.jelly.items.weapons.AbstractMagicSword;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FairySword extends AbstractMagicSword {
    public FairySword(Tier toolMaterial, Properties properties) {
        super(toolMaterial, 3, -2.4F, properties);
    }

}
