package net.nathan.nathansbiomes.world.tree;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.world.tree.custom.BigStarshroomFoliagePlacer;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<BigStarshroomFoliagePlacer> BIG_STARSHROOM_FOLIAGE_PLACER =
            Registry.register(Registries.FOLIAGE_PLACER_TYPE,
                    Identifier.of(NathansBiomes.MOD_ID, "big_starshroom_foliage_placer"),
                    new FoliagePlacerType<>(BigStarshroomFoliagePlacer.CODEC));

    public static void register() {
        NathansBiomes.LOGGER.info("Registering the Foliage Placers for " + NathansBiomes.MOD_ID);
    }
}
