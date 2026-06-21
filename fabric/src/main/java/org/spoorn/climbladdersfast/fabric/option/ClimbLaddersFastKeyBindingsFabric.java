package org.spoorn.climbladdersfast.fabric.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import org.spoorn.climbladdersfast.option.KeyBindings;

@Environment(EnvType.CLIENT)
public class ClimbLaddersFastKeyBindingsFabric {
    
    public static void init() {
        KeyBindings.toggle = KeyMappingHelper.registerKeyMapping(KeyBindings.toggle);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBindings.toggle.consumeClick()) {
                KeyBindings.handleToggle(client.player);
            }
        });
    }
}
