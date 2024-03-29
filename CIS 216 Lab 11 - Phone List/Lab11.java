package lab.pkg11;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday December 10, 2021
//
//  Project Name:   Laboratory 11   
//
//  Program Description: This program will keep track of a phone list by 
//  letting the user perform menu-based options constantly until he or she 
//  chooses to quit. The program will use a file once to access the initial 
//  phone information through sub-arrays and once to save all applicable 
//  changes to the phone list through user choices and input once the user 
//  quits. There are choices to add an entity, delete an entity, modify an 
//  entity, search for and display an entity, and display all entities.    
// 
//  Project Number:  Project 11

public class Lab11 {

    public static void main(String[] args) throws IOException {
        
        String [ ] name;
        String [ ] office;
        String [ ] phone;
        int numEntries;
      
        //Manage Phone List
        name = new String [5000];
        office = new String [5000];
        phone = new String [5000];
        numEntries = LoadPhoneList(name, office, phone);
        numEntries = PerformEachTask(name, office, phone, numEntries);
        SavePhoneList(numEntries, name, office, phone);
        
    }
    
    public static int LoadPhoneList(String [ ] name, String [ ] office, 
        String [ ] phone) throws IOException {
        
        int numEntries;
        File phoneInfo;
        Scanner phoneInfoSC;
        
        //Load Phone List
        numEntries = 0;
        phoneInfo = new File("phonelist.txt");
        phoneInfoSC = new Scanner(phoneInfo);
        while(numEntries < name.length && phoneInfoSC.hasNext( )) {
            
            name [numEntries] = phoneInfoSC.nextLine( ); //from file
            office [numEntries] = phoneInfoSC.nextLine( ); //from file
            phone [numEntries] = phoneInfoSC.nextLine( ); //from file
            numEntries++;
            
        }
        phoneInfoSC.close( );
        return numEntries;
        
    }
    
    public static int PerformEachTask(String [ ] name, String [ ] office, 
        String [ ] phone, int numEntries) {
        
        char userChoice;
        
        //Perform Each Task
        userChoice = 'Z';
        while(userChoice != 'F') {
            
            userChoice = GetChoice( );
            numEntries = PerformChoice(userChoice, name, office, phone, 
                numEntries);
            
        }
        return numEntries;
        
    }
    
    public static char GetChoice( ) {
        
        char userChoice;
        Scanner kbd = new Scanner(System.in);
        
        //Get Choice
        userChoice = 'G';
        while(userChoice != 'A' && userChoice != 'B' && userChoice != 'C' && 
            userChoice != 'D' && userChoice != 'E' && userChoice != 'F') {
            
            System.out.print("What would you like to do: Add an entry [A], "
                + "Display a specific entry [B], Delete an entry [C], Modify "
                    + "an entry [D], Display all entries [E], or Quit and save "
                        + "the phone list [F]?  "); //to user
            userChoice = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
            System.out.println( );
            
        }
        return userChoice;
        
    }
    
    public static int PerformChoice(int userChoice, String [ ] name, 
        String [ ] office, String [ ] phone, int numEntries) {

        //Perform Choice
        if(userChoice == 'A')
            numEntries = AddEntry(name, office, phone, numEntries);
        if(userChoice == 'B')
            DisplayOneEntry(numEntries, name, office, phone);
        if(userChoice == 'C')
            numEntries = DeleteEntry(numEntries, name, office, phone);
        if(userChoice == 'D')
            ModifyEntry(numEntries, name, office, phone);
        if(userChoice == 'E')
            DisplayAllEntries(numEntries, name, office, phone);
        
        return numEntries;
        
    }
    
    public static int AddEntry(String [ ] name, String [ ] office, 
        String [ ] phone, int numEntries) {
        
        Scanner kbd = new Scanner(System.in);
        
        //Add Entry
        if(numEntries < name.length) {
            
            System.out.print("Enter name:  "); //to user
            name [numEntries] = kbd.nextLine( ); //from user
            System.out.print("Enter office location:  "); //to user
            office [numEntries] = kbd.nextLine( ); //from user
            System.out.print("Enter phone number:  "); //to user
            phone [numEntries] = kbd.nextLine( ); //from user
            numEntries++;
            
        }
        System.out.println( );
        return numEntries;
        
    }
    
    public static void DisplayOneEntry(int numEntries, String [ ] name, 
        String [ ] office, String [ ] phone) {
        
        String searchName;
        int loc;
        
        //Display One Entry
        searchName = GetName( );
        DisplayHeading( );
        loc = SearchForMatchingName(numEntries, searchName, name);
        DisplayMatchingEntry(loc, numEntries, name, office, phone);
        
    }
    
    public static String GetName( ) {
        
        String searchName;
        Scanner kbd = new Scanner(System.in);
        
        //Get Name
        System.out.print("Enter name to search:  "); //to user
        searchName = kbd.nextLine( ); //from user
        System.out.println( );
        
        return searchName;
        
    }
    
    public static void DisplayHeading( ) {
        
        //Display Heading
        System.out.printf("%-40s%-40s%-1s\n", "Name", "Office Location", 
            "Phone Number"); //to user
        
    }
    
    public static int SearchForMatchingName(int numEntries, String searchName,
        String [ ] name) {
        
        int loc;
        
        //Search for Matching Name
        loc = 0;
        while(loc < numEntries && searchName.compareToIgnoreCase(name [loc]) 
            != 0)
            loc++;
        
        return loc;
        
    }
    
    public static void DisplayMatchingEntry(int loc, int numEntries, 
        String [ ] name, String [ ] office, String [ ] phone) {
        
        //Display Matching Entry
        System.out.printf("%-40s%-40s%-1s\n", name [loc], office [loc], 
            phone [loc]); //to user
        System.out.println( );
            
    }
    
    public static int DeleteEntry(int numEntries, String [ ] name, 
        String [ ] office, String [ ] phone) {
        
        String searchName;
        int loc;
        
        //Delete Entry
        searchName = GetName( );
        loc = SearchForMatchingName(numEntries, searchName, name);
        numEntries = PerformDeletion(loc, numEntries, name, office, phone);
        
        return numEntries;
        
    }
    
    public static int PerformDeletion(int loc, int numEntries, 
        String [ ] name, String [ ] office, String [ ] phone) {
        
        //Perform Deletion   
        name [loc] = name [numEntries - 1];
        office [loc] = office [numEntries - 1];
        phone [loc] = phone [numEntries - 1];
        numEntries--;
            
        return numEntries;
        
    }
    
    public static void ModifyEntry(int numEntries, String [ ] name, 
        String [ ] office, String [ ] phone) {
        
        String searchName;
        int loc;
        
        //Modify Entry
        searchName = GetName( );
        loc = SearchForMatchingName(numEntries, searchName, name);
        PerformModification(loc, numEntries, name, office, phone);
        
    }
    
    public static void PerformModification(int loc, int numEntries, 
        String [ ] name, String [ ] office, String [ ] phone) {
        
        Scanner kbd = new Scanner(System.in);
        
        //Perform Modification
        System.out.print("Enter new name:  "); //to user
        name [loc] = kbd.nextLine( ); //from user
        System.out.print("Enter new office location:  "); //to user
        office [loc] = kbd.nextLine( ); //from user
        System.out.print("Enter new phone number:  "); //to user
        phone [loc] = kbd.nextLine( ); //from user
        System.out.println( );
                    
    }
    
    public static void DisplayAllEntries(int numEntries, String [ ] name, 
        String [ ] office, String [ ] phone) {
        
        //Display All Entries
        DisplayHeading( );
        DisplayEntrySummary(numEntries, name, office, phone);
       
    }
    
    public static void DisplayEntrySummary(int numEntries, 
        String [ ] name, String [ ] office, String [ ] phone) {
        
        //Display Entry Summary
        for(int cnt = 0; cnt < numEntries; cnt++)
            System.out.printf("%-40s%-40s%-1s\n", name [cnt], office [cnt], 
                phone [cnt]); //to user
        System.out.println( );
        
    }
    
    public static void SavePhoneList(int numEntries, String [ ] name, 
        String [ ] office, String [ ] phone) throws IOException {
        
        PrintWriter savePhoneInfoPW;
        
        //Save Phone List
        savePhoneInfoPW = new PrintWriter("phonelist.txt");       
        for(int cnt = 0; cnt < numEntries; cnt++) {
            
            savePhoneInfoPW.println(name [cnt]); //to file
            savePhoneInfoPW.println(office [cnt]); //to file
            savePhoneInfoPW.println(phone [cnt]); //to file
            
        }
        savePhoneInfoPW.close( );
        
    }
    
}
