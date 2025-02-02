package net.nathan.barklings.world.features;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.nathan.barklings.BarklingsMain;

public class ModFeatures {
    public static final Feature<DefaultFeatureConfig> WARPED_MANGO_FEATURE =
            new WarpedMangoPodFeature(DefaultFeatureConfig.CODEC);

    public static void registerFeatures() {
        Registry.register(Registries.FEATURE,
                Identifier.of(BarklingsMain.MOD_ID, "warped_mango_pods"),
                WARPED_MANGO_FEATURE);
    }
}
