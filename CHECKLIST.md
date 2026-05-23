# ✅ Installation Checklist

## Pre-Installation

- [ ] **Java 17+** installed
  ```bash
  java -version
  ```
  Expected: `version "17"` or higher

- [ ] **Git** installed (optional but recommended)
  ```bash
  git --version
  ```

- [ ] **Minecraft Fabric** installed
  - Download: https://fabricmc.net/
  - Install Fabric for 1.20.4

- [ ] **Minecraft 1.20.4** installed
  - Install through launcher

---

## Building Mod

### Option A: Using gradlew (Recommended)

- [ ] Clone or download project
- [ ] Navigate to project folder
  ```bash
  cd ZwierzLauncher
  ```

- [ ] Make gradlew executable
  ```bash
  chmod +x gradlew
  ```

- [ ] Setup Gradle (if needed)
  ```bash
  ./setup-gradle.sh
  ```

- [ ] Build project
  ```bash
  ./gradlew build
  ```

- [ ] Wait for completion (2-5 minutes on first build)

- [ ] Check JAR created
  ```bash
  ls build/libs/autojoin-1.0.0.jar
  ```

### Option B: Using Maven

- [ ] Install Maven
  ```bash
  mvn -version
  ```

- [ ] Navigate to project folder

- [ ] Build project
  ```bash
  mvn clean package
  ```

- [ ] Check JAR created
  ```bash
  ls target/autojoin-1.0.0.jar
  ```

### Option C: Using quickstart.sh

- [ ] Make script executable
  ```bash
  chmod +x quickstart.sh
  ```

- [ ] Run script
  ```bash
  ./quickstart.sh
  ```

---

## Installation

- [ ] Create mods folder
  ```bash
  mkdir -p ~/.minecraft/mods
  ```

- [ ] Copy JAR to mods folder
  ```bash
  cp build/libs/autojoin-1.0.0.jar ~/.minecraft/mods/
  ```

- [ ] Verify JAR exists
  ```bash
  ls ~/.minecraft/mods/ | grep autojoin
  ```

---

## Configuration

- [ ] Create config directory
  ```bash
  mkdir -p ~/.minecraft/config/autojoin
  ```

- [ ] Copy example config
  ```bash
  cp config_example.json ~/.minecraft/config/autojoin/autojoin.json
  ```

- [ ] Edit configuration
  ```bash
  # Edit your server details:
  nano ~/.minecraft/config/autojoin/autojoin.json
  ```

  Set these values:
  - [ ] `"enabled": true`
  - [ ] `"serverAddress": "your-server.com"` (replace with actual)
  - [ ] `"serverPort": 25565` (or your port)
  - [ ] `"delayMs": 1000` (adjust if needed)
  - [ ] `"autoJoinOnLaunch": true`
  - [ ] `"showMessages": true`

- [ ] Validate JSON
  ```bash
  jq . ~/.minecraft/config/autojoin/autojoin.json
  ```

---

## Testing

- [ ] Start Minecraft Launcher
  - [ ] Select Fabric 1.20.4
  - [ ] Click Play

- [ ] Check if mod loads
  - Look in bottom left for "Fabric" version
  - Or check logs: `tail -f ~/.minecraft/logs/latest.log`

- [ ] Wait for auto-join (or increase delay if needed)
  - Default: 1 second
  - If not joining, try: `"delayMs": 3000`

- [ ] Check chat for messages
  - Should see: `[AutoJoin] Dołączanie na serwer`

- [ ] Verify connection
  - Should join your server automatically

---

## Troubleshooting

If auto-join didn't work:

- [ ] **Check mod installed**
  ```bash
  ls ~/.minecraft/mods/ | grep autojoin
  ```

- [ ] **Check config exists**
  ```bash
  cat ~/.minecraft/config/autojoin/autojoin.json
  ```

- [ ] **Verify server is online**
  ```bash
  ping your-server.com
  ```

- [ ] **Check Minecraft logs**
  ```bash
  tail -50 ~/.minecraft/logs/latest.log | grep -i "autojoin\|error"
  ```

- [ ] **Increase delay** (if connection is slow)
  ```json
  "delayMs": 3000
  ```

- [ ] **Disable and re-enable mod**
  ```json
  "enabled": false
  ```
  then
  ```json
  "enabled": true
  ```

- [ ] **Check if Fabric is actually loaded**
  - Pause menu should show Fabric version
  - Not just vanilla Minecraft

See [TROUBLESHOOTING.md](TROUBLESHOOTING.md) for more help.

---

## Final Verification

After successful setup:

- [ ] Mod appears in mods list (check with mod manager)
- [ ] Auto-join works (server shows you joined)
- [ ] Config changes take effect
- [ ] No error messages in chat or logs

---

## Useful Files

| File | Purpose |
|------|---------|
| [README.md](README.md) | Overview & features |
| [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md) | Detailed build guide |
| [QUICK_COMMANDS.md](QUICK_COMMANDS.md) | Handy commands |
| [REQUIREMENTS.md](REQUIREMENTS.md) | System requirements |
| [TROUBLESHOOTING.md](TROUBLESHOOTING.md) | Problem solving |
| `config_example.json` | Example config |

---

## Support

✅ If everything works → Enjoy!

❌ If something fails → Check:
1. [TROUBLESHOOTING.md](TROUBLESHOOTING.md)
2. [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)
3. GitHub Issues: https://github.com/kora-derewa/ZwierzLauncher/issues

---

**Status: ☑️ Ready to Go!** 🎮🚀
