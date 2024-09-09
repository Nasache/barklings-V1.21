package net.nathan.forest_dwellers.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.block.ModBlocks;
import net.nathan.forest_dwellers.item.ModItems;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {


        blockStateModelGenerator.registerFlowerbed(ModBlocks.CLOVER);

        blockStateModelGenerator.registerLantern(ModBlocks.LIVING_LANTERN);

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.STRAWBERRY_BUSH).coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> {
            return BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.STRAWBERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross));
        })));

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.GRAPE_BUSH).coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> {
            return BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.GRAPE_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross));
        })));

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.BLUEBERRY_BUSH).coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> {
            return BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.BLUEBERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross));
        })));



    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.CHERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.BANANA, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORANGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.POMEGRANATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.STARFRUIT, Models.GENERATED);

        itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAPES, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLUEBERRY, Models.GENERATED);

        itemModelGenerator.register(ModItems.HIDDEN_VOICES_MUSIC_DISC, Models.GENERATED);

        itemModelGenerator.register(ModItems.DWELLER_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}
