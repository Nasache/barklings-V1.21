package net.nathan.forest_dwellers.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;
import net.nathan.forest_dwellers.entity.custom.DwellerEntity;

public class ModEntities {

    public static final EntityType<DwellerEntity> DWELLER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(ForestDwellersMain.MOD_ID, "dweller"),
            EntityType.Builder.create(DwellerEntity::new, SpawnGroup.CREATURE).dimensions(0.7f, 0.9f).build());


    public static void registerModEntities() {
        ForestDwellersMain.LOGGER.info("Registering Mod Entities for " + ForestDwellersMain.MOD_ID);
    }
}
