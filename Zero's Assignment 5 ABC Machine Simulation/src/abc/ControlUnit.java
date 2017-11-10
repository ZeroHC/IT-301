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

    private static final int OPCODE_SHIFTER = 13;
    private static final int SOURCE_REGISTER_1_SHIFTER = 10;
    private static final int SOURCE_REGISTER_2_SHIFTER = 7;
    private static final int DESTINATION_REGISTER_SHIFTER = 4;

    private ABCMachine machine;
    private short opCode;
    private short sourceRegister1;
    private short sourceRegister2;
    private short destinationRegister;
    private short address;

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

        while (true)
        {
            instruction = fetch();
            if (instruction == 0)
            {
                break;
            }

            else
            {
                decode(instruction);

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
        opCode = (short)((instruction & OP_CODE_MASK) >>> OPCODE_SHIFTER);
        sourceRegister1 = (short)((instruction & SOURCE_REGISTER_1_MASK) >>> SOURCE_REGISTER_1_SHIFTER);
        sourceRegister2 = (short)((instruction & SOURCE_REGISTER_2_MASK) >>> SOURCE_REGISTER_2_SHIFTER);
        destinationRegister = (short)((instruction & DESTINATION_REGISTER_MASK) >>> DESTINATION_REGISTER_SHIFTER);
        address = (short)(instruction & ADDRESS_MASK);
    }

    //this method executes the instruction code
    private void execute()
    {
        if (opCode != 0)
        {

        }
    }
}
