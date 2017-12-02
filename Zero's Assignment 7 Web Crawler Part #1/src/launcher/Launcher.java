package launcher;

import consumers.Parser;
import producers.Fetcher;
import queues.SharedLinkQueue;
import queues.SharedPageQueue;

import java.util.Scanner;

public class Launcher
{
    private static Scanner inputScanner = new Scanner(System.in);

    private static int producerCounter = 0;
    private static int consumerCounter = 0;

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("1. Add seed url");
            System.out.println("2. Add consumer");
            System.out.println("3. Add producer");
            System.out.println("4. Add keyword search");
            System.out.println("5. Print stats");
            System.out.println("0. Quit program");

            int userChoice = inputScanner.nextInt();
            inputScanner.nextLine();

            switch (userChoice)
            {
                case 1:
                    addUrl();
                    break;

                case 2:
                    addConsumer();
                    break;

                case 3:
                    addProducer();
                    break;

                case 4:
                    addKeyword();
                    break;

                case 5:
                    printStats();
                    break;

                case 0:
                    return;
            }
        }
    }

    private static void addUrl()
    {
        try
        {
            System.out.print("\nURL: ");
            SharedLinkQueue.addLink(inputScanner.nextLine());
            System.out.println();
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void addConsumer()
    {
        Parser tempConsumer = new Parser();
        tempConsumer.start();
        consumerCounter++;
        System.out.println();
    }

    private static void addProducer()
    {
        Fetcher tempProducer = new Fetcher();
        tempProducer.start();
        producerCounter++;
        System.out.println();
    }

    private static void addKeyword()
    {
        System.out.print("\nKeyword: ");
        Parser.addKeywords(inputScanner.nextLine());
        System.out.println();
    }

    private static void printStats()
    {
        System.out.println();
        System.out.println("Keywords found: " + Parser.getKeywordFoundCounter());
        System.out.println("Links found: " + SharedLinkQueue.getLinksFound());
        System.out.println("Pages found: " + SharedPageQueue.getPagesDownloaded());
        System.out.println("Failed downloads: " + Fetcher.getDownloadFailedCounter());
        System.out.println("Producers: " + producerCounter);
        System.out.println("Consumers: " + consumerCounter);
        System.out.println();
    }
}