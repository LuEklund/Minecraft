package com.section._6.section_6;

import com.xxmicloxx.NoteBlockAPI.songplayer.NoteBlockSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class PlaySongCommand implements CommandExecutor {
    private Section_6 main;
    public PlaySongCommand(Section_6 main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            RadioSongPlayer rsp = new RadioSongPlayer(NBSDecoder.parse(new File(main.getDataFolder(), "Music.nbs")));
            rsp.addPlayer(player.getUniqueId());
            rsp.setPlaying(true);
        }

        return false;
    }
}
