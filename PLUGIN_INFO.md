# Fabric plugin for auto-joining Minecraft server
# Zainstaluj w: ~/.minecraft/mods/

# Installation / Instalacja:
# 1. Build: ./gradlew build
# 2. Copy JAR to mods folder / Skopiuj JAR do folderu mods
# 3. Configure in config/autojoin/autojoin.json
# 4. Launch game / Uruchom grę

## Features / Funkcje:
✓ Automatyczne dołączanie na serwer
✓ Konfiguracja plikowa (JSON)
✓ Opóźnienie przed dołączeniem (opcjonalne)
✓ Komunikaty w chacie
✓ Obsługa portów niestandardowych
✓ Obsługa domen

## Configuration example / Przykład konfiguracji:

```json
{
  "enabled": true,
  "serverAddress": "your-server.com",
  "serverPort": 25565,
  "delayMs": 1000,
  "autoJoinOnLaunch": true,
  "showMessages": true
}
```

## Build / Budowanie:

```bash
chmod +x gradlew
./gradlew build
```

## Structure / Struktura:

```
src/main/java/com/zwierz/autojoin/
├── AutoJoinMod.java          - Entry point / Punkt wejścia
├── ConfigManager.java        - Configuration loading / Ładowanie konfiguracji
└── client/
    ├── AutoJoinClient.java   - Client-side logic / Logika strony klienta
    ├── ServerConnector.java  - Server connection / Połączenie serwera
    └── mixin/
        └── TitleScreenMixin.java - Title screen hook / Hook ekranu tytułu
```

## Version / Wersja:
- Minecraft: 1.20.4
- Fabric Loader: 0.14.25+
- Java: 17+
