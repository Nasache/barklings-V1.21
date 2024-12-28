package net.nathan.barklings.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.nathan.barklings.BarklingsMain;
import net.nathan.barklings.entity.custom.BarklingEntity;
import net.nathan.barklings.entity.variant.BarklingVariant;

import java.util.Map;

public class BarklingRenderer extends MobEntityRenderer<BarklingEntity, BarklingModel> {

    private static final Map<BarklingVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BarklingVariant.class), map -> {
                map.put(BarklingVariant.OAK, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/oak/oak_dweller.png"));
                map.put(BarklingVariant.OAK_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/oak/oak_dweller_moss.png"));

                map.put(BarklingVariant.BIRCH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller.png"));
                map.put(BarklingVariant.BIRCH_SMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_smush.png"));
                map.put(BarklingVariant.BIRCH_BMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_bmush.png"));
                map.put(BarklingVariant.BIRCH_DMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_dmush.png"));
                map.put(BarklingVariant.BIRCH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_moss.png"));
                map.put(BarklingVariant.BIRCH_SMUSH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_smush_moss.png"));
                map.put(BarklingVariant.BIRCH_BMUSH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_bmush_moss.png"));
                map.put(BarklingVariant.BIRCH_DMUSH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/birch/birch_dweller_dmush_moss.png"));

                map.put(BarklingVariant.DARK_OAK, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller.png"));
                map.put(BarklingVariant.DARK_OAK_RMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_rmush.png"));
                map.put(BarklingVariant.DARK_OAK_BMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_bmush.png"));
                map.put(BarklingVariant.DARK_OAK_DMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_dmush.png"));
                map.put(BarklingVariant.DARK_OAK_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_moss.png"));
                map.put(BarklingVariant.DARK_OAK_RMUSH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_rmush_moss.png"));
                map.put(BarklingVariant.DARK_OAK_BMUSH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_bmush_moss.png"));
                map.put(BarklingVariant.DARK_OAK_DMUSH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/dark_oak/dark_oak_dweller_dmush_moss.png"));

                map.put(BarklingVariant.SPRUCE, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller.png"));
                map.put(BarklingVariant.SPRUCE_SMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_smush.png"));
                map.put(BarklingVariant.SPRUCE_BMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_bmush.png"));
                map.put(BarklingVariant.SPRUCE_DMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_dmush.png"));
                map.put(BarklingVariant.SPRUCE_SNOW, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_snow.png"));
                map.put(BarklingVariant.SPRUCE_SMUSH_SNOW, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_smush_snow.png"));
                map.put(BarklingVariant.SPRUCE_BMUSH_SNOW, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_bmush_snow.png"));
                map.put(BarklingVariant.SPRUCE_DMUSH_SNOW, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/spruce/spruce_dweller_dmush_snow.png"));

                map.put(BarklingVariant.CHERRY, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/cherry/cherry_dweller.png"));
                map.put(BarklingVariant.CHERRY_HONEY, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/cherry/cherry_dweller_honey.png"));
                map.put(BarklingVariant.CHERRY_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/cherry/cherry_dweller_moss.png"));

                map.put(BarklingVariant.MANGROVE, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller.png"));
                map.put(BarklingVariant.MANGROVE_RMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_rmush.png"));
                map.put(BarklingVariant.MANGROVE_BMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_bmush.png"));
                map.put(BarklingVariant.MANGROVE_DMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_dmush.png"));
                map.put(BarklingVariant.MANGROVE_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_moss.png"));
                map.put(BarklingVariant.MANGROVE_RMUSH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_rmush_moss.png"));
                map.put(BarklingVariant.MANGROVE_BMUSH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_bmush_moss.png"));
                map.put(BarklingVariant.MANGROVE_DMUSH_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mangrove/mangrove_dweller_dmush_moss.png"));

                map.put(BarklingVariant.JUNGLE, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/jungle/jungle_dweller.png"));
                map.put(BarklingVariant.JUNGLE_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/jungle/jungle_dweller_moss.png"));
                map.put(BarklingVariant.JUNGLE_VINES, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/jungle/jungle_dweller_vines.png"));

                map.put(BarklingVariant.ACACIA, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/acacia/acacia_dweller.png"));
                map.put(BarklingVariant.ACACIA_MOSS, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/acacia/acacia_dweller_moss.png"));
                map.put(BarklingVariant.ACACIA_VINES, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/acacia/acacia_dweller_vines.png"));

                map.put(BarklingVariant.CRIMSON, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/crimson/crimson_dweller.png"));
                map.put(BarklingVariant.CRIMSON_SHROOM, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/crimson/crimson_dweller_shroom.png"));
                map.put(BarklingVariant.CRIMSON_WART, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/crimson/crimson_dweller_wart.png"));
                map.put(BarklingVariant.CRIMSON_WART_SHROOM, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/crimson/crimson_dweller_wart_shroom.png"));

                map.put(BarklingVariant.WARPED, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/warped/warped_dweller.png"));
                map.put(BarklingVariant.WARPED_SHROOM, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/warped/warped_dweller_shroom.png"));
                map.put(BarklingVariant.WARPED_WART, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/warped/warped_dweller_wart.png"));
                map.put(BarklingVariant.WARPED_WART_SHROOM, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/warped/warped_dweller_wart_shroom.png"));

                map.put(BarklingVariant.MUSHROOM, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mushroom/mushroom_dweller.png"));
                map.put(BarklingVariant.RED_MUSHROOM, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mushroom/rmushroom_dweller.png"));
                map.put(BarklingVariant.BROWN_MUSHROOM, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mushroom/bmushroom_dweller.png"));
                map.put(BarklingVariant.MUSHROOM_RMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mushroom/mushroom_dweller_rmush.png"));
                map.put(BarklingVariant.MUSHROOM_BMUSH, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/mushroom/mushroom_dweller_bmush.png"));

                map.put(BarklingVariant.AZALEA, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/azalea/azalea_dweller.png"));
                map.put(BarklingVariant.FLOWER_AZALEA, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/azalea/flowering_azalea_dweller.png"));
                map.put(BarklingVariant.AZALEA_TREE, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/azalea/azalea_dweller_sap.png"));
                map.put(BarklingVariant.FLOWER_AZALEA_TREE, Identifier.of(BarklingsMain.MOD_ID, "textures/entity/dwellers/azalea/flowering_azalea_dweller_sap.png"));
            });

    public BarklingRenderer(EntityRendererFactory.Context context) {
        super(context, new BarklingModel(context.getPart(ModEntityModelLayers.BARKLING)), 0.4f);
        this.addFeature(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));

    }

    @Override
    public Identifier getTexture(BarklingEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(BarklingEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
