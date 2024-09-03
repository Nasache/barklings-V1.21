package net.nathan.forest_dwellers;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.nathan.forest_dwellers.block.ModBlocks;
import net.nathan.forest_dwellers.effect.ModEffects;
import net.nathan.forest_dwellers.entity.ModEntities;
import net.nathan.forest_dwellers.entity.custom.DwellerEntity;
import net.nathan.forest_dwellers.item.ModItemGroup;
import net.nathan.forest_dwellers.item.ModItems;
import net.nathan.forest_dwellers.util.ModLootTableModifiers;
import net.nathan.forest_dwellers.util.ModRegistries;
import net.nathan.forest_dwellers.world.gen.ModWorldGeneration;
import net.nathan.forest_dwellers.world.tree.ModFoliagePlacerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForestDwellersMain implements ModInitializer {
	public static final String MOD_ID = "forest_dwellers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEffects.registerEffects();

		ModItemGroup.registerItemGroups();

		ModItems.registerModItems();

		ModBlocks.registerModBlocks();

		ModRegistries.registerModStuffs();

		ModLootTableModifiers.modifyLootTables();

		ModFoliagePlacerTypes.register();

		ModWorldGeneration.generateModWorldGeneration();

		ModEntities.registerModEntities();


		FabricDefaultAttributeRegistry.register(ModEntities.DWELLER, DwellerEntity.createDwellerAttributes());
	}
}