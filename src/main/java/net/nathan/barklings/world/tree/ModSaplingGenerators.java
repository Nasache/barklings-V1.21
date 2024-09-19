package net.nathan.barklings.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.nathan.barklings.BarklingsMain;
import net.nathan.barklings.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator GILDED_OAK = new SaplingGenerator(BarklingsMain.MOD_ID + ":gilded_oak",
            Optional.empty(), Optional.of(ModConfiguredFeatures.GILDED_OAK_KEY), Optional.empty());
}