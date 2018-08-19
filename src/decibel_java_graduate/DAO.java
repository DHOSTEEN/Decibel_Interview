/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decibel_java_graduate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class DAO {
    
    private Connection conn;
    private Database db = Database.getInstance();
    private PreparedStatement prep_statement;
   // private String url = "jdbc:sqlite:E:/SoftEngPro/TESTERS/db/decTest.db";

    String sqlInsert =  "INSERT INTO MasterTable(policyID, statecode, county,eq_site_limit,"
            + " hu_site_limit, fl_site_limit, fr_site_limit, tiv_2011, tiv_2012,"
            + " eq_site_deductible, hu_site_deductible, fl_site_deductible,"
            + " fr_site_deductible, point_latitude, point_longitude,"
            + " line,construction, point_granularity)"
            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public DAO(){
    
        Database db = Database.getInstance();
       // conn = db.getConnection();
       
    }
    /**Inserts into data base as String*/
    public void add(String[] line){// this version was first pass attaempt at filling table - LOSES type
    
        try{
            
            conn = db.getConnection();
            conn.setAutoCommit(false);// VASTLY sped up transactions, before approx 53ms per insert, now approx 0.086866ms for upper 
            PreparedStatement prep_statement = conn.prepareStatement(sqlInsert);
            int sql_index = 1;
            for(int i = 0; i<line.length; i++){
                
                prep_statement.setString(sql_index, line[i]);
                sql_index++;
            }
            prep_statement.executeUpdate();
        }catch(SQLException e){
        if(conn != null){
                try {
                    conn.rollback();//prevents issues with bulk insert failures
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**Inserts all row into database with matching types - decreased average time by 10k nanoseconds (home machine)*/
    public void add_v2(String[] line){     
         try{

            conn = db.getConnection();
            conn.setAutoCommit(false); // VASTLY sped up transactions, before approx 53ms per insert, now approx 0.086866ms for upper 

            prep_statement = conn.prepareStatement(sqlInsert);            
            prep_statement.setInt(1, Integer.parseInt(line[0]));
            prep_statement.setString(2, line[1]);
            prep_statement.setString(3, line[2]);
            prep_statement.setFloat(4, Float.parseFloat(line[3]));
            prep_statement.setFloat(5, Float.parseFloat(line[4]));
            prep_statement.setFloat(6, Float.parseFloat(line[5]));
            prep_statement.setFloat(7, Float.parseFloat(line[6]));
            prep_statement.setFloat(8, Float.parseFloat(line[7]));
            prep_statement.setFloat(9, Float.parseFloat(line[8]));
            prep_statement.setFloat(10, Float.parseFloat(line[9]));
            prep_statement.setFloat(11, Float.parseFloat(line[10]));
            prep_statement.setFloat(12, Float.parseFloat(line[11]));
            prep_statement.setFloat(13, Float.parseFloat(line[12]));
            prep_statement.setFloat(14, Float.parseFloat(line[13]));
            prep_statement.setFloat(15, Float.parseFloat(line[14]));
            prep_statement.setString(16, line[15]);
            prep_statement.setString(17, line[16]);
            prep_statement.setInt(18, Integer.parseInt(line[17]));

            prep_statement.executeUpdate();
            
        }catch(SQLException e){
            if(conn != null){
                try {
                    conn.rollback();//prevents issues with bulk insert failures
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        finally{
         
             try {
                if(prep_statement != null){
                   prep_statement.close();
                }
            }
            catch (SQLException ex) {
                 Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    /**Simple SQL query to verify number of lines contained in table*/
    public void testGet(){
    
       int count = 0;
        try (
            Statement stmt  = conn.createStatement();

            ResultSet rs    = stmt.executeQuery("SELECT * FROM MasterTable")){

           // loop through the result set
           while (rs.next()) {

               count++;
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
        System.out.println("Number of entries in table is: " + count);//number of entries in table
    }

    /**Test method used to verify if error throw when all of table is set to TEXT
     - does not*/
    public void testCorrectValues(){

        boolean x = true;
        try {
            Statement prep_statement = conn.createStatement();
            String test = "SELECT hu_site_limit, fl_site_limit, hu_site_limit+fl_site_limit AS "
                    + "\"WIBBLES\""
                    + "FROM MasterTable "
                    + "WHERE policyID = 119736";
            String test2 = "SELECT * FROM MasterTable WHERE policyID = 119736";
            ResultSet result_set = prep_statement.executeQuery(test);
            while(result_set.next() && x){
            
                System.out.println("hu_site_limit: " + result_set.getString("hu_site_limit"));
                System.out.println("fl_site_limit: " + result_set.getString("fl_site_limit"));
                System.out.println("is: " + result_set.getFloat("WIBBLES"));
                x = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
}
