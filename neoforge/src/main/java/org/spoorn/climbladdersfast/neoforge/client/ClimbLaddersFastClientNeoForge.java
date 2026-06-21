package org.spoorn.climbladdersfast.neoforge.client;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import org.spoorn.climbladdersfast.client.ClimbLaddersFastClient;

public class ClimbLaddersFastClientNeoForge {

    public static void init() {
        if (FMLEnvironment.getDist() != Dist.CLIENT) {
            return;
        }
        
        ClimbLaddersFastClient.init();
    }
}
