package net.jwn.mod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.jwn.mod.Main;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_TUTORIAL = "key.category." + Main.MOD_ID + ".tutorial";
    public static final String KEY_OUT = "key." + Main.MOD_ID + ".out";
    public static final String KEY_UP = "key." + Main.MOD_ID + ".up";
    public static final String KEY_BET = "key." + Main.MOD_ID + ".bet";
    public static final String KEY_DOWN = "key." + Main.MOD_ID + ".down";

    public static final KeyMapping OUT_KEY = new KeyMapping(KEY_OUT, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_TUTORIAL);
    public static final KeyMapping DOWN_KEY = new KeyMapping(KEY_DOWN, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_CATEGORY_TUTORIAL);
    public static final KeyMapping BET_KEY = new KeyMapping(KEY_BET, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, KEY_CATEGORY_TUTORIAL);
    public static final KeyMapping UP_KEY = new KeyMapping(KEY_UP, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, KEY_CATEGORY_TUTORIAL);
}
