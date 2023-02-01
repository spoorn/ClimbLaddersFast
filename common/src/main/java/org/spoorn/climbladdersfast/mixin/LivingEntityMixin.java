package org.spoorn.climbladdersfast.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.climbladdersfast.config.ModConfig;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    private LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "applyClimbingSpeed", at = @At(value = "TAIL"), cancellable = true)
    public void adjustClimbingSpeed(Vec3d motion, CallbackInfoReturnable<Vec3d> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        // Do nothing if entity isn't climbing, or is sneaking, or isn't a player
        if (!livingEntity.isClimbing() || livingEntity.isSneaking() || !(livingEntity instanceof PlayerEntity)) {
            return;
        }
        
        // Check disable list
        boolean disableScaffolding = ModConfig.get().disableScaffoldingFastClimbing;
        boolean disableVines = ModConfig.get().disableVinesFastClimbing;
        if (disableScaffolding || disableVines) {
            BlockState climbingBlockState = super.getBlockStateAtPos();
            if (climbingBlockState != null) {   // Should always be true as isClimbing() implicitly checks this
                String id = Registries.BLOCK.getId(climbingBlockState.getBlock()).toString();
                // Climbable identifiers: https://minecraft.fandom.com/wiki/Tag
                // and in climbable.json
                if ((disableScaffolding && id.equals(Registries.BLOCK.getId(Blocks.SCAFFOLDING).toString()))
                    || (disableVines && id.contains("vine"))) {
                    return;
                }
            }
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
