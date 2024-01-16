package lab.pkg01;

import java.util.Scanner;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday September 17, 2021
//
//  Project Name:   Laboratory 01   
//
//  Program Description:
//     You work for a company that delivers sand (like for roads etc.) 
//     You are the person who usually quotes the prices. In order to do this, 
//     you need to know what it actually costs. Customers always want to know 
//     the most economical method available (i.e. the cheapest). The problem is
//     that the customers can select the truck size, which makes estimating 
//     their expense somewhat challenging. However, you, being as bright and 
//     talented as you are, have come up with a solution. You have decided to 
//     write a program that will compute the costs so you can then produce a 
//     quote for a customer.
//     Your program will prompt the user for the customer name and address as 
//     well as the amount of sand (in cubic yards) they desire. Additionally, 
//     the program will ask the user to enter the distance to the customer, the 
//     driver's hourly rate, as well as the price per gallon of diesel fuel. 
//     From this data, the program will determine how long the driver will 
//     spend driving based upon how many truckloads of sand will be delivered 
//     and the cost of diesel.
//     For the delivery of the sand, your company offers a large or small dump 
//     truck. The dimensions of the small dump truck bed is 9.5(L) X 5.5(W) X 
//     3(H) while the large dump truck dimensions are 10.5(L) X 6(W) X 3.5(H). 
//     Assume that sand can be heaped in the dump trucks thus forming a 
//     "pyramid" type shape on top of the bed. The volume of this "pyramid" is 
//     1/3 * the volume of the base. You may assume that the small dump truck 
//     travels an average of 45 miles per hour while the large dump truck 
//     travels an average speed of 40 miles per hour. Further assume that the 
//     small dump truck can travel 7 miles per gallon of diesel fuel while the 
//     large dump truck travels 5.1 miles per gallon.
//     Your program must provide a summary of costs for each size of dump truck.
//     Redisplay all user- entered information along with all calculated values 
//     (i.e. number of hours of driving, number of truckloads, etc.) 
//     Display the calculated values in formatted columns. You may build all 
//     truck dimension, mileage, and speed values directly into the 
//     program - do NOT use constants.
//
//  Project Number:  Project 01

public class Lab01 {

    public static void main(String[ ] args) {
       
       String name, address;
       double amountOfSand;
       double smallLength, smallWidth, smallHeight;
       double largeLength, largeWidth, largeHeight;
       double distanceToCustomer;
       double hourlyRate;
       double smallMPG, largeMPG;
       double smallMPH, largeMPH;
       double pricePerGallon;
       double smallVolume, largeVolume;
       double numSmallLoads, numLargeLoads;
       double smallHours, largeHours;
       double smallGallons, largeGallons;
       double smallWages, largeWages;
       double smallFuelCost, largeFuelCost;
       double overallSmallCost, overallLargeCost;
       
       //Establish keyboard availability
       Scanner keyboard = new Scanner(System.in);
       
       //Get Customer Information
       System.out.print("Enter name:  "); //to user
       name = keyboard.nextLine( ); //from user
       System.out.print("Enter address:  "); //to user
       address = keyboard.nextLine( ); //from user
       
       //Get Desired Amount of Sand
       System.out.print("Enter the amount of sand wanted "
           + "(in cubic yards):  "); //to user
       amountOfSand = keyboard.nextDouble( ); //from user
       
       //Set Small and Large Truck Dimensions
       smallLength = 9.5; //in feet
       smallWidth = 5.5; //in feet
       smallHeight = 3.0; //in feet
       largeLength = 10.5; //in feet
       largeWidth = 6.0; //in feet
       largeHeight = 3.5; //in feet
       
       //Get Distance to Customer
       System.out.print("Enter the distance "
           + "(one-way) to customer:  "); //to user
       distanceToCustomer = keyboard.nextDouble( ); //from user
       
       //Get Driver Hourly Rate
       System.out.print("Enter the driver's hourly pay rate:  "); //to user
       hourlyRate = keyboard.nextDouble( ); //from user
       
       //Set MPG for Each Truck Size
       smallMPG = 7.0; //in miles per gallon
       largeMPG = 5.1; //in miles per gallon
       
       //Set Truck Speeds
       smallMPH = 45; //in miles per hour
       largeMPH = 40; //in miles per hour
       
       //Get Price Per Gallon of Diesel Fuel
       System.out.print("Enter price per gallon of diesel fuel:  "); //to user
       pricePerGallon = keyboard.nextDouble( ); //from user
       System.out.println( );
       
       //Calculate Small and Large Truck Volumes
       smallVolume = smallLength * smallWidth * smallHeight * 4.0 / 3.0 / 27.0;
       largeVolume = largeLength * largeWidth * largeHeight * 4.0 / 3.0 / 27.0;
       
       //Calculate Number of Truckloads for Each Truck Size
       numSmallLoads = (int)(amountOfSand / smallVolume + 0.99); //round UP
       numLargeLoads = (int)(amountOfSand / largeVolume + 0.99); //round UP
       
       //Calculate How Long Driver Drives
       smallHours = distanceToCustomer * 2 * numSmallLoads / smallMPH;
       largeHours = distanceToCustomer * 2 * numLargeLoads / largeMPH;
       
       //Calculate Fuel Usage
       smallGallons = distanceToCustomer * 2 * numSmallLoads / smallMPG;
       largeGallons = distanceToCustomer * 2 * numLargeLoads / largeMPG;
       
       //Calculate Driver's Wages
       smallWages = smallHours * hourlyRate;
       largeWages = largeHours * hourlyRate;
       
       //Calculate Diesel Fuel Cost
       smallFuelCost = pricePerGallon * smallGallons;
       largeFuelCost = pricePerGallon * largeGallons;
       
       //Calculate Overall Cost of Each Truck Size
       overallSmallCost = smallFuelCost + smallWages;
       overallLargeCost = largeFuelCost + largeWages;
       
       //Display Customer Information
       System.out.println("Customer:  " + name); //to user
       System.out.println("Address:  " + address); //to user
       System.out.println( );
       
       //Display Shipping Cost Summary
       System.out.printf("%-23s%15s%15s\n", "Item", "Small Truck", 
           "Large Truck"); //to user
       System.out.printf("%-23s%15.1f%15.1f\n", "Truck volume:", 
           smallVolume, largeVolume); //to user
       System.out.printf("%-23s%15.1f%15.1f\n", "Number of truckloads:", 
           numSmallLoads, numLargeLoads); //to user
       System.out.printf("%-23s%15.2f%15.2f\n", "Driver wages", 
           smallWages, largeWages); //to user
       System.out.printf("%-23s%15.2f%15.2f\n", "Fuel costs:", 
           smallFuelCost, largeFuelCost); //to user
       System.out.printf("%-23s%15.2f%15.2f\n", "Overall costs:", 
           overallSmallCost, overallLargeCost); //to user
    }   //END main
    
}   //END Sand class
