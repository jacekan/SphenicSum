package test;

import model.CalculatorLogic;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test methods checking model's methods.
 * 
 */
public class CalculatorTest
{
    @Test
    public void testCalculateSum() 
    {
        CalculatorLogic calculatorLogic = new CalculatorLogic();
        
        calculatorLogic.calculateSum(-10);
        assertEquals("", 0, calculatorLogic.getResult());
        
        calculatorLogic.calculateSum(-1);
        assertEquals("", 0, calculatorLogic.getResult());
        
        calculatorLogic.calculateSum(0);
        assertEquals("", 0, calculatorLogic.getResult());
        
        calculatorLogic.calculateSum(1);
        assertEquals("", 30, calculatorLogic.getResult());
        
        calculatorLogic.calculateSum(2);
        assertEquals("", 72, calculatorLogic.getResult());
        
        calculatorLogic.calculateSum(10);
        assertEquals("", 847, calculatorLogic.getResult());
    }
    
    @Test
    public void testToString() 
    {
        CalculatorLogic calculatorLogic = new CalculatorLogic();
        
        assertEquals("", "0", calculatorLogic.toString());
        
        calculatorLogic.calculateSum(-10);
        assertEquals("", "0", calculatorLogic.toString());
        
        calculatorLogic.calculateSum(-1);
        assertEquals("", "0", calculatorLogic.toString());
        
        calculatorLogic.calculateSum(0);
        assertEquals("", "0", calculatorLogic.toString());
        
        calculatorLogic.calculateSum(1);
        assertEquals("", "30", calculatorLogic.toString());
        
        calculatorLogic.calculateSum(2);
        assertEquals("", "72", calculatorLogic.toString());
        
        calculatorLogic.calculateSum(10);
        assertEquals("", "847", calculatorLogic.toString());
    }
    
    @Test(timeout = 100)
    public void testPerformance() 
    {
        CalculatorLogic calculatorLogic = new CalculatorLogic();
         
        calculatorLogic.calculateSum(1000);
    }
}
