package net.nathan.forest_dwellers.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;
import net.nathan.forest_dwellers.block.ModBlocks;

public class ModItemGroup {

    public static final ItemGroup FOREST_DWELLERS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ForestDwellersMain.MOD_ID, "forest_dwellers"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.forest_dwellers"))
                    .icon(() -> new ItemStack(ModItems.PEAR)).entries((displayContext, entries) -> {

                        entries.add(ModItems.DWELLER_SPAWN_EGG);
                        entries.add(ModBlocks.CLOVER);

                        entries.add(ModItems.CHERRIES);
                        entries.add(ModItems.BANANA);
                        entries.add(ModItems.ORANGE);
                        entries.add(ModItems.PLUM);
                        entries.add(ModItems.POMEGRANATE);
                        entries.add(ModItems.PEAR);
                        entries.add(ModItems.STARFRUIT);

                        entries.add(ModItems.STRAWBERRY);
                        entries.add(ModItems.BLUEBERRY);
                        entries.add(ModItems.GRAPES);

                        entries.add(ModItems.LEEK);
                        entries.add(ModItems.ROASTED_LEEK);
                        entries.add(ModItems.LEEK_SEEDS);

                        entries.add(ModItems.TOMATO);
                        entries.add(ModItems.LETTUCE_LEAF);

                    }).build());

    public static void registerItemGroups() {
    }
}
