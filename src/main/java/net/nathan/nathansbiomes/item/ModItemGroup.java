package net.nathan.nathansbiomes.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.NathansBiomes;

public class ModItemGroup {

    public static final ItemGroup SNOW = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(NathansBiomes.MOD_ID, "snow"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.snow"))
                    .icon(() -> new ItemStack(ModItems.ICE_WAND)).entries((displayContext, entries) -> {

                        entries.add(ModItems.ICE_WAND);



                    }).build());

    public static void registerItemGroups() {
    }
}
