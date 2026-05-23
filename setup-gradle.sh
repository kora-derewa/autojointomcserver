#!/bin/bash

# Script to download and setup Gradle wrapper
# Run this if gradle-wrapper.jar is missing

cd "$(dirname "$0")" || exit 1

GRADLE_VERSION="8.5"
WRAPPER_DIR="gradle/wrapper"

# Create wrapper directory if it doesn't exist
mkdir -p "$WRAPPER_DIR"

# Download Gradle
echo "Downloading Gradle $GRADLE_VERSION..."
curl -L "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" -o "/tmp/gradle-${GRADLE_VERSION}.zip"

if [ $? -eq 0 ]; then
    echo "Extracting Gradle..."
    unzip -q "/tmp/gradle-${GRADLE_VERSION}.zip" -d "/tmp/"
    
    # Copy gradle-wrapper.jar
    cp "/tmp/gradle-${GRADLE_VERSION}/lib/gradle-wrapper.jar" "$WRAPPER_DIR/"
    
    # Cleanup
    rm -rf "/tmp/gradle-${GRADLE_VERSION}"* 
    
    echo "✓ Gradle Wrapper setup complete!"
    echo "You can now run: ./gradlew build"
else
    echo "✗ Failed to download Gradle"
    exit 1
fi
