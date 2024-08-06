package net.nathan.nathansbiomes.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.HoeItem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.item.ItemUsageContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Mixin(HoeItem.class)
public interface HoeItemMixin {
    @Accessor("TILLING_ACTIONS")
    static Map<Block, Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> getTillingActions() {
        throw new AssertionError();
    }
}