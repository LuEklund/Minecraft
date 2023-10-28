package com.section._6.section_6.BroadCast;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BroadCastListener implements Listener {
    @EventHandler
    public void onBroadCast(ServerBroadcastEvent e)
    {
        System.out.println("Player: " + e.getPlayer().getName() + "ran broadcast message: " + e.getMessage());
    }
}
