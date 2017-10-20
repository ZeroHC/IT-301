package masking;

import java.io.File;
import java.util.Scanner;

/**
 * Reads a file containing image data formatted as binary strings.
 * @author Janet Hollier
 * @version 1.0
 */
public class BinaryNumberBuilder
{
	/**
	 * Read images file in and generates a byte array
	 * 
	 * @param filename the file to read and analyze
	 * @param rows the number of rows in the file
	 * @param cols the number of columns in the file
	 */
	public static byte[] getInput(String filename, int rows, int cols)
	{
		// create a byte array to hold numbers
		byte[] numbers = new byte[rows * cols];

		// open the input file that contains 1s & 0s
		try (Scanner input = new Scanner(new File(filename)))
		{
			int index = 0;

			// start bit mask at 1 (for first image)
			byte mask = 0b00000001;
			while (input.hasNext())
			{
				if (input.hasNextInt())
				{
					int value = input.nextInt();

					// if input is 1, add the mask to the corresponding position in the byte array
					if (value == 1)
					{
						numbers[index] = (byte) (numbers[index] | mask);
					}

					// if index pointer has reached the end of the byte array update the mask and 
					// reset the index
					if (++index % (numbers.length) == 0)
					{
						mask = (byte) (mask << 1);
						index = 0;
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		// return the byte array containing consolidated mask numbers
		return numbers;
	}
}
