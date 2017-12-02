package part4;

import java.util.ArrayList;

/*
 * Hanchen (Zero) Liu
 * 11/22/2017
 * SharedData.java
 *
 * This class adds string to an array list,
 * as well as check the size of the array list and
 * get the string in a specific index
 */

/**
 * This class adds string to an array list,
 * as well as check the size of the array list and
 * get the string in a specific index
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class SharedData
{
    //create a static array list field here...
    private static ArrayList<String> master = new ArrayList<>();

    /**
     * this method uses synchronized key word to make the thread add a string to an array list safely
     *
     * @param word a word to be added to the array list
     */
    public static void add(String word)
    {
        //add word to the array list
        synchronized (SharedData.class)
        {
            master.add(word);
        }
    }

    /**
     * this method uses synchronized key word to make the thread retrieve the array list size safely
     *
     * @return the number of elements in the array list
     */
    public static int wordPhraseTotal()
    {
        //return the number of elements in the array list
        synchronized (SharedData.class)
        {
            return master.size();
        }
    }

    /**
     * this method uses synchronized key word to make the thread get the string in target position safely
     *
     * @return the elements at index position in the array list
     */
    public static String getWordPhrase(int position)
    {
        //returns the element at index "position" in the array list
        synchronized (SharedData.class)
        {
            return master.get(position);
        }
    }
}