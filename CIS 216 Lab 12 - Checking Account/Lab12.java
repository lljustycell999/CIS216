package lab.pkg12;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Monday December 13, 2021
//
//  Project Name:   Laboratory 12   
//
//  Program Description: This program will manage a checking account through 
//  several steps. Firstly, the program will obtain and load customer data from
//  a file, which will contain the balance and information for every 
//  transaction through a subarray of objects. If the subarray is full, 
//  the program will stop the user from adding additional transactions. 
//  Additionally, the program will be menu-driven and will allow the user to 
//  continuously add transactions, delete transactions, modify transactions, or
//  display statements containing the current balance, information for each 
//  transaction, and the balance after each individual transaction until the 
//  user chooses to quit and save the updated checking account data. Basic 
//  error handling will be enforced to avoid the program from crashing.   
// 
//  Project Number:  Project 12

public class Lab12 {

    public static void main(String[] args) throws IOException {
        
        CheckingAccount [ ] transactions;
        int numTransactions;
        
        //Manage Checking Account
        transactions = new CheckingAccount [2000];
        
        numTransactions = LoadCheckingAccountData(transactions);
        numTransactions = PerformEachTask(numTransactions, transactions);
        SaveUpdatedCheckingAccountData(numTransactions, transactions);
        
    }
    
    public static int LoadCheckingAccountData(CheckingAccount [ ] transactions) 
        throws IOException {
        
        int numTransactions;
        File checkingAccountInfo;
        Scanner checkingAccountInfoSC;
        double balance;
        String transactionDate;
        String transactionDescription;
        double transactionAmount;
             
        //Load Checking Account Data
        numTransactions = 0;
        checkingAccountInfo = new File("checkingAccount.txt");
        checkingAccountInfoSC = new Scanner(checkingAccountInfo);
        balance = checkingAccountInfoSC.nextDouble( ); //from file
        checkingAccountInfoSC.nextLine( );
        while(numTransactions < transactions.length && 
            checkingAccountInfoSC.hasNext( )) {
            
            transactionDate = checkingAccountInfoSC.nextLine( ); //from file
            transactionDescription = checkingAccountInfoSC.nextLine( ); 
                //from file
            transactionAmount = checkingAccountInfoSC.nextDouble( ); //from file
            checkingAccountInfoSC.nextLine( );
        
            transactions[numTransactions] = new CheckingAccount( );
            
            transactions[numTransactions].SetBalance(balance);
            transactions[numTransactions].SetTransactionDate(transactionDate);
            transactions[numTransactions].SetTransactionDescription(
                transactionDescription);
            transactions[numTransactions].SetTransactionAmount(
                transactionAmount);
            numTransactions++;
              
        }
        checkingAccountInfoSC.close( );
        return numTransactions;
        
    }
    
    public static int PerformEachTask(int numTransactions, 
        CheckingAccount [ ] transactions) {
        
        char userChoice;
        
        //Perform Each Task
        userChoice = 'L';
        while(userChoice != 'Q') {
            
            userChoice = GetChoice( );
            numTransactions = PerformChoice(userChoice, numTransactions, 
                transactions);
            
        }
        return numTransactions;
        
    }
    
    public static char GetChoice( ) {
        
        char userChoice;
        Scanner kbd = new Scanner(System.in);
        
        //Get Choice
        userChoice = 'Z';
        while(userChoice != 'A' && userChoice != 'D' && userChoice != 'M' && 
            userChoice != 'S' && userChoice != 'Q') {
            
            System.out.print("What would you like to do: Add a transaction "
                + "[A], Delete a transaction [D], Modify a transaction [M], "
                    + "Display a statement [S], or Quit and save [Q]?  "); 
                        //to user
            userChoice = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
            System.out.println( );
           
        }
        return userChoice;
        
    }
    
    public static int PerformChoice(char userChoice, int numTransactions, 
        CheckingAccount [ ] transactions) {
       
        //Perform Choice
        if(userChoice == 'A')
            numTransactions = AddTransaction(numTransactions, transactions);
        if(userChoice == 'D')
            numTransactions = DeleteATransaction(numTransactions, transactions);
        if(userChoice == 'M')
            ModifyATransaction(numTransactions, transactions);
        if(userChoice == 'S')
            DisplayAStatement(numTransactions, transactions);
        
        return numTransactions;
        
    }
    
    public static int AddTransaction(int numTransactions, 
        CheckingAccount [ ] transactions) {
        
        String transactionDate;
        String transactionDescription;
        double transactionAmount;
        Scanner kbd = new Scanner(System.in);
        
        //Add Transaction
        if(numTransactions < transactions.length) {
         
            transactions[numTransactions] = new CheckingAccount( );
        
            System.out.print("Enter Transaction Date:  "); //to user
            transactionDate = kbd.nextLine( ); //from user
            System.out.print("Enter Transaction Description:  "); //to user
            transactionDescription = kbd.nextLine( ); //from user
            System.out.print("Enter Transaction Amount:  "); //to user
            transactionAmount = kbd.nextDouble( ); //from user
            System.out.println( );
        
            transactions[numTransactions].SetTransactionDate(transactionDate);
            transactions[numTransactions].SetTransactionDescription(
                transactionDescription);
            transactions[numTransactions].SetTransactionAmount(
                transactionAmount);
            numTransactions++;
        
        }
        return numTransactions;
        
    }
    
    public static int DeleteATransaction(int numTransactions, 
        CheckingAccount [ ] transactions) {
        
        String searchTransactionDate;
        String searchTransactionDescription;
        double searchTransactionAmount;
        int loc;
        
        //Delete a Transaction
        searchTransactionDate = GetTransactionDate( );
        searchTransactionDescription = GetTransactionDescription( );
        searchTransactionAmount = GetTransactionAmount( );
        loc = SearchForMatchingTransactionInformation(numTransactions, 
            transactions, searchTransactionDate, searchTransactionDescription, 
                searchTransactionAmount);
        numTransactions = PerformDeletion(transactions, loc, numTransactions);
        
        return numTransactions;

    }
    
    public static String GetTransactionDate( ) {
        
        String searchTransactionDate;
        Scanner kbd = new Scanner(System.in);
        
        //Get Transaction Date
        System.out.print("Enter Transaction Date:  "); //to user
        searchTransactionDate = kbd.nextLine( ); //from user
        
        return searchTransactionDate;
        
    }
    
    public static String GetTransactionDescription( ) {
        
        String searchTransactionDescription;
        Scanner kbd = new Scanner(System.in);
        
        //Get Transaction Description
        System.out.print("Enter Transaction Description:  "); //to user
        searchTransactionDescription = kbd.nextLine( ); //from user
        
        return searchTransactionDescription;

    }
    
    public static double GetTransactionAmount( ) {
        
        double searchTransactionAmount;
        Scanner kbd = new Scanner(System.in);
        
        //Get Transaction Amount
        System.out.print("Enter Transaction Amount:  "); //to user
        searchTransactionAmount = kbd.nextDouble( ); //from user
        System.out.println( );
        
        return searchTransactionAmount;
        
    }
    
    public static int SearchForMatchingTransactionInformation(int 
        numTransactions, CheckingAccount [ ] transactions, String 
            searchTransactionDate, String searchTransactionDescription, 
                double searchTransactionAmount) {
        
        int loc;
        
        //Search for Matching Transaction Information
        loc = 0;
        while(loc < numTransactions && 
            (searchTransactionDate.compareToIgnoreCase(transactions[
                loc].GetTransactionDate( )) != 0 || 
                    searchTransactionDescription.compareToIgnoreCase(
                        transactions[loc].GetTransactionDescription( )) != 0 
                            || searchTransactionAmount != transactions[
                                loc].GetTransactionAmount( ))) 
            loc++;
     
        return loc;
        
    }
    
    public static int PerformDeletion(CheckingAccount [ ] transactions, 
        int loc, int numTransactions) {
        
        if(loc < numTransactions) {
            
            //Perform Deletion
            transactions[loc] = transactions[numTransactions - 1];

            transactions[loc].SetTransactionDate(transactions[numTransactions - 1
                ].GetTransactionDate( ));
            transactions[loc].SetTransactionDescription(transactions[
                numTransactions - 1].GetTransactionDescription( ));
            transactions[loc].SetTransactionAmount(transactions[numTransactions - 1
                ].GetTransactionAmount( ));
            numTransactions--;
        
        }
        
        return numTransactions;
        
    }
    
    public static void ModifyATransaction(int numTransactions, 
        CheckingAccount [ ] transactions) {
        
        String searchTransactionDate;
        String searchTransactionDescription;
        double searchTransactionAmount;
        int loc;
        
        //Modify a Transaction
        searchTransactionDate = GetTransactionDate( );
        searchTransactionDescription = GetTransactionDescription( );
        searchTransactionAmount = GetTransactionAmount( );
        loc = SearchForMatchingTransactionInformation(numTransactions, 
            transactions, searchTransactionDate, searchTransactionDescription, 
                searchTransactionAmount);
        PerformModification(loc, transactions, numTransactions);
        
    }
    
    public static void PerformModification(int loc, CheckingAccount [ ] 
        transactions, int numTransactions) {
        
        String nTransactionDate;
        String nTransactionDescription;
        double nTransactionAmount;
        Scanner kbd = new Scanner(System.in);
        
        //Perform Modification
        if(loc < numTransactions) {
            
            System.out.print("Enter new transaction date:  "); //to user
            nTransactionDate = kbd.nextLine( ); //from user
            System.out.print("Enter new transaction description:  "); //to user
            nTransactionDescription = kbd.nextLine( ); //from user
            System.out.print("Enter new transaction amount:  "); //to user
            nTransactionAmount = kbd.nextDouble( ); //from user

            transactions[loc].SetTransactionDate(nTransactionDate);
            transactions[loc].SetTransactionDescription(nTransactionDescription);
            transactions[loc].SetTransactionAmount(nTransactionAmount);
            System.out.println( );
        
        }
        
    }
    
    public static void DisplayAStatement(int numTransactions, 
        CheckingAccount [ ] transactions) {
        
        //Display a Statement
        DisplayHeading(numTransactions, transactions);
        DisplayCheckingAccountSummary(numTransactions, transactions);
        
    }
            
    public static void DisplayHeading(int numTransactions, 
        CheckingAccount [ ] transactions) {
        
        //Display Heading
        System.out.printf("%-20s%-50s%-20s%-20s\n", "Transaction Date", 
            "Transaction Description", "Transaction Amount", 
                "Balance After Transaction"); //to user

    }
    
    public static void DisplayCheckingAccountSummary(int numTransactions, 
        CheckingAccount [ ] transactions) {
        
        double transactionTotal;
        
        //Display Checking Account Summary
        transactionTotal = 0;
        for(int cnt = 0; cnt < numTransactions; cnt++) {
            
            transactionTotal = transactionTotal + 
                transactions[cnt].GetTransactionAmount( );
            System.out.printf("%-20s%-50s%-20.2f%-20.2f\n", 
                transactions[cnt].GetTransactionDate( ), 
                    transactions[cnt].GetTransactionDescription( ), 
                        transactions[cnt].GetTransactionAmount( ), 
                            transactions[0].GetBalance( ) + transactionTotal); 
                                //to user
            
        }
        System.out.println( );
        System.out.printf("%-1s%-1.2f\n", "Current Balance: $", 
            transactions[0].GetBalance( ) + transactionTotal); //to user
        System.out.println( );

    }
    
    public static void SaveUpdatedCheckingAccountData(int numTransactions, 
        CheckingAccount [ ] transactions) throws IOException {
        
        PrintWriter saveCheckingAccountPW;
        
        //Save Updated Checking Account Data
        saveCheckingAccountPW = new PrintWriter("checkingAccount.txt");
        saveCheckingAccountPW.printf("%-1.2f\n", transactions[0].GetBalance( )); 
            //to file
        for(int cnt = 0; cnt < numTransactions; cnt++) {
            
            saveCheckingAccountPW.println(transactions[cnt].GetTransactionDate(
                )); //to file
            saveCheckingAccountPW.println(transactions[cnt
                ].GetTransactionDescription( )); //to file
            saveCheckingAccountPW.printf("%-1.2f\n", transactions[cnt
                ].GetTransactionAmount( )); //to file
          
        }
        saveCheckingAccountPW.close( );
        
    }
    
}
