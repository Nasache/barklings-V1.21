package net.nathan.barklings.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.RegistryKeys;
import net.nathan.barklings.util.ModDropHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin {
    private static final TagKey<Block> FRUIT_LEAVES = TagKey.of(RegistryKeys.BLOCK, Identifier.of("barklings", "fruit_leaves"));

    @Inject(method = "onBreak", at = @At("HEAD"), cancellable = true)
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<BlockState> cir) {
        if (state.isIn(FRUIT_LEAVES)) {
            ModDropHandler.handleLeafDrops(world, pos, state, player);
        }
    }
}
