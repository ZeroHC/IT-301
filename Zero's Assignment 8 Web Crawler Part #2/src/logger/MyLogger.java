package logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger
{
    //create a logger for tracing statements
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void setup()
    {
        //set up a log file for tracing statements
        try
        {
            FileHandler logHandler = new FileHandler("webcrawler.log");
            logHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(logHandler);
            LOGGER.setUseParentHandlers(false);
        }
        catch (IOException e)
        {
            System.out.println("Error setting up log file.");
        }
    }
}