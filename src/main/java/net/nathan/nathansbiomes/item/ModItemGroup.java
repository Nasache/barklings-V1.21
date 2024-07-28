package net.nathan.nathansbiomes.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.block.ModBlocks;

public class ModItemGroup {

    public static final ItemGroup SNOW = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(NathansBiomes.MOD_ID, "snow"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.snow"))
                    .icon(() -> new ItemStack(ModItems.ICE_WAND)).entries((displayContext, entries) -> {

                        entries.add(ModBlocks.SNOW_BRICKS);
                        entries.add(ModBlocks.CHISELED_SNOW_BRICKS);
                        entries.add(ModBlocks.SNOW_BRICK_STAIRS);
                        entries.add(ModBlocks.SNOW_BRICK_SLAB);
                        entries.add(ModBlocks.SNOW_BRICK_WALL);
                        entries.add(ModBlocks.SNOW_BRICK_PRESSURE_PLATE);
                        entries.add(ModBlocks.SNOW_BRICK_BUTTON);


                        entries.add(ModItems.ICE_WAND);
                        entries.add(ModItems.ICEOLOGER_SPAWN_EGG);



                    }).build());

    public static void registerItemGroups() {
    }
}
