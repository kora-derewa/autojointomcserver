package com.zwierz.autojoin.client;

import com.zwierz.autojoin.AutoJoinMod;
import com.zwierz.autojoin.ConfigManager;
import com.zwierz.autojoin.mixin.ConnectScreenAccessor;
import com.zwierz.autojoin.mixin.ScreenAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AutoJoinClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoJoinMod.MOD_ID);
    private static boolean musicMuted = false;
    private static boolean startupMusicPlayed = false;
    private static ButtonWidget connectScreenBtn;

    @Override
    public void onInitializeClient() {
        LOGGER.info("AutoJoin Client initializing");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ConfigManager.Config config = ConfigManager.getConfig();

            if (client.currentScreen instanceof TitleScreen) {
                applyStartupMusic(client, config);
            }
            applyMuteMusic(client, config);

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

            if (client.currentScreen instanceof ConnectScreen && connectScreenBtn != null) {
                boolean done = ((ConnectScreenAccessor) client.currentScreen).getConnection() == null;
                connectScreenBtn.setMessage(done
                    ? Text.literal("§eSpróbuj ponownie")
                    : Text.literal("§cAnuluj"));
            }
        });

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof TitleScreen) {
                startupMusicPlayed = false;
                return;
            }
            if (screen instanceof ConnectScreen && ConfigManager.getConfig().showCancelButton) {
                LOGGER.info("AutoJoin: dodaję przycisk na ConnectScreen");
                ConnectScreen cs = (ConnectScreen) screen;
                connectScreenBtn = ButtonWidget.builder(
                    Text.literal("§cAnuluj"),
                    b -> {
                        if (((ConnectScreenAccessor) cs).getConnection() != null) {
                            ((ConnectScreenAccessor) cs).setConnectingCancelled(true);
                            ((ConnectScreenAccessor) cs).getConnection().disconnect(Text.literal("Anulowano"));
                            cs.close();
                        } else {
                            AutoJoinMod.hasJoined = false;
                            AutoJoinMod.cancelled = false;
                            AutoJoinMod.joinAttemptTime = 0;
                            cs.close();
                        }
                    }
                )
                .dimensions(screen.width / 2 - 60, screen.height / 2 + 30, 120, 20)
                .build();
                ((ScreenAccessor) screen).invokeAddDrawableChild(connectScreenBtn);
            }
        });
    }

    static void applyStartupMusic(MinecraftClient client, ConfigManager.Config config) {
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

    static void applyMuteMusic(MinecraftClient client, ConfigManager.Config config) {
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
