package net.nathan.barklings.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import net.nathan.barklings.item.ModItems;

public class ModLootTableModifiers {

    private static final Identifier SPRUCE_LEAVES_ID
            = Identifier.of("minecraft", "blocks/spruce_leaves");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (SPRUCE_LEAVES_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(InvertedLootCondition.builder(
                                MatchToolLootCondition.builder(
                                        ItemPredicate.Builder.create()
                                                .items(Items.SHEARS)
                                )
                        ))
                        .conditionally(RandomChanceLootCondition.builder(0.005f))
                        .with(ItemEntry.builder(ModItems.SNOWY_PLUM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
