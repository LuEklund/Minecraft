package com.section8.minigame.listener;

import com.section8.minigame.Minigame;
import com.section8.minigame.instance.Arena;
import com.section8.minigame.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GameListener implements Listener {

    private Minigame minigame;

    public GameListener(Minigame minigame)
    {
        this.minigame = minigame;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Arena arena = minigame.getArenaManager().getArena(e.getPlayer());
        if(arena != null && arena.getState().equals(GameState.LIVE))
        {
                arena.getGame().addPoint(e.getPlayer());
        }
    }
}
