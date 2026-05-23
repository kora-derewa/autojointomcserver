# 🔧 Instrukcja Budowania AutoJoin Mod

## Metoda 1: Gradle (Recommended) 

### Wymagania:
- Java 17+ (`java -version`)
- Git

### Kroki:

```bash
# 1. Pobierz projekt
git clone https://github.com/kora-derewa/ZwierzLauncher.git
cd ZwierzLauncher

# 2. Pobierz Gradle Wrapper (jeśli brakuje)
chmod +x setup-gradle.sh
./setup-gradle.sh

# 3. Ustaw uprawnienia dla gradlew
chmod +x gradlew

# 4. Buduj projekt
./gradlew build
```

**Wynik:** `build/libs/autojoin-1.0.0.jar`

---

## Metoda 2: Maven

### Wymagania:
- Java 17+
- Maven 3.6+ (`mvn -v`)

### Kroki:

```bash
# 1. Pobierz projekt
git clone https://github.com/kora-derewa/ZwierzLauncher.git
cd ZwierzLauncher

# 2. Buduj projektem
mvn clean package
```

**Wynik:** `target/autojoin-1.0.0.jar`

---

## Metoda 3: Ręczne pobieranie Gradle Wrapper

Jeśli skrypt `setup-gradle.sh` nie działa:

```bash
# 1. Pobierz Gradle
cd gradle/wrapper
wget https://services.gradle.org/distributions/gradle-8.5-bin.zip

# 2. Rozpakuj
unzip gradle-8.5-bin.zip

# 3. Skopiuj gradle-wrapper.jar
cp gradle-8.5/lib/gradle-wrapper.jar .

# 4. Wróć i buduj
cd ../..
chmod +x gradlew
./gradlew build
```

---

## Metoda 4: Docker (Alternatywa)

```dockerfile
FROM gradle:8.5-jdk17

WORKDIR /app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew build

CMD ["cat", "build/libs/autojoin-1.0.0.jar"]
```

Buduj:
```bash
docker build -t autojoin-builder .
docker run --rm -v $(pwd)/build/libs:/app/build/libs autojoin-builder
```

---

## Rozwiązywanie problemów

### Problem: `./gradlew: command not found`
```bash
chmod +x gradlew
```

### Problem: `gradle-wrapper.jar not found`
```bash
./setup-gradle.sh
# lub
chmod +x setup-gradle.sh && ./setup-gradle.sh
```

### Problem: `JAVA_HOME not set`
```bash
# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk

# Windows
set JAVA_HOME=C:\Program Files\Java\jdk-17
```

### Problem: Zła wersja Java
```bash
# Sprawdź wersję
java -version

# Zainstaluj Java 17
# Ubuntu/Debian
sudo apt-get install openjdk-17-jdk

# Fedora
sudo dnf install java-17-openjdk

# macOS
brew install openjdk@17
```

---

## Instalacja MOD'a

Po pomyślnym zbudowaniu:

1. **Skopiuj JAR**
   ```bash
   cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
   # lub
   cp build/libs/autojoin-1.0.0.jar ~/AppData/Roaming/.minecraft/mods/  # Windows
   ```

2. **Skonfiguruj** (Minecraft uruchomi się raz automatycznie)
   - Edytuj: `~/.minecraft/config/autojoin/autojoin.json`
   - Ustaw adres serwera

3. **Uruchom grę**
   - Minecraft powinien automatycznie dołączyć na serwer

---

## Pliki wynikowe

### Gradle
```
build/
└── libs/
    └── autojoin-1.0.0.jar
```

### Maven
```
target/
└── autojoin-1.0.0.jar
```

---

## Opcje budowania

### Tylko testy:
```bash
./gradlew test
```

### Czyszczenie
```bash
./gradlew clean
```

### Bez cache'u
```bash
./gradlew build --no-build-cache
```

### Verbose
```bash
./gradlew build -i  # info
./gradlew build -d  # debug
```

---

## Debug

```bash
# Uruchomienie z debugowaniem
./gradlew build -x test  # pomiń testy
./gradlew build --stacktrace

# Sprawdzenie zależności
./gradlew dependencies
```

---

**Gotowe! Powodzenia z budowaniem! 🚀**
