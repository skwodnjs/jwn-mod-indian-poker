package net.jwn.mod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.jwn.mod.Main;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_TUTORIAL = "key.category." + Main.MOD_ID + ".tutorial";
    public static final String KEY_OUT = "key." + Main.MOD_ID + ".out";

    public static final KeyMapping OUT_KEY = new KeyMapping(KEY_OUT, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_TUTORIAL);
}
