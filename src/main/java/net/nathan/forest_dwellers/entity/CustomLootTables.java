package net.nathan.forest_dwellers.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;

public class CustomLootTables {
    private static final Set<RegistryKey<LootTable>> LOOT_TABLES = new HashSet<>();
    private static final Set<RegistryKey<LootTable>> LOOT_TABLES_READ_ONLY;

    public static final RegistryKey<LootTable> OAK_DWELLER_BARTERING;
    public static final RegistryKey<LootTable> BIRCH_DWELLER_BARTERING;
    public static final RegistryKey<LootTable> SPRUCE_DWELLER_BARTERING;
    public static final RegistryKey<LootTable> DARK_OAK_DWELLER_BARTERING;
    public static final RegistryKey<LootTable> MANGROVE_DWELLER_BARTERING;
    public static final RegistryKey<LootTable> CHERRY_DWELLER_BARTERING;
    public static final RegistryKey<LootTable> JUNGLE_DWELLER_BARTERING;
    public static final RegistryKey<LootTable> ACACIA_DWELLER_BARTERING;
    public static final RegistryKey<LootTable> CRIMSON_DWELLER_BARTERING;
    public static final RegistryKey<LootTable> WARPED_DWELLER_BARTERING;

    static {
        LOOT_TABLES_READ_ONLY = Collections.unmodifiableSet(LOOT_TABLES);

        OAK_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/oak_dweller_bartering"));
        BIRCH_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/birch_dweller_bartering"));
        SPRUCE_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/spruce_dweller_bartering"));
        DARK_OAK_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/dark_oak_dweller_bartering"));
        MANGROVE_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/mangrove_dweller_bartering"));
        CHERRY_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/cherry_dweller_bartering"));
        JUNGLE_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/jungle_dweller_bartering"));
        ACACIA_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/acacia_dweller_bartering"));
        CRIMSON_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/crimson_dweller_bartering"));
        WARPED_DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller/warped_dweller_bartering"));
    }

    private static RegistryKey<LootTable> registerLootTable(Identifier id) {
        RegistryKey<LootTable> key = RegistryKey.of(RegistryKeys.LOOT_TABLE, id);
        if (LOOT_TABLES.add(key)) {
            return key;
        } else {
            throw new IllegalArgumentException(id.toString() + " is already a registered built-in loot table");
        }
    }

    public static Set<RegistryKey<LootTable>> getAll() {
        return LOOT_TABLES_READ_ONLY;
    }
}
