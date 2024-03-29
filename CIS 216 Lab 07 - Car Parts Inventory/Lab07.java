package lab.pkg07;

import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday November 5, 2021
//
//  Project Name:   Laboratory 07   
//
//  Program Description: This program will allow a user to keep track of a car
//  parts inventory. The user will have the choice to look-up a part (display 
//  its information), add a new part to the inventory, print the entire
//  inventory, or quit the program. The program will continue to process until
//  the user quits. There will be some input validation for "obvious" errors,
//  like inputting incorrect information for the menu. The car parts data will 
//  reside in a file and will include ID numbers, descriptions, costs, prices,
//  weights, locations, and quantities. Each box in the top-down design will 
//  utilize its own function.
// 
//  Project Number:  Project 07

public class Lab07 {

    public static void main(String[] args) throws IOException {
        
        int userChoice;
        
        //Manage Parts Inventory
        userChoice = 0;
        while(userChoice != 4) {
            
            userChoice = GetChoice( );
            PerformChoice(userChoice);
        
        }
        
    }
    
    public static int GetChoice( ) {
        
        Scanner kbd = new Scanner(System.in);
        int userChoice;
                       
        //Get Choice
        userChoice = 5;
        while(userChoice != 1 && userChoice != 2 && userChoice != 3 &&
            userChoice != 4) {
                
            System.out.print("What would you like to do: Look-up a part [1], "
                + "Add a new part to the inventory [2], Print the entire "
                    + "inventory [3], or Quit [4]?  "); //to user
            userChoice = kbd.nextInt( ); //from user
            System.out.println( );
                
        }    
        return userChoice;
          
    }
    
    public static void PerformChoice(int userChoice) throws IOException {
        
        //Perform Choice
        if(userChoice == 1)
            DisplayInformationOfASpecificPart( );
        if(userChoice == 2)
            AddPartToInventory( );
        if(userChoice == 3)
            PrintInventory( );
    
    }
    
    public static void DisplayInformationOfASpecificPart( ) throws IOException 
        {
        
        Parts processParts;
        processParts = new Parts( );
        int userPartIDNumber;
        
        //Display Information of a Specific Part 
        userPartIDNumber = GetPartIDNumber( );
        DisplayPartReportHeadingForMatchingPart( );
        ProcessEachPartForIDNumberFromFile(processParts, userPartIDNumber);
        
    }
    
    public static int GetPartIDNumber( ) {
               
        Scanner kbd = new Scanner(System.in);
        int userPartIDNumber;
        
        //Get Part ID Number
        System.out.print("Enter part ID number:  "); //to user
        userPartIDNumber = kbd.nextInt( ); //from user
        System.out.println( );
        
        return userPartIDNumber;
        
    }
    
    public static void DisplayPartReportHeadingForMatchingPart( ) {
        
        //Display Part Report Heading for Matching Part
        System.out.printf("%1s%16s%15s%14s%18s%31s\n",                 
            "Description of Part Found ", "Cost ", "Price ", "Weight ", 
                "Location ", "Quantity"); //to user   
        
    }
    
    public static void ProcessEachPartForIDNumberFromFile(Parts processParts, 
        int userPartIDNumber) throws IOException {
               
        File partsInventory;
        Scanner partsInventorySC;
        
        //Process Each Part for ID Number from File
        partsInventory = new File("partsInventory.txt");
        partsInventorySC = new Scanner(partsInventory);     
        while(partsInventorySC.hasNext( )) {
        
            GetPartInformationFromFile(partsInventorySC, processParts);
            DisplayPartSummaryForMatchingPart(userPartIDNumber, processParts);  
        
        }
        System.out.println( );
        partsInventorySC.close( );
        
    }
    
    public static void GetPartInformationFromFile(Scanner partsInventorySC, 
        Parts considerPartsInventoryInfo) throws IOException {
              
        int partIDNumber;
        String partDescription;
        double partCost;
        double partPrice;
        double partWeight;
        String partLocation;
        int partQuantity;
        
        //Get Part Information from File
        partIDNumber = partsInventorySC.nextInt( ); //from file
        partsInventorySC.nextLine( );
        partDescription = partsInventorySC.nextLine( ); //from file
        partCost = partsInventorySC.nextDouble( ); //from file
        partPrice = partsInventorySC.nextDouble( ); //from file
        partWeight = partsInventorySC.nextDouble( ); //from file
        partsInventorySC.nextLine( );
        partLocation = partsInventorySC.nextLine( ); //from file
        partQuantity = partsInventorySC.nextInt( ); //from file
        
        considerPartsInventoryInfo.SetPartIDNumber(partIDNumber);
        considerPartsInventoryInfo.SetPartDescription(partDescription);
        considerPartsInventoryInfo.SetPartCost(partCost);
        considerPartsInventoryInfo.SetPartPrice(partPrice);
        considerPartsInventoryInfo.SetPartWeight(partWeight);
        considerPartsInventoryInfo.SetPartLocation(partLocation);
        considerPartsInventoryInfo.SetPartQuantity(partQuantity);
        
    } 
    
    public static void DisplayPartSummaryForMatchingPart(int userPartIDNumber, 
        Parts displayDesiredPart) {
        
        //Display Part Summary for Matching Part
        if(displayDesiredPart.GetPartIDNumber( ) == userPartIDNumber)
            
            System.out.printf("%-37s%-14.2f%-13.2f%-16.2f%-32s%-1d\n", 
                displayDesiredPart.GetPartDescription( ), 
                    displayDesiredPart.GetPartCost( ), 
                        displayDesiredPart.GetPartPrice( ), 
                            displayDesiredPart.GetPartWeight( ), 
                                displayDesiredPart.GetPartLocation( ), 
                                    displayDesiredPart.GetPartQuantity( ));
                                        //to user
                
    }
    
    public static void AddPartToInventory( ) throws IOException {
        
        AppendParts logParts;
        logParts = new AppendParts( );
        
        //Add Part to Inventory
        GetPartInformation(logParts);
        AppendPartInformationToFile(logParts);
        
    }
    
    public static void GetPartInformation(AppendParts considerUserPartInfo) {
        
        Scanner kbd = new Scanner(System.in);
        int newPartIDNumber;
        String newPartDescription;
        double newPartCost;
        double newPartPrice;
        double newPartWeight;
        String newPartLocation;
        int newPartQuantity;
        
        //Get Part Information
        System.out.print("Enter Part ID Number:  "); //to user
        newPartIDNumber = kbd.nextInt( ); //from user
        kbd.nextLine( );
        System.out.print("Enter Part Description:  "); //to user
        newPartDescription = kbd.nextLine( ); //from user
        System.out.print("Enter Part Cost:  "); //to user
        newPartCost = kbd.nextDouble( ); //from user
        System.out.print("Enter Part Price:  "); //to user
        newPartPrice = kbd.nextDouble( ); //from user
        System.out.print("Enter Part Weight:  "); //to user
        newPartWeight = kbd.nextDouble( ); //from user
        kbd.nextLine( );
        System.out.print("Enter Part Location:  "); //to user
        newPartLocation = kbd.nextLine( ); //from user
        System.out.print("Enter Part Quantity:  "); //to user
        newPartQuantity = kbd.nextInt( ); //from user
        
        considerUserPartInfo.SetNewPartIDNumber(newPartIDNumber);
        considerUserPartInfo.SetNewPartDescription(newPartDescription);
        considerUserPartInfo.SetNewPartCost(newPartCost);
        considerUserPartInfo.SetNewPartPrice(newPartPrice);
        considerUserPartInfo.SetNewPartWeight(newPartWeight);
        considerUserPartInfo.SetNewPartLocation(newPartLocation);
        considerUserPartInfo.SetNewPartQuantity(newPartQuantity);
                
    }
    
    public static void AppendPartInformationToFile(AppendParts 
        storePart) throws IOException {
        
        FileWriter partsLogFW;
        PrintWriter partsLogPW;
        DecimalFormat formatParts;
        
        //Append Part Information to File        
        formatParts = new DecimalFormat("#0.00");
        partsLogFW = new FileWriter("partsInventory.txt", true);
        partsLogPW = new PrintWriter(partsLogFW);
        partsLogPW.println(storePart.GetNewPartIDNumber( )); //to file
        partsLogPW.println(storePart.GetNewPartDescription( )); //to file
        partsLogPW.println(formatParts.format(storePart.GetNewPartCost( ))); 
            //to file
        partsLogPW.println(formatParts.format(storePart.GetNewPartPrice( ))); 
            //to file
        partsLogPW.println(formatParts.format(storePart.GetNewPartWeight( ))); 
            //to file
        partsLogPW.println(storePart.GetNewPartLocation( )); //to file
        partsLogPW.println(storePart.GetNewPartQuantity( )); //to file
        partsLogPW.close( );
        System.out.println( );
        
    }
    
    public static void PrintInventory( ) throws IOException {
        
        //Print Inventory
        DisplayPartReportHeading( );
        ProcessEachPartFromFile( );
        
    }
    
    public static void DisplayPartReportHeading( ) {
        
        //Display Part Report Heading
        System.out.printf("%1s%20s%25s%15s%16s%18s%32s\n", "Part ID Number", 
            "Part Description", "Cost", "Price", "Weight", "Location", 
                "Quantity"); //to user
                     
    }
    
    public static void ProcessEachPartFromFile( ) throws IOException {
        
        Parts processParts;
        processParts = new Parts( );
        File partsInventory;
        Scanner partsInventorySC;
        
        //Process Each Part from File        
        partsInventory = new File("partsInventory.txt");
        partsInventorySC = new Scanner(partsInventory);
        while(partsInventorySC.hasNext( )) {
        
            GetPartInformationFromFile(partsInventorySC, processParts);
            DisplayPartSummary(processParts);  
    
        }
        System.out.println( );
        partsInventorySC.close( );
             
    }
    
    public static void DisplayPartSummary(Parts displayParts) {
        
        //Display Part Summary
        System.out.printf("%-18d%-37s%-14.2f%-15.2f%-16.2f%-32s%-1d\n", 
            displayParts.GetPartIDNumber( ), displayParts.GetPartDescription( ), 
                displayParts.GetPartCost( ), displayParts.GetPartPrice( ), 
                    displayParts.GetPartWeight( ), 
                        displayParts.GetPartLocation( ), 
                            displayParts.GetPartQuantity( )); //to user             
        
    }
    
}
