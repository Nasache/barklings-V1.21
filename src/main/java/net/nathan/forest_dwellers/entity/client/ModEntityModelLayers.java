package net.nathan.forest_dwellers.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;

public class ModEntityModelLayers {
    public static final EntityModelLayer DWELLER =
            new EntityModelLayer(Identifier.of(ForestDwellersMain.MOD_ID, "dweller"), "main");
}
