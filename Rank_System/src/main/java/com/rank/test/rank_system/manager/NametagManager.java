package com.rank.test.rank_system.manager;

import com.rank.test.rank_system.Rank;
import com.rank.test.rank_system.Rank_System;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    private Rank_System main;

    public NametagManager(Rank_System main){
        this.main = main;
    }
    public void setNametags(Player player){
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        for (Rank rank : Rank.values())
        {
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            team.setPrefix(rank.getDisplay());
        }
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (player.getUniqueId() != target.getUniqueId()) {
                System.out.println("1-score-board-of: " + player.getName() + " added " + target.getName() + " as " + main.getRankManager().getRank(target.getUniqueId()).name());
                player.getScoreboard().getTeam(main.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
                //adds everyone to the new players score board.
                //except them self
            }
        }
    }
    public void newTag(Player player){
        Rank rank = main.getRankManager().getRank(player.getUniqueId());
        for (Player target : Bukkit.getOnlinePlayers()){
            System.out.println("2-score-board-of: " + target.getName() + " added " + player.getName() + " as " + rank.getDisplay());
            target.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
            //all the players adds the new player
        }
    }
    public void removeTag(Player player){
        System.out.println("3-score-board-of: " + player.getName() + " removed");
        for (Player target : Bukkit.getOnlinePlayers()){
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }

}
