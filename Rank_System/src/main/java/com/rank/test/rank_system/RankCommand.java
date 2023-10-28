package com.rank.test.rank_system;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    private Rank_System main;
    public RankCommand(Rank_System main){
        this.main = main;
    }
    //rank <Player> <Rank>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            if (player.isOp())
            {
                if(args.length == 2){
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                    if(target.hasPlayedBefore() || target.isOnline())
                    {
                        for(Rank rank : Rank.values()) {
                            if(rank.name().equalsIgnoreCase((args[1]))){
                                main.getRankManager().setRank(target.getUniqueId(), rank, false);
                                player.sendMessage(ChatColor.GREEN
                                        + "you changed "
                                        + target.getName()
                                        + "'s rank to "
                                        + rank.getDisplay());
                                if(target.isOnline() && (target.getPlayer() != player))
                                {
                                    target.getPlayer().sendMessage(ChatColor.GREEN
                                            + player.getName()
                                            + "set your rank to "
                                            + rank.getDisplay());
                                }
                                return false;
                            }
                        }
                        player.sendMessage(ChatColor.RED + "invalid Rank options are");
                        for(Rank rank : Rank.values()) {
                            player.sendMessage(ChatColor.RED + rank.toString());
                        }
                    }
                    else  {
                        player.sendMessage(ChatColor.RED + "this user has never joined the server before");
                    }
                }else
                {
                    player.sendMessage(ChatColor.RED + "Invalid usaged, use /rank <Player> <Rank>");
                }
            }else {
                player.sendMessage(ChatColor.RED + "You must be OP to use this command");
            }
        }

        return false;
    }
}
