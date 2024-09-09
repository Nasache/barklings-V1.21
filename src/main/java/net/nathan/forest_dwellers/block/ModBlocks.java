package net.nathan.forest_dwellers.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;
import net.nathan.forest_dwellers.block.custom.BlueberryBush;
import net.nathan.forest_dwellers.block.custom.GrapeBush;
import net.nathan.forest_dwellers.block.custom.LivingLanternBlock;
import net.nathan.forest_dwellers.block.custom.StrawberryBush;

public class ModBlocks {


    public static final Block STRAWBERRY_BUSH = registerBlockWithoutBlockItem("strawberry_bush",
            new StrawberryBush(AbstractBlock.Settings.create().mapColor(MapColor.LIME).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block GRAPE_BUSH = registerBlockWithoutBlockItem("grape_bush",
            new GrapeBush(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block BLUEBERRY_BUSH = registerBlockWithoutBlockItem("blueberry_bush",
            new BlueberryBush(AbstractBlock.Settings.create().mapColor(MapColor.LIME).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block CLOVER = registerBlock("clover",
            new FlowerbedBlock(AbstractBlock.Settings.copy(Blocks.PINK_PETALS)));

    public static final Block LIVING_LANTERN = registerBlock("living_lantern",
            new LivingLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).mapColor(MapColor.BROWN).sounds(BlockSoundGroup.WOOD).luminance((state) -> {
                return 12;})));


    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(ForestDwellersMain.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ForestDwellersMain.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(ForestDwellersMain.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        ForestDwellersMain.LOGGER.info("Registering ModBlocks for " + ForestDwellersMain.MOD_ID);
    }
}
