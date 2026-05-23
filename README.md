# AutoJoin Minecraft Mod

[![Fabric](https://img.shields.io/badge/Fabric-1.20.4-brightgreen)](https://fabricmc.net)
[![Java](https://img.shields.io/badge/Java-17+-red)](https://adoptium.net)
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)
[![Version](https://img.shields.io/badge/Version-1.0.0-blue)](https://github.com/kora-derewa/autojointomcserver)

Fabric mod do automatycznego dołączania na serwer Minecraft z konfiguracjąplikową JSON.

---

## Spis treści

- [Szybki Start](#szybki-start)
- [Funkcje](#funkcje)
- [Wymagania](#wymagania)
- [Instalacja](#instalacja)
- [Budowanie](#budowanie)
- [Konfiguracja](#konfiguracja)
- [Struktura projektu](#struktura-projektu)
- [Dla programistów](#dla-programistów)
- [Troubleshooting](#troubleshooting)
- [Licencja](#licencja)

---

## Szybki Start

```bash
git clone git@github.com:kora-derewa/autojointomcserver.git
cd autojointomcserver
chmod +x gradlew
./gradlew build
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
```

Po uruchomieniu Minecrafta edytuj `~/.minecraft/config/autojoin/autojoin.json` i ustaw adres swojego serwera.

---

## Funkcje

- Automatyczne dołączanie na wybrany serwer po starcie gry
- Konfiguracja przez prosty plik JSON
- Obsługa domen i niestandardowych portów
- Regulowane opóźnienie przed dołączeniem
- Komunikaty w chacie
- Włącz/wyłącz bez restartu gry
- Przycisk "Spróbuj ponownie" na ekranie błędu połączenia
- Open source (MIT)

---

## Wymagania

| Komponent | Minimalna wersja |
|-----------|-----------------|
| Java | 17+ (zalecane 21) |
| Minecraft | 1.20.4 |
| Fabric Loader | 0.14.25+ |

### Instalacja Javy

**Linux (Fedora):**
```bash
sudo dnf install java-21-openjdk-devel
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt-get install openjdk-17-jdk
```

**macOS:**
```bash
brew install openjdk@17
```

**Windows:** Pobierz z [adoptium.net](https://adoptium.net)

### Instalacja Fabric Loader

1. Pobierz installer z [fabricmc.net](https://fabricmc.net)
2. Zainstaluj dla Minecraft 1.20.4
3. W launcherze wybierz profil Fabric

---

## Instalacja

### Linux/macOS
```bash
mkdir -p ~/.minecraft/mods
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
```

### Windows
```cmd
mkdir "%APPDATA%\.minecraft\mods"
copy build\libs\autojoin-1.0.0.jar "%APPDATA%\.minecraft\mods\"
```

---

## Budowanie

### Wymagania

- Java 17+
- Git

### Krok po kroku

```bash
# 1. Sklonuj repozytorium
git clone git@github.com:kora-derewa/autojointomcserver.git
cd autojointomcserver

# 2. Zbuduj mod
chmod +x gradlew
./gradlew build

# 3. Zainstaluj
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
```

Jeśli masz Javę 25 (Fedora 43), użyj:
```bash
./gradlew build -Dorg.gradle.java.home=/usr/lib/jvm/java-21-openjdk
```

### Przydatne komendy

| Komenda | Opis |
|---------|------|
| `./gradlew build` | Budowanie moda |
| `./gradlew clean` | Czyszczenie artefaktów |
| `./gradlew build --no-build-cache` | Budowanie bez cache |
| `./gradlew build --stacktrace` | Budowanie z debugowaniem |
| `./gradlew dependencies` | Lista zależności |

---

## Konfiguracja

Plik konfiguracji tworzy się automatycznie przy pierwszym uruchomieniu:
```
~/.minecraft/config/autojoin/autojoin.json
```

### Parametry

```json
{
  "enabled": true,
  "serverAddress": "mc.example.com",
  "serverPort": 25565,
  "delayMs": 1000,
  "autoJoinOnLaunch": true,
  "showMessages": true,
  "muteMusic": true,
  "startupMusic": "",
  "showCancelButton": true
}
```

| Parametr | Typ | Domyślnie | Opis |
|----------|-----|-----------|------|
| `enabled` | boolean | `true` | Włącz/wyłącz mod |
| `serverAddress` | string | `localhost` | Adres IP lub domena serwera |
| `serverPort` | int | `25565` | Port serwera |
| `delayMs` | long | `1000` | Opóźnienie przed dołączeniem (ms) |
| `autoJoinOnLaunch` | boolean | `true` | Auto-dołączenie przy starcie |
| `showMessages` | boolean | `true` | Komunikaty w chacie |
| `muteMusic` | boolean | `true` | Wycisza całą muzykę w tle |
| `startupMusic` | string | `""` | Sound event odtwarzany na ekranie tytułu (np. `minecraft:music_disc.13`, `minecraft:entity.experience_orb.pickup`). Puste = brak muzyki startowej |
| `showCancelButton` | boolean | `true` | Pokazuj przycisk anulowania auto-join na ekranie tytułu |

### Przykłady

**Hypixel:**
```json
{
  "enabled": true,
  "serverAddress": "mc.hypixel.net",
  "serverPort": 25565,
  "delayMs": 2000,
  "autoJoinOnLaunch": true,
  "showMessages": true
}
```

**Serwer lokalny:**
```json
{
  "enabled": true,
  "serverAddress": "127.0.0.1",
  "serverPort": 25565,
  "delayMs": 500,
  "autoJoinOnLaunch": true,
  "showMessages": false
}
```

---

## Struktura projektu

```
autojointomcserver/
├── build.gradle                 # Konfiguracja Gradle
├── gradle.properties            # Wersje zależności
├── settings.gradle              # Ustawienia Gradle
├── gradlew / gradlew.bat       # Gradle wrapper
├── gradle/wrapper/              # Pliki wrappera
├── config_example.json          # Przykładowa konfiguracja
├── LICENSE                      # Licencja MIT
├── README.md                    # Ten plik
└── src/main/java/com/zwierz/autojoin/
    ├── AutoJoinMod.java         # Entry point moda
    ├── ConfigManager.java       # Zarządzanie konfiguracją
    ├── client/
    │   ├── AutoJoinClient.java  # Logika dołączania
    │   └── ServerConnector.java # Połączenie z serwerem
    └── mixin/
        └── TitleScreenMixin.java # Hook ekranu tytułu
```

---

## Dla programistów

### Struktura kodu

- **AutoJoinMod.java** – punkt wejścia moda, inicjalizacja Fabric
- **ConfigManager.java** – ładowanie i walidacja JSON przez GSON
- **AutoJoinClient.java** – event listenery ticków klienta, logika dołączania
- **ServerConnector.java** – łączenie z serwerem przez ConnectScreen
- **TitleScreenMixin.java** – hook na ekran tytułu

### Development

```bash
# Edytuj kod, potem przebuduj
./gradlew build

# Zainstaluj i testuj
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
# Uruchom Minecraft Fabric 1.20.4
```

### Zmiana wersji Minecraft

Edytuj `gradle.properties`:
```properties
minecraft_version=1.20.4
loader_version=0.14.25
fabric_version=0.97.0+1.20.4
```

---

## Troubleshooting

### Mod nie pojawia się w grze

```bash
# Sprawdź czy JAR istnieje
ls -la ~/.minecraft/mods/ | grep autojoin

# Sprawdź logi Minecrafta
tail -50 ~/.minecraft/logs/latest.log | grep -i autojoin
```

**Przyczyny:**
- JAR nie jest w `~/.minecraft/mods/`
- Nie używasz profilu Fabric
- Zła wersja Minecrafta (wymagana 1.20.4)

### Mod nie dołącza do serwera

```bash
# Sprawdź config
cat ~/.minecraft/config/autojoin/autojoin.json

# Sprawdź czy serwer odpowiada
ping twoj-serwer.com
```

**Rozwiązania:**
- Zwiększ `delayMs` (np. do 3000)
- Sprawdź czy `enabled` jest `true`
- Sprawdź adres i port serwera

### Błąd budowania

**"./gradlew: command not found"**
```bash
chmod +x gradlew
```

**"Java 25 unsupported"**
```bash
./gradlew build -Dorg.gradle.java.home=/usr/lib/jvm/java-21-openjdk
```

**"gradle-wrapper.jar not found"**
```bash
mkdir -p gradle/wrapper
curl -Lo gradle/wrapper/gradle-wrapper.jar \
  https://github.com/gradle/gradle/raw/v8.7.0/gradle/wrapper/gradle-wrapper.jar
```

**"BUILD FAILED" – inne błędy**
```bash
# Wyczyść cache i przebuduj
./gradlew clean
rm -rf .gradle/loom-cache
./gradlew build --refresh-dependencies -Dorg.gradle.java.home=/usr/lib/jvm/java-21-openjdk
```

### Sprawdzanie wersji

```bash
java -version
./gradlew -version
```

---

## Licencja

MIT License. Szczegóły w pliku [LICENSE](LICENSE).

---

## Kontakt

- GitHub: [@kora-derewa](https://github.com/kora-derewa)
- Issues: [utwórz zgłoszenie](https://github.com/kora-derewa/autojointomcserver/issues)
