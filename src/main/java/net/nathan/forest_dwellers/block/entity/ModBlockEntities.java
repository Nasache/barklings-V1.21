package net.nathan.forest_dwellers.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;
import net.nathan.forest_dwellers.block.ModBlocks;

public class ModBlockEntities {
    public static final BlockEntityType<ModSignBlockEntity> MOD_SIGN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(ForestDwellersMain.MOD_ID, "mod_sign_entity"),
            FabricBlockEntityTypeBuilder.create(ModSignBlockEntity::new,
                    ModBlocks.GILDED_OAK_SIGN, ModBlocks.GILDED_OAK_WALL_SIGN).build());

    public static final BlockEntityType<ModHangingSignBlockEntity> MOD_HANGING_SIGN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(ForestDwellersMain.MOD_ID, "mod_hanging_sign_entity"),
            FabricBlockEntityTypeBuilder.create(ModHangingSignBlockEntity::new,
                    ModBlocks.GILDED_OAK_HANGING_SIGN, ModBlocks.GILDED_OAK_WALL_HANGING_SIGN).build());


    public static void registerBlockEntities() {
        ForestDwellersMain.LOGGER.info("Registering Block Entities for " + ForestDwellersMain.MOD_ID);
    }
}
