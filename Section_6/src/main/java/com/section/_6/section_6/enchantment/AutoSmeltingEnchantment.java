package com.section._6.section_6.enchantment;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class AutoSmeltingEnchantment extends Enchantment implements Listener {
    public AutoSmeltingEnchantment() {
        super(NamespacedKey.minecraft("auto_smelting"));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        if(!e.isDropItems()) {
            return;
        }
        System.out.println("something was droped");
        if(e.getPlayer().getInventory().getItemInMainHand() == null || e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR){
            return;
        }
        System.out.println("There is something in the main ahnd");
        if(!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(this)) return;
        if(e.getBlock().getType() != Material.IRON_ORE) return;
        e.setDropItems(false);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
    }

    @Override
    public String getName() {
        return "Auto Smelting";
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return true;
    }
}
