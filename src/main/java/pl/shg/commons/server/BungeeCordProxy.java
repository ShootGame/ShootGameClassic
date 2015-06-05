/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.shg.shootgame.plugin.ShootPlugin;

/**
 *
 * @author Aleksander
 */
public class BungeeCordProxy implements IProxiedServer {
    @Override
    public void connect(Player player, TargetServer server) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(array);
        
        player.sendMessage(ChatColor.YELLOW + "Laczenie z serwerem " + ChatColor.AQUA + server.getName() + ChatColor.YELLOW + "...");
        try {
            output.writeUTF("Connect");
            output.writeUTF(server.getID());
        } catch (IOException ex) {
            player.sendMessage(ChatColor.RED + "Nastapil blad podczas laczenia z serwerem " + server + ": " + ex.getMessage());
            Logger.getLogger(BungeeCordProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        player.sendPluginMessage(ShootPlugin.getPlugin(), "BungeeCord", array.toByteArray());
    }
}
