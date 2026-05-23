#!/bin/bash

# MINIMAL FIX - Run this if you get "./gradlew: No such file" error

echo "Naprawianie Gradle wrapper..."

# 1. Make gradlew executable
chmod +x gradlew

# 2. Download gradle-wrapper.jar if missing
if [ ! -f "gradle/wrapper/gradle-wrapper.jar" ]; then
    echo "Pobieranie gradle-wrapper.jar..."
    
    mkdir -p /tmp/gradle-dl
    cd /tmp/gradle-dl
    
    # Download
    if command -v wget &> /dev/null; then
        wget -q https://services.gradle.org/distributions/gradle-8.5-bin.zip
    elif command -v curl &> /dev/null; then
        curl -s -L https://services.gradle.org/distributions/gradle-8.5-bin.zip -o gradle-8.5-bin.zip
    else
        echo "Błąd: Nie znaleziono curl ani wget"
        exit 1
    fi
    
    # Extract
    unzip -q gradle-8.5-bin.zip
    
    # Copy back
    cp gradle-8.5/lib/gradle-wrapper.jar "$OLDPWD/gradle/wrapper/"
    
    cd - > /dev/null
    rm -rf /tmp/gradle-dl
    
    echo "✓ gradle-wrapper.jar pobrany"
fi

echo ""
echo "✅ Gradle naprawiony!"
echo "Teraz uruchom:"
echo "  ./gradlew build"
