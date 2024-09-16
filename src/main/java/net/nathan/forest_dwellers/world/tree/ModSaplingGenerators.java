package net.nathan.forest_dwellers.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.nathan.forest_dwellers.ForestDwellersMain;
import net.nathan.forest_dwellers.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator GILDED_OAK = new SaplingGenerator(ForestDwellersMain.MOD_ID + ":gilded_oak",
            Optional.empty(), Optional.of(ModConfiguredFeatures.GILDED_OAK_KEY), Optional.empty());
}