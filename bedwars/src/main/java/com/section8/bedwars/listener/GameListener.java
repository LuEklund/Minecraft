package com.section8.bedwars.listener;

import com.section8.bedwars.Bedwars;
import com.section8.bedwars.instance.Arena;
import com.section8.bedwars.GameState;
import com.section8.bedwars.instance.Game;
import com.section8.bedwars.instance.Team;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class GameListener implements Listener {

    private Bedwars bedwars;

    public GameListener(Bedwars bedwars)
    {
        this.bedwars = bedwars;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Arena arena = bedwars.getArenaManager().getArena(e.getPlayer());
        if(arena != null && arena.getState().equals(GameState.LIVE))
        {
             Game game = arena.getGame();
             if(e.getBlock().getType() == Material.RED_BED && e.getBlock().hasMetadata("team")){
                 boolean isOwn = game.destroyBed(Team.valueOf(e.getBlock().getMetadata("team").get(0).asString()), e.getPlayer());
                 if(isOwn)
                     e.setCancelled(true);
                 else
                     e.setDropItems(false);
             }
        }
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        Arena arena = bedwars.getArenaManager().getArena(e.getEntity());
        if(arena != null && arena.getState().equals(GameState.LIVE)) {
            Game game = arena.getGame();
            game.death(e.getEntity());
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e)
    {
        Arena arena = bedwars.getArenaManager().getArena(e.getPlayer());
        if(arena != null && arena.getState().equals(GameState.LIVE)) {
            e.setRespawnLocation(arena.getGame().respawn(e.getPlayer()));
        }
    }


}
