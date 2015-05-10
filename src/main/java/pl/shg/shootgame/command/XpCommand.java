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
public class XpCommand extends CommandBase {
    public XpCommand() {
        this.setAliases("xp", "pkt");
        this.setDescription("Pokaz ilosc Twojego XP lub innego gracza");
        this.setPermission("shootgame.command.xp");
        this.setUsage("[player]");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("test");
    }

    @Override
    public int minArguments() {
        return 0;
    }
    
}
