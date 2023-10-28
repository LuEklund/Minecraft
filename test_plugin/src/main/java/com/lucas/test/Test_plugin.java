package com.lucas.test;

import com.google.gson.Gson;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.Color;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rail;
import org.bukkit.block.data.type.Cake;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.block.data.type.Sign;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public final class Test_plugin extends JavaPlugin implements Listener {

    private BossBar Bar;
    private PotionEffect effectSpeed;

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    @Override
    public void onEnable() {



        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "custom_diamond_Sword"), new ItemStack(Material.DIAMOND_SWORD));
        recipe.shape(
                " D ",
                " D ",
                " D "
        );
        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);

        ItemStack is = new ItemStack(Material.STICK);
        is.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta isMeta = is.getItemMeta();
        isMeta.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Custom Stick!");
        is.setItemMeta(isMeta);


        recipe = new ShapedRecipe(new NamespacedKey(this, "custom_stick"), is);
        recipe.shape(
                "GGG",
                "GSG",
                "GGG"
        );
        recipe.setIngredient('G', Material.GOLD_BLOCK);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);





        Bukkit.getScheduler().runTaskLater(this, () -> {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("10sec after start");
            }
        }, 200);

        //file maneging
        try {
            initiateFile("data.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /*Bukkit.getScheduler().runTaskTimer(this, () ->{
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("15sec and 1sec");
            }
        }, 300, 20);*/
        Bukkit.getPluginManager().registerEvents(new ToggleListener(), this);
        Bukkit.getPluginManager().registerEvents(new ScoreBoard(), this);
        Bukkit.getPluginManager().registerEvents(new Riding(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new NameTagListener(), this);
        Bar = Bukkit.createBossBar(
                ChatColor.AQUA + "faked",
                BarColor.PINK,
                BarStyle.SEGMENTED_20
        );

        Bar.setProgress(0.6);
        Bukkit.getPluginManager().registerEvents(this, this);

        effectSpeed = new PotionEffect(PotionEffectType.SPEED, 200, 4, true);
        ArmorStand stand = (ArmorStand) Bukkit.getWorld("World").spawnEntity(new Location(Bukkit.getWorld("World"), 0, 67 ,-8), EntityType.ARMOR_STAND);
        //stand.remove();
        //stand.


        getConfig().options().copyDefaults();
        saveDefaultConfig();
        // Plugin startup logic
        System.out.println("Hello World! Leta a go");
        getCommand("test").setExecutor(new TestCommand(this));
        getCommand("menu").setExecutor(new GUICommand());
        getCommand("cool").setExecutor(new Cooldown());
        getCommand("perms").setExecutor(new PermsCommand(this));
        getCommand("config").setExecutor(new ConfigCommand(this));
        getCommand("he").setExecutor(new HealCommand());
        getCommand("he").setTabCompleter(new HealTab());

    }


    private void initiateFile(String name) throws Exception
    {
        Data data = new Data("Lucas", true, new Date());
        try {
            getDataFolder().mkdir(); //creates the DataFolder (plugin folder)
            File file = new File(getDataFolder(),name);
            if (!file.exists()) {
                file.createNewFile();
                Gson gson = new Gson();
                Writer writer = new FileWriter(file, false);
                gson.toJson(data, writer);
                writer.flush();
                writer.close();
                System.out.println("saved data!");
            }
            else
            {
                Gson gson = new Gson();
                Reader reader = new FileReader(file);
                Data readData = gson.fromJson(reader, Data.class);
                System.out.println(readData.getDate().toString() + readData.getPlayerName());
                reader.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("cant load file error");
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        if (e.getHand().equals(EquipmentSlot.HAND))
        {
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            {
                if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_HOE))
                {
                    Vector vec = player.getLocation().getDirection();
                    vec.multiply(5);
                    Creeper creeper = (Creeper)player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER,false);
                    creeper.setVelocity(vec);
                    creeper.ignite();
                    creeper.addPassenger(player);
                    //Arrow arrow = player.launchProjectile(Arrow.class, player.getLocation().getDirection());
                    //arrow.setGravity(false);
                }
                else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
                {
                    BlockState state = e.getClickedBlock().getState();
                    BlockData data = state.getBlockData();
                    if (data instanceof Cake)
                    {
                        ((Cake) data).setBites(2);
                    } else if (data instanceof GlassPane) {
                        ((GlassPane) data).setWaterlogged(true);
                    } else if (data instanceof Rail) {
                        ((Rail) data).setShape(Rail.Shape.NORTH_WEST);
                    } else if (data instanceof Sign) {
                        ((Sign) data).setRotation(BlockFace.NORTH);
                    }
                    state.setBlockData(data);
                    state.update();
                }
            }
        }
    }
    @EventHandler
    public void onLaunch(ServerListPingEvent e)
    {
        e.setMaxPlayers(42);
        e.setMotd(ChatColor.BLACK + "COol server!\n" + ChatColor.AQUA + "good!");
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {

        Player player = e.getPlayer();
        if(e.isSneaking())
        {
            if (player.getTargetBlockExact(5) != null && player.getTargetBlockExact(5).getType().equals(Material.OAK_SIGN))
            {
                player.sendSignChange(player.getTargetBlockExact(5).getLocation(), new String[]{
                        "this",
                        "test",
                        "is!",
                        " "
                });
                //player.sendBlockChange(player.getTargetBlockExact(5).getLocation(), Material.AIR.createBlockData());
            }
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_CAT_HISS, 1.0F, 1.0F);
        }
        /*
        Firework fire = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
        FireworkMeta meta = fire.getFireworkMeta();
        meta.addEffect(FireworkEffect.builder().withColor(Color.RED).withColor(Color.AQUA).with(FireworkEffect.Type.BURST).withFlicker().build());
        meta.setPower(1);
        fire.setFireworkMeta(meta);*/
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        e.getPlayer().addPotionEffect(effectSpeed);
        for (PotionEffect effect : e.getPlayer().getActivePotionEffects())
        {
            e.getPlayer().sendMessage(ChatColor.RED + "you have effect: " + effect.getType());
        }
        Bar.addPlayer(e.getPlayer());
        e.getPlayer().sendTitle(
                ChatColor.RED + "Hello!",
                ChatColor.GREEN + "Welcome to the server!",
                20,
                100,
                30);
        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4§LHelloo There!"));
        e.getPlayer().setPlayerListHeaderFooter(ChatColor.RED + "Hello", "First line\n sec line!");

        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helm_meta = (LeatherArmorMeta)helm.getItemMeta();
        helm_meta.setColor(Color.BLUE);
        helm.setItemMeta(helm_meta);
        e.getPlayer().getInventory().addItem(helm);
        e.getPlayer().getInventory().setHelmet(helm);


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {

        if(perms.containsKey(e.getPlayer().getUniqueId()))
        {
            System.out.println("removed player from list " + e.getPlayer().getName());
            perms.remove(e.getPlayer().getUniqueId());
        }
        else
            System.out.println("player not in list " + e.getPlayer().getName());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public HashMap<UUID, PermissionAttachment> getPerms() {return perms;}
    //private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
}
