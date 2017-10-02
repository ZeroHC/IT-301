package Questions;

public class MultipleChoice extends Question
{
    private String[] options;
    private int answer;

    public MultipleChoice(String message, String[] options, int answer)
    {
        super(message);

        this.options = options;
        this.answer = answer;
    }

    @Override
    public String generateQuestion()
    {
        String temp = "";
        for (int i = 0; i < options.length; i++)
        {
            temp = temp + "\n" + (i + 1) + " " + options[i];
        }
        return getMessage() + temp + "\n\nanswer: " + options[answer - 1];
    }

    @Override
    public boolean isAnswer(int choice)
    {
        if (choice == answer) return true;
        else return false;
    }
}
