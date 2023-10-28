package com.section8.minigame.instance;

import com.section8.minigame.MinigameResetMap;
import com.section8.minigame.GameState;
import com.section8.minigame.manager.ConfigManager;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private MinigameResetMap minigameResetMap;

    private int id;
    private Location spawn;

    private GameState state;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;
    private boolean canJoin;


    public Arena(MinigameResetMap minigameResetMap, int id, Location spawn)
    {
        this.minigameResetMap = minigameResetMap;

        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(minigameResetMap, this);
        this.game = new Game(this);
        this.canJoin = true;
        //System.out.println("INSTANCE: " + this);
    }



    //-------[GAME]--------
    public void start() {
        game.start();
    }

    public void reset()
    {
        if(state == GameState.LIVE) {
            this.canJoin = false;
            Location loc = ConfigManager.getLobbySpawn();
            for (UUID uuid : players) {
                Bukkit.getPlayer(uuid).teleport(loc);
            }
            players.clear();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String worldName = spawn.getWorld().getName();
            //System.out.println("================================================================");
            //System.out.println("unloading world: " + spawn + " no autosave");
            Bukkit.unloadWorld(spawn.getWorld(), false);
            //System.out.println("UNLOADED world: " + spawn + ", Starting to generate world");
            World world = Bukkit.createWorld(new WorldCreator(worldName));
            //System.out.println("GENERATED world: " + spawn);
            world.setAutoSave(false);
            //System.out.println("AUTOSAVE OFF for world: " + spawn);
            //System.out.println("================================================================");
        }
        sendTitle("","");
        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(minigameResetMap, this);
        game = new Game(this);

    }

    //------[Player Methods]------
    public void addPlayer(Player player){
        players.add(player.getUniqueId());
        System.out.println("================================================================");
        System.out.println("ID: " + id);
        System.out.println("INSTANCE: " + this);
        System.out.println("state: " + state);
        System.out.println("countdown: " + countdown);
        System.out.println("game: " + game);
        System.out.println("Location: " + spawn);
        System.out.println("================================================================");
        if(spawn == null)
        {
            FileConfiguration config = minigameResetMap.getConfig();
            spawn = new Location(
                    Bukkit.getWorld(config.getString("arenas." + id + ".world")),
                    config.getDouble("arenas." + id + ".x"),
                    config.getDouble("arenas." + id + ".y"),
                    config.getDouble("arenas." + id + ".z"),
                    (float) config.getDouble("arenas." + id + ".yaw"),
                    (float) config.getDouble("arenas." + id + ".pitch"));
            System.out.println("Location was NULL now is: " + spawn);
        }
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
            reset();
        }
        else if (state == GameState.LIVE && players.size() < ConfigManager.getRequiredPlayers()) {
            sendMessage(ChatColor.RED + "The game has ended, as too many players have left");

            reset();
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
    public boolean canJoin() {
        return canJoin;
    }

    //-------[SETTERS]--------

    public void setState(GameState state)
    {
        this.state = state;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void toggleCanJoin(){
        this.canJoin = !this.canJoin;
    }
}
