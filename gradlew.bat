@echo off

setlocal enabledelayedexpansion

set DIR=%~dp0

for %%i in (%DIR%) do set GRADLE_HOME=%%~fi

set GRADLE_CMD="%GRADLE_HOME%gradle.bat"

if exist %GRADLE_CMD% (
    %GRADLE_CMD% %*
) else (
    echo Gradle not found
    exit /b 1
)
