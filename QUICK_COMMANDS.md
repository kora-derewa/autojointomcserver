# 📚 Szybkie Komendy

## Instalacja & Setup

```bash
# Klonowanie
git clone https://github.com/kora-derewa/ZwierzLauncher.git
cd ZwierzLauncher

# Setup (first time)
chmod +x gradlew setup-gradle.sh quickstart.sh

# Quick start
./quickstart.sh
```

## Budowanie

```bash
# Gradle - Full build
./gradlew build

# Gradle - Clean build
./gradlew clean build

# Gradle - Build without tests
./gradlew build -x test

# Maven - Build
mvn clean package

# Maven - Build without tests
mvn clean package -DskipTests

# Rebuild (no cache)
./gradlew build --no-build-cache
```

## Výstup (Output)

```bash
# Gradle
build/libs/autojoin-1.0.0.jar

# Maven
target/autojoin-1.0.0.jar
```

## Instalacja MOD'a

```bash
# Copy to mods folder
cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/

# Check if installed
ls ~/.minecraft/mods/ | grep autojoin

# Remove mod
rm ~/.minecraft/mods/autojoin*.jar
```

## Konfiguracja

```bash
# Create config
mkdir -p ~/.minecraft/config/autojoin

# Copy example
cp config_example.json ~/.minecraft/config/autojoin/autojoin.json

# Edit config
nano ~/.minecraft/config/autojoin/autojoin.json
# lub
vim ~/.minecraft/config/autojoin/autojoin.json
# lub otwórz w VS Code
code ~/.minecraft/config/autojoin/autojoin.json

# View config
cat ~/.minecraft/config/autojoin/autojoin.json

# Validate JSON
jq . ~/.minecraft/config/autojoin/autojoin.json
```

## Java

```bash
# Check version
java -version

# Set JAVA_HOME (Linux/Mac)
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH

# Install Java 17 (Ubuntu/Debian)
sudo apt-get install openjdk-17-jdk

# Install Java 17 (Fedora)
sudo dnf install java-17-openjdk

# Install Java 17 (macOS)
brew install openjdk@17
```

## Gradle Wrapper

```bash
# Setup gradle wrapper
./setup-gradle.sh

# Make executable
chmod +x gradlew

# Manual setup
mkdir -p gradle/wrapper
cd gradle/wrapper
wget https://services.gradle.org/distributions/gradle-8.5-bin.zip
unzip gradle-8.5-bin.zip
cp gradle-8.5/lib/gradle-wrapper.jar .
cd ../..
./gradlew build
```

## Debugging & Logs

```bash
# Build with info
./gradlew build -i

# Build with debug
./gradlew build -d

# Check dependencies
./gradlew dependencies

# View Minecraft logs
tail -f ~/.minecraft/logs/latest.log

# Search for errors
grep -i "error\|exception" ~/.minecraft/logs/latest.log
```

## System Cleanup

```bash
# Clean gradle build
./gradlew clean

# Remove gradle cache
rm -rf ~/.gradle/caches

# Remove mod cache
rm -rf ~/.minecraft/versions/1.20.4-fabric
rm -rf ~/.minecraft/mods/autojoin*.jar

# Clean all
./gradlew clean && rm -rf build/ .gradle/
```

## Git

```bash
# Clone with branch
git clone -b main https://github.com/kora-derewa/ZwierzLauncher.git

# Check status
git status

# Commit
git add .
git commit -m "Your message"

# Push
git push origin main

# Pull
git pull origin main
```

## Permissions

```bash
# Make executable
chmod +x gradlew
chmod +x setup-gradle.sh
chmod +x quickstart.sh

# Check permissions
ls -la gradlew setup-gradle.sh quickstart.sh
```

## Network

```bash
# Test server connectivity
ping mc.example.com

# Check if port is open
nc -zv mc.example.com 25565

# Check if port is listening
netstat -tuln | grep 25565
```

## File Management

```bash
# Find gradle-wrapper.jar
find . -name "gradle-wrapper.jar"

# Find build outputs
find . -name "autojoin-*.jar"

# Tree view
tree -L 2

# Size
du -sh build/
du -sh ~/.gradle/
```

## Shortcuts (Aliases)

Add to `~/.bashrc` or `~/.zshrc`:

```bash
# Build shortcuts
alias build-autojoin='cd ~/path/to/ZwierzLauncher && ./gradlew build'
alias install-autojoin='cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/'
alias edit-config='code ~/.minecraft/config/autojoin/autojoin.json'
alias view-logs='tail -f ~/.minecraft/logs/latest.log'

# Utility
alias check-java='java -version'
alias clean-build='./gradlew clean build'
```

Then use:
```bash
build-autojoin
install-autojoin
edit-config
view-logs
check-java
clean-build
```

---

## Cheat Sheet (One-Liners)

```bash
# Clone, build, install (all at once)
git clone https://github.com/kora-derewa/ZwierzLauncher.git && cd ZwierzLauncher && chmod +x gradlew && ./gradlew build && cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/

# Create config from template
mkdir -p ~/.minecraft/config/autojoin && cp config_example.json ~/.minecraft/config/autojoin/autojoin.json

# View last 50 lines of Minecraft log
tail -50 ~/.minecraft/logs/latest.log

# Check all info
echo "Java: $(java -version 2>&1 | head -1)" && echo "Git: $(git --version)" && echo "Gradle: $(./gradlew -v | head -1)"
```

---

**Zapisz tego pliku jako referencję!** 📖
