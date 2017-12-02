package queues;

import java.util.LinkedList;
import java.util.Queue;

public class SharedLinkQueue
{
    private static final int MAX_QUEUE_SIZE = 50000;
    private static final Queue<String> linkQueue = new LinkedList<>();
    private static int foundLinkCounter = 0;

    public static void addLink(String url) throws InterruptedException
    {
        synchronized (linkQueue)
        {
            while (linkQueue.size() == MAX_QUEUE_SIZE)
            {
                linkQueue.wait();
            }

            if (!linkQueue.contains(url))
            {
                foundLinkCounter++;
            }

            linkQueue.add(url);

            linkQueue.notifyAll();
        }
    }

    public static String getNextLink() throws InterruptedException
    {
        synchronized (linkQueue)
        {
            while (linkQueue.isEmpty())
            {
                linkQueue.wait();
            }

            String url = linkQueue.poll();

            linkQueue.notifyAll();

            return url;
        }
    }

    public static int getLinksFound()
    {
        synchronized (linkQueue)
        {
            return foundLinkCounter;
        }
    }
}