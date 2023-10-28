package com.lucas.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class PermsCommand implements CommandExecutor{

    private Test_plugin main;
    private HashMap<UUID, PermissionAttachment> perms;

    public PermsCommand(Test_plugin main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            PermissionAttachment attachment;
            this.perms = main.getPerms();
            if(!perms.containsKey(player.getUniqueId()))
            {
                attachment = player.addAttachment(main);
                perms.put(player.getUniqueId(), attachment);
            }else {
                attachment = perms.get(player.getUniqueId());
            }
            if (player.hasPermission("worldedit.help"))
            {
                attachment.unsetPermission("worldedit.help");
                player.sendMessage("removed perms!");
            }else
            {
                attachment.setPermission("worldedit.help", true);
                player.sendMessage("added perms!");

            }
        }

        return false;
    }
}
