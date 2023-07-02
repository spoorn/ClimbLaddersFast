package org.spoorn.climbladdersfast.fabric.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import org.spoorn.climbladdersfast.option.KeyBindings;

@Environment(EnvType.CLIENT)
public class ClimbLaddersFastKeyBindingsFabric {
    
    public static void init() {
        KeyBindings.toggle = KeyBindingHelper.registerKeyBinding(KeyBindings.toggle);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBindings.toggle.wasPressed()) {
                KeyBindings.handleToggle(client.player);
            }
        });
    }
}
