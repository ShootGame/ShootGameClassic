/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.command.def;

import org.bukkit.command.CommandSender;
import pl.shg.shootgame.api.command.Command;

/**
 *
 * @author Aleksander
 */
public class XpCommand extends Command {
    public XpCommand() {
        super("xp");
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
