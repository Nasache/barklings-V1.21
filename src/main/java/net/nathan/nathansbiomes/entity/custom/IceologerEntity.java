package net.nathan.nathansbiomes.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nathan.nathansbiomes.block.ModBlocks;

import java.util.List;


public class IceologerEntity extends SpellcastingIllagerEntity {
    public IceologerEntity(EntityType<? extends IceologerEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 10;
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtTargetGoal());
        this.goalSelector.add(2, new FleeEntityGoal<>(this, PlayerEntity.class, 8.0F, 0.6, 1.0));
        this.goalSelector.add(4, new SummonIceGoal());
        this.goalSelector.add(8, new WanderAroundGoal(this, 0.6));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.add(1, (new RevengeGoal(this, RaiderEntity.class)).setGroupRevenge());
        this.targetSelector.add(2, (new ActiveTargetGoal<>(this, PlayerEntity.class, true)).setMaxTimeWithoutVisibility(300));
    }

    public static DefaultAttributeContainer.Builder createIceologerAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0).add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0);
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
            return super.canStart() && IceologerEntity.this.getTarget() != null;
        }

        protected int getSpellTicks() {
            return 100;
        }

        protected int startTimeDelay() {
            return 340;
        }

        protected void castSpell() {
            List<PlayerEntity> nearbyPlayers = IceologerEntity.this.getWorld().getEntitiesByClass(PlayerEntity.class,
                    IceologerEntity.this.getBoundingBox().expand(15), player -> player.isAlive());

            for (PlayerEntity player : nearbyPlayers) {
                BlockPos pos = player.getBlockPos().up(10);
                int[][] firstLayer = {
                        {0, 0}, {1, 0}, {2, 0},
                        {0, 1}, {1, 1}, {2, 1},
                        {0, 2}, {1, 2}, {2, 2}
                };

                int[][] secondLayer = {
                        {0, 1}, {1, 1}, {2, 1},
                        {1, 0}, {1, 1}, {1, 2}
                };

                placeBlocks((ServerWorld) IceologerEntity.this.getWorld(), pos, firstLayer, 0);
                placeBlocks((ServerWorld) IceologerEntity.this.getWorld(), pos, secondLayer, 1);
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


        protected SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON;
        }

        protected Spell getSpell() {
            return Spell.SUMMON_VEX; // Adjust to a new spell type if necessary
        }
    }
}
