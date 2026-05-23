package com.zwierz.autojoin.client;

import com.zwierz.autojoin.AutoJoinMod;
import com.zwierz.autojoin.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AutoJoinClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoJoinMod.MOD_ID);
    private static boolean hasJoined = false;
    private static long joinAttemptTime = 0;

    @Override
    public void onInitializeClient() {
        LOGGER.info("AutoJoin Client initializing");

        // Event wykonywany co tick po stronie klienta
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.currentScreen instanceof TitleScreen && !hasJoined) {
                ConfigManager.Config config = ConfigManager.getConfig();

                if (config.enabled && config.autoJoinOnLaunch) {
                    long currentTime = System.currentTimeMillis();

                    // Czekaj określone opóźnienie przed dołączeniem
                    if (joinAttemptTime == 0) {
                        joinAttemptTime = currentTime;
                    }

                    if (currentTime - joinAttemptTime >= config.delayMs) {
                        joinServer(client, config);
                        hasJoined = true;
                    }
                }
            }
        });

        // Reset flagi przy powrocie na ekran tytułu
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (!(screen instanceof TitleScreen)) {
                hasJoined = false;
                joinAttemptTime = 0;
            }
        });
    }

    private static void joinServer(MinecraftClient client, ConfigManager.Config config) {
        try {
            LOGGER.info("Próba dołączenia na serwer: {}", config.getServerAddress());

            if (config.showMessages) {
                client.inGameHud.getChatHud().addMessage(
                    Text.literal("§6[AutoJoin] §aDołączanie na serwer: " + config.getServerAddress())
                );
            }

            ServerConnector.connectToServer(client, config.serverAddress, config.serverPort);

        } catch (Exception e) {
            LOGGER.error("Błąd przy próbie dołączenia", e);
            if (config.showMessages) {
                client.inGameHud.getChatHud().addMessage(
                    Text.literal("§6[AutoJoin] §cBłąd dołączenia do serwera")
                );
            }
        }
    }
}
