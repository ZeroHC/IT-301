package test;

import abc.ABCMachine;

/*
 * Hanchen (Zero) Liu
 * 11/10/2017
 * ABCDriver.java
 *
 * This class is the test class for ABC Machine simulation
 */

/**
 * This class is the test class for ABC Machine simulation
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class ABCDriver
{
    /**
     * this is the main method that simulates the ABC Machine
     *
     * @param args command line args
     */
	public static void main(String[] args)
	{
		//create a new ABCMachine and pass it a new program to run
		ABCMachine testMachine1 = new ABCMachine("programs/program1.abc");
        ABCMachine testMachine2 = new ABCMachine("programs/program2.abc");
        ABCMachine testMachine3 = new ABCMachine("programs/program3.abc");

		//print out the registers and memory after the program runs
        printTestResult(testMachine1);
        System.out.println();
        printTestResult(testMachine2);
        System.out.println();
        printTestResult(testMachine3);
    }

    //this method will print out a simple simulation result of ABC Machine
	private static void printTestResult(ABCMachine testMachine)
    {
        //starts up the ABC Machine
        testMachine.runProgram();

        //prints out the registers
	    System.out.println("Register dump: ");
	    testMachine.printRegisters();

        System.out.println();

        //prints out the memories
	    System.out.println("Memory dump:");
	    testMachine.printMemory();
    }
}
