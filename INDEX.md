# 📖 AutoJoin Minecraft Mod - Documentation Index

Welcome! Zapraszamy do dokumentacji AutoJoin Server Mod'a. Poniżej znajdziesz wszystkie materiały.

## 🚀 Start tutaj

### Jeśli wiesz co robić:
👉 [QUICK_COMMANDS.md](QUICK_COMMANDS.md) - Wszystkie komendy w jednym miejscu

### Jeśli nie wiesz od czego zacząć:
1. 👉 [CHECKLIST.md](CHECKLIST.md) - Krok po kroku instrukcje
2. 👉 [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md) - Szczegółowy guide budowania

### Jeśli cos się nie powiodło:
👉 [TROUBLESHOOTING.md](TROUBLESHOOTING.md) - Rozwiązania dla popularne problemów

---

## 📚 Kompletna lista dokumentów

### 🎮 Główne dokumenty

| Dokument | Opis | Dla kogo |
|----------|------|----------|
| **[README.md](README.md)** | Przegląd projektu, feature'y, instalacja | Wszyscy |
| **[CHECKLIST.md](CHECKLIST.md)** | ✅ Krok po kroku instrukcje | Początkujący |
| **[QUICK_COMMANDS.md](QUICK_COMMANDS.md)** | 💻 Wszystkie komendy | Zaawansowani |

### 🔨 Budowanie & Setup

| Dokument | Opis | Dla kogo |
|----------|------|----------|
| **[BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)** | Pełny guide budowania (Gradle/Maven/Docker) | Wszyscy |
| **[REQUIREMENTS.md](REQUIREMENTS.md)** | Wymagania systemowe i zmienne środowiskowe | Wszyscy |
| **setup-gradle.sh** | Automatyczny setup Gradle'a | Gradle users |
| **quickstart.sh** | One-command setup & build | Wszyscy |

### 🆘 Rozwiązywanie problemów

| Dokument | Opis | Dla kogo |
|----------|------|----------|
| **[TROUBLESHOOTING.md](TROUBLESHOOTING.md)** | Problemy & rozwiązania | Wszyscy |

### ⚙️ Konfiguracja

| Plik | Opis |
|------|------|
| **config_example.json** | Przykładowa konfiguracja |
| **[MOD_README.md](MOD_README.md)** | Informacje o mod'zie |
| **[PLUGIN_INFO.md](PLUGIN_INFO.md)** | Info dla programistów |

### 📋 Konfiguracja budowania

| Plik | Opis |
|------|------|
| **build.gradle** | Gradle configuration |
| **gradle.properties** | Gradle properties (versions) |
| **settings.gradle** | Gradle settings |
| **pom.xml** | Maven configuration (alternatywa) |
| **gradle/wrapper/** | Gradle wrapper files |

### 📄 Licencja & Info

| Plik | Opis |
|------|------|
| **LICENSE** | MIT License |
| **.gitignore** | Git ignore patterns |

---

## 🎯 Scenariusze użycia

### Scenario 1: "Chcę szybko zainstalować mod"
```
1. Przeczytaj: CHECKLIST.md
2. Uruchom: ./quickstart.sh
3. Skonfiguruj: config_example.json
4. Gotowe!
```

### Scenario 2: "Chcę zbudować sam z kodu"
```
1. Sprawdź: REQUIREMENTS.md (wymagania)
2. Postępuj: BUILD_INSTRUCTIONS.md
3. Zainstaluj: CHECKLIST.md (Installation section)
```

### Scenario 3: "Coś mi się nie buduje"
```
1. Czytaj: TROUBLESHOOTING.md
2. Spróbuj rozwiązania
3. Jeśli dalej nie działa → GitHub Issues
```

### Scenario 4: "Mod jest zainstalowany, ale nie dołącza"
```
1. Otwórz: TROUBLESHOOTING.md
2. Sekcja: "Mod się zainstalował, ale nie dołącza"
3. Edytuj config: ~/.minecraft/config/autojoin/autojoin.json
```

### Scenario 5: "Szukam szybkich komend"
```
1. Otwórz: QUICK_COMMANDS.md
2. Kopiuj/wykonuj polecenia
```

---

## 💡 Quick Reference

### Najczęstsze komendy:

```bash
# Setup
chmod +x quickstart.sh && ./quickstart.sh

# Build
./gradlew build

# Install
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/

# Config
nano ~/.minecraft/config/autojoin/autojoin.json
```

### Najczęstsze problemy:

| Problem | Rozwiązanie |
|---------|------------|
| `chmod +x gradlew` nie działa | `chmod +x gradlew` |
| Java nie znaleziona | Zainstaluj Java 17+ |
| gradle-wrapper.jar brakuje | Uruchom `./setup-gradle.sh` |
| Mod się nie pojawia | Sprawdź `~/.minecraft/mods/` |
| Nie dołącza do serwera | Edytuj `autojoin.json` i zwiększ delay |

---

## 📊 Struktura plików

```
ZwierzLauncher/
├── 📖 Dokumentacja
│   ├── README.md                      ← Start tutaj!
│   ├── CHECKLIST.md                   ← Krok po kroku
│   ├── BUILD_INSTRUCTIONS.md          ← Jak budować
│   ├── QUICK_COMMANDS.md              ← Szybkie komendy
│   ├── REQUIREMENTS.md                ← Wymagania
│   ├── TROUBLESHOOTING.md             ← Problemy
│   ├── MOD_README.md                  ← Info o mod'zie
│   ├── PLUGIN_INFO.md                 ← Dla programistów
│   └── INDEX.md                       ← Tego pliku!
│
├── 🔨 Budowanie
│   ├── build.gradle                   ← Gradle config
│   ├── gradle.properties              ← Gradle properties
│   ├── gradle/wrapper/                ← Gradle wrapper
│   ├── settings.gradle                ← Gradle settings
│   ├── pom.xml                        ← Maven config
│   ├── gradlew / gradlew.bat          ← Gradle scripts
│   ├── setup-gradle.sh                ← Setup script
│   └── quickstart.sh                  ← Quick start script
│
├── 💾 Kod źródłowy
│   └── src/main/
│       ├── java/com/zwierz/autojoin/
│       │   ├── AutoJoinMod.java
│       │   ├── ConfigManager.java
│       │   ├── client/
│       │   └── mixin/
│       └── resources/
│           ├── fabric.mod.json
│           ├── autojoin.mixins.json
│           └── assets/
│
├── ⚙️ Konfiguracja
│   ├── config_example.json            ← Przykład config
│   ├── .gitignore
│   └── LICENSE
│
└── 📦 Wynik budowania
    ├── build/libs/autojoin-1.0.0.jar  ← Finalny JAR (Gradle)
    └── target/autojoin-1.0.0.jar      ← Finalny JAR (Maven)
```

---

## 🔗 Powiązane linki

- **GitHub**: https://github.com/kora-derewa/ZwierzLauncher
- **Fabric**: https://fabricmc.net/
- **Minecraft**: https://minecraft.net/
- **Java**: https://adoptium.net/

---

## ❓ FAQ

**P: Od czego zacząć?**
O: Przeczytaj [README.md](README.md), potem [CHECKLIST.md](CHECKLIST.md)

**P: Jak szybko zainstalować mod?**
O: Uruchom `./quickstart.sh`

**P: Jakie są wymagania?**
O: Java 17+, Minecraft 1.20.4, Fabric. Szczegóły w [REQUIREMENTS.md](REQUIREMENTS.md)

**P: Nie umiem programować, czy mogę go użyć?**
O: Tak! Wystarczy zainstalować JAR i skonfigurować JSON.

**P: Mogę modyfikować kod?**
O: Tak, jest open source (MIT License)

**P: Gdzie znaleźć pomoc?**
O: [TROUBLESHOOTING.md](TROUBLESHOOTING.md) lub GitHub Issues

---

## 🎓 Dla programistów

Jeśli chcesz modify'ować kod:

1. Przeczytaj [PLUGIN_INFO.md](PLUGIN_INFO.md)
2. Zobacz strukturę w [src/main/java/](src/main/java/)
3. Zmodyfikuj zgodnie z potrzebami
4. Zbuduj: `./gradlew build`
5. Przetestuj w Minecraft'ie

---

## 🚀 Status

✅ **Production Ready** - Mod jest gotowy do użytku!

- ✅ Stabilny build
- ✅ Pełna dokumentacja
- ✅ Obsługa błędów
- ✅ Open source

---

**Last Updated:** May 23, 2026  
**Version:** 1.0.0  
**License:** MIT  

---

**Powodzenia! Mamy nadzieję, że ten mod ci się spodoba! 🎮✨**
