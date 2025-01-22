package net.nathan.barklings.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nathan.barklings.item.ModItems;

public class ModDropHandler {

    public static void handleLeafDrops(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (world.isClient) return;

        boolean includesSilkTouch = false;
        boolean isShears = false;

        if (player != null) {
            if (player.getAbilities().creativeMode) {
                return;
            }

            ItemStack toolStack = player.getMainHandStack();

            for (var enchantment : toolStack.getEnchantments().getEnchantments()) {
                if (enchantment.getIdAsString().equals("minecraft:silk_touch")) {
                    includesSilkTouch = true;
                    break;
                }
            }

            isShears = toolStack.isOf(Items.SHEARS);
        }

        if (includesSilkTouch || isShears) return;

        ItemStack drop = null;
        float dropChance = 0.005f;

        if (state.isOf(Blocks.SPRUCE_LEAVES)) {
            drop = new ItemStack(ModItems.SNOWY_PLUM);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.BIRCH_LEAVES)) {
            drop = new ItemStack(ModItems.WAX_PEAR);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.JUNGLE_LEAVES)) {
            drop = new ItemStack(ModItems.VINANA);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.CHERRY_LEAVES)) {
            drop = new ItemStack(ModItems.CHERRIES);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.DARK_OAK_LEAVES)) {
            drop = new ItemStack(ModItems.POMEGRANATE);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.ACACIA_LEAVES)) {
            drop = new ItemStack(ModItems.ORANGE);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.MANGROVE_LEAVES)) {
            drop = new ItemStack(ModItems.STARFRUIT);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.AZALEA_LEAVES)) {
            drop = new ItemStack(ModItems.VINANA);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.FLOWERING_AZALEA_LEAVES)) {
            drop = new ItemStack(ModItems.VINANA);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.BROWN_MUSHROOM_BLOCK)) {
            drop = new ItemStack(ModItems.VINANA);
            dropChance = 0.5f; //TEST
        } else if (state.isOf(Blocks.RED_MUSHROOM_BLOCK)) {
            drop = new ItemStack(ModItems.VINANA);
            dropChance = 0.5f; //TEST
        }

        if (drop != null && world.getRandom().nextFloat() < dropChance) {
            ServerWorld serverWorld = (ServerWorld) world;
            Block.dropStack(serverWorld, pos, drop);
        }
    }
}
