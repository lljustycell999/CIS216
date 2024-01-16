package lab.pkg09;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday November 19, 2021
//
//  Project Name:   Laboratory 09   
//
//  Program Description: This program will generate order summaries by allowing 
//  users to add or remove quantities of 7 different products. Firstly, the 
//  program will ask for the customer's name, address, city, state, and zip 
//  code. Next, the user will constantly be given the option to add or remove 
//  product quantities of a specific item until the user chooses to generate an 
//  order summary. When the user chooses to add or remove product quantities, 
//  the program will ask for a valid item number to look up the proper 
//  product description and price from two loaded arrays. Additionally, the 
//  program will request for a positive item quantity of the chosen item to 
//  either add or remove from the order. There will also be an array that keeps 
//  track of the total item quantities of each product. Finally, when the user 
//  stops adding and removing item quantities, an order summary will be 
//  displayed that contains the customer information, purchased products,
//  individual prices, quantities, item totals, subtotal, tax, and total due. 
//  Some of this information will also be logged to a file
// 
// 
//  Project Number:  Project 09

public class Lab09 {
  
    public static void main(String[] args) throws IOException {
        
        String [ ] productName;
        double [ ] productPrice;
        String customerName;
        String customerStreetAddress;
        String customerCityStateZip;
        int customerChoice;
        double [ ] productTotals;
        double subtotal;
        double tax;
        double total;
        int [ ] productQuantityTotal;
        int validItemNumber;
        File logFundraisingInfo;
        PrintWriter logFundraisingInfoPW;
        
        //Generate Order Summary
        productQuantityTotal = new int [7];
        logFundraisingInfo = new File("logFundraisingInfo.txt");
        logFundraisingInfoPW = new PrintWriter(logFundraisingInfo);
        
        productName = GetProductNamesFromFile( );
        productPrice = GetProductPricesFromFile( );
        customerName = GetCustomerName( );
        customerStreetAddress = GetCustomerStreetAddress( );
        customerCityStateZip = GetCustomerCityStateZip( );
        
        customerChoice = 0;
        validItemNumber = 7;
        while(customerChoice != 3) {
           
            customerChoice = GetEachCustomerChoice( );
            PerformEachChoice(customerChoice, productName, 
                productQuantityTotal);
            
        }
        productTotals = CalculateTotalsOfEachProduct(productPrice, 
            productQuantityTotal);
        subtotal = CalculateSubtotal(productTotals);
        tax = CalculateTax(subtotal);
        total = CalculateTotalCost(subtotal, tax);
        DisplayOrderSummary(customerName, customerStreetAddress, 
            customerCityStateZip, productQuantityTotal, productName, 
                productPrice, productTotals, validItemNumber, subtotal, tax, 
                    total);
        LogOrderToFile(logFundraisingInfoPW, customerName, 
            customerStreetAddress, customerCityStateZip, productQuantityTotal, 
                productName, total);
        logFundraisingInfoPW.close( );
        
    }
    
    public static String [ ] GetProductNamesFromFile( ) throws IOException {
        
        String [ ] productName;
        productName = new String [7];
       
        File productInfo;
        Scanner productInfoSC;
        
        int slotCounter;
        
        //Get Product Names from File
        productInfo = new File("fundraisingProductInfo.txt");
        productInfoSC = new Scanner(productInfo);
        
        slotCounter = 0;
        while(slotCounter < 7 && productInfoSC.hasNext( )) {
        
            productName [slotCounter] = productInfoSC.nextLine( ); //from file
            productInfoSC.nextLine( );
            slotCounter++;
        
        }
        productInfoSC.close( );
        
        return productName;  
          
    }
    
    public static double [ ] GetProductPricesFromFile( ) throws IOException {
        
        double [ ] productPrice;
        productPrice = new double [7];
        
        int anotherSlotCounter;
        
        File productInfo;
        Scanner productInfoSC;
        
        //Get Product Prices from File
        productInfo = new File("fundraisingProductInfo.txt");
        productInfoSC = new Scanner(productInfo);
        
        anotherSlotCounter = 0;
        while(anotherSlotCounter < 7 && productInfoSC.hasNext( )) {
            
            productInfoSC.nextLine( );
            productPrice [anotherSlotCounter] = productInfoSC.nextDouble( ); 
                //from file
            productInfoSC.nextLine( );
            anotherSlotCounter++;
        
        }
        productInfoSC.close( );
        
        return productPrice;  
        
    }
    
    public static String GetCustomerName( ) {
        
        String customerName;
        
        Scanner kbd = new Scanner(System.in);
        
        //Get Customer Name
        System.out.print("Enter Customer Name:  "); //to user
        customerName = kbd.nextLine( ); //from user
        System.out.println( );
        
        return customerName;
        
    }
    
    public static String GetCustomerStreetAddress( ) {
        
        String customerStreetAddress;
        
        Scanner kbd = new Scanner(System.in);
        
        //Get Customer Street Address
        System.out.print("Enter Customer's Street Address:  "); //to user
        customerStreetAddress = kbd.nextLine( ); //from user
        System.out.println( );
        
        return customerStreetAddress;
        
    }
    
    public static String GetCustomerCityStateZip( ) {
        
        String customerCityStateZip;
        
        Scanner kbd = new Scanner(System.in);
        
        //Get Customer City, State, Zip
        System.out.print("Enter Customer's City, State, and Zip Code:  "); 
            //to user
        customerCityStateZip = kbd.nextLine( ); //from user
        System.out.println( );
        
        return customerCityStateZip;
        
    }
    
    public static int GetEachCustomerChoice( ) {
        
        int customerChoice;
        
        Scanner kbd = new Scanner(System.in);
        
        //Get Each Customer Choice
        customerChoice = 4;
        while(customerChoice != 1 && customerChoice != 2 && customerChoice 
            != 3) {
            
            System.out.print("What would you like to do: Add Product Quantitie"
                + "s [1], Remove Product Quantities [2] or Display Order Summa"
                    + "ry [3]?  "); //to user
            customerChoice = kbd.nextInt( ); //from user
            System.out.println( );
        
        }
        return customerChoice; 
        
    }
    
    public static void PerformEachChoice(int customerChoice, String [ ] 
        productName, int [ ] productQuantityTotal) { 
            
        //Perform Each Choice
        if(customerChoice == 1)
            AddProductQuantities(productName, productQuantityTotal);
        if(customerChoice == 2)
            RemoveProductQuantities(productName, productQuantityTotal);
        
    }
    
    public static void AddProductQuantities(String [ ] productName, int [ ] 
        productQuantityTotal) {
        
        int validItemNumber;
        int itemQuantity;
        
        //Add Product Quantities
        validItemNumber = GetValidItemNumber( );
        itemQuantity = GetItemQuantity(productName, validItemNumber);
        AddToProductQuantityTotals(productQuantityTotal, validItemNumber, 
            itemQuantity);
        
    }
    
    public static int GetValidItemNumber( ) {
        
        int validItemNumber;
        
        Scanner kbd = new Scanner(System.in);
        
        //Get Valid Item Number
        validItemNumber = 7;
        while(validItemNumber > 6 || validItemNumber < 0) {
            
            System.out.print("Enter Valid Item Number (0-6):  "); //to user
            validItemNumber = kbd.nextInt( ); //from user
            System.out.println( );
        
        }
        return validItemNumber;
        
    }
    
    public static int GetItemQuantity(String productName [ ], 
        int validItemNumber) {
       
        int itemQuantity;
        
        Scanner kbd = new Scanner(System.in);
        
        //Get Item Quantity
        itemQuantity = -1;
        while(itemQuantity < 0) {
            
            System.out.print("Enter Item Quantity for " + 
                productName [validItemNumber] + ":  "); //to user
            itemQuantity = kbd.nextInt( ); //from user   
            System.out.println( );
        
        }
        return itemQuantity;
        
    }
    
    public static int [ ] AddToProductQuantityTotals(int [ ] 
        productQuantityTotal, int validItemNumber, int itemQuantity) {
        
        //Add to Product Quantity Totals
        productQuantityTotal [validItemNumber] = productQuantityTotal 
            [validItemNumber] + itemQuantity;
        
        return productQuantityTotal;
        
    }
    
    public static void RemoveProductQuantities(String [ ] productName, 
        int [ ] productQuantityTotal) {
        
        int validItemNumber;
        int itemQuantity;
        
        //Remove Product Quantities
        validItemNumber = GetValidItemNumber( );
        itemQuantity = GetItemQuantity(productName, validItemNumber);
        SubtractToProductQuantityTotals(productQuantityTotal, validItemNumber, 
            itemQuantity);
        
    }
    
    public static int [ ] SubtractToProductQuantityTotals(int [ ] 
        productQuantityTotal, int validItemNumber, int itemQuantity) {
        
        //Subtract to Product Quantity Totals
        productQuantityTotal [validItemNumber] = productQuantityTotal 
            [validItemNumber] - itemQuantity;
         
        return productQuantityTotal;
        
    }
    
    public static double [ ] CalculateTotalsOfEachProduct(double productPrice 
        [ ], int [ ] productQuantityTotal) {
        
        double [ ] productTotals;
        productTotals = new double [7];
        
        //Calculate Totals of Each Product
        productTotals [0] = productPrice [0] * productQuantityTotal [0];
        productTotals [1] = productPrice [1] * productQuantityTotal [1];
        productTotals [2] = productPrice [2] * productQuantityTotal [2];
        productTotals [3] = productPrice [3] * productQuantityTotal [3];
        productTotals [4] = productPrice [4] * productQuantityTotal [4];
        productTotals [5] = productPrice [5] * productQuantityTotal [5];
        productTotals [6] = productPrice [6] * productQuantityTotal [6];
       
        return productTotals;
        
    }
    
    public static double CalculateSubtotal(double productTotals [ ]) {
        
        double subtotal;
        
        //Calculate Subtotal
        subtotal = productTotals [0] + productTotals [1] + productTotals [2] + 
            productTotals [3] + productTotals [4] + productTotals [5] + 
                productTotals [6];
        
        return subtotal;
        
    }
    
    public static double CalculateTax(double subtotal) {
        
        double tax;
        
        //Calculate Tax
        tax = subtotal * 0.08;
        
        return tax;
        
    }
    
    public static double CalculateTotalCost(double subtotal, double tax) {
        
        double total;
        
        //Calculate Total Cost
        total = subtotal + tax;
        
        return total;
        
    }
    
    public static void DisplayOrderSummary(String customerName, 
        String customerStreetAddress, String customerCityStateZip, 
            int [ ] productQuantityTotal, String productName [ ], 
                double [ ] productPrice, double productTotals [ ], 
                    int validItemNumber, double subtotal, double tax, 
                        double total) {
        
        //Display Order Summary
        System.out.println("Customer Name: " + customerName); //to user
        System.out.println("Customer's Street Address: " + 
            customerStreetAddress); //to user
        System.out.println("Customer's City, State, and Zip Code: " + 
            customerCityStateZip); //to user
        System.out.println( );
        System.out.printf("%1s%40s%40s%40s\n", "Product Names: ", 
            "Product Prices: ", "Product Quantities: ", "Product Totals: "); 
                //to user     
        if(productQuantityTotal [0] > 0)         
            System.out.printf("%-39s%-36.2f%-44d%-1.2f\n", productName [0], 
                productPrice [0], productQuantityTotal [0], productTotals [0]); 
                    //to user 
        if(productQuantityTotal [1] > 0)   
            System.out.printf("%-39s%-36.2f%-44d%-1.2f\n", productName [1], 
                productPrice [1], productQuantityTotal [1], productTotals [1]); 
                    //to user
        if(productQuantityTotal [2] > 0)
            System.out.printf("%-39s%-36.2f%-44d%-1.2f\n", productName [2], 
                productPrice [2], productQuantityTotal [2], productTotals [2]); 
                    //to user
        if(productQuantityTotal [3] > 0)
            System.out.printf("%-39s%-36.2f%-44d%-1.2f\n", productName [3], 
                productPrice [3], productQuantityTotal [3], productTotals [3]); 
                    //to user
        if(productQuantityTotal [4] > 0)
            System.out.printf("%-39s%-36.2f%-44d%-1.2f\n", productName [4], 
                productPrice [4], productQuantityTotal [4], productTotals [4]); 
                    //to user
        if(productQuantityTotal [5] > 0)
            System.out.printf("%-39s%-36.2f%-44d%-1.2f\n", productName [5], 
                productPrice [5], productQuantityTotal [5], productTotals [5]); 
                    //to user
        if(productQuantityTotal [6] > 0)
            System.out.printf("%-39s%-36.2f%-44d%-1.2f\n", productName [6], 
                productPrice [6], productQuantityTotal [6], productTotals [6]); 
                    //to user 
        System.out.println( );
        System.out.printf("%-39s%1.2f\n", "Subtotal: ", subtotal); //to user
        System.out.printf("%-39s%1.2f\n", "Total Tax (8.0%): ", tax); //to user
        System.out.printf("%-39s%1.2f\n", "Total Cost: ", total); //to user
        
    }
    
    public static void LogOrderToFile(PrintWriter logFundraisingInfoPW, 
        String customerName, String customerStreetAddress, 
            String customerCityStateZip, int [ ] productQuantityTotal, 
                String [ ] productName, double total) {
        
        //Log Order to File
        logFundraisingInfoPW.println(customerName); //to file
        logFundraisingInfoPW.println(customerStreetAddress); //to file
        logFundraisingInfoPW.println(customerCityStateZip); //to file   
        if(productQuantityTotal [0] > 0) {
            
            logFundraisingInfoPW.println("0"); //to file
            logFundraisingInfoPW.println(productQuantityTotal [0]); 
                //to file
        
        }     
        if(productQuantityTotal [1] > 0) {
            
            logFundraisingInfoPW.println("1"); //to file
            logFundraisingInfoPW.println(productQuantityTotal [1]); 
                //to file
        
        }      
        if(productQuantityTotal [2] > 0) {
            
            logFundraisingInfoPW.println("2"); //to file
            logFundraisingInfoPW.println(productQuantityTotal [2]); 
                //to file
        
        }   
        if(productQuantityTotal [3] > 0) {
            
            logFundraisingInfoPW.println("3"); //to file
            logFundraisingInfoPW.println(productQuantityTotal [3]); 
                //to file
        
        }       
        if(productQuantityTotal [4] > 0) {
            
            logFundraisingInfoPW.println("4"); //to file
            logFundraisingInfoPW.println(productQuantityTotal [4]); 
                //to file
        
        }      
        if(productQuantityTotal [5] > 0) {
            
            logFundraisingInfoPW.println("5"); //to file
            logFundraisingInfoPW.println(productQuantityTotal [5]); 
                //to file
        
        }  
        if(productQuantityTotal [6] > 0) {
            
            logFundraisingInfoPW.println("6"); //to file
            logFundraisingInfoPW.println(productQuantityTotal [6]); 
                //to file
        
        }     
        logFundraisingInfoPW.printf("%1.2f\n", total); //to file
        
    }
 
}
