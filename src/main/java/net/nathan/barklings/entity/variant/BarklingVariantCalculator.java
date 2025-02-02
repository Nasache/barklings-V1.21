package net.nathan.barklings.entity.variant;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.util.math.random.Random;

public class BarklingVariantCalculator {

    public static BarklingVariant getVariantForBiome(RegistryEntry<Biome> registryEntry, Random random) {
        if (registryEntry.matchesKey(BiomeKeys.FOREST) || registryEntry.matchesKey(BiomeKeys.FLOWER_FOREST)
                || registryEntry.matchesKey(BiomeKeys.PLAINS) || registryEntry.matchesKey(BiomeKeys.SUNFLOWER_PLAINS)
                || registryEntry.matchesKey(BiomeKeys.WINDSWEPT_FOREST) || registryEntry.matchesKey(BiomeKeys.MEADOW)
                || registryEntry.matchesKey(BiomeKeys.RIVER) || registryEntry.matchesKey(BiomeKeys.BEACH)
                || registryEntry.matchesKey(BiomeKeys.STONY_SHORE) || registryEntry.matchesKey(BiomeKeys.OCEAN)
                || registryEntry.matchesKey(BiomeKeys.DEEP_OCEAN) || registryEntry.matchesKey(BiomeKeys.WARM_OCEAN)
                || registryEntry.matchesKey(BiomeKeys.LUKEWARM_OCEAN) || registryEntry.matchesKey(BiomeKeys.DEEP_LUKEWARM_OCEAN)) {
            return BarklingVariant.byId(random.nextInt(4));
        } else if (registryEntry.matchesKey(BiomeKeys.BIRCH_FOREST) || registryEntry.matchesKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST)) {
            return BarklingVariant.byId(2 + random.nextInt(8));
        } else if (registryEntry.matchesKey(BiomeKeys.DARK_FOREST)) {
            return BarklingVariant.byId(10 + random.nextInt(8));
        } else if (registryEntry.matchesKey(BiomeKeys.TAIGA) || registryEntry.matchesKey(BiomeKeys.OLD_GROWTH_PINE_TAIGA)
                || registryEntry.matchesKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA) || registryEntry.matchesKey(BiomeKeys.SNOWY_TAIGA)
                || registryEntry.matchesKey(BiomeKeys.WINDSWEPT_HILLS) || registryEntry.matchesKey(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS)
                || registryEntry.matchesKey(BiomeKeys.JAGGED_PEAKS) || registryEntry.matchesKey(BiomeKeys.FROZEN_PEAKS)
                || registryEntry.matchesKey(BiomeKeys.STONY_PEAKS) || registryEntry.matchesKey(BiomeKeys.FROZEN_RIVER)
                || registryEntry.matchesKey(BiomeKeys.SNOWY_BEACH) || registryEntry.matchesKey(BiomeKeys.SNOWY_PLAINS)
                || registryEntry.matchesKey(BiomeKeys.ICE_SPIKES) || registryEntry.matchesKey(BiomeKeys.COLD_OCEAN)
                || registryEntry.matchesKey(BiomeKeys.DEEP_COLD_OCEAN) || registryEntry.matchesKey(BiomeKeys.FROZEN_OCEAN)
                || registryEntry.matchesKey(BiomeKeys.DEEP_FROZEN_OCEAN)) {
            return BarklingVariant.byId(18 + random.nextInt(8));
        } else if (registryEntry.matchesKey(BiomeKeys.CHERRY_GROVE)) {
            return BarklingVariant.byId(26 + random.nextInt(3));
        } else if (registryEntry.matchesKey(BiomeKeys.SWAMP) || registryEntry.matchesKey(BiomeKeys.MANGROVE_SWAMP)) {
            return BarklingVariant.byId(29 + random.nextInt(8));
        } else if (registryEntry.matchesKey(BiomeKeys.JUNGLE) || registryEntry.matchesKey(BiomeKeys.BAMBOO_JUNGLE)
                || registryEntry.matchesKey(BiomeKeys.SPARSE_JUNGLE)) {
            return BarklingVariant.byId(37 + random.nextInt(3));
        } else if (registryEntry.matchesKey(BiomeKeys.DESERT) || registryEntry.matchesKey(BiomeKeys.SAVANNA)
                || registryEntry.matchesKey(BiomeKeys.SAVANNA_PLATEAU) || registryEntry.matchesKey(BiomeKeys.WINDSWEPT_SAVANNA)
                || registryEntry.matchesKey(BiomeKeys.BADLANDS) || registryEntry.matchesKey(BiomeKeys.WOODED_BADLANDS)
                || registryEntry.matchesKey(BiomeKeys.ERODED_BADLANDS)) {
            return BarklingVariant.byId(40 + random.nextInt(3));
        } else if (registryEntry.matchesKey(BiomeKeys.CRIMSON_FOREST)) {
            return BarklingVariant.byId(43 + random.nextInt(4));
        } else if (registryEntry.matchesKey(BiomeKeys.WARPED_FOREST)) {
            return BarklingVariant.byId(47 + random.nextInt(4));
        } else if (registryEntry.matchesKey(BiomeKeys.NETHER_WASTES) || registryEntry.matchesKey(BiomeKeys.SOUL_SAND_VALLEY)
                || registryEntry.matchesKey(BiomeKeys.BASALT_DELTAS)) {
            return BarklingVariant.byId(43 + random.nextInt(8));
        } else if (registryEntry.isIn(BiomeTags.SPAWNS_COLD_VARIANT_FROGS)) {
            return BarklingVariant.byId(18 + random.nextInt(8));
        } else if (registryEntry.isIn(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)) {
            return BarklingVariant.byId(40 + random.nextInt(3));
        } else if (registryEntry.matchesKey(BiomeKeys.MUSHROOM_FIELDS)) {
            return BarklingVariant.byId(51 + random.nextInt(5));
        } else if (registryEntry.matchesKey(BiomeKeys.LUSH_CAVES) || registryEntry.matchesKey(BiomeKeys.DRIPSTONE_CAVES)
                || registryEntry.matchesKey(BiomeKeys.DEEP_DARK)) {
            return BarklingVariant.byId(56 + random.nextInt(4));
        } else {
            return BarklingVariant.byId(random.nextInt(4));
        }
    }
}
