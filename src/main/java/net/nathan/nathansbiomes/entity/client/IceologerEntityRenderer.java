package net.nathan.nathansbiomes.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.NathansBiomes;

@Environment(EnvType.CLIENT)
public class IceologerEntityRenderer<T extends SpellcastingIllagerEntity> extends IllagerEntityRenderer<T> {
    private static final Identifier TEXTURE = Identifier.of(NathansBiomes.MOD_ID, "textures/entity/iceologer.png");

    public IceologerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new IllagerEntityModel<>(context.getPart(EntityModelLayers.ILLUSIONER)), 0.5F);
        this.addFeature(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
        this.model.getHat().visible = true;
    }

    @Override
    public Identifier getTexture(T spellcastingIllagerEntity) {
        return TEXTURE;
    }
}
