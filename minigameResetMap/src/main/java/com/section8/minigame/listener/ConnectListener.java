package com.section8.minigame.listener;

import com.section8.minigame.MinigameResetMap;
import com.section8.minigame.instance.Arena;
import com.section8.minigame.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {


    private MinigameResetMap minigameResetMap;
    public ConnectListener(MinigameResetMap minigameResetMap){
        this.minigameResetMap = minigameResetMap;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        e.getPlayer().teleport(ConfigManager.getLobbySpawn());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Arena arena = minigameResetMap.getArenaManager().getArena(e.getPlayer());
        if(arena != null)
        {
            arena.removePlayer(e.getPlayer());
        }
    }

}
