package com.zwierz.autojoin.client;

import com.zwierz.autojoin.AutoJoinMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoJoinMod.MOD_ID);

    public static void connectToServer(MinecraftClient client, String address, int port) {
        try {
            ServerAddress serverAddress = ServerAddress.parse(address + ":" + port);
            ServerInfo serverInfo = new ServerInfo(address, address + ":" + port, false);
            Screen parent = client.currentScreen != null ? client.currentScreen : new TitleScreen();
            ConnectScreen.connect(parent, client, serverAddress, serverInfo);
        } catch (Exception e) {
            LOGGER.error("Błąd przy łączeniu z serwerem", e);
        }
    }

    public static void sendCommand(MinecraftClient client, String command) {
        if (client.player != null) {
            client.player.sendChatMessage("/" + command);
        }
    }
}
