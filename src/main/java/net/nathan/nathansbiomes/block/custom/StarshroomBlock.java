package net.nathan.nathansbiomes.block.custom;

import com.mojang.serialization.MapCodec;
import java.util.OptionalInt;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class StarshroomBlock extends TranslucentBlock {
    public static final MapCodec<net.nathan.nathansbiomes.block.custom.StarshroomBlock> CODEC = createCodec(net.nathan.nathansbiomes.block.custom.StarshroomBlock::new);
    public static final int MAX_DISTANCE = 7;
    public static final IntProperty DISTANCE;
    public static final BooleanProperty PERSISTENT;
    private static final int field_31112 = 1;

    public MapCodec<? extends net.nathan.nathansbiomes.block.custom.StarshroomBlock> getCodec() {
        return CODEC;
    }

    public StarshroomBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(DISTANCE, 7)).with(PERSISTENT, false));
    }

    protected VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    protected boolean hasRandomTicks(BlockState state) {
        return (Integer)state.get(DISTANCE) == 7 && !(Boolean)state.get(PERSISTENT);
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.shouldDecay(state)) {
            dropStacks(state, world, pos);
            world.removeBlock(pos, false);
        }
    }

    protected boolean shouldDecay(BlockState state) {
        return !(Boolean)state.get(PERSISTENT) && (Integer)state.get(DISTANCE) == 7;
    }

    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, updateDistanceFromLogs(state, world, pos), 3);
    }

    protected int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        int i = getDistanceFromLog(neighborState) + 1;
        if (i != 1 || (Integer)state.get(DISTANCE) != i) {
            world.scheduleBlockTick(pos, this, 1);
        }

        return state;
    }

    private static BlockState updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos) {
        int i = 7;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            mutable.set(pos, direction);
            i = Math.min(i, getDistanceFromLog(world.getBlockState(mutable)) + 1);
            if (i == 1) {
                break;
            }
        }

        return (BlockState)state.with(DISTANCE, i);
    }

    private static int getDistanceFromLog(BlockState state) {
        return getOptionalDistanceFromLog(state).orElse(7);
    }

    public static OptionalInt getOptionalDistanceFromLog(BlockState state) {
        if (state.isIn(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return state.contains(DISTANCE) ? OptionalInt.of((Integer)state.get(DISTANCE)) : OptionalInt.empty();
        }
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.hasRain(pos.up())) {
            if (random.nextInt(15) == 1) {
                BlockPos blockPos = pos.down();
                BlockState blockState = world.getBlockState(blockPos);
                if (!blockState.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP)) {
                    ParticleUtil.spawnParticle(world, pos, random, ParticleTypes.DRIPPING_WATER);
                }
            }
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{DISTANCE, PERSISTENT});
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = (BlockState)this.getDefaultState().with(PERSISTENT, true);
        return updateDistanceFromLogs(blockState, ctx.getWorld(), ctx.getBlockPos());
    }

    static {
        DISTANCE = Properties.DISTANCE_1_7;
        PERSISTENT = Properties.PERSISTENT;
    }
}
