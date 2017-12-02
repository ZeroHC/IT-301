package part3;

import java.io.File;
import java.util.Scanner;

/*
 * Hanchen (Zero) Liu
 * 11/22/2017
 * SearchThreadDriver.java
 *
 * This class tests out search threads
 */

/**
 * This class tests out search threads
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class SearchThreadDriver
{
    /**
     * this is the main method that simulates threads
     *
     * @param args command line args
     */
    public static void main(String[] args) throws InterruptedException
    {
        //use a scanner to catch user input
        Scanner console = new Scanner(System.in);

        //asks a user of a word to search for
        System.out.print("Enter a word to search for: ");
        String userInput = console.next();

        //gets a list of files from the lists folder
        File folder = new File("lists/");
        File[] listOfFiles = folder.listFiles();

        //creates an array of test threads
        Thread[] myThreads = new Thread[listOfFiles.length];

        //creates an array of runnable
        SearchThread[] myRunnable = new SearchThread[listOfFiles.length];

        //use a for loop to instantiate the list of runnable
        for (int i = 0; i < listOfFiles.length; i++)
        {
            myRunnable[i] = new SearchThread(listOfFiles[i].getPath(), userInput);
        }

        //use a for loop to instantiate the list of threads
        for (int i = 0; i < listOfFiles.length; i++)
        {
            myThreads[i] = new Thread(myRunnable[i]);
        }

        //use a for loop to start up all threads
        for (int i = 0; i < listOfFiles.length; i++)
        {
            myThreads[i].start();
        }

        //use a for loop to wait for all threads to finish
        //then prints out which file is the user input word found
        for (int i = 0; i < listOfFiles.length; i++)
        {
            myThreads[i].join();
            if (myRunnable[i].isFound())
            {
                System.out.println("\"" + userInput + "\" found in " + listOfFiles[i].getPath());
            }
        }
    }
}