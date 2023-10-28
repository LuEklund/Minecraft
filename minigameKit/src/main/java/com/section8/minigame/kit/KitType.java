package com.section8.minigame.kit;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum KitType {

    MINER(ChatColor.GOLD + "Miner", Material.DIAMOND_PICKAXE, "I am a Miner"),
    FIGHTER(ChatColor.RED + "Fighter", Material.DIAMOND_SWORD, "FIGHT FIGHT!");

    private String display;
    private String description;
    private Material material;

    KitType(String display, Material material, String description){
        this.display = display;
        this.material = material;
        this.description = description;

    }

    public String getDisplay() {
        return display;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDescription() {
        return description;
    }
}
