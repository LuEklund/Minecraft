package com.section8.minigame.listener;

import com.section8.minigame.instance.Arena;
import com.section8.minigame.GameState;
import com.section8.minigame.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GameListener implements Listener {

    private com.section8.minigame.minigameTeam minigameTeam;

    public GameListener(com.section8.minigame.minigameTeam minigameTeam)
    {
        this.minigameTeam = minigameTeam;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if (e.getInventory() != null && e.getCurrentItem() != null && e.getView().getTitle().contains("Team Selection"))
        {
            Team team = Team.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());
            Player player = (Player) e.getWhoClicked();
            Arena arena = minigameTeam.getArenaManager().getArena(player);
            if(arena != null)
            {
                if(arena.getTeam(player) == team){
                    player.sendMessage(ChatColor.RED + "You are already in that team!");
                }else{
                    player.sendMessage(ChatColor.GREEN + "You are now in team " + team.getDisplay());
                    arena.setTeam(player, team);
                }
            }
            player.closeInventory();
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Arena arena = minigameTeam.getArenaManager().getArena(e.getPlayer());
        if(arena != null && arena.getState().equals(GameState.LIVE))
        {
                arena.getGame().addPoint(e.getPlayer());
        }
    }
}
