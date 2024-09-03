package net.nathan.forest_dwellers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.nathan.forest_dwellers.entity.ModEntities;
import net.nathan.forest_dwellers.entity.client.DwellerModel;
import net.nathan.forest_dwellers.entity.client.DwellerRenderer;
import net.nathan.forest_dwellers.entity.client.ModEntityModelLayers;

public class ForestDwellersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.DWELLER, DwellerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DWELLER, DwellerRenderer::new);

    }
}