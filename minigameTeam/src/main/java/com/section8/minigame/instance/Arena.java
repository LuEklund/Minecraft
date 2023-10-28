package com.section8.minigame.instance;

import com.google.common.collect.TreeMultimap;
import com.section8.minigame.GameState;
import com.section8.minigame.manager.ConfigManager;
import com.section8.minigame.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Arena {

    private com.section8.minigame.minigameTeam minigameTeam;

    private int id;
    private Location spawn;

    private GameState state;
    private List<UUID> players;
    private HashMap<UUID, Team> teams;
    private Countdown countdown;
    private Game game;

    public Arena(com.section8.minigame.minigameTeam minigameTeam, int id, Location spawn)
    {
        this.minigameTeam = minigameTeam;

        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.teams = new HashMap<>();
        this.countdown = new Countdown(minigameTeam, this);
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
            teams.clear();
        }
        sendTitle("","");
        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(minigameTeam, this);
        game = new Game(this);

    }

    //------[Player Methods]------
    public void addPlayer(Player player){
        players.add(player.getUniqueId());
        player.teleport(spawn);

        TreeMultimap<Integer, Team> count = TreeMultimap.create();
        for(Team team : Team.values()){
            count.put(getTeamCount(team), team);
        }

        Team lowest = (Team) count.values().toArray()[0];
        setTeam(player, lowest);
        player.sendMessage(ChatColor.AQUA + "You have been automatically added to the " + lowest + " team!");

        if(state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers())
        {
            countdown.start();
        }
    }

    public void removePlayer(Player player){
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("","",0,10,0);
        removeTeam(player);

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

    public int getTeamCount(Team team)
    {
        int amount = 0;
        for (Team t : teams.values()){
            if(t == team)
                amount++;
        }
        return amount;
    }

    public Team getTeam(Player player)
    {
        return teams.get(player.getUniqueId());
    }

    //-------[SETTERS]--------

    public void setState(GameState state)
    {
        this.state = state;
    }
    public void setTeam(Player player, Team team)
    {
        removeTeam(player);
        teams.put(player.getUniqueId(), team);
    }

    public void removeTeam(Player player){
        if(teams.containsKey(player.getUniqueId())){
            teams.remove(player.getUniqueId());
        }
    }

}
