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
    private void onInit(CallbackInfo ci) {
        if (!ConfigManager.getConfig().showCancelButton) return;

        ConnectScreen self = (ConnectScreen) (Object) this;

        ButtonWidget btn = ButtonWidget.builder(
            Text.literal("§cAnuluj"),
            b -> {
                net.minecraft.network.ClientConnection conn = ((ConnectScreenAccessor) self).getConnection();
                if (conn != null) {
                    ((ConnectScreenAccessor) self).setConnectingCancelled(true);
                    conn.disconnect(Text.literal("Anulowano"));
                }
                AutoJoinMod.cancelled = true;
                self.close();
            }
        )
        .dimensions(self.width / 2 - 60, self.height / 2 + 40, 120, 20)
        .build();

        ((ScreenAccessor) self).invokeAddDrawableChild(btn);
    }
}
