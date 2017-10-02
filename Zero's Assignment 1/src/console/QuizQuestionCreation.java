package console;

import Questions.MultipleAnswers;
import Questions.MultipleChoice;
import Questions.Question;
import Questions.TrueFalse;

public class QuizQuestionCreation
{

    private static final int TF_USER_CHOICE = 1;
    private static final int MC_USER_CHOICE = 2;
    private static final int MA_USER_CHOICE = 4;

    public static void main(String[] args)
    {
        String tfQuestion = "Mount Everest is the closest mountain to the moon.";
        boolean tfAnswer = false;
        TrueFalse trueFalse = new TrueFalse(tfQuestion, tfAnswer);
        System.out.println(trueFalse.generateQuestion());

        System.out.println(answerChecker(TF_USER_CHOICE, trueFalse));

        System.out.println();

        String mcQuestion = "Which of the following states is the only"
                + "\nstate in the eastern hemisphere?";
        String[] choices = {"California", "Maine", "Alaska", "Florida"};
        int mcAnswer = 4;
        MultipleChoice multipleChoice = new MultipleChoice(mcQuestion, choices, mcAnswer);
        System.out.println(multipleChoice.generateQuestion());

        System.out.println(answerChecker(MC_USER_CHOICE, multipleChoice));

        System.out.println();

        String maQuestion = "The seven largest countries of the world"
                + "\ncover more than half the planets territories."
                + "\nFive of these seven include: USA, Russia, China,"
                + "\nAustralia and Brazil. Select the remaining nations.";
        String[] choicePool = {"Argentina", "Nigeria", "Iceland", "Canada"};
        int[] answerPool = {1, 4};
        MultipleAnswers multipleAnswers = new MultipleAnswers(maQuestion, choicePool, answerPool);
        System.out.println(multipleAnswers.generateQuestion());

        System.out.println(answerChecker(MA_USER_CHOICE, multipleAnswers));
    }

    private static String answerChecker(int choice, Question q)
    {
        System.out.println("\nYou chose " + choice);
        if (q.isAnswer(choice))
        {
            return "You are correct!";
        }
        else
        {
            return "You are incorrect!";
        }
    }
}