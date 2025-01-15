package net.nathan.barklings.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.nathan.barklings.util.ModDropHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin {

    @Inject(method = "randomTick", at = @At("HEAD"))
    public void onRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (!(Boolean) state.get(LeavesBlock.PERSISTENT) && (Integer) state.get(LeavesBlock.DISTANCE) == 7) {
            ModDropHandler.handleLeafDrops(world, pos, state, null);
        }
    }
}
