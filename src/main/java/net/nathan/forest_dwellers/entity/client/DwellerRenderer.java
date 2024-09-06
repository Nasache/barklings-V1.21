package net.nathan.forest_dwellers.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
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
                map.put(DwellerVariant.OAK, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/oak/oak_dweller.png"));
                map.put(DwellerVariant.OAK_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/oak/oak_dweller_moss.png"));

                map.put(DwellerVariant.BIRCH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller.png"));
                map.put(DwellerVariant.BIRCH_SMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_smush.png"));
                map.put(DwellerVariant.BIRCH_BMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_bmush.png"));
                map.put(DwellerVariant.BIRCH_DMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_dmush.png"));
                map.put(DwellerVariant.BIRCH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_moss.png"));
                map.put(DwellerVariant.BIRCH_SMUSH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_smush_moss.png"));
                map.put(DwellerVariant.BIRCH_BMUSH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_bmush_moss.png"));
                map.put(DwellerVariant.BIRCH_DMUSH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_dmush_moss.png"));

                map.put(DwellerVariant.DARK_OAK, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller.png"));
                map.put(DwellerVariant.DARK_OAK_RMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_rmush.png"));
                map.put(DwellerVariant.DARK_OAK_BMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_bmush.png"));
                map.put(DwellerVariant.DARK_OAK_DMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_dmush.png"));
                map.put(DwellerVariant.DARK_OAK_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_moss.png"));
                map.put(DwellerVariant.DARK_OAK_RMUSH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_rmush_moss.png"));
                map.put(DwellerVariant.DARK_OAK_BMUSH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_bmush_moss.png"));
                map.put(DwellerVariant.DARK_OAK_DMUSH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_dmush_moss.png"));

                map.put(DwellerVariant.SPRUCE, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller.png"));
                map.put(DwellerVariant.SPRUCE_SMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_smush.png"));
                map.put(DwellerVariant.SPRUCE_BMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_bmush.png"));
                map.put(DwellerVariant.SPRUCE_DMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_dmush.png"));
                map.put(DwellerVariant.SPRUCE_SNOW, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_snow.png"));
                map.put(DwellerVariant.SPRUCE_SMUSH_SNOW, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_smush_snow.png"));
                map.put(DwellerVariant.SPRUCE_BMUSH_SNOW, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_bmush_snow.png"));
                map.put(DwellerVariant.SPRUCE_DMUSH_SNOW, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_dmush_snow.png"));

                map.put(DwellerVariant.CHERRY, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/cherry/cherry_dweller.png"));
                map.put(DwellerVariant.CHERRY_HONEY, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/cherry/cherry_dweller_honey.png"));
                map.put(DwellerVariant.CHERRY_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/cherry/cherry_dweller_moss.png"));

                map.put(DwellerVariant.MANGROVE, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller.png"));
                map.put(DwellerVariant.MANGROVE_RMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_rmush.png"));
                map.put(DwellerVariant.MANGROVE_BMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_bmush.png"));
                map.put(DwellerVariant.MANGROVE_DMUSH, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_dmush.png"));
                map.put(DwellerVariant.MANGROVE_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_moss.png"));
                map.put(DwellerVariant.MANGROVE_RMUSH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_rmush_moss.png"));
                map.put(DwellerVariant.MANGROVE_BMUSH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_bmush_moss.png"));
                map.put(DwellerVariant.MANGROVE_DMUSH_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_dmush_moss.png"));

                map.put(DwellerVariant.JUNGLE, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/jungle/jungle_dweller.png"));
                map.put(DwellerVariant.JUNGLE_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/jungle/jungle_dweller_moss.png"));
                map.put(DwellerVariant.JUNGLE_VINES, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/jungle/jungle_dweller_vines.png"));

                map.put(DwellerVariant.ACACIA, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/acacia/acacia_dweller.png"));
                map.put(DwellerVariant.ACACIA_MOSS, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/acacia/acacia_dweller_moss.png"));
                map.put(DwellerVariant.ACACIA_VINES, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/acacia/acacia_dweller_vines.png"));

                map.put(DwellerVariant.CRIMSON, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/crimson/crimson_dweller.png"));
                map.put(DwellerVariant.CRIMSON_SHROOM, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/crimson/crimson_dweller_shroom.png"));
                map.put(DwellerVariant.CRIMSON_WART, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/crimson/crimson_dweller_wart.png"));
                map.put(DwellerVariant.CRIMSON_WART_SHROOM, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/crimson/crimson_dweller_wart_shroom.png"));

                map.put(DwellerVariant.WARPED, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/warped/warped_dweller.png"));
                map.put(DwellerVariant.WARPED_SHROOM, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/warped/warped_dweller_shroom.png"));
                map.put(DwellerVariant.WARPED_WART, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/warped/warped_dweller_wart.png"));
                map.put(DwellerVariant.WARPED_WART_SHROOM, Identifier.of(ForestDwellersMain.MOD_ID, "textures/entity/dwellers/warped/warped_dweller_wart_shroom.png"));

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
