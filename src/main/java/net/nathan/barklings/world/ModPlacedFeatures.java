package net.nathan.barklings.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.nathan.barklings.BarklingsMain;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> PATCH_STRAWBERRY_PLACED_KEY = registerKey("strawberry_placed");
    public static final RegistryKey<PlacedFeature> PATCH_GRAPE_PLACED_KEY = registerKey("grape_placed");
    public static final RegistryKey<PlacedFeature> PATCH_BLUEBERRY_PLACED_KEY = registerKey("blueberry_placed");

    public static final RegistryKey<PlacedFeature> PATCH_MANGO_PLACED_KEY = registerKey("mango_placed");


    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, PATCH_STRAWBERRY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_STRAWBERRY_BUSH),
                new PlacementModifier[]{RarityFilterPlacementModifier.of(125), SquarePlacementModifier.of(),
                        PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()});

        register(context, PATCH_GRAPE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_GRAPE_BUSH),
                new PlacementModifier[]{RarityFilterPlacementModifier.of(125), SquarePlacementModifier.of(),
                        PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()});

        register(context, PATCH_BLUEBERRY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_BLUEBERRY_BUSH),
                new PlacementModifier[]{RarityFilterPlacementModifier.of(75), SquarePlacementModifier.of(),
                        PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()});

        register(context, PATCH_MANGO_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_MANGO_POD),
                new PlacementModifier[]{
                        CountPlacementModifier.of(3),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(
                                YOffset.getBottom(),
                                YOffset.fixed(120)
                        ), EnvironmentScanPlacementModifier.of(
                        Direction.DOWN,
                        BlockPredicate.bothOf(
                                BlockPredicate.not(BlockPredicate.IS_AIR),
                                BlockPredicate.matchingBlocks(Blocks.WARPED_WART_BLOCK)
                        ),
                        3
                ),
                        BiomePlacementModifier.of()});
    }


    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(BarklingsMain.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}