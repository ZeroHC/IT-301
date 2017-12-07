package producers;

import logger.MyLogger;
import queues.SharedLinkQueue;
import queues.SharedPageQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * Hanchen (Zero) Liu
 * 12/01/2017
 * Fetcher.java
 *
 * This class downloads web pages found on the world-wide-web
 */

/**
 * This class downloads web pages found on the world-wide-web
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class Fetcher extends Thread
{
    //initialize a counter for counting how many downloads have failed
    private static int downloadFailedCounter = 0;

    /**
     * this method pulls a link from the link queue,
     * downloads the (HTML) page text at the given URL
     * and stores the (HTML) page text on the page queue as a string
     */
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                //gets a url from the shared link queue
                URL url = new URL(SharedLinkQueue.getNextLink());

                try
                {
                    //decrypt the url
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    BufferedReader download = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuilder tempBuilder = new StringBuilder();
                    String currentLine = download.readLine();

                    while (currentLine != null)
                    {
                        tempBuilder.append(currentLine);
                        currentLine = download.readLine();
                    }

                    //stores the page text to the page queue as a string
                    SharedPageQueue.addPage(tempBuilder.toString());
                }
                catch (IOException e)
                {
                    //counts how many links failed to download
                    downloadFailedCounter++;
                    MyLogger.LOGGER.warning("Download Failure: " + e.getMessage());
                }
            }
            catch (MalformedURLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * this method retrieves the number of times a link failed to download
     * @return number of times a link failed to download
     */
    public static int getDownloadFailedCounter()
    {
        return downloadFailedCounter;
    }
}