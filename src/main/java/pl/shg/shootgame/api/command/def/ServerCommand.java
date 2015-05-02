/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.command.def;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.shg.shootgame.api.command.Command;
import pl.shg.shootgame.api.server.Servers;

/**
 *
 * @author Aleksander
 */
public class ServerCommand extends Command {
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
            
        }
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
}
