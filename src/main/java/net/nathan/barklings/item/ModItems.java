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


    public static final Item CHERRIES = registerItem("cherries",
            new Item(new Item.Settings().food(ModFoodComponents.CHERRIES)));
    public static final Item BANANA = registerItem("banana",
            new Item(new Item.Settings().food(ModFoodComponents.BANANA)));
    public static final Item ORANGE = registerItem("orange",
            new Item(new Item.Settings().food(ModFoodComponents.ORANGE)));
    public static final Item POMEGRANATE = registerItem("pomegranate",
            new Item(new Item.Settings().food(ModFoodComponents.POMEGRANATE)));
    public static final Item PEAR = registerItem("pear",
            new Item(new Item.Settings().food(ModFoodComponents.PEAR)));
    public static final Item PLUM = registerItem("plum",
            new Item(new Item.Settings().food(ModFoodComponents.PLUM)));
    public static final Item STARFRUIT = registerItem("starfruit",
            new Item(new Item.Settings().food(ModFoodComponents.STARFRUIT)));

    public static final Item WARPED_MANGO = registerItem("warped_mango",
            new Item(new Item.Settings().food(ModFoodComponents.WARPED_MANGO)));
    public static final Item CRIMSON_DURIAN = registerItem("crimson_durian",
            new Item(new Item.Settings().food(ModFoodComponents.CRIMSON_DURIAN)));

    public static final Item STRAWBERRY = registerItem("strawberry",
            new AliasedBlockItem(ModBlocks.STRAWBERRY_BUSH, new Item.Settings().food(ModFoodComponents.STRAWBERRY)));
    public static final Item GRAPES = registerItem("grapes",
            new AliasedBlockItem(ModBlocks.GRAPE_BUSH, new Item.Settings().food(ModFoodComponents.GRAPES)));
    public static final Item BLUEBERRY = registerItem("blueberry",
            new AliasedBlockItem(ModBlocks.BLUEBERRY_BUSH, new Item.Settings().food(ModFoodComponents.BLUEBERRY)));


    public static final Item HIDDEN_VOICES_MUSIC_DISC = registerItem("hidden_voices_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.HIDDEN_VOICES_KEY).maxCount(1)));


    public static final Item BARKLING_SPAWN_EGG = registerItem("barkling_spawn_egg",
            new SpawnEggItem(ModEntities.BARKLING, 0x745a36, 0xc29d62, new Item.Settings()));

    public static final Item GILDED_OAK_SAPLING = registerItem("gilded_oak_sapling",
            new AliasedBlockItem(ModBlocks.GILDED_OAK_SAPLING, new Item.Settings()));

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
