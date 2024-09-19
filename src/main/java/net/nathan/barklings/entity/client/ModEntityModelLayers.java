package net.nathan.barklings.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.nathan.barklings.BarklingsMain;

public class ModEntityModelLayers {
    public static final EntityModelLayer BARKLING =
            new EntityModelLayer(Identifier.of(BarklingsMain.MOD_ID, "dweller"), "main");
}
