package org.spoorn.climbladdersfast.option;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.spoorn.climbladdersfast.config.ModConfig;

public class KeyBindings {

    public static final String TOGGLE_LANG_KEY = "key.climbladdersfast.toggle";
    public static final String TOGGLE_CATEGORY_LANG_KEY = "category.climbladdersfast.options";
    public static final String TOGGLE_ENABLED_LANG = "climbladdersfast.toggle.enabled";
    public static final String TOGGLE_DISABLED_LANG = "climbladdersfast.toggle.disabled";
    public static KeyBinding toggle = new KeyBinding(
        TOGGLE_LANG_KEY, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_BACKSLASH, TOGGLE_CATEGORY_LANG_KEY
    );
    
    // Handle toggle button press
    public static void handleToggle(PlayerEntity player) {
        if (ModConfig.get().enabled) {
            player.sendMessage(Text.translatable(TOGGLE_DISABLED_LANG), false);
            ModConfig.get().enabled = false;
        } else {
            player.sendMessage(Text.translatable(TOGGLE_ENABLED_LANG), false);
            ModConfig.get().enabled = true;
        }
    }
}
