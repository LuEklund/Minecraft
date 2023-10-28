package com.section._6.database;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Database extends JavaPlugin {

    private PlayerManager playerManager;
    private DataConnect database;

    @Override
    public void onEnable() {
        database = new DataConnect();
        try {
            database.connect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        playerManager = new PlayerManager();
        System.out.println(database.isConnected());
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
    }

    @Override
    public void onDisable() {
        database.disconnect();
    }
    public DataConnect getDataConnect(){
        return database;
    }
    public PlayerManager getPlayerManager(){return playerManager;}
}
