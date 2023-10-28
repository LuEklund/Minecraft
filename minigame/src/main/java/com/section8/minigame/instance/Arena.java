package com.section8.minigame.instance;

import com.section8.minigame.Minigame;
import com.section8.minigame.GameState;
import com.section8.minigame.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Arena {

    private Minigame minigame;

    private int id;
    private Location spawn;

    private GameState state;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;

    public Arena(Minigame minigame, int id, Location spawn)
    {
        this.minigame = minigame;

        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(minigame, this);
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
        countdown = new Countdown(minigame, this);
        game = new Game(this);

    }

    //------[Player Methods]------
    public void addPlayer(Player player){
        players.add(player.getUniqueId());
        player.teleport(spawn);
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

    public int getId() {
        return id;
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
