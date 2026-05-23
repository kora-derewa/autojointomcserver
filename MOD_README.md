# AutoJoin Server Mod

> **⭐ Główna dokumentacja**: Czytaj [README.md](README.md)

Automatyczne dołączanie na serwer Minecraft dla Fabric.

## Szybki start

1. Pobierz plik `.jar` z [Releases](https://github.com/kora-derewa/ZwierzLauncher/releases)
2. Umieść go w folderze `~/.minecraft/mods/`
3. Uruchom launcher Minecraft (Fabric 1.20.4)
4. Edytuj config: `~/.minecraft/config/autojoin/autojoin.json`
5. Uruchom grę

## Konfiguracja

Plik konfiguracji tworzy się automatycznie: `.minecraft/config/autojoin/autojoin.json`

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

- **enabled** (boolean) - Włącz/wyłącz mod
- **serverAddress** (string) - Adres IP lub domena serwera
- **serverPort** (int) - Port serwera (domyślnie 25565)
- **delayMs** (long) - Opóźnienie przed dołączeniem w ms
- **autoJoinOnLaunch** (boolean) - Automatyczne dołączenie przy starcie
- **showMessages** (boolean) - Pokaż komunikaty w chacie

## Budowanie

```bash
./gradlew build
```

Wynik będzie w: `build/libs/autojoin-1.0.0.jar`

Lub użyj szybkiego startu:
```bash
./quickstart.sh
```

## Wymagania

- Minecraft 1.20.4+
- Fabric Loader 0.14.25+
- Java 17+

## Cechy

✓ Automatyczne dołączanie na serwer  
✓ Konfiguracja plikowa (JSON)  
✓ Opóźnienie przed dołączeniem  
✓ Komunikaty w chacie  
✓ Obsługa portów niestandardowych  
✓ Obsługa domen  
✓ Włączanie/wyłączanie bez restartu  

## Dokumentacja

Pełna dokumentacja w [README.md](README.md) | Więcej porad: [TROUBLESHOOTING.md](TROUBLESHOOTING.md)

## Licencja

MIT - zobacz [LICENSE](LICENSE)

---

**Wsparcie**: [GitHub Issues](https://github.com/kora-derewa/ZwierzLauncher/issues)
