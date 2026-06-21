package org.spoorn.climbladdersfast.neoforge;

import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.NetworkConstants;
import org.spoorn.climbladdersfast.ClimbLaddersFast;
import org.spoorn.climbladdersfast.neoforge.client.ClimbLaddersFastClientNeoForge;

@Mod(ClimbLaddersFast.MODID)
public class ClimbLaddersFastNeoForge {
    
    public ClimbLaddersFastNeoForge() {
        // Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        ClimbLaddersFast.init();
        
        // Client
        ClimbLaddersFastClientNeoForge.init();
    }
}
