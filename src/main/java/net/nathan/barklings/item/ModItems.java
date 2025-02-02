package net.nathan.barklings.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.barklings.BarklingsMain;
import net.nathan.barklings.block.ModBlocks;
import net.nathan.barklings.entity.ModBoats;
import net.nathan.barklings.entity.ModEntities;
import net.nathan.barklings.sound.ModSounds;

public class ModItems {


    public static final Item AURORA_CHERRIES = registerItem("aurora_cherries",
            new Item(new Item.Settings().food(ModFoodComponents.AURORA_CHERRIES)));
    public static final Item VIRANA = registerItem("virana",
            new Item(new Item.Settings().food(ModFoodComponents.VIRANA)));
    public static final Item SOLIND = registerItem("solind",
            new Item(new Item.Settings().food(ModFoodComponents.SOLIND)));
    public static final Item NOCTURNATE = registerItem("nocturnate",
            new Item(new Item.Settings().food(ModFoodComponents.NOCTURNATE)));
    public static final Item WAX_PEAR = registerItem("wax_pear",
            new Item(new Item.Settings().food(ModFoodComponents.WAX_PEAR)));
    public static final Item SNOWY_PLUM = registerItem("snowy_plum",
            new Item(new Item.Settings().food(ModFoodComponents.SNOWY_PLUM)));
    public static final Item MARSH_STAR = registerItem("marsh_star",
            new Item(new Item.Settings().food(ModFoodComponents.MARSH_STAR)));

    public static final Item WARPED_MANGO = registerItem("warped_mango",
            new AliasedBlockItem(ModBlocks.WARPED_MANGO_POD, new Item.Settings().food(ModFoodComponents.WARPED_MANGO)));
    public static final Item CRIMSON_DURIAN = registerItem("crimson_durian",
            new Item(new Item.Settings().food(ModFoodComponents.CRIMSON_DURIAN)));

    public static final Item RED_CAPAYA = registerItem("red_capaya",
            new Item(new Item.Settings().food(ModFoodComponents.RED_CAPAYA)));
    public static final Item BROWN_SPORSIMMON = registerItem("brown_sporsimmon",
            new Item(new Item.Settings().food(ModFoodComponents.BROWN_SPORSIMMON)));

    public static final Item FLOWERING_LYCHEE = registerItem("flowering_lychee",
            new Item(new Item.Settings().food(ModFoodComponents.FLOWERING_LYCHEE)));

    public static final Item BLOOM_BERRY = registerItem("bloom_berry",
            new AliasedBlockItem(ModBlocks.BLOOM_BERRY_BUSH, new Item.Settings().food(ModFoodComponents.BLOOM_BERRY)));
    public static final Item DRY_BERRIES = registerItem("dry_berries",
            new AliasedBlockItem(ModBlocks.DRY_BERRY_BUSH, new Item.Settings().food(ModFoodComponents.DRY_BERRIES)));
    public static final Item DUSK_BERRY = registerItem("dusk_berry",
            new AliasedBlockItem(ModBlocks.DUSK_BERRY_BUSH, new Item.Settings().food(ModFoodComponents.DUSK_BERRY)));


    public static final Item HIDDEN_VOICES_MUSIC_DISC = registerItem("hidden_voices_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.HIDDEN_VOICES_KEY).maxCount(1)));


    public static final Item BARKLING_SPAWN_EGG = registerItem("barkling_spawn_egg",
            new SpawnEggItem(ModEntities.BARKLING, 0x745a36, 0xc29d62, new Item.Settings()));

    public static final Item GILDED_OAK_SIGN = registerItem("gilded_oak_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.GILDED_OAK_SIGN, ModBlocks.GILDED_OAK_WALL_SIGN));
    public static final Item GILDED_OAK_HANGING_SIGN = registerItem("gilded_oak_hanging_sign",
            new HangingSignItem(ModBlocks.GILDED_OAK_HANGING_SIGN, ModBlocks.GILDED_OAK_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    public static final Item GILDED_OAK_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.GILDED_OAK_BOAT_ID, ModBoats.GILDED_OAK_BOAT_KEY, false);
    public static final Item GILDED_OAK_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.GILDED_OAK_CHEST_BOAT_ID, ModBoats.GILDED_OAK_BOAT_KEY, true);



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(BarklingsMain.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BarklingsMain.LOGGER.info("Registering Mod Items for " + BarklingsMain.MOD_ID);

    }
}
