package com.section._6.section_6.GUI;

import com.section._6.section_6.GUI.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            new GUI(player, 1);
        }
        return false;
    }
}
