package com.section._6.section_6.GUI;

import com.section._6.section_6.GUI.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if(e.getInventory() != null && e.getCurrentItem() != null && e.getView().getTitle().contains("Page test"))
        {
            int page = Integer.parseInt(e.getInventory().getItem(0).getItemMeta().getLocalizedName());
            if (e.getRawSlot() == 0 && e.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE))
            {
                new GUI((Player) e.getWhoClicked(), page - 1);
            } else if (e.getRawSlot() == 8 && e.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE)) {
                new GUI((Player) e.getWhoClicked(), page + 1);
            }
            e.setCancelled(true);
        }
    }
}
