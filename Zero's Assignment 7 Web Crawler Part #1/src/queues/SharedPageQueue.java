package queues;

import java.util.LinkedList;
import java.util.Queue;

public class SharedPageQueue
{
    private static final int MAX_QUEUE_SIZE = 50000;
    private static final Queue<String> pageQueue = new LinkedList<>();
    private static int downloadedPageCounter = 0;

    public static void addPage(String pageText) throws InterruptedException
    {
        synchronized (pageQueue)
        {
            while (pageQueue.size() == MAX_QUEUE_SIZE)
            {
                pageQueue.wait();
            }

            pageQueue.add(pageText);

            downloadedPageCounter++;

            pageQueue.notifyAll();
        }
    }

    public static String getNextPage() throws InterruptedException
    {
        synchronized (pageQueue)
        {
            while (pageQueue.isEmpty())
            {
                pageQueue.wait();
            }

            String page = pageQueue.poll();

            pageQueue.notifyAll();

            return page;
        }
    }

    public static int getPagesDownloaded()
    {
        synchronized (pageQueue)
        {
            return downloadedPageCounter;
        }
    }
}