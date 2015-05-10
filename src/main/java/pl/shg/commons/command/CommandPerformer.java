/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.command;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Aleksander
 */
public class CommandPerformer extends Command {
    private final CompleteCommand executor;
    
    public CommandPerformer(CompleteCommand executor, String command) {
        super(command);
        this.executor = executor;
    }
    
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        return this.executor.onCommand(sender, this, label, args);
    }
    
    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return this.executor.onTabComplete(sender, this, alias, args);
    }
}
