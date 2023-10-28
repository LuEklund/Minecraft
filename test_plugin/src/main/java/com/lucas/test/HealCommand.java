package com.lucas.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender Sender, Command command, String s, String[] strings) {

        if(Sender instanceof Player)
        {
            if(strings.length != 1)
                return false;
            if(strings[0].equalsIgnoreCase("me")) {
                Player player = (Player) Sender;
                player.sendMessage("hehe restored ");
                player.setHealth(20);
            }
        }

        return false;
    }
}
