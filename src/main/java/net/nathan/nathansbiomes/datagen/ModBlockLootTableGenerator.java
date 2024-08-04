package net.nathan.nathansbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.nathan.nathansbiomes.block.ModBlocks;


import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {

    public ModBlockLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        addDrop(ModBlocks.SNOW_GRASS_BLOCK, oreDrops(ModBlocks.SNOW_GRASS_BLOCK, Blocks.DIRT.asItem()));

        addDrop(ModBlocks.SNOW_BRICKS);
        addDrop(ModBlocks.CHISELED_SNOW_BRICKS);
        addDrop(ModBlocks.SNOW_BRICK_STAIRS);
        addDrop(ModBlocks.SNOW_BRICK_SLAB, slabDrops(ModBlocks.SNOW_BRICK_SLAB));
        addDrop(ModBlocks.SNOW_BRICK_BUTTON);
        addDrop(ModBlocks.SNOW_BRICK_PRESSURE_PLATE);
        addDrop(ModBlocks.SNOW_BRICK_WALL);

        addDrop(ModBlocks.SNOWDROP);
        addDrop(ModBlocks.POTTED_SNOWDROP, pottedPlantDrops(ModBlocks.SNOWDROP));

    }
}
