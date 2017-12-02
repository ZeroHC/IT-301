package consumers;

import queues.SharedLinkQueue;
import queues.SharedPageQueue;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @version 1.0
 */
public class Parser extends Thread
{
    //initialize an array list to store all the keywords need to be searched
    private static ArrayList<String> keywords = new ArrayList<>();
    //initialize a counter to count how many times all keywords have been found
    private static int keywordFoundCounter = 0;

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
        while (true)
        {
             //decrypt the page text
            String pageText = SharedPageQueue.getNextPage();

            //searches for more links
            Pattern pattern = Pattern.compile("href=\"(http:.*?)\"");
            Matcher matcher = pattern.matcher(pageText);

            while (matcher.find())
            {
                String link = matcher.group(1);

                //add the link to the shared link queue
                SharedLinkQueue.addLink(link);
            }

            //look for each keywords in the page text
            for (String keyword : keywords)
            {
                String[] parts = pageText.split(keyword);

                for (int i = 0; i < parts.length; i++)
                {
                    keywordFoundCounter++;
                }
            }
        }
    }

    /**
     * this method adds a keyword to the list of keywords that need to be searched
     * @param keyword a keyword needs to be searched
     */
    public static void addKeywords(String keyword)
    {
        keywords.add(keyword);
    }

    /**
     * this method retrieves how many times all the keywords have been found
     * @return number of times that all the keywords have been found
     */
    public static int getKeywordFoundCounter()
    {
        return keywordFoundCounter;
    }
}
