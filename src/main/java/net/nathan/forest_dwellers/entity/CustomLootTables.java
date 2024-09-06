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

    public static final RegistryKey<LootTable> DWELLER_BARTERING;

    static {
        LOOT_TABLES_READ_ONLY = Collections.unmodifiableSet(LOOT_TABLES);

        // Correctly registering the loot table
        DWELLER_BARTERING = registerLootTable(Identifier.of(ForestDwellersMain.MOD_ID, "gameplay/dweller_bartering"));
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
