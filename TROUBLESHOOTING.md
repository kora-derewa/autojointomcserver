# 🔧 Troubleshooting Guide

## Problemy przy budowaniu

### ❌ "Unknown command './gradlew'"

**Problem:** Gradlew nie jest plikiem wykonywalnym

**Rozwiązanie:**
```bash
chmod +x gradlew
./gradlew build
```

**Windows:**
```cmd
gradlew.bat build
```

---

### ❌ "JAVA_HOME is not set"

**Problem:** Java path nie jest ustawiona

**Rozwiązanie Linux/Mac:**
```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH
java -version
./gradlew build
```

**Rozwiązanie Windows (CMD):**
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%
java -version
gradlew.bat build
```

**Rozwiązanie Windows (PowerShell):**
```powershell
$env:JAVA_HOME="C:\Program Files\Java\jdk-17"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
java -version
.\gradlew.bat build
```

---

### ❌ "gradle: No such file or directory"

**Problem:** Gradle wrapper JAR brakuje

**Rozwiązanie:**
```bash
./setup-gradle.sh
# lub ręcznie:
chmod +x setup-gradle.sh
./setup-gradle.sh
```

Jeśli to nie działa:
```bash
mkdir -p gradle/wrapper
cd gradle/wrapper
wget https://services.gradle.org/distributions/gradle-8.5-bin.zip
unzip gradle-8.5-bin.zip
cp gradle-8.5/lib/gradle-wrapper.jar .
cd ../..
./gradlew build
```

---

### ❌ "Wrong Java version"

**Problem:** Java jest zainstalowana, ale zła wersja

**Sprawdzenie:**
```bash
java -version
# Powinna pokazać "17", "18", "19", "20" lub "21"
```

**Jeśli jest za niska wersja:**

**Linux (Ubuntu/Debian):**
```bash
sudo apt-get update
sudo apt-get install openjdk-17-jdk
sudo update-alternatives --config java
```

**Linux (Fedora):**
```bash
sudo dnf install java-17-openjdk
sudo alternatives --set java /usr/lib/jvm/java-17-openjdk/bin/java
```

**macOS:**
```bash
brew install openjdk@17
# Ustaw w ~/.zshrc:
export PATH="/usr/local/opt/openjdk@17/bin:$PATH"
export JAVA_HOME="/usr/local/opt/openjdk@17"
```

**Windows:**
1. Odinstaluj starą Javę (Panel Sterowania → Programy)
2. Pobierz Java 17: https://adoptium.net/
3. Zainstaluj
4. Uruchom PowerShell jako administrator:
   ```powershell
   setx JAVA_HOME "C:\Program Files\Java\jdk-17"
   ```
5. Restart PowerShell

---

### ❌ Build timeout / Zbyt długi build

**Problem:** Build trwa zbyt długo lub przerywa się

**Rozwiązanie:**
```bash
# Zwiększ timeout
./gradlew build --no-build-cache

# Lub zwiększ RAM dla Gradle
export GRADLE_OPTS="-Xmx2048m"
./gradlew build

# Lub użyj paralel build
./gradlew build --parallel
```

---

### ❌ "Permission denied"

**Problem:** Nie masz uprawnień do pliku

**Rozwiązanie:**
```bash
chmod +x gradlew
chmod +x setup-gradle.sh
chmod +x quickstart.sh
```

---

### ❌ Out of Memory (OOM)

**Problem:** Gradle zabrakło pamięci RAM

**Rozwiązanie:**
```bash
# Zwiększ heap
export GRADLE_OPTS="-Xmx4096m"
./gradlew build

# Lub w ~/.gradle/gradle.properties:
# org.gradle.jvmargs=-Xmx4096m
```

---

## Problemy przy instalacji MOD'a

### ❌ Mod się nie pojawia w grze

**Sprawdzenie:**

1. Czy Fabric jest zainstalowany?
   - Uruchom grę Fabric
   - Na ekranie powinna być wersja Fabric

2. Czy JAR jest w dobrym miejscu?
   ```bash
   ls ~/.minecraft/mods/ | grep autojoin
   # Powinien pokazać: autojoin-1.0.0.jar
   ```

3. Czy to wersja modu dla Minecraft 1.20.4?
   - Sprawdź w `build.gradle`
   - Powinna być: `minecraft_version=1.20.4`

**Rozwiązanie:**
```bash
# Usuń cache
rm -rf ~/.minecraft/versions/1.20.4-fabric
rm -rf ~/.minecraft/mods/autojoin*.jar

# Skopiuj mod ponownie
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/

# Restart launcher i gry
```

---

### ❌ "Invalid signature"

**Problem:** JAR ma zły podpis lub jest uszkodzony

**Rozwiązanie:**
```bash
# Usuń i przebuilduj
rm build/libs/autojoin-1.0.0.jar
./gradlew clean build
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
```

---

### ❌ Mod się zainstalował, ale nie dołącza

**Sprawdzenie:**

1. Czy konfiguracja jest ustawiona?
   ```bash
   cat ~/.minecraft/config/autojoin/autojoin.json
   ```

2. Czy `enabled` jest `true`?

3. Czy adres serwera jest poprawny?
   ```bash
   # Test ping
   ping <serverAddress>
   ```

4. Czy serwer jest online?
   - Spróbuj dołączyć ręcznie w Minecraft

**Rozwiązanie:**

Edytuj `~/.minecraft/config/autojoin/autojoin.json`:
```json
{
  "enabled": true,
  "serverAddress": "poprawny.adres.serwera",
  "serverPort": 25565,
  "delayMs": 2000,
  "autoJoinOnLaunch": true,
  "showMessages": true
}
```

Zmień `delayMs` na wyższą wartość (np. 2000) jeśli dołączanie jest zbyt szybkie.

---

### ❌ Mod powoduje crash/lag

**Problem:** Gra się robi powolna lub się wyłącza

**Sprawdzenie crashu:**
```bash
cat ~/.minecraft/logs/latest.log | tail -100
```

**Szukaj wiadomości o `AutoJoin` lub `autojoin`**

**Rozwiązanie:**

1. Wyłącz mod czasowo:
   ```json
   "enabled": false
   ```

2. Sprawdzź czy port jest dostępny:
   ```bash
   netstat -tuln | grep 25565
   ```

3. Zwiększ delay:
   ```json
   "delayMs": 5000
   ```

4. Czy inne mody się nie kolidują?
   - Spróbuj bez innych modów

---

## Problemy przy konfiguracji

### ❌ Config nie został załadowany

**Problem:** Plik autojoin.json nie został utworzony

**Rozwiązanie:**

1. Upewnij się że mod jest zainstalowany
2. Uruchom grę Fabric
3. Plik powinien się stworzyć w: `~/.minecraft/config/autojoin/`

**Jeśli to nie zadziała:**
```bash
mkdir -p ~/.minecraft/config/autojoin
cp config_example.json ~/.minecraft/config/autojoin/autojoin.json
```

---

### ❌ Zły format JSON w konfiguracji

**Problem:** Błąd parsowania JSON

**Sprawdzenie:**
```bash
# Zainstaluj jq
sudo apt-get install jq
# lub macOS:
brew install jq

# Zwaliduj JSON
jq . ~/.minecraft/config/autojoin/autojoin.json
```

**Powinna być odpowiedź bez błędów.**

**Najczęstsze błędy:**
- Brakujące przecinki między polami
- Cudzysłowy zamiast apostrofów
- Spacje w ciągu JSON

**Prawidłowy format:**
```json
{
  "enabled": true,
  "serverAddress": "test.com",
  "serverPort": 25565,
  "delayMs": 1000,
  "autoJoinOnLaunch": true,
  "showMessages": true
}
```

---

## Debug / Bardziej zaawansowane

### Włącz debug mode

```bash
# Ustaw zmienne
export GRADLE_OPTS="-Xmx2048m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

# Builduj
./gradlew build

# Gradle będzie czekać na debugger na port 5005
```

### Sprawdź logi

```bash
# Build logs
./gradlew build --info
./gradlew build --debug

# Minecraft logs
tail -f ~/.minecraft/logs/latest.log
```

### Czyszczenie cache

```bash
# Gradle cache
./gradlew clean

# Maven cache (jeśli używasz Maven)
mvn clean

# System
rm -rf ~/.gradle/caches
```

---

## Gdy nic nie pomaga

1. **Przeczytaj:** [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)
2. **Sprawdź:** [REQUIREMENTS.md](REQUIREMENTS.md)
3. **Raportujesz błąd:** [GitHub Issues](https://github.com/kora-derewa/ZwierzLauncher/issues)

**Podaj informacje:**
- Wersję Java: `java -version`
- Wersję Minecraft'a
- Pełną treść błędu z logów
- Jakie mody masz zainstalowane

---

**Powodzenia! 🚀**
