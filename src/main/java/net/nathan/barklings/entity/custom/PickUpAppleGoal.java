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
import net.minecraft.util.math.Vec3d;
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

    private static final int ADMIRING_TIME = 40;

    public PickUpAppleGoal(BarklingEntity barkling, double speed) {
        this.barkling = barkling;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    private BarklingVariant getVariant() {
        return BarklingVariant.byId(barkling.getTypeVariant() & 255);
    }

    private List<ItemStack> dropLoot() {
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

    private void dropRandomLoot() {
        List<ItemStack> loot = dropLoot();
        loot.forEach(itemStack -> {
            if (!itemStack.isEmpty()) {
                Vec3d pos = barkling.getPos();
                ItemEntity itemEntity = new ItemEntity(barkling.getWorld(), pos.x, pos.y, pos.z, itemStack);
                barkling.getWorld().spawnEntity(itemEntity);
            }
        });
    }

    @Override
    public boolean canStart() {
        if (barkling.getDataTracker().get(BarklingEntity.IS_ADMIRING)) {
            return true;
        }

        List<ItemEntity> apples = barkling.getWorld().getEntitiesByClass(
                ItemEntity.class,
                barkling.getBoundingBox().expand(8.0D, 4.0D, 8.0D),
                (item) -> item.getStack().isIn(ModTags.Items.FRUIT)
        );

        if (!apples.isEmpty()) {
            targetApple = apples.getFirst();
            return true;
        }

        return false;
    }


    @Override
    public void start() {
        if (targetApple != null) {
            barkling.getNavigation().startMovingTo(targetApple, speed);
        } else if (!barkling.getDataTracker().get(BarklingEntity.IS_ADMIRING)) {
            stop();
        }
    }

    @Override
    public void stop() {
        barkling.getDataTracker().set(BarklingEntity.ADMIRE_TIMER, 0);
        barkling.getDataTracker().set(BarklingEntity.IS_ADMIRING, false);
        targetApple = null;
        barkling.getNavigation().stop();
        barkling.equipItemInHand(ItemStack.EMPTY);
    }


    @Override
    public void tick() {
        if (barkling.getDataTracker().get(BarklingEntity.IS_ADMIRING)) {
            handleAdmiration();
            return;
        }

        if (targetApple == null || !targetApple.isAlive()) {
            if (barkling.getMainHandStack().isEmpty() && canStart()) {
                start();
            } else {
                stop();
            }
            return;
        }

        double distance = barkling.squaredDistanceTo(targetApple);
        if (distance <= 2.5D) {
            barkling.getNavigation().stop();

            if (barkling.getMainHandStack().isEmpty()) {
                barkling.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
                ItemStack stack = targetApple.getStack();

                ItemStack singleItem = stack.split(1);
                barkling.equipItemInHand(singleItem);

                if (stack.isEmpty()) {
                    targetApple.discard();
                }

                barkling.getDataTracker().set(BarklingEntity.IS_ADMIRING, true);
                barkling.getDataTracker().set(BarklingEntity.ADMIRE_TIMER, 0);
            }
        } else {
            barkling.getNavigation().startMovingTo(targetApple, speed);
        }
    }

    private void handleAdmiration() {
        int admireTimer = barkling.getDataTracker().get(BarklingEntity.ADMIRE_TIMER);

        if (admireTimer < ADMIRING_TIME) {
            barkling.getDataTracker().set(BarklingEntity.ADMIRE_TIMER, admireTimer + 1);

            if (admireTimer % 5 == 0) {
                barkling.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.8F, 1.0F);
            }
        } else {
            barkling.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
            dropRandomLoot();
            barkling.equipItemInHand(ItemStack.EMPTY);

            barkling.getDataTracker().set(BarklingEntity.IS_ADMIRING, false);
            barkling.getDataTracker().set(BarklingEntity.ADMIRE_TIMER, 0);

            if (canStart()) {
                start();
            } else {
                stop();
            }
        }
    }

}