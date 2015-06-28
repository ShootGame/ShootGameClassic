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
import pl.shg.shootgame.Language;

/**
 *
 * @author Aleksander
 */
public class ServerCommand extends CommandBase {
    public ServerCommand() {
        this.setAliases("server", "serwer");
        this.setDescription("Przejdz na dany serwer lub pokaz informacje o obecnym");
        this.setUsage("[target]");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Language.COMMAND_SERVER_CURRENT_CONSOLE.get(sender, Servers.getOnline().getName()));
        } else if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + Language.COMMAND_SERVER_CURRENT.get(sender,
                    ChatColor.GOLD + Servers.getOnline().getName() + ChatColor.YELLOW + "."));
            sender.sendMessage(ChatColor.YELLOW + Language.COMMAND_SERVER_LIST.get(sender));
        } else {
            String server = this.getStringFromArgs(0, args);
            TargetServer target = Servers.getServer(server);
            if (target == null) {
                target = Servers.findServer(server);
            }
            
            if (target == null) {
                sender.sendMessage(ChatColor.RED + Language.COMMAND_SERVER_NOT_FOUND.get(sender, server));
                return;
            }
            
            if (!target.isPublic() && !sender.hasPermission("shootgame.servers-bypass")) {
                sender.sendMessage(ChatColor.RED + Language.COMMAND_SERVER_PERMISSION.get(sender, target.getName()));
            } else if (!target.isOnline()) {
                sender.sendMessage(ChatColor.RED + Language.COMMAND_SERVER_OFFLINE.get(sender, target.getName()));
            } else {
                Servers.getProxy().connect((Player) sender, target);
            }
        }
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
}
