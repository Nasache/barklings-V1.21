package net.nathan.nathansbiomes.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.nathan.nathansbiomes.entity.custom.MagicSnowballEntity;

public class IceSpawnerItem extends ToolItem {

    public IceSpawnerItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.getDamage() < itemStack.getMaxDamage()) {
            player.getItemCooldownManager().set(this, 30);

            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BREEZE_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

            if (!world.isClient) {
                MagicSnowballEntity snowball = new MagicSnowballEntity(world, player);
                snowball.setItem(new ItemStack(this));

                Vec3d eyePos = player.getCameraPosVec(1.0F);
                Vec3d lookDir = player.getRotationVec(1.0F);

                Vec3d spawnPos = eyePos.add(lookDir.multiply(0.5));

                snowball.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());

                snowball.setVelocity(lookDir.multiply(2));

                world.spawnEntity(snowball);
            }

            itemStack.damage(1, player, EquipmentSlot.MAINHAND);

            player.incrementStat(Stats.USED.getOrCreateStat(this));

            return TypedActionResult.success(itemStack, world.isClient());
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

}
