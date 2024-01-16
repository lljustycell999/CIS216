package lab.pkg03;

import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday October 1, 2021
//
//  Project Name:   Laboratory 03   
//
//  Program Description:
//     This program will manage billing for the Miracle Caterers company. 
//     The program will give the user the option to either display a bill or 
//     update the prices, which are stored in a file, and then perform the 
//     desired choice. The program will also include graphical inputs for all 
//     interactions. In terms of program output, the bill can be displayed on 
//     the console. When changing prices, the program will ask the user for the 
//     new prices, including the standard meal, deluxe meal, and the four 
//     banquet room costs. The current (old) prices will also be displayed 
//     before the user enters each new price. The prices will then be updated 
//     and stored in the same file. When producing a bill, the user will need 
//     to enter several items before the program will calculate the bill, 
//     including the meal type, room choice, number of meals, and deposit. 
//     When these items are provided, the program will produce a bill for the 
//     user. The bill is calculated based on the initial costs of the standard 
//     meals ($24.55/meal), deluxe meals ($31.95/meal), and the four banquet 
//     rooms ([A] is $115.28, [B] is $147.50, [C] is $168.34, and [D] is 
//     $223.94). The bill calculations also consider an additional 25% cost only 
//     to the food charges to account for the tax and tip. Lastly, the program 
//     will calculate a discount to show what the bill will be if it is paid 
//     within ten days. For bills under $500 the discount is 4%, for bills 
//     between $500 and $900 (inclusive) the discount rate is 5%, and for 
//     bills that exceed $900 the discount rate is 6%. Proper headings will be
//     included when the bill is displayed, along with the subtotal of the food,
//     the food tax and tip rate and cost, the room rental fee, the total cost, 
//     the discount rate and total, the deposit amount, and the balance due when
//     the discount total and deposit amount are subtracted from the total cost.
//
//  Project Number:  Project 03

public class Lab03 {

    public static void main(String[] args) throws IOException {
        
        File priceFile;
        Scanner priceFileSC;
         
        double standardMealCost,
               deluxeMealCost,
               banquetRoom1Cost,
               banquetRoom2Cost,
               banquetRoom3Cost,
               banquetRoom4Cost;
        
        int userChoice;
        
        char mealChoice;
        
        char banquetRoomChoice;
        
        int numMeals;
        
        double depositAmount;
        
        double mealCost,
               mealSubCost;
        
        double banquetRoomCost;
        
        double foodTaxAndTip;
        
        double totalBill;
        
        double discountRate,
               totalDiscount;
        
        double balanceDue;
        
        String temp;
        
        DecimalFormat formattedPrice;
        
        PrintWriter newPriceFile;
        
        //Get Prices
        priceFile = new File("priceFile.txt");
        priceFileSC = new Scanner(priceFile);
        standardMealCost = priceFileSC.nextDouble( ); //from file
        deluxeMealCost = priceFileSC.nextDouble( ); //from file
        banquetRoom1Cost = priceFileSC.nextDouble( ); //from file
        banquetRoom2Cost = priceFileSC.nextDouble( ); //from file
        banquetRoom3Cost = priceFileSC.nextDouble( ); //from file
        banquetRoom4Cost = priceFileSC.nextDouble( ); //from file
        priceFileSC.close( );
            
        //Get Choice
        temp = JOptionPane.showInputDialog("What would you like to do: "
            + "Generate Bill [1] or Change Prices [2]?  "); //to user
        userChoice = Integer.parseInt(temp); //from user
        
        if(userChoice == 1) {
  
            //Get Meal Type
            mealChoice = JOptionPane.showInputDialog("Enter meal type: "
                + "Standard Meal [S] or Deluxe Meal [D]  ").charAt(0); 
                    //to user and from user

            //Get Banquet Room Choice
            banquetRoomChoice = JOptionPane.showInputDialog
                ("Enter banquet room choice: Room [A], [B], [C], or "
                    + "[D]  ").charAt(0); //to user and from user
               
            //Get Number of Meals
            temp = JOptionPane.showInputDialog("Enter number of "
                + "meals:  "); //to user
            numMeals = Integer.parseInt(temp); //from user
        
            //Get Deposit Amount
            temp = JOptionPane.showInputDialog("Enter deposit "
                + "amount:  "); //to user
            depositAmount = Double.parseDouble(temp); //from user
        
            //Calculate Food Cost
            //if(mealChoice == 'S')
                mealCost = standardMealCost;
            if(mealChoice == 'D')
                mealCost = deluxeMealCost;
            mealSubCost = mealCost * numMeals;
        
            //Calculate Banquet Room Cost
            //if(banquetRoomChoice == 'A')
                banquetRoomCost = banquetRoom1Cost;
            if(banquetRoomChoice == 'B')
                banquetRoomCost = banquetRoom2Cost;
            if(banquetRoomChoice == 'C')
                banquetRoomCost = banquetRoom3Cost;
            if(banquetRoomChoice == 'D')
                banquetRoomCost = banquetRoom4Cost;
        
            //Calculate Food Fees
            foodTaxAndTip = mealSubCost * 0.25;
        
            //Calculate Total Bill
            totalBill = mealSubCost + banquetRoomCost + foodTaxAndTip;
        
            //Calculate Discount
            //if(totalBill < 500)
                discountRate = 0.04;
            if(totalBill >= 500 && 900 >= totalBill)
                discountRate = 0.05;
            if(totalBill > 900)
                discountRate = 0.06;
            totalDiscount = totalBill * discountRate;
        
            //Calculate Balance Due
            balanceDue = totalBill - depositAmount - totalDiscount;
        
            //Display Bill
            System.out.printf("%44s\n", "MIRACLE CATERING SERVICES"); //to user
            System.out.printf("%54s\n", "When you get a GREAT meal, it's "
                + "a MIRACLE."); //to user
            System.out.println( );
            System.out.printf("%41s\n", "BILL for SERVICES"); //to user
            System.out.println( );
            System.out.printf("%5s%46.2f\n", "Total food:  ", 
                mealSubCost); //to user
            System.out.printf("%4s%17s%28.2f\n", "Tax and tip:  ", "0.25", 
                foodTaxAndTip); //to user
            System.out.printf("%5s%41.2f\n", "Room rental fee:  ", 
                banquetRoomCost); //to user
            System.out.println( );
            System.out.printf("%5s%51.2f\n", "Total:  ", totalBill); //to user
            System.out.println( );
            System.out.printf("%5s\n", "Discount if bill is paid"); //to user
            System.out.printf("%24s%7.2f%28.2f\n", "within 10 days:  ", 
                discountRate, totalDiscount); //to user
            System.out.printf("%5s%49.2f\n", "Deposit:  ", 
                depositAmount); //to user
            System.out.println( );
            System.out.printf("%5s%45.2f\n", "Balance due:  ", 
                balanceDue); //to user
        }
        
        if(userChoice == 2) {
            
            //Get New Prices
            formattedPrice = new DecimalFormat("#0.00");
            temp = JOptionPane.showInputDialog("Enter new standard meal cost"
                + " (" + formattedPrice.format(standardMealCost) + 
                    ")"); //to user
            standardMealCost = Double.parseDouble(temp); //from user
            temp = JOptionPane.showInputDialog("Enter new deluxe meal cost"
                + " (" + formattedPrice.format(deluxeMealCost) + 
                    ")"); //to user
            deluxeMealCost = Double.parseDouble(temp); //from user
            temp = JOptionPane.showInputDialog("Enter new banquet room A"
                + " rental cost (" + formattedPrice.format(banquetRoom1Cost) + 
                    ")"); //to user
            banquetRoom1Cost = Double.parseDouble(temp); //from user
            temp = JOptionPane.showInputDialog("Enter new banquet room B"
                + " rental cost (" + formattedPrice.format(banquetRoom2Cost) + 
                    ")"); //to user
            banquetRoom2Cost = Double.parseDouble(temp); //from user
            temp = JOptionPane.showInputDialog("Enter new banquet room C"
                + " rental cost (" + formattedPrice.format(banquetRoom3Cost) + 
                    ")"); //to user
            banquetRoom3Cost = Double.parseDouble(temp); //from user
            temp = JOptionPane.showInputDialog("Enter new banquet room D"
                + " rental cost (" + formattedPrice.format(banquetRoom4Cost) + 
                    ")"); //to user
            banquetRoom4Cost = Double.parseDouble(temp); //from user

            //Update Billing File
            newPriceFile = new PrintWriter("priceFile.txt");
            newPriceFile.printf("%2.2f\n", standardMealCost); //to file
            newPriceFile.printf("%2.2f\n", deluxeMealCost); //to file
            newPriceFile.printf("%2.2f\n", banquetRoom1Cost); //to file
            newPriceFile.printf("%2.2f\n", banquetRoom2Cost); //to file
            newPriceFile.printf("%2.2f\n", banquetRoom3Cost); //to file
            newPriceFile.printf("%2.2f\n", banquetRoom4Cost); //to file
            newPriceFile.close( );
        }
            System.exit(0);         
    }   //END main
    
}   //END Catering class
