package com.lucas.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NameTagManager {
    public static void setNameTags(Player player){ //create scoreboard and team
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for(Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.getOrderSymbol() +  rank.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', rank.getDisplay()));
        }
    }
    public static void newTag(Player player){ //assign player to their team
    Rank rank = Rank.OWNER;
    for (Player target : Bukkit.getOnlinePlayers()){
        target.getScoreboard().getTeam(rank.getOrderSymbol() + rank.name()).addEntry(player.getName());
    }
    }
    public static void removeTag(Player player){ //remove player from all scoreboards
        for (Player target : Bukkit.getOnlinePlayers())
        {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }
}
