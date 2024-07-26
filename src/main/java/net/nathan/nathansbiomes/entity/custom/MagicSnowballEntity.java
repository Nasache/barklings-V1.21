package net.nathan.nathansbiomes.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import net.nathan.nathansbiomes.block.ModBlocks;

public class MagicSnowballEntity extends SnowballEntity {

    private static final int DESPAWN_TIME_TICKS = 20;
    private int timeSinceHit = 0;

    public MagicSnowballEntity(World world, Entity owner) {
        super(world, (LivingEntity) owner);
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld() != null) {
            timeSinceHit++;
            if (timeSinceHit > DESPAWN_TIME_TICKS) {
                discard();
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        timeSinceHit = 0;

        if (hitResult.getType() == HitResult.Type.ENTITY) {
            Entity entity = ((EntityHitResult) hitResult).getEntity();

            if (getWorld() instanceof ServerWorld serverWorld) {
                BlockPos entityPos = entity.getBlockPos().up(10);

                int[][] firstLayer = {
                        {0, 0}, {1, 0}, {2, 0},
                        {0, 1}, {1, 1}, {2, 1},
                        {0, 2}, {1, 2}, {2, 2}
                };

                int[][] secondLayer = {
                        {0, 1}, {1, 1}, {2, 1},
                        {1, 0}, {1, 1}, {1, 2}
                };

                placeBlocks(serverWorld, entityPos, firstLayer, 0);
                placeBlocks(serverWorld, entityPos, secondLayer, 1);

                spawnExplosionParticle(serverWorld, entityPos.down());
                spawnParticleEffect(serverWorld, entityPos.down(10));
            }

            discard();
        }
    }

    private void placeBlocks(ServerWorld world, BlockPos origin, int[][] coordinates, int yOffset) {
        for (int[] coordinate : coordinates) {
            BlockPos targetPos = origin.add(coordinate[0] - 1, yOffset, coordinate[1] - 1);
            BlockState targetState = world.getBlockState(targetPos);

            if (targetState.isAir()) {
                world.setBlockState(targetPos, ModBlocks.FALLING_ICE_BLOCK.getDefaultState());
            }
        }
    }

    private void spawnExplosionParticle(ServerWorld world, BlockPos pos) {
        double centerX = pos.getX() + 0.5;
        double centerY = pos.getY() + 0.5;
        double centerZ = pos.getZ() + 0.5;

        world.spawnParticles(ParticleTypes.EXPLOSION, centerX, centerY, centerZ, 1, 0.0, 0.0, 0.0, 0.0);
    }

    private void spawnParticleEffect(ServerWorld world, BlockPos pos) {
        double centerX = pos.getX() + 0.5;
        double centerY = pos.getY() + 0.5;
        double centerZ = pos.getZ() + 0.5;

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                world.spawnParticles(ParticleTypes.CLOUD, centerX + x, centerY, centerZ + z, 1, 0.0, 0.0, 0.0, 0.0);
            }
        }
    }
}
