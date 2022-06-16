package org.spoorn.climbladdersfast;

import lombok.extern.log4j.Log4j2;
import org.spoorn.climbladdersfast.config.ModConfig;

@Log4j2
public class ClimbLaddersFast {
    
    public static final String MODID = "climbladdersfast";
    
    public static void init() {
        log.info("Hello from ClimbLaddersFast!");
        
        ModConfig.init();
    }
}
