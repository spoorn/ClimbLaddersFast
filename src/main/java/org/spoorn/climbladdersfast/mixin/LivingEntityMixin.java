package org.spoorn.climbladdersfast.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.climbladdersfast.config.ModConfig;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "applyClimbingSpeed", at = @At(value = "TAIL"), cancellable = true)
    public void adjustClimbingSpeed(Vec3d motion, CallbackInfoReturnable<Vec3d> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        // Do nothing if entity isn't climbing, or is sneaking, or isn't a player
        if (!livingEntity.isClimbing() || livingEntity.isSneaking() || !(livingEntity instanceof PlayerEntity)) {
            return;
        }

        if (isEntityLookingUpOrDown(livingEntity) && isEntityStill(livingEntity)) {
            Vec3d vec3d = cir.getReturnValue();
            double y = vec3d.getY();
            // Go up or down ladder according to configured speed
            if (isEntityLookingUp(livingEntity)) {
                y = getNormalizedPitch(livingEntity.getPitch()) * ModConfig.get().climbUpSpeed;
            } else if (isEntityLookingDown(livingEntity)) {
                y = getNormalizedPitch(livingEntity.getPitch()) * ModConfig.get().climbDownSpeed * -1.0;
            }
            cir.setReturnValue(new Vec3d(vec3d.getX(), y, vec3d.getZ()));
        }
    }

    private boolean isEntityLookingUp(LivingEntity livingEntity) {
        return livingEntity.getPitch() < 0;
    }

    private boolean isEntityLookingDown(LivingEntity livingEntity) {
        return livingEntity.getPitch() > 0;
    }

    private boolean isEntityLookingUpOrDown(LivingEntity livingEntity) {
        return livingEntity.getPitch() != 0;
    }

    private boolean isEntityStill(LivingEntity livingEntity) {
        return livingEntity.forwardSpeed == 0;
    }

    private double getNormalizedPitch(float pitch) {
        return Math.abs(pitch / 90.0);
    }
}
