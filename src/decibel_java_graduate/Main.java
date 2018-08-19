/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decibel_java_graduate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Daniel O'Steen
 * 
 * 
 */
public class Main {
    
    private static DAO dao = new DAO();
  
    private static long start_time;
    private static long end_time;
    private static long load_start_time;
    private static long load_end_time;
    private static long sum;
    private static long avg;
    
    
    public static void main(String[] args) throws FileNotFoundException{
    
        CreateTable.createTable();//creates a table
        
        load_start_time = System.nanoTime();
        String csvFile = "src/FL_insurance_sample.csv";
        File file = new File(csvFile);
        Scanner scan = new Scanner(file);
       
        int control =0;
        scan.nextLine();//skips header line
     
        
        while (scan.hasNext()) {
            
            String whole_line = scan.nextLine();
            String[] list = whole_line.split(",");

            start_time = System.nanoTime();
            dao.add_v2(list);
            end_time = System.nanoTime();
            sum += (end_time - start_time);
            
            System.out.println("Time per row: " + (end_time - start_time) + " nanoseconds");
            control++;
        }
        avg = sum/control;
        scan.close();
        load_end_time = System.nanoTime();
        System.out.println("The total load time, plus time taken for terminal output is: " + (load_end_time - load_start_time)/1000000 + "m/s"
                + "\n The average time per line is: " + avg + " nano seconds");
        
        //System.out.println(control);
        //dao.testGet();
        //dao.testCorrectValues();
 
    }
    
    
}
