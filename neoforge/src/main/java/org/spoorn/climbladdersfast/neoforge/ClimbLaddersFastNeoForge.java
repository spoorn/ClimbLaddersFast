package org.spoorn.climbladdersfast.neoforge;

import net.neoforged.fml.common.Mod;
import org.spoorn.climbladdersfast.ClimbLaddersFast;
import org.spoorn.climbladdersfast.neoforge.client.ClimbLaddersFastClientNeoForge;

@Mod(ClimbLaddersFast.MODID)
public class ClimbLaddersFastNeoForge {
    
    public ClimbLaddersFastNeoForge() {
        ClimbLaddersFast.init();
        
        // Client
        ClimbLaddersFastClientNeoForge.init();
    }
}
