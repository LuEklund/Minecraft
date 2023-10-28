package com.section8.bedwars.manager;

import com.section8.bedwars.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setUpConfig(Bedwars bedwars)
    {
        ConfigManager.config = bedwars.getConfig();
        bedwars.saveDefaultConfig();
        bedwars.reloadConfig();
        /*bedwars.getConfig();
        bedwars.saveDefaultConfig();
        bedwars.reloadConfig();
        ConfigManager.config = bedwars.getConfig();*/
    }

    public static int getRequiredPlayers(){
        return config.getInt("required-players");
    }
    public static int getCountdownSeconds(){
        return config.getInt("countdown-seconds");
    }

    public static Location getLobbySpawn()
    {
        return new Location(
                Bukkit.getWorld(config.getString("lobby-spawn.world")),
                config.getDouble("lobby-spawn.x"),
                config.getDouble("lobby-spawn.y"),
                config.getDouble("lobby-spawn.z"),
                (float) config.getDouble("lobby-spawn.yaw"),
                (float) config.getDouble("lobby-spawn.pitch"));
    }

}
