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
    private byte[] data;
    private int columns;

    /**
     * Creates a DisplayImage object with a list of bytes,
     * the number of rows of the list,
     * the number of columns of the list
     *
     * @param data list of bytes
     * @param columns number of columns of the list
     */
    public DisplayImage(byte[] data, int columns)
    {
        this.data = data;
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
        //create a mask based on user's choice
        byte mask = (byte)(0b00000001 << (choice - 1));

        //use a for loop to print out the target image
        for (int i = 0; i < data.length; i++)
        {
            //line break at every 16th index
            if (i != 0 && i % columns == 0)
            {
                System.out.print("\n");
            }

            /*
             * if the target bit is 0, prints out a space
             * else prints out a X
             */
            if ((data[i] & mask) == 0)
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