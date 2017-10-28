package game;

/*
 * Hanchen (Zero) Liu
 * 10/27/2017
 * MagicSquare.java
 *
 * This is the MagicSquare class
 * that is used for update and display
 * the magic square game
 */

/**
 * This is the MagicSquare class
 * that is used for update and display
 * the magic square game
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class MagicSquare
{
    private short choices = 0;

    /**
     * instantiate a magic square object
     */
    public MagicSquare() {}

    /**
     * this method sets the requested bit to the "on" position.
     *
     * @param selection player's selection
     * @return if the position is ready for set
     */
    public boolean choose(byte selection)
    {
        //create a mask based on user's selection
        short mask = (short) (1 << (selection - 1));

        if (selection > 9 || selection < 1 || (choices & mask) != 0) return false;
        else
        {
            choices = (short)(choices | mask);
            return true;
        }
    }

    /**
     * this method checks if the requested bit has been chosen or not
     *
     * @param selection player's selection
     * @return if the requested bit has already chosen
     */
    public boolean hasAlreadyChosen(byte selection)
    {
        //create a mask based on user's selection
        short mask = (short) (1 << (selection - 1));

        return ((choices & mask) != 0);
    }

    /**
     * this method gets the current player's choices
     *
     * @return player's choices
     */
    public short getChoices()
    {
        return choices;
    }

    /**
     * this method displays the game progress
     *
     * @return the game board
     */
    public String printChoices()
    {
        byte[] gameElements = {2, 7, 6,
                                9, 5, 1,
                                4, 3, 8};

        StringBuilder gameBoard = new StringBuilder();

        for (int i = 0; i < gameElements.length; i++)
        {
            if (i!= 0 && i % 3 == 0) gameBoard.append("\n");
            if (hasAlreadyChosen(gameElements[i]))
            {
                gameBoard.append(gameElements[i]);
            }
            else
            {
                gameBoard.append("_");
            }
        }

        return gameBoard.append("\n").toString();
    }
}