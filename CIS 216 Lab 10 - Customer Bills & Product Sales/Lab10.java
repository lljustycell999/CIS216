package lab.pkg10;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Tuesday November 23, 2021
//
//  Project Name:   Laboratory 10   
//
//  Program Description: This program will generate customer bills or product 
//  sales summaries constantly unless the user chooses to quit. 
//  When generating a bill, the user only needs to give a customer name to 
//  produce all sales for that customer, including extended costs and a grand 
//  total due. When generating the sales summary, the user needs to input a 
//  product name to create a list of customers who have bought the specific 
//  item, how many they bought, their extended costs, and the grand total sold.  
// 
//  Project Number:  Project 10

public class Lab10 {

    public static void main(String[] args) throws IOException {
        
        int numSales;
        int [ ] quantity;
        String [ ] productName;
        double [ ] price;
        String [ ] customerName;
        
        quantity = new int [8000];
        productName = new String [8000];
        price = new double [8000];
        customerName = new String [8000];
        
        //Manage Sales Reporting
        numSales = LoadSales(quantity, productName, price, customerName);
        PerformEachTask(numSales, quantity, productName, price, customerName);
        
    }
    
    public static int LoadSales(int [ ] quantity, 
        String [ ] productName, double [ ] price, String [ ] customerName) 
            throws IOException {
        
        int numSales;
        File salesFile;
        Scanner salesFileSC;
        
        //Load Sales
        numSales = 0;
        salesFile = new File("transfile.txt");
        salesFileSC = new Scanner(salesFile);
        while(salesFileSC.hasNext( )) {
            
            customerName [numSales] = salesFileSC.nextLine( ); //from file
            quantity [numSales] = salesFileSC.nextInt( ); //from file
            salesFileSC.nextLine( );
            productName [numSales] = salesFileSC.nextLine( ); //from file
            price [numSales] = salesFileSC.nextDouble( ); //from file
            salesFileSC.nextLine( );
            numSales++;
            
        }
        salesFileSC.close( );
        
        return numSales;
      
    }
    
    public static void PerformEachTask(int numSales, int [ ] quantity, 
        String [ ] productName, double [ ] price, String [ ] customerName) {
        
        char choice;
        
        //Perform Each Task
        choice = GetChoice( );    
        while(choice != 'Q') {
            
            PerformChoice(choice, numSales, quantity, productName, price, 
                customerName);
            choice = GetChoice( );
            
        }
        
    }
    
    public static char GetChoice( ) {
        
        char choice;
        Scanner kbd = new Scanner(System.in);
        
        //Get Choice
        choice = 'Z';
        while(choice != 'B' && choice != 'S' && choice != 'Q') {
            
        System.out.print("Do you wish to generate a customer (B)ill, generate "
            + "a (S)ales summary, or (Q)uit"); //to user
        choice = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
        
        }
        return choice;
    
    }
    
    public static void PerformChoice(char choice, int numSales, 
        int [ ] quantity, String [ ] productName, double [ ] price, 
            String [ ] customerName) {
        
        //Perform Choice
        if(choice == 'B')
            GenerateBill(numSales, quantity, productName, price, customerName);
        if(choice == 'S')
            GenerateSalesSummary(numSales, quantity, productName, price, 
                customerName);
        
    }
    
    public static void GenerateBill(int numSales, int [ ] quantity, 
        String [ ] productName, double [ ] price, String [ ] customerName) {
        
        String billedCustomerName;
        double total;
        
        //Generate Bill
        billedCustomerName = GetCustomerName( );
        DisplaySalesReportHeading(billedCustomerName);
        total = ProcessEachSale(numSales, quantity, productName, price, 
            customerName, billedCustomerName);
        DisplaySalesTotal(total);
        
    }
    
    public static String GetCustomerName( ) {
        
        String billedCustomerName;
        Scanner kbd = new Scanner(System.in);
        
        //Get Customer Name
        System.out.print("Enter the name of the customer whose bill you would "
            + "like to generate:  "); //to user
        billedCustomerName = kbd.nextLine( ); //from user
        System.out.println( );
        
        return billedCustomerName;
        
    }
    
    public static void DisplaySalesReportHeading(String billedCustomerName) {
        
        //Display Sales Report Heading
        System.out.printf("%40s\n", "Miracle Manufacturing"); //to user
        System.out.printf("%36s\n", "Customer Bill"); //to user
        System.out.println("SOLD TO:"); //to user
        System.out.println(billedCustomerName); //to user
        System.out.println( );
        System.out.printf("%8s%15s%50s%15s\n", "Quantity", "Product Name", 
            "Price", "Extended"); //to user
            
    }
    
    public static double ProcessEachSale(int numSales, int [ ] quantity, 
        String [ ] productName, double [ ] price, String [ ] customerName, 
            String billedCustomerName) {
        
        double total;
        double extendedCost;
        
        //Process Each Sale
        total = 0;
        for(int cnt = 0; cnt < numSales; cnt++) {
            
            extendedCost = CalculateExtendedCost(quantity, cnt, price);
            total = UpdateSalesTotal(customerName, cnt, billedCustomerName,
                total, extendedCost);
            DisplayMatchingSalesLine(customerName, cnt, billedCustomerName,
                quantity, productName, price, extendedCost);
                  
        } 
        return total;
        
    }
    
    public static double CalculateExtendedCost(int [ ] quantity, int cnt, 
        double [ ] price) {
        
        double extendedCost;
        
        //Calculate Extended Cost
        extendedCost = quantity [cnt] * price [cnt];
        
        return extendedCost;
        
    }
    
    public static double UpdateSalesTotal(String [ ] customerName, int cnt,
        String billedCustomerName, double total, double extendedCost) {
        
        //Update Sales Total
        if(customerName [cnt].compareToIgnoreCase(billedCustomerName) == 0)   
            total = total + extendedCost;
            
        return total;
        
    }
    
    public static void DisplayMatchingSalesLine(String [ ] customerName, 
        int cnt, String billedCustomerName, int [ ] quantity, 
            String productName [ ], double [ ] price, double extendedCost) {
        
        //Display Matching Sales Line
        if(customerName [cnt].compareToIgnoreCase(billedCustomerName) == 0)
            System.out.printf("%-11d%-57s%-12.2f%-14.2f\n", quantity [cnt], 
                productName [cnt], price [cnt], extendedCost); //to user
        
    }
    
    public static void DisplaySalesTotal(double total) {
        
        //Display Sales Total
        System.out.printf("%-80s%6.2f\n", "Total Due:", total); //to user
        System.out.println( );
        
    }
    
    public static void GenerateSalesSummary(int numSales, 
        int [ ] quantity, String [ ] productName, double [ ] price, 
            String [ ] customerName) {
        
        String summaryProductName;
        double total;
        
        //Generate Sales Summary
        summaryProductName = GetProductName( );
        DisplaySummaryReportHeading(summaryProductName);
        total = ProcessEachSaleForReport(numSales, quantity, productName, 
            price, customerName, summaryProductName);
        DisplayTotalSold(total);
        
    }
    
    public static String GetProductName( ) {
        
        String summaryProductName;
        Scanner kbd = new Scanner(System.in);
        
        //Get Product Name
        System.out.print("Enter the name of the product whose sales summary "
            + "you would like to generate:  "); //to user
        summaryProductName = kbd.nextLine( ); //from user
        System.out.println( );
        
        return summaryProductName;
        
    }
    
    public static void DisplaySummaryReportHeading(String summaryProductName) {
        
        //Display Summary Report Heading
        System.out.printf("%40s\n", "Miracle Manufacturing"); //to user
        System.out.printf("%36s\n", "Sales Summary"); //to user
        System.out.println("PRODUCT:"); //to user
        System.out.println(summaryProductName); //to user
        System.out.println( );
        System.out.printf("%8s%15s%50s%15s\n", "Quantity", "Customer Name", 
            "Price", "Extended"); //to user
        
    }
    
    public static double ProcessEachSaleForReport(int numSales, 
        int [ ] quantity, String [ ] productName, double [ ] price, 
            String [ ] customerName, String summaryProductName) {
        
        double total;
        double extendedCost;
        
        //Process Each Sale for Report
        total = 0;
        for(int cnt = 0; cnt < numSales; cnt++) {
            
            extendedCost = CalculateExtendedCost(quantity, cnt, price);
            total = UpdateSummaryTotal(productName, cnt, summaryProductName,
                total, extendedCost);
            DisplayMatchingProductSaleLine(productName, cnt, summaryProductName,
                quantity, customerName, price, extendedCost);
                  
        } 
        return total;
        
    }
    
    public static double UpdateSummaryTotal(String [ ] productName, 
        int cnt, String summaryProductName, double total, double extendedCost) {
        
        //Update Summary Total
        if(productName [cnt].compareToIgnoreCase(summaryProductName) == 0)   
            total = total + extendedCost;
            
        return total;
        
    }
    
    public static void DisplayMatchingProductSaleLine(String [ ] productName, 
        int cnt, String summaryProductName, int [ ] quantity, 
            String [ ] customerName, double [ ] price, double extendedCost) {
        
        //Display Matching Product Sale Line
        if(productName [cnt].compareToIgnoreCase(summaryProductName) == 0)
            System.out.printf("%-10d%-58s%-12.2f%-14.2f\n", quantity [cnt], 
                customerName [cnt], price [cnt], extendedCost); //to user
        
    }
    
    public static void DisplayTotalSold(double total) {
        
        //Display Total Sold
        System.out.printf("%-80s%6.2f\n", "Total Sold:", total); //to user
        System.out.println( );
        
    }
    
}
