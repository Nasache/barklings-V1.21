package net.nathan.nathansbiomes.world.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.nathan.nathansbiomes.block.ModBlocks;
import net.nathan.nathansbiomes.world.biome.ModBiomes;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule SNOW = makeStateRule(Blocks.SNOW_BLOCK);
    private static final MaterialRules.MaterialRule ICE = makeStateRule(Blocks.BLUE_ICE);
    private static final MaterialRules.MaterialRule SNOW_GRASS = makeStateRule(ModBlocks.SNOW_GRASS_BLOCK);



    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isSnowyCaves = MaterialRules.biome(ModBiomes.SNOWY_CAVES);
        MaterialRules.MaterialCondition isWinterForest = MaterialRules.biome(ModBiomes.WINTER_FOREST);

        MaterialRules.MaterialRule snowyCavesSurface = MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, SNOW),
                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, ICE)
        );
        MaterialRules.MaterialRule winterForestSurface = MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.surface(), SNOW_GRASS)
        );


        return MaterialRules.sequence(
                MaterialRules.condition(isSnowyCaves, snowyCavesSurface),
                MaterialRules.condition(isWinterForest, winterForestSurface)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
