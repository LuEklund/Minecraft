package com.section8.bedwars.instance;

import com.section8.bedwars.GameState;
import com.section8.bedwars.Bedwars;
import com.section8.bedwars.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private Bedwars bedwars;
    private Arena arena;
    private int countdownSeconds;

    public Countdown(Bedwars bedwars, Arena arena)
    {
        this.bedwars = bedwars;
        this.arena = arena;
        this.countdownSeconds = ConfigManager.getCountdownSeconds();
    }

    public void start()
    {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(bedwars, 0 , 20);
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
