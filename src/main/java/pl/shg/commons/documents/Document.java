/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import pl.shg.commons.database.Connection;
import pl.shg.commons.database.DatabaseThread;
import pl.shg.commons.database.FutureTask;

/**
 *
 * @author Aleksander
 */
public class Document {
    public Document() {
        // TODO ?
    }
    
    public Connection getConnection() {
        return this.getAnnotation().connection();
    }
    
    public String getName() {
        return this.getAnnotation().name();
    }
    
    public boolean isStrong() {
        return this.getAnnotation().strong();
    }
    
    public void query(String query, Object[] values, FutureTask future) {
        if (values == null) {
            values = new Object[0];
        }
        
        DatabaseThread.getThread().addTask(this.getConnection(), query, values, future);
    }
    
    private DocumentInfo getAnnotation() {
        return this.getClass().getDeclaredAnnotation(DocumentInfo.class);
    }
}
