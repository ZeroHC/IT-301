package driver;

/*
 * Hanchen (Zero) Liu
 * 10/19/2017
 * TestingDriver.java
 *
 * This class prompts the user for an image
 * number and uses the BinaryNumberBuilder &
 * DisplayImage classes to draw
 * the associated image to the Console
 */

import masking.BinaryNumberBuilder;
import masking.DisplayImage;

import java.util.Scanner;

/**
 * This class prompts the user for an image
 * number and uses the BinaryNumberBuilder &
 * DisplayImage classes to draw
 * the associated image to the Console
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class MaskingDriver
{
    //private constants for storing data
    private static final String FILE_NAME = "files/spaceinvaders.txt";
    private static final int TOTAL_ROWS = 8;
    private static final int TOTAL_COLUMNS = 16;

    private static final byte[] IMAGE_FILES = BinaryNumberBuilder.getInput(FILE_NAME, TOTAL_ROWS, TOTAL_COLUMNS);

    private static DisplayImage displayImage = new DisplayImage(IMAGE_FILES, TOTAL_COLUMNS);

    /**
     * This is the main method that shows images based on user's choice
     *
     * @param args command line args
     */
    public static void main(String[] args)
    {
        //scanner for user input
        Scanner userInput = new Scanner(System.in);

        //use a do-while loop to ask user for image to display
        do
        {
            System.out.print("Choose an image (1-8) or enter 0 to exit: ");
            int imgChoice = userInput.nextInt();
            userInput.nextLine();

            if (imgChoice <= 0 || imgChoice > 8) break;

            else
            {
                displayImage.showImage(imgChoice);
            }

        }while (true);

        System.out.println("\nHave a great day!");
    }
}
