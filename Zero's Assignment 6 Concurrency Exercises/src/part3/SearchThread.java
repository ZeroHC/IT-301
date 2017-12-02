package part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Hanchen (Zero) Liu
 * 11/22/2017
 * SearchThread.java
 *
 * This class creates thread that searches a word inside of a file
 */

/**
 * This class creates thread that searches a word inside of a file
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class SearchThread implements Runnable
{
    private String fileName;
    private String targetWord;
    private boolean found = false;

    /**
     * this method instantiates a search word thread
     *
     * @param fileName name of the file to search through
     * @param targetWord the word needs to be searched
     */
    public SearchThread(String fileName, String targetWord)
    {
        this.fileName = fileName;
        this.targetWord = targetWord;
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
                //reads the file line by line
                String lineReader = fileScanner.nextLine();
                //if the line contains the target word, change found to true
                if (lineReader.contains(targetWord)) found = true;
            }

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * this method retrieves the found state
     *
     * @return if the target word is found in the file or not
     */
    public boolean isFound()
    {
        return found;
    }
}