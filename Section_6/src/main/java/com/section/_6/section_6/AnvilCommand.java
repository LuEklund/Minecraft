package com.section._6.section_6;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AnvilCommand implements CommandExecutor {
    private Section_6 main;

    public AnvilCommand(Section_6 main)
    {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            new AnvilGUI.Builder()
                    .onComplete((target, text) ->{
                        Bukkit.broadcastMessage(target.getName() + " says " + text + " ");
                        return  AnvilGUI.Response.close();
                    })
                    .text("Message...")
                    .title("Broadcast Message")
                    .itemLeft(new ItemStack(Material.NAME_TAG))
                    .plugin(main)
                    .open(player);
        }
        return false;
    }
}
