package net.nathan.nathansbiomes.world.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.nathan.nathansbiomes.world.tree.ModFoliagePlacerTypes;

public class BigStarshroomFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<BigStarshroomFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            fillFoliagePlacerFields(instance)
                    .and(Codec.intRange(0, 12).fieldOf("height").forGetter((placer) -> placer.height))
                    .apply(instance, BigStarshroomFoliagePlacer::new)
    );
    private final int height;

    public BigStarshroomFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModFoliagePlacerTypes.BIG_STARSHROOM_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight,
                            TreeNode treeNode, int foliageHeight, int radius, int offset) {
        // Layer 1 (Y: -5)
        this.generateSquare(world, placer, random, config, treeNode.getCenter(), 1, -5, treeNode.isGiantTrunk());

        // Layer 2 (Y: -4 to -3)
        for (int i = -4; i < -2; i++) {
            this.generateSquare(world, placer, random, config, treeNode.getCenter(), 2, i, treeNode.isGiantTrunk());
        }

        // Layer 3 (Y: -2 to -1)
        for (int i = -2; i < 0; i++) {
            this.generateSquare(world, placer, random, config, treeNode.getCenter(), 3, i, treeNode.isGiantTrunk());
        }

        // Layer 4 (Y: 0 to 1)
        for (int i = 0; i < 2; i++) {
            this.generateSquare(world, placer, random, config, treeNode.getCenter(), 4, i, treeNode.isGiantTrunk());
        }

        // Layer 5 (Y: 2)
        this.generateSquare(world, placer, random, config, treeNode.getCenter(), 5, 2, treeNode.isGiantTrunk());
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return height;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        // Adjust to prevent certain leaves from generating
        return (dx == radius && dz == radius && y == 1) ||
                (dx == 2 && dz >= 1 && y == -1) ||
                (dx == 1 && dz == 2 && y == -1) ||
                (dx == radius && dz == radius && y <= -2);
    }
}
