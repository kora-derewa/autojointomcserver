package com.zwierz.autojoin;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoJoinMod implements ModInitializer {
    public static final String MOD_ID = "autojoin";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("AutoJoin Server mod initializing");
        ConfigManager.loadConfig();
    }
}
