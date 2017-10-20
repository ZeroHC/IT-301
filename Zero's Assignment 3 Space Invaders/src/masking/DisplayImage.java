package masking;

/*
 * Hanchen (Zero) Liu
 * 10/19/2017
 * DisplayImage.java
 *
 * This class works together with the
 * BinaryNumberBuilder class to display
 * an image from the input file
 */

import static driver.MaskingDriver.IMAGE_FILES;

/**
 * This class works together with the
 * BinaryNumberBuilder class to display
 * an image from the input file
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class DisplayImage
{
    /*
     * this array holds each byte as it was read from the
     * file using BinaryNumberBuilder. This byte array
     * should be passed into the DisplayImage object using
     * the constructor
     */
    private static byte[] data;
    private static int rows;
    private static int columns;

    public DisplayImage(byte[] data, int rows, int columns)
    {
        this.data = data;
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * This method returns the image specified by 'choice',
     * where choice represents one of the eight possible images (1-8)
     *
     * @param choice users choice of image
     */
    public void showImage(int choice)
    {
        for (int i = 0; i < data.length; i++)
        {
            if (i != 0 && i % columns == 0)
            {
                System.out.print("\n");
            }
            if (data[i] == 0)
            {
                System.out.print(" ");
            }
            else
            {
                System.out.print("X");
            }
        }

        System.out.println("\n");
    }

}