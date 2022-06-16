package org.spoorn.climbladdersfast.fabric.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import org.spoorn.climbladdersfast.config.ModConfig;

/**
 * Mod Menu config screen.
 */
public class ClimbLaddersFastModMenuApiImpl implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(ModConfig.class, parent).get();
    }
}
