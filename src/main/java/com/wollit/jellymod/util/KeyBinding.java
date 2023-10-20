package com.wollit.jellymod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY = "key.category.jellymod.jelly";
    public static final String KEY_USE_CLASS_ABILITY = "key.jellymod.use_ability";
    public static final String KEY_CHOOSE_CLASS = "key.jellymod.choose_class";
    public static final String KEY_TEST = "key.jellymod.test";

    public static final KeyMapping USE_CLASS_ABILITY_KEY = new KeyMapping(KEY_USE_CLASS_ABILITY, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_7, KEY_CATEGORY);
    public static final KeyMapping CHOOSE_CLASS_KEY = new KeyMapping(KEY_CHOOSE_CLASS, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_8, KEY_CATEGORY);
    public static final KeyMapping TEST_KEY = new KeyMapping(KEY_TEST, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_9, KEY_CATEGORY);

}
