package net.nathan.forest_dwellers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.nathan.forest_dwellers.block.ModBlocks;
import net.nathan.forest_dwellers.entity.ModEntities;
import net.nathan.forest_dwellers.entity.client.DwellerModel;
import net.nathan.forest_dwellers.entity.client.DwellerRenderer;
import net.nathan.forest_dwellers.entity.client.ModEntityModelLayers;

public class ForestDwellersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAWBERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUEBERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAPE_BUSH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CLOVER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIVING_LANTERN, RenderLayer.getCutout());


        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.DWELLER, DwellerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DWELLER, DwellerRenderer::new);

    }
}