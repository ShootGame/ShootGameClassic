/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.server;

import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public interface IProxiedServer {
    void connect(Player player, TargetServer server);
}
