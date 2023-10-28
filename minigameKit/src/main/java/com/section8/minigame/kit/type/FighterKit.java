package com.section8.minigame.kit.type;

import com.section8.minigame.MinigameKit;
import com.section8.minigame.kit.Kit;
import com.section8.minigame.kit.KitType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class FighterKit extends Kit {

    public FighterKit(MinigameKit minigameKit, UUID uuid)
    {
        super(minigameKit, KitType.FIGHTER, uuid);
    }

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,100,3));
    }

    @EventHandler
    public void onBlockBreaks(BlockBreakEvent e){
        if(uuid.equals(e.getPlayer().getUniqueId())){
            System.out.println(e.getPlayer().getName() + "the Fighter has broken a block");
        }
    }
}
