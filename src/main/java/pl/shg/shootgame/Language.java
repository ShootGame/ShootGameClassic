/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.shg.commons.bukkit.UserUtils;
import pl.shg.commons.translations.LangMessage;
import pl.shg.commons.translations.Translations;

/**
 *
 * @author Aleksander
 */
public enum Language {
    COMMAND_SERVER_CURRENT("command.server.current"),
    COMMAND_SERVER_CURRENT_CONSOLE("command.server.current-console"),
    COMMAND_SERVER_LIST("command.server.list"),
    COMMAND_SERVER_NOT_FOUND("command.server.not-found"),
    COMMAND_SERVER_OFFLINE("command.server.offline"),
    COMMAND_SERVER_PERMISSION("command.server.permission"),
    
    COMMAND_SERVERS_CONNECT("command.servers.connect"),
    COMMAND_SERVERS_HIDDEN("command.servers.hidden"),
    COMMAND_SERVERS_TITLE("command.servers.title"),
    
    LISTENERS_JOIN("listeners.join"),
    LISTENERS_QUIT("listeners.quit"),
    ;
    
    private final LangMessage message;
    
    private Language(String key) {
        this.message = new LangMessage("shootgame." + key);
    }
    
    public LangMessage get() {
        return this.message;
    }
    
    public String get(CommandSender sender, Object... params) {
        if (sender instanceof Player) {
            return this.get().getUserMessage(UserUtils.getUser((Player) sender), params);
        }
        
        return Translations.getMessage(this.getKey()).getDefault();
    }
    
    public String getKey() {
        return this.get().getKey();
    }
}
