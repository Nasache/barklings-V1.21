package net.nathan.forest_dwellers.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.nathan.forest_dwellers.ForestDwellersMain;
import net.nathan.forest_dwellers.entity.custom.DwellerEntity;

public class DwellerRenderer extends MobEntityRenderer<DwellerEntity, DwellerModel> {

    public DwellerRenderer(EntityRendererFactory.Context context) {
        super(context, new DwellerModel(context.getPart(ModEntityModelLayers.DWELLER)), 0.4f);
    }

    @Override
    public Identifier getTexture(DwellerEntity entity) {
        return Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/oak_dweller.png");
    }

    @Override
    public void render(DwellerEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
