package Questions;

public abstract class Question
{
    private String message;

    public Question(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public abstract String generateQuestion();

    public abstract boolean isAnswer(int choice);
}
