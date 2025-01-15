package net.nathan.barklings.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.nathan.barklings.BarklingsMain;

public class ModTags {
    public static class Blocks{

        public static final TagKey<Block> GILDED_OAK_LOGS = TagKey.of(RegistryKeys.BLOCK, Identifier.of("barklings", "gilded_oak_logs"));

        public static final TagKey<Block> FRUIT_LEAVES = TagKey.of(RegistryKeys.BLOCK, Identifier.of("barklings", "fruit_leaves"));


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(BarklingsMain.MOD_ID, name));
        }
    }

    public static class Items {
        public static TagKey<Item> FRUIT =
                createTag("fruit");

        public static final TagKey<Item> GILDED_OAK_LOGS = TagKey.of(RegistryKeys.ITEM, Identifier.of("barklings", "gilded_oak_logs"));

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(BarklingsMain.MOD_ID, name));
        }
    }
}
