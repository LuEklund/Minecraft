package com.section8.minigame.listener;

import com.section8.minigame.MinigameKit;
import com.section8.minigame.instance.Arena;
import com.section8.minigame.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {


    private MinigameKit minigameKit;
    public ConnectListener(MinigameKit minigameKit){
        this.minigameKit = minigameKit;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        e.getPlayer().teleport(ConfigManager.getLobbySpawn());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Arena arena = minigameKit.getArenaManager().getArena(e.getPlayer());
        if(arena != null)
        {
            arena.removePlayer(e.getPlayer());
        }
    }

}
