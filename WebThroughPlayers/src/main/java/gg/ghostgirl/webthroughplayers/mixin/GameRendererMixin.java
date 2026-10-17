package gg.ghostgirl.webthroughplayers.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * When the player is holding a cobweb, a player entity must not steal the
 * crosshair target from a block behind them. Other items retain vanilla
 * targeting behavior.
 */
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(
        method = "findCrosshairTarget(Lnet/minecraft/entity/Entity;DDF)Lnet/minecraft/util/hit/HitResult;",
        at = @At("RETURN"),
        cancellable = true
    )
    private void webthroughplayers$ignorePlayersForCobwebs(
        Entity camera,
        double blockInteractionRange,
        double entityInteractionRange,
        float tickProgress,
        CallbackInfoReturnable<HitResult> cir
    ) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.world == null) {
            return;
        }

        // Only change targeting while a cobweb is held in the main hand.
        if (!client.player.getMainHandStack().isOf(Items.COBWEB)) {
            return;
        }

        HitResult current = cir.getReturnValue();
        if (current instanceof EntityHitResult entityHit
            && entityHit.getEntity() instanceof PlayerEntity) {
            // Re-run the normal block raycast from the camera, ignoring entities.
            HitResult blockHit = camera.raycast(blockInteractionRange, tickProgress, false);
            cir.setReturnValue(blockHit);
        }
    }
}
