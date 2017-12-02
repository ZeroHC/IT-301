package part4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Hanchen (Zero) Liu
 * 11/22/2017
 * FileMergeThread.java
 *
 * This class creates thread that adds all lines in a file to an array list
 */

/**
 * This class creates thread that adds all lines in a file to an array list
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class FileMergeThread implements Runnable
{
    private String fileName;

    /**
     * this method instantiates a file merge thread
     *
     * @param fileName name of the file to look through
     */
    public FileMergeThread(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * this method runs the thread
     */
    @Override
    public void run()
    {
        //gets the file
        File myFile = new File(fileName);

        try
        {
            //uses scanner to go through the file
            Scanner fileScanner = new Scanner(myFile);

            //check if it's the end of the file
            while (fileScanner.hasNextLine())
            {
                //adds the line to the shared data
                SharedData.add(fileScanner.nextLine());
            }

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}