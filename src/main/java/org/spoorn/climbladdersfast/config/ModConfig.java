package org.spoorn.climbladdersfast.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import org.spoorn.climbladdersfast.ClimbLaddersFast;

@Config(name = ClimbLaddersFast.MODID)
public class ModConfig implements ConfigData {

    @Comment("Speed for climbing up ladders [default = 0.4]")
    public double climbUpSpeed = 0.4;

    @Comment("Speed for climbing down ladders [default = 0.4]")
    public double climbDownSpeed = 0.4;

    public static void init() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
