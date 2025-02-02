package net.nathan.barklings.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.nathan.barklings.block.ModBlocks;
import net.nathan.barklings.block.custom.WarpedMangoPod;

public class WarpedMangoPodFeature extends Feature<DefaultFeatureConfig> {
    private static final int HORIZONTAL_SPREAD = 4;
    private static final int VERTICAL_SPREAD = 2;
    private static final int MAX_PODS = 9;

    public WarpedMangoPodFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        int placedCount = 0;

        for(int i = 0; i < 12; i++) {
            BlockPos.Mutable pos = origin.mutableCopy()
                    .move(
                            random.nextInt(HORIZONTAL_SPREAD * 2 + 1) - HORIZONTAL_SPREAD,
                            random.nextInt(VERTICAL_SPREAD * 2 + 1) - VERTICAL_SPREAD,
                            random.nextInt(HORIZONTAL_SPREAD * 2 + 1) - HORIZONTAL_SPREAD
                    );

            if (isValidPosition(world, pos)) {
                world.setBlockState(pos, ModBlocks.WARPED_MANGO_POD.getDefaultState()
                        .with(WarpedMangoPod.AGE, MathHelper.nextInt(random, 1, 3)), 2);
                placedCount++;

                if (placedCount >= MAX_PODS) break;
            }
        }

        return placedCount > 0;
    }

    private boolean isValidPosition(WorldAccess world, BlockPos.Mutable pos) {
        for(int i = 0; i < 3; i++) {
            if (world.getBlockState(pos).isAir() &&
                    world.getBlockState(pos.up()).isOf(Blocks.WARPED_WART_BLOCK)) {
                return true;
            }
            pos.move(Direction.UP);
        }
        return false;
    }
}