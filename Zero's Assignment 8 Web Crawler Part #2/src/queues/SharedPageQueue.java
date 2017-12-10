package queues;

import org.jsoup.nodes.Document;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Hanchen (Zero) Liu
 * 12/01/2017
 * SharedPageQueue.java
 *
 * This class stores a linked list of strings, each of which has the text of a web document
 */

/**
 * This class stores a linked list of strings, each of which has the text of a web document
 *
 * @author Hanchen (Zero) Liu
 * @version 1.1
 */
public class SharedPageQueue
{
    //initialize the queue size
    private static final int MAX_QUEUE_SIZE = 50000;

    //initialize a queue for storing the web documents
    private static final Queue<Document> pageQueue = new LinkedList<>();

    //initialize a counter for counting the number of web documents
    private static int downloadedPageCounter = 0;

    /**
     * this method adds a web document to the page queue
     * @param page a web document that needs to be added to the queue
     */
    public static void addPage(Document page)
    {
        //make sure it is thread safe
        synchronized (pageQueue)
        {
            //tell the thread to wait when the queue is full
            while (pageQueue.size() == MAX_QUEUE_SIZE)
            {
                try
                {
                    pageQueue.wait();
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage());
                }
            }

            //add the web document to the queue
            pageQueue.add(page);

            downloadedPageCounter++;

            //notify thread that a new web document has been added
            pageQueue.notifyAll();
        }
    }

    /**
     * this method retrieves one web document from the page queue
     * @return a web document
     */
    public static Document getNextPage()
    {
        //make sure it is thread safe
        synchronized (pageQueue)
        {
            //tell the thread to wait when the queue is empty
            while (pageQueue.isEmpty())
            {
                try
                {
                    pageQueue.wait();
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage());
                }
            }

            //pulling one web document out from the queue
            Document page = pageQueue.poll();

            //notify the thread that a web document has been pulled out
            pageQueue.notifyAll();

            return page;
        }
    }

    /**
     * this method retrieves the numbers of web documents added to the queue
     * @return numbers of web documents added to the queue
     */
    public static int getPagesDownloaded()
    {
        //make sure it is thread safe
        synchronized (pageQueue)
        {
            return downloadedPageCounter;
        }
    }
}