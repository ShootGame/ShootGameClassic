/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.shg.commons.command.CommandBase;
import pl.shg.commons.server.Servers;
import pl.shg.commons.server.TargetServer;

/**
 *
 * @author Aleksander
 */
public class ServerCommand extends CommandBase {
    public ServerCommand() {
        super("server");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Ta komende moze wykonac tylko gracz.");
            return;
        }
        
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Obecnie znajdujesz sie na " + ChatColor.GOLD +
                    Servers.getOnline().getName() + ChatColor.YELLOW + ".");
            sender.sendMessage(ChatColor.YELLOW + "Aby przejsc na inny serwer uzyj /serwer <nazwa>");
        } else {
            String server = args[0].toLowerCase();
            TargetServer target = Servers.getServer(server);
            if (target == null) {
                target = Servers.findServer(server);
            }
            
            if (target != null) {
                Servers.getProxy().connect((Player) sender, target);
            } else {
                sender.sendMessage(ChatColor.RED + "Serwer zawierajacy \"" + args[0] + "\" nie zostal odnaleziony.");
            }
        }
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
}
