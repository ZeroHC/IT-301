package consumers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import queues.SharedLinkQueue;
import queues.SharedPageQueue;

import java.util.HashMap;

/*
 * Hanchen (Zero) Liu
 * 12/01/2017
 * Parser.java
 *
 * This class analyze each page found
 */

/**
 * This class analyze each page found
 *
 * @author Hanchen (Zero) Liu
 * @version 1.1
 */
public class Parser extends Thread
{
    //initialize an hash map to store all the keywords need to be searched as well as how many times it's been found
    private static HashMap<String, Integer> keywordAndCounter = new HashMap<>();

    //initialize a counter to count how many times all keywords have been found
    private static int keywordFoundCounter = 0;

    //initialize a flag for stopping thread
    private volatile boolean done = false;

    /**
     * this method pulls a page from the page queue,
     * searches the page for all links in anchor (<a href="">) elements,
     * adds each link found to the link queue,
     * searches the page for any keywords specified by the user of the web crawler (more on this later)
     * and keeps track of how many keywords are encountered
     */
    @Override
    public void run()
    {
        while (!done)
        {
            //get a page from the shared page queue
            Document page = SharedPageQueue.getNextPage();

            //finds all links
            Elements links = page.select("a[href]");

            //add all found links to the shared link queue
            for (Element link : links)
            {
                String url = link.absUrl("href");
                SharedLinkQueue.addLink(url);
            }

            //look for each keywords in the page text
            for (String keyword : keywordAndCounter.keySet())
            {
                String[] parts = page.text().split(keyword);

                int keywordCounter = keywordAndCounter.get(keyword) + parts.length;

                //updates the value
                keywordAndCounter.put(keyword, keywordCounter);

                keywordFoundCounter += parts.length;
            }
        }
    }

    /**
     * this method adds a keyword to the set of keywords that need to be searched
     * @param keyword a keyword needs to be searched
     */
    public static void addKeywords(String keyword)
    {
        keywordAndCounter.put(keyword, 0);
    }

    /**
     * this method retrieves how many times all the keywords have been found
     * @return number of times that all the keywords have been found
     */
    public static int getKeywordFoundCounter()
    {
        return keywordFoundCounter;
    }

    /**
     * this method stops a thread from running
     */
    public void stopThread()
    {
        done = true;
    }

    /**
     * this method retrieves a hash map that contains keywords and how many times its been found
     * @return keywords and counter set
     */
    public static HashMap<String, Integer> getKeywordAndCounter()
    {
        return keywordAndCounter;
    }
}
