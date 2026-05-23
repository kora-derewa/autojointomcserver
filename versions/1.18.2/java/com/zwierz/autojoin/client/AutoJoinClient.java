package com.zwierz.autojoin.client;

import com.zwierz.autojoin.AutoJoinMod;
import com.zwierz.autojoin.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.LiteralText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AutoJoinClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoJoinMod.MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("AutoJoin Client initializing");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ConfigManager.Config config = ConfigManager.getConfig();

            if (client.currentScreen instanceof TitleScreen
                    && config.enabled && config.autoJoinOnLaunch
                    && !AutoJoinMod.hasJoined && !AutoJoinMod.cancelled) {
                long currentTime = System.currentTimeMillis();
                if (AutoJoinMod.joinAttemptTime == 0) {
                    AutoJoinMod.joinAttemptTime = currentTime;
                }
                if (currentTime - AutoJoinMod.joinAttemptTime >= config.delayMs) {
                    joinServer(client, config);
                    AutoJoinMod.hasJoined = true;
                }
            }
        });
    }

    private static void joinServer(MinecraftClient client, ConfigManager.Config config) {
        try {
            LOGGER.info("Próba dołączenia na serwer: {}", config.getServerAddress());

            if (config.showMessages) {
                client.inGameHud.getChatHud().addMessage(
                    new LiteralText("§6[AutoJoin] §aDołączanie na serwer: " + config.getServerAddress())
                );
            }

            ServerConnector.connectToServer(client, config.serverAddress, config.serverPort);

        } catch (Exception e) {
            LOGGER.error("Błąd przy próbie dołączenia", e);
            if (config.showMessages) {
                client.inGameHud.getChatHud().addMessage(
                    new LiteralText("§6[AutoJoin] §cBłąd dołączenia do serwera")
                );
            }
        }
    }
}
