package net.nathan.barklings.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.nathan.barklings.block.ModBlocks;
import net.nathan.barklings.item.ModItems;
import net.nathan.barklings.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {

    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerPlanksRecipe(exporter, ModBlocks.GILDED_OAK_PLANKS, ModTags.Items.GILDED_OAK_LOGS, 4);

        offerBarkBlockRecipe(exporter, ModBlocks.GILDED_OAK_WOOD, ModBlocks.GILDED_OAK_LOG);
        offerBarkBlockRecipe(exporter, ModBlocks.STRIPPED_GILDED_OAK_WOOD, ModBlocks.STRIPPED_GILDED_OAK_LOG);

        createStairsRecipe(ModBlocks.GILDED_OAK_STAIRS, Ingredient.ofItems(ModBlocks.GILDED_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.GILDED_OAK_PLANKS), conditionsFromItem(ModBlocks.GILDED_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GILDED_OAK_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GILDED_OAK_SLAB, Ingredient.ofItems(ModBlocks.GILDED_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.GILDED_OAK_PLANKS), conditionsFromItem(ModBlocks.GILDED_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GILDED_OAK_SLAB)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.GILDED_OAK_BUTTON)
                .input(ModBlocks.GILDED_OAK_PLANKS)
                .criterion(hasItem(ModBlocks.GILDED_OAK_PLANKS), conditionsFromItem(ModBlocks.GILDED_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GILDED_OAK_BUTTON)));

        createFenceRecipe(ModBlocks.GILDED_OAK_FENCE, Ingredient.ofItems(ModBlocks.GILDED_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.GILDED_OAK_PLANKS), conditionsFromItem(ModBlocks.GILDED_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GILDED_OAK_FENCE)));
        createFenceGateRecipe(ModBlocks.GILDED_OAK_FENCE_GATE, Ingredient.ofItems(ModBlocks.GILDED_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.GILDED_OAK_PLANKS), conditionsFromItem(ModBlocks.GILDED_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GILDED_OAK_FENCE_GATE)));

        createDoorRecipe(ModBlocks.GILDED_OAK_DOOR, Ingredient.ofItems(ModBlocks.GILDED_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.GILDED_OAK_PLANKS), conditionsFromItem(ModBlocks.GILDED_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GILDED_OAK_DOOR)));
        createTrapdoorRecipe(ModBlocks.GILDED_OAK_TRAPDOOR, Ingredient.ofItems(ModBlocks.GILDED_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.GILDED_OAK_PLANKS), conditionsFromItem(ModBlocks.GILDED_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GILDED_OAK_TRAPDOOR)));

        offerPressurePlateRecipe(exporter, ModBlocks.GILDED_OAK_PRESSURE_PLATE, ModBlocks.GILDED_OAK_PLANKS);

        offerHangingSignRecipe(exporter, ModItems.GILDED_OAK_HANGING_SIGN, ModBlocks.STRIPPED_GILDED_OAK_LOG);
        createSignRecipe(ModItems.GILDED_OAK_SIGN, Ingredient.ofItems(ModBlocks.GILDED_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.GILDED_OAK_PLANKS), conditionsFromItem(ModBlocks.GILDED_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.GILDED_OAK_SIGN)));

        offerBoatRecipe(exporter, ModItems.GILDED_OAK_BOAT, ModBlocks.GILDED_OAK_PLANKS);
        offerChestBoatRecipe(exporter, ModItems.GILDED_OAK_CHEST_BOAT, ModItems.GILDED_OAK_BOAT);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LIVING_LANTERN)
                .pattern("#L#")
                .pattern("LTL")
                .pattern("#L#")
                .input('#', Ingredient.fromTag(ItemTags.LEAVES))
                .input('L', Ingredient.fromTag(ItemTags.LOGS))
                .input('T', Items.TORCH)
                .criterion(hasItem(Items.TORCH), conditionsFromItem(Items.TORCH))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Items.LIME_DYE)
                .input(ModBlocks.CLOVER)
                .criterion(hasItem(ModBlocks.CLOVER), conditionsFromItem(ModBlocks.CLOVER))
                .offerTo(exporter);
    }
}
