package net.nathan.nathansbiomes.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.entity.mob.HostileEntity.canSpawnInDark;

@Mixin(StrayEntity.class)
public class MixinStrayEntity {

    @Overwrite
    public static boolean canSpawn(EntityType<StrayEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos blockPos = pos;

        do {
            blockPos = blockPos.up();
        } while(world.getBlockState(blockPos).isOf(Blocks.POWDER_SNOW));

        return canSpawnInDark(type, world, spawnReason, pos, random);
    }
}
