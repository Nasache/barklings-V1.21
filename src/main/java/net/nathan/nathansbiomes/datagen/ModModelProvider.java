package net.nathan.nathansbiomes.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.block.ModBlocks;
import net.nathan.nathansbiomes.item.ModItems;


import java.util.Optional;

import static net.minecraft.data.client.BlockStateModelGenerator.TintType.NOT_TINTED;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {


        BlockStateModelGenerator.BlockTexturePool snowBricksTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SNOW_BRICKS);
        snowBricksTexturePool.stairs(ModBlocks.SNOW_BRICK_STAIRS);
        snowBricksTexturePool.slab(ModBlocks.SNOW_BRICK_SLAB);
        snowBricksTexturePool.button(ModBlocks.SNOW_BRICK_BUTTON);
        snowBricksTexturePool.pressurePlate(ModBlocks.SNOW_BRICK_PRESSURE_PLATE);
        snowBricksTexturePool.wall(ModBlocks.SNOW_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_SNOW_BRICKS);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.ICE_WAND, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BROKEN_ICE_WAND, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGIC_SNOWBALL, Models.GENERATED);

        itemModelGenerator.register(ModItems.ICEOLOGER_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}
