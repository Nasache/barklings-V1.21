package net.nathan.barklings.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.nathan.barklings.BarklingsMain;
import net.nathan.barklings.block.custom.*;
import net.nathan.barklings.util.ModWoodTypes;
import net.nathan.barklings.world.tree.ModSaplingGenerators;

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

    public static final Block GILDED_OAK_PLANKS = registerBlock("gilded_oak_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block GILDED_OAK_STAIRS = registerBlock("gilded_oak_stairs",
            new StairsBlock(ModBlocks.GILDED_OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));
    public static final Block GILDED_OAK_SLAB = registerBlock("gilded_oak_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));
    public static final Block GILDED_OAK_BUTTON = registerBlock("gilded_oak_button",
            new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    public static final Block GILDED_OAK_PRESSURE_PLATE = registerBlock("gilded_oak_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final Block GILDED_OAK_FENCE = registerBlock("gilded_oak_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block GILDED_OAK_FENCE_GATE = registerBlock("gilded_oak_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)));
    public static final Block GILDED_OAK_DOOR = registerBlock("gilded_oak_door",
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    public static final Block GILDED_OAK_TRAPDOOR = registerBlock("gilded_oak_trapdoor",
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));
    public static final Block GILDED_OAK_LOG = registerBlock("gilded_oak_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block GILDED_OAK_WOOD = registerBlock("gilded_oak_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_GILDED_OAK_LOG = registerBlock("stripped_gilded_oak_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_GILDED_OAK_WOOD = registerBlock("stripped_gilded_oak_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block GILDED_OAK_LEAVES = registerBlock("gilded_oak_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.GOLD)));
    public static final Block GILDED_OAK_SAPLING = registerBlock("gilded_oak_sapling",
            new SaplingBlock(ModSaplingGenerators.GILDED_OAK, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).mapColor(MapColor.GOLD)));
    public static final Block POTTED_GILDED_OAK_SAPLING = registerBlockWithoutBlockItem("potted_gilded_oak_sapling",
            new FlowerPotBlock(GILDED_OAK_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING).mapColor(MapColor.GOLD)));

    public static final Block GILDED_OAK_SIGN = registerBlockWithoutBlockItem("gilded_oak_sign",
            new ModStandingSignBlock(ModWoodTypes.GILDED_OAK, AbstractBlock.Settings.copy(Blocks.OAK_SIGN)));
    public static final Block GILDED_OAK_WALL_SIGN = registerBlockWithoutBlockItem("gilded_oak_wall_sign",
            new ModWallSignBlock(ModWoodTypes.GILDED_OAK, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN).dropsLike(GILDED_OAK_SIGN)));
    public static final Block GILDED_OAK_HANGING_SIGN = registerBlockWithoutBlockItem("gilded_oak_hanging_sign",
            new ModHangingSignBlock(ModWoodTypes.GILDED_OAK, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)));
    public static final Block GILDED_OAK_WALL_HANGING_SIGN = registerBlockWithoutBlockItem("gilded_oak_wall_hanging_sign",
            new ModWallHangingSignBlock(ModWoodTypes.GILDED_OAK, AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN).dropsLike(GILDED_OAK_HANGING_SIGN)));


    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(BarklingsMain.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(BarklingsMain.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(BarklingsMain.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        BarklingsMain.LOGGER.info("Registering ModBlocks for " + BarklingsMain.MOD_ID);
    }
}
