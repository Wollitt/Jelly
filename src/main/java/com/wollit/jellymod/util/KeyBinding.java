package com.wollit.jellymod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY = "key.category.jellymod.jelly";
    public static final String KEY_USE_CLASS_ABILITY = "key.jellymod.use_ability";

    public static final KeyMapping USE_CLASS_ABILITY_KEY = new KeyMapping(KEY_USE_CLASS_ABILITY, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY);

}
