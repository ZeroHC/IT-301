package console;

import helpers.IOUtilities;
import questions.MultipleAnswers;
import questions.MultipleChoice;
import questions.Question;
import questions.TrueFalse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Hanchen (Zero) Liu
 * 10/11/2017
 * CreationConsole.java
 * This is the CreationConsole class that helps user to create
 * question list, as well as reviewing the list of questions
 */

/**
 * This is the CreationConsole class that helps user to create
 * question list, as well as reviewing the list of questions
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class CreationConsole
{
    //initialize a global scanner for user input
    private static final Scanner INPUT_SCANNER = new Scanner(System.in);

    //initialize the menu choice
    private static int menuChoice;

    /**
     * This is the main method that helps user to create quiz, show existing quiz
     * or exit
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        //prints out the welcome message
        System.out.println("Quiz Management System");
        System.out.println("**********************");
        System.out.println("This program allows you to create, save and show quizzes." +
                " Please use the menu below.");

        //use a while loop to show menu every time a task is complete
        while (menuChoice != 3)
        {
            //show menu
            menuChoice = showMenu();

            //if user choose 1 then go the quiz creation phase
            if (menuChoice == 1)
            {
                createQuiz();
            }

            //if user choose 2 then go the the quiz review phase
            else if (menuChoice == 2)
            {
                showQuiz();
            }
        }

        //exit out if user choose 3
        System.out.println("\nHave a great day!");
    }

    //this method shows the menu
    private static int showMenu()
    {
        System.out.println("\n1. Create quiz");
        System.out.println("2. Show quiz");
        System.out.println("3. Exit");

        menuChoice = INPUT_SCANNER.nextInt();
        INPUT_SCANNER.nextLine();

        return menuChoice;
    }

    //this method demonstrates the quiz creation menu
    private static void createQuiz() throws IOException
    {
        //ask for the quiz name which will be the filename
        System.out.println("\nEnter a name for the quiz:");
        String fileName = INPUT_SCANNER.nextLine();

        //ask for how many questions are going to be in this quiz
        System.out.println("\nHow many questions?");
        int numberOfQuestions = INPUT_SCANNER.nextInt();
        INPUT_SCANNER.nextLine();

        //initialize an array list to store all questions
        ArrayList<Question> questions = new ArrayList<>();

        //add questions to the array list
        questions = addQuestions(questions, numberOfQuestions);

        //calls the writeQuestions method to write to a file
        IOUtilities.writeQuestions(fileName, questions);
    }

    //this method initializes questions and adds questions
    private static ArrayList <Question> addQuestions(ArrayList <Question> questions, int numberOfQuestions)
    {
        //use a for loop to add number of questions that user wanted
        for (int i = 0; i < numberOfQuestions; i++)
        {
            //ask for the question type
            System.out.println("\nEnter a type of question (TrueFalse, " +
                    "MultipleChoice, MultipleAnswers):");
            String questionType = INPUT_SCANNER.nextLine();

            //ask for the question introduction
            System.out.println("\nEnter question introduction:");
            String questionDescription = INPUT_SCANNER.nextLine();

            //if the question type is true false, then add a true false question
            if (questionType.equals("TrueFalse"))
            {
                questions.add(addTrueFalse(questionDescription));
            }

            //if the question type is multiple choice or multiple answers, then add that questions
            else
            {
                questions.add(addMultiple(questions, questionType, questionDescription));
            }
        }

        //returns the list of questions
        return questions;
    }

    //this method creates a true false question
    private static Question addTrueFalse(String questionDescription)
    {
        //ask for the correct answer
        System.out.println("\nIs the answer true/false?");
        boolean answer = INPUT_SCANNER.nextBoolean();
        INPUT_SCANNER.nextLine();

        return new TrueFalse(questionDescription, answer);
    }

    //this method is used to create either a multiple choice or multiple answers question
    private static Question addMultiple(ArrayList <Question> questions, String questionType, String questionDescription)
    {
        //ask for number of choices to show
        System.out.println("\nNumber of choices:");
        int numberOfChoice = INPUT_SCANNER.nextInt();
        INPUT_SCANNER.nextLine();

        //initialize a string array to store all choices
        String[] choices = new String[numberOfChoice];

        //use a for loop to ask each possible choice
        for (int j = 0; j < numberOfChoice; j++)
        {
            System.out.println("\nEnter answer " + (j+1) + ":");
            choices[j] = INPUT_SCANNER.nextLine();
        }

        //if the question type is multiple choice, then add a multiple choice question
        if (questionType.equals("MultipleChoice"))
        {
            return addMultipleChoice(questionDescription, choices);
        }

        //if the question type is multiple answers, then add a multiple answers question
        else
        {
            return addMultipleAnswer(questionDescription, choices);
        }
    }

    //this method creates a multiple choice question
    private static Question addMultipleChoice(String questionDescription, String[] choices)
    {
        //ask for the correct answer
        System.out.println("\nEnter the correct answer (1-" + choices.length + "):");
        int answer = INPUT_SCANNER.nextInt();
        INPUT_SCANNER.nextLine();

        return new MultipleChoice(questionDescription, choices, answer);
    }

    //this method creates a multiple answers question
    private static Question addMultipleAnswer(String questionDescription, String[] choices)
    {
        //ask for the correct answers
        System.out.println("\nEnter each correct answer separated by commas, " +
                "for example \"1,3,4\" or \"2,4\":");

        String temp = INPUT_SCANNER.nextLine();

        //use an int array to store all the correct answers
        int[] answers = Arrays.stream(temp.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        return new MultipleAnswers(questionDescription, choices, answers);
    }

    // this method demonstrates the show quiz menu
    private static void showQuiz() throws IOException
    {
        //ask for the quiz/file name
        System.out.println("\nEnter a name for the quiz:");
        String filename = INPUT_SCANNER.nextLine();

        //ask if user want to show answer or not
        System.out.println("\nShow answers? (true/false)");
        boolean showAnswer = INPUT_SCANNER.nextBoolean();
        INPUT_SCANNER.nextLine();

        System.out.println();

        //calls the readQuestions method to read from the target file and print it out to console
        System.out.println(IOUtilities.readQuestions(filename, showAnswer));
    }
}
