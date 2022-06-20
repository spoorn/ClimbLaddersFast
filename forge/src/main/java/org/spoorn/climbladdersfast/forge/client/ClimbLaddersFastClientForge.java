package org.spoorn.climbladdersfast.forge.client;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.spoorn.climbladdersfast.client.ClimbLaddersFastClient;

public class ClimbLaddersFastClientForge {

    public static void init() {
        if (FMLEnvironment.dist != Dist.CLIENT) {
            return;
        }
        
        ClimbLaddersFastClient.init();
    }
}
