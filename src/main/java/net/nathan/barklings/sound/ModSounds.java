package net.nathan.barklings.sound;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.nathan.barklings.BarklingsMain;

public class ModSounds {

    public static final SoundEvent HIDDEN_VOICES = registerSoundEvent("hidden_voices");
    public static final RegistryKey<JukeboxSong> HIDDEN_VOICES_KEY = of("hidden_voices");


    private static RegistryKey<JukeboxSong> of(String name) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(BarklingsMain.MOD_ID, name));
    }

    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(BarklingsMain.MOD_ID, name),
                SoundEvent.of(Identifier.of(BarklingsMain.MOD_ID, name)));
    }

    public static void registerSounds() {
        BarklingsMain.LOGGER.info("Registering Mod Sounds for " + BarklingsMain.MOD_ID);
    }
}