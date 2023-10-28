package com.rank.test.rank_system.manager;

import com.rank.test.rank_system.Rank;
import com.rank.test.rank_system.Rank_System;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private Rank_System main;
    private File file;
    private YamlConfiguration config;
    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public RankManager(Rank_System main)
    {
        this.main = main;
        if(!main.getDataFolder().exists()){
            main.getDataFolder().mkdir();
        }

        file = new File(main.getDataFolder(), "ranks.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setRank(UUID uuid, Rank rank, boolean firstJoin)
    {
        if (Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin)
        {
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if (perms.containsKey(uuid))
            {
                attachment = perms.get(uuid);
            }
            else {
                attachment = player.addAttachment(main);
                perms.put(uuid, attachment);
            }
            for (String perm : getRank(uuid).getPermsissions()){
                if (player.hasPermission(perm))
                {
                    attachment.unsetPermission(perm);
                }
            }
            for (String perm : rank.getPermsissions()){
                attachment.setPermission(perm, true);
            }
        }

        config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin){
            Player player = Bukkit.getPlayer(uuid);
            main.getNametagManager().removeTag(player);
            main.getNametagManager().newTag(player);
        }


    }

    public Rank getRank(UUID uuid)
    {
        return Rank.valueOf(config.getString(uuid.toString()));
    }
    public HashMap<UUID, PermissionAttachment> getPerms() {return perms;}
}
