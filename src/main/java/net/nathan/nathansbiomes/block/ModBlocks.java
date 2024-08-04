package net.nathan.nathansbiomes.block;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.block.custom.FallingIceBlock;
import net.nathan.nathansbiomes.world.tree.ModSaplingGenerators;

public class ModBlocks {


    public static final Block SNOW_GRASS_BLOCK = registerBlock("snow_grass_block",
            new GrassBlock(AbstractBlock.Settings.copy(Blocks.ROOTED_DIRT).strength(0.6F)));

    public static final Block BLUE_STARSHROOM_BLOCK = registerBlock("blue_starshroom_block",
            new TranslucentBlock(AbstractBlock.Settings.copy(Blocks.WARPED_HYPHAE).nonOpaque().luminance((state) -> {
                return 7;
            })));



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


    public static final Block SNOWDROP = registerBlock("snowdrop",
            new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 4, AbstractBlock.Settings.copy(Blocks.OXEYE_DAISY).mapColor(MapColor.WHITE)));
    public static final Block POTTED_SNOWDROP = registerBlockWithoutBlockItem("potted_snowdrop",
            new FlowerPotBlock(SNOWDROP, AbstractBlock.Settings.copy(Blocks.POTTED_OXEYE_DAISY).mapColor(MapColor.WHITE)));


    public static final Block WINTER_OAK_SAPLING = registerBlockWithoutBlockItem("winter_oak_sapling",
            new SaplingBlock(ModSaplingGenerators.WINTER_OAK, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));



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
