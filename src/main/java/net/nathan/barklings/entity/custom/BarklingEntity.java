package net.nathan.barklings.entity.custom;

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
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.nathan.barklings.entity.CustomLootTables;
import net.nathan.barklings.entity.ModEntities;
import net.nathan.barklings.entity.variant.BarklingVariant;
import net.nathan.barklings.entity.variant.BarklingVariantCalculator;
import net.nathan.barklings.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BarklingEntity extends AnimalEntity implements InventoryOwner {
    public static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(BarklingEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private final SimpleInventory inventory = new SimpleInventory(8);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public static final TrackedData<Boolean> IS_ADMIRING =
            DataTracker.registerData(BarklingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Integer> ADMIRE_TIMER =
            DataTracker.registerData(BarklingEntity.class, TrackedDataHandlerRegistry.INTEGER);


    public BarklingEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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

    public static DefaultAttributeContainer.Builder createBarklingAttributes() {
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
        BarklingEntity child = ModEntities.BARKLING.create(world);
        if (child != null) {
            RegistryEntry<Biome> registryEntry = world.getBiome(this.getBlockPos());
            BarklingVariant variant = BarklingVariantCalculator.getVariantForBiome(registryEntry, world.getRandom());
            child.setVariant(variant);
        }
        return child;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
        builder.add(IS_ADMIRING, false);
        builder.add(ADMIRE_TIMER, 0);
    }


    int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    public BarklingVariant getVariant() {
        return BarklingVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(BarklingVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        RegistryEntry<Biome> registryEntry = world.getBiome(this.getBlockPos());
        Random random = world.getRandom();

        BarklingVariant variant = BarklingVariantCalculator.getVariantForBiome(registryEntry, random);

        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
        this.readInventory(nbt, this.getRegistryManager());

        this.dataTracker.set(IS_ADMIRING, nbt.getBoolean("IsAdmiring"));
        this.dataTracker.set(ADMIRE_TIMER, nbt.getInt("AdmireTimer"));
    }


    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
        this.writeInventory(nbt, this.getRegistryManager());

        nbt.putBoolean("IsAdmiring", this.dataTracker.get(IS_ADMIRING));
        nbt.putInt("AdmireTimer", this.dataTracker.get(ADMIRE_TIMER));
    }



    private List<ItemStack> getDropForVariant() {
        BarklingVariant variant = getVariant();
        RegistryKey<LootTable> lootTableIdentifier;

        switch (variant) {
            case OAK:
            case OAK_MOSS:
                lootTableIdentifier = CustomLootTables.OAK_BARKLING_DROPS;
                break;
            case BIRCH:
            case BIRCH_MOSS:
            case BIRCH_SMUSH:
            case BIRCH_SMUSH_MOSS:
            case BIRCH_BMUSH:
            case BIRCH_BMUSH_MOSS:
            case BIRCH_DMUSH:
            case BIRCH_DMUSH_MOSS:
                lootTableIdentifier = CustomLootTables.BIRCH_BARKLING_DROPS;
                break;
            case DARK_OAK:
            case DARK_OAK_MOSS:
            case DARK_OAK_BMUSH:
            case DARK_OAK_BMUSH_MOSS:
            case DARK_OAK_RMUSH:
            case DARK_OAK_RMUSH_MOSS:
            case DARK_OAK_DMUSH:
            case DARK_OAK_DMUSH_MOSS:
                lootTableIdentifier = CustomLootTables.DARK_OAK_BARKLING_DROPS;
                break;
            case SPRUCE:
            case SPRUCE_SNOW:
            case SPRUCE_SMUSH:
            case SPRUCE_SMUSH_SNOW:
            case SPRUCE_BMUSH:
            case SPRUCE_BMUSH_SNOW:
            case SPRUCE_DMUSH:
            case SPRUCE_DMUSH_SNOW:
                lootTableIdentifier = CustomLootTables.SPRUCE_BARKLING_DROPS;
                break;
            case CHERRY:
            case CHERRY_MOSS:
            case CHERRY_HONEY:
                lootTableIdentifier = CustomLootTables.CHERRY_BARKLING_DROPS;
                break;
            case MANGROVE:
            case MANGROVE_MOSS:
            case MANGROVE_BMUSH:
            case MANGROVE_BMUSH_MOSS:
            case MANGROVE_RMUSH:
            case MANGROVE_RMUSH_MOSS:
            case MANGROVE_DMUSH:
            case MANGROVE_DMUSH_MOSS:
                lootTableIdentifier = CustomLootTables.MANGROVE_BARKLING_DROPS;
                break;
            case JUNGLE:
            case JUNGLE_MOSS:
            case JUNGLE_VINES:
                lootTableIdentifier = CustomLootTables.JUNGLE_BARKLING_DROPS;
                break;
            case ACACIA:
            case ACACIA_MOSS:
            case ACACIA_VINES:
                lootTableIdentifier = CustomLootTables.ACACIA_BARKLING_DROPS;
                break;
            case CRIMSON:
            case CRIMSON_SHROOM:
            case CRIMSON_WART:
            case CRIMSON_WART_SHROOM:
                lootTableIdentifier = CustomLootTables.CRIMSON_BARKLING_DROPS;
                break;
            case WARPED:
            case WARPED_SHROOM:
            case WARPED_WART:
            case WARPED_WART_SHROOM:
                lootTableIdentifier = CustomLootTables.WARPED_BARKLING_DROPS;
                break;
            case MUSHROOM:
            case RED_MUSHROOM:
            case BROWN_MUSHROOM:
            case MUSHROOM_RMUSH:
            case MUSHROOM_BMUSH:
                lootTableIdentifier = CustomLootTables.MUSHROOM_BARKLING_DROPS;
                break;
            case AZALEA:
            case FLOWER_AZALEA:
            case AZALEA_TREE:
            case FLOWER_AZALEA_TREE:
                lootTableIdentifier = CustomLootTables.AZALEA_BARKLING_DROPS;
                break;
            default:
                return Collections.emptyList();
        }

        LootTable lootTable = Objects.requireNonNull(
                Objects.requireNonNull(this.getWorld().getServer())
                        .getReloadableRegistries()
                        .getLootTable(lootTableIdentifier)
        );

        LootContextParameterSet lootContext = new LootContextParameterSet.Builder((ServerWorld) this.getWorld())
                .add(LootContextParameters.THIS_ENTITY, this)
                .add(LootContextParameters.ORIGIN, this.getPos())
                .add(LootContextParameters.DAMAGE_SOURCE, this.getRecentDamageSource())
                .build(LootContextTypes.ENTITY);

        return lootTable.generateLoot(lootContext);
    }


    @Override
    public SimpleInventory getInventory() {
        return this.inventory;
    }

    public void equipItemInHand(ItemStack stack) {
        this.equipStack(EquipmentSlot.MAINHAND, stack);
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);

        if (!this.isBaby() && !this.getWorld().isClient) {
            List<ItemStack> drops = getDropForVariant();

            for (ItemStack drop : drops) {
                this.dropStack(drop);
            }
        }
    }

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
