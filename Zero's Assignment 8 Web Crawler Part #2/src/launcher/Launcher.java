package launcher;

import consumers.Parser;
import logger.MyLogger;
import producers.Fetcher;
import queues.SharedLinkQueue;
import queues.SharedPageQueue;

import java.util.*;

/*
 * Hanchen (Zero) Liu
 * 12/01/2017
 * Launcher.java
 *
 * This class is the test class for web crawler
 */

/**
 * This class is the test class for web crawler
 *
 * @author Hanchen (Zero) Liu
 * @version 1.1
 */
public class Launcher
{
    //initialize a scanner for global use
    private static Scanner inputScanner = new Scanner(System.in);

    //initialize an array list to store all the producers created
    private static ArrayList<Fetcher> producers = new ArrayList<>();

    //initialize an array list to store all the consumers created
    private static ArrayList<Parser> consumers = new ArrayList<>();

    /**
     * this is the main method that simulates threads
     *
     * @param args command line args
     */
    public static void main(String[] args)
    {
        MyLogger.setup();

        int userChoice;

        //display the menu after each action
        while (true)
        {
            System.out.println("1. Add seed url");
            System.out.println("2. Add consumer");
            System.out.println("3. Add producer");
            System.out.println("4. Add keyword search");
            System.out.println("5. Print stats");
            System.out.println("6. Informational Thread");
            System.out.println("0. Quit program");

            //get user's choice
            userChoice = inputScanner.nextInt();
            inputScanner.nextLine();

            //base on user choice do different things
            switch (userChoice)
            {
                case 1: addUrl();
                    break;

                case 2: addConsumer();
                    break;

                case 3: addProducer();
                    break;

                case 4: addKeyword();
                    break;

                case 5: printStats();
                    break;

                case 6: createInfoThread();
                    break;

                case 0: threadStopper();
                    return;
            }
        }
    }

    //this method adds a seed url to the shared link queue
    private static void addUrl()
    {
        System.out.print("\nURL: ");
        String url = inputScanner.nextLine();
        SharedLinkQueue.addLink(url);
        MyLogger.LOGGER.warning(url + " has been added as a seed URL");
        System.out.println();
    }

    //this method creates a new consumer thread and starts it
    private static void addConsumer()
    {
        Parser tempConsumer = new Parser();
        tempConsumer.start();
        consumers.add(tempConsumer);
        tempConsumer.setName("Parser #" + consumers.size());
        MyLogger.LOGGER.warning(tempConsumer.getName() + " has started");
        System.out.println();
    }

    //this method creates a new producer thread and starts it
    private static void addProducer()
    {
        Fetcher tempProducer = new Fetcher();
        tempProducer.start();
        producers.add(tempProducer);
        tempProducer.setName("Fetcher #" + producers.size());
        MyLogger.LOGGER.warning(tempProducer.getName() + " has started");
        System.out.println();
    }

    //this method adds a keyword to the list of keyword that needs to be searched
    private static void addKeyword()
    {
        System.out.print("\nKeyword: ");
        Parser.addKeywords(inputScanner.nextLine());
        System.out.println();
    }

    //this methods prints out stats of the web crawler program
    private static void printStats()
    {
        System.out.println();
        System.out.println("Keywords found: " + Parser.getKeywordFoundCounter());
        System.out.println();
        System.out.println("Individual keyword counts:");
        printIndividualCounts();
        System.out.println();
        System.out.println("Links found: " + SharedLinkQueue.getLinksFound());
        System.out.println("Pages found: " + SharedPageQueue.getPagesDownloaded());
        System.out.println("Failed downloads: " + Fetcher.getDownloadFailedCounter());
        System.out.println("Producers: " + producers.size());
        System.out.println("Consumers: " + consumers.size());
        System.out.println();
    }

    //this method creates a informational thread that repeatedly prints the stats but with a 5 second delay
    private static void createInfoThread()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        printStats();
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });

        thread.start();
    }

    //this method stops all the threads that are running
    private static void threadStopper()
    {
        if (producers.size() == 0 || consumers.size() == 0)
        {
            return;
        }
        else
        {
            for (Fetcher tempFetcher : producers)
            {
                tempFetcher.stopThread();
                MyLogger.LOGGER.warning(tempFetcher.getName() + " has been stopped!");
            }
            for (Parser tempParser : consumers)
            {
                tempParser.stopThread();
                MyLogger.LOGGER.warning(tempParser.getName() + " has been stopped!");
            }
        }
    }

    //this method prints out counts for each individual keywords
    private static void printIndividualCounts()
    {
        // Get a set of the entries
        Set set = Parser.getKeywordAndCounter().entrySet();

        // Get an iterator
        Iterator iterator = set.iterator();

        // Display elements
        while(iterator.hasNext())
        {
            Map.Entry mapEntry = (Map.Entry)iterator.next();
            System.out.print(mapEntry.getKey() + ": ");
            System.out.println(mapEntry.getValue());
        }
    }
}