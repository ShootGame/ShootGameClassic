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
        this.setAliases("server", "serwer");
        this.setDescription("Przejdz na dany serwer lub pokaz informacje o obecnym");
        this.setPermission("shootgame.command.server");
        this.setUsage("[target]");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Ten serwer to " + Servers.getOnline().getName() + ".");
        } else if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Obecnie znajdujesz sie na " + ChatColor.GOLD +
                    Servers.getOnline().getName() + ChatColor.YELLOW + ".");
            sender.sendMessage(ChatColor.YELLOW + "Aby wyswietlic liste serwerów uzyj /serwery");
        } else {
            String server = this.getStringFromArgs(0, args);
            TargetServer target = Servers.getServer(server);
            if (target == null) {
                target = Servers.findServer(server);
            }
            
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Serwer \"" + server + "\" nie zostal odnaleziony.");
                return;
            }
            
            if (target.isPublic() || sender.hasPermission("shootgame.servers-bypass")) {
                Servers.getProxy().connect((Player) sender, target);
            } else {
                sender.sendMessage(ChatColor.RED + "Serwer " + target.getName() +
                        " jest prywatny oraz nie posiadasz do niego dostepu.");
            }
        }
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
}
