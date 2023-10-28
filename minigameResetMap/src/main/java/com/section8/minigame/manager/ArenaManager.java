package com.section8.minigame.manager;

import com.section8.minigame.MinigameResetMap;
import com.section8.minigame.instance.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();


    public ArenaManager(MinigameResetMap minigameResetMap)
    {
        FileConfiguration config = minigameResetMap.getConfig();
        for (String id : config.getConfigurationSection("arenas.").getKeys(false)){
            System.out.println("ID: " + id);
            System.out.println("ID info: " + config.getString("arenas." + id));
            System.out.println("world: " + config.getString("arenas." + id + ".world"));
            World world = Bukkit.createWorld(new WorldCreator(config.getString("arenas." + id + ".world")));
            world.setAutoSave(false);
            arenas.add(new Arena(minigameResetMap, Integer.parseInt(id), new Location(
                    Bukkit.getWorld(config.getString("arenas." + id + ".world")),
                    config.getDouble("arenas." + id + ".x"),
                    config.getDouble("arenas." + id + ".y"),
                    config.getDouble("arenas." + id + ".z"),
                    (float) config.getDouble("arenas." + id + ".yaw"),
                    (float) config.getDouble("arenas." + id + ".pitch"))));
        }
    }

    public List<Arena> getArenas()
    {
        return arenas;
    }

    public Arena getArena(Player player)
    {
        for (Arena arena : arenas)
        {
            if(arena.getPlayers().contains(player.getUniqueId())){
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(int id)
    {
        for (Arena arena : arenas)
        {
            if(arena.getId() == id){
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(World world)
    {
        for (Arena arena : arenas)
        {
            if(arena.getSpawn().getWorld().getName().equals(world.getName())){
                return arena;
            }
        }
        return null;
    }
}
