package net.nathan.forest_dwellers.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.nathan.forest_dwellers.entity.client.animation.DwellerAnimations;
import net.nathan.forest_dwellers.entity.custom.DwellerEntity;

public class DwellerModel extends SinglePartEntityModel<DwellerEntity> {
    private final ModelPart body;
    private final ModelPart head;
    public DwellerModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -11.0F, -5.0F, 10.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 18).cuboid(-4.0F, -2.0F, -4.0F, 8.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

        ModelPartData decorations = head.addChild("decorations", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, 0.0F));

        ModelPartData cap_mushroom = decorations.addChild("cap_mushroom", ModelPartBuilder.create().uv(31, 31).cuboid(4.0F, -4.0F, 1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 30).cuboid(5.0F, -8.0F, 1.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(30, 0).cuboid(4.0F, -9.0F, 0.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(19, 30).cuboid(4.0F, -8.0F, -1.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(30, 4).cuboid(7.0F, -8.0F, 0.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(30, 4).cuboid(3.0F, -8.0F, 0.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(19, 30).cuboid(4.0F, -8.0F, 3.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData flat_mushroom = decorations.addChild("flat_mushroom", ModelPartBuilder.create().uv(24, 23).cuboid(-3.0F, -5.0F, -2.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(27, 31).cuboid(-4.0F, -7.0F, -2.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(27, 18).cuboid(-5.0F, -8.0F, -4.0F, 3.0F, 1.0F, 5.0F, new Dilation(0.001F))
                .uv(29, 27).cuboid(-6.0F, -8.0F, -3.0F, 5.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData legs = body.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rleg = legs.addChild("rleg", ModelPartBuilder.create().uv(0, 30).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -3.0F, 0.0F));

        ModelPartData lleg = legs.addChild("lleg", ModelPartBuilder.create().uv(24, 18).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -3.0F, 0.0F));

        ModelPartData arms = body.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rarm = arms.addChild("rarm", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -10.0F, 0.0F));

        ModelPartData larm = arms.addChild("larm", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, -10.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(DwellerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(DwellerAnimations.ANIM_DWELLER_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, DwellerAnimations.ANIM_DWELLER_IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
        headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        body.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return body;
    }
}
