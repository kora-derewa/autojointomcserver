package com.zwierz.autojoin.client;

import com.zwierz.autojoin.AutoJoinMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoJoinMod.MOD_ID);

    public static void connectToServer(MinecraftClient client, String address, int port) {
        try {
            LOGGER.info("Łączenie z serwerem: {}:{}", address, port);

            ServerAddress serverAddress = ServerAddress.parse(address + ":" + port);

            ConnectScreen.connect(
                new ConnectScreen(null, client, serverAddress.getAddress(), serverAddress.getPort()),
                client,
                serverAddress.getAddress(),
                serverAddress.getPort(),
                false
            );

            LOGGER.info("Wysłano żądanie połączenia do serwera");
        } catch (Exception e) {
            LOGGER.error("Błąd przy łączeniu z serwerem", e);
        }
    }
}
