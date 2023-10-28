package com.lucas.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class GUICommand implements CommandExecutor {

    private void AddItem(Material material, String name, String lore, int inv_pos, Inventory inv)
    {
        ItemStack Item;
        Item = new ItemStack(material);
        ItemMeta meta = Item.getItemMeta();
        meta = Item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        Item.setItemMeta(meta);
        inv.setItem(inv_pos,Item);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            Inventory inv = Bukkit.createInventory(player, 54, ChatColor.BLUE.toString() + ChatColor.BOLD + "GUI menu");
            AddItem(Material.ENDER_PEARL, ChatColor.BLUE + "Random teleport", ChatColor.GRAY + "Teleport to random player!", 20, inv);
            AddItem(Material.DIAMOND_SWORD, ChatColor.RED + "DEATH", ChatColor.GRAY + "kills you", 22, inv);
            AddItem(Material.BUCKET, ChatColor.WHITE + "Clear Inventory", ChatColor.GRAY + "empty's your inventory", 24, inv);
            AddItem(Material.BARRIER, ChatColor.RED + "Close GUI", ChatColor.GRAY + "close the menu", 0, inv);

            for (int i : new int[]{1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44})
            {
                AddItem(Material.BLACK_STAINED_GLASS_PANE, "", " ", i, inv);
            }

            ItemStack is = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta)is.getItemMeta();
            meta.setOwningPlayer(player);
            meta.setDisplayName(ChatColor.DARK_PURPLE + player.getName());
            meta.setLore(Arrays.asList(ChatColor.GRAY + "this is you"));
            is.setItemMeta(meta);
            inv.setItem(21,is);
            player.openInventory(inv);
        }
        return false;
    }
}
