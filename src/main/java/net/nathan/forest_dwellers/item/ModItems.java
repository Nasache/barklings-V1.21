package net.nathan.forest_dwellers.item;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;
import net.nathan.forest_dwellers.entity.ModEntities;

public class ModItems {


    public static final Item DWELLER_SPAWN_EGG = registerItem("dweller_spawn_egg",
            new SpawnEggItem(ModEntities.DWELLER, 0x745a36, 0xc29d62, new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ForestDwellersMain.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ForestDwellersMain.LOGGER.info("Registering Mod Items for " + ForestDwellersMain.MOD_ID);

    }
}
