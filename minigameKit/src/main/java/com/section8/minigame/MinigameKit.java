package com.section8.minigame;

import com.section8.minigame.manager.ArenaManager;
import com.section8.minigame.command.ArenaCommand;
import com.section8.minigame.listener.ConnectListener;
import com.section8.minigame.listener.GameListener;
import com.section8.minigame.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinigameKit extends JavaPlugin {

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
