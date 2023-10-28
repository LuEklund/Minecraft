package com.section8.minigame.listener;

import com.section8.minigame.MinigameResetMap;
import com.section8.minigame.instance.Arena;
import com.section8.minigame.GameState;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.world.WorldLoadEvent;

public class GameListener implements Listener {

    private MinigameResetMap minigameResetMap;

    public GameListener(MinigameResetMap minigameResetMap)
    {
        this.minigameResetMap = minigameResetMap;
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e)
    {
        System.out.println("================================================================");
        System.out.println("================================================================");
        System.out.println("=======================WORLD LOAD===============================");
        System.out.println("================================================================");
        System.out.println("================================================================");
        Arena arena = minigameResetMap.getArenaManager().getArena(e.getWorld());
        if(arena!= null) {
            System.out.println("could load ARENA: " + arena);
            arena.toggleCanJoin();
            System.out.println("can join: " + arena.canJoin());

        }else {
            System.out.println("could not load");

        }

    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Arena arena = minigameResetMap.getArenaManager().getArena(e.getPlayer());
        if(arena != null && arena.getState().equals(GameState.LIVE) && arena.canJoin())
        {
                arena.getGame().addPoint(e.getPlayer());
        }
    }
}
