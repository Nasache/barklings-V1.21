package net.nathan.nathansbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.nathan.nathansbiomes.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.SNOW_BRICKS,
                        ModBlocks.CHISELED_SNOW_BRICKS,
                        ModBlocks.SNOW_BRICK_SLAB,
                        ModBlocks.SNOW_BRICK_STAIRS,
                        ModBlocks.SNOW_BRICK_WALL,
                        ModBlocks.SNOW_BRICK_PRESSURE_PLATE,
                        ModBlocks.SNOW_BRICK_BUTTON
                );

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.SNOW_GRASS_BLOCK
                );

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.BLUE_STARSHROOM_BLOCK,
                        ModBlocks.GREEN_STARSHROOM_BLOCK,
                        ModBlocks.PURPLE_STARSHROOM_BLOCK
                );

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.BLUE_STARSHROOM_BLOCK,
                        ModBlocks.GREEN_STARSHROOM_BLOCK,
                        ModBlocks.PURPLE_STARSHROOM_BLOCK
                );

        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(ModBlocks.SNOW_GRASS_BLOCK
                );

        getOrCreateTagBuilder(BlockTags.FLOWERS)
                .add(ModBlocks.SNOWDROP
                );

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.SNOW_BRICK_WALL);

    }
}
