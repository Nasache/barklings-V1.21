package net.nathan.forest_dwellers.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;

public class ModEffects {


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        RegistryKey<StatusEffect> registryKey = RegistryKey.of(Registries.STATUS_EFFECT.getKey(), Identifier.of(ForestDwellersMain.MOD_ID, name));
        Registry.register(Registries.STATUS_EFFECT, registryKey, statusEffect);
        return Registries.STATUS_EFFECT.getEntry(registryKey).orElseThrow(() -> new IllegalStateException("StatusEffect " + name + " not found."));
    }

    public static void registerEffects() {
        ForestDwellersMain.LOGGER.info("Registering Mod Effects for " + ForestDwellersMain.MOD_ID);
    }
}
