package net.nathan.barklings.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.barklings.BarklingsMain;
import net.nathan.barklings.block.ModBlocks;

public class ModBlockEntities {
    public static final BlockEntityType<ModSignBlockEntity> MOD_SIGN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(BarklingsMain.MOD_ID, "mod_sign_entity"),
            BlockEntityType.Builder.create(ModSignBlockEntity::new,
                    ModBlocks.GILDED_OAK_SIGN, ModBlocks.GILDED_OAK_WALL_SIGN).build());

    public static final BlockEntityType<ModHangingSignBlockEntity> MOD_HANGING_SIGN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(BarklingsMain.MOD_ID, "mod_hanging_sign_entity"),
            BlockEntityType.Builder.create(ModHangingSignBlockEntity::new,
                    ModBlocks.GILDED_OAK_HANGING_SIGN, ModBlocks.GILDED_OAK_WALL_HANGING_SIGN).build());




    public static void registerBlockEntities() {
        BarklingsMain.LOGGER.info("Registering Block Entities for " + BarklingsMain.MOD_ID);
    }
}
