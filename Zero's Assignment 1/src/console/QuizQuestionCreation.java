package console;

import Questions.MultipleAnswers;
import Questions.MultipleChoice;
import Questions.Question;
import Questions.TrueFalse;

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
 * This is the TrueFalse class that extends from the Question class
 * and creates a true false question object
 */
public class QuizQuestionCreation
{
    //initiate some global constants for testing
    private static final int TF_USER_CHOICE = 1;
    private static final int MC_USER_CHOICE = 2;
    private static final int MA_USER_CHOICE = 4;

    /**
     * main method for testing
     *
     * @param args
     */
    public static void main(String[] args)
    {
        //initiate the question description
        String tfQuestion = "Mount Everest is the closest mountain to the moon.";
        //initiate the answer
        boolean tfAnswer = false;

        //generate a true false question using the TrueFalse question object
        TrueFalse trueFalse = new TrueFalse(tfQuestion, tfAnswer);

        //prints out the formatted question
        System.out.println(trueFalse.generateQuestion());

        //checks and prints out the result of user's choice
        System.out.println(answerChecker(TF_USER_CHOICE, trueFalse));
        System.out.println();

        //initiate the question description with string builder
        StringBuilder mcQuestion = new StringBuilder("Which of the following states is the only")
                .append("\nstate in the eastern hemisphere?");
        //initiate the list of available options
        String[] choices = {"California", "Maine", "Alaska", "Florida"};
        //initiate the answer
        int mcAnswer = 4;

        //generate a multiple choice question using the MultipleChoice question object
        MultipleChoice multipleChoice = new MultipleChoice(mcQuestion.toString(), choices, mcAnswer);

        //prints out the formatted question
        System.out.println(multipleChoice.generateQuestion());

        //checks and prints out the result of user's choice
        System.out.println(answerChecker(MC_USER_CHOICE, multipleChoice));
        System.out.println();

        //initiate the question description with string builder
        StringBuilder maQuestion =  new StringBuilder("The seven largest countries of the world")
                .append("\ncover more than half the planets territories.")
                .append("\nFive of these seven include: USA, Russia, China,")
                .append("\nAustralia and Brazil. Select the remaining nations.");

        //initiate the list of available options
        String[] choicePool = {"Argentina", "Nigeria", "Iceland", "Canada"};

        //initiate the list of correct answers
        int[] answerPool = {1, 4};

        //generate a multiple answers question using the MultipleAnswers question object
        MultipleAnswers multipleAnswers = new MultipleAnswers(maQuestion.toString(), choicePool, answerPool);

        //prints out the formatted question
        System.out.println(multipleAnswers.generateQuestion());

        //checks and prints out the result of user's choice
        System.out.println(answerChecker(MA_USER_CHOICE, multipleAnswers));
    }

    //this method checks user's choice with the stored answer(s), and gives out a result
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