package com.section._6.command_manager;

import org.bukkit.plugin.java.JavaPlugin;

public final class Command_Manager extends JavaPlugin {

    @Override
    public void onEnable() {
        new TestCommand();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
