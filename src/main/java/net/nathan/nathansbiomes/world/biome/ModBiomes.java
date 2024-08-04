package net.nathan.nathansbiomes.world.biome;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.nathan.nathansbiomes.NathansBiomes;


public class ModBiomes {
    public static final RegistryKey<Biome> SNOWY_CAVES = register("snowy_caves");
    public static final RegistryKey<Biome> WINTER_FOREST = register("winter_forest");


    public static RegistryKey<Biome> register(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(NathansBiomes.MOD_ID, name));
    }

    public static void bootstrap(Registerable<Biome> context) {
        context.register(SNOWY_CAVES, snowyCaves(context));
        context.register(WINTER_FOREST, winterForest(context));

    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);

    }

    public static Biome snowyCaves(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        ModBiomeFeatures.addSnowyCavesMobs(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addTaigaGrass(biomeBuilder);
        ModBiomeFeatures.addSnowyCavesVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.0f)
                .temperatureModifier(Biome.TemperatureModifier.FROZEN)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .grassColor(0x599e91)
                        .fogColor(0x75b7c9)
                        .waterColor(0x31699e)
                        .waterFogColor(0x75b7c9)
                        .foliageColor(0x469983)
                        .skyColor(0x98d5e3).build())
                .build();
    }

    public static Biome winterForest(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        ModBiomeFeatures.addSnowyCavesMobs(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.0f)
                .temperature(0.0f)
                .temperatureModifier(Biome.TemperatureModifier.FROZEN)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .grassColor(0x599e91)
                        .fogColor(0x75b7c9)
                        .waterColor(0x31699e)
                        .waterFogColor(0x75b7c9)
                        .foliageColor(0x469983)
                        .skyColor(0x98d5e3).build())
                .build();
    }
}
