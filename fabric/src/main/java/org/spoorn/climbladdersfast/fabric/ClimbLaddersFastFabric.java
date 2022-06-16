package org.spoorn.climbladdersfast.fabric;

import org.spoorn.climbladdersfast.ClimbLaddersFast;
import net.fabricmc.api.ModInitializer;

public class ClimbLaddersFastFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ClimbLaddersFast.init();
    }
}
