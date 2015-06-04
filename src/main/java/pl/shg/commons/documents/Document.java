/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import java.lang.annotation.Annotation;
import pl.shg.commons.database.Connection;
import pl.shg.commons.database.DatabaseThread;
import pl.shg.commons.database.FutureTask;
import pl.shg.commons.helpers.Helper;

/**
 *
 * @author Aleksander
 */
public class Document {
    private Helper helper;
    
    public Document() {
        try {
            Class<? extends Helper> helperClass = this.getAnnotation().helper();
            this.helper = helperClass.getConstructor(this.getClass()).newInstance(this);
        } catch (Throwable ex) {
            this.helper = new Helper(this);
        }
    }
    
    public Connection getConnection() {
        return this.getAnnotation().connection();
    }
    
    public Helper getHelper() {
        return this.helper;
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
        Annotation annotation = this.getClass().getDeclaredAnnotation(DocumentInfo.class);
        if (annotation != null) {
            return (DocumentInfo) annotation;
        } else {
            throw new UnsupportedOperationException(this.getClass().getSimpleName() + " must be @DocumentInfo annotated.");
        }
    }
}
