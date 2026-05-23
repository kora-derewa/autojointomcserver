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

        updateButtonState();

        if (cancelButton == null) {
            cancelButton = ButtonWidget.builder(
                Text.literal(""),
                btn -> onCancelClick(config)
            )
            .dimensions(self.width / 2 + 110, self.height / 4 + 110, 110, 20)
            .build();
        }

        updateButtonState();
        ((ScreenAccessor) self).invokeAddDrawableChild(cancelButton);
    }

    @Unique
    private static void updateButtonState() {
        if (cancelButton == null) return;

        if (AutoJoinMod.cancelled) {
            cancelButton.setMessage(Text.literal("§7Anulowano"));
            cancelButton.active = false;
        } else if (AutoJoinMod.hasJoined) {
            cancelButton.setMessage(Text.literal("§e↻ Spróbuj ponownie"));
            cancelButton.active = true;
        } else {
            cancelButton.setMessage(Text.literal("§c✕ Anuluj auto-join"));
            cancelButton.active = true;
        }
    }

    @Unique
    private static void onCancelClick(ConfigManager.Config config) {
        if (AutoJoinMod.cancelled) {
            return;
        }

        if (AutoJoinMod.hasJoined) {
            AutoJoinMod.hasJoined = false;
            AutoJoinMod.cancelled = false;
            AutoJoinMod.joinAttemptTime = 0;
            updateButtonState();
            if (config.showMessages) {
                net.minecraft.client.MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                    Text.literal("§6[AutoJoin] §eAuto-join wznowiony")
                );
            }
            AutoJoinMod.LOGGER.info("AutoJoin: reset, mozna sprobowac ponownie");
            return;
        }

        AutoJoinMod.cancelled = true;
        updateButtonState();
        if (config.showMessages) {
            net.minecraft.client.MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                Text.literal("§6[AutoJoin] §cAuto-dołączanie anulowane")
            );
        }
        AutoJoinMod.LOGGER.info("AutoJoin anulowane przez gracza");
    }
}
