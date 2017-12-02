package part4;

import part2.CountListThread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 * Hanchen (Zero) Liu
 * 11/22/2017
 * FileMergeThreadDriver.java
 *
 * This class tests out file merge threads
 */

/**
 * This class tests out file merge threads
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class FileMergeThreadDriver
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

        //use a for loop to instantiate the list of threads
        for (int i = 0; i < listOfFiles.length; i++)
        {
            myThreads[i] = new Thread(new FileMergeThread(listOfFiles[i].getPath()));
        }

        //use a for loop to start up all threads
        for (int i = 0; i < listOfFiles.length; i++)
        {
            myThreads[i].start();
        }

        //use a for loop to wait for all threads to finish
        for (int i = 0; i < listOfFiles.length; i++)
        {
            myThreads[i].join();
        }

        try
        {
            //use a print writer to write the data in the array list to a file
            PrintWriter printWriter = new PrintWriter("lists/master_list.txt");

            //write the data in the array list one by one to the file
            for (int i = 0; i < SharedData.wordPhraseTotal(); i++)
            {
                printWriter.println(SharedData.getWordPhrase(i));
            }

            //close the file
            printWriter.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        //test if the data in the array list is written to the file
        String testFile = "lists/master_list.txt";
        CountListThread testRunnable = new CountListThread(testFile);
        Thread testThread = new Thread(testRunnable);
        testThread.start();
        testThread.join();
        System.out.println(testFile + ": " + testRunnable.getLineCount());
    }
}