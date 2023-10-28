package com.section._6.command_manager;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TestCommand extends Command{

    public TestCommand()
    {
        super(
                "test",
                new String[]{"cake","bana"},
                "COol CMD",
                "test.test");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        Bukkit.broadcastMessage("Cmd manager WORKS!");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
