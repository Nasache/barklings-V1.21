package net.nathan.forest_dwellers.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;

public class ModTags {
    public static class Blocks{
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(ForestDwellersMain.MOD_ID, name));
        }
    }

    public static class Items {
        public static TagKey<Item> FRUIT =
                createTag("fruit");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(ForestDwellersMain.MOD_ID, name));
        }
    }
}