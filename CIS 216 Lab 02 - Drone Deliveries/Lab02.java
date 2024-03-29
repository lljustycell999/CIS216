package lab.pkg02;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday September 24, 2021
//
//  Project Name:   Laboratory 02   
//
//  Program Description:
//     This program will determine how fast drones from specifc departure sites
//     near the area could respond to orders. The program will also indicate
//     which drones are out-of-range. Drone information for each nearby
//     departure site is read from a data file, including the port names, their  
//     x and y coordinates, their drone speeds, their maximum ranges, and their 
//     payloads. The program will ask the user for the customer name, street
//     address, city/state/zip, delivery coordinates, and the payload weight.
//     The results will redisplay the customer information with labels and 
//     display the port number, port name, speed, maximum range, 
//     payload, distance to delivery point, and estimated time arrival for
//     each drone in a tabular format. Column headings will also be included.
//     The program will divide the distance to scene by the drone speed to
//     get the estimated time of arrival (elapsed hours). The program also 
//     utilizes the distance formula to calculate the distance to delivery for 
//     each drone, which has to be multiplied by 2.6163 to get the
//     the program to give actual miles and not linear miles due to the
//     coordinates. 
//
//  Project Number:  Project 02

public class Lab02 {

    public static void main(String[] args) throws IOException {
       
       File inFile;
       Scanner inFileSC;
              
       String droneSiteName1, 
              droneSiteName2,
              droneSiteName3,
              droneSiteName4,
              droneSiteName5,
              droneSiteName6,
              droneSiteName7;
       
       Double droneXCoord1,
              droneXCoord2,
              droneXCoord3,
              droneXCoord4,
              droneXCoord5,
              droneXCoord6,
              droneXCoord7;
       
       Double droneYCoord1,
              droneYCoord2,
              droneYCoord3,
              droneYCoord4,
              droneYCoord5,
              droneYCoord6,
              droneYCoord7;
       
       Double droneSpeed1,
              droneSpeed2,
              droneSpeed3,
              droneSpeed4,
              droneSpeed5,
              droneSpeed6,
              droneSpeed7;
       
       Double droneRange1,
              droneRange2,
              droneRange3,
              droneRange4,
              droneRange5,
              droneRange6,
              droneRange7;
       
       Double dronePayload1,
              dronePayload2,
              dronePayload3,
              dronePayload4,
              dronePayload5,
              dronePayload6,
              dronePayload7;
       
       String name,
              address,
              cityStateZip;
       
       Double xDeliveryCoord,
              yDeliveryCoord;
       
       Double payloadWeight;
       
       Double deliveryDistance1,
              deliveryDistance2,
              deliveryDistance3,
              deliveryDistance4,
              deliveryDistance5,
              deliveryDistance6,
              deliveryDistance7;

       Double travelTime1,
              travelTime2,
              travelTime3,
              travelTime4,
              travelTime5,
              travelTime6,
              travelTime7;
       
        Scanner kbd;
        
        //Establish Keyboard Availability
        kbd = new Scanner(System.in);
        
        //Get Drone Information from File
        inFile = new File("dronefile.txt");
        inFileSC = new Scanner(inFile);
        droneSiteName1 = inFileSC.nextLine( ); //from file
        droneSiteName2 = inFileSC.nextLine( ); //from file
        droneSiteName3 = inFileSC.nextLine( ); //from file
        droneSiteName4 = inFileSC.nextLine( ); //from file
        droneSiteName5 = inFileSC.nextLine( ); //from file
        droneSiteName6 = inFileSC.nextLine( ); //from file
        droneSiteName7 = inFileSC.nextLine( ); //from file
        droneXCoord1 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneXCoord2 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneXCoord3 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneXCoord4 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneXCoord5 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneXCoord6 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneXCoord7 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneYCoord1 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneYCoord2 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneYCoord3 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneYCoord4 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneYCoord5 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneYCoord6 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneYCoord7 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneSpeed1 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneSpeed2 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneSpeed3 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneSpeed4 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneSpeed5 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneSpeed6 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneSpeed7 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneRange1 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneRange2 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneRange3 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneRange4 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneRange5 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneRange6 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        droneRange7 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        dronePayload1 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        dronePayload2 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        dronePayload3 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        dronePayload4 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        dronePayload5 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        dronePayload6 = inFileSC.nextDouble( ); //from file
        inFileSC.nextLine( );
        dronePayload7 = inFileSC.nextDouble( ); //from file
        inFileSC.close( );
        
        //Get Customer Information
        System.out.print("Enter customer name:  "); //to user
        name = kbd.nextLine( ); //from user
        System.out.print("Enter address:  "); //to user
        address = kbd.nextLine( ); //from user
        System.out.print("Enter city, state, and zip:  "); //to user
        cityStateZip = kbd.nextLine( ); //from user
        
        //Get Delivery Coordinates
        System.out.print("Enter X delivery coordinate:  "); //to user
        xDeliveryCoord = kbd.nextDouble( ); //from user
        System.out.print("Enter Y delivery coordinate:  "); //to user
        yDeliveryCoord = kbd.nextDouble( ); //from user
        
        //Get Payload Weight
        System.out.print("Enter payload weight:  "); //to user
        payloadWeight = kbd.nextDouble( ); //from user
        System.out.println( );
        
        //Calculate Drone Delivery Distances
        deliveryDistance1 = Math.sqrt(Math.pow(droneXCoord1 - 
                xDeliveryCoord, 2) + 
            Math.pow(droneYCoord1 - yDeliveryCoord, 2)) * 2.6163; //in miles
        deliveryDistance2 = Math.sqrt(Math.pow(droneXCoord2 - 
                xDeliveryCoord, 2) + 
            Math.pow(droneYCoord2 - yDeliveryCoord, 2)) * 2.6163; //in miles
        deliveryDistance3 = Math.sqrt(Math.pow(droneXCoord3 - 
                xDeliveryCoord, 2) + 
            Math.pow(droneYCoord3 - yDeliveryCoord, 2)) * 2.6163; //in miles
        deliveryDistance4 = Math.sqrt(Math.pow(droneXCoord4 - 
                xDeliveryCoord, 2) + 
            Math.pow(droneYCoord4 - yDeliveryCoord, 2)) * 2.6163; //in miles
        deliveryDistance5 = Math.sqrt(Math.pow(droneXCoord5 - 
                xDeliveryCoord, 2) + 
            Math.pow(droneYCoord5 - yDeliveryCoord, 2)) * 2.6163; //in miles
        deliveryDistance6 = Math.sqrt(Math.pow(droneXCoord6 - 
                xDeliveryCoord, 2) + 
            Math.pow(droneYCoord6 - yDeliveryCoord, 2)) * 2.6163; //in miles
        deliveryDistance7 = Math.sqrt(Math.pow(droneXCoord7 - 
                xDeliveryCoord, 2) + 
            Math.pow(droneYCoord7 - yDeliveryCoord, 2)) * 2.6163; //in miles
        
        //Calculate Travel Times
        travelTime1 = deliveryDistance1 / droneSpeed1; //in hours
        travelTime2 = deliveryDistance2 / droneSpeed2; //in hours
        travelTime3 = deliveryDistance3 / droneSpeed3; //in hours
        travelTime4 = deliveryDistance4 / droneSpeed4; //in hours
        travelTime5 = deliveryDistance5 / droneSpeed5; //in hours
        travelTime6 = deliveryDistance6 / droneSpeed6; //in hours
        travelTime7 = deliveryDistance7 / droneSpeed7; //in hours
        
        //Display Customer Information
        System.out.println("Customer name:  " + name); //to user
        System.out.println("Address:  " + address); //to user
        System.out.println("City, state, and zip:  " + cityStateZip); //to user
        System.out.println("Payload weight:  " + payloadWeight); //to user
        System.out.println();
        
        //Display Drone Delivery Information
        System.out.printf("%11s%20s%17s%19s%13s%32s%27s\n", "Port Number", 
            "Port Name", "Drone Speed", "Maximum Range", "Payload", 
                "Distance to Delivery Point", 
                    "Estimated Travel Time"); //to user
        System.out.printf("%11s%20s%17.2f%19.2f%13.2f%32.2f%27.2f\n", "1", 
            droneSiteName1, droneSpeed1, droneRange1, dronePayload1,
                deliveryDistance1, travelTime1); //to user
        System.out.printf("%11s%20s%17.2f%19.2f%13.2f%32.2f%27.2f\n", "2", 
            droneSiteName2, droneSpeed2, droneRange2, dronePayload2,
                deliveryDistance2, travelTime2); //to user
        System.out.printf("%11s%20s%17.2f%19.2f%13.2f%32.2f%27.2f\n", "3", 
            droneSiteName3, droneSpeed3, droneRange3, dronePayload3,
                deliveryDistance3, travelTime3); //to user
        System.out.printf("%11s%20s%17.2f%19.2f%13.2f%32.2f%27.2f\n", "4", 
            droneSiteName4, droneSpeed4, droneRange4, dronePayload4,
                deliveryDistance4, travelTime4); //to user
        System.out.printf("%11s%20s%17.2f%19.2f%13.2f%32.2f%27.2f\n", "5", 
            droneSiteName5, droneSpeed5, droneRange5, dronePayload5,
                deliveryDistance5, travelTime5); //to user
        System.out.printf("%11s%20s%17.2f%19.2f%13.2f%32.2f%27.2f\n", "6", 
            droneSiteName6, droneSpeed6, droneRange6, dronePayload6,
                deliveryDistance6, travelTime6); //to user
        System.out.printf("%11s%20s%17.2f%19.2f%13.2f%32.2f%27.2f\n", "7", 
            droneSiteName7, droneSpeed7, droneRange7, dronePayload7,
                deliveryDistance7, travelTime7); //to user      
    }   //END main
    
}   //END Drone class
