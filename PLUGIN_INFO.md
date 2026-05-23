# Fabric Plugin - AutoJoin Server

> **⭐ Główna dokumentacja**: Czytaj [README.md](README.md)

Fabric plugin do automatycznego dołączania na serwer Minecraft.

## 📋 Dla użytkowników

Jeśli szukasz instrukcji instalacji, czytaj: **[README.md](README.md)**

## 👨‍💻 Dla programistów

### Instalacja w ~/.minecraft/mods/:
```bash
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
```

### Konfiguracja znajduje się w:
```bash
~/.minecraft/config/autojoin/autojoin.json
```

## 🏗️ Struktura kodu

```
src/main/java/com/zwierz/autojoin/
├── AutoJoinMod.java              # Entry point
├── ConfigManager.java            # Zarządzanie konfiguracją
└── client/
    ├── AutoJoinClient.java       # Logika strony klienta
    ├── ServerConnector.java      # Połączenie z serwerem
    └── mixin/
        └── TitleScreenMixin.java # Hook ekranu tytułu
```

### Główne klasy

**AutoJoinMod.java**
- Entry point modu
- Inicjalizacja Fabric mod initializer

**ConfigManager.java**
- Ładowanie i walidacja konfiguracji JSON
- GSON do parsowania pliku
- Obsługa błędów i domyślne wartości

**AutoJoinClient.java**
- Logika inicjacji klienta
- Event listeners dla client tick
- Dołączanie do serwera

**ServerConnector.java**
- Połączenie z serwerem
- Użycie ServerAddress
- ConnectScreen do interfejsu

**TitleScreenMixin.java**
- Mixins do modyfikacji ekranu tytułu
- Hook inicjalizacji

## 🔧 Budowanie

```bash
# Gradle
./gradlew build

# Maven
mvn clean package

# Quick start
./quickstart.sh
```

## 📦 Zależności

- Minecraft 1.20.4
- Fabric Loader 0.14.25+
- Java 17+
- Gson (do JSONa)

## 📁 Zasoby

```
src/main/resources/
├── fabric.mod.json                    # Metadane modu
├── autojoin.mixins.json              # Konfiguracja mixinów
└── assets/autojoin/lang/
    └── pl_pl.json                     # Tłumaczenia
```

## 🔐 Wymagania

- Java 17+
- Gradle 8.5+
- Git (do klonowania)

## 📚 Dokumentacja

| Plik | Opis |
|------|------|
| [README.md](README.md) | Główna dokumentacja |
| [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md) | Instrukcje budowania |
| [QUICK_COMMANDS.md](QUICK_COMMANDS.md) | Komendy dla programistów |
| [TROUBLESHOOTING.md](TROUBLESHOOTING.md) | Rozwiązywanie problemów |

## 🚀 Development

### Setup:
```bash
git clone https://github.com/kora-derewa/ZwierzLauncher.git
cd ZwierzLauncher
./gradlew build
```

### Zmiany kodu:
```bash
# Edytuj kod
vim src/main/java/com/zwierz/autojoin/...

# Rebuild
./gradlew build

# Test
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
```

### Edycja konfiguracji:
1. Edytuj `gradle.properties` aby zmienić wersje
2. Uruchom `./gradlew build`

## 📝 Licencja

MIT - zobacz [LICENSE](LICENSE)

---

**Kontakt**: [@kora-derewa](https://github.com/kora-derewa)  
**Issues**: [GitHub](https://github.com/kora-derewa/ZwierzLauncher/issues)
