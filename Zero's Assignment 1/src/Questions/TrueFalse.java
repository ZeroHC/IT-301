package Questions;

/*
 * Hanchen (Zero) Liu
 * 10/03/2017
 * TrueFalse.java
 * This is the TrueFalse class that extends from the Question class
 * and creates a true false question object
 */

/**
 * @author Hanchen (Zero) Liu
 * @version 1.0
 *
 * This is the TrueFalse class that extends from the Question class
 * and creates a true false question object
 */
public class TrueFalse extends Question
{
    //takes a boolean to store the correct answer
    private boolean answer;

    /**
     * Creates a TrueFalse question with a description and an answer
     * @param message question description
     * @param answer the question answer
     */
    public TrueFalse(String message, boolean answer)
    {
        super(message);

        this.answer = answer;
    }

    /**
     * Generates a question with a description and the answer
     * @return the question description and the answer
     */
    @Override
    public String generateQuestion()
    {
        //use string builder to improve performance
        return new StringBuilder().append(getMessage())
                                    .append("\n1. false\n2. true \n\nanswer: ")
                                    .append(answer)
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
        int correct = 0;
        if (answer)
        {
            correct = 1;
        }

        return choice - 1 == correct;
    }
}
