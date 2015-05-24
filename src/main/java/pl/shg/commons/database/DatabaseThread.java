/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksander
 */
public class DatabaseThread implements Runnable {
    private static DatabaseThread instance;
    private final List<Task> waitingTasks = new ArrayList<>();
    private final Thread thread;
    
    public DatabaseThread() {
        instance = this;
        this.thread = new Thread(this, "Database Thread");
        this.thread.start();
    }
    
    @Override
    public synchronized void run() {
        if (this.waitingTasks.isEmpty()) {
            return;
        }
        Task task = this.waitingTasks.get(0);
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); // check for the driver
            java.sql.Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shootgame", "root", "");
            
            PreparedStatement statement = connection.prepareStatement(task.getQuery());
            for (int i = 0; i < task.getValues().length; i++) {
                statement.setObject(i, task.getValues()[i]);
            }
            
            ResultSet result = null;
            if (task.getQuery().toUpperCase().startsWith("SELECT")) {
                result = statement.executeQuery();
            } else {
                statement.execute();
            }
            
            task.callFuture(result);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DatabaseThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.waitingTasks.remove(task);
    }
    
    public synchronized void addTask(Connection connection, String query, Object[] values, FutureTask future) {
        this.waitingTasks.add(new Task(connection, query, values, future));
        this.run();
    }
    
    public static DatabaseThread getThread() {
        if (instance == null) {
            instance = new DatabaseThread();
        }
        
        return instance;
    }
    
    private class Task {
        private final Connection connection;
        private final String query;
        private final Object[] values;
        private final FutureTask future;
        
        public Task(Connection connection, String query, Object[] values, FutureTask future) {
            this.connection = connection;
            this.query = query;
            this.values = values;
            this.future = future;
        }
        
        public Connection getConnection() {
            return this.connection;
        }
        
        public String getQuery() {
            return this.query;
        }
        
        public Object[] getValues() {
            return this.values;
        }
        
        public void callFuture(ResultSet result) {
            this.future.success(result);
        }
    }
}
