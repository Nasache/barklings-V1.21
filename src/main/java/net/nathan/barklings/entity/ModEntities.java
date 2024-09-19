package net.nathan.barklings.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.barklings.BarklingsMain;
import net.nathan.barklings.entity.custom.BarklingEntity;

public class ModEntities {

    public static final EntityType<BarklingEntity> BARKLING = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(BarklingsMain.MOD_ID, "barkling"),
            EntityType.Builder.create(BarklingEntity::new, SpawnGroup.CREATURE).dimensions(0.7f, 0.9f).build());


    public static void registerModEntities() {
        BarklingsMain.LOGGER.info("Registering Mod Entities for " + BarklingsMain.MOD_ID);
    }
}
