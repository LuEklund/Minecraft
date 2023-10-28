package com.section8.minigame.instance;

import com.section8.minigame.GameState;
import com.section8.minigame.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private com.section8.minigame.minigameTeam minigameTeam;
    private Arena arena;
    private int countdownSeconds;

    public Countdown(com.section8.minigame.minigameTeam minigameTeam, Arena arena)
    {
        this.minigameTeam = minigameTeam;
        this.arena = arena;
        this.countdownSeconds = ConfigManager.getCountdownSeconds();
    }

    public void start()
    {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(minigameTeam, 0 , 20);
    }

    @Override
    public void run() {
        if(countdownSeconds == 0){
            cancel();
            arena.start();
            arena.sendTitle("", "");
            return;
        }
        if(countdownSeconds <= 10 || countdownSeconds % 10 == 0)
        {
            arena.sendMessage(ChatColor.GREEN + "Game will start in " + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".");
        }
        arena.sendTitle(ChatColor.GREEN.toString() + countdownSeconds + " seconds" +  (countdownSeconds == 1 ? "" : "s"),
                ChatColor.GRAY + "until game start");

        countdownSeconds--;
    }
}
