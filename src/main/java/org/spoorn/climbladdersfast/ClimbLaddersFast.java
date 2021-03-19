package org.spoorn.climbladdersfast;

import net.fabricmc.api.ModInitializer;
import org.spoorn.climbladdersfast.config.ModConfig;

public class ClimbLaddersFast implements ModInitializer {

    public static final String MODID = "ClimbLaddersFast";

    @Override
    public void onInitialize() {
        ModConfig.init();
    }
}
