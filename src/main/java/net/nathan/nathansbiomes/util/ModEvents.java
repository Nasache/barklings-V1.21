package net.nathan.nathansbiomes.util;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.nathan.nathansbiomes.block.ModBlocks;
import net.nathan.nathansbiomes.mixin.HoeItemMixin;
import net.nathan.nathansbiomes.mixin.ShovelItemMixin;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ModEvents {

    private static final Supplier<Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> snowGrassFarmlandTillingAction = () ->
            Pair.of(HoeItem::canTillFarmland, context -> HoeItem.createTillAction(Blocks.FARMLAND.getDefaultState()).accept(context));

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            Map<Block, Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> tillingActions = HoeItemMixin.getTillingActions();
            tillingActions.put(ModBlocks.SNOW_GRASS_BLOCK, snowGrassFarmlandTillingAction.get());

            Map<Block, BlockState> pathStates = ShovelItemMixin.getPathStates();
            pathStates.put(ModBlocks.SNOW_GRASS_BLOCK, Blocks.DIRT_PATH.getDefaultState());
        });
    }
}
