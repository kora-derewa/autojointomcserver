package com.zwierz.autojoin.mixin;

import com.zwierz.autojoin.AutoJoinMod;
import com.zwierz.autojoin.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
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

        ButtonWidget retryBtn = new ButtonWidget(
            self.width / 2 - 100, self.height / 4 + 180, 200, 20,
            new LiteralText("§eSpróbuj ponownie"),
            b -> {
                AutoJoinMod.hasJoined = false;
                AutoJoinMod.cancelled = false;
                AutoJoinMod.joinAttemptTime = 0;
                AutoJoinMod.commandsSent = false;
                AutoJoinMod.commandTimer = 0;
                MinecraftClient.getInstance().setScreen(new TitleScreen());
            }
        );
        ((ScreenAccessor) self).invokeAddDrawableChild(retryBtn);
    }
}
