/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.command;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pl.shg.shootgame.api.Log;

/**
 *
 * @author Aleksander
 */
public class Executor implements CompleteCommand {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        CommandBase cmd = CommandBase.getCommand(command.getName());
        if (cmd != null) {
            if (args.length < cmd.minArguments()) {
                sender.sendMessage(ChatColor.RED + cmd.getUsage());
            } else {
                cmd.execute(sender, args);
            }
        } else {
            Log.admins("Nie znaleziono executora dla komendy " + command.getName() + ".");
        }
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmnd, String string, String[] strings) {
        return null;
    }
}
