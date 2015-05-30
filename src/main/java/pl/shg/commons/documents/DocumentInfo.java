/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import pl.shg.commons.database.Connection;
import pl.shg.commons.helpers.Helper;

/**
 *
 * @author Aleksander
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentInfo {
    String name();
    
    boolean strong() default true;
    
    Connection connection();
    
    Class<? extends Document>[] documents() default {};
    
    Class<? extends Helper> helper();
}
