package test;

import abc.ABCMachine;

public class ABCDriver
{
	public static void main(String[] args)
	{
		//create a new ABCMachine and pass it a new program to run
		ABCMachine testMachine1 = new ABCMachine("programs/program1.abc");
        ABCMachine testMachine2 = new ABCMachine("programs/program2.abc");

		//print out the registers and memory after the program runs
        printTestResult(testMachine1);
        System.out.println();
        printTestResult(testMachine2);
    }

	private static void printTestResult(ABCMachine testMachine)
    {
        testMachine.runProgram();
        System.out.println("Memory dummy:");
        testMachine.printMemory();

        System.out.println();

        System.out.println("Register dummy: ");
        testMachine.printRegisters();
    }
}
