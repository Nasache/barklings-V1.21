package net.nathan.nathansbiomes.entity;


import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.entity.custom.IceologerEntity;
import net.nathan.nathansbiomes.entity.custom.MagicSnowballEntity;

public class ModEntities {

    public static final EntityType<IceologerEntity> ICEOLOGER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(NathansBiomes.MOD_ID, "iceologer"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, IceologerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 2f)).build());


    public static void registerModEntities() {
        NathansBiomes.LOGGER.info("Registering Mod Entities for " + NathansBiomes.MOD_ID);

    }
}
