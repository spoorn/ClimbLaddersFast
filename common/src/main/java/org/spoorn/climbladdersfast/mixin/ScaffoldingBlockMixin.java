package org.spoorn.climbladdersfast.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.climbladdersfast.config.ModConfig;

@Mixin(ScaffoldingBlock.class)
public class ScaffoldingBlockMixin extends Block {

    protected ScaffoldingBlockMixin(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Inject(method = "getCollisionShape", at = @At(value = "HEAD"), cancellable = true)
    private void removeScaffoldingCollision(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (ModConfig.get().disableScaffoldingCollision && !ModConfig.get().disableScaffoldingFastClimbing 
                && context.isAbove(Shapes.block(), pos, true) && !context.isDescending()) {
            cir.setReturnValue(Shapes.empty());
            cir.cancel();
        }
    }
}
