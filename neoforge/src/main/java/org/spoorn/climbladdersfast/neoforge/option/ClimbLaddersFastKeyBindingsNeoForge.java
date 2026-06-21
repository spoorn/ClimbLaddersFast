package org.spoorn.climbladdersfast.neoforge.option;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.spoorn.climbladdersfast.ClimbLaddersFast;
import org.spoorn.climbladdersfast.option.KeyBindings;

public class ClimbLaddersFastKeyBindingsNeoForge {
    @Mod.EventBusSubscriber(modid = ClimbLaddersFast.MODID, value = Dist.CLIENT)
    public static class ClientNeoForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBindings.toggle.consumeClick()) {
                KeyBindings.handleToggle(Minecraft.getInstance().player);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = ClimbLaddersFast.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBindings.toggle);
        }
    }
}
