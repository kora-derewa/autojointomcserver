package com.zwierz.autojoin.mixin;

import com.zwierz.autojoin.AutoJoinMod;
import com.zwierz.autojoin.ConfigManager;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {

    @Inject(method = "init", at = @At("TAIL"))
    private void onConnectScreenInit(CallbackInfo ci) {
        if (!ConfigManager.getConfig().showCancelButton) return;

        ConnectScreen self = (ConnectScreen) (Object) this;

        ButtonWidget cancelBtn = ButtonWidget.builder(
            Text.literal("§cAnuluj"),
            btn -> {
                AutoJoinMod.cancelled = true;
                ((ConnectScreenAccessor) self).setConnectingCancelled(true);
                if (((ConnectScreenAccessor) self).getConnection() != null) {
                    ((ConnectScreenAccessor) self).getConnection().disconnect(Text.literal("Anulowano"));
                }
                self.close();
            }
        )
        .dimensions(self.width / 2 - 50, self.height / 2 + 30, 100, 20)
        .build();

        ((ScreenAccessor) self).invokeAddDrawableChild(cancelBtn);
    }
}
