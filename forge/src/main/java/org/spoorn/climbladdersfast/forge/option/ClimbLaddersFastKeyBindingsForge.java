package org.spoorn.climbladdersfast.forge.option;

import net.minecraft.client.MinecraftClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.spoorn.climbladdersfast.ClimbLaddersFast;
import org.spoorn.climbladdersfast.option.KeyBindings;

public class ClimbLaddersFastKeyBindingsForge {
    @Mod.EventBusSubscriber(modid = ClimbLaddersFast.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBindings.toggle.wasPressed()) {
                KeyBindings.handleToggle(MinecraftClient.getInstance().player);
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
