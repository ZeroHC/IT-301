package producers;

import logger.MyLogger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import queues.SharedLinkQueue;
import queues.SharedPageQueue;
import java.io.IOException;

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
 * @version 1.1
 */
public class Fetcher extends Thread
{
    //initialize a counter for counting how many downloads have failed
    private static int downloadFailedCounter = 0;

    //initialize a flag for stopping thread
    private volatile boolean done = false;

    /**
     * this method pulls a link from the link queue,
     * downloads the (HTML) page at the given URL
     * and stores the (HTML) page on the page queue
     */
    @Override
    public void run()
    {
        while (!done)
        {
            //gets a url from the shared link queue
            String url = SharedLinkQueue.getNextLink();

            try
            {
                Document page = Jsoup.connect(url).get();

                SharedPageQueue.addPage(page);
            }
            catch (IOException | IllegalArgumentException e)
            {
                //counts how many links failed to download
                downloadFailedCounter++;
                MyLogger.LOGGER.warning("Download Failure: " + e.getMessage());
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

    /**
     * this method stops a thread from running
     */
    public void stopThread()
    {
        done = true;
    }
}