package questions;

/*
 * Hanchen (Zero) Liu
 * 10/03/2017
 * MultipleAnswers.java
 * This is the MultipleAnswers class that extends from the Question class
 * and creates a multiple answers question object
 */

/**
 * This is the MultipleAnswers class that extends from the Question class
 * and creates a multiple answers question object
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class MultipleAnswers extends Question
{
    //takes a string array to store available options
    private String[] options;

    //takes an int array to store correct answers
    private int[] answers;

    /**
     * Creates a multiple answers question with a question description,
     * a list of available options, and a list of correct answers
     *
     * @param message question description
     * @param options available options
     * @param answers correct answers
     */
    public MultipleAnswers(String message, String[] options, int[] answers)
    {
        super(message);

        this.options = options;
        this.answers = answers;
    }

    /**
     * Generates a question with a description,
     * a list of options and a list of answers
     *
     * @return the question description and the answer
     */
    @Override
    public String generateQuestion()
    {
        //use string builder to improve performance
        StringBuilder tempOptions = new StringBuilder();
        for (int i = 0; i < options.length; i++)
        {
            tempOptions.append("\n")
                    .append(i + 1)
                    .append(". ")
                    .append(options[i]);
        }

        StringBuilder tempAnswers = new StringBuilder();
        for (int j = 0; j < answers.length; j++)
        {
            tempAnswers.append("\nanswer: ")
                    .append(options[answers[j] - 1]);
        }

        return new StringBuilder().append(getMessage())
                .append(tempOptions)
                .append("\n")
                .append(tempAnswers)
                .append("\n")
                .toString();
    }

    /**
     * check user's choice with the stored answer to see if user is correct
     *
     * @param choice user's choice
     * @return true if user's choice is the same as the stored answer otherwise false
     */
    @Override
    public boolean isAnswer(int choice)
    {
        for (int i : answers)
        {
            if (choice == i)
            {
                return true;
            }
        }

        return false;
    }
}
