package net.nathan.forest_dwellers;

import net.fabricmc.api.ModInitializer;

import net.nathan.forest_dwellers.block.ModBlocks;
import net.nathan.forest_dwellers.block.entity.ModBlockEntities;
import net.nathan.forest_dwellers.entity.ModBoats;
import net.nathan.forest_dwellers.entity.ModEntities;
import net.nathan.forest_dwellers.item.ModItemGroup;
import net.nathan.forest_dwellers.item.ModItems;
import net.nathan.forest_dwellers.sound.ModSounds;
import net.nathan.forest_dwellers.util.ModRegistries;
import net.nathan.forest_dwellers.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForestDwellersMain implements ModInitializer {
	public static final String MOD_ID = "forest_dwellers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();

		ModBlocks.registerModBlocks();

		ModRegistries.registerModStuffs();

		ModEntities.registerModEntities();

		ModWorldGeneration.generateModWorldGeneration();

		ModSounds.registerSounds();

		ModBlockEntities.registerBlockEntities();
		ModBoats.registerBoats();
	}
}