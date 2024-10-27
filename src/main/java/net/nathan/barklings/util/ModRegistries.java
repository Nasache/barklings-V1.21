package net.nathan.barklings.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.ComposterBlock;
import net.nathan.barklings.block.ModBlocks;
import net.nathan.barklings.entity.ModEntities;
import net.nathan.barklings.entity.custom.BarklingEntity;
import net.nathan.barklings.item.ModItems;

public class ModRegistries {
    public static void registerModStuffs() {
        registerModCompostables();
        registerFlammables();
        registerAttributes();
        registerFuels();
        registerStrippables();
    }

    private static void registerFuels() {
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModBlocks.GILDED_OAK_PLANKS, 300);
        registry.add(ModBlocks.GILDED_OAK_STAIRS, 300);
        registry.add(ModBlocks.GILDED_OAK_SLAB, 150);
        registry.add(ModBlocks.GILDED_OAK_BUTTON, 100);
        registry.add(ModBlocks.GILDED_OAK_PRESSURE_PLATE, 300);
        registry.add(ModBlocks.GILDED_OAK_FENCE, 300);
        registry.add(ModBlocks.GILDED_OAK_FENCE_GATE, 300);
        registry.add(ModBlocks.GILDED_OAK_DOOR, 200);
        registry.add(ModBlocks.GILDED_OAK_TRAPDOOR, 300);
        registry.add(ModItems.GILDED_OAK_BOAT, 1200);
        registry.add(ModItems.GILDED_OAK_CHEST_BOAT, 1200);
        registry.add(ModItems.GILDED_OAK_SIGN, 200);
        registry.add(ModItems.GILDED_OAK_HANGING_SIGN, 800);
        registry.add(ModBlocks.GILDED_OAK_SAPLING, 100);
    }

    private static void registerModCompostables () {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CHERRIES, 0.65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BANANA, 0.65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ORANGE, 0.65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.POMEGRANATE, 0.65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.PEAR, 0.65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.PLUM, 0.65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.STARFRUIT, 0.65f);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BLUEBERRY, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.STRAWBERRY, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.GRAPES, 0.3f);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.CLOVER.asItem(), 0.3f);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.GILDED_OAK_SAPLING, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.GILDED_OAK_LEAVES, 0.3f);

    }

    private static void registerFlammables() {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GILDED_OAK_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GILDED_OAK_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_GILDED_OAK_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_GILDED_OAK_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GILDED_OAK_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GILDED_OAK_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GILDED_OAK_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GILDED_OAK_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GILDED_OAK_FENCE_GATE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GILDED_OAK_LEAVES, 30, 60);
    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.GILDED_OAK_LOG, ModBlocks.STRIPPED_GILDED_OAK_LOG);
        StrippableBlockRegistry.register(ModBlocks.GILDED_OAK_WOOD, ModBlocks.STRIPPED_GILDED_OAK_WOOD);
        
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.BARKLING, BarklingEntity.createBarklingAttributes());
    }
}
