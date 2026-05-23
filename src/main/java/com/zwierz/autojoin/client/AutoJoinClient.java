package com.zwierz.autojoin.client;

import com.zwierz.autojoin.AutoJoinMod;
import com.zwierz.autojoin.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AutoJoinClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoJoinMod.MOD_ID);
    private static boolean hasJoined = false;
    private static boolean cancelled = false;
    private static long joinAttemptTime = 0;
    private static boolean musicMuted = false;
    private static boolean startupMusicPlayed = false;
    private static KeyBinding cancelKey;

    @Override
    public void onInitializeClient() {
        LOGGER.info("AutoJoin Client initializing");

        cancelKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.autojoin.cancel",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            "category.autojoin"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ConfigManager.Config config = ConfigManager.getConfig();

            if (cancelKey.wasPressed()) {
                if (!hasJoined && !cancelled) {
                    cancelled = true;
                    if (config.showMessages) {
                        client.inGameHud.getChatHud().addMessage(
                            Text.literal("§6[AutoJoin] §cAuto-dołączanie anulowane")
                        );
                    }
                    LOGGER.info("AutoJoin anulowane przez gracza");
                }
            }

            if (client.currentScreen instanceof TitleScreen) {
                applyStartupMusic(client, config);
            }
            applyMuteMusic(client, config);

            if (client.currentScreen instanceof TitleScreen && !hasJoined && !cancelled) {
                if (config.enabled && config.autoJoinOnLaunch) {
                    long currentTime = System.currentTimeMillis();

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

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (!(screen instanceof TitleScreen)) {
                hasJoined = false;
                cancelled = false;
                joinAttemptTime = 0;
                startupMusicPlayed = false;
            }
        });
    }

    private static void applyStartupMusic(MinecraftClient client, ConfigManager.Config config) {
        if (config.enabled
                && config.startupMusic != null
                && !config.startupMusic.isEmpty()
                && !startupMusicPlayed) {
            try {
                Identifier soundId = Identifier.tryParse(config.startupMusic);
                if (soundId != null) {
                    client.getSoundManager().play(
                        PositionedSoundInstance.master(SoundEvent.of(soundId), 1.0f)
                    );
                    LOGGER.info("Odtwarzanie muzyki startowej: {}", config.startupMusic);
                }
                startupMusicPlayed = true;
            } catch (Exception e) {
                LOGGER.error("Błąd odtwarzania muzyki startowej: {}", config.startupMusic, e);
                startupMusicPlayed = true;
            }
        }
    }

    private static void applyMuteMusic(MinecraftClient client, ConfigManager.Config config) {
        if (config.muteMusic && !musicMuted) {
            client.options.getSoundVolumeOption(SoundCategory.MUSIC).setValue(0.0);
            musicMuted = true;
            LOGGER.info("Muzyka wyciszona");
        }

        if (!config.muteMusic && musicMuted) {
            client.options.getSoundVolumeOption(SoundCategory.MUSIC).setValue(1.0);
            musicMuted = false;
            LOGGER.info("Muzyka przywrócona");
        }
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
