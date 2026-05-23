# 📋 Wymagania systemowe

## Minimalne wymagania

| Komponent | Minimum | Rekomendowane |
|-----------|---------|---------------|
| **Java** | 17 | 17+ |
| **RAM** | 2 GB | 4 GB+ |
| **Minecraft** | 1.20.4 | 1.20.4+ |
| **Fabric Loader** | 0.14.25 | 0.14.25+ |
| **Dysk** | 50 MB | 500 MB |

## Instalacja wymaganych komponentów

### Java 17+

#### Linux (Ubuntu/Debian)
```bash
sudo apt-get update
sudo apt-get install openjdk-17-jdk
java -version
```

#### Linux (Fedora/RHEL)
```bash
sudo dnf install java-17-openjdk
java -version
```

#### macOS
```bash
brew install openjdk@17
java -version
```

#### Windows
1. Pobierz z: https://adoptium.net/
2. Zainstaluj
3. Uruchom w PowerShell: `java -version`

### Fabric Loader

1. Pobierz launcher: https://fabricmc.net/
2. Zainstaluj Fabric (wbudowany w launcher)
3. Wybierz Minecraft 1.20.4

## Sprawdzenie wymagań

```bash
# Sprawdź Java
java -version

# Sprawdź Maven (opcjonalnie)
mvn -version

# Sprawdź Git (do klonowania)
git --version
```

## Wymagania dla budowania

### Obowiązkowe
- ✅ Java 17+ (JDK)
- ✅ Git

### Opcjonalne
- ⭕ Gradle (pobierany automatycznie)
- ⭕ Maven 3.6+ (alternatywa do Gradle)
- ⭕ Docker (dla konteneryzacji)

## Zmienne środowiskowe

### JAVA_HOME

```bash
# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH

# Windows (CMD)
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

# Windows (PowerShell)
$env:JAVA_HOME="C:\Program Files\Java\jdk-17"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
```

## Port Minecraft'a

- **Domyślny port serwera:** 25565
- **Obsługiwane porty:** 1-65535

## Pojemność dysku

- **JDK 17:** ~300 MB
- **Gradle cache:** ~500 MB - 2 GB
- **Maven cache:** ~500 MB - 1 GB
- **Minecraft .minecraft:** 2-10 GB
- **Mod JAR:** ~50 KB

## Kompatybilność

### Systemy operacyjne
✅ Linux (Ubuntu, Debian, Fedora, etc.)
✅ macOS (Intel, Apple Silicon)
✅ Windows (7, 8, 10, 11)

### Minecraft wersje
✅ 1.20.4 (oficjalna obsługa)
⚠️ 1.20.x (może działać)
❌ Poniżej 1.20.4 (nie obsługiwane)

## Trybowania

Jeśli brakuje wymagań:

1. **Java**
   ```bash
   # Sprawdzenie
   java -version
   # Powinno wyświetlić: "java version "17..."
   ```

2. **Git**
   ```bash
   git --version
   ```

3. **Gradle**
   ```bash
   # Setup script
   ./setup-gradle.sh
   ```

## FAQ

**P: Mogę używać Java 11/8?**
O: Nie. Projekt wymaga Java 17+. Użyj Java 17, 18, 19, 20 lub 21.

**P: Czy to działa na Mod Loaderze X?**
O: Nie. Tylko Fabric jest obsługiwany.

**P: Ile RAM potrzebuję?**
O: Minimum 2 GB, ale rekomendujemy 4+ GB dla łagodnej gry.

**P: Czy działa na serwerach bukkit/spigot?**
O: Nie, to mod kliencki. Serwer może być vanilla/fabric/bukkit.

---

**Wątpliwości? Sprawdź [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)**
