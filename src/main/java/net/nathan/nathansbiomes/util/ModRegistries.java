package net.nathan.nathansbiomes.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.ComposterBlock;
import net.nathan.nathansbiomes.block.ModBlocks;
import net.nathan.nathansbiomes.entity.ModEntities;
import net.nathan.nathansbiomes.entity.custom.IceologerEntity;

public class ModRegistries {
    public static void registerModStuffs() {
        registerModCompostables();
        registerModFlammables();
        registerAttributes();
    }


    private static void registerModCompostables () {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.SNOWDROP, 0.3f);

    }

    private static void registerModFlammables() {

    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.ICEOLOGER, IceologerEntity.createIceologerAttributes());
    }
}
