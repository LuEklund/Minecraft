package com.section8.minigame.listener;

import com.section8.minigame.MinigameKit;
import com.section8.minigame.instance.Arena;
import com.section8.minigame.GameState;
import com.section8.minigame.kit.KitType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GameListener implements Listener {

    private MinigameKit minigameKit;

    public GameListener(MinigameKit minigameKit)
    {
        this.minigameKit = minigameKit;
    }


    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        Player player = (Player) e.getWhoClicked();

        if(e.getInventory() != null && e.getView().getTitle().contains("Kit Selection") && e.getCurrentItem()!= null)
        {
            e.setCancelled(true);
            KitType type = KitType.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());

            Arena arena = minigameKit.getArenaManager().getArena(player);
            if(arena != null)
            {
                KitType activated = arena.getKitType(player);
                if (activated != null && activated == type)
                {
                    player.sendMessage(ChatColor.RED + "You already have this kit!");
                }else{
                    player.sendMessage(ChatColor.GREEN + "You equipped the" + type.getDisplay() + " kit!");
                    arena.setKit(player.getUniqueId(), type);
                }
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Arena arena = minigameKit.getArenaManager().getArena(e.getPlayer());
        if(arena != null && arena.getState().equals(GameState.LIVE))
        {
                arena.getGame().addPoint(e.getPlayer());
        }
    }
}
