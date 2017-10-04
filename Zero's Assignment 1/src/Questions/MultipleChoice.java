package Questions;

/*
 * Hanchen (Zero) Liu
 * 10/03/2017
 * MultipleChoice.java
 * This is the MultipleChoice class that extends from the Question class
 * and creates a multiple choice question object
 */

/**
 * This is the MultipleChoice class that extends from the Question class
 * and creates a multiple choice question object
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class MultipleChoice extends Question
{
    //takes a string array to store available options
    private String[] options;

    //takes an int to store the correct answer
    private int answer;

    /**
     * Creates a multiple choice question with a description,
     * a list of options, and the answer
     *
     * @param message question description
     * @param options list of options
     * @param answer question answer
     */
    public MultipleChoice(String message, String[] options, int answer)
    {
        super(message);

        this.options = options;
        this.answer = answer;
    }

    /**
     * Generates a question with a description,
     * a list of options and the answer
     *
     * @return the question description and the answer
     */
    @Override
    public String generateQuestion()
    {
        //use string builder to improve performance
        StringBuilder tempSB = new StringBuilder();

        for (int i = 0; i < options.length; i++)
        {
            tempSB.append("\n")
                    .append(i + 1)
                    .append(". ")
                    .append(options[i]);
        }

        return new StringBuilder().append(getMessage())
                .append(tempSB)
                .append("\n\nanswer: ")
                .append(options[answer - 1])
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
        return choice == answer;
    }
}
