package net.nathan.nathansbiomes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.nathan.nathansbiomes.block.ModBlocks;
import net.nathan.nathansbiomes.entity.ModEntities;
import net.nathan.nathansbiomes.entity.client.IceologerEntityRenderer;
import net.nathan.nathansbiomes.entity.client.IceologerFangsEntityRenderer;
public class NathansBiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SNOWDROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SNOWDROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_STARSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BLUE_STARSHROOM, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_STARSHROOM_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEN_STARSHROOM_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_STARSHROOM_BLOCK, RenderLayer.getTranslucent());

        EntityRendererRegistry.register(ModEntities.ICEOLOGER, IceologerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ICEOLOGER_FANGS, IceologerFangsEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MAGIC_SNOWBALL, FlyingItemEntityRenderer::new);
    }
}