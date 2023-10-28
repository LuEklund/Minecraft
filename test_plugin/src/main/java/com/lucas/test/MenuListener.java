package com.lucas.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener implements Listener {

    @EventHandler
    public void OnInvClickEvent(InventoryClickEvent e)
    {
        if(ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.BLUE.toString() + ChatColor.BOLD + "GUI menu")
            && e.getCurrentItem() != null)
        {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            switch(e.getRawSlot())
            {
                case 0: //close
                    player.closeInventory();

                    break;
                case 20: //random tp
                    Random random = new Random();
                    Player target = (Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())];
                    player.teleport(target);
                    player.sendMessage(ChatColor.RED + "You teleported " + target.getName());
                    player.closeInventory();

                    break;
                case 21:
                    player.getInventory().addItem(e.getCurrentItem());
                    break;
                case 22: //death
                    player.setHealth(0);
                    player.sendMessage(ChatColor.RED + "You killed yourself");
                    player.closeInventory();

                    break;
                case 24: //clear inv
                    player.closeInventory();
                    player.getInventory().clear();
                    player.sendMessage(ChatColor.RED + "You killed cleared your inventory");

                    break;
                default:
                    return;
            }
        }
    }

}
