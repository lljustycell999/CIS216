package lab.pkg04;

import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday October 8, 2021
//
//  Project Name:   Laboratory 04   
//
//  Program Description: This program will keep track of sales for a group of 
//  salespersons. The user may generate a "commission report", add a sale to the
//  "sales log", or produce a commission statement. A file will store "sales 
//  log" information, including the salesperson's name, sale date, customer
//  name, quantity of items sold, name of items sold, and price per item. The
//  "generate report" option will ask for the salesperson's name, sale month,
//  and sale year. A report will then be generated that includes all applicable
//  sales log data and extended prices. The total sales will also be displayed.
//  Secondly, The "add sale" option will ask the user for sale data to append
//  to the file. Lastly, the "produce commission statement" option will also
//  ask for the salesperson's name, sale month, and sale year. All applicable
//  extended prices will then be added. A commission rate will be determined
//  and the total commission will be calculated. Finally, the total sales,
//  commission rate, and total commission will be displayed.   
//     
//  Project Number:  Project 04

public class Lab04 {

    public static void main(String[] args) throws IOException {
        
        int userChoice;
        
        String sName;     
        int sMonth;
        int sYear;
        
        File salesFile;
        Scanner salesFileSC;
        
        String fileSName;
        int fileSMonth;
        int fileSDay;
        int fileSYear;    
        String customerName;
        int itemSoldQuantity;
        String itemSoldName;
        double itemPrice;
        
        double extendedCost;
        
        double totalSales;
        
        DecimalFormat formattedPrice;
        
        String newSName;
        int newSMonth;
        int newSDay;
        int newSYear;
        String newCustomerName;
        int newItemSoldQuantity;
        String newItemSoldName;
        double newItemPrice;
        
        FileWriter salesFileFW;
        PrintWriter salesFilePW;
          
        double commissionRate;
        
        double commission;
        
        Scanner kbd;
        
        //Establish Keyboard Availability
        kbd = new Scanner(System.in);
        
        //Get User Choice
        System.out.print("What would you like to do: Generate commission "
            + "report [1], add a sale to sales log [2], or generate "
                + "commission statement [3]?  "); //to user
        userChoice = kbd.nextInt( ); //from user
        kbd.nextLine( );
        
        if(userChoice == 1) {
            
            //Get Report Parameters
            System.out.print("Enter name of the salesperson:  "); //to user
            sName = kbd.nextLine( ); //from user
            System.out.print("Enter month:  "); //to user
            sMonth = kbd.nextInt( ); //from user
            System.out.print("Enter year:  "); //to user
            sYear = kbd.nextInt( ); //from user
            System.out.println( );
            
            //Display Report Headings
            System.out.printf("%27s%2s%25s%2s%50s%20s%20s\n", 
                "Name of salesperson:  ", "Date of sale:  ", 
                    "Customer name:  ", "Quantity of item sold:  ", 
                        "Name of item sold:  ", "Price per item:  ", 
                            "Extended price of sale:  "); //to user         
            salesFile = new File("saleslog.txt");
            salesFileSC = new Scanner(salesFile);
            totalSales = 0.0;
            while(salesFileSC.hasNext( )) {
                
                //Get Sale Information from File
                fileSName = salesFileSC.nextLine( ); //from file
                fileSMonth = salesFileSC.nextInt( ); //from file
                fileSDay = salesFileSC.nextInt( ); //from file
                fileSYear = salesFileSC.nextInt( ); //from file
                salesFileSC.nextLine( );
                customerName = salesFileSC.nextLine( ); //from file
                itemSoldQuantity = salesFileSC.nextInt( ); //from file
                salesFileSC.nextLine( );
                itemSoldName = salesFileSC.nextLine( ); //from file
                itemPrice = salesFileSC.nextDouble( ); //from file
                salesFileSC.nextLine( );
                    
                //Calculate Extended Cost
                extendedCost = itemPrice * itemSoldQuantity;

                //Update Total Sales
                if(fileSName.compareToIgnoreCase(sName) == 0 && fileSMonth 
                    == sMonth && fileSYear == sYear)
                    totalSales = totalSales + extendedCost;

                //Display Sale Summary Line
                if(fileSName.compareToIgnoreCase(sName) == 0 && fileSMonth 
                    == sMonth && fileSYear == sYear) {
                    System.out.printf("%25s%7d/%2d/%2d%25s%24d%50s%20.2f%"
                        + "25.2f\n", fileSName, sMonth, fileSDay, sYear, 
                            customerName, itemSoldQuantity, itemSoldName, 
                                itemPrice, extendedCost); //to user  
                 
                   }
                        
            }
            salesFileSC.close( );
            
            //Display Total Sales
            System.out.println( );
            System.out.printf("%2s%2.2f\n", "Total sales:  ", 
                totalSales); //to user
            
        }
        
        else
            if(userChoice == 2) {
            
                //Get New Sale Information
                formattedPrice = new DecimalFormat("#0.00");
                System.out.print("Enter name of salesperson:  "); //to user
                newSName = kbd.nextLine( ); //from user
                System.out.print("Enter month of sale:  "); //to user
                newSMonth = kbd.nextInt( ); //from user
                System.out.print("Enter day of sale:  "); //to user
                newSDay = kbd.nextInt( ); //from user
                System.out.print("Enter year of sale:  "); //to user
                newSYear = kbd.nextInt( ); //from user
                kbd.nextLine( );
                System.out.print("Enter customer name:  "); //to user
                newCustomerName = kbd.nextLine( ); //from user
                System.out.print("Enter quantity of item sold:  "); //to user
                newItemSoldQuantity = kbd.nextInt( ); //from user
                kbd.nextLine( );
                System.out.print("Enter name of item sold:  "); //to user
                newItemSoldName = kbd.nextLine( ); //from user
                System.out.print("Enter price per item:  "); //to user
                newItemPrice = kbd.nextDouble( ); //from user
            
                //Append New Sale Information to Sales Log
                salesFileFW = new FileWriter("saleslog.txt", true);
                salesFilePW = new PrintWriter(salesFileFW);
                salesFilePW.println(newSName); //to file
                salesFilePW.println(newSMonth); //to file
                salesFilePW.println(newSDay); //to file
                salesFilePW.println(newSYear); //to file
                salesFilePW.println(newCustomerName); //to file
                salesFilePW.println(newItemSoldQuantity); //to file
                salesFilePW.println(newItemSoldName); //to file
                salesFilePW.println(formattedPrice.format(newItemPrice)); //to file
                salesFilePW.close( );
                
                System.exit(0);
                
            } 
            
            else
                if(userChoice == 3) {
            
                    //Get Report Parameters
                    System.out.print("Enter name of the salesperson:  "
                        + ""); //to user
                    sName = kbd.nextLine( ); //from user
                    System.out.print("Enter month:  "); //to user
                    sMonth = kbd.nextInt( ); //from user
                    System.out.print("Enter year:  "); //to user
                    sYear = kbd.nextInt( ); //from user
                    System.out.println( );                 
                    salesFile = new File("saleslog.txt");
                    salesFileSC = new Scanner(salesFile);
                    totalSales = 0.0;
                    while(salesFileSC.hasNext( )) {
                        
                        //Get Sale Information from File      
                        fileSName = salesFileSC.nextLine( ); //from file
                        fileSMonth = salesFileSC.nextInt( ); //from file
                        fileSDay = salesFileSC.nextInt( ); //from file
                        fileSYear = salesFileSC.nextInt( ); //from file
                        salesFileSC.nextLine( );
                        customerName = salesFileSC.nextLine( ); //from file
                        itemSoldQuantity = salesFileSC.nextInt( ); //from file
                        salesFileSC.nextLine( );
                        itemSoldName = salesFileSC.nextLine( ); //from file
                        itemPrice = salesFileSC.nextDouble( ); //from file
                        salesFileSC.nextLine( );
                
                        //Calculate Extended Cost
                        extendedCost = itemPrice * itemSoldQuantity;
                
                        //Update Total Sales
                        if(fileSName.compareToIgnoreCase(sName) == 0 && 
                            fileSMonth == sMonth && fileSYear == sYear)
                            totalSales = totalSales + extendedCost;
                        }
                    
                    salesFileSC.close( );
                    
                    //Determine Commission Rate
                    commissionRate = 0.052;
                    if(totalSales >= 10000 && totalSales <= 50000)
                        commissionRate = 0.065;
                    else
                        if(totalSales > 50000)
                            commissionRate = 0.079;
                
                    //Calculate Commission
                    commission = totalSales * commissionRate;
                
                    //Display Commission Statement
                    System.out.printf("%2s%2.2f\n", "Total of sales:  ", 
                        totalSales); //to user
                    System.out.printf("%2s%2.3f\n", "Commission rate:  ", 
                        commissionRate); //to user
                    System.out.printf("%2s%2.2f\n", "Commission amount:  ", 
                        commission); //to user
                    
                }
        
        }   //END main
    
    }   //END Sales class
