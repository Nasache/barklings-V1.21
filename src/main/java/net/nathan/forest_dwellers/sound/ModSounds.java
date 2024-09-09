package net.nathan.forest_dwellers.sound;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;

public class ModSounds {

    public static final SoundEvent HIDDEN_VOICES = registerSoundEvent("hidden_voices");
    public static final RegistryKey<JukeboxSong> HIDDEN_VOICES_KEY = of("hidden_voices");


    private static RegistryKey<JukeboxSong> of(String name) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(ForestDwellersMain.MOD_ID, name));
    }

    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(ForestDwellersMain.MOD_ID, name),
                SoundEvent.of(Identifier.of(ForestDwellersMain.MOD_ID, name)));
    }

    public static void registerSounds() {
        ForestDwellersMain.LOGGER.info("Registering Mod Sounds for " + ForestDwellersMain.MOD_ID);
    }
}