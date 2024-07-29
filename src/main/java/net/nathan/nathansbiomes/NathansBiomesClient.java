package net.nathan.nathansbiomes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.nathan.nathansbiomes.entity.ModEntities;
import net.nathan.nathansbiomes.entity.client.IceologerEntityRenderer;
import net.nathan.nathansbiomes.entity.client.IceologerFangsEntityRenderer;
public class NathansBiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        EntityRendererRegistry.register(ModEntities.ICEOLOGER, IceologerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ICEOLOGER_FANGS, IceologerFangsEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MAGIC_SNOWBALL, FlyingItemEntityRenderer::new);
    }
}