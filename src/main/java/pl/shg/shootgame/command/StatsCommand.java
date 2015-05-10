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
public class StatsCommand extends CommandBase {
    public StatsCommand() {
        this.setAliases("stats");
        this.setDescription("Pokaz swoje statystyki, lub innego gracza");
        this.setPermission("shootgame.command.stats");
        this.setUsage("[player]");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        
    }
    
    @Override
    public int minArguments() {
        return 0;
    }
}
