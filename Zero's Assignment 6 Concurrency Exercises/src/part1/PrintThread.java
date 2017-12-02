package part1;

import java.io.File;

/*
 * Hanchen (Zero) Liu
 * 11/22/2017
 * PrintThread.java
 *
 * This class creates a print thread
 */

/**
 * This class creates a print thread
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class PrintThread implements Runnable
{
    private String filePath;
    private File[] listOfFiles;

    /**
     * this method instantiates a print thread
     *
     * @param filePath file path for finding files
     */
    public PrintThread(String filePath)
    {
        this.filePath = filePath;
        listOfFiles = new File(filePath).listFiles();
    }

    /**
     * this method runs the thread
     */
    @Override
    public void run()
    {
        //use a for each loop to get all files
        for (File myFile : listOfFiles)
        {
            //prints out all files one by one
            System.out.println("Found file: " + myFile.getName());
        }
    }
}