package abc;

/*
 * Hanchen (Zero) Liu
 * 11/10/2017
 * ControlUnit.java
 *
 * This class represents a control unit in the ABC machine
 */

/**
 * This class represents a control unit in the ABC machine
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
public class ControlUnit
{
    //masks for decoding instruction code
    private static final int OP_CODE_MASK = 57344;            //111 000 000 000 0000
    private static final int SOURCE_REGISTER_1_MASK = 7168;   //000 111 000 000 0000
    private static final int SOURCE_REGISTER_2_MASK = 896;    //000 000 111 000 0000
    private static final int DESTINATION_REGISTER_MASK = 112; //000 000 000 111 0000
    private static final int ADDRESS_MASK = 15;               //000 000 000 000 1111

    //shifters for decoding instruction code
    private static final int OPCODE_SHIFTER = 13;
    private static final int SOURCE_REGISTER_1_SHIFTER = 10;
    private static final int SOURCE_REGISTER_2_SHIFTER = 7;
    private static final int DESTINATION_REGISTER_SHIFTER = 4;

    //private fields
    private ABCMachine machine;
    private short opCode;
    private short sourceRegister1;
    private short sourceRegister2;
    private short destinationRegister;
    private short address;

    /**
     * initialize a control unit object
     *
     * @param machine the ABC machine
     */
    public ControlUnit(ABCMachine machine)
	{
		this.machine = machine;
	}

    /**
     * this method will process through all program counter
     */
	public void startProcessing()
	{
        /*
         * Your program should loop here and continuously
         * fetch, decode and execute instructions that are
         * stored in the ABCMachine. The program should
         * "halt" when it reaches an instruction that is
         * zero in binary.
         */

        //use a short to store one instruction
        short instruction;

        //while
        while (true)
        {
            //fetch the code
            instruction = fetch();

            //if the instruction is zero, the program will halt
            if (instruction == 0)
            {
                break;
            }

            else
            {
                //decode the instruction
                decode(instruction);

                //execute the instruction
                execute();
            }
        }
	}

	//this method fetches thur the register and memory and returns one instruction code
	private short fetch()
    {
        byte pc = machine.getPc();
        machine.setIr(machine.getMemory()[pc]);
        machine.setPc((byte)(pc + 1));

        return machine.getIr();
    }

    //this method decodes the instruction code into 5 sub parts
    private void decode(short instruction)
    {
        //gets op code from instruction
        opCode = (short)((instruction & OP_CODE_MASK) >>> OPCODE_SHIFTER);

        //gets source register 1 from instruction
        sourceRegister1 = (short)((instruction & SOURCE_REGISTER_1_MASK) >>> SOURCE_REGISTER_1_SHIFTER);

        //gets source register 2 from instruction
        sourceRegister2 = (short)((instruction & SOURCE_REGISTER_2_MASK) >>> SOURCE_REGISTER_2_SHIFTER);

        //gets destination register from instruction
        destinationRegister = (short)((instruction & DESTINATION_REGISTER_MASK) >>> DESTINATION_REGISTER_SHIFTER);

        //gets the address from instruction
        address = (short)(instruction & ADDRESS_MASK);
    }

    //this method executes the instruction code
    private void execute()
    {
        //use a switch statement to check the instruction
        switch (opCode)
        {
            //if op code is 0, add value from source register 1 and 2
            case 0:
                machine.getRegisters()[destinationRegister] = machine.getAlu().add(machine.getRegisters()[sourceRegister1],
                        machine.getRegisters()[sourceRegister2]);
                break;

            //if op code is 1, subtract value from source register 1 and 2
            case 1:
                machine.getRegisters()[destinationRegister] = machine.getAlu().subtract(machine.getRegisters()[sourceRegister1],
                        machine.getRegisters()[sourceRegister2]);
                break;

            //if op code is 2, multiply value from source register 1 and 2
            case 2:
                machine.getRegisters()[destinationRegister] = machine.getAlu().multiply(machine.getRegisters()[sourceRegister1],
                        machine.getRegisters()[sourceRegister2]);
                break;

            //if op code is 3, divide value from source register 1 and 2
            case 3:
                machine.getRegisters()[destinationRegister] = machine.getAlu().divide(machine.getRegisters()[sourceRegister1],
                        machine.getRegisters()[sourceRegister2]);
                break;

            //if op code is 4, store value from register location to memory address
            case 4:
                machine.getMemory()[address]= machine.getRegisters()[sourceRegister1];
                break;

            //if op code is 5, load value from memory address to register location
            case 5:
                machine.getRegisters()[sourceRegister1] = machine.getMemory()[address];
                break;

            //if op code is 6, if the previous calculation's result is n z or p, move pc to the target address
            case 6:
                if ((sourceRegister1 & machine.getAlu().getNzp()) != 0)
                {
                    machine.setPc((byte)address);
                    break;
                }
                else break;

            //if op code is 7, move pc to the target address
            case 7: machine.setPc((byte)address);
                break;
        }
    }
}
