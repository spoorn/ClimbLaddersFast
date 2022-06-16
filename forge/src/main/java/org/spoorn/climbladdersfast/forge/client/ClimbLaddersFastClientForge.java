package org.spoorn.climbladdersfast.forge.client;


import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import org.spoorn.climbladdersfast.client.ClimbLaddersFastClient;

public class ClimbLaddersFastClientForge {

    public static void init() {
        if (Platform.getEnvironment() != Env.CLIENT) {
            return;
        }

        ClimbLaddersFastClient.init();
    }
}
