package org.spoorn.climbladdersfast.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.climbladdersfast.config.ModConfig;

@Mixin(ScaffoldingBlock.class)
public class ScaffoldingBlockMixin extends Block {

    @Shadow @Final private static VoxelShape OUTLINE_SHAPE;

    protected ScaffoldingBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getCollisionShape", at = @At(value = "HEAD"), cancellable = true)
    private void removeScaffoldingCollision(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (ModConfig.get().disableScaffoldingCollision && context.isAbove(OUTLINE_SHAPE, pos, true) && !context.isDescending()) {
            cir.setReturnValue(VoxelShapes.empty());
            cir.cancel();
        }
    }
}
