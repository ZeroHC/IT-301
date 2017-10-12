package questions;

/*
 * Hanchen (Zero) Liu
 * 10/03/2017
 * Question.java
 * This is the Question class that creates a question object
 */

/**
 * This is the Question class that creates a question object
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public abstract class Question
{
    //take a string to store the question description
    private String message;

    /**
     * Creates a Question with a question description
     * @param message the question description
     */
    public Question(String message)
    {
        this.message = message;
    }

    /**
     * gets the description of the question
     * @return the description for the question object
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * generate the question
     * @return formatted question
     */
    public abstract String generateQuestion();

    /**
     * check user's choice with the stored answer to see if user is correct
     *
     * @param choice user's choice
     * @return true if user's choice is the same as the stored answer otherwise false
     */
    public abstract boolean isAnswer(int choice);
}
