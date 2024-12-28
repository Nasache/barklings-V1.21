package net.nathan.barklings;

import net.fabricmc.api.ModInitializer;

import net.nathan.barklings.block.ModBlocks;
import net.nathan.barklings.block.entity.ModBlockEntities;
import net.nathan.barklings.entity.ModBoats;
import net.nathan.barklings.entity.ModEntities;
import net.nathan.barklings.item.ModItemGroup;
import net.nathan.barklings.item.ModItems;
import net.nathan.barklings.sound.ModSounds;
import net.nathan.barklings.util.ModLootTableModifiers;
import net.nathan.barklings.util.ModRegistries;
import net.nathan.barklings.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BarklingsMain implements ModInitializer {
	public static final String MOD_ID = "barklings";
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

		ModLootTableModifiers.modifyLootTables();
	}
}