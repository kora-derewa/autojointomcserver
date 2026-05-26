package com.zwierz.autojoin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoJoinMod.MOD_ID);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_DIR = Paths.get("config", "autojoin");
    private static final Path CONFIG_FILE = CONFIG_DIR.resolve("autojoin.json");
    
    private static Config config;

    public static void loadConfig() {
        try {
            // Utwórz katalog jeśli nie istnieje
            Files.createDirectories(CONFIG_DIR);

            // Jeśli plik konfiguracji nie istnieje, utwórz domyślny
            if (!Files.exists(CONFIG_FILE)) {
                createDefaultConfig();
                LOGGER.info("Utworzono domyślny plik konfiguracji: {}", CONFIG_FILE);
            } else {
                // Załaduj istniejący plik
                try (FileReader reader = new FileReader(CONFIG_FILE.toFile())) {
                    config = GSON.fromJson(reader, Config.class);
                    LOGGER.info("Załadowano konfigurację z: {}", CONFIG_FILE);
                }
            }

            // Walidacja konfiguracji
            if (config == null) {
                createDefaultConfig();
            }

            validateConfig();
            LOGGER.info("AutoJoin Config załadowany:");
            LOGGER.info("  - Włączony: {}", config.enabled);
            LOGGER.info("  - Adres serwera: {}", config.serverAddress);
            LOGGER.info("  - Port: {}", config.serverPort);
            LOGGER.info("  - Opóźnienie (ms): {}", config.delayMs);

        } catch (IOException e) {
            LOGGER.error("Błąd przy ładowaniu konfiguracji", e);
            createDefaultConfig();
        }
    }

    private static void createDefaultConfig() {
        config = new Config();
        config.enabled = true;
        config.serverAddress = "localhost";
        config.serverPort = 25565;
        config.delayMs = 1000;
        config.autoJoinOnLaunch = true;
        config.showMessages = true;
        config.muteMusic = true;
        config.startupMusic = "";
        config.showCancelButton = true;
        config.autoCommandEnabled = false;
        config.autoCommands = new String[]{};
        config.commandDelayMs = 2000;

        try {
            Files.createDirectories(CONFIG_DIR);
            try (FileWriter writer = new FileWriter(CONFIG_FILE.toFile())) {
                GSON.toJson(config, writer);
            }
            LOGGER.info("Domyślna konfiguracja zapisana do: {}", CONFIG_FILE);
        } catch (IOException e) {
            LOGGER.error("Błąd przy tworzeniu domyślnej konfiguracji", e);
        }
    }

    private static void validateConfig() {
        if (config.serverPort < 1 || config.serverPort > 65535) {
            LOGGER.warn("Nieprawidłowy port: {}. Ustawianie na domyślny: 25565", config.serverPort);
            config.serverPort = 25565;
        }

        if (config.delayMs < 0) {
            LOGGER.warn("Nieprawidłowe opóźnienie: {}. Ustawianie na domyślne: 1000", config.delayMs);
            config.delayMs = 1000;
        }

        if (config.serverAddress == null || config.serverAddress.trim().isEmpty()) {
            LOGGER.warn("Adres serwera jest pusty. Ustawianie na domyślny: localhost");
            config.serverAddress = "localhost";
        }
    }

    public static Config getConfig() {
        if (config == null) {
            loadConfig();
        }
        return config;
    }

    public static void reloadConfig() {
        loadConfig();
    }

    public static class Config {
        public boolean enabled = true;
        public String serverAddress = "localhost";
        public int serverPort = 25565;
        public long delayMs = 1000;
        public boolean autoJoinOnLaunch = true;
        public boolean showMessages = true;
        public boolean muteMusic = true;
        public String startupMusic = "";
        public boolean showCancelButton = true;
        public boolean autoCommandEnabled = false;
        public String[] autoCommands = new String[]{};
        public int commandDelayMs = 2000;

        public String getServerAddress() {
            return serverAddress + ":" + serverPort;
        }
    }
}
