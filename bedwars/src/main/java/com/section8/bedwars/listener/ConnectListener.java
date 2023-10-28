package com.section8.bedwars.listener;

import com.section8.bedwars.Bedwars;
import com.section8.bedwars.instance.Arena;
import com.section8.bedwars.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {


    private Bedwars bedwars;
    public ConnectListener(Bedwars bedwars){
        this.bedwars = bedwars;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        e.getPlayer().teleport(ConfigManager.getLobbySpawn());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Arena arena = bedwars.getArenaManager().getArena(e.getPlayer());
        if(arena != null)
        {
            arena.removePlayer(e.getPlayer());
        }
    }

}
