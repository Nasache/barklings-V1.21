package net.nathan.forest_dwellers.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.nathan.forest_dwellers.entity.ModEntities;
import net.nathan.forest_dwellers.entity.variant.DwellerVariant;
import org.jetbrains.annotations.Nullable;

public class DwellerEntity extends AnimalEntity {

public static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
        DataTracker.registerData(DwellerEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public DwellerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.25, (stack) -> {
            return stack.isOf(Items.APPLE) || stack.isOf(Items.GOLDEN_APPLE);
        }, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <=0 ) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    public static DefaultAttributeContainer.Builder createDwellerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.GOLDEN_APPLE);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        DwellerEntity child = ModEntities.DWELLER.create(world);
        if (child != null) {
            RegistryEntry<Biome> registryEntry = world.getBiome(this.getBlockPos());
            DwellerVariant variant;

            if (registryEntry.matchesKey(BiomeKeys.FOREST) || registryEntry.matchesKey(BiomeKeys.FLOWER_FOREST)) {
                int randomOakId = 0 + world.getRandom().nextInt(4);
                variant = DwellerVariant.byId(randomOakId);
            } else if (registryEntry.matchesKey(BiomeKeys.BIRCH_FOREST) || registryEntry.matchesKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST)) {
                int randomBirchId = 2 + world.getRandom().nextInt(8);
                variant = DwellerVariant.byId(randomBirchId);
            } else if (registryEntry.matchesKey(BiomeKeys.DARK_FOREST)) {
                int randomDarkOakId = 10 + world.getRandom().nextInt(8);
                variant = DwellerVariant.byId(randomDarkOakId);
            } else if (registryEntry.matchesKey(BiomeKeys.GROVE) || registryEntry.matchesKey(BiomeKeys.TAIGA)
                    || registryEntry.matchesKey(BiomeKeys.SNOWY_TAIGA) || registryEntry.matchesKey(BiomeKeys.OLD_GROWTH_PINE_TAIGA)
                    || registryEntry.matchesKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA) || registryEntry.matchesKey(BiomeKeys.SNOWY_BEACH)
                    || registryEntry.matchesKey(BiomeKeys.SNOWY_PLAINS) || registryEntry.matchesKey(BiomeKeys.SNOWY_SLOPES)
                    || registryEntry.matchesKey(BiomeKeys.COLD_OCEAN) || registryEntry.matchesKey(BiomeKeys.DEEP_COLD_OCEAN)
                    || registryEntry.matchesKey(BiomeKeys.FROZEN_OCEAN) || registryEntry.matchesKey(BiomeKeys.FROZEN_PEAKS)
                    || registryEntry.matchesKey(BiomeKeys.FROZEN_RIVER) || registryEntry.matchesKey(BiomeKeys.DEEP_FROZEN_OCEAN)) {
                int randomSpruceId = 18 + world.getRandom().nextInt(8);
                variant = DwellerVariant.byId(randomSpruceId);
            } else {
                int randomOakId = 0 + world.getRandom().nextInt(4);
                variant = DwellerVariant.byId(randomOakId);
            }

            child.setVariant(variant);
        }
        return child;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    public DwellerVariant getVariant() {
        return DwellerVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(DwellerVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        RegistryEntry<Biome> registryEntry = world.getBiome(this.getBlockPos());
        DwellerVariant variant;

        if (registryEntry.matchesKey(BiomeKeys.FOREST) || registryEntry.matchesKey(BiomeKeys.FLOWER_FOREST)) {
            int randomOakId = 0 + world.getRandom().nextInt(4);
            variant = DwellerVariant.byId(randomOakId);
        } else if (registryEntry.matchesKey(BiomeKeys.BIRCH_FOREST) || registryEntry.matchesKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST)) {
            int randomBirchId = 2 + world.getRandom().nextInt(8);
            variant = DwellerVariant.byId(randomBirchId);
        } else if (registryEntry.matchesKey(BiomeKeys.DARK_FOREST)) {
            int randomDarkOakId = 10 + world.getRandom().nextInt(8);
            variant = DwellerVariant.byId(randomDarkOakId);
        } else if (registryEntry.matchesKey(BiomeKeys.GROVE) || registryEntry.matchesKey(BiomeKeys.TAIGA)
                || registryEntry.matchesKey(BiomeKeys.SNOWY_TAIGA) || registryEntry.matchesKey(BiomeKeys.OLD_GROWTH_PINE_TAIGA)
                || registryEntry.matchesKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA) || registryEntry.matchesKey(BiomeKeys.SNOWY_BEACH)
                || registryEntry.matchesKey(BiomeKeys.SNOWY_PLAINS) || registryEntry.matchesKey(BiomeKeys.SNOWY_SLOPES)
                || registryEntry.matchesKey(BiomeKeys.COLD_OCEAN) || registryEntry.matchesKey(BiomeKeys.DEEP_COLD_OCEAN)
                || registryEntry.matchesKey(BiomeKeys.FROZEN_OCEAN) || registryEntry.matchesKey(BiomeKeys.FROZEN_PEAKS)
                || registryEntry.matchesKey(BiomeKeys.FROZEN_RIVER) || registryEntry.matchesKey(BiomeKeys.DEEP_FROZEN_OCEAN)) {
            int randomSpruceId = 18 + world.getRandom().nextInt(8);
            variant = DwellerVariant.byId(randomSpruceId);
        } else {
            int randomOakId = 0 + world.getRandom().nextInt(4);
            variant = DwellerVariant.byId(randomOakId);
        }

        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }


    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }
}
