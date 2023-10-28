package com.section._6.section_6;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Custom_skins implements Listener {

    private void setSkin(Player player){
        EntityPlayer ep = ((CraftPlayer) player).getHandle();
        GameProfile gp = ep.getBukkitEntity().getProfile();
        PropertyMap pm = gp.getProperties();
        Property property = pm.get("textures").iterator().next();
        pm.remove("textures", property);
        pm.put("textures", new Property("textures", "eyJ0aW1lc3RhbXAiOjE1ODU3MjM2NDg5NjksInByb2ZpbGVJZCI6IjAyYjBlODZkYzg2YTRhZTdiYzQxMDE1ZDIxZjgwYzFjIiwicHJvZmlsZU5hbWUiOiJab21iaWUiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc4M2FhYWVlMjI4NjhjYWZkYWExZjZmNGEwZTU2YjBmZGI2NGNkMGFlYWFiZDZlODM4MThjMzEyZWJlNjY0MzcifX19","dgv2Ul9dJFGGwEZ9+F7ex/JtI8FdH8UUNvPoLCzAQEu7pWfNlxav3iwNX1GfwPFE8uMWGX/BbwfNlWnzszxxQAywyiuKAz/iEMyDPmRgHlr4Vg+kItuhrhtHFTcWaJhdEiXbv5KBOCQLPb/P2VL6q1ZQpiw1vLGQOO3sLy/H2Zcn32k3p90o1Q4XSlsxBzEOypCNZH45G9h8E0685B99O8kitilJEcZvbNbairctg53KIzcO11DlxubP5P69kAOnpdsFV25giY9oKUHl1NdMtEjz72CSUt1/iKGYuXuZtPvylPqCY0nR5KQTcYV4trYLGGuLM2Apx/0NVVDYakqpY8CtB3gCmACjHRpY7R9lZiey6cA0bqmeT4ksFqWPvAyN1aifhfN0tOlMkYvRGYBRjPQfvAb1r4TsejqRAChh3r0kua+iXvnPDIvYEY2pNeN3xv4ZlDH2iSn3/DtOJxihxlVUN4e7/0pGupFxuswhByX7znTsWLa/yNW9kpWKG6bXS8fBu22tE+q8tW9MZ/D0Ay4SUxtaj7aHSqvoKkPqoi10udRXmlzWmAIEaSL4uhXDncyA78PQJDpaJD2uwarqdMgaKB1MJ1M4BB3XeB1PB7KIDSQ0w3ENne+j2IIwRXcAAWXOqTn1pTtbgjj5Jo9LYEjOwzPAOpuWBoha7C19A3M="));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        setSkin(e.getPlayer());
    }

}
