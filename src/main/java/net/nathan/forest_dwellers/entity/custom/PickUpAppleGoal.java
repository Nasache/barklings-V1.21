package net.nathan.forest_dwellers.entity.custom;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.nathan.forest_dwellers.entity.CustomLootTables;

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

    public List<ItemStack> dropLoot() {
        LootTable lootTable = Objects.requireNonNull(
                Objects.requireNonNull(dweller.getWorld().getServer()).getReloadableRegistries().getLootTable(CustomLootTables.DWELLER_BARTERING)
        );
        List<ItemStack> list = lootTable.generateLoot(
                (new LootContextParameterSet.Builder((ServerWorld) dweller.getWorld()))
                        .add(LootContextParameters.THIS_ENTITY, dweller)
                        .build(LootContextTypes.BARTER)
        );
        return list;
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
            BlockPos pos = dweller.getBlockPos();
            ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), randomItem);
            world.spawnEntity(itemEntity);
        }
    }

    @Override
    public boolean canStart() {
        List<ItemEntity> list = this.dweller.getWorld().getEntitiesByClass(ItemEntity.class,
                this.dweller.getBoundingBox().expand(8.0D, 4.0D, 8.0D), (item) -> item.getStack().isOf(Items.APPLE));
        if (!list.isEmpty()) {
            this.targetApple = list.getFirst();
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        BlockPos applePos = this.targetApple.getBlockPos();
        Vec3d targetPosition = new Vec3d(applePos.getX() + 0.5D, applePos.getY() + 0.5D, applePos.getZ() + 0.5D);
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
            double distance = this.dweller.squaredDistanceTo(this.targetApple);
            if (distance > 1.0D) {
                BlockPos applePos = this.targetApple.getBlockPos();
                Vec3d targetPosition = new Vec3d(applePos.getX() + 0.5D, applePos.getY() + 0.5D, applePos.getZ() + 0.5D);
                this.dweller.getNavigation().startMovingTo(targetPosition.x, targetPosition.y, targetPosition.z, this.speed);
            } else {
                this.dweller.getNavigation().stop();
                this.dweller.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, (this.dweller.getRandom().nextFloat() - this.dweller.getRandom().nextFloat()) * 0.2F + 1.0F);

                ItemStack stack = this.targetApple.getStack();
                if (stack.getCount() > 1) {
                    stack.decrement(1);
                    this.targetApple.setStack(stack);
                } else {
                    this.targetApple.discard();
                }

                this.dropRandomLoot();
                this.cooldown = 20;
            }
        }
    }
}