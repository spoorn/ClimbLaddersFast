package org.spoorn.climbladdersfast.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.climbladdersfast.config.ModConfig;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    private LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "handleOnClimbable", at = @At(value = "TAIL"), cancellable = true)
    public void adjustClimbingSpeed(Vec3 motion, CallbackInfoReturnable<Vec3> cir) {
        if (!ModConfig.get().enabled) {
            return;
        }
        
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        // Do nothing if entity isn't climbing, or is sneaking, or isn't a player
        if (!livingEntity.onClimbable() || livingEntity.isShiftKeyDown() || !(livingEntity instanceof Player)) {
            return;
        }
        
        // Check disable list
        boolean disableScaffolding = ModConfig.get().disableScaffoldingFastClimbing;
        boolean disableVines = ModConfig.get().disableVinesFastClimbing;
        if (disableScaffolding || disableVines) {
            BlockState climbingBlockState = super.getInBlockState();
            if (climbingBlockState != null) {   // Should always be true as onClimbable() implicitly checks this
                String id = BuiltInRegistries.BLOCK.getKey(climbingBlockState.getBlock()).toString();
                // Climbable identifiers: https://minecraft.fandom.com/wiki/Tag
                // and in climbable.json
                if ((disableScaffolding && id.equals(BuiltInRegistries.BLOCK.getKey(Blocks.SCAFFOLDING).toString()))
                    || (disableVines && id.contains("vine"))) {
                    return;
                }
            }
        }

        if (isEntityLookingUpOrDown(livingEntity) && isEntityStill(livingEntity)) {
            Vec3 vec3d = cir.getReturnValue();
            double y = vec3d.y;
            // Go up or down ladder according to configured speed
            if (isEntityLookingUp(livingEntity)) {
                y = getNormalizedPitch(livingEntity.getXRot()) * ModConfig.get().climbUpSpeed;
            } else if (isEntityLookingDown(livingEntity)) {
                y = getNormalizedPitch(livingEntity.getXRot()) * ModConfig.get().climbDownSpeed * -1.0;
            }
            cir.setReturnValue(new Vec3(vec3d.x, y, vec3d.z));
        }
    }

    private boolean isEntityLookingUp(LivingEntity livingEntity) {
        return livingEntity.getXRot() < 0;
    }

    private boolean isEntityLookingDown(LivingEntity livingEntity) {
        return livingEntity.getXRot() > 0;
    }

    private boolean isEntityLookingUpOrDown(LivingEntity livingEntity) {
        return livingEntity.getXRot() != 0;
    }

    private boolean isEntityStill(LivingEntity livingEntity) {
        return livingEntity.zza == 0;
    }

    private double getNormalizedPitch(float pitch) {
        return Math.abs(pitch / 90.0);
    }
}
