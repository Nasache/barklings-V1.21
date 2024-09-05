package net.nathan.forest_dwellers.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.nathan.forest_dwellers.ForestDwellersMain;
import net.nathan.forest_dwellers.entity.custom.DwellerEntity;
import net.nathan.forest_dwellers.entity.variant.DwellerVariant;

import java.util.Map;

public class DwellerRenderer extends MobEntityRenderer<DwellerEntity, DwellerModel> {

    private static final Map<DwellerVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DwellerVariant.class), map -> {
                map.put(DwellerVariant.PLAIN_OAK, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/oak_dweller.png"));
                map.put(DwellerVariant.BOTH_OAK, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/oak_dweller_both.png"));
                map.put(DwellerVariant.RED_OAK, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/oak_dweller_red.png"));
                map.put(DwellerVariant.BROWN_OAK, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/oak_dweller_brown.png"));
                map.put(DwellerVariant.DARK_OAK, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/dark_oak_dweller.png"));
            });

    public DwellerRenderer(EntityRendererFactory.Context context) {
        super(context, new DwellerModel(context.getPart(ModEntityModelLayers.DWELLER)), 0.4f);
    }

    @Override
    public Identifier getTexture(DwellerEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(DwellerEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
