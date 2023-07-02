package org.spoorn.climbladdersfast.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import org.spoorn.climbladdersfast.ClimbLaddersFast;

@Config(name = ClimbLaddersFast.MODID)
public class ModConfig implements ConfigData {
    
    @ConfigEntry.Gui.Excluded
    public static boolean registered = false;
    
    @Comment("True to enable ClimbLaddersFast, false to disable.  This is also toggled by the toggle keybind in controls. [default = true]")
    public boolean enabled = true;

    @Comment("Speed for climbing up ladders [default = 0.4]")
    public double climbUpSpeed = 0.4;

    @Comment("Speed for climbing down ladders [default = 0.4]")
    public double climbDownSpeed = 0.4;

    @Comment("True to disable faster climbing on Scaffolding [default = false]")
    public boolean disableScaffoldingFastClimbing = false;
    
    @Comment("True to disable collisions with Scaffolding, allowing fast climbing down scaffolding just like ladders [default = true]")
    public boolean disableScaffoldingCollision = true;
    
    @Comment("True to disable faster climbing on vines [default = false]")
    public boolean disableVinesFastClimbing = false;

    public static void init() {
        if (!registered) {
            AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
            registered = true;
        }
    }

    public static ModConfig get() {
        // Due to https://github.com/spoorn/ClimbLaddersFast/issues/9
        if (!registered) {
            init();
        }
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
