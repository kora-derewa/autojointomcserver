package com.zwierz.autojoin.mixin;

import com.zwierz.autojoin.AutoJoinMod;
import com.zwierz.autojoin.ConfigManager;
import com.zwierz.autojoin.mixin.ScreenAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisconnectedScreen.class)
public class DisconnectedScreenMixin {

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        if (!ConfigManager.getConfig().showCancelButton) return;

        DisconnectedScreen self = (DisconnectedScreen) (Object) this;

        ButtonWidget retryBtn = ButtonWidget.builder(
            Text.literal("§eSpróbuj ponownie"),
            b -> {
                AutoJoinMod.hasJoined = false;
                AutoJoinMod.cancelled = false;
                AutoJoinMod.joinAttemptTime = 0;
                MinecraftClient.getInstance().setScreen(new TitleScreen());
            }
        )
        .dimensions(self.width / 2 + 5, self.height / 2 + 80, 150, 20)
        .build();
        ((ScreenAccessor) self).invokeAddDrawableChild(retryBtn);
    }
}
