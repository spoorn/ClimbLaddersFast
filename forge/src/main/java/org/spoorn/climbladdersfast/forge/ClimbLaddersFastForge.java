package org.spoorn.climbladdersfast.forge;

import dev.architectury.platform.forge.EventBuses;
import org.spoorn.climbladdersfast.ClimbLaddersFast;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ClimbLaddersFast.MODID)
public class ClimbLaddersFastForge {
    
    public ClimbLaddersFastForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ClimbLaddersFast.MODID, FMLJavaModLoadingContext.get().getModEventBus());
        ClimbLaddersFast.init();
    }
}
