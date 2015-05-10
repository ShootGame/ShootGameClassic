/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.command;

import org.bukkit.command.CommandSender;
import pl.shg.commons.command.CommandBase;

/**
 *
 * @author Aleksander
 */
public class ShgCommand extends CommandBase {
    public ShgCommand() {
        this.setAliases("shg", "shootgame");
        this.setDescription("Informacje oraz statystyki serwera");
        this.setPermission("shootgame.command.manage");
        this.setUsage("-h - for help");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
}
