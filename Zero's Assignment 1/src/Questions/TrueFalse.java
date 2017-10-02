package Questions;

public class TrueFalse extends Question
{
    private boolean answer;

    public TrueFalse(String message, boolean answer)
    {
        super(message);

        this.answer = answer;
    }

    @Override
    public String generateQuestion()
    {
        return getMessage() + "\n1. false\n2. true \n\nanswer: " + answer;
    }

    @Override
    public boolean isAnswer(int choice)
    {
        int correct = 0;
        if (answer == true)
        {
            correct = 1;
        }

/*
        return new Long(choice - 1).equals(correct);
*/
        if (choice - 1 == correct) return true;
        else return false;
    }
}
