package com.section8.minigame.kit;

import com.section8.minigame.MinigameKit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.UUID;

public abstract class Kit implements Listener {

    protected KitType type;
    protected UUID uuid;

    public Kit(MinigameKit minigameKit, KitType type, UUID uuid) {
        this.type = type;
        this.uuid = uuid;

        Bukkit.getPluginManager().registerEvents(this, minigameKit);
    }

    public UUID getUuid() {
        return uuid;
    }

    public KitType getType() {
        return type;
    }

    public abstract void onStart(Player player);

    public void remove() {
        HandlerList.unregisterAll(this);
    }
}
