package net.nathan.barklings.util;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.nathan.barklings.BarklingsMain;

public class ModWoodTypes {
    public static final WoodType GILDED_OAK = new WoodTypeBuilder()
            .soundGroup(BlockSoundGroup.WOOD)
            .hangingSignSoundGroup(BlockSoundGroup.HANGING_SIGN)
            .fenceGateCloseSound(SoundEvents.BLOCK_FENCE_GATE_CLOSE)
            .fenceGateOpenSound(SoundEvents.BLOCK_FENCE_GATE_OPEN)
            .register(Identifier.of(BarklingsMain.MOD_ID, "gilded_oak"), BlockSetType.OAK);
}
