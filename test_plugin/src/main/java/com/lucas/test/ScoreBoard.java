package com.lucas.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class ScoreBoard implements Listener {

    private HashMap<UUID, Integer> blocksCounter = new HashMap<>();
    @EventHandler
    public void onJoinScore(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("testBoard","dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Test Board!");
        Team blockBroken = board.registerNewTeam("blocks");
        blockBroken.addEntry(ChatColor.BOLD.toString());
        blockBroken.setPrefix(ChatColor.BLUE + "Blocks broken ");
        blockBroken.setSuffix(ChatColor.YELLOW + "0");
        obj.getScore(ChatColor.BOLD.toString()).setScore(1);

        Score space = obj.getScore(" ");
        space.setScore(2);

        Score name = obj.getScore(ChatColor.YELLOW + "Name: " + player.getName());
        name.setScore(3);
        player.setScoreboard(board);

        blocksCounter.put(player.getUniqueId(), 0);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Player player = e.getPlayer();
        int amount = blocksCounter.get(player.getUniqueId());
        amount++;

        blocksCounter.put(player.getUniqueId(), amount);
        player.getScoreboard().getTeam("blocks").setSuffix(ChatColor.YELLOW.toString() + amount);

    }
}
