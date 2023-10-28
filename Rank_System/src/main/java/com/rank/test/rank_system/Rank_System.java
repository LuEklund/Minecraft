package com.rank.test.rank_system;

import com.rank.test.rank_system.manager.NametagManager;
import com.rank.test.rank_system.manager.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rank_System extends JavaPlugin {

    private RankManager rankManager;
    private NametagManager nametagManager;
    @Override
    public void onEnable() {
        getCommand("rank").setExecutor(new RankCommand(this));

        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);

        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public RankManager getRankManager() {return rankManager;}
    public NametagManager getNametagManager() {return nametagManager;}
}
