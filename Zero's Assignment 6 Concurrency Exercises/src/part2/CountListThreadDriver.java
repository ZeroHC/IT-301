package part2;

import java.io.File;

/*
 * Hanchen (Zero) Liu
 * 11/22/2017
 * CountListThreadDriver.java
 *
 * This class tests out count threads
 */

/**
 * This class tests out count threads
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class CountListThreadDriver
{
    /**
     * this is the main method that simulates threads
     *
     * @param args command line args
     */
    public static void main(String[] args) throws InterruptedException
    {
        //gets a list of files from the lists folder
        File folder = new File("lists/");
        File[] listOfFiles = folder.listFiles();

        //creates an array of test threads
        Thread[] myThreads = new Thread[listOfFiles.length];

        //creates an array of runnable
        CountListThread[] myRunnable = new CountListThread[listOfFiles.length];

        //use a for loop to instantiate the list of runnable
        for (int i = 0; i < listOfFiles.length; i++)
        {
            myRunnable[i] = new CountListThread(listOfFiles[i].getPath());
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
        //then prints out the number of lines of each file
        for (int i = 0; i < listOfFiles.length; i++)
        {
            myThreads[i].join();
            System.out.println(listOfFiles[i].getPath() + ": " + myRunnable[i].getLineCount());
        }
    }
}