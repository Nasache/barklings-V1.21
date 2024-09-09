package net.nathan.forest_dwellers.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.ComposterBlock;
import net.nathan.forest_dwellers.block.ModBlocks;
import net.nathan.forest_dwellers.entity.ModEntities;
import net.nathan.forest_dwellers.entity.custom.DwellerEntity;
import net.nathan.forest_dwellers.item.ModItems;

public class ModRegistries {
    public static void registerModStuffs() {
        registerModCompostables();
        registerModFlammables();
        registerAttributes();
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
    }

    private static void registerModFlammables() {
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.DWELLER, DwellerEntity.createDwellerAttributes());
    }
}
