package com.section._6.section_6;

import com.section._6.section_6.BroadCast.BroadCastListener;
import com.section._6.section_6.BroadCast.BroadcastCommand;
import com.section._6.section_6.Cuboid.Cuboid;
import com.section._6.section_6.Cuboid.FillCommand;
import com.section._6.section_6.Discord.DiscordListener;
import com.section._6.section_6.Discord.GiveRoleCommand;
import com.section._6.section_6.GUI.GUICommand;
import com.section._6.section_6.GUI.GUIListener;
import com.section._6.section_6.enchantment.AutoSmeltingEnchantment;
import com.sk89q.worldedit.WorldEdit;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import org.mozilla.javascript.EcmaError;

import javax.security.auth.login.LoginException;
import java.lang.reflect.Field;

public final class Section_6 extends JavaPlugin{

    //Custom enchantment
    private void registerEnchantment(Enchantment enchantment)
    {
        try{
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        }catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private Cuboid cuboid;
    private JDA jda;
    @Override
    public void onEnable() {
        //worldedit api
        WorldEdit we = WorldEdit.getInstance();
        System.out.println("api working: " + we != null);

        if(!this.getDataFolder().exists()){
            this.getDataFolder().mkdir();
        }

        //Cuboid Api
        cuboid = new Cuboid(
                new Location(Bukkit.getWorld("world"), 0 ,80 ,-30),
                new Location(Bukkit.getWorld("world"), 25,90 ,-5));

        //custom enchantment
        AutoSmeltingEnchantment as = new AutoSmeltingEnchantment();
        Bukkit.getPluginManager().registerEvents(as, this);
        registerEnchantment(as);
        Bukkit.getPluginManager().registerEvents(new JoinListener(as.getKey()), this);

        //open AI
        TalkCommand talk = new TalkCommand(this);
        getCommand("talk").setExecutor(talk);
        Bukkit.getPluginManager().registerEvents(talk, this);

        //GIU
        getCommand("menu").setExecutor(new GUICommand());
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);

        //Discord Bot
        //MTA5MTIzMDU5OTg0ODY2OTE5NQ.G_X6fV.dz02OlXK6bwFBA8VhgN_OZxcsckGSiBvitNrIo
        getCommand("giverole").setExecutor(new GiveRoleCommand(this));
        try {
            JDABuilder builder = JDABuilder.createDefault("MYTOKEN");
            builder.setActivity(Activity.watching("YOur serevr"));
            builder.addEventListeners(new DiscordListener());
            jda = builder
                    .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                    .build();
            System.out.println("Success!");
        }catch (Exception e) {
            e.printStackTrace();
        }

        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(cuboid), this);
        Bukkit.getPluginManager().registerEvents(new Custom_skins(), this);
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("playsong").setExecutor(new PlaySongCommand(this));
        getCommand("anvil").setExecutor(new AnvilCommand(this));
        getCommand("fi").setExecutor(new FillCommand());
        Bukkit.getPluginManager().registerEvents(new BroadCastListener(), this);

        // Plugin startup logic

    }

    public JDA getJda(){return jda;}
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
