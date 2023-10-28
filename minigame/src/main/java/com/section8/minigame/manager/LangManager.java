package com.section8.minigame.manager;

import com.section8.minigame.Minigame;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LangManager {

    private static YamlConfiguration lang;


    public static void setUpLangFile(Minigame minigame) {
        File file = new File(minigame.getDataFolder(), "lang.yml");
        if(!file.exists()) {
            minigame.saveResource("lang.yml", false);
        }
        lang = YamlConfiguration.loadConfiguration(file);
    }

    public static String getLeaveArena() {
        return lang.getString("leave-arena");
    }

    public static String getKeys (String key) {
        return lang.getString(key);
    }


}
