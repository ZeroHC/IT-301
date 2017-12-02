package part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Hanchen (Zero) Liu
 * 11/22/2017
 * CountListThread.java
 *
 * This class creates thread that counts number of lines in a file
 */

/**
 * This class creates thread that counts number of lines in a file
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class CountListThread implements Runnable
{
    private String fileName;
    private int lineCount;

    /**
     * this method instantiates a count lines thread
     *
     * @param fileName name of the file that is going to be looked through
     */
    public CountListThread(String fileName)
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
            while (fileScanner.hasNext())
            {
                //update the line
                fileScanner.nextLine();
                //update the line count
                lineCount++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * this method retrieves the number of lines for the file
     *
     * @return number of lines
     */
    public int getLineCount()
    {
        return lineCount;
    }
}