package com.zwierz.autojoin.mixin;

import com.zwierz.autojoin.AutoJoinMod;
import com.zwierz.autojoin.ConfigManager;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Unique
    private static ButtonWidget cancelButton;

    @Inject(method = "init", at = @At("TAIL"))
    private void onScreenOpen(CallbackInfo ci) {
        ConfigManager.Config config = ConfigManager.getConfig();
        if (!config.enabled || !config.autoJoinOnLaunch) return;

        TitleScreen self = (TitleScreen) (Object) this;

        cancelButton = ButtonWidget.builder(
            Text.literal("§c✕ Anuluj auto-join"),
            btn -> {
                AutoJoinMod.cancelled = true;
                btn.active = false;
                btn.setMessage(Text.literal("§7Anulowano"));
                if (config.showMessages) {
                    net.minecraft.client.MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                        Text.literal("§6[AutoJoin] §cAuto-dołączanie anulowane")
                    );
                }
                AutoJoinMod.LOGGER.info("AutoJoin anulowane przez gracza");
            }
        )
        .dimensions(self.width / 2 + 110, self.height / 4 + 110, 110, 20)
        .build();
        cancelButton.active = !AutoJoinMod.hasJoined && !AutoJoinMod.cancelled;
        cancelButton.visible = true;

        ((ScreenAccessor) self).invokeAddDrawableChild(cancelButton);
    }
}
