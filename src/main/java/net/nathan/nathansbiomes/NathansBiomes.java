package net.nathan.nathansbiomes;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.nathan.nathansbiomes.block.ModBlocks;
import net.nathan.nathansbiomes.entity.ModEntities;
import net.nathan.nathansbiomes.entity.custom.IceologerEntity;
import net.nathan.nathansbiomes.item.ModItemGroup;
import net.nathan.nathansbiomes.item.ModItems;
import net.nathan.nathansbiomes.util.ModLootTableModifiers;
import net.nathan.nathansbiomes.util.ModRegistries;
import net.nathan.nathansbiomes.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NathansBiomes implements ModInitializer {
	public static final String MOD_ID = "nathansbiomes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();

		ModItems.registerModItems();

		ModBlocks.registerModBlocks();

		ModRegistries.registerModStuffs();

		ModLootTableModifiers.modifyLootTables();

		ModWorldGeneration.generateModWorldGeneration();

		ModEntities.registerModEntities();



		FabricDefaultAttributeRegistry.register(ModEntities.ICEOLOGER, IceologerEntity.createIceologerAttributes());


	}
}