package net.nathan.nathansbiomes.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;
import net.nathan.nathansbiomes.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        BoatEntity boat = (BoatEntity) (Object) this;
        List<?> passengers = boat.getPassengerList();
        if (!passengers.isEmpty() && passengers.getFirst() instanceof PlayerEntity player) {
            if (player.hasStatusEffect(ModEffects.PENGUINS_GRACE)) {
                Vec3d velocity = boat.getVelocity();
                Vec3d newVelocity = new Vec3d(velocity.x * 1.05, velocity.y, velocity.z * 1.05);
                boat.setVelocity(newVelocity);
            }
        }
    }
}

