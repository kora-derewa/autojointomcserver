# 🎮 AutoJoin Minecraft Server Mod

[![Fabric](https://img.shields.io/badge/Fabric-1.20.4-brightgreen)](https://fabricmc.net)
[![Java](https://img.shields.io/badge/Java-17+-red)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)
[![Version](https://img.shields.io/badge/Version-1.0.0-blue)](https://github.com/kora-derewa/ZwierzLauncher/releases)

**Automatyczne dołączanie na serwer Minecraft z konfiguracją plikową**

Fabric mod ułatwiający automatyczne łączenie się z wybranym serwerem Minecraft przy każdym uruchomieniu gry. Kontrola poprzez prosty plik JSON.

---

## 📋 Spis treści

- [🚀 Szybki Start](#-szybki-start)
- [📦 Instalacja](#-instalacja)
- [⚙️ Konfiguracja](#️-konfiguracja)
- [🔨 Budowanie](#-budowanie)
- [✨ Funkcje](#-funkcje)
- [⚡ Wymagania systemowe](#-wymagania-systemowe)
- [📁 Struktura projektu](#-struktura-projektu)
- [🐛 Troubleshooting](#-troubleshooting)
- [📚 Dokumentacja](#-dokumentacja)
- [📝 Licencja](#-licencja)
- [👥 Kontakt](#-kontakt)

---

## 🚀 Szybki Start

### Opcja 1: Pobierz gotowy JAR (Najprościej - 3 minuty)
```bash
# 1. Pobierz z releases
# 2. Umieść w ~/.minecraft/mods/autojoin-1.0.0.jar
# 3. Uruchom grę
```

### Opcja 2: Zbuduj sam (5 minut)
```bash
# 1. Klonuj repozytorium
git clone https://github.com/kora-derewa/ZwierzLauncher.git
cd ZwierzLauncher

# 2. Użyj quickstart (wszystko automatycznie)
chmod +x quickstart.sh
./quickstart.sh

# 3. Skopiuj JAR
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
```

### Opcja 3: Ręczne budowanie
```bash
git clone https://github.com/kora-derewa/ZwierzLauncher.git
cd ZwierzLauncher
chmod +x gradlew
./gradlew build
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
```

**Gotowe! 🎉 Teraz skonfiguruj serwer (patrz poniżej)**

---

## 📦 Instalacja

### Wymagania przed instalacją:
- ✅ **Minecraft 1.20.4**
- ✅ **Fabric Loader** zainstalowany
- ✅ **Java 17+**

### Kroki instalacji:

1. **Pobierz JAR**
   - Ze strony [Releases](https://github.com/kora-derewa/ZwierzLauncher/releases)
   - Lub zbuduj lokalnie (patrz [Budowanie](#-budowanie))

2. **Umieść w folder mods**
   ```bash
   ~/.minecraft/mods/autojoin-1.0.0.jar
   ```
   
   Ścieżki dla innych systemów:
   - **Windows**: `%APPDATA%\.minecraft\mods\autojoin-1.0.0.jar`
   - **macOS**: `~/Library/Application Support/minecraft/mods/autojoin-1.0.0.jar`
   - **Linux**: `~/.minecraft/mods/autojoin-1.0.0.jar`

3. **Uruchom Minecraft**
   - Wybierz profil Fabric 1.20.4
   - Gra uruchomi się i mod zainstaluje się automatycznie

4. **Skonfiguruj** (patrz [Konfiguracja](#️-konfiguracja))

---

## ⚙️ Konfiguracja

### Lokalizacja pliku konfiguracji:
```
~/.minecraft/config/autojoin/autojoin.json
```

Plik tworzy się **automatycznie** przy pierwszym uruchomieniu gry z mod'em.

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

### Parametry konfiguracji:

| Parametr | Typ | Domyślnie | Opis |
|----------|-----|----------|------|
| `enabled` | boolean | `true` | Włącz/wyłącz mod |
| `serverAddress` | string | `localhost` | Adres IP lub domena serwera |
| `serverPort` | int | `25565` | Port serwera (1-65535) |
| `delayMs` | long | `1000` | Opóźnienie przed dołączeniem (milisekundy) |
| `autoJoinOnLaunch` | boolean | `true` | Automatyczne dołączenie przy starcie gry |
| `showMessages` | boolean | `true` | Wyświetlanie komunikatów w chacie |

### Przykłady konfiguracji:

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

**Lokalny serwer:**
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

**Serwer z niestandardowym portem:**
```json
{
  "enabled": true,
  "serverAddress": "moj-serwer.example.com",
  "serverPort": 12345,
  "delayMs": 1500,
  "autoJoinOnLaunch": true,
  "showMessages": true
}
```

---

## 🔨 Budowanie

### Wymagania do budowania:
- **Java 17+** (`java -version`)
- **Git** (opcjonalnie)

### Metoda 1: Quick Start (Rekomendowane)
```bash
chmod +x quickstart.sh
./quickstart.sh
```

JAR będzie w: `build/libs/autojoin-1.0.0.jar`

### Metoda 2: Ręczne (Gradle)
```bash
chmod +x gradlew
./gradlew build
```

### Metoda 3: Maven (Alternatywa)
```bash
mvn clean package
```

JAR będzie w: `target/autojoin-1.0.0.jar`

### Metoda 4: Bez cache'u
```bash
./gradlew clean build
```

**[👉 Szczegółowe instrukcje](BUILD_INSTRUCTIONS.md) | [👉 Troubleshooting](TROUBLESHOOTING.md)**

---

## ✨ Funkcje

✅ **Automatyczne dołączanie** na wybrany serwer  
✅ **Konfiguracja JSON** - łatwa do edycji  
✅ **Obsługa domen i IP** - play.example.com lub 1.2.3.4  
✅ **Niestandardowe porty** - nie tylko 25565  
✅ **Regulowalne opóźnienie** - przed dołączeniem  
✅ **Komunikaty w chacie** - informacje o dołączeniu  
✅ **Włącz/wyłącz bez restartu** - zmień config, zmianę widać natychmiast  
✅ **Wielojęzyczne** - Polska i Angielska  
✅ **Open Source** - MIT License  
✅ **Zero lag** - optymalny kod  

---

## ⚡ Wymagania systemowe

### Minimalne:
| Komponent | Minimum |
|-----------|---------|
| **Java** | 17+ |
| **Minecraft** | 1.20.4 |
| **Fabric Loader** | 0.14.25+ |
| **RAM** | 512 MB (dla modu) |
| **Dysk** | 50 KB (jar) |

### Rekomendowane:
| Komponent | Rekomendacja |
|-----------|------------|
| **Java** | 17, 18, 19, 20 lub 21 |
| **Minecraft** | 1.20.4 (lub nowsza) |
| **RAM** | 2-4 GB |

### Instalacja zależności:

**Java 17 - Ubuntu/Debian:**
```bash
sudo apt-get install openjdk-17-jdk
```

**Java 17 - Fedora:**
```bash
sudo dnf install java-17-openjdk
```

**Java 17 - macOS:**
```bash
brew install openjdk@17
```

**Java 17 - Windows:**
- Pobierz z: https://adoptium.net/
- Zainstaluj

---

## 📁 Struktura projektu

### Pełna struktura:
```
ZwierzLauncher/
│
├── 📚 Dokumentacja
│   ├── README.md                    ← TEN PLIK (główny)
│   ├── START_HERE.md               ← Szybki start
│   ├── CHECKLIST.md                ← Krok po kroku
│   ├── BUILD_INSTRUCTIONS.md       ← Budowanie
│   ├── QUICK_COMMANDS.md           ← Komendy
│   ├── REQUIREMENTS.md             ← Wymagania
│   ├── TROUBLESHOOTING.md          ← Problemy
│   └── ... (10+ plików)
│
├── 💾 Kod źródłowy
│   └── src/main/java/com/zwierz/autojoin/
│       ├── AutoJoinMod.java              # Entry point
│       ├── ConfigManager.java            # Zarządzanie konfiguracją
│       ├── client/
│       │   ├── AutoJoinClient.java       # Logika dołączania
│       │   └── ServerConnector.java      # Łącznik serwera
│       └── mixin/
│           └── TitleScreenMixin.java     # Hook ekranu tytułu
│
├── 🔨 Budowanie
│   ├── build.gradle                 # Gradle config
│   ├── gradle.properties
│   ├── pom.xml                      # Maven config
│   └── gradle/wrapper/
│
├── 🟢 Skrypty
│   ├── quickstart.sh                # One-command build
│   ├── setup-gradle.sh              # Setup Gradle
│   └── fix-gradlew.sh               # Naprawa
│
└── ⚙️ Config
    └── config_example.json          # Przykład
```

### Główne pliki kodu:

| Plik | Linie | Opis |
|------|-------|------|
| `AutoJoinMod.java` | 20 | Punkt wejścia, logika inicjalizacji |
| `ConfigManager.java` | 120 | Ładowanie i walidacja konfiguracji |
| `AutoJoinClient.java` | 70 | Dołączanie do serwera |
| `ServerConnector.java` | 30 | Połączenie z serwerem |
| `TitleScreenMixin.java` | 25 | Hook na ekran tytułu |

---

## 🐛 Troubleshooting

### Problem: Mod się nie pojawia w grze

**Powody:**
- JAR nie jest w dobrym folderze
- Nie używasz Fabric Loadera
- Zła wersja Minecraft'a

**Rozwiązanie:**
```bash
# Sprawdź czy JAR jest na miejscu
ls ~/.minecraft/mods/ | grep autojoin

# Upewnij się że Fabric 1.20.4 jest zainstalowany
# Uruchom launcher i sprawdź wersję Fabric
```

### Problem: Mod nie dołącza do serwera

**Powody:**
- Zły adres serwera w config
- Serwer jest offline
- Opóźnienie za krótkie

**Rozwiązanie:**
```bash
# 1. Edytuj config
nano ~/.minecraft/config/autojoin/autojoin.json

# 2. Zmień:
"serverAddress": "poprawny-adres.com"
"delayMs": 2000  # Zwiększ jeśli nie dołącza

# 3. Test ping
ping twoj-serwer.com
```

### Problem: Błąd przy budowaniu "./gradlew"

**Rozwiązanie:**
```bash
chmod +x gradlew
./gradlew build
```

**Lub:**
```bash
./fix-gradlew.sh
```

### Problem: "JAVA_HOME is not set"

```bash
# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH

# Windows (CMD)
set JAVA_HOME=C:\Program Files\Java\jdk-17
```

**[👉 Więcej porad](TROUBLESHOOTING.md)**

---

## 📚 Dokumentacja

| Dokument | Opis |
|----------|------|
| **[README.md](README.md)** | Ten plik - wszystko w jednym |
| **[START_HERE.md](START_HERE.md)** | 3-minutowy quick start |
| **[CHECKLIST.md](CHECKLIST.md)** | Krok po kroku instrukcje |
| **[BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)** | Pełne instrukcje budowania (4 metody) |
| **[QUICK_COMMANDS.md](QUICK_COMMANDS.md)** | Wszystkie komendy w jednym miejscu |
| **[REQUIREMENTS.md](REQUIREMENTS.md)** | Wymagania systemowe |
| **[TROUBLESHOOTING.md](TROUBLESHOOTING.md)** | Rozwiązywanie problemów |
| **[FIX_GRADLEW_ERROR.md](FIX_GRADLEW_ERROR.md)** | Naprawa błędu gradlew |
| **[INDEX.md](INDEX.md)** | Pełny spis wszystkich plików |
| **[FILES_MANIFEST.md](FILES_MANIFEST.md)** | Lista i statystyka plików |

---

## 📝 Licencja

MIT License - plik [LICENSE](LICENSE)

Wolne do użytku, modyfikacji i dystrybucji.

```
Copyright (c) 2024 kora-derewa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction...
```

---

## 👥 Kontakt

- **GitHub**: [@kora-derewa](https://github.com/kora-derewa)
- **Projekt**: [ZwierzLauncher](https://github.com/kora-derewa/ZwierzLauncher)
- **Issues/Bugs**: [Report bug](https://github.com/kora-derewa/ZwierzLauncher/issues)

---

## 🤝 Contributing

Wkłady są mile widziane! 

### Jak wnieść wkład:

1. **Fork projekt**
   ```bash
   git clone https://github.com/TWOJE-KONTO/ZwierzLauncher.git
   ```

2. **Utwórz feature branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```

3. **Commit zmiany**
   ```bash
   git commit -m 'Add AmazingFeature'
   ```

4. **Push do branch**
   ```bash
   git push origin feature/AmazingFeature
   ```

5. **Otwórz Pull Request**

### Co możesz zmienić:
- 🐛 Naprawić błędy
- ✨ Dodać nowe funkcje
- 📖 Poprawić dokumentację
- 🌍 Dodać tłumaczenia
- 💪 Ulepszyć kod

---

## 📊 Statystyka projektu

- **Pliki Java**: 5 (265 linii kodu)
- **Dokumentacja**: 13 plików
- **Konfiguracja**: Gradle + Maven
- **Licencja**: MIT
- **Wersja**: 1.0.0
- **Status**: ✅ Production Ready

---

## 🎯 Roadmap

- ✅ Automatyczne dołączanie
- ✅ Konfiguracja JSON
- ✅ Wsparcie portów niestandardowych
- ⏳ GUI config (planowane)
- ⏳ Multi-server (planowane)
- ⏳ Scheduler (planowane)

---

## 🙏 Podziękowania

Dziękujemy wszystkim użytkownikom i sympatykom projektu!

---

**Built with ❤️ for Minecraft community**

Jeśli potrzebujesz pomocy, czytaj dokumentację lub zgłoś issue! 🚀
