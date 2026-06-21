package org.spoorn.climbladdersfast.option;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;
import org.spoorn.climbladdersfast.ClimbLaddersFast;
import org.spoorn.climbladdersfast.config.ModConfig;

public class KeyBindings {

    public static final String TOGGLE_LANG_KEY = "key.climbladdersfast.toggle";
    public static final String TOGGLE_CATEGORY_LANG_KEY = "key.category.climbladdersfast.options";
    public static final String TOGGLE_ENABLED_LANG = "climbladdersfast.toggle.enabled";
    public static final String TOGGLE_DISABLED_LANG = "climbladdersfast.toggle.disabled";
    public static final KeyMapping.Category TOGGLE_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(ClimbLaddersFast.MODID, "options"));
    public static KeyMapping toggle = new KeyMapping(
        TOGGLE_LANG_KEY, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_BACKSLASH, TOGGLE_CATEGORY
    );
    
    // Handle toggle button press
    public static void handleToggle(Player player) {
        if (ModConfig.get().enabled) {
            player.sendSystemMessage(Component.translatable(TOGGLE_DISABLED_LANG));
            ModConfig.get().enabled = false;
        } else {
            player.sendSystemMessage(Component.translatable(TOGGLE_ENABLED_LANG));
            ModConfig.get().enabled = true;
        }
    }
}
