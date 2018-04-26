package model;

/** 
 * Exception class that is generated when assigning a value less than zero.
 *
 */
public class CalculatorException extends Exception
{

    public CalculatorException() 
    {
        super();
    }

    public CalculatorException(String message) 
    {
        super(message);
    }
}