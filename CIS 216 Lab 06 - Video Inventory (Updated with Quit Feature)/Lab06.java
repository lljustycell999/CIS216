package lab.pkg06;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday October 22, 2021
//
//  Project Name:   Laboratory 06   
//
//  Program Description: This program will allow a user to keep track of their 
//  "video" inventory. The user can choose to add videos, search for one 
//  specific video, display listings of videos, or quit the program. When 
//  adding a video, the program will ask for the video title, classification,
//  rating, and format to append to the file. When searching for a video, the 
//  program will display video information on the console after getting a video
//  title from the user. When generating listings, the user can choose to
//  display all videos in general, in a specifc genre, or in a
//  specific rating. The program will continue to ask and perform user choices 
//  until "Quit" is chosen. 
// 
//  Project Number:  Project 06

public class Lab06 {

    public static void main(String[] args) throws IOException {
        
        int userChoice;
        
        String movieName;
        String movieGenre;
        String movieRating;
        String movieFormat;
        
        FileWriter movieFileFW;
        PrintWriter movieFilePW;
        
        String searchMovieName;
        
        File movieFile;
        Scanner movieFileSC;
        
        String fileMovieName;
        String fileMovieGenre;
        String fileMovieRating;
        String fileMovieFormat;
        
        String userMovieGenre;
        
        String userMovieRating;
        
        Scanner kbd;
        
        //Establish Keyboard Availability
        kbd = new Scanner(System.in);
        
        userChoice = 0; 
        while(userChoice != 6) {
            
            //Get Choice
            userChoice = 7;
            while(userChoice != 1 && userChoice != 2 && userChoice != 3 && 
                userChoice != 4 && userChoice != 5 && userChoice != 6) {
                
                System.out.print("What would you like to do: Add a video [1], "
                    + "Search for a video [2], Display all videos [3], Display "
                        + "videos of a specific genre [4], Display videos of "
                            + "a specific rating [5], or Quit [6]?  ");
                                //to user
                userChoice = kbd.nextInt( ); //from user
                System.out.println( );
                kbd.nextLine( );
        
            }
                
            if(userChoice == 1) {
            
                //Get Video Information
                System.out.print("Enter movie name:  "); //to user
                movieName = kbd.nextLine( ); //from user
                System.out.print("Enter movie genre:  "); //to user
                movieGenre = kbd.nextLine( ); //from user
                System.out.print("Enter movie rating:  "); //to user
                movieRating = kbd.nextLine( ); //from user
                System.out.print("Enter movie format:  "); //to user
                movieFormat = kbd.nextLine( ); //from user
            
                //Append Video Information to File
                movieFileFW = new FileWriter("movies.txt", true);
                movieFilePW = new PrintWriter(movieFileFW);
                movieFilePW.println(movieName); //to file
                movieFilePW.println(movieGenre); //to file
                movieFilePW.println(movieRating); //to file
                movieFilePW.println(movieFormat); //to file
                movieFilePW.close( );
                System.out.println( );
                
            }
                        
            if(userChoice == 2) {
            
                //Get Movie Title
                System.out.print("Enter the name of the movie you are "
                    + "looking for:  "); //to user
                searchMovieName = kbd.nextLine( ); //from user
                System.out.println( );
                movieFile = new File("movies.txt");
                movieFileSC = new Scanner(movieFile);
                while(movieFileSC.hasNext( )) {
                    
                    //Get Movie Information from File
                    fileMovieName = movieFileSC.nextLine( ); //from file
                    fileMovieGenre = movieFileSC.nextLine( ); //from file
                    fileMovieRating = movieFileSC.nextLine( ); //from file
                    fileMovieFormat = movieFileSC.nextLine( ); //from file

                    //Display Video Summary
                    if(fileMovieName.compareToIgnoreCase(searchMovieName) == 
                        0) {          
                        System.out.println("Movie Found:  " + fileMovieName); 
                            //to user
                        System.out.println("Movie Genre:  " + fileMovieGenre);
                            //to user
                        System.out.println("Movie Rating:  " + fileMovieRating);
                            //to user
                        System.out.println("Movie Format:  " + fileMovieFormat);
                            //to user

                    }

                }
                movieFileSC.close( );
                System.out.println( );
                
            }
            
            if(userChoice == 3) {

                //Display Report Headings
                System.out.printf("%2s%80s%20s%20s\n", "Movie Name",
                    "Movie Genre", "Movie Rating", "Movie Format"); //to user
                movieFile = new File("movies.txt");
                movieFileSC = new Scanner(movieFile);
                while(movieFileSC.hasNext( )) {

                    //Get Movie Information from File
                    fileMovieName = movieFileSC.nextLine( ); //from file
                    fileMovieGenre = movieFileSC.nextLine( ); //from file
                    fileMovieRating = movieFileSC.nextLine( ); //from file
                    fileMovieFormat = movieFileSC.nextLine( ); //from file

                    //Display Summary of All Movies
                    System.out.printf("%-79s%-19s%-20s%-19s\n", fileMovieName,
                        fileMovieGenre, fileMovieRating, fileMovieFormat);
                            //to user

                }
                movieFileSC.close( );
                System.out.println( );

            }

            if(userChoice == 4) {

                //Get Genre
                userMovieGenre = "Wrong answer";
                while(userMovieGenre.compareToIgnoreCase("Romance") != 0 && 
                    userMovieGenre.compareToIgnoreCase("Horror") != 0 && 
                        userMovieGenre.compareToIgnoreCase("Action") != 0 && 
                            userMovieGenre.compareToIgnoreCase("Science "
                                + "fiction") != 0 && 
                                    userMovieGenre.compareToIgnoreCase("Documen"
                                        + "tary") != 0) { 
                    
                    System.out.print("Enter desired movie genre: Romance, "
                        + "Horror, Action, Science fiction, or Documentary?  "); 
                            //to user
                    userMovieGenre = kbd.nextLine( ); //from user
                    System.out.println( );
                
                }
                
                //Display Report Headings
                System.out.printf("%2s%80s%20s%20s\n", "Movie Name",
                    "Movie Genre", "Movie Rating", "Movie Format"); //to user
                movieFile = new File("movies.txt");
                movieFileSC = new Scanner(movieFile);
                while(movieFileSC.hasNext( )) {

                    //Get Movie Information from File
                    fileMovieName = movieFileSC.nextLine( ); //from file
                    fileMovieGenre = movieFileSC.nextLine( ); //from file
                    fileMovieRating = movieFileSC.nextLine( ); //from file
                    fileMovieFormat = movieFileSC.nextLine( ); //from file       

                    //Display Video Information that Matches Genre 
                    if(fileMovieGenre.compareToIgnoreCase(userMovieGenre) == 0) 
                        System.out.printf("%-79s%-19s%-20s%-19s\n", 
                            fileMovieName, fileMovieGenre, fileMovieRating, 
                                fileMovieFormat); //to user
                                
                }
                movieFileSC.close( );
                System.out.println( );

            }

            if(userChoice == 5) {

                //Get Rating
                userMovieRating = "Another wrong answer";
                while(userMovieRating.compareToIgnoreCase("NR") != 0 && 
                    userMovieRating.compareToIgnoreCase("G") != 0 && 
                        userMovieRating.compareToIgnoreCase("PG") != 0 && 
                            userMovieRating.compareToIgnoreCase("PG-13") != 0 
                                && userMovieRating.compareToIgnoreCase("R") != 
                                    0 && userMovieRating.compareToIgnoreCase("N"
                                        + "C-17") != 0) {
                    
                    System.out.print("Enter desired movie rating: Not rated "
                        + "[NR], G, PG, PG-13, R, or NC-17?  "); //to user
                    userMovieRating = kbd.nextLine( ); //from user
                    System.out.println( );
                
                }
                
                //Display Report Headings
                System.out.printf("%2s%80s%20s%20s\n", "Movie Name",
                    "Movie Genre", "Movie Rating", "Movie Format"); //to user    
                movieFile = new File("movies.txt");
                movieFileSC = new Scanner(movieFile);
                while(movieFileSC.hasNext( )) {

                    //Get Movie Information from File
                    fileMovieName = movieFileSC.nextLine( ); //from file 
                    fileMovieGenre = movieFileSC.nextLine( ); //from file
                    fileMovieRating = movieFileSC.nextLine( ); //from file
                    fileMovieFormat = movieFileSC.nextLine( ); //from file

                    //Display Video Information that Matches Rating
                    if(fileMovieRating.compareToIgnoreCase(userMovieRating) 
                        == 0)
                        System.out.printf("%-79s%-19s%-20s%-19s\n",
                            fileMovieName, fileMovieGenre, fileMovieRating, 
                                fileMovieFormat); //to user
                                
                }
                movieFileSC.close( );
                System.out.println( );

            }

        }
        System.exit(0);
        
    }   //END main
    
}   //END Movie class
