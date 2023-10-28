package com.section8.minigame.instance;

import com.section8.minigame.GameState;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points;

    public Game(Arena arena)
    {
        this.arena = arena;
        this.points = new HashMap<>();
    }

    public void start()
    {
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.GREEN + "Game started!");

        for(UUID uuid : arena.getPlayers())
        {
            points.put(uuid, 0);
        }
    }

    public void addPoint(Player player)
    {
        int playerPoints = points.get(player.getUniqueId()) + 1;
        if (playerPoints == 20){
            arena.sendMessage(ChatColor.GOLD + player.getName() + " Won! Thanks for playing :)");
            arena.reset();
            return;
        }
        player.sendMessage(ChatColor.GREEN + "+1 Point");
        points.replace(player.getUniqueId(), playerPoints);
    }

}
