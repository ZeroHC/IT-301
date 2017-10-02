package Questions;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;

public class MultipleAnswers extends Question
{
    private String[] options;
    private int[] answers;

    public MultipleAnswers(String message, String[] options, int[] answers)
    {
        super(message);

        this.options = options;
        this.answers = answers;
    }

    @Override
    public String generateQuestion()
    {
        String tempOptions = "";
        for (int i = 0; i < options.length; i++)
        {
            tempOptions = tempOptions + "\n" + (i + 1) + " " + options[i];
        }

        String tempAnswers = "";
        for (int j = 0; j < answers.length; j++)
        {
            tempAnswers = tempAnswers + "\nanswer: " + options[answers[j] - 1];
        }

        return getMessage() + tempOptions + "\n" + tempAnswers;
    }

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
