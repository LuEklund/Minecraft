package com.lucas.test;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Riding implements Listener {
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e)
    {
        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();
        if(player.isSneaking())
        {
            player.addPassenger(entity);
        }
        else {
            entity.addPassenger(player);
        }
    }
}
