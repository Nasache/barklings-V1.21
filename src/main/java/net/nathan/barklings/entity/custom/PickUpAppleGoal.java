package net.nathan.barklings.entity.custom;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.nathan.barklings.entity.CustomLootTables;
import net.nathan.barklings.entity.variant.BarklingVariant;
import net.nathan.barklings.util.ModTags;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class PickUpAppleGoal extends Goal {
    private final BarklingEntity barkling;
    private final double speed;
    private ItemEntity targetApple;
    private int cooldown;

    public PickUpAppleGoal(BarklingEntity barkling, double speed) {
        this.barkling = barkling;
        this.speed = speed;
        this.cooldown = 0;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    public BarklingVariant getVariant() {
        return BarklingVariant.byId(barkling.getTypeVariant() & 255);
    }

    public List<ItemStack> dropLoot() {
        BarklingVariant variant = getVariant();

        RegistryKey<LootTable> lootTableIdentifier;
        switch (variant) {
            case OAK:
            case OAK_MOSS:
                lootTableIdentifier = CustomLootTables.OAK_BARKLING_BARTERING;
                break;
            case BIRCH:
            case BIRCH_MOSS:
            case BIRCH_SMUSH:
            case BIRCH_SMUSH_MOSS:
            case BIRCH_BMUSH:
            case BIRCH_BMUSH_MOSS:
            case BIRCH_DMUSH:
            case BIRCH_DMUSH_MOSS:
                lootTableIdentifier = CustomLootTables.BIRCH_BARKLING_BARTERING;
                break;
            case DARK_OAK:
            case DARK_OAK_MOSS:
            case DARK_OAK_BMUSH:
            case DARK_OAK_BMUSH_MOSS:
            case DARK_OAK_RMUSH:
            case DARK_OAK_RMUSH_MOSS:
            case DARK_OAK_DMUSH:
            case DARK_OAK_DMUSH_MOSS:
                lootTableIdentifier = CustomLootTables.DARK_OAK_BARKLING_BARTERING;
                break;
            case SPRUCE:
            case SPRUCE_SNOW:
            case SPRUCE_SMUSH:
            case SPRUCE_SMUSH_SNOW:
            case SPRUCE_BMUSH:
            case SPRUCE_BMUSH_SNOW:
            case SPRUCE_DMUSH:
            case SPRUCE_DMUSH_SNOW:
                lootTableIdentifier = CustomLootTables.SPRUCE_BARKLING_BARTERING;
                break;
            case CHERRY:
            case CHERRY_MOSS:
            case CHERRY_HONEY:
                lootTableIdentifier = CustomLootTables.CHERRY_BARKLING_BARTERING;
                break;
            case MANGROVE:
            case MANGROVE_MOSS:
            case MANGROVE_BMUSH:
            case MANGROVE_BMUSH_MOSS:
            case MANGROVE_RMUSH:
            case MANGROVE_RMUSH_MOSS:
            case MANGROVE_DMUSH:
            case MANGROVE_DMUSH_MOSS:
                lootTableIdentifier = CustomLootTables.MANGROVE_BARKLING_BARTERING;
                break;
            case JUNGLE:
            case JUNGLE_MOSS:
            case JUNGLE_VINES:
                lootTableIdentifier = CustomLootTables.JUNGLE_BARKLING_BARTERING;
                break;
            case ACACIA:
            case ACACIA_MOSS:
            case ACACIA_VINES:
                lootTableIdentifier = CustomLootTables.ACACIA_BARKLING_BARTERING;
                break;
            case CRIMSON:
            case CRIMSON_SHROOM:
            case CRIMSON_WART:
            case CRIMSON_WART_SHROOM:
                lootTableIdentifier = CustomLootTables.CRIMSON_BARKLING_BARTERING;
                break;
            case WARPED:
            case WARPED_SHROOM:
            case WARPED_WART:
            case WARPED_WART_SHROOM:
                lootTableIdentifier = CustomLootTables.WARPED_BARKLING_BARTERING;
                break;
            case MUSHROOM:
            case RED_MUSHROOM:
            case BROWN_MUSHROOM:
            case MUSHROOM_RMUSH:
            case MUSHROOM_BMUSH:
                lootTableIdentifier = CustomLootTables.MUSHROOM_BARKLING_BARTERING;
                break;
            case AZALEA:
            case FLOWER_AZALEA:
            case AZALEA_TREE:
            case FLOWER_AZALEA_TREE:
                lootTableIdentifier = CustomLootTables.AZALEA_BARKLING_BARTERING;
                break;
            default:
                return Collections.emptyList();
        }

        LootTable lootTable = Objects.requireNonNull(
                Objects.requireNonNull(barkling.getWorld().getServer())
                        .getReloadableRegistries()
                        .getLootTable(lootTableIdentifier)
        );

        return lootTable.generateLoot(
                new LootContextParameterSet.Builder((ServerWorld) barkling.getWorld())
                        .add(LootContextParameters.THIS_ENTITY, barkling)
                        .build(LootContextTypes.BARTER)
        );
    }

    private ItemStack getRandomItem(List<ItemStack> loot) {
        if (loot.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return loot.get(barkling.getRandom().nextInt(loot.size()));
    }

    private void dropRandomLoot() {
        List<ItemStack> loot = dropLoot();
        ItemStack randomItem = getRandomItem(loot);
        if (!randomItem.isEmpty()) {
            World world = barkling.getWorld();
            Vec3d pos = barkling.getPos();
            ItemEntity itemEntity = new ItemEntity(world, pos.x, pos.y, pos.z, randomItem);
            world.spawnEntity(itemEntity);
        }
    }

    @Override
    public boolean canStart() {
        if (this.barkling.isBaby()) {
            return false;
        }

        List<ItemEntity> list = this.barkling.getWorld().getEntitiesByClass(ItemEntity.class,
                this.barkling.getBoundingBox().expand(8.0D, 4.0D, 8.0D), (item) -> item.getStack().isIn(ModTags.Items.FRUIT));
        if (!list.isEmpty()) {
            this.targetApple = list.getFirst();
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        BlockPos applePos = this.targetApple.getBlockPos();
        Vec3d targetPosition = new Vec3d(applePos.getX(), applePos.getY(), applePos.getZ());
        this.barkling.getNavigation().startMovingTo(targetPosition.x, targetPosition.y, targetPosition.z, this.speed);
    }

    @Override
    public void stop() {
        this.barkling.getNavigation().stop();
        this.targetApple = null;
        this.cooldown = 0;
    }

    @Override
    public void tick() {
        if (this.cooldown > 0) {
            this.cooldown--;
            return;
        }

        if (this.targetApple != null && this.targetApple.isAlive()) {
            Vec3d targetPosition = new Vec3d(this.targetApple.getX(), this.targetApple.getY(), this.targetApple.getZ());
            double distanceSquared = this.barkling.getPos().squaredDistanceTo(targetPosition);

            if (hasLineOfSight(this.barkling, this.targetApple)) {
                double tradeDistanceSquared = 2.5D;
                double stopDistanceSquared = 0.0D;

                if (distanceSquared > stopDistanceSquared) {
                    this.barkling.getNavigation().startMovingTo(targetPosition.x, targetPosition.y, targetPosition.z, this.speed);
                } else {
                    this.barkling.getNavigation().stop();
                }

                if (distanceSquared <= tradeDistanceSquared) {
                    this.barkling.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, (this.barkling.getRandom().nextFloat() - this.barkling.getRandom().nextFloat()) * 0.2F + 1.0F);

                    ItemStack stack = this.targetApple.getStack();
                    if (stack.getCount() > 1) {
                        stack.decrement(1);
                        this.targetApple.setStack(stack);
                    } else {
                        this.targetApple.discard();
                    }

                    this.dropRandomLoot();
                    this.cooldown = 30;
                }
            } else {
                this.barkling.getNavigation().startMovingTo(targetPosition.x, targetPosition.y, targetPosition.z, this.speed);
            }
        }
    }


    private boolean hasLineOfSight(BarklingEntity barkling, ItemEntity target) {
        World world = barkling.getWorld();
        Vec3d barklingPos = barkling.getEyePos();
        Vec3d targetPos = target.getPos();

        return world.raycast(new RaycastContext(barklingPos, targetPos, RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE, barkling)).getType() == HitResult.Type.MISS;
    }
}
