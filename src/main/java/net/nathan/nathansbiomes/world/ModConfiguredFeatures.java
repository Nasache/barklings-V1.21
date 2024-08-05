package net.nathan.nathansbiomes.world;


import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.block.ModBlocks;
import net.nathan.nathansbiomes.world.tree.custom.BigStarshroomFoliagePlacer;


public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> WINTER_OAK_KEY = registerKey("winter_oak");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BIG_BLUE_STARSHROOM_KEY = registerKey("big_blue_starshroom");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        register(context, WINTER_OAK_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new LargeOakTrunkPlacer(5, 5, 5),
                BlockStateProvider.of(Blocks.AIR),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), 0),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, BIG_BLUE_STARSHROOM_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.BIRCH_LOG),
                new GiantTrunkPlacer(15, 5, 5),
                BlockStateProvider.of(ModBlocks.BLUE_STARSHROOM_BLOCK),
                new BigStarshroomFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), 0),
                new TwoLayersFeatureSize(1, 0, 2)).build());
    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(NathansBiomes.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}