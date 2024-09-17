package net.nathan.forest_dwellers.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;
import net.nathan.forest_dwellers.block.ModBlocks;
import net.nathan.forest_dwellers.item.ModItems;

public class ModBoats {
    public static final Identifier GILDED_OAK_BOAT_ID = Identifier.of(ForestDwellersMain.MOD_ID, "gilded_oak_boat");
    public static final Identifier GILDED_OAK_CHEST_BOAT_ID = Identifier.of(ForestDwellersMain.MOD_ID, "gilded_oak_chest_boat");

    public static final RegistryKey<TerraformBoatType> GILDED_OAK_BOAT_KEY = TerraformBoatTypeRegistry.createKey(GILDED_OAK_BOAT_ID);

    public static void registerBoats() {
        TerraformBoatType gildedOakBoat = new TerraformBoatType.Builder()
                .item(ModItems.GILDED_OAK_BOAT)
                .chestItem(ModItems.GILDED_OAK_CHEST_BOAT)
                .planks(ModBlocks.GILDED_OAK_PLANKS.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, GILDED_OAK_BOAT_KEY, gildedOakBoat);
    }
}