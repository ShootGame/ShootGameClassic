/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api;

import pl.shg.shootgame.api.util.Validate;

/**
 *
 * @author Aleksander
 */
public class ShootGame {
    private static ShootGame shoot;
    
    public static ShootGame getShoot() {
        return shoot;
    }
    
    public static void setShoot(ShootGame shoot) {
        Validate.notNull(shoot, "shoot can not be null");
        ShootGame.shoot = shoot;
    }
}
