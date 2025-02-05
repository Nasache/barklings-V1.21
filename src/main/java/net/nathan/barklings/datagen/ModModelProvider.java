package net.nathan.barklings.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.nathan.barklings.block.ModBlocks;
import net.nathan.barklings.item.ModItems;

import java.util.Optional;

import static net.minecraft.data.client.BlockStateModelGenerator.TintType.NOT_TINTED;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        blockStateModelGenerator.registerLantern(ModBlocks.LIVING_LANTERN);

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.BLOOM_BERRY_BUSH).coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> {
            return BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.BLOOM_BERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross));
        })));

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.DRY_BERRY_BUSH).coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> {
            return BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.DRY_BERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross));
        })));

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.DUSK_BERRY_BUSH).coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> {
            return BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.DUSK_BERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross));
        })));


        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.WARPED_MANGO_POD).coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> {
            return BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.WARPED_MANGO_POD, "_stage" + stage, Models.CROSS, TextureMap::cross));
        })));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.CRIMSON_DURIAN_POD).coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> {
            return BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.CRIMSON_DURIAN_POD, "_stage" + stage, Models.CROSS, TextureMap::cross));
        })));


        BlockStateModelGenerator.BlockTexturePool gildedOakPlanksTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GILDED_OAK_PLANKS);
        gildedOakPlanksTexturePool.stairs(ModBlocks.GILDED_OAK_STAIRS);
        gildedOakPlanksTexturePool.slab(ModBlocks.GILDED_OAK_SLAB);
        gildedOakPlanksTexturePool.button(ModBlocks.GILDED_OAK_BUTTON);
        gildedOakPlanksTexturePool.pressurePlate(ModBlocks.GILDED_OAK_PRESSURE_PLATE);
        gildedOakPlanksTexturePool.fence(ModBlocks.GILDED_OAK_FENCE);
        gildedOakPlanksTexturePool.fenceGate(ModBlocks.GILDED_OAK_FENCE_GATE);
        blockStateModelGenerator.registerDoor(ModBlocks.GILDED_OAK_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.GILDED_OAK_TRAPDOOR);

        gildedOakPlanksTexturePool.family(BlockFamilies.register(ModBlocks.GILDED_OAK_PLANKS).sign(ModBlocks.GILDED_OAK_SIGN, ModBlocks.GILDED_OAK_WALL_SIGN).build());

        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_GILDED_OAK_LOG, ModBlocks.GILDED_OAK_HANGING_SIGN, ModBlocks.GILDED_OAK_WALL_HANGING_SIGN);

        blockStateModelGenerator.registerLog(ModBlocks.GILDED_OAK_LOG).log(ModBlocks.GILDED_OAK_LOG).wood(ModBlocks.GILDED_OAK_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_GILDED_OAK_LOG).log(ModBlocks.STRIPPED_GILDED_OAK_LOG).wood(ModBlocks.STRIPPED_GILDED_OAK_WOOD);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GILDED_OAK_LEAVES);

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.GILDED_OAK_SAPLING, ModBlocks.POTTED_GILDED_OAK_SAPLING, NOT_TINTED);
        
        
        

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.AURORA_CHERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.VIRANA, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOLIND, Models.GENERATED);
        itemModelGenerator.register(ModItems.NOCTURNATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WAX_PEAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNOWY_PLUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.MARSH_STAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARPED_MANGO, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_DURIAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.RED_CAPAYA, Models.GENERATED);
        itemModelGenerator.register(ModItems.BROWN_SPORSIMMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLOWERING_LYCHEE, Models.GENERATED);

        itemModelGenerator.register(ModItems.BLOOM_BERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRY_BERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUSK_BERRY, Models.GENERATED);

        itemModelGenerator.register(ModItems.HIDDEN_VOICES_MUSIC_DISC, Models.GENERATED);

        itemModelGenerator.register(ModItems.GILDED_OAK_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.GILDED_OAK_CHEST_BOAT, Models.GENERATED);

        itemModelGenerator.register(ModItems.BARKLING_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}
