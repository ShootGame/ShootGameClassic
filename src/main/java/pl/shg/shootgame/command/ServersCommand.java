/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import pl.shg.commons.command.CommandBase;
import pl.shg.commons.server.ArcadeTarget;
import pl.shg.commons.server.LobbyTarget;
import pl.shg.commons.server.MinecraftTarget;
import pl.shg.commons.server.Servers;
import pl.shg.commons.server.TargetServer;
import pl.shg.shootgame.Language;

/**
 *
 * @author Aleksander
 */
public class ServersCommand extends CommandBase {
    public ServersCommand() {
        this.setAliases("servers", "serwery");
        this.setDescription("Pokaz liste dostepnych serwerów");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(CommandBase.getTitle(Language.COMMAND_SERVERS_TITLE.get(sender), null));
        
        for (TargetServer target : Servers.getServers()) {
            if (target.isOnline() && (target.isPublic() || sender.hasPermission("shootgame.servers-bypass"))) {
                if (target instanceof ArcadeTarget) {
                    sender.sendMessage(this.printArcade(sender, (ArcadeTarget) target));
                } else if (target instanceof LobbyTarget) {
                    sender.sendMessage(this.printLobby(sender, (LobbyTarget) target));
                } else if (target instanceof MinecraftTarget) {
                    sender.sendMessage(this.printMinecraft(sender, (MinecraftTarget) target));
                }
            }
        }
        sender.sendMessage(ChatColor.YELLOW + Language.COMMAND_SERVERS_CONNECT.get(sender));
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
    
    private String getPrefix(CommandSender sender, TargetServer target) {
        if (target.isPublic()) {
            return "";
        } else {
            return ChatColor.GOLD + ChatColor.ITALIC.toString() + "[" +
                    Language.COMMAND_SERVERS_TITLE.get(sender) + "] " + ChatColor.RESET;
        }
    }
    
    private String printArcade(CommandSender sender, ArcadeTarget target) {
        return ChatColor.GRAY + "- " + this.getPrefix(sender, target) + ChatColor.DARK_AQUA + ChatColor.BOLD +
                target.getName() + ChatColor.RESET + " " + ChatColor.DARK_PURPLE + target.getArcadePlayers() +
                ChatColor.GRAY + "/" + target.getArcadeSlots() + " - " + target.getColor().toString() +
                ChatColor.ITALIC + target.getMap();
    }
    
    private String printLobby(CommandSender sender, LobbyTarget target) {
        return ChatColor.GRAY + "- " + this.getPrefix(sender, target) + ChatColor.DARK_AQUA + ChatColor.BOLD +
                target.getName() + ChatColor.RESET + " " + ChatColor.DARK_PURPLE + target.getPlayers();
    }
    
    private String printMinecraft(CommandSender sender, MinecraftTarget target) {
        return ChatColor.GRAY + "- " + this.getPrefix(sender, target) + ChatColor.DARK_AQUA + ChatColor.BOLD +
                target.getName() + ChatColor.RESET + " " + ChatColor.DARK_PURPLE + target.getPlayers()+
                ChatColor.GRAY + "/" + target.getSlots();
    }
}
