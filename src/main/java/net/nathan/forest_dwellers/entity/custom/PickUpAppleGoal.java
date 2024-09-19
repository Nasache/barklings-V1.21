package net.nathan.forest_dwellers.entity.custom;

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
import net.nathan.forest_dwellers.entity.CustomLootTables;
import net.nathan.forest_dwellers.entity.variant.DwellerVariant;
import net.nathan.forest_dwellers.util.ModTags;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class PickUpAppleGoal extends Goal {
    private final DwellerEntity dweller;
    private final double speed;
    private ItemEntity targetApple;
    private int cooldown;

    public PickUpAppleGoal(DwellerEntity dweller, double speed) {
        this.dweller = dweller;
        this.speed = speed;
        this.cooldown = 0;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    public DwellerVariant getVariant() {
        return DwellerVariant.byId(dweller.getTypeVariant() & 255);
    }

    public List<ItemStack> dropLoot() {
        DwellerVariant variant = getVariant();

        RegistryKey<LootTable> lootTableIdentifier;
        switch (variant) {
            case OAK:
            case OAK_MOSS:
                lootTableIdentifier = CustomLootTables.OAK_DWELLER_BARTERING;
                break;
            case BIRCH:
            case BIRCH_MOSS:
            case BIRCH_SMUSH:
            case BIRCH_SMUSH_MOSS:
            case BIRCH_BMUSH:
            case BIRCH_BMUSH_MOSS:
            case BIRCH_DMUSH:
            case BIRCH_DMUSH_MOSS:
                lootTableIdentifier = CustomLootTables.BIRCH_DWELLER_BARTERING;
                break;
            case DARK_OAK:
            case DARK_OAK_MOSS:
            case DARK_OAK_BMUSH:
            case DARK_OAK_BMUSH_MOSS:
            case DARK_OAK_RMUSH:
            case DARK_OAK_RMUSH_MOSS:
            case DARK_OAK_DMUSH:
            case DARK_OAK_DMUSH_MOSS:
                lootTableIdentifier = CustomLootTables.DARK_OAK_DWELLER_BARTERING;
                break;
            case SPRUCE:
            case SPRUCE_SNOW:
            case SPRUCE_SMUSH:
            case SPRUCE_SMUSH_SNOW:
            case SPRUCE_BMUSH:
            case SPRUCE_BMUSH_SNOW:
            case SPRUCE_DMUSH:
            case SPRUCE_DMUSH_SNOW:
                lootTableIdentifier = CustomLootTables.SPRUCE_DWELLER_BARTERING;
                break;
            case CHERRY:
            case CHERRY_MOSS:
            case CHERRY_HONEY:
                lootTableIdentifier = CustomLootTables.CHERRY_DWELLER_BARTERING;
                break;
            case MANGROVE:
            case MANGROVE_MOSS:
            case MANGROVE_BMUSH:
            case MANGROVE_BMUSH_MOSS:
            case MANGROVE_RMUSH:
            case MANGROVE_RMUSH_MOSS:
            case MANGROVE_DMUSH:
            case MANGROVE_DMUSH_MOSS:
                lootTableIdentifier = CustomLootTables.MANGROVE_DWELLER_BARTERING;
                break;
            case JUNGLE:
            case JUNGLE_MOSS:
            case JUNGLE_VINES:
                lootTableIdentifier = CustomLootTables.JUNGLE_DWELLER_BARTERING;
                break;
            case ACACIA:
            case ACACIA_MOSS:
            case ACACIA_VINES:
                lootTableIdentifier = CustomLootTables.ACACIA_DWELLER_BARTERING;
                break;
            case CRIMSON:
            case CRIMSON_SHROOM:
            case CRIMSON_WART:
            case CRIMSON_WART_SHROOM:
                lootTableIdentifier = CustomLootTables.CRIMSON_DWELLER_BARTERING;
                break;
            case WARPED:
            case WARPED_SHROOM:
            case WARPED_WART:
            case WARPED_WART_SHROOM:
                lootTableIdentifier = CustomLootTables.WARPED_DWELLER_BARTERING;
                break;
            default:
                return Collections.emptyList();
        }

        LootTable lootTable = Objects.requireNonNull(
                Objects.requireNonNull(dweller.getWorld().getServer())
                        .getReloadableRegistries()
                        .getLootTable(lootTableIdentifier)
        );

        return lootTable.generateLoot(
                new LootContextParameterSet.Builder((ServerWorld) dweller.getWorld())
                        .add(LootContextParameters.THIS_ENTITY, dweller)
                        .build(LootContextTypes.BARTER)
        );
    }

    private ItemStack getRandomItem(List<ItemStack> loot) {
        if (loot.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return loot.get(dweller.getRandom().nextInt(loot.size()));
    }

    private void dropRandomLoot() {
        List<ItemStack> loot = dropLoot();
        ItemStack randomItem = getRandomItem(loot);
        if (!randomItem.isEmpty()) {
            World world = dweller.getWorld();
            Vec3d pos = dweller.getPos();
            ItemEntity itemEntity = new ItemEntity(world, pos.x, pos.y, pos.z, randomItem);
            world.spawnEntity(itemEntity);
        }
    }

    @Override
    public boolean canStart() {
        if (this.dweller.isBaby()) {
            return false;
        }

        List<ItemEntity> list = this.dweller.getWorld().getEntitiesByClass(ItemEntity.class,
                this.dweller.getBoundingBox().expand(8.0D, 4.0D, 8.0D), (item) -> item.getStack().isIn(ModTags.Items.FRUIT));
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
        this.dweller.getNavigation().startMovingTo(targetPosition.x, targetPosition.y, targetPosition.z, this.speed);
    }

    @Override
    public void stop() {
        this.dweller.getNavigation().stop();
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
            double distanceSquared = this.dweller.getPos().squaredDistanceTo(targetPosition);

            if (hasLineOfSight(this.dweller, this.targetApple)) {
                double tradeDistanceSquared = 2.5D;
                double stopDistanceSquared = 0.0D;

                if (distanceSquared > stopDistanceSquared) {
                    this.dweller.getNavigation().startMovingTo(targetPosition.x, targetPosition.y, targetPosition.z, this.speed);
                } else {
                    this.dweller.getNavigation().stop();
                }

                if (distanceSquared <= tradeDistanceSquared) {
                    this.dweller.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, (this.dweller.getRandom().nextFloat() - this.dweller.getRandom().nextFloat()) * 0.2F + 1.0F);

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
                this.dweller.getNavigation().startMovingTo(targetPosition.x, targetPosition.y, targetPosition.z, this.speed);
            }
        }
    }


    private boolean hasLineOfSight(DwellerEntity dweller, ItemEntity target) {
        World world = dweller.getWorld();
        Vec3d dwellerPos = dweller.getEyePos();
        Vec3d targetPos = target.getPos();

        return world.raycast(new RaycastContext(dwellerPos, targetPos, RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE, dweller)).getType() == HitResult.Type.MISS;
    }
}
