package abc;

import abc.ABCMachine;

public class ControlUnit
{
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
        while (!(machine.getIr() == 0))
        {
            fetch();
        }
	}

	private void fetch()
    {
        for (short register : machine.getRegisters())
        {
            byte address = 0;
            for (short memory : machine.getMemory())
            {
                if (register == memory)
                {
                    for (short memoryAddress: machine.getMemory())
                    {
                        if ((memoryAddress & address) == address)
                        {
                            machine.setIr(memoryAddress);
                        }
                    }
                }
                address++;
            }
        }
    }

    private void decode()
    {

    }

    private void execute()
    {

    }
}
