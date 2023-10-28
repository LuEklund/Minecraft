package com.lucas.test;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToggleListener implements Listener {

    //private boolean enable = true;
    private List<UUID> enable = new ArrayList<>();
    @EventHandler
    public void onPlayerIntereact(PlayerInteractEvent e)
    {
        if(e.getHand().equals(EquipmentSlot.HAND)
                && e.getPlayer().getInventory().getItemInMainHand() != null
                && e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR))
        {
            if(enable.contains(e.getPlayer().getUniqueId()))
            {
                e.getPlayer().sendMessage("disabled chat");
                enable.remove(e.getPlayer().getUniqueId());
            }else{
                enable.add(e.getPlayer().getUniqueId());
                e.getPlayer().sendMessage("enabled chat");
            }
        }
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        if(enable.contains(e.getPlayer().getUniqueId()))
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage("Chat is disabled");
        }
    }
}
