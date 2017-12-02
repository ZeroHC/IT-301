package queues;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Hanchen (Zero) Liu
 * 12/01/2017
 * SharedLinkQueue.java
 *
 * This class stores a linked list of strings, each of which has a URL for a web document
 */

/**
 * This class stores a linked list of strings, each of which has a URL for a web document
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class SharedLinkQueue
{
    //initialize the queue size
    private static final int MAX_QUEUE_SIZE = 50000;

    //initialize a queue for storing the urls
    private static final Queue<String> linkQueue = new LinkedList<>();

    //initialize a counter for each unique link found
    private static int foundLinkCounter = 0;

    /**
     * this method adds a link to the link queue
     * @param url a link that needs to be added to the queue
     */
    public static void addLink(String url)
    {
        //make sure it is thread safe
        synchronized (linkQueue)
        {
            //tell thread to wait when the queue is full
            while (linkQueue.size() == MAX_QUEUE_SIZE)
            {
                try
                {
                    linkQueue.wait();
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage());
                }
            }

            //count every unique link
            if (!linkQueue.contains(url))
            {
                foundLinkCounter++;
            }

            //add the url to the queue
            linkQueue.add(url);

            //notify the thread that a url has been added to the queue
            linkQueue.notifyAll();
        }
    }

    /**
     * this method retrieves one link from the link queue
     * @return a url
     */
    public static String getNextLink()
    {
        //make sure it is thread safe
        synchronized (linkQueue)
        {
            //tell the thread to wait when the queue is empty
            while (linkQueue.isEmpty())
            {
                try
                {
                    linkQueue.wait();
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage());
                }
            }

            //pulling one url out from the queue
            String url = linkQueue.poll();

            //notify the thread that a url has been pulled out
            linkQueue.notifyAll();

            return url;
        }
    }

    /**
     * this method retrieve the numbers of unique links added to the queue
     * @return numbers of unique links added to the queue
     */
    public static int getLinksFound()
    {
        //make sure it is thread safe
        synchronized (linkQueue)
        {
            return foundLinkCounter;
        }
    }
}