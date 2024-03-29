package lab.pkg08;

import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday November 5, 2021
//
//  Project Name:   Laboratory 08   
//
//  Program Description: This program will allow a user to keep track of a car
//  parts inventory. The user will have the choice to look-up a part (display 
//  its information), add a new part to the inventory, print the entire
//  inventory, or quit the program. The program will continue to process until
//  the user quits. There will be some input validation for "obvious" errors,
//  like inputting incorrect information for the menu. The car parts data will 
//  reside in a file and will include ID numbers, descriptions, costs, prices,
//  weights, locations, and quantities. Each box in the top-down design will 
//  utilize its own function. There are now also options to modify or delete a
//  part that is in the inventory once a proper ID number is provided from the 
//  user. 
// 
//  Project Number:  Project 08

public class Lab08 {

    public static void main(String[] args) throws IOException {
        
        int userChoice;
        
        //Manage Parts Inventory
        userChoice = 0;
        while(userChoice != 6) {
            
            userChoice = GetChoice( );
            PerformChoice(userChoice);
        
        }
        
    }
    
    public static int GetChoice( ) {
        
        Scanner kbd = new Scanner(System.in);
        int userChoice;
                       
        //Get Choice
        userChoice = 7;
        while(userChoice != 1 && userChoice != 2 && userChoice != 3 &&
            userChoice != 4 && userChoice != 5 && userChoice != 6) {
                
            System.out.print("What would you like to do: Look-up a part [1], "
                + "Add a new part to the inventory [2], Print the entire "
                    + "inventory [3], Delete a part from the inventory [4], "
                        + "Modify a part in the inventory [5], or Quit [6]"
                            + "?  "); //to user
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
        if(userChoice == 4)
            DeleteAPartFromInventory( );
        if(userChoice == 5)
            ModifyAPartInInventory( );
    
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
    
    public static void DeleteAPartFromInventory( ) throws IOException {
        
        int userPartIDNumber;
        
        //Delete a Part from Inventory
        userPartIDNumber = GetPartIDNumber( );
        ProcessEachPartForDeletionFromFile(userPartIDNumber);
        
    }
    
    public static void ProcessEachPartForDeletionFromFile(int userPartIDNumber) 
        throws IOException {
        
        Parts processParts;
        processParts = new Parts( );
        File partsInventory;
        Scanner partsInventorySC;
        PrintWriter newPartsInventoryPW;
        File newPartsInventory = new File("newPartsInventory.txt");
        
        //Process Each Part for Deletion from File
        newPartsInventoryPW = new PrintWriter("newPartsInventory.txt");
        partsInventory = new File("partsInventory.txt");
        partsInventorySC = new Scanner(partsInventory);
        while(partsInventorySC.hasNext( )) {
        
            GetPartInformationFromFile(partsInventorySC, processParts);
            StoreNonMatchingPartsInNewFile(userPartIDNumber, processParts, 
                newPartsInventoryPW);  
    
        }
        partsInventorySC.close( );
        newPartsInventoryPW.close( );
        partsInventory.delete( );
        newPartsInventory.renameTo(partsInventory);
        
    }
    
    public static void StoreNonMatchingPartsInNewFile(int userPartIDNumber, 
        Parts deleteUnwantedPart, PrintWriter newPartsInventoryPW) throws 
            IOException {
        
        DecimalFormat formatParts;
        
        //Store Non-Matching Parts in New File
        if(deleteUnwantedPart.GetPartIDNumber( ) != userPartIDNumber) {
            
            formatParts = new DecimalFormat("#0.00");
            newPartsInventoryPW.println(deleteUnwantedPart.GetPartIDNumber( )); 
                //to file
            newPartsInventoryPW.println(
                deleteUnwantedPart.GetPartDescription( )); //to file              
            newPartsInventoryPW.println(formatParts.format(
                deleteUnwantedPart.GetPartCost( ))); //to file
            newPartsInventoryPW.println(formatParts.format(
                deleteUnwantedPart.GetPartPrice( ))); //to file     
            newPartsInventoryPW.println(formatParts.format(
                deleteUnwantedPart.GetPartWeight( ))); //to file
            newPartsInventoryPW.println(deleteUnwantedPart.GetPartLocation( )); 
                //to file
            newPartsInventoryPW.println(deleteUnwantedPart.GetPartQuantity( )); 
                //to file
            System.out.println( );
           
        }
        
    }
    
    public static void ModifyAPartInInventory( ) throws IOException {
        
        int userPartIDNumber;
        
        //Modify a Part in Inventory
        userPartIDNumber = GetPartIDNumber( );
        ProcessEachPartForModificationFromFile(userPartIDNumber);
        
    }
    
    public static void ProcessEachPartForModificationFromFile(int 
        userPartIDNumber) throws IOException {
        
        Parts processParts;
        processParts = new Parts( );
        File partsInventory;
        Scanner partsInventorySC;
        PrintWriter newPartsInventoryPW;
        File newPartsInventory = new File("newPartsInventory.txt");
        
        //Process Each Part for Modification from File
        newPartsInventoryPW = new PrintWriter("newPartsInventory.txt");
        partsInventory = new File("partsInventory.txt");
        partsInventorySC = new Scanner(partsInventory);
        while(partsInventorySC.hasNext( )) {
        
            GetPartInformationFromFile(partsInventorySC, processParts);
            ModifyMatchingPart(userPartIDNumber, processParts, 
                newPartsInventoryPW); 
            StorePartInformationToNewFile(newPartsInventoryPW, processParts);
    
        }
        System.out.println( );
        partsInventorySC.close( );
        newPartsInventoryPW.close( );
        partsInventory.delete( );
        newPartsInventory.renameTo(partsInventory);
                
    } 
    
    public static void ModifyMatchingPart(int userPartIDNumber, 
        Parts modifyPart, PrintWriter newPartsInventoryPW) {
        
        int partIDNumber;
        String partDescription;
        double partCost;
        double partPrice;
        double partWeight;
        String partLocation;
        int partQuantity;
        Scanner kbd = new Scanner(System.in);
        
        //Modify Matching Part
        if(modifyPart.GetPartIDNumber( ) == userPartIDNumber) {
        
            System.out.print("Enter New Part ID Number:  "); //to user
            partIDNumber = kbd.nextInt( ); //from user
            kbd.nextLine( );
            System.out.print("Enter New Part Description:  "); //to user
            partDescription = kbd.nextLine( ); //from user
            System.out.print("Enter New Part Cost:  "); //to user
            partCost = kbd.nextDouble( ); //from user
            System.out.print("Enter New Part Price:  "); //to user
            partPrice = kbd.nextDouble( ); //from user
            System.out.print("Enter New Part Weight:  "); //to user
            partWeight = kbd.nextDouble( ); //from user
            kbd.nextLine( );
            System.out.print("Enter New Part Location:  "); //to user
            partLocation = kbd.nextLine( ); //from user
            System.out.print("Enter New Part Quantity:  "); //to user
            partQuantity = kbd.nextInt( ); //from user
            
            modifyPart.SetPartIDNumber(partIDNumber);
            modifyPart.SetPartDescription(partDescription);
            modifyPart.SetPartCost(partCost);
            modifyPart.SetPartPrice(partPrice);
            modifyPart.SetPartWeight(partWeight);
            modifyPart.SetPartLocation(partLocation);
            modifyPart.SetPartQuantity(partQuantity);
                 
        }
        
    }
    
    public static void StorePartInformationToNewFile(PrintWriter 
        newPartsInventoryPW, Parts modifyPart) {
        
        DecimalFormat formatParts;
        
        //Store Part Information to New File
        formatParts = new DecimalFormat("#0.00");
        newPartsInventoryPW.println(modifyPart.GetPartIDNumber( )); //to file
        newPartsInventoryPW.println(modifyPart.GetPartDescription( )); 
            //to file
        newPartsInventoryPW.println(formatParts.format(
            modifyPart.GetPartCost( ))); //to file           
        newPartsInventoryPW.println(formatParts.format(
            modifyPart.GetPartPrice( ))); //to file            
        newPartsInventoryPW.println(formatParts.format(
            modifyPart.GetPartWeight( ))); //to file
        newPartsInventoryPW.println(modifyPart.GetPartLocation( )); //to file
        newPartsInventoryPW.println(modifyPart.GetPartQuantity( )); //to file
        
    }
    
}
