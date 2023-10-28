package com.section8.bedwars;

import com.section8.bedwars.manager.ArenaManager;
import com.section8.bedwars.command.ArenaCommand;
import com.section8.bedwars.listener.ConnectListener;
import com.section8.bedwars.listener.GameListener;
import com.section8.bedwars.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bedwars extends JavaPlugin {

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        ConfigManager.setUpConfig(this);

        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
        getCommand("arena").setExecutor(new ArenaCommand(this));
        arenaManager = new ArenaManager(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ArenaManager getArenaManager() {return arenaManager;}

}
