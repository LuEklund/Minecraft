package com.lucas.test;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class TestCommand implements CommandExecutor {

    private Test_plugin main;

    public TestCommand(Test_plugin main)
    {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player)
        {
            NamespacedKey key = new NamespacedKey(main,"surname");
            Player player = (Player)sender;
            PersistentDataContainer pdc = player.getPersistentDataContainer();
            pdc.set(key, PersistentDataType.STRING, "Eklund");

            if(pdc.has(key, PersistentDataType.STRING))
            {
                String surname = pdc.get(key, PersistentDataType.STRING);
                player.sendMessage(surname);

            }

            if(player.hasPermission("test_plugin.use"))
            {
                player.sendMessage("permission good");
            }
            else
            {
                player.sendMessage("permission Bad");
            }

        }
        return false;
    }
}
