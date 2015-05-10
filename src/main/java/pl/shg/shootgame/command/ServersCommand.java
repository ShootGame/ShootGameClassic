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
        super("servers");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(CommandBase.getTitle("Lista serwerów ShootGame", null));
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.GOLD + "Znajdujesz sie na " + ChatColor.GREEN + ChatColor.BOLD + Servers.getOnline().getName());
        }
        
        for (TargetServer target : Servers.getServers()) {
            if (target.isOnline() && (target.isPublic() || sender.hasPermission("shootgame.servers-bypass"))) {
                if (target instanceof ArcadeTarget) {
                    sender.sendMessage(this.printArcade((ArcadeTarget) target));
                } else if (target instanceof LobbyTarget) {
                    
                } else if (target instanceof MinecraftTarget) {
                    sender.sendMessage(this.printMinecraft((MinecraftTarget) target));
                }
            }
        }
        sender.sendMessage(ChatColor.YELLOW + "Aby przejsc na inny serwer uzyj /serwer <nazwa>");
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
    
    private String printArcade(ArcadeTarget target) {
        String prefix = "";
        if (!target.isPublic()) {
            prefix = ChatColor.GOLD + ChatColor.ITALIC.toString() + "[Ukryty] " + ChatColor.RESET;
        }
        return ChatColor.GRAY + "- " + prefix + ChatColor.DARK_AQUA + ChatColor.BOLD + target.getName() +
                ChatColor.RESET + " " + ChatColor.DARK_PURPLE + target.getArcadePlayers() +
                ChatColor.GRAY + "/" + target.getArcadeSlots() + " - " + ChatColor.DARK_AQUA +
                ChatColor.ITALIC + target.getMap();
    }
    
    private String printMinecraft(MinecraftTarget target) {
        String prefix = "";
        if (!target.isPublic()) {
            prefix = ChatColor.GOLD + ChatColor.ITALIC.toString() + "[Ukryty] " + ChatColor.RESET;
        }
        return ChatColor.GRAY + "- " + prefix + ChatColor.DARK_AQUA + ChatColor.BOLD + target.getName() +
                ChatColor.RESET + " " + ChatColor.DARK_PURPLE + target.getPlayers()+
                ChatColor.GRAY + "/" + target.getSlots();
    }
}
