package com.section._6.section_6.Discord;

import com.section._6.section_6.Section_6;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.UserSnowflake;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveRoleCommand implements CommandExecutor {
    private Section_6 main;

    public GiveRoleCommand(Section_6 main)
    {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(sender instanceof Player)
        {
            Guild guild = main.getJda().getGuildById("1091232850910662656");
            guild.addRoleToMember(UserSnowflake.fromId("402859016457420820"), guild.getRoleById("1091616598940463184")).queue();
            guild.getTextChannelById("1091232851514638348").sendMessage("Successfully given :)").queue();
            ((Player)sender).sendMessage("Got new role WOW!");

        }

        return false;
    }
}
