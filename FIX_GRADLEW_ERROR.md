# 🔴 NAPRAWA: "./gradlew: line 19: ... gradle: Няма такога файла ці каталога"

## Problem

Widząc błąd:
```
./gradlew: line 19: /home/derewa/Сцягнутыя файлы/autojointomcserver-main/gradle: Няма такога файла ці каталога
```

To oznacza, że plik `gradlew` szuka `gradle` w głównym katalogu, ale pliki nie są na miejscu.

---

## ✅ Szybka naprawa (Opcja 1)

Uruchom ten skrypt:

```bash
chmod +x fix-gradlew.sh
./fix-gradlew.sh
```

Potem:
```bash
./gradlew build
```

---

## 🔧 Ręczna naprawa (Opcja 2)

### Krok 1: Upewnia się że gradlew jest executable

```bash
chmod +x gradlew
chmod +x gradlew.bat
```

### Krok 2: Pobierz gradle-wrapper.jar

```bash
mkdir -p gradle/wrapper

# Opcja A: Użyj curl
curl -L https://services.gradle.org/distributions/gradle-8.5-bin.zip -o /tmp/gradle-8.5.zip

# Opcja B: Użyj wget
wget https://services.gradle.org/distributions/gradle-8.5-bin.zip -O /tmp/gradle-8.5.zip
```

### Krok 3: Rozpakuj i skopiuj JAR

```bash
unzip -q /tmp/gradle-8.5.zip -d /tmp/
cp /tmp/gradle-8.5/lib/gradle-wrapper.jar gradle/wrapper/
rm -rf /tmp/gradle-8.5*
```

### Krok 4: Buduj

```bash
./gradlew build
```

---

## 🛠️ Naprawa ze skopiowanych plikach (Opcja 3)

Jeśli pobieranie nie działa:

1. Pobierz ręcznie: https://gradle.org/releases/
   - Pobierz: `gradle-8.5-bin.zip`

2. Rozpakuj gdzieś

3. Skopiuj plik:
   ```
   gradle-8.5/lib/gradle-wrapper.jar → YourProject/gradle/wrapper/gradle-wrapper.jar
   ```

4. Uruchom:
   ```bash
   ./gradlew build
   ```

---

## 📝 Alternatywa: Maven zamiast Gradle

Jeśli Gradle dalej nie działa, użyj Maven:

```bash
# Sprawdź czy Maven jest zainstalowany
mvn -version

# Buduj
mvn clean package
```

Wynik: `target/autojoin-1.0.0.jar`

---

## ☑️ Weryfikacja

Sprawdź czy wszystko jest na miejscu:

```bash
ls -la gradlew                          # Powinien być plik
ls -la gradle/wrapper/gradle-wrapper.properties
ls -la gradle/wrapper/gradle-wrapper.jar
```

Powinno pokazać pliki bez błędu.

---

## 🚀 Pełne rozwiązanie (One-liner)

```bash
chmod +x gradlew && mkdir -p gradle/wrapper && \
curl -L https://services.gradle.org/distributions/gradle-8.5-bin.zip -o /tmp/gradle.zip && \
unzip -q /tmp/gradle.zip -d /tmp/ && \
cp /tmp/gradle-8.5/lib/gradle-wrapper.jar gradle/wrapper/ && \
rm -rf /tmp/gradle* && \
./gradlew build
```

---

## Jeśli nic nie pomaga

1. Przeczytaj: [TROUBLESHOOTING.md](TROUBLESHOOTING.md)
2. Sprawdź: [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)
3. Raportuj: [GitHub Issues](https://github.com/kora-derewa/ZwierzLauncher/issues)

**Podaj informacje:**
- Output pełny błędu
- Wersja Java: `java -version`
- System: `uname -a` (Linux/Mac) lub `wver` (Windows)

---

**🎯 Powdzenia! Zaraz się zbuduje!** 🚀
