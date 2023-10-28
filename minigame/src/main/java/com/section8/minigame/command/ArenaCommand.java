package com.section8.minigame.command;

import com.section8.minigame.Minigame;
import com.section8.minigame.instance.Arena;
import com.section8.minigame.GameState;
import com.section8.minigame.manager.LangManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    private Minigame minigame;
    public ArenaCommand(Minigame minigame){
        this.minigame = minigame;
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
                for (Arena arenas : minigame.getArenaManager().getArenas())
                {
                    player.sendMessage(ChatColor.GREEN + "- " + arenas.getId() + " = (" + arenas.getState().name() + ")");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
                Arena arena = minigame.getArenaManager().getArena(player);
                if (arena != null)
                {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', LangManager.getLeaveArena()));
                    arena.removePlayer(player);
                }else {
                    player.sendMessage(ChatColor.RED + "You are not in a arena");
                }
            } else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
                if (minigame.getArenaManager().getArena(player) != null)
                {
                    player.sendMessage(ChatColor.RED + "you are already i na arena!");
                    return false;
                }
                int id;
                try{
                    id = Integer.parseInt(args[1]);
                }catch (NumberFormatException e){
                    player.sendMessage(ChatColor.RED + "you specified an invalid arena ID.");
                    return false;
                }
                if(id >= 0 && id < minigame.getArenaManager().getArenas().size()){
                    Arena arena = minigame.getArenaManager().getArena(id);
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
