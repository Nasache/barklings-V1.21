package net.nathan.barklings.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nathan.barklings.BarklingsMain;
import net.nathan.barklings.block.ModBlocks;

public class ModItemGroup {

    public static final ItemGroup BARKLINGS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(BarklingsMain.MOD_ID, "barklings"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.barklings"))
                    .icon(() -> new ItemStack(ModItems.WAX_PEAR)).entries((displayContext, entries) -> {

                        entries.add(ModItems.BARKLING_SPAWN_EGG);

                        entries.add(ModBlocks.GILDED_OAK_LOG);
                        entries.add(ModBlocks.GILDED_OAK_WOOD);
                        entries.add(ModBlocks.STRIPPED_GILDED_OAK_LOG);
                        entries.add(ModBlocks.STRIPPED_GILDED_OAK_WOOD);
                        entries.add(ModBlocks.GILDED_OAK_PLANKS);
                        entries.add(ModBlocks.GILDED_OAK_STAIRS);
                        entries.add(ModBlocks.GILDED_OAK_SLAB);
                        entries.add(ModBlocks.GILDED_OAK_PRESSURE_PLATE);
                        entries.add(ModBlocks.GILDED_OAK_BUTTON);
                        entries.add(ModBlocks.GILDED_OAK_FENCE);
                        entries.add(ModBlocks.GILDED_OAK_FENCE_GATE);
                        entries.add(ModBlocks.GILDED_OAK_DOOR);
                        entries.add(ModBlocks.GILDED_OAK_TRAPDOOR);
                        entries.add(ModItems.GILDED_OAK_SIGN);
                        entries.add(ModItems.GILDED_OAK_HANGING_SIGN);
                        entries.add(ModItems.GILDED_OAK_BOAT);
                        entries.add(ModItems.GILDED_OAK_CHEST_BOAT);
                        entries.add(ModBlocks.GILDED_OAK_SAPLING);
                        entries.add(ModBlocks.GILDED_OAK_LEAVES);

                        entries.add(ModBlocks.CLOVER_CARPET);
                        entries.add(ModItems.HIDDEN_VOICES_MUSIC_DISC);

                        entries.add(ModBlocks.LIVING_LANTERN);

                        entries.add(ModItems.WAX_PEAR);
                        entries.add(ModItems.SNOWY_PLUM);
                        entries.add(ModItems.VIRANA);
                        entries.add(ModItems.SOLIND);
                        entries.add(ModItems.NOCTURNATE);
                        entries.add(ModItems.MARSH_STAR);
                        entries.add(ModItems.AURORA_CHERRIES);

                        entries.add(ModItems.FLOWERING_LYCHEE);

                        entries.add(ModItems.RED_CAPAYA);
                        entries.add(ModItems.BROWN_SPORSIMMON);
                        entries.add(ModItems.CRIMSON_DURIAN);
                        entries.add(ModItems.WARPED_MANGO);

                        entries.add(ModItems.BLOOM_BERRY);
                        entries.add(ModItems.DUSK_BERRY);
                        entries.add(ModItems.DRY_BERRIES);

                    }).build());

    public static void registerItemGroups() {
    }
}
