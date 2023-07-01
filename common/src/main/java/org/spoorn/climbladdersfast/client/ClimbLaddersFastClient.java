package org.spoorn.climbladdersfast.client;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Log4j2
public class ClimbLaddersFastClient {
    public static void init() {
        log.info("Hello client from ClimbLaddersFast!");
    }
}
