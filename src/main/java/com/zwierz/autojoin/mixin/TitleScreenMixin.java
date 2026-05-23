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

        int btnX = self.width / 2 - 100;
        int btnY = self.height / 4 + 144;
        int btnW = 200;

        ButtonWidget btn;
        if (AutoJoinMod.cancelled) {
            btn = ButtonWidget.builder(Text.literal("§7Anulowano"), b -> {})
                .dimensions(btnX, btnY, btnW, 20).build();
            btn.active = false;
        } else if (AutoJoinMod.hasJoined) {
            btn = ButtonWidget.builder(Text.literal("§eSpróbuj ponownie"), b -> {
                    AutoJoinMod.hasJoined = false;
                    AutoJoinMod.cancelled = false;
                    AutoJoinMod.joinAttemptTime = 0;
                })
                .dimensions(btnX, btnY, btnW, 20).build();
        } else {
            btn = ButtonWidget.builder(Text.literal("§cAnuluj auto-join"), b -> {
                    AutoJoinMod.cancelled = true;
                    if (config.showMessages) {
                        net.minecraft.client.MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                            Text.literal("§6[AutoJoin] §cAuto-dołączanie anulowane")
                        );
                    }
                })
                .dimensions(btnX, btnY, btnW, 20).build();
        }

        ((ScreenAccessor) self).invokeAddDrawableChild(btn);
    }
}
