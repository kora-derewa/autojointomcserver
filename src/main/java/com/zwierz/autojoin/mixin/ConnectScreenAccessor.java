package com.zwierz.autojoin.mixin;

import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ConnectScreen.class)
public interface ConnectScreenAccessor {
    @Accessor("connectingCancelled")
    void setConnectingCancelled(boolean cancelled);

    @Accessor("connection")
    ClientConnection getConnection();
}
