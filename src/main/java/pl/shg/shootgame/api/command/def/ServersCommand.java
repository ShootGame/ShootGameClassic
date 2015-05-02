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
import pl.shg.shootgame.api.Color;
import pl.shg.shootgame.api.command.Command;
import pl.shg.shootgame.api.server.ArcadeTarget;
import pl.shg.shootgame.api.server.LobbyTarget;
import pl.shg.shootgame.api.server.MinecraftTarget;
import pl.shg.shootgame.api.server.Servers;
import pl.shg.shootgame.api.server.TargetServer;

/**
 *
 * @author Aleksander
 */
public class ServersCommand extends Command {
    public ServersCommand() {
        super("servers");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(Command.getTitle("Lista serwerów ShootGame", null));
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.GOLD + "- " + Color.GREEN + Color.BOLD + Servers.getOnline().getName());
        }
        
        for (TargetServer target : Servers.getServers()) {
            if (target.isPublic() || sender.hasPermission("shootgame.servers-bypass")) {
                if (target instanceof ArcadeTarget) {
                    sender.sendMessage(this.printArcade((ArcadeTarget) target));
                } else if (target instanceof LobbyTarget) {
                    
                } else if (target instanceof MinecraftTarget) {
                    sender.sendMessage(this.printMinecraft((MinecraftTarget) target));
                }
            }
        }
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
    
    private String printArcade(ArcadeTarget target) {
        return ChatColor.GRAY + "- " + Color.DARK_AQUA + Color.BOLD + target.getName() +
                Color.RESET + " " + Color.DARK_PURPLE + target.getArcadePlayers() +
                Color.GRAY + "/" + target.getArcadeSlots() + " - " + Color.DARK_AQUA +
                Color.ITALIC + target.getMap();
    }
    
    private String printMinecraft(MinecraftTarget target) {
        return ChatColor.GRAY + "- " + Color.DARK_AQUA + Color.BOLD + target.getName() +
                Color.RESET + " " + Color.DARK_PURPLE + target.getPlayers()+
                Color.GRAY + "/" + target.getSlots();
    }
}
