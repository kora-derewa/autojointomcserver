#!/bin/bash
# Build AutoJoin for all supported Minecraft versions
set -e

JAVA_21=/usr/lib/jvm/java-21-openjdk
JAVA_25=/usr/lib/jvm/java-25-openjdk

mkdir -p releases

for ver in 1.18.2 1.19.4 1.20.4 1.21.4; do
    echo "=== Building $ver ==="
    JAVA_HOME=$JAVA_21 ./gradlew clean build -PmcVersion=$ver --no-daemon -q
    cp build/libs/autojoin-${ver}-*.jar releases/
    echo "=== $ver OK ==="
done

# Build 26.1 requires Java 25, skip if Loom not supported
if [ -d "$JAVA_25" ]; then
    echo "=== Building 26.1 (experimental) ==="
    JAVA_HOME=$JAVA_25 ./gradlew clean build -PmcVersion=26.1 --no-daemon -q && cp build/libs/autojoin-26.1-*.jar releases/ && echo "=== 26.1 OK ===" || echo "=== 26.1 SKIPPED (Loom Java 25 unsupported) ==="
fi

echo "=== All builds done ==="
ls -lh releases/
