/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decibel_java_graduate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Database {
 
    private static Database instance;
    private static Connection conn;
    
    private String url = "jdbc:sqlite:decTest.db";
    
    private Database(){
    
        try {
            conn  = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static Database getInstance(){
    
        if(conn == null){
        
            instance = new Database();
            return instance;
        }
        return instance;
    }
    public Connection getConnection(){
    
        return conn;
    }
}
