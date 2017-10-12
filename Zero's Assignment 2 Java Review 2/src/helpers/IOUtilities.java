package helpers;

import questions.Question;

import java.io.*;
import java.util.ArrayList;

/*
 * Hanchen (Zero) Liu
 * 10/11/2017
 * IOUtilities.java
 * This is the IOUtilities helper class that helps the program
 * to write to a file and read from a file
 */

/**
 * This is the IOUtilities helper class that helps the program
 * to write to a file and read from a file
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class IOUtilities
{
    /**
     * This method writes all the questions from the question array list
     * to a file
     *
     * @param filename the desired filename
     * @param questions lists of questions
     * @throws IOException
     */
    public static void writeQuestions(String filename, ArrayList<Question> questions) throws IOException
    {
        //creates a file with a desired file name
        BufferedWriter fileWriter  = new BufferedWriter(new OutputStreamWriter(
                   new FileOutputStream(filename + ".txt")));

        //writes all questions to the file
        for (int i = 0; i < questions.size(); i++)
        {
           fileWriter.write(questions.get(i).generateQuestion()
                   .replaceAll("\n", System.getProperty("line.separator")));
           fileWriter.newLine();
        }

        //close the file
        fileWriter.close();
    }

    /**
     * This method reads all the questions from the file
     *
     * @param filename target filename
     * @param showAnswer if user want to show answer or not
     * @return the lists of questions
     * @throws IOException
     */
    public static String readQuestions(String filename, boolean showAnswer) throws IOException
    {
        //load up the target file
        BufferedReader fileReader = new BufferedReader(new FileReader(filename + ".txt"));

        //use a StringBuilder to store all the text
        StringBuilder questionBuilder = new StringBuilder();

        //use a string to go through file line by line
        String lineReader;

        //if the user wants to show answers
        //add answers to the output StringBuilder
        if (showAnswer)
        {
            while ((lineReader = fileReader.readLine()) != null)
            {
                questionBuilder.append(lineReader)
                                .append("\n");
            }
            return questionBuilder.toString();
        }

        //if the user doesn't want to show answers
        //hide answers
        else
        {
            while ((lineReader = fileReader.readLine()) != null)
            {
                String trimmedLine = lineReader.trim();
                if (!trimmedLine.startsWith("answer"))
                {
                    questionBuilder.append(lineReader)
                            .append("\n");
                }

                questionBuilder.append("");
            }

            //close the file
            fileReader.close();

            //return the lists of questions
            return questionBuilder.toString();
        }
    }
}
