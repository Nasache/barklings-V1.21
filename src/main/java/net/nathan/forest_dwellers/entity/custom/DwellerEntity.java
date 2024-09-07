package net.nathan.forest_dwellers.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.nathan.forest_dwellers.entity.ModEntities;
import net.nathan.forest_dwellers.entity.variant.DwellerVariant;
import net.nathan.forest_dwellers.entity.variant.DwellerVariantCalculator;
import net.nathan.forest_dwellers.util.ModTags;
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
        this.goalSelector.add(3, new PickUpAppleGoal(this, 1.25));
        this.goalSelector.add(4, new TemptGoal(this, 1.25, (stack) -> {
            return stack.isIn(ModTags.Items.FRUIT) || stack.isOf(Items.GOLDEN_APPLE);
        }, false));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
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
            DwellerVariant variant = DwellerVariantCalculator.getVariantForBiome(registryEntry, world.getRandom());
            child.setVariant(variant);
        }
        return child;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
    }

    int getTypeVariant() {
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
        Random random = world.getRandom();

        DwellerVariant variant = DwellerVariantCalculator.getVariantForBiome(registryEntry, random);

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

    private ItemStack getDropForVariant() {
        DwellerVariant variant = getVariant();

        switch (variant) {
            case OAK:
            case OAK_MOSS:
                return new ItemStack(Items.OAK_LOG);
            case BIRCH:
            case BIRCH_MOSS:
            case BIRCH_SMUSH:
            case BIRCH_SMUSH_MOSS:
            case BIRCH_BMUSH:
            case BIRCH_BMUSH_MOSS:
            case BIRCH_DMUSH:
            case BIRCH_DMUSH_MOSS:
                return new ItemStack(Items.BIRCH_LOG);
            case DARK_OAK:
            case DARK_OAK_MOSS:
            case DARK_OAK_BMUSH:
            case DARK_OAK_BMUSH_MOSS:
            case DARK_OAK_RMUSH:
            case DARK_OAK_RMUSH_MOSS:
            case DARK_OAK_DMUSH:
            case DARK_OAK_DMUSH_MOSS:
                return new ItemStack(Items.DARK_OAK_LOG);
            case SPRUCE:
            case SPRUCE_SNOW:
            case SPRUCE_SMUSH:
            case SPRUCE_SMUSH_SNOW:
            case SPRUCE_BMUSH:
            case SPRUCE_BMUSH_SNOW:
            case SPRUCE_DMUSH:
            case SPRUCE_DMUSH_SNOW:
                return new ItemStack(Items.SPRUCE_LOG);
            case CHERRY:
            case CHERRY_MOSS:
            case CHERRY_HONEY:
                return new ItemStack(Items.CHERRY_LOG);
            case MANGROVE:
            case MANGROVE_MOSS:
            case MANGROVE_BMUSH:
            case MANGROVE_BMUSH_MOSS:
            case MANGROVE_RMUSH:
            case MANGROVE_RMUSH_MOSS:
            case MANGROVE_DMUSH:
            case MANGROVE_DMUSH_MOSS:
                return new ItemStack(Items.MANGROVE_LOG);
            case JUNGLE:
            case JUNGLE_MOSS:
            case JUNGLE_VINES:
                return new ItemStack(Items.JUNGLE_LOG);
            case ACACIA:
            case ACACIA_MOSS:
            case ACACIA_VINES:
                return new ItemStack(Items.ACACIA_LOG);
            case CRIMSON:
            case CRIMSON_SHROOM:
            case CRIMSON_WART:
            case CRIMSON_WART_SHROOM:
                return new ItemStack(Items.CRIMSON_STEM);
            case WARPED:
            case WARPED_SHROOM:
            case WARPED_WART:
            case WARPED_WART_SHROOM:
                return new ItemStack(Items.WARPED_STEM);
            default:
                return ItemStack.EMPTY;
        }
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);

        if (!this.isBaby() && !this.getWorld().isClient) {
            this.dropStack(getDropForVariant());
        }
    }

    //@Nullable
    //@Override
    //protected SoundEvent getAmbientSound() {
    //    return SoundEvents.ENTITY_ALLAY_AMBIENT_WITH_ITEM;
    //}

    protected SoundEvent getStepSound() {
        return SoundEvents.BLOCK_WOOD_STEP;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(this.getStepSound(), 0.10F, 2.0F);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_WOOD_HIT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_BAMBOO_WOOD_BREAK;
    }
}
