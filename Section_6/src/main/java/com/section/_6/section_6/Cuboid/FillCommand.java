package com.section._6.section_6.Cuboid;

import com.section._6.section_6.Cuboid.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Cuboid cuboid = new Cuboid(
                    new Location(Bukkit.getWorld("world"), 0 ,80 ,-30),
                    new Location(Bukkit.getWorld("world"), 25,90 ,-5));
            for(Block block : cuboid.getBlocks())
            {
                block.setType(Material.LIME_WOOL);
            }
        }
        return false;
    }
}
