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
import pl.shg.commons.server.ArcadeTarget;
import pl.shg.commons.server.LobbyTarget;
import pl.shg.commons.server.MinecraftTarget;
import pl.shg.commons.server.Servers;
import pl.shg.commons.server.TargetServer;

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
        sender.sendMessage(CommandBase.getTitle("Lista serwerów ShootGame", null));
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.GOLD + "Znajdujesz sie na serwerze " +
                    ChatColor.GREEN + ChatColor.BOLD + Servers.getOnline().getName());
        }
        
        for (TargetServer target : Servers.getServers()) {
            if (target.isOnline() && (target.isPublic() || sender.hasPermission("shootgame.servers-bypass"))) {
                if (target instanceof ArcadeTarget) {
                    sender.sendMessage(this.printArcade((ArcadeTarget) target));
                } else if (target instanceof LobbyTarget) {
                    sender.sendMessage(this.printLobby((LobbyTarget) target));
                } else if (target instanceof MinecraftTarget) {
                    sender.sendMessage(this.printMinecraft((MinecraftTarget) target));
                }
            }
        }
        sender.sendMessage(ChatColor.YELLOW + "Aby przejsc na dany serwer uzyj /serwer <nazwa>");
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
    
    private String getPrefix(TargetServer target) {
        if (target.isPublic()) {
            return "";
        } else {
            return ChatColor.GOLD + ChatColor.ITALIC.toString() + "[Ukryty] " + ChatColor.RESET;
        }
    }
    
    private String printArcade(ArcadeTarget target) {
        return ChatColor.GRAY + "- " + this.getPrefix(target) + ChatColor.DARK_AQUA + ChatColor.BOLD +
                target.getName() + ChatColor.RESET + " " + ChatColor.DARK_PURPLE + target.getArcadePlayers() +
                ChatColor.GRAY + "/" + target.getArcadeSlots() + " - " + target.getColor().toString() +
                ChatColor.ITALIC + target.getMap();
    }
    
    private String printLobby(LobbyTarget target) {
        return ChatColor.GRAY + "- " + this.getPrefix(target) + ChatColor.DARK_AQUA + ChatColor.BOLD +
                target.getName() + ChatColor.RESET + " " + ChatColor.DARK_PURPLE + target.getPlayers();
    }
    
    private String printMinecraft(MinecraftTarget target) {
        return ChatColor.GRAY + "- " + this.getPrefix(target) + ChatColor.DARK_AQUA + ChatColor.BOLD +
                target.getName() + ChatColor.RESET + " " + ChatColor.DARK_PURPLE + target.getPlayers()+
                ChatColor.GRAY + "/" + target.getSlots();
    }
}
