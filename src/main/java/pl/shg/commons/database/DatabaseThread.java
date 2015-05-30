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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksander
 */
public class DatabaseThread extends Thread {
    private static DatabaseThread instance;
    private boolean running = false;
    private final List<Task> waitingTasks = new ArrayList<>();
    
    public DatabaseThread() {
        super("Database Connection Thread");
        this.start();
    }
    
    @Override
    public void run() {
        while (this.isRunning()) {
            try {
                this.tick();
                Thread.sleep(1L);
            } catch (Throwable ex) {
                Logger.getLogger(DatabaseThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addTask(Connection connection, String query, Object[] values, FutureTask future) {
        this.waitingTasks.add(new Task(connection, query, values, future));
    }
    
    private void perform(Task task) throws Throwable {
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
        statement.close();
        
        this.waitingTasks.remove(task);
    }
    
    private void tick() throws Throwable {
        synchronized (this.waitingTasks) {
            if (!this.waitingTasks.isEmpty()) {
                this.perform(this.waitingTasks.get(0));
            }
        }
    }
    
    public static DatabaseThread getThread() {
        return instance;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void setRunning(boolean running) {
        this.running = running;
    }
    
    public static void setThread(DatabaseThread thread) {
        DatabaseThread.instance = thread;
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
