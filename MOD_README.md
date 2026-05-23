# AutoJoin Server Mod

Automatyczne dołączanie na serwer Minecraft dla Fabric.

## Instalacja

1. Pobierz plik `.jar` z releases
2. Umieść go w folderze `mods` twojego klienta Fabric
3. Uruchom launcher

## Konfiguracja

Plik konfiguracji znajduje się w: `.minecraft/config/autojoin/autojoin.json`

### Przykładowa konfiguracja:

```json
{
  "enabled": true,
  "serverAddress": "play.example.com",
  "serverPort": 25565,
  "delayMs": 1000,
  "autoJoinOnLaunch": true,
  "showMessages": true
}
```

### Parametry:

- **enabled** - Włącz/wyłącz mod
- **serverAddress** - Adres IP lub domena serwera
- **serverPort** - Port serwera (domyślnie 25565)
- **delayMs** - Opóźnienie przed dołączeniem w milisekundach
- **autoJoinOnLaunch** - Automatyczne dołączenie przy uruchomieniu gry
- **showMessages** - Wyświetlanie komunikatów w chacie

## Budowanie

```bash
./gradlew build
```

Wynik będzie w: `build/libs/autojoin-1.0.0.jar`

## Wymagania

- Minecraft 1.20.4+
- Fabric Loader
- Java 17+

## Licencja

MIT
