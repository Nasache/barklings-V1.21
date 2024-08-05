package net.nathan.nathansbiomes.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator WINTER_OAK = new SaplingGenerator(NathansBiomes.MOD_ID + "winter_oak",
            Optional.empty(), Optional.of(ModConfiguredFeatures.WINTER_OAK_KEY), Optional.empty());

    public static final SaplingGenerator BLUE_STARSHROOM = new SaplingGenerator(NathansBiomes.MOD_ID + "blue_starshroom",
            Optional.of(ModConfiguredFeatures.BIG_BLUE_STARSHROOM_KEY), Optional.empty(), Optional.empty());
}
