package net.nathan.nathansbiomes.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.nathan.nathansbiomes.world.ModPlacedFeatures;
import net.nathan.nathansbiomes.world.biome.ModBiomes;

public class ModTreeGeneration {
    public static void generateTrees() {

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.WINTER_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_GROVE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.WINTER_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.WINTER_OAK_PLACED_KEY);
    }
}