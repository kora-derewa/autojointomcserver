# 🎮 AutoJoin Minecraft Server Mod

[![Fabric](https://img.shields.io/badge/Fabric-1.20.4-brightgreen)](https://fabricmc.net)
[![Java](https://img.shields.io/badge/Java-17+-red)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)

Automatyczne dołączanie na serwer Minecraft z konfiguracją plikową.

## 📋 Spis treści

- [Szybki Start](#szybki-start)
- [Instalacja](#instalacja)
- [Konfiguracja](#konfiguracja)
- [Budowanie](#budowanie)
- [Wymagania](#wymagania)
- [Funkcje](#funkcje)
- [Rozwiązywanie problemów](#rozwiązywanie-problemów)

## 🚀 Szybki Start

### Opcja 1: Pobierz gotowy JAR
1. Przejdź do [Releases](https://github.com/kora-derewa/ZwierzLauncher/releases)
2. Pobierz `autojoin-1.0.0.jar`
3. Umieść w `~/.minecraft/mods/`
4. Uruchom grę

### Opcja 2: Zbuduj sam
```bash
git clone https://github.com/kora-derewa/ZwierzLauncher.git
cd ZwierzLauncher
./gradlew build
```

## 📦 Instalacja

1. **Pobierz JAR**
   - Z releases lub zbuduj lokalnie
   
2. **Umieść w folder mods**
   ```
   ~/.minecraft/mods/autojoin-1.0.0.jar
   ```

3. **Uruchom Minecraft** (Fabric)
   - Mod automatycznie się zainstaluje

## ⚙️ Konfiguracja

Plik konfiguracji tworzony automatycznie:
```
~/.minecraft/config/autojoin/autojoin.json
```

### Domyślna konfiguracja:
```json
{
  "enabled": true,
  "serverAddress": "mc.example.com",
  "serverPort": 25565,
  "delayMs": 1000,
  "autoJoinOnLaunch": true,
  "showMessages": true
}
```

### Parametry:
| Parameter | Typ | Opis |
|-----------|-----|------|
| `enabled` | boolean | Włącz/wyłącz mod |
| `serverAddress` | string | Adres IP lub domena serwera |
| `serverPort` | int | Port serwera (1-65535) |
| `delayMs` | long | Opóźnienie przed dołączeniem (ms) |
| `autoJoinOnLaunch` | boolean | Dołącz przy starcie gry |
| `showMessages` | boolean | Pokaż komunikaty w chacie |

## 🔨 Budowanie

### Wymagania
- **Java 17+**
- **Git**
- **Gradle** (opcjonalnie)

### Krok po kroku

```bash
# Clone
git clone https://github.com/kora-derewa/ZwierzLauncher.git
cd ZwierzLauncher

# Zbuduj
./gradlew build

# JAR będzie w:
# build/libs/autojoin-1.0.0.jar
```

**[👉 Szczegółowe instrukcje budowania](BUILD_INSTRUCTIONS.md)**

## ⚡ Wymagania

- **Minecraft:** 1.20.4+
- **Fabric Loader:** 0.14.25+
- **Java:** 17+ (JDK)
- **RAM:** 512MB (minimalnie)

## ✨ Funkcje

✅ Automatyczne dołączanie do serwera  
✅ Konfiguracja JSON (łatwa do edycji)  
✅ Obsługa domen i IP  
✅ Regulowalne porty  
✅ Opóźnienie przed dołączeniem  
✅ Komunikaty w chacie  
✅ Włączanie/wyłączanie bez restartu gry  
✅ Wielojęzyczne tłumaczenia (PL, EN)  

## 🐛 Rozwiązywanie problemów

### Mod się nie zainstalował
- Upewnij się, że używasz Fabric Loader
- Sprawdź czy JAR jest w `~/.minecraft/mods/`
- Restart laundera

### Nie dołącza do serwera
- Sprawdź adres w `autojoin.json`
- Upewnij się że serwer jest online
- Zwiększ `delayMs` w konfiguracji

### Błąd przy budowaniu
- Zainstaluj Java 17+: `java -version`
- Uruchom: `./setup-gradle.sh`
- [Więcej porad](BUILD_INSTRUCTIONS.md#rozwiązywanie-problemów)

## 📁 Struktura projektu

```
ZwierzLauncher/
├── src/main/
│   ├── java/com/zwierz/autojoin/
│   │   ├── AutoJoinMod.java
│   │   ├── ConfigManager.java
│   │   ├── client/
│   │   └── mixin/
│   └── resources/
│       ├── fabric.mod.json
│       ├── autojoin.mixins.json
│       └── assets/
├── gradle/wrapper/
├── build.gradle
├── gradle.properties
└── README.md
```

## 📝 Licencja

MIT License - zobacz [LICENSE](LICENSE) plik

## 👥 Kontakt

- GitHub: [@kora-derewa](https://github.com/kora-derewa)
- Issues: [Report bug](https://github.com/kora-derewa/ZwierzLauncher/issues)

## 🤝 Contributing

Wkłady są mile widziane! Proszę:
1. Fork projekt
2. Utwórz branch (`git checkout -b feature/AmazingFeature`)
3. Commit zmiany (`git commit -m 'Add AmazingFeature'`)
4. Push do branch (`git push origin feature/AmazingFeature`)
5. Otwórz Pull Request

---

**Built with ❤️ for Minecraft community**
