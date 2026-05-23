#!/bin/bash

# QUICK START SCRIPT - AutoJoin Mod

set -e

echo "🎮 AutoJoin Mod - Quick Start"
echo "=============================="
echo ""

# Check Java
echo "✓ Sprawdzanie Java..."
if ! command -v java &> /dev/null; then
    echo "✗ Java nie znaleziona!"
    echo "  Zainstaluj Java 17+: https://adoptium.net/"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | grep -oP 'version "\K[0-9]+')
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "✗ Java $JAVA_VERSION znaleziona, ale wymagana jest 17+"
    exit 1
fi
echo "  ✓ Java $JAVA_VERSION OK"

# Check Git
echo "✓ Sprawdzanie Git..."
if ! command -v git &> /dev/null; then
    echo "  ⚠ Git nie znaleziony (opcjonalnie)"
fi

# Setup Gradle
echo ""
echo "✓ Konfiguracja Gradle..."
if [ ! -f "gradle/wrapper/gradle-wrapper.jar" ]; then
    echo "  ⚠ gradle-wrapper.jar nie znaleziony, pobieranie..."
    
    if command -v curl &> /dev/null; then
        mkdir -p gradle/wrapper
        GRADLE_VERSION="8.5"
        curl -L "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
            -o "/tmp/gradle-${GRADLE_VERSION}.zip"
        
        unzip -q "/tmp/gradle-${GRADLE_VERSION}.zip" -d "/tmp/"
        cp "/tmp/gradle-${GRADLE_VERSION}/lib/gradle-wrapper.jar" "gradle/wrapper/"
        rm -rf "/tmp/gradle-${GRADLE_VERSION}"*
        
        echo "  ✓ Gradle pobrany"
    else
        echo "  ✗ curl nie znaleziony, pobierz gradle ręcznie:"
        echo "    ./setup-gradle.sh"
        exit 1
    fi
else
    echo "  ✓ Gradle wrapper znaleziony"
fi

# Make gradlew executable
chmod +x gradlew

# Build
echo ""
echo "🔨 Budowanie projektu..."
echo "  (To może potrwać 2-5 minut przy pierwszym uruchomieniu)"
echo ""

if ./gradlew build; then
    echo ""
    echo "✅ Build successful!"
    echo ""
    echo "📁 JAR znajduje się w:"
    echo "   build/libs/autojoin-1.0.0.jar"
    echo ""
    echo "📦 Instalacja:"
    echo "   1. Otwórz folder: ~/.minecraft/mods/"
    echo "   2. Skopiuj: build/libs/autojoin-1.0.0.jar"
    echo "   3. Uruchom Minecraft (Fabric)"
    echo ""
    echo "⚙️  Konfiguracja:"
    echo "   ~/.minecraft/config/autojoin/autojoin.json"
    echo ""
    echo "🚀 Gotowe! Powodzenia!"
else
    echo ""
    echo "✗ Build failed!"
    echo "  Sprawdź BUILD_INSTRUCTIONS.md dla pomocy"
    exit 1
fi
