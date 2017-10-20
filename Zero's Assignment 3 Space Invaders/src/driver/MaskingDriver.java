package driver;

/*
 * Hanchen (Zero) Liu
 * 10/19/2017
 * MaskingDriver.java
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
    private static final String FILE_NAME = "files/spaceinvaders.txt";
    private static final int TOTAL_ROWS = 64;
    private static final int TOTAL_COLUMNS = 16;
    private static final int IMAGE_ROWS = 8;
    private static final int IMAGE_COLUMNS = 16;

    public static final byte[] IMAGE_FILES = BinaryNumberBuilder.getInput(FILE_NAME, TOTAL_ROWS, TOTAL_COLUMNS);

    private static DisplayImage displayImage;

    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);

        int imgChoice;

        do
        {
            System.out.print("Choose an image (1-8) or enter 0 to exit: ");
            imgChoice = userInput.nextInt();
            userInput.nextLine();

            if (imgChoice <= 0 || imgChoice > 8) break;

            else
            {
                setImageBoundary(imgChoice);

                displayImage.showImage(imgChoice);
            }

        }while (imgChoice > 0 && imgChoice <= 8);

        System.out.println("\nHave a great day!");
    }

    private static void setImageBoundary(int choice)
    {
        int startingRow = 0;
        int endingRow = 0;

        switch (choice)
        {
            case 1: startingRow = 0; endingRow = 7;
                break;
            case 2: startingRow = 8; endingRow = 15;
                break;
            case 3: startingRow = 16; endingRow = 23;
                break;
            case 4: startingRow = 24; endingRow = 31;
                break;
            case 5: startingRow = 32; endingRow = 39;
                break;
            case 6: startingRow = 40; endingRow = 47;
                break;
            case 7: startingRow = 48; endingRow = 55;
                break;
            case 8: startingRow = 56; endingRow = 63;
                break;
        }

        getImage(startingRow, endingRow, choice);
    }

    private static void getImage(int startingRow, int endingRow, int choice) throws ArrayIndexOutOfBoundsException
    {
        byte[] tempData = new byte[(int)Math.pow(2, (endingRow - startingRow))];

        for (int i = 0; i < Math.pow(2, (endingRow - startingRow)); i++)
        {
            tempData[i] = IMAGE_FILES[(int)Math.pow(2, (endingRow - startingRow)) * (choice - 1) + i];
        }

        displayImage = new DisplayImage(tempData, IMAGE_ROWS, IMAGE_COLUMNS);
    }
}
