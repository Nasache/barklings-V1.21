package net.nathan.nathansbiomes.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamilies;
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

        BlockStateModelGenerator.BlockTexturePool starshroomPlanksTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.STARSHROOM_PLANKS);
        starshroomPlanksTexturePool.stairs(ModBlocks.STARSHROOM_STAIRS);
        starshroomPlanksTexturePool.slab(ModBlocks.STARSHROOM_SLAB);
        starshroomPlanksTexturePool.button(ModBlocks.STARSHROOM_BUTTON);
        starshroomPlanksTexturePool.pressurePlate(ModBlocks.STARSHROOM_PRESSURE_PLATE);
        starshroomPlanksTexturePool.fence(ModBlocks.STARSHROOM_FENCE);
        starshroomPlanksTexturePool.fenceGate(ModBlocks.STARSHROOM_FENCE_GATE);
        blockStateModelGenerator.registerDoor(ModBlocks.STARSHROOM_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.STARSHROOM_TRAPDOOR);

        //starshroomPlanksTexturePool.family(BlockFamilies.register(ModBlocks.STARSHROOM_PLANKS).sign(ModBlocks.STARSHROOM_SIGN, ModBlocks.STARSHROOM_WALL_SIGN).build());
//
        //blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_STARSHROOM_STEM, ModBlocks.STARSHROOM_HANGING_SIGN, ModBlocks.STARSHROOM_WALL_HANGING_SIGN);

        blockStateModelGenerator.registerLog(ModBlocks.STARSHROOM_STEM).log(ModBlocks.STARSHROOM_STEM).wood(ModBlocks.STARSHROOM_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_STARSHROOM_STEM).log(ModBlocks.STRIPPED_STARSHROOM_STEM).wood(ModBlocks.STRIPPED_STARSHROOM_WOOD);




        BlockStateModelGenerator.BlockTexturePool snowBricksTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SNOW_BRICKS);
        snowBricksTexturePool.stairs(ModBlocks.SNOW_BRICK_STAIRS);
        snowBricksTexturePool.slab(ModBlocks.SNOW_BRICK_SLAB);
        snowBricksTexturePool.button(ModBlocks.SNOW_BRICK_BUTTON);
        snowBricksTexturePool.pressurePlate(ModBlocks.SNOW_BRICK_PRESSURE_PLATE);
        snowBricksTexturePool.wall(ModBlocks.SNOW_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_SNOW_BRICKS);


        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.SNOWDROP, ModBlocks.POTTED_SNOWDROP, NOT_TINTED);

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.BLUE_STARSHROOM, ModBlocks.POTTED_BLUE_STARSHROOM, NOT_TINTED);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUE_STARSHROOM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GREEN_STARSHROOM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURPLE_STARSHROOM_BLOCK);


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
