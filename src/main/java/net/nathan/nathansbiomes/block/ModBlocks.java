package net.nathan.nathansbiomes.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.block.custom.FallingIceBlock;

public class ModBlocks {


    public static final Block FROZEN_GRASS_BLOCK = registerBlock("frozen_grass_block",
            new GrassBlock(AbstractBlock.Settings.copy(Blocks.ROOTED_DIRT).strength(1.5F, 6.0F)));


    public static final Block FROZEN_DIRT = registerBlock("frozen_dirt",
            new Block(AbstractBlock.Settings.copy(Blocks.ROOTED_DIRT).strength(1.5F, 6.0F)));

    public static final Block SNOW_BRICKS = registerBlock("snow_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block CHISELED_SNOW_BRICKS = registerBlock("chiseled_snow_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final Block SNOW_BRICK_STAIRS = registerBlock("snow_brick_stairs",
            new StairsBlock(ModBlocks.SNOW_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)));
    public static final Block SNOW_BRICK_SLAB = registerBlock("snow_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)));
    public static final Block SNOW_BRICK_BUTTON = registerBlock("snow_brick_button",
            new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(Blocks.STONE_BUTTON)));
    public static final Block SNOW_BRICK_PRESSURE_PLATE = registerBlock("snow_brick_pressure_plate",
            new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(Blocks.STONE_PRESSURE_PLATE)));
    public static final Block SNOW_BRICK_WALL = registerBlock("snow_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)));

    public static final Block FALLING_ICE_BLOCK = registerBlockWithoutBlockItem("falling_ice_block",
            new FallingIceBlock(AbstractBlock.Settings.copy(Blocks.ICE)));



    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(NathansBiomes.MOD_ID, name), block);
    }
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(NathansBiomes.MOD_ID, name), block);
    }


    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(NathansBiomes.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
    public static void registerModBlocks() {
        NathansBiomes.LOGGER.info("Registering ModBlocks for " + NathansBiomes.MOD_ID);
    }
}
