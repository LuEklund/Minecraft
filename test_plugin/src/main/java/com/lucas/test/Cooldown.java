package com.lucas.test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Cooldown implements CommandExecutor {

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).build();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            if(!cooldown.asMap().containsKey(player.getUniqueId()))
            {
                player.sendMessage(ChatColor.GREEN + "cool down worked");
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 5000);
            }
            else {
                Long distance = cooldown.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                player.sendMessage(ChatColor.RED + "You must wait" + TimeUnit.MILLISECONDS.toSeconds(distance) + " Seconds!");
            }
        }
        return false;
    }
}
