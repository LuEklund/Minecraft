package com.lucas.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.ListIterator;

public class ConfigCommand implements CommandExecutor {

    private Test_plugin main;

    public ConfigCommand(Test_plugin receiving_test){
        this.main = receiving_test;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            main.getConfig().set("Word", "cakes");
            List<String> list = main.getConfig().getStringList("String-list");
            list.add("new VALUE!!!");
            main.getConfig().set("String-list", list);
            main.saveConfig();


            player.sendMessage(main.getConfig().getString("Word"));
            player.sendMessage(main.getConfig().getInt("number") + "");
            if(main.getConfig().getBoolean("enableTrue")){
                player.sendMessage("enableTrue is true");
            }
            for (String string : main.getConfig().getStringList("String-list"))
            {
                player.sendMessage(string);
            }


        }
        return false;
    }
}
