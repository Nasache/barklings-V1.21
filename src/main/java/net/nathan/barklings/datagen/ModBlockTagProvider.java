package net.nathan.barklings.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.nathan.barklings.block.ModBlocks;
import net.nathan.barklings.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.GILDED_OAK_PLANKS,
                        ModBlocks.GILDED_OAK_STAIRS,
                        ModBlocks.GILDED_OAK_SLAB,
                        ModBlocks.GILDED_OAK_BUTTON,
                        ModBlocks.GILDED_OAK_PRESSURE_PLATE,
                        ModBlocks.GILDED_OAK_FENCE,
                        ModBlocks.GILDED_OAK_FENCE_GATE,
                        ModBlocks.GILDED_OAK_DOOR,
                        ModBlocks.GILDED_OAK_TRAPDOOR,
                        ModBlocks.GILDED_OAK_LOG,
                        ModBlocks.GILDED_OAK_WOOD,
                        ModBlocks.STRIPPED_GILDED_OAK_LOG,
                        ModBlocks.STRIPPED_GILDED_OAK_WOOD,
                        ModBlocks.GILDED_OAK_HANGING_SIGN,
                        ModBlocks.GILDED_OAK_SIGN,
                        ModBlocks.GILDED_OAK_WALL_SIGN,
                        ModBlocks.GILDED_OAK_WALL_HANGING_SIGN,
                        ModBlocks.LIVING_LANTERN);

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.GILDED_OAK_PLANKS);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.GILDED_OAK_LEAVES);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.GILDED_OAK_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.GILDED_OAK_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.GILDED_OAK_LOG,
                        ModBlocks.GILDED_OAK_WOOD,
                        ModBlocks.STRIPPED_GILDED_OAK_LOG,
                        ModBlocks.STRIPPED_GILDED_OAK_WOOD);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(ModBlocks.GILDED_OAK_LOG,
                        ModBlocks.GILDED_OAK_WOOD,
                        ModBlocks.STRIPPED_GILDED_OAK_LOG,
                        ModBlocks.STRIPPED_GILDED_OAK_WOOD);

        getOrCreateTagBuilder(ModTags.Blocks.GILDED_OAK_LOGS)
                .add(ModBlocks.GILDED_OAK_LOG, ModBlocks.GILDED_OAK_WOOD,
                        ModBlocks.STRIPPED_GILDED_OAK_LOG, ModBlocks.STRIPPED_GILDED_OAK_WOOD);

        getOrCreateTagBuilder(ModTags.Blocks.FRUIT_LEAVES)
                .add(Blocks.SPRUCE_LEAVES, Blocks.BIRCH_LEAVES,
                        Blocks.ACACIA_LEAVES, Blocks.JUNGLE_LEAVES,
                        Blocks.DARK_OAK_LEAVES, Blocks.MANGROVE_LEAVES,
                        Blocks.CHERRY_LEAVES,
                        Blocks.AZALEA_LEAVES, Blocks.FLOWERING_AZALEA_LEAVES,
                        Blocks.RED_MUSHROOM_BLOCK, Blocks.BROWN_MUSHROOM_BLOCK);

        getOrCreateTagBuilder(BlockTags.FLOWERS)
                .add(ModBlocks.CLOVER);

    }
}
