package com.section8.bedwars.instance;

import com.section8.bedwars.Bedwars;
import com.section8.bedwars.GameState;
import com.section8.bedwars.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Arena {

    private Bedwars bedwars;



    private int id;
    private int yRespawn;
    private HashMap<Team, Location> spawns;
    private HashMap<Team, BedLocation> beds;

    private GameState state;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;

    public Arena(Bedwars bedwars, int id, int yRespawn, HashMap<Team, Location> spawn, HashMap<Team, BedLocation> beds)
    {
        this.bedwars = bedwars;

        this.id = id;
        this.yRespawn = yRespawn;
        this.spawns = spawn;
        this.beds = beds;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(bedwars, this);
        this.game = new Game(this);
    }



    //-------[GAME]--------
    public void start() {
        game.start();
    }

    public void reset(boolean kickPlayers)
    {
        if(kickPlayers)
        {
            Location loc = ConfigManager.getLobbySpawn();
            for (UUID uuid : players)
            {
                Bukkit.getPlayer(uuid).teleport(loc);
            }
            players.clear();
        }
        sendTitle("","");
        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(bedwars, this);
        game.cancelTasks();
        game = new Game(this);

    }

    //------[Player Methods]------
    public void addPlayer(Player player){
        players.add(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        if(state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers())
        {
            countdown.start();
        }
    }
    public void removePlayer(Player player){
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("","",0,10,0);

        if(state == GameState.COUNTDOWN && players.size() < ConfigManager.getRequiredPlayers()) {
            sendMessage(ChatColor.RED + "Not enough Players countdown stopped");
            reset(false);
        }
        else if (state == GameState.LIVE && players.size() < ConfigManager.getRequiredPlayers()) {
            sendMessage(ChatColor.RED + "The game has ended, as too many players have left");

            reset(false);
        }
    }
    public void sendMessage(String message)
    {
        for (UUID uuid : players)
        {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }
    public void sendTitle(String title, String subTitle)
    {
        for (UUID uuid : players)
        {
            Bukkit.getPlayer(uuid).sendTitle(title, subTitle,10,70,20);
        }
    }

    //--------[GETTERS]---------
    public List<UUID> getPlayers() {
        return players;
    }

    public GameState getState()
    {
        return state;
    }
    public HashMap<Team, Location> getSpawns() {return spawns;}
    public HashMap<Team, BedLocation> getBeds() {return beds;}
    public Bedwars getBedwars() { return bedwars;}
    public int getId() {
        return id;
    }

    public int getyRespawn() {
        return yRespawn;
    }

    public Game getGame() {
        return game;
    }

    //-------[SETTERS]--------

    public void setState(GameState state)
    {
        this.state = state;
    }



}
