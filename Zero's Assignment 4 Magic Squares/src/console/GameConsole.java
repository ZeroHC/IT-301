package console;

/*
 * Hanchen (Zero) Liu
 * 10/27/2017
 * GameConsole.java
 *
 * This is the game console class
 * that will present the magic square
 * game to PLAYERS
 */

import game.MagicSquare;

import java.util.Scanner;

/**
 * This is the game console class
 * that will present the magic square
 * game to PLAYERS
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class GameConsole
{
    //creating private constants to store game data
    private static final MagicSquare[] GAME_TRACKERS = {new MagicSquare(), new MagicSquare()};

    private static final String[] PLAYERS = new String[2];

    private static final short[] VICTORY_CONDITIONS = {98, 273, 140,
                                                266, 84, 161,
                                                146, 56};

    private static final short DRAW_CONDITION = 511;

    private static final Scanner USER_INPUT = new Scanner(System.in);

    /**
     * this is the main method that presents the magic square game
     *
     * @param args command line args
     */
    public static void main(String[] args)
    {
        //display the game info
        printGameInfo();

        System.out.println();

        //ask for players' names
        getPlayerName();

        System.out.println();

        //starts the game
        game();

        System.out.println("Thank you for playing!");
    }

    // prints out the game info
    private static void printGameInfo()
    {
        System.out.println("Welcome to the game of Magic Squares");
        System.out.println("***********************************");
        System.out.println("Rules:");
        System.out.println("2 PLAYERS play the game.");
        System.out.println("Each player takes turns picking a number from 1-9.");
        System.out.println("No number can be chosen twice");
        System.out.println("The first player to have 3 numbers that sum to 15 wins!");
        System.out.println("***********************************");
    }

    //asks players' names
    private static void getPlayerName()
    {
        for (int i = 0; i < PLAYERS.length; i++)
        {
            System.out.println("Enter a name for player #" + (i+1));
            PLAYERS[i] = USER_INPUT.nextLine();
            System.out.println();
        }
    }

    //starts game
    private static void game()
    {
        //use a byte variable to store player selection
        byte playerSelection;

        //create a named block for break out the loop
        master:
        {
            while (true)
            {
                for (int i = 0; i < PLAYERS.length; i++)
                {
                    //ask for player's choice
                    playerSelection = getPlayerSelection(i);

                    //check if player's choice has already been chosen
                    while (GAME_TRACKERS[0].hasAlreadyChosen(playerSelection) || GAME_TRACKERS[1].hasAlreadyChosen(playerSelection))
                    {
                        System.out.println("A player has already chosen " + playerSelection);
                        System.out.println();

                        playerSelection = getPlayerSelection(i);
                    }

                    /*
                     * if the player chosen bit is ready for set,
                     * set the chosen bit to on, then print out the game progress
                     */
                    if (GAME_TRACKERS[i].choose(playerSelection))
                    {
                        System.out.println(GAME_TRACKERS[i].printChoices());
                    }

                    //if a player wins or players are draw, stop the game
                    if (winningState(i) || playerDraw()) break master;
                }
            }
        }
    }

    //this method asks for player selection
    private static byte getPlayerSelection(int playerIndex)
    {
        byte playerSelection;
        System.out.print(PLAYERS[playerIndex] + ", please enter a number: ");
        playerSelection = USER_INPUT.nextByte();
        USER_INPUT.nextLine();

        return playerSelection;
    }

    //this method checks if any play has won the game
    private static boolean winningState(int playerIndex)
    {
        //use a for each loop to go through all the victory conditions
        for (short victoryCondition : VICTORY_CONDITIONS)
        {
            if ((GAME_TRACKERS[playerIndex].getChoices() & victoryCondition) == victoryCondition)
            {
                System.out.println("Congratulations " + PLAYERS[playerIndex] + ", you win!");
                return true;
            }
        }
        return false;
    }

    //this method checks if players are draw
    private static boolean playerDraw()
    {
        if ((GAME_TRACKERS[0].getChoices() | GAME_TRACKERS[1].getChoices()) == DRAW_CONDITION)
        {
            System.out.println("Draw! No one wins!");
            return true;
        }
        return false;
    }
}
