package com.section._6.section_6;

import com.section._6.section_6.Cuboid.Cuboid;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private Cuboid cuboid;

    public PlayerMoveListener(Cuboid cuboid)
    {
        this.cuboid = cuboid;
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        if(cuboid.contains(e.getPlayer().getLocation()))
        {
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("In the region"));
        }
    }
}
