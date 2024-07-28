package net.nathan.nathansbiomes.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.nathan.nathansbiomes.block.ModBlocks;

import java.util.Random;

public class IceologerEntity extends SpellcastingIllagerEntity {
    private static final Random RANDOM = new Random();

    public IceologerEntity(EntityType<? extends IceologerEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 10;
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtTargetGoal());
        this.goalSelector.add(2, new FleeEntityGoal<>(this, PlayerEntity.class, 6.0F, 0.4, 0.7));
        this.goalSelector.add(3, new SummonIceGoal());
        this.goalSelector.add(4, new ConjureFangsGoal());
        this.goalSelector.add(8, new WanderAroundGoal(this, 0.6));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.add(1, (new RevengeGoal(this, RaiderEntity.class)).setGroupRevenge());
        this.targetSelector.add(2, (new ActiveTargetGoal<>(this, PlayerEntity.class, true)).setMaxTimeWithoutVisibility(300));
    }

    public static DefaultAttributeContainer.Builder createIceologerAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0);
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }

    public SoundEvent getCelebratingSound() {
        return SoundEvents.ENTITY_EVOKER_CELEBRATE;
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    protected void mobTick() {
        super.mobTick();
        freezeWaterUnderneath();
    }

    private void freezeWaterUnderneath() {
        BlockPos pos = this.getBlockPos();
        World world = this.getWorld();

        for (int y = 0; y <= 9; y++) {
            BlockPos checkPos = pos.down(y);
            BlockState state = world.getBlockState(checkPos);
            if (state.getBlock() == Blocks.WATER) {
                world.setBlockState(checkPos, Blocks.FROSTED_ICE.getDefaultState());
            }
        }
    }

    public boolean isTeammate(Entity other) {
        return other != null && (other == this || super.isTeammate(other));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_EVOKER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_EVOKER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_EVOKER_HURT;
    }

    protected SoundEvent getCastSpellSound() {
        return SoundEvents.ENTITY_EVOKER_CAST_SPELL;
    }

    public void addBonusForWave(ServerWorld world, int wave, boolean unused) {
    }

    private class SummonIceGoal extends SpellcastingIllagerEntity.CastSpellGoal {
        SummonIceGoal() {
            super();
        }

        public boolean canStart() {
            LivingEntity target = IceologerEntity.this.getTarget();
            if (target != null) {
                if (RANDOM.nextInt(70) + 1 == 8) {
                    double distance = IceologerEntity.this.squaredDistanceTo(target);
                    return distance > 18;
                }
            }
            return false;
        }

        protected int getSpellTicks() {
            return 50;
        }

        protected int startTimeDelay() {
            return 50;
        }

        protected void castSpell() {
            LivingEntity target = IceologerEntity.this.getTarget();
            ServerWorld serverWorld = (ServerWorld) getWorld();
            if (target != null) {
                BlockPos centerPos = target.getBlockPos().up(10);

                int[][] firstLayer = {
                        {-1, -1}, {0, -1}, {1, -1},
                        {-1, 0}, {0, 0}, {1, 0},
                        {-1, 1}, {0, 1}, {1, 1}
                };

                int[][] secondLayer = {
                        {0, -1}, {-1, 0}, {0, 0},
                        {1, 0}, {0, 1}
                };

                placeBlocks((ServerWorld) IceologerEntity.this.getWorld(), centerPos, firstLayer, 0);
                placeBlocks((ServerWorld) IceologerEntity.this.getWorld(), centerPos, secondLayer, 1);

                spawnExplosionParticle(serverWorld, centerPos.down());
                spawnParticleEffect(serverWorld, centerPos.down(10));
            }
        }

        private void placeBlocks(ServerWorld world, BlockPos pos, int[][] offsets, int yOffset) {
            for (int[] offset : offsets) {
                BlockPos blockPos = pos.add(offset[0], yOffset, offset[1]);
                BlockState blockState = ModBlocks.FALLING_ICE_BLOCK.getDefaultState();
                if (world.isAir(blockPos)) {
                    world.setBlockState(blockPos, blockState);
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



    protected SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON;
        }

        protected Spell getSpell() {
            return Spell.DISAPPEAR;
        }
    }

    private class ConjureFangsGoal extends SpellcastingIllagerEntity.CastSpellGoal {
        ConjureFangsGoal() {
            super();
        }

        protected int getSpellTicks() {
            return 40;
        }

        protected int startTimeDelay() {
            return 40;
        }

        public boolean canStart() {
            LivingEntity target = IceologerEntity.this.getTarget();
            if (target != null) {
                if (RANDOM.nextInt(30) + 1 == 8) {
                    double distance = IceologerEntity.this.squaredDistanceTo(target);
                    return distance <= 18;
                }
            }
            return false;
        }

        protected void castSpell() {
            LivingEntity target = IceologerEntity.this.getTarget();
            if (target != null) {
                BlockPos center = new BlockPos((int) IceologerEntity.this.getX(), (int) IceologerEntity.this.getY(), (int) IceologerEntity.this.getZ());
                BlockPos[] positions = new BlockPos[]{
                        new BlockPos(-1, 0, -2), new BlockPos(0, 0, -2), new BlockPos(1, 0, -2),
                        new BlockPos(-2, 0, -1), new BlockPos(-1, 0, -1), new BlockPos(0, 0, -1), new BlockPos(1, 0, -1), new BlockPos(2, 0, -1),
                        new BlockPos(-2, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(1, 0, 0), new BlockPos(2, 0, 0),
                        new BlockPos(-2, 0, 1), new BlockPos(-1, 0, 1), new BlockPos(0, 0, 1), new BlockPos(1, 0, 1), new BlockPos(2, 0, 1),
                        new BlockPos(-1, 0, 2), new BlockPos(0, 0, 2), new BlockPos(1, 0, 2)
                };

                for (BlockPos pos : positions) {
                    conjureFangs(center.getX() + pos.getX() - 0.5, center.getY(), center.getZ() + pos.getZ() - 0.5);
                }
            }
        }

        private void conjureFangs(double x, double y, double z) {
            BlockPos blockPos = new BlockPos((int) x, (int) y, (int) z);
            boolean hasSolidBlockBelow = false;
            double yPos = 0.0;

            do {
                BlockPos belowPos = blockPos.down();
                BlockState blockState = IceologerEntity.this.getWorld().getBlockState(belowPos);
                if (blockState.isSideSolidFullSquare(IceologerEntity.this.getWorld(), belowPos, Direction.UP)) {
                    if (!IceologerEntity.this.getWorld().isAir(blockPos)) {
                        BlockState currentState = IceologerEntity.this.getWorld().getBlockState(blockPos);
                        VoxelShape voxelShape = currentState.getCollisionShape(IceologerEntity.this.getWorld(), blockPos);
                        if (!voxelShape.isEmpty()) {
                            yPos = voxelShape.getMax(Direction.Axis.Y);
                        }
                    }
                    hasSolidBlockBelow = true;
                    break;
                }
                blockPos = blockPos.down();
            } while (blockPos.getY() >= MathHelper.floor(y) - 1);

            if (hasSolidBlockBelow) {
                IceologerEntity.this.getWorld().spawnEntity(new IceologerFangsEntity(IceologerEntity.this.getWorld(), x, blockPos.getY() + yPos, z, 0.0F, 3, IceologerEntity.this));
            }
        }

        protected SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_ATTACK;
        }

        protected Spell getSpell() {
            return Spell.SUMMON_VEX;
        }
    }
}
