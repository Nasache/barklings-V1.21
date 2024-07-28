package net.nathan.nathansbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.block.ModBlocks;


import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {

    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICKS)
                .pattern("SS")
                .pattern("SS")
                .input('S', Blocks.SNOW_BLOCK)
                .criterion(hasItem(Blocks.SNOW_BLOCK), conditionsFromItem(Blocks.SNOW_BLOCK))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.SNOW_BRICKS)));

        createStairsRecipe(ModBlocks.SNOW_BRICK_STAIRS, Ingredient.ofItems(ModBlocks.SNOW_BRICKS))
                .criterion(hasItem(ModBlocks.SNOW_BRICKS), conditionsFromItem(ModBlocks.SNOW_BRICKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.SNOW_BRICK_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICK_SLAB, Ingredient.ofItems(ModBlocks.SNOW_BRICKS))
                .criterion(hasItem(ModBlocks.SNOW_BRICKS), conditionsFromItem(ModBlocks.SNOW_BRICKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.SNOW_BRICK_SLAB)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.SNOW_BRICK_BUTTON)
                .input(ModBlocks.SNOW_BRICKS)
                .criterion(hasItem(ModBlocks.SNOW_BRICKS), conditionsFromItem(ModBlocks.SNOW_BRICKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.SNOW_BRICK_BUTTON)));
        offerPressurePlateRecipe(exporter, ModBlocks.SNOW_BRICK_PRESSURE_PLATE, ModBlocks.SNOW_BRICKS);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICK_WALL, ModBlocks.SNOW_BRICKS);

        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SNOW_BRICKS, ModBlocks.SNOW_BRICK_SLAB);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SNOW_BRICKS, ModBlocks.SNOW_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICK_STAIRS, ModBlocks.SNOW_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICK_SLAB, ModBlocks.SNOW_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICK_WALL, ModBlocks.SNOW_BRICKS);



    }
}
