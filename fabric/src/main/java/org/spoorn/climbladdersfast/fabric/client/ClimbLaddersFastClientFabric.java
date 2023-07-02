package org.spoorn.climbladdersfast.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spoorn.climbladdersfast.client.ClimbLaddersFastClient;
import org.spoorn.climbladdersfast.fabric.option.ClimbLaddersFastKeyBindingsFabric;

@Environment(EnvType.CLIENT)
public class ClimbLaddersFastClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClimbLaddersFastClient.init();
        ClimbLaddersFastKeyBindingsFabric.init();
    }
}
