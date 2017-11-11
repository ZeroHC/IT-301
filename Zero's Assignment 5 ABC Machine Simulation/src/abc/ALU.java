package abc;

/*
 * Hanchen (Zero) Liu
 * 11/10/2017
 * ALU.java
 *
 * This class represents an ALU in the ABC machine
 */

/**
 * This class represents an ALU in the ABC machine
 *
 * @author Hanchen (Zero) Liu
 * @version 1.0
 */
class ALU
{
    //private field
    private byte nzp;

    /**
     * this method adds two values from source register
     *
     * @param src1 a short type value of source register 1
     * @param src2 a short type value of source register 2
     *
     * @return result of src1 + src2
     */
    short add(short src1, short src2)
    {
        short result = (short)(src1 + src2);

        //check the nzp value
        checkNzp(result);

        return result;
    }

    /**
     * this method subtracts two values from source register
     *
     * @param src1 a short type value of source register 1
     * @param src2 a short type value of source register 2
     *
     * @return result of src1 - src2
     */
    short subtract(short src1, short src2)
    {
        short result = (short)(src1 - src2);

        //check the nzp value
        checkNzp(result);

        return result;
    }

    /**
     * this method multiplies two values from source register
     *
     * @param src1 a short type value of source register 1
     * @param src2 a short type value of source register 2
     *
     * @return result of src1 * src2
     */
    short multiply(short src1, short src2)
    {
        short result = (short)(src1 * src2);

        //check the nzp value
        checkNzp(result);

        return result;
    }

    /**
     * this method divides two values from source register
     *
     * @param src1 a short type value of source register 1
     * @param src2 a short type value of source register 2
     *
     * @return result of src1 / src2
     */
    short divide(short src1, short src2)
    {
        short result = (short)(src1/src2);

        //check the nzp value
        checkNzp(result);

        return result;
    }

    //this method checks the nzp value of a calculation result
    private void checkNzp(short checker)
    {
        if (checker < 0) nzp = 0b100;
        else if (checker == 0) nzp = 0b010;
        else nzp = 0b001;
    }

    /**
     * this method get the nzp value
     *
     * @return nzp value
     */
    public byte getNzp()
    {
        return nzp;
    }
}
