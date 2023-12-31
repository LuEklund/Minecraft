package com.section._6.database;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;
import java.util.UUID;

public class ConnectionListener implements Listener {

    private Database main;

    public ConnectionListener(Database main)
    {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        try {
            CustomPlayer playerData =  new CustomPlayer(main, uuid);
            main.getPlayerManager().addCustomPlayer(uuid, playerData);
        } catch (SQLException ex) {
            player.kickPlayer("Your data could not be loaded!");
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        main.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());
    }
}
