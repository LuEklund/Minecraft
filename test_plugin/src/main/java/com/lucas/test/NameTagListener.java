package com.lucas.test;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NameTagListener implements Listener {
    @EventHandler
    public void onJoining(PlayerJoinEvent e)
    {
        NameTagManager.setNameTags(e.getPlayer());
        NameTagManager.newTag(e.getPlayer());
    }
    @EventHandler
    public void onQuiting(PlayerQuitEvent e)
    {
        NameTagManager.removeTag(e.getPlayer());
    }
}
