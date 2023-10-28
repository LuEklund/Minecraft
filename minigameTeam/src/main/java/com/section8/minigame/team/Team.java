package com.section8.minigame.team;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Team {

    RED(ChatColor.RED + "RED", Material.RED_STAINED_GLASS_PANE),
    BLUE(ChatColor.BLUE + "BLUE", Material.BLUE_STAINED_GLASS_PANE),
    GREEN(ChatColor.GREEN + "GREEN", Material.GREEN_STAINED_GLASS_PANE);

    private Material material;
    private String display;

    Team(String display, Material material) {
        this.display = display;
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplay() {
        return display;
    }

}
