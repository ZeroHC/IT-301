package abc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the small ABC Machine. The simple architecture
 * has a control unit and alu, as well as 8 registers. Memory addresses
 * use 4 bits for a total of 16 memory locations and each word size is
 * 16 bits.
 * 
 * (DO NOT ALTER THIS FILE!)
 * 
 * @author Josh Archer
 * @version 1.0
 */
public class ABCMachine
{
	//architecture attributes
	public static final int REGISTER_BITS = 3;
	public static final int NUM_REGISTERS = 8;
	public static final int MEM_ADDRESS_BITS = 4;
	public static final int NUM_MEMORY = 16;
	public static final int WORD_BITS = 16;
	
	//memory areas
	private byte pc = 0; //only 4 bits needed
	private short ir = 0; //16 bits for an instruction
	private short[] registers = new short[NUM_REGISTERS]; //8 registers
	private short[] memory = new short[NUM_MEMORY]; //16 memory locations
	
	//components 
	private ALU alu;
	private ControlUnit controlUnit;

	/**
	 * Creates a new ABC machine with a memory map that contains
	 * instructions in the lower memory addresses and data in 
	 * the higher memory addresses.
	 * 
	 * @param programFile a program with ABC machine instructions
	 */
	public ABCMachine(String programFile)
	{
		//primary components of the machine
		controlUnit = new ControlUnit(this);
		alu = new ALU();
		
		//load the program instructions and data into the memory
		//of the ABC machine
		String[] fileContents = readFile(programFile);
		loadMemory(fileContents);
	}
	
	//reads the machine instructions from the file specified
	private String[] readFile(String programFile)
	{
		ArrayList<String> lines = new ArrayList<String>();
		
		try (Scanner scanner = new Scanner(new File(programFile)))
		{
			while (scanner.hasNextLine())
			{
				lines.add(scanner.nextLine());
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Error reading from file: " + ex.getMessage());
		}
		
		return lines.toArray(new String[lines.size()]);
	}

	//loads each word from the file into memory
	private void loadMemory(String[] fileContents)
	{
		for (int i = 0; i < fileContents.length; i++)
		{
			//convert as an unsigned int (there is not a similar Short.parseUnsignedInt())
			memory[i] = (short) Integer.parseUnsignedInt(fileContents[i], 2);
		}
	}
	
	/**
	 * Passes control of the program to the control unit to fetch, decode
	 * and execute instructions.
	 */
	public void runProgram()
	{
		controlUnit.startProcessing();
	}
	
	/**
	 * Prints out the state of the registers of the ABC machine.
	 */
	public void printRegisters()
	{
		for (short register : registers)
		{
			String binaryString = Integer.toBinaryString(Short.toUnsignedInt(register));
			binaryString = padZeroes(binaryString, NUM_REGISTERS);
			System.out.println(binaryString);
		}
	}
	
	/**
	 * Prints out the memory of the ABC machine.
	 */
	public void printMemory()
	{
		for (short register : memory)
		{
			String binaryString = Integer.toBinaryString(Short.toUnsignedInt(register));
			binaryString = padZeroes(binaryString, NUM_MEMORY);
			System.out.println(binaryString);
		}
	}
	
	//this method ensures that each word printed has exactly 16 bits
	private String padZeroes(String binaryString, int max)
	{
		while (binaryString.length() < max)
		{
			binaryString = "0" + binaryString;
		}
		return binaryString;
	}
	
	/**
	 * Retrieves the ALU for the ABC machine.
	 * @return the arithmetic logic unit
	 */
	public ALU getAlu()
	{
		return alu;
	}

	/**
	 * Returns the registers of the ABC machine.
	 * 
	 * @return an array of 8 registers
	 */
	public short[] getRegisters()
	{
		return registers;
	}

	/**
	 * Returns the memory of the ABC machine.
	 * 
	 * @return an array of 16 memory locations
	 */
	public short[] getMemory()
	{
		return memory;
	}
	
	/**
	 * Retrieves the program counter of the ABC machine.
	 * 
	 * @return the address of the next instruction to execute.
	 */
	public byte getPc()
	{
		return pc;
	}

	/**
	 * Alters the program counter of the ABC machine.
	 * 
	 * @param pc a new address that contains an instruction to
	 *        execute next
	 */
	public void setPc(byte pc)
	{
		this.pc = pc;
	}

	/**
	 * Returns the instruction register for the ABC machine. This
	 * register holds the current instruction being decoded/executed.
	 * 
	 * @return the current instruction being used by the machine
	 */
	public short getIr()
	{
		return ir;
	}

	/**
	 * Loads a new instruction into the instruction register.
	 * 
	 * @param ir the next instruction to use
	 */
	public void setIr(short ir)
	{
		this.ir = ir;
	}
}
