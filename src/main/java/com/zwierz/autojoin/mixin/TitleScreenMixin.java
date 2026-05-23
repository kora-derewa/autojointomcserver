package com.zwierz.autojoin.mixin;

import com.zwierz.autojoin.AutoJoinMod;
import com.zwierz.autojoin.ConfigManager;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    @Inject(method = "init", at = @At("TAIL"))
    private void onScreenOpen(CallbackInfo ci) {
        ConfigManager.Config config = ConfigManager.getConfig();
        if (!config.enabled || !config.autoJoinOnLaunch || !config.showCancelButton) return;

        TitleScreen self = (TitleScreen) (Object) this;

        if (!AutoJoinMod.hasJoined) return;

        ButtonWidget btn = ButtonWidget.builder(
            AutoJoinMod.cancelled
                ? Text.literal("§7Anulowano")
                : Text.literal("§eSpróbuj ponownie"),
            b -> {
                if (!AutoJoinMod.cancelled) {
                    AutoJoinMod.hasJoined = false;
                    AutoJoinMod.cancelled = false;
                    AutoJoinMod.joinAttemptTime = 0;
                }
            }
        )
        .dimensions(self.width / 2 - 100, self.height / 4 + 144, 200, 20)
        .build();
        if (AutoJoinMod.cancelled) btn.active = false;

        ((ScreenAccessor) self).invokeAddDrawableChild(btn);
    }
}
