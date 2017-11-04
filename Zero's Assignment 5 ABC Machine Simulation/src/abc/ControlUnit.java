package abc;

import abc.ABCMachine;

public class ControlUnit
{
    //masks for decoding instruction code
    private static final short OP_CODE_MASK = -8192;            //111 000 000 000 0000
    private static final short SOURCE_REGISTER_1_MASK = 7168;   //000 111 000 000 0000
    private static final short SOURCE_REGISTER_2_MASK = 896;    //000 000 111 000 0000
    private static final short DESTINATION_REGISTER_MASK = 112; //000 000 000 111 0000
    private static final short ADDRESS_MASK = 15;               //000 000 000 000 1111

	private ABCMachine machine;

	public ControlUnit(ABCMachine machine)
	{
		this.machine = machine;
	}

	public void startProcessing()
	{
        /*
         * Your program should loop here and continuously
         * fetch, decode and execute instructions that are
         * stored in the ABCMachine. The program should
         * "halt" when it reaches an instruction that is
         * zero in binary.
         */


        short instruction;
        short[] instructionParts;

        while (true)
        {
            instruction = fetch();
            if (instruction == 0)
            {
                break;
            }

            else
            {
                instructionParts = decode(instruction);

                execute(instructionParts);
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
    private short[] decode(short instruction)
    {
        short opCode = (short)(instruction & OP_CODE_MASK);
        short sourceRegister1 = (short)(instruction & SOURCE_REGISTER_1_MASK);
        short sourceRegister2 = (short)(instruction & SOURCE_REGISTER_2_MASK);
        short destinationRegister = (short)(instruction & DESTINATION_REGISTER_MASK);
        short address = (short)(instruction & ADDRESS_MASK);

        return new short[]{opCode, sourceRegister1, sourceRegister2, destinationRegister, address};
    }

    //this method executes the instruction code
    private void execute(short[] instructionCodes)
    {

    }
}
