package org.spoorn.climbladdersfast.option;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;
import org.spoorn.climbladdersfast.config.ModConfig;

public class KeyBindings {

    public static final String TOGGLE_LANG_KEY = "key.climbladdersfast.toggle";
    public static final String TOGGLE_CATEGORY_LANG_KEY = "category.climbladdersfast.options";
    public static final String TOGGLE_ENABLED_LANG = "climbladdersfast.toggle.enabled";
    public static final String TOGGLE_DISABLED_LANG = "climbladdersfast.toggle.disabled";
    public static KeyMapping toggle = new KeyMapping(
        TOGGLE_LANG_KEY, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_BACKSLASH, TOGGLE_CATEGORY_LANG_KEY
    );
    
    // Handle toggle button press
    public static void handleToggle(Player player) {
        if (ModConfig.get().enabled) {
            player.displayClientMessage(Component.translatable(TOGGLE_DISABLED_LANG), false);
            ModConfig.get().enabled = false;
        } else {
            player.displayClientMessage(Component.translatable(TOGGLE_ENABLED_LANG), false);
            ModConfig.get().enabled = true;
        }
    }
}
