package com.rank.test.rank_system;

import org.bukkit.ChatColor;

public enum Rank {

    OWNER(ChatColor.DARK_RED.toString() + ChatColor.BOLD +"Owner ", new String[]{"worldedit.help"}),
    ADMIN(ChatColor.RED + "Admin ", new String[]{"worldedit.help"}),
    MEMBER(ChatColor.YELLOW + "Member ", new String[]{}),
    GUEST(ChatColor.GRAY + "Guest ", new String[]{});

    private String display;

    private String[] permissions;


    Rank(String display, String[] permissions){
        this.permissions = permissions;
        this.display = display;
    }

    public String getDisplay(){return display; }
    public String[] getPermsissions(){return permissions; }

}
