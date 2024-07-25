package net.nathan.nathansbiomes.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.item.custom.IceSpawnerItem;

public class ModItems {

    public static final Item ICE_WAND = registerItem("ice_wand",
            new IceSpawnerItem(ModToolMaterial.ICE_WAND, new Item.Settings()));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NathansBiomes.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NathansBiomes.LOGGER.info("Registering Mod Items for " + NathansBiomes.MOD_ID);

    }
}
