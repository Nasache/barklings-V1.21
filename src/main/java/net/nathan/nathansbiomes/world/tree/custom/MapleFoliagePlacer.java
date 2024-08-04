//package net.nathan.nathansbiomes.world.tree.custom;
//
//import com.mojang.serialization.Codec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import net.minecraft.util.math.intprovider.IntProvider;
//import net.minecraft.util.math.random.Random;
//import net.minecraft.world.TestableWorld;
//import net.minecraft.world.gen.feature.TreeFeatureConfig;
//import net.minecraft.world.gen.foliage.FoliagePlacer;
//import net.minecraft.world.gen.foliage.FoliagePlacerType;
//import net.nathan.mcexpanded.world.tree.ModFoliagePlacerTypes;
//
//public class MapleFoliagePlacer extends FoliagePlacer {
//    public static final Codec<MapleFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> fillFoliagePlacerFields(instance)
//            .and(Codec.intRange(0, 12).fieldOf("height").forGetter((placer) -> placer.height)).apply(instance, MapleFoliagePlacer::new));
//    private final int height;
//
//    public MapleFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
//        super(radius, offset);
//        this.height = height;
//    }
//
//    @Override
//    protected FoliagePlacerType<?> getType() {
//        return ModFoliagePlacerTypes.MAPLE_FOLIAGE_PLACER;
//    }
//
//    @Override
//    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight,
//                            TreeNode treeNode, int foliageHeight, int radius, int offset) {
//        for(int i = 2; i < 3; i++) {
//            this.generateSquare(world, placer, random, config, treeNode.getCenter(), 0, i, treeNode.isGiantTrunk());
//        } for(int i = 0; i < 2; i++) {
//            this.generateSquare(world, placer, random, config, treeNode.getCenter(), 1, i, treeNode.isGiantTrunk());
//        } for(int i = -4; i < 0; i++) {
//            this.generateSquare(world, placer, random, config, treeNode.getCenter(), 2, i, treeNode.isGiantTrunk());
//        } for(int i = -5; i < -4; i++) {
//            this.generateSquare(world, placer, random, config, treeNode.getCenter(), 1, i, treeNode.isGiantTrunk());
//        }
//    }
//
//    @Override
//    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
//        return height;
//    }
//
//    @Override
//    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
//        return dx == radius && dz == radius &&  y == 1 ||
//                dx == 2 && dz >= 1 &&  y == -1 ||
//                dx == 1 && dz == 2 &&  y == -1 ||
//                dx == radius && dz == radius &&  y <= -2;
//    }
//}
//