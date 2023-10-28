package com.section8.bedwars.manager;

import com.section8.bedwars.Bedwars;
import com.section8.bedwars.instance.Arena;
import com.section8.bedwars.instance.BedLocation;
import com.section8.bedwars.instance.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();


    public ArenaManager(Bedwars bedwars)
    {
        FileConfiguration config = bedwars.getConfig();
        for (String id : config.getConfigurationSection("arenas.").getKeys(false)){
            System.out.println("ID:" + id);
            World world = Bukkit.createWorld(new WorldCreator(config.getString("arenas." + id + ".world")));
            System.out.println("WORLD:" + world);
            HashMap<Team, Location> spawns = new HashMap<>();
            for (String team : config.getConfigurationSection("arenas." + id + ".spawns.").getKeys(false)){
                spawns.put(Team.valueOf(team.toUpperCase()), new Location(
                        world,
                        config.getDouble("arenas." + id + ".spawns." + team + ".x"),
                        config.getDouble("arenas." + id + ".spawns." + team + ".y"),
                        config.getDouble("arenas." + id + ".spawns." + team + ".z"),
                        (float) config.getDouble("arenas." + id + ".spawns." + team + ".yaw"),
                        (float) config.getDouble("arenas." + id + ".spawns." + team + ".pitch")));
            }
            HashMap<Team, BedLocation> beds = new HashMap<>();
            for (String team : config.getConfigurationSection("arenas." + id + ".spawns.").getKeys(false)){
                System.out.println("TEAM:" + team);
                System.out.println("BED.X:" + config.getDouble("arenas." + id + ".beds." + team + ".x"));
                System.out.println("BED.Y:" + config.getDouble("arenas." + id + ".beds." + team + ".y"));
                System.out.println("BED.Z:" + config.getDouble("arenas." + id + ".beds." + team + ".z"));
                System.out.println("BED.FACE:" + BlockFace.valueOf(config.getString("arenas." + id + ".beds." + team + ".facing").toUpperCase()));
                beds.put(Team.valueOf(team.toUpperCase()), new BedLocation(
                        world,
                        config.getDouble("arenas." + id + ".beds." + team + ".x"),
                        config.getDouble("arenas." + id + ".beds." + team + ".y"),
                        config.getDouble("arenas." + id + ".beds." + team + ".z"),
                        BlockFace.valueOf(config.getString("arenas." + id + ".beds." + team + ".facing").toUpperCase())));
            }
            arenas.add(new Arena(bedwars, Integer.parseInt(id), Integer.parseInt(config.getString("arenas." + id + ".y-respawn")), spawns, beds));
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
}
