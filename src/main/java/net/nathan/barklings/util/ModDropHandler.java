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

        boolean isMushroomBlock = state.isOf(Blocks.BROWN_MUSHROOM_BLOCK) || state.isOf(Blocks.RED_MUSHROOM_BLOCK);

        if (includesSilkTouch || (isShears && !isMushroomBlock)) {
            return;
        }

        ItemStack drop = null;
        float dropChance = 0.005f;

        if (state.isOf(Blocks.SPRUCE_LEAVES)) {
            drop = new ItemStack(ModItems.SNOWY_PLUM);
        } else if (state.isOf(Blocks.BIRCH_LEAVES)) {
            drop = new ItemStack(ModItems.WAX_PEAR);
        } else if (state.isOf(Blocks.JUNGLE_LEAVES)) {
            drop = new ItemStack(ModItems.VIRANA);
        } else if (state.isOf(Blocks.CHERRY_LEAVES)) {
            drop = new ItemStack(ModItems.AURORA_CHERRIES);
        } else if (state.isOf(Blocks.DARK_OAK_LEAVES)) {
            drop = new ItemStack(ModItems.NOCTURNATE);
        } else if (state.isOf(Blocks.ACACIA_LEAVES)) {
            drop = new ItemStack(ModItems.SOLIND);
        } else if (state.isOf(Blocks.MANGROVE_LEAVES)) {
            drop = new ItemStack(ModItems.MARSH_STAR);
        } else if (state.isOf(Blocks.AZALEA_LEAVES)) {
            drop = new ItemStack(ModItems.FLOWERING_LYCHEE);
        } else if (state.isOf(Blocks.FLOWERING_AZALEA_LEAVES)) {
            drop = new ItemStack(ModItems.FLOWERING_LYCHEE);
        } else if (state.isOf(Blocks.BROWN_MUSHROOM_BLOCK)) {
            drop = new ItemStack(ModItems.BROWN_SPORSIMMON);
        } else if (state.isOf(Blocks.RED_MUSHROOM_BLOCK)) {
            drop = new ItemStack(ModItems.RED_CAPAYA);
        }

        if (drop != null && world.getRandom().nextFloat() < dropChance) {
            ServerWorld serverWorld = (ServerWorld) world;
            Block.dropStack(serverWorld, pos, drop);
        }
    }
}