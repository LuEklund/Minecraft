package com.section8.bedwars.command;

import com.section8.bedwars.Bedwars;
import com.section8.bedwars.instance.Arena;
import com.section8.bedwars.GameState;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    private Bedwars bedwars;
    public ArenaCommand(Bedwars bedwars){
        this.bedwars = bedwars;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            if(args.length == 1 && args[0].equalsIgnoreCase("list"))
            {
                player.sendMessage(ChatColor.GREEN + "These are the available arenas:");
                player.sendMessage(ChatColor.GREEN + "- ID = (STATE)");
                for (Arena arenas : bedwars.getArenaManager().getArenas())
                {
                    player.sendMessage(ChatColor.GREEN + "- " + arenas.getId() + " = (" + arenas.getState().name() + ")");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
                Arena arena = bedwars.getArenaManager().getArena(player);
                if (arena != null)
                {
                    player.sendMessage(ChatColor.RED + "You left the Arena.");
                    arena.removePlayer(player);
                }else {
                    player.sendMessage(ChatColor.RED + "You are not in a arena");
                }
            } else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
                if (bedwars.getArenaManager().getArena(player) != null)
                {
                    player.sendMessage(ChatColor.RED + "you are already ina arena!");
                    return false;
                }
                int id;
                try{
                    id = Integer.parseInt(args[1]);
                }catch (NumberFormatException e){
                    player.sendMessage(ChatColor.RED + "you specified an invalid arena ID.");
                    return false;
                }
                if(id >= 0 && id < bedwars.getArenaManager().getArenas().size()){
                    Arena arena = bedwars.getArenaManager().getArena(id);
                    if(arena.getPlayers().size() != 4)
                    {
                        if(arena.getState() == GameState.RECRUITING || arena.getState() == GameState.COUNTDOWN)
                        {
                            player.sendMessage(ChatColor.GREEN + "you are now playing in arena " + id + ".");
                            arena.addPlayer(player);
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED + "you cant join this arena.");
                        }
                    }else {
                        player.sendMessage(ChatColor.RED + "this game is full");
                    }
                }else {
                    player.sendMessage(ChatColor.RED + "you specified an invalid arena ID.");
                }
            }else {
                player.sendMessage(ChatColor.RED + "INVALID usage! These are the options:");
                player.sendMessage(ChatColor.RED + "- /arena list");
                player.sendMessage(ChatColor.RED + "- /arena leave");
                player.sendMessage(ChatColor.RED + "- /arena join <id>");
            }
        }
        return false;
    }
}
