package net.nathan.nathansbiomes.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.block.custom.FallingIceBlock;

import static net.minecraft.block.Blocks.ICE;
public class ModBlocks {

    public static final Block FALLING_ICE_BLOCK = registerBlockWithoutBlockItem("falling_ice_block",
            new FallingIceBlock(AbstractBlock.Settings.copy(ICE)));



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
