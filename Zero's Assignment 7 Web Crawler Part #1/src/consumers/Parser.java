package consumers;

import queues.SharedLinkQueue;
import queues.SharedPageQueue;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser extends Thread
{
    private static ArrayList<String> keywords = new ArrayList<>();
    private static int keywordFoundCounter = 0;

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                String pageText = SharedPageQueue.getNextPage();

                Pattern pattern = Pattern.compile("href=\"(http:.*?)\"");
                Matcher matcher = pattern.matcher(pageText);

                while (matcher.find())
                {
                    String link = matcher.group(1);

                    SharedLinkQueue.addLink(link);
                }

                for (String keyword : keywords)
                {
                    String[] parts = pageText.split(keyword);
                    for (int i = 0; i < parts.length; i++)
                    {
                        keywordFoundCounter++;
                    }
                }
            }
            catch (InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addKeywords(String keyword)
    {
        keywords.add(keyword);
    }

    public static int getKeywordFoundCounter()
    {
        return keywordFoundCounter;
    }
}
