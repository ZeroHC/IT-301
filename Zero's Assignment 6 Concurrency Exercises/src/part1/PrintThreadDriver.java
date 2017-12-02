package part1;

/*
 * Hanchen (Zero) Liu
 * 11/22/2017
 * PrintThreadDriver.java
 *
 * This class tests out a print thread
 */

/**
 * This class tests out a print thread
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class PrintThreadDriver
{
    /**
     * this is the main method that simulates threads
     *
     * @param args command line args
     */
    public static void main(String[] args) throws InterruptedException
    {
        //create a test thread that uses a runnable interface
        Thread myThread = new Thread(new PrintThread("lists/"));

        //main thread print
        System.out.println("Analyzing lists director...");

        //starts the custom created thread
        myThread.start();
        //tell the main thread to wait for custom threads to finish
        myThread.join();

        //main thread print
        System.out.println("Done analyzing lists directory...");
    }
}