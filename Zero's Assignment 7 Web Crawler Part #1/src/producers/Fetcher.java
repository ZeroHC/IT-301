package producers;

import queues.SharedLinkQueue;
import queues.SharedPageQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Fetcher extends Thread
{
    private static int downloadFailedCounter;

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                URL url = new URL(SharedLinkQueue.getNextLink());

                try
                {
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    BufferedReader download = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuilder tempBuilder = new StringBuilder();
                    String currentLine = download.readLine();

                    while (currentLine != null)
                    {
                        tempBuilder.append(currentLine);
                        currentLine = download.readLine();
                    }

                    SharedPageQueue.addPage(tempBuilder.toString());
                }
                catch (IOException e)
                {
                    downloadFailedCounter++;
                }
            }
            catch (MalformedURLException | InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getDownloadFailedCounter()
    {
        return downloadFailedCounter;
    }
}