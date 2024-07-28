package net.nathan.nathansbiomes.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EvokerFangsEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.nathan.nathansbiomes.entity.custom.IceologerFangsEntity;

@Environment(EnvType.CLIENT)
public class IceologerFangsEntityRenderer extends EntityRenderer<IceologerFangsEntity> {
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/illager/evoker_fangs.png");
    private final EvokerFangsEntityModel<IceologerFangsEntity> model;

    public IceologerFangsEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new EvokerFangsEntityModel(context.getPart(EntityModelLayers.EVOKER_FANGS));
    }

    public void render(IceologerFangsEntity iceologerFangsEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        float h = iceologerFangsEntity.getAnimationProgress(g);
        if (h != 0.0F) {
            float j = 2.0F;
            if (h > 0.9F) {
                j *= (1.0F - h) / 0.1F;
            }

            matrixStack.push();
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F - iceologerFangsEntity.getYaw()));
            matrixStack.scale(-j, -j, j);
            float k = 0.03125F;
            matrixStack.translate(0.0, -0.626, 0.0);
            matrixStack.scale(0.5F, 0.5F, 0.5F);
            this.model.setAngles(iceologerFangsEntity, h, 0.0F, 0.0F, iceologerFangsEntity.getYaw(), iceologerFangsEntity.getPitch());
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
            matrixStack.pop();
            super.render(iceologerFangsEntity, f, g, matrixStack, vertexConsumerProvider, i);
        }
    }

    public Identifier getTexture(IceologerFangsEntity iceologerFangsEntity) {
        return TEXTURE;
    }
}
