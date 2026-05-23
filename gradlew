#!/bin/bash

# Gradle wrapper script dla Linux/Mac

PRG="$0"
while [ -h "$PRG" ]; do
  ls=$(ls -ld "$PRG")
  link=$(expr "$ls" : '.*-> \(.*\)$')
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=$(dirname "$PRG")"/$link"
  fi
done
PRGDIR=$(dirname "$PRG")

GRADLE_HOME="$PRGDIR"
export GRADLE_HOME
exec "$GRADLE_HOME/gradle" "$@"
