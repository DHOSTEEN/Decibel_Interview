/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decibel_java_graduate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Daniel
 */
public class CreateTable {
    
    public static void main(String[] args){
       // createTable();
      // createTestTable();
    }
    
    static String url = "jdbc:sqlite:decTest.db";

    public static void createTable(){
    
        String sql = "CREATE TABLE IF NOT EXISTS MasterTable (\n"
                + "policyID INTEGER NOT NULL PRIMARY KEY, \n"
                + "statecode TEXT NOT NULL, \n"
                + "county TEXT NOT NULL, \n"
                + "eq_site_limit REAL NOT NULL, \n"
                + "hu_site_limit REAL NOT NULL, \n"
                + "fl_site_limit REAL NOT NULL, \n"
                + "fr_site_limit REAL NOT NULL, \n"
                + "tiv_2011 REAL NOT NULL, \n"
                + "tiv_2012 REAL NOT NULL, \n"
                + "eq_site_deductible REAL NOT NULL, \n"
                + "hu_site_deductible REAL NOT NULL, \n"
                + "fl_site_deductible REAL NOT NULL, \n"
                + "fr_site_deductible REAL NOT NULL, \n"
                + "point_latitude REAL NOT NULL, \n"
                + "point_longitude REAL NOT NULL, \n"
                + "line TEXT NOT NULL, \n"
                + "construction TEXT NOT NULL, \n"
                + "point_granularity INTEGER);";
               // + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*public static void createTestTable(){
        String sql = "CREATE TABLE MasterTable (\n"
                + "policyID INTEGER NOT NULL PRIMARY KEY, \n"
                + "statecode TEXT NOT NULL, \n"
                + "county TEXT NOT NULL, \n"
                + "eq_site_limit TEXT NOT NULL, \n"
                + "hu_site_limit TEXT NOT NULL, \n"
                + "fl_site_limit TEXT NOT NULL, \n"
                + "fr_site_limit TEXT NOT NULL, \n"
                + "tiv_2011 TEXT NOT NULL, \n"
                + "tiv_2012 TEXT NOT NULL, \n"
                + "eq_site_deductible TEXT NOT NULL, \n"
                + "hu_site_deductible TEXT NOT NULL, \n"
                + "fl_site_deductible TEXT NOT NULL, \n"
                + "fr_site_deductible TEXT NOT NULL, \n"
                + "point_latitude TEXT NOT NULL, \n"
                + "point_longitude TEXT NOT NULL, \n"
                + "line TEXT NOT NULL, \n"
                + "construction TEXT NOT NULL, \n"
                + "point_granularity INTEGER);";
               // + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/
    
}
